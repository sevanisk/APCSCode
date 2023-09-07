 //name:Sophia Evanisko
 //date:10/29/2018
import java.util.*;
import java.io.*;
public class MergeSort_Driver
{
   public static void main(String[] args) throws Exception
   {
      //Part 1, for doubles
      double[] array = {3,1,4,1,5,9,2,6};    // small example array from the MergeSort handout
      // int n = (int)(Math.random()*50+10);
//       double[] array = new double[n];
//       for(int k = 0; k < array.length; k++)
//          array[k] = Math.random();
         	
      MergeSort.sort(array);
   
      print(array);
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("oops!");
         
      //Part 2, for Comparables
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      MergeSort.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }

   
   public static void print(double[] a)   
   {                            // The for-each loop. 
      for(double number : a)    //doing something to each element
         System.out.print(number+" ");
      System.out.println();
   }
   public static boolean isAscending(double[] a)
   {
      for(int x = 0; x < a.length -2; x++)
         if(!(a[x] <= a[x+1]))
            return false;
      return true;
   }
   public static void print(Object[] peach)
   {
      for(Object abc : peach)     //for-each
         System.out.print(abc+" ");
   }
   
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static boolean isAscending(Comparable[] a)
   {
      for(int x = 0; x < a.length-2; x++)
         if(a[x].compareTo(a[x+1]) < 0)
            return true;
      return false;
   }
}
/////////////////////////////////////////////
// 15 Oct 07
// MergeSort Code Handout
// from Lambert & Osborne, p. 482 - 485

class MergeSort
{
   private static final int CUTOFF = 10; // for small lists, recursion isn't worth it
   public static void sort(double[] array)
   { 
      double[] copyBuffer = new double[array.length];
      mergeSortHelper(array, copyBuffer, 0, array.length - 1);
   }
   /*  array			array that is being sorted
       copyBuffer		temp space needed during the merge process
       low, high		bounds of subarray
       middle			midpoint of subarray   */
   private static void mergeSortHelper(double[] array, double[] copyBuffer,
                                                      int low, int high)
   {  
       //if ( high - low  < CUTOFF )                  //switch to selection sort  when
       //   SelectionLowHigh.sort(array, low, high);        //the list gets small enough 
       //else
      if (low < high)
      {
         int middle = (low + high) / 2;
         mergeSortHelper(array, copyBuffer, low, middle);
         mergeSortHelper(array, copyBuffer, middle + 1, high);
         merge(array, copyBuffer, low, middle, high);
      }
   }
   
   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low				beginning of first sorted subarray
      middle			end of first sorted subarray
      middle + 1		beginning of second sorted subarray
      high				end of second sorted subarray   */
   public static void merge(double[] array, double[] copyBuffer, int low, int middle, int high)
   {
      int z = low;   
      int a = middle + 1;  
      int c = low;
      while(low <= middle && a <= high)
      {
         if(array[low] <= array[a])
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
   
   
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static void sort(Comparable[] array)
   {
      Comparable[] copyBuffer = new Comparable[array.length];
      mergeSortHelper(array, copyBuffer, 0, array.length - 1); 
   }

   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low, high		bounds of subarray
      middle			midpoint of subarray  */
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
   
   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low				beginning of first sorted subarray
      middle			end of first sorted subarray
      middle + 1		beginning of second sorted subarray
      high				end of second sorted subarray   */
   
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

/***************************************************
This is the extension.  You will have to uncomment Lines 54-56 above.
name:   date:
***********************************************/
class SelectionLowHigh
{
   public static void sort(double[] array, int low, int high)
   {  
   
   }
   private static int findMax(double[] array, int low, int upper)
   {
      return 0;
   }
   private static void swap(double[] array, int a, int b)
   {
   
   } 
}
