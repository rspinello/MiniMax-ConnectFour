//Robert Spinello
import java.util.*;

class Stack<E>
{
  private ArrayList<E> items;
  
  public Stack() // default constructor; creates an empty stack   
  { 
    items = new ArrayList<E>(); // initial capacity is 10
  }
  
  public Stack(int initialCapacity) //one argument constructor, creates a stack with initial capacity initialCapacity
  {
    items = new ArrayList<E>(initialCapacity);
  }
  
  public void push(E x)
  {
    items.add(x); //uses the ArrayList method add(E o) 
  }
  
  public E pop()  
  { 
    if (empty()) // determine whether or not there is an item to remove  
    {  
      System.out.println("Stack Underflow");
      System.exit(0); 
    }
    return items.remove(items.size()-1); //uses the ArrayList method remove(int n)
  }
  
  public boolean empty()
  {
    return items.isEmpty();//uses the ArrayList method isEmpty()
  }
  
  public int size()
  {
    return items.size(); //uses the ArayList method size()
  }
  
  public E peek()
  {
    if (empty()) // determine whether or not there is an item on the stack
    {
      System.out.println("Stack Underflow");
      System.exit(0);
    }
    
    return items.get(items.size()-1); //uses the ArrayList method get(int i)
  }
}