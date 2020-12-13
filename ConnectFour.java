import java.util.*;

public class ConnectFour
{
  private Board game;
  private Scanner input;
  private boolean done; 
  
  public ConnectFour()
  {
    game = new Board(); 
    playGame();
  }//end default
  
  public void playGame()
  {
    input = new Scanner(System.in);
    Move choice;
    Stack<Move> movement = new Stack<Move>(); 
    game.printBoard(); 
    
    while(!done)
    {
      game.legalMoves(movement);
      
      System.out.println("Choose a column:");
      choice = new Move(input.nextInt()); 
      
      game.play(choice);
      game.printBoard(); 
      
      if(game.winner() == 'r')
      {
        done = true; 
        System.out.println("Player Wins!");
      }//end 
      else 
      {
        //game.changeTurn();
        
        Move recommendedMove = new Move();
        int depth = 5;
        int value = MiniMax.lookAhead(game, depth, recommendedMove);
        System.out.println("Computer selected column " + recommendedMove.getMove());
        game.play(recommendedMove);
        game.printBoard();
        
        if(game.winner() == 'b')
        {
          done = true;
          System.out.println("Computer Wins!");
        }//end if 
      }//end else
      
      game.changeTurn();
      
    }//end while 
  }//end method

  public static void main(String[] args)
  {
    ConnectFour cf = new ConnectFour(); 
  }//end main
  
}//end class 