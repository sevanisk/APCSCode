// Name:   
// Date:
 
import java.util.*;
import java.io.*;

public class Jugs
{
   public static void main(String[] args) throws Exception
   {
   
   }
	
   public static void solve(int a, int b, int n)
   {
      //Build a graph of the possible Jug pair states, 
      //given a as the capacity of the first jug, b as the capacity of the second jug, 
      //and n as the desired measurement of liquid
      Jug start = createGraph(a, b); 
      
      //Print the solution to the given Jug problem using breadth-first search
      findReachableBreadth(start, a, b, n); 
   }
	
	//Breadth-first search from EdgeList
   public static void findReachableBreadth(Jug v, int ac, int bc, int n)
   {
     
   }
	
   public static Jug createGraph(int a, int b)
   {
   
   }
}

/* The Jug object is a representation of the state of the pair of jugs.
 * Jug is a slight modification of the Vertex class from EdgeList.
 */
class Jug 
{

}
