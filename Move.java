//Robert Spinello
public class Move
{
  private int column; 
  
  public Move()
  {
    column = 0;
  }//end default 
  
  public Move(int n)
  {
    column = n; 
  }//end constructor 
  
  public void setMove(Move n)
  {
    this.column = n.column; 
  }//end method
  
  public int getMove()
  {
    return this.column;
  }//end method
  
  public String toString()
  {
    return ""+column; 
  }//end method 
  
}//end class