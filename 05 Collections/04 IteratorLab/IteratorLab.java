// Name: Sophia Evanisko
// Date: 12/10/2018

import java.io.*;
import java.util.*;

/**
 * Use for-each loops or iterators.
 * Do not use traditional for-loops.
 */  
public class IteratorLab
{
   public static void main(String[] args)
   {
      System.out.println("Iterator Lab\n");
      int[] rawNumbers = {-9, 4, 2, 5, -10, 6, -4, 24, 20, -28};
      for(int n : rawNumbers )
         System.out.print(n + " ");    
      ArrayList<Integer> numbers = createNumbers(rawNumbers);
      System.out.println("ArrayList: "+ numbers);      //Implicit Iterator!
      System.out.println("Count negative numbers: " + countNeg(numbers));
      System.out.println("Average: " + average(numbers));
      System.out.println("Replace negative numbers: " + replaceNeg(numbers));
      System.out.println("Delete zeros: " + deleteZero(numbers));
      String[] rawMovies = {"High_Noon", "High_Noon", "Star_Wars", "Tron", "Mary_Poppins", 
               "Dr_No", "Dr_No", "Mary_Poppins", "High_Noon", "Tron"};
      ArrayList<String> movies = createMovies(rawMovies);
      System.out.println("Movies: " + movies);
      System.out.println("Movies: " +  removeDupes(movies));
   }

   /**
    * @return ArrayList containing all values in the int array
    */     
   public static ArrayList<Integer> createNumbers(int[] rawNumbers) 
   {
      ArrayList<Integer> myList = new ArrayList<Integer>();
      for ( Integer in : rawNumbers )
         myList.add( in );
      return myList;
   }
 
   /**
    * @return ArrayList containing all values in the String array
    */    
   public static ArrayList<String> createMovies(String[] rawWords) 
   {
      ArrayList<String> myList = new ArrayList<String>();
      for ( String str : rawWords )
         myList.add( str );
      return myList;   
   }

   /**
    * Precondition:  Arraylist a is not empty; contains Integer objects
    * @return number of negative values in ArrayList a
    */    
   public static int countNeg(ArrayList<Integer> a)
   {
      Iterator<Integer> it = a.iterator();
      int count = 0;
      while(it.hasNext())
         if(it.next() < 0)
            count++;
      return count;   
   }
  
   /**
    * Precondition:  Arraylist a is not empty; contains Integer objects
    * @return average of all values in the ArrayList a
    */    
   public static double average(ArrayList<Integer> a)
   {
      Iterator<Integer> it1 = a.iterator();
      int total = 0;
      int count = 0;
      while(it1.hasNext())
      {
         total += it1.next();
         count++;
      }
      return total/count;          
   }

   /**
   
    * Changes all negative values in ArrayList to 0.
    * Precondition:  Arraylist a is not empty; contains Integer objects
    * @return ArrayList with negative values turned to 0 
    */    
   public static ArrayList<Integer> replaceNeg(ArrayList<Integer> a)
   {
      ListIterator<Integer> lit = a.listIterator();
      while(lit.hasNext())
         if(lit.next() < 0)
            lit.set(0);
      return a;  
   }
  
   /**
    * Deletes all zero values in ArrayList.
    * Precondition:  Arraylist a is not empty; contains Integer objects
    * @return ArrayList with no zero values. 
    */   
   public static ArrayList<Integer> deleteZero(ArrayList<Integer> a)
   {
      ListIterator<Integer> lit1 = a.listIterator();
      while(lit1.hasNext())
         if(lit1.next() == 0)
            lit1.remove();
      return a;   
   }

   /**
    * Removes duplicates from list.
    * Precondition:  Arraylist a is not empty; contains String objects
    * @return ArrayList without duplicate movie titles. 
    */   
   public static ArrayList<String> removeDupes(ArrayList<String> a)
   {
      ListIterator<String> lit2 = a.listIterator();
      int count = 0;
      int r = 0;
      while(lit2.hasNext())
      {
         int tORf = 0;
         int revs = r;
         String str = lit2.next();
         for(String s : a)
         {
            if(s.equals(str) && count != revs)
               tORf = 1;
            revs++;
         }
         if(tORf == 1)
         {
            r++;
            lit2.remove();
         }
         count++;
      }
      return a;      
   }
   
}

