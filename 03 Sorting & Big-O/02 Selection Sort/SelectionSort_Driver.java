 // name: Sophia Evanisko
 // date: 10/27/2018
import java.util.*;
import java.io.*;
public class SelectionSort_Driver
{
   public static void main(String[] args) throws Exception
   {
     //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      
      Selection.sort(array);
      print(array);
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
      System.out.println();
      
      //Part 2, for Strings
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      Selection.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }
   public static void print(double[] a)
   {
      for(double d: a)         //for-each
         System.out.print(d+" ");
      System.out.println();
   }
   public static void print(Object[] papaya)
   {
      for(Object abc : papaya)     //for-each
         System.out.print(abc+" ");
   }
   public static boolean isAscending(double[] a)
   {
      for(int x = 0; x < a.length -2; x++)
         if(!(a[x] < a[x+1]))
            return false;
      return true;
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
//*********************************************
//name:Sophia Evanisko
//date:10/25/2018
class Selection
{
   public static void sort(double[] array)
   {
      int x = 0;
      for(int y = 0; y < array.length-1; y++)
      {
         swap(array, findMax(array, array.length-x), array.length-1-x); 
         x++;
      }
   }
   
   private static int findMax(double[] array, int upper)//"upper" controls where the inner
   																//loop of the Selection Sort ends
   {
      double max = 0;
      int maxPos = 0;
      for(int x = 0; x < upper; x++)
         if(array[x] > max)
         {
            maxPos = x;
            max = array[x];
         }
      return maxPos;
   }
   private static void swap(double[] array, int a, int b)
   {
      double temp = array[b];
      array[b] = array[a];
      array[a] = temp;
   }   	
   
	/*******  for Comparables ********************/
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

