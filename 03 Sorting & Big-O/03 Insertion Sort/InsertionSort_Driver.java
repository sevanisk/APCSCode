 //name:Sophia Evanisko
 //date:10/25/2018
import java.util.*;
import java.io.*;
public class InsertionSort_Driver
{
   public static void main(String[] args) throws Exception
   {
     //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      
      Insertion.sort(array);
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
   
      Insertion.sort(arrayStr);
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

//**********************************************************
 //name:Sophia Evanisko
 //date:10/25/2018
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
