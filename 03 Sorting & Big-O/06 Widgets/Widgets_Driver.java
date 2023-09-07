//name:Sophia Evanisko    
//date:10/31/2018 

import java.io.*;      //the File class
import java.util.*;    //the Scanner class
   
public class Widgets_Driver
{
   public static final int numberOfWidgets = 57;
   public static void main(String[] args) throws Exception
   {
      // test the methods
      Widget a = new Widget();
      System.out.println("Default widget A:  " + a.toString());
      Widget b = new Widget(23, 10);
      System.out.println("2-arg constructor B:  " + b.toString());
      Widget c = new Widget(b);
      System.out.println("Copy constructor C:  " + c.toString());
      System.out.print("C's cubits = " + c.getCubits());
      System.out.println( " and hands = " + c.getHands());
      System.out.println("Test the equals methods: ");
      System.out.println("   A equals B " + a.equals(b));
      System.out.println("   B equals C " + b.equals(c));
      
      c.setCubits(3);
      c.setHands(4);
      System.out.print("C is reset to " + c.toString());
      
      System.out.println("\nTest each sort on 57 Widgets");
      Comparable[] apple = input("widgets.txt");
      Scanner sc = new Scanner(System.in);
      System.out.println("  1 Selection Sort");
      System.out.println("  2 Insertion Sort");
      System.out.println("  3 Merge Sort");
      System.out.println("  4 Quick Sort");
      System.out.print("Choose your sort:  ");
      int choice = Integer.parseInt(sc.next());
      System.out.println();
      switch( choice )
      {
         case 1:  Selection.sort(apple); 
            break;
         case 2:  Insertion.sort(apple);   
            break;
         case 3:  MergeSort.sort(apple);  
            break;
         case 4:  QuickSort.sort(apple);  
            break;
         default:  System.out.println("Wrong choice");
      }
      print(apple);   
   }
          	
   public static Comparable[] input(String filename) throws Exception
   {
      Scanner sc= new Scanner(new File(filename));
      Widget[] array = new Widget[57];
      for(int x = 0; x < array.length; x++)
         array[x] = new Widget(sc.nextInt(), sc.nextInt());
      sc.close();
      return array;
   }
   	  
   public static void print(Object[] mango)
   {
      for(Object fruit : mango)     //for-each
         System.out.println(fruit +" ");
   }
}

/////////////////////////////////////////////////////
class Widget implements Comparable<Widget>
{
   private int myCubits;
   private int myHands;
   
   //constructors
   public Widget()
   {
      this.myCubits = 0;
      this.myHands = 0;
   }
   
   public Widget (int cub, int hand)
   {
      this.myCubits = cub;
      this.myHands = hand;
   }
   
   public Widget(Widget copy)
   {
      this.myCubits = copy.myCubits;
      this.myHands = copy.myHands;
   }
   
   //accessors and modifiers
   public int getCubits()
   {
      return myCubits;
   }
   
   public int getHands()
   {
      return myHands;
   }
   
   public void setCubits(int cub)
   {
      this.myCubits = cub;
   }
   
   public void setHands(int hand)
   {
      this.myHands = hand;
   }
   
   //compareTo and equals
   public int compareTo(Widget other)
   {
      if(myCubits > other.myCubits)
         return 1;
      
      else if(myCubits < other.myCubits)
         return -1;
      
      else if(myCubits == other.myCubits)
         if(myHands > other.myHands)
            return 1;
         else if(myHands < other.myHands)
            return -1;
      return 0;
   }
   
   //toString
   public String toString()
   {
      return myCubits + " cubits " + myHands + " hands";
   }
   
}
    
    
    
 //sorts   
class Selection
{
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
    public static void sort(Comparable[] array)
   {
      int a = 0;
      for(int x = 0; x < array.length; x++)
      {
         swap(array, findMax(array, x), array.length-1-a);
         a++;
      }
   }
   
   @SuppressWarnings("unchecked")
    public static int findMax(Comparable[] array, int upper)
   {
      Object max = array[0];
      int maxPos = 0;
      for(int x = 0; x < array.length - upper; x ++)
         if(array[x].compareTo(max) > 0)
         {
            max = array[x];
            maxPos = x;
         }
      return maxPos;
   }
   
   public static void swap(Object[] array, int a, int b)
   {
      Object x = array[a];
      array[a] = array[b];
      array[b] = x;
   }
}
  
class Insertion
{
   public static double[] sort(double[] array)
   { 
      for(int m = 0; m <= array.length - 1; m++)
      {
         double value = array[m];
         int x = shift(array, m, value);
         array[x] = value;
      
      }
      return array;
   }
   private static int shift(double[] array, int index, double value)
   {  
      int i = index;
      while(i > 0 && array[i-1] > value)
      {
         array[i] = array[i-1];
         i = i-1;
      }
      return i;
   }
   
   @SuppressWarnings("unchecked")
    public static Comparable[] sort(Comparable[] array)
   { 
      for(int m = 0; m <= array.length-1; m++)
      {
         Comparable value = array[m];
         int x = shift(array, m, value);
         array[x] = value;
      }
      return array; 
   }
   @SuppressWarnings("unchecked")
    private static int shift(Comparable[] array, int index, Comparable value)
   {
      int i = index;
      while(i > 0 && array[i-1].compareTo(value) > 0)
      {
         array[i] = array[i-1];
         i = i-1;
      }
      return i;
   }
}

class MergeSort
{
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static void sort(Comparable[] array)
   {
      Comparable[] copyBuffer = new Comparable[array.length];
      mergeSortHelper(array, copyBuffer, 0, array.length - 1); 
   }


   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   private static void mergeSortHelper(Comparable[] array, Comparable[] copyBuffer, int low, int high)
   {  
      if (low < high)
      {
         int middle = (low + high) / 2;
         mergeSortHelper(array, copyBuffer, low, middle);
         mergeSortHelper(array, copyBuffer, middle + 1, high);
         merge(array, copyBuffer, low, middle, high);
      }
   }
   
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static void merge(Comparable[] array, Comparable[] copyBuffer,
                                   int low, int middle, int high)
   {
      int z = low;   
      int a = middle + 1;  
      int c = low;
      while(low <= middle && a <= high)
      {
         if(array[low].compareTo(array[a]) <= 0)
         {
            copyBuffer[c] = array[low];
            low++;
         }
         else
         {
            copyBuffer[c] = array[a];
            a++;
         }
         c++;
      }
      
      while(low <= middle && c <= high)
      {
         copyBuffer[c] = array[low];
         c++;
         low++;
      }
      
      if(low >= middle)
      {
         while(c <= high && low <= high)
         {
            copyBuffer[c]  = array[c];
            c++;
            low++;
         }
      }
      //copies back into array
      
      while(z <= high)
      {
         array[z] = copyBuffer[z];
         z++;
      }  
   }    	
}

class QuickSort
{
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static void sort(Comparable[] array)
   {
      helper(array, 0, array.length - 1);
   }
   
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   private static void helper(Comparable[] array, int first, int last)
   {
      int indexOfPivot;
      if (first < last)   				// General case
      {    
         indexOfPivot = rearrange(array, first, last);
         helper(array, first, indexOfPivot - 1);	// sort left side
         helper(array, indexOfPivot + 1, last);	   // sort right side
      }
   }
   
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   private static int rearrange(Comparable[] array, int first, int last)
   {
      int indexOfPivot = first;       
      Comparable pivot = array[first];
   
      while (first <= last)
      { 
         if(array[first].compareTo(pivot) <= 0)       //if on correct side, 
            first++;                      //   move over		
         else if(array[last].compareTo(pivot) >= 0)   //if on correct side,	
            last--;                       //   move over		
         else         // both are on the wrong side  
         {  
            swap(array, first, last);         // then swap them, and
            first++;                       //   move over
            last--;                       //   move over 
         }
      }
      swap(array, last, indexOfPivot); 	// swap pivot with element at indexOfPivot
      indexOfPivot = last;			         // set indexOfPivot to place where the halves meet
      return indexOfPivot;
   }

   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   private static void swap(Comparable[] array, int a, int b)
   {
      Comparable x = array[a];
      array[a] = array[b];
      array[b] = x;
   }
}
   
 /***************************************
      ----jGRASP exec: java Widgets_Teacher
 Default widget A:  0 cubits 0 hands
 2-arg constructor B:  23 cubits 10 hands
 Copy constructor C:  23 cubits 10 hands
 C's cubits = 23 and hands = 10
 Test the equals methods: 
    A equals B false
    B equals C true
 C is reset to 0 cubits 0 hands
 
 Test each sort on 57 Widgets
   1 Selection Sort
   2 Insertion Sort
   3 Merge Sort
   4 Quick Sort
 Choose your sort:  2
 
 0 cubits 14 hands
 1 cubits 3 hands
 2 cubits 14 hands
 5 cubits 14 hands
 10 cubits 14 hands
 11 cubits 11 hands
 12 cubits 0 hands
 12 cubits 7 hands
 13 cubits 9 hands
 15 cubits 12 hands
 17 cubits 5 hands
 18 cubits 13 hands
 19 cubits 13 hands
 19 cubits 13 hands
 22 cubits 6 hands
 23 cubits 7 hands
 24 cubits 15 hands
 24 cubits 15 hands
 26 cubits 2 hands
 28 cubits 5 hands
 28 cubits 12 hands
 29 cubits 15 hands
 31 cubits 0 hands
 32 cubits 1 hands
 32 cubits 11 hands
 32 cubits 11 hands
 32 cubits 12 hands
 35 cubits 3 hands
 39 cubits 2 hands
 39 cubits 5 hands
 41 cubits 10 hands
 43 cubits 2 hands
 43 cubits 5 hands
 43 cubits 6 hands
 51 cubits 2 hands
 54 cubits 14 hands
 55 cubits 8 hands
 56 cubits 3 hands
 57 cubits 12 hands
 62 cubits 15 hands
 63 cubits 0 hands
 64 cubits 13 hands
 67 cubits 3 hands
 70 cubits 0 hands
 73 cubits 5 hands
 74 cubits 7 hands
 75 cubits 9 hands
 81 cubits 5 hands
 85 cubits 14 hands
 86 cubits 3 hands
 90 cubits 13 hands
 91 cubits 3 hands
 92 cubits 1 hands
 92 cubits 8 hands
 96 cubits 1 hands
 98 cubits 8 hands
 99 cubits 5 hands
 
  ----jGRASP: operation complete.    
  ************************************/