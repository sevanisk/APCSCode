// Name:Sophia Evanisko
// Date:12/07/2018
   
import java.util.*;

public class CollectionsSpeed
{
   public static final int n = 10000;
   
   public static void main(String[] args)
   {
      ArrayList<Integer> alist = new ArrayList<Integer>();
      LinkedList<Integer> llist = new LinkedList<Integer>();
      makeValues(alist, llist);
      
      System.out.println("get each for ArrayList = " + timeGetEach(alist));
      System.out.println("get each for LinkedList = " + timeGetEach(llist));
      System.out.println("\nadd at 0 for ArrayList = " + timeAddFirst(alist));
      System.out.println("add at 0 for LinkedList = " + timeAddFirst(llist));
      System.out.println("\nadd at list.size() for ArrayList = " + timeAddLast(alist));
      System.out.println("add at list.size() for LinkedList = " + timeAddLast(llist));
      System.out.println("addLast for LinkedList = " + timeAddLastLL(llist));
   }
   
   public static void makeValues(ArrayList<Integer> alist, LinkedList<Integer> llist)
   {
      for( int i = 0; i < n; i++ )
      {
         alist.add(i);              		 
         llist.add(i);							
      }
   }
   
   /**
    * Get n items by searching for each one.
    */      
   public static double timeGetEach(List<Integer> list)
   {
      double start = System.nanoTime();
      for( int i = 0; i < n; i++ )
      {
         int index = list.get(i);
      }
      return (System.nanoTime() - start)/1E6;
   }
   
   /**
    * Add 10000 new objects at position 0.
    */
   public static double timeAddFirst(List<Integer> list)
   {
      double start = System.nanoTime();
      for(int i = 0; i < 10000; i++)
         list.add(0, 1);
      return (System.nanoTime() - start)/1E6;
   }
   
   /*    
    * Add 10000 new objects at position list.size() 
    */
   public static double timeAddLast(List<Integer> list)
   {
      double start = System.nanoTime();
      for(int i = 0; i < 10000; i++)
         list.add(list.size(), 1);
      return (System.nanoTime() - start)/1E6;
   }
   
   /*    
    * Add 10000 new objects at the end.  
    * Uses the LinkedList method addLast().
    * You must cast List list into a LinkedList. 
    */
   public static double timeAddLastLL(List<Integer> list)
   {
      LinkedList a =(LinkedList)list;
      double start = System.nanoTime();
      for(int i = 0; i < 10000; i++)
         a.addLast(1);
      return (System.nanoTime() - start)/1E6;
   }
}
