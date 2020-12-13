//Robert Spinello
import java.awt.*; 
import java.util.*; 

public class Board
{
  public char[][] board; 
  public int turn; 
  
  public Board()
  {
    board = new char[6][7];
    
    for (int i = 0; i < 6; i++)
    {
      for (int j = 0; j < 7; j++)
      {
        board[i][j] = 'e';
      }//end inner
    }//end outer
  }//end default
  
  public Board(Board b)  
  {
    board = new char[6][7];
    
    for (int i = 0; i < 6; i++)
    {
      for (int j = 0; j < 7; j++)
      {
      board[i][j] = b.board[i][j];
      }//end inner 
    }//end outer
    
    turn = b.turn;
  }//end constructor 
  
  
  //checking for winner 
  public char winner()
  {
    //checks horizontal b
    for (int row=0; row<=2; row++)
    {
      for (int col=0; col<=3; col++)
      {
        if (board[row][col] == 'b' &&
            board[row+1][col+1] == 'b' &&
            board[row+2][col+2] == 'b'&&
            board[row+3][col+3] == 'b')
          return 'b';
      }//end inner 
    }//end outer 
    
    for (int row=0; row<=2; row++)
    {
      for (int col=3; col<=6; col++)
      {
      if (board[row][col] =='b' &&
          board[row+1][col-1] == 'b' &&
            board[row+2][col-2] == 'b' &&
          board[row+3][col-3] == 'b')
        return 'b';
      }//end outer 
    }//end inner 
    
    //checks H + V for b
    for (int row=0; row<6; row++)
    {
      for (int col=0; col<=3; col++)
      {
      if (board[row][col] == 'b' &&
          board[row][col+1] == 'b' &&
          board[row][col+2] == 'b' &&
          board[row][col+3] == 'b')
        return 'b'; 
      }//end inner 
    }//end outer 
    
    for (int col= 0; col< 7; col++)
    {
      for (int row = 0; row < 3; row++)
      {
      if (board[row][col] == 'b' &&
          board[row+1][col] == 'b' &&
          board[row+2][col] == 'b' &&
          board[row+3][col] == 'b' ) 
        return 'b'; 
      }//end inner 
    }//end outer 
    
    
     //checks horizontal r
    for (int row=0; row<=2; row++)
    {
      for (int col=0; col<=3; col++)
      {
        if (board[row][col] == 'r' &&
            board[row+1][col+1] == 'r' &&
            board[row+2][col+2] == 'r'&&
            board[row+3][col+3] == 'r')
          return 'r';
      }//end inner 
    }//end outer 
    
    for (int row=0; row<=2; row++)
    {
      for (int col=3; col<=6; col++)
      {
      if (board[row][col] =='r' &&
          board[row+1][col-1] == 'r' &&
            board[row+2][col-2] == 'r' &&
          board[row+3][col-3] == 'r')
        return 'r';
      }//end outer 
    }//end inner 
    
    //checks H + V for r
    for (int row=0; row<6; row++)
    {
      for (int col=0; col<=3; col++)
      {
      if (board[row][col] == 'r' &&
          board[row][col+1] == 'r' &&
          board[row][col+2] == 'r' &&
          board[row][col+3] == 'r')
        return 'r'; 
      }//end inner 
    }//end outer 
    
    for (int col= 0; col< 7; col++)
    {
      for (int row = 0; row < 3; row++)
      {
      if (board[row][col] == 'r' &&
          board[row+1][col] == 'r' &&
          board[row+2][col] == 'r' &&
          board[row+3][col] == 'r' ) 
        return 'r'; 
      }//end inner 
    }//end outer    
    
    return 'x'; 
  }//end method
  
  public boolean done()
  {
    return winner() !='x'; 
  }//end method 
  
  
  
  //playing the game 
  public Point play(Move tryit)
  {
    Point p = new Point();
      for(int i = 5; i >= 0; i --)
      { 
        if(board[i][tryit.getMove()] != 'b' && board[i][tryit.getMove()] != 'r')
        {
          p.move(i,tryit.getMove());  
          if(getTurn() == 1)
            board[i][tryit.getMove()] = 'b';
          else 
            board[i][tryit.getMove()] = 'r';
          break;
        }//end if 
      }//end for
    
    return p; 
    
  }//end method
  

  public int evaluate()
  {
    //the large the sum, b is more likely to win the board
    //the smaller the sum, r is more likely to win the board
    int[][] eval = {{3,4,5,7,5,4,3},
                    {4,6,8,10,8,6,4},
                    {5,8,11,13,11,8,5},
                    {5,8,11,13,11,8,5},
                    {4,6,8,10,8,6,4},
                    {3,4,5,7,5,4,3}};
    int sum = 0;
    if(winner() == 'b')
      return 10000;
    else if(winner() == 'r')
      return -10000;
    
    for (int i = 0; i < 6; i++)
    {
      for (int j = 0; j < 7; j++)
      {
        if(board[i][j] == 'b')
          sum = sum + eval[i][j];
        else if(board[i][j] == 'r')
          sum = sum - eval[i][j];
      }//end inner 
    }//end outer
    
     return sum;
    
  }//end method 
  
  
  
  //turn methods
  public void changeTurn()
  {
    turn = (turn+1)%2;
  }//end method
  
  public void setTurn(int t)
  {
    turn = t;
  }//end method
  
  public int getTurn()
  {
    return turn; 
  }//end method
  
  
  
  // move methods  
  public void legalMoves(Stack<Move> moves)
  {
    for(int i = 0; i < 7; i ++)
    {
      if(board[0][i] == 'e')
        moves.push(new Move(i));
    }//end for 
  }//end method 
  
  public boolean columnFull(int n)
  {
    if(board[0][n] != 'e')
      return true;
    return false;
  }//end method

  public boolean boardFull()
  {
    int count = 0;
    for(int i = 0; i < 7; i ++)
    {
      if(board[0][i] != 'e')
        count++;
    }//end for 
    if(count == 7)
      return true;
    return false; 
  }//end method
  
  public int worstCase() 
  {
    if (turn == 1) 
      return -10000;
    return 10000;
  }//end method 
  
  public boolean better(int value, int oldValue)
  {
    if(turn == 1)
      return (value >= oldValue);
    return (value <= oldValue);
  }//end method 
  
  
  //printing the board
  public void printBoard()
  {
    for (int i = 0; i < 6; i++)
    {
      for (int j = 0; j < 7; j++)
      {
      System.out.print(board[i][j] + " ");
      }//end inner 
      System.out.println();
    }//end outer
  }//end method
  

// test main
//  public static void main(String[] args)
//  {
//    int n;
//    Move choice;
//    Scanner input = new Scanner(System.in);
//    Board b = new Board(); 
//    Stack<Move> movement = new Stack<Move>(); 
//    
//    b.setTurn(1);
//    
//    while(!b.done())
//    {
//      b.printBoard();
//      b.legalMoves(movement);
//      do
//      {
//        System.out.println("Choose a column:");
//        choice = new Move(input.nextInt()); 
//      }while(b.checkMove(movement, choice));
//      
//      b.play(choice);
//      b.changeTurn();
//      
//      System.out.println(b.evaluate());
//   
//    }//end while
//    
//    b.printBoard();
//    
//    if(b.winner() == 'b')
//      System.out.println("BLACK WINS");
//    else 
//    System.out.println("RED WINS");
//    
//  }//end main
  
}//end class