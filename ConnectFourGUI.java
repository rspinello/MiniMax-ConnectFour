//Robert Spinello
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*; 

public class ConnectFourGUI extends JFrame
{
  private JLabel[][] labelArray = new JLabel[6][7];
  private JButton[] buttonArray = new JButton[7]; 
  private JPanel top, center, bottom; 
  private Board game = new Board(), blank = new Board();
  private JLabel announce; 
  private JButton newGame, exit; 
  
    
    
  public ConnectFourGUI()
  {
    super("Connect Four");
    setBounds(0,0,700,715);
    
    top = new JPanel(new FlowLayout());
    announce = new JLabel("Player Goes First");
    //add(announce, BorderLayout.NORTH);
    announce.setHorizontalAlignment(JLabel.CENTER);
    announce.setFont(new Font("Serif", Font.BOLD, 35));
    exit = new JButton("Exit");
    newGame = new JButton("New Game");
    top.add(newGame);
    top.add(announce);
    top.add(exit);
    top.setBackground(Color.GRAY);
    add(top, BorderLayout.NORTH);
    
    center = new JPanel(new GridLayout(6,7));
    
    for(int i = 0; i < 6; i ++)
    {
      for(int j = 0; j < 7; j++)
      {
        labelArray[i][j] = new JLabel(new ImageIcon("EmptyCell.png"));
      }//end for 
    }//end for

    for(int i = 0; i < 6; i++)
    {
      for(int j = 0; j < 7; j++)
      {
        center.add(labelArray[i][j]);
      }//end inner for 
    }//end outer for
    center.setBackground(Color.GRAY);
    add(center, BorderLayout.CENTER); 
    
    bottom = new JPanel(new GridLayout(1,7));
    for(int i = 0; i < 7; i ++)
    {
      buttonArray[i] = new JButton("" + i);
    }//end for
    for(int i = 0; i < 7; i ++)
    {
      bottom.add(buttonArray[i]);
    }//end for
    bottom.setBackground(Color.GRAY);
    add(bottom, BorderLayout.SOUTH);

    exit.addActionListener(new ButtonListener());
    newGame.addActionListener(new ButtonListener());
    buttonArray[0].addActionListener(new ButtonListener());
    buttonArray[1].addActionListener(new ButtonListener());
    buttonArray[2].addActionListener(new ButtonListener());
    buttonArray[3].addActionListener(new ButtonListener());
    buttonArray[4].addActionListener(new ButtonListener());
    buttonArray[5].addActionListener(new ButtonListener());
    buttonArray[6].addActionListener(new ButtonListener());
    
    setResizable(false);
    setVisible(true);
  }//end default 
  private class ButtonListener implements ActionListener // responds to button event
  {
    public void actionPerformed(ActionEvent e) // ActionListener Interface method
    {
      if(e.getSource() == exit)
        System.exit(0);
      if(e.getSource() == newGame)
      {
        for(int i = 0; i < 6; i ++)
        {
          for(int j = 0; j < 7; j++)
          {
            labelArray[i][j].setIcon(new ImageIcon("EmptyCell.png"));
          }//end for 
        }//end for
        game = new Board(blank);
        for(int i = 0; i < 7; i++)
        {
          buttonArray[i].setEnabled(true);
        }//end for
        
        announce.setText("Player Goes First");
      }//end if 
      if(e.getSource() == buttonArray[0])
      {
        Move m = new Move(0);
        playGame(m);
      }//end if 
      if(e.getSource() == buttonArray[1])
      {
        Move m = new Move(1);
        playGame(m);
      }//end if 
      if(e.getSource() == buttonArray[2])
      {
        Move m = new Move(2);
        playGame(m);
      }//end if 
      if(e.getSource() == buttonArray[3])
      {
        Move m = new Move(3);
        playGame(m);
      }//end if 
      if(e.getSource() == buttonArray[4])
      {
        Move m = new Move(4);
        playGame(m);
      }//end if 
      if(e.getSource() == buttonArray[5])
      {
        Move m = new Move(5);
        playGame(m);
      }//end if 
      if(e.getSource() == buttonArray[6])
      {
        Move m = new Move(6);
        playGame(m);
      }//end if 
    }//end method 
  }//end class
  
  
  public void playGame(Move choice)
  {
    Stack<Move> movement = new Stack<Move>();
    Point p = game.play(choice);
    labelArray[(int)p.getX()][(int)p.getY()].setIcon(new ImageIcon("RedCell.png"));
    
    game.legalMoves(movement);
    for(int i = 0; i < 7; i++)
    {
      if(game.columnFull(i) == true)
        buttonArray[i].setEnabled(false);
    }//end for 
    if(game.boardFull() == true)
        announce.setText("Its a Tie!");
    
    
    if(game.winner() == 'r')
    {
      announce.setText("Player Wins!");
      for(int i = 0; i < 7; i++)
      {
        buttonArray[i].setEnabled(false);
      }//end for 
    }//end if 
    else 
    {
      Move recommendedMove = new Move();
      int depth = 3;
      int value = MiniMax.lookAhead(game, depth, recommendedMove);
      announce.setText("Computer selected column " + recommendedMove.getMove());
      p = game.play(recommendedMove);
      labelArray[(int)p.getX()][(int)p.getY()].setIcon(new ImageIcon("BlackCell.png"));
      
      if(game.winner() == 'b')
      {
        announce.setText("Computer Wins!");
        for(int i = 0; i < 7; i++)
        {
          buttonArray[i].setEnabled(false);
        }//end for 
      }//end if 
      
      game.legalMoves(movement);
      for(int i = 0; i < 7; i++)
      {
        if(game.columnFull(i) == true)
          buttonArray[i].setEnabled(false);
      }//end for 
      
      if(game.boardFull() == true)
        announce.setText("Its a Tie!");
    }//end else
    
    game.changeTurn();
    
  }//end method
  
  
  
  public static void main(String[] args)
  {
    ConnectFourGUI cf = new ConnectFourGUI(); 
  }//end main 
  
}//end class 