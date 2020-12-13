//Robert Spinello
public class MiniMax
{
  public static int lookAhead(Board game, int depth, Move recommended)
  {
   game.changeTurn();
   if(game.done() || depth == 0)
     return game.evaluate();
   else
   {
     Stack<Move> moves = new Stack<Move>();
     game.legalMoves(moves);
     int value; 
     int bestValue = game.worstCase(); 
     while(!moves.empty())
     {
       Move move = new Move(); 
       Move tryIt = moves.peek();
       Board newGame = new Board(game);
       newGame.play(tryIt);
       
       value = lookAhead(newGame, depth - 1, move);
       
       if(game.better(value,bestValue))
       {
         bestValue = value;
         recommended.setMove(tryIt);
       }//end if 
       
       moves.pop();
       
     }//end while
     
     return bestValue; 
     
   }//end else 
  }//end method
  
}//end class 