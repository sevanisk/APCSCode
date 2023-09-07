// Name:Sophia Evanisko
// Date:11/21/2018

import java.util.*;
import java.io.*;

public class Josephus
{
   private static String WINNER = "Josephus";
   
   public static void main(String[] args) throws FileNotFoundException
   {
      /* run numberCircle first with J_numbers.txt  */
      Scanner sc = new Scanner(System.in);
      System.out.print("How many names (2-20)? ");
      int n = Integer.parseInt(sc.next());
      System.out.print("How many names to count off each time?"  );
      int countOff = Integer.parseInt(sc.next());
      ListNode winningPos = numberCircle(n, countOff, "J_numbers.txt");
      System.out.println(winningPos.getValue() + " wins!");  
     
      /* run josephusCircle next with J_names.txt  */
      System.out.println("\n ****  Now start all over. **** \n");
      System.out.print("Where should "+WINNER+" stand?  ");
      int winPos = Integer.parseInt(sc.next());        
      ListNode theWinner = josephusCircle(n, countOff, "J_names.txt", winPos);
      System.out.println(theWinner.getValue() + " wins!");  
   }
   
   public static ListNode numberCircle(int n, int countOff, String filename) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      p = countingOff(p, countOff, n);
      return p;
   }
   
   public static ListNode josephusCircle(int n, int countOff, String filename, int winPos) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      replaceAt(p, WINNER, winPos);
      p = countingOff(p, countOff, n);
      return p;
   }

   /* reads the names, calls insert(), builds the linked list.
	 */
   public static int z = 0;
   public static ListNode point;
   public static ListNode readNLinesOfFile(int n, File f) throws FileNotFoundException
   {
      Scanner sc = null;
      ListNode newp = new ListNode(null, null);
      sc = new Scanner(f);
      z = 0;
      for(int x = 0; x < n; x++)
      {
         z++;
         Object o = sc.nextLine();
         newp = insert(newp, o);
      }  
      return newp; 
   }
   
   /* helper method to build the list.  Creates the node, then
    * inserts it in the circular linked list.
	 */
   public static ListNode insert(ListNode p, Object obj)
   {
      ListNode point = p;
      for(int x = 0; x < z-2; x++)
         point = point.getNext(); 
    
      if(p.getValue() == null)
         return new ListNode(obj, null);  
      point.setNext(new ListNode(obj, p));
      return p;
   }   
   /* Runs a Josephus game, counting off and removing each name. Prints after each removal.
      Ends with one remaining name, who is the winner. 
	 */
   public static ListNode countingOff(ListNode p, int count, int n)
   {
      for(int x = 0; x < n -1; x++)
      {
         print(p);
         p = remove(p, count);
      }
      return p;
   }
   
   /* removes the node after counting off count-1 nodes.
	 */ //FIX
   public static ListNode remove(ListNode p, int count)
   {
      for(int x = 0; x < count-2; x++)
         p = p.getNext();
         
      ListNode point = p;
      p.setNext(p.getNext().getNext());
      //for(int x = 0; x < count-1; x++)
         p = p.getNext();
      return p;
   }
   
   /* prints the circular linked list.
	 */
   public static void print(ListNode p)
   {
      Object o = p.getValue();
      while(p.getNext().getValue() != o)
      {
         System.out.print(p.getValue() + ", ");
         p= p.getNext();
      }
      System.out.println(p.getValue() + " ");
   }
	
   /* replaces the value (the string) at the winning node.
	 */
   public static void replaceAt(ListNode p, Object obj, int pos)
   {
      ListNode z = p;
      for(int x = 0; x < pos-1; x++)
         z = z.getNext();
      z.setValue(obj);
   }
}

