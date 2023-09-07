// Name:Sophia Evanisko
// Date:09/27/2018
  
import java.util.*;
public class Fibonacci
{
   private static int x = 0;
   public static void main(String[] args)
   {
      long start, end, fib; //why long?
      int[] fibNumber = {1, 5, 10, 20, 30, 40, 41, 42};
      System.out.print("\tFibonacci\tBy Iteration\tTime\tby Recursion\t Time");
      for(int n = fibNumber[0]; n <= fibNumber[fibNumber.length - 1]; n++)
      { 
         start = System.nanoTime();
         fib = fibIterate(n);
         end = System.nanoTime();
         System.out.print("\t\t" + n + "\t\t" + fib + "\t" + (end-start)/1000.);
         start = System.nanoTime(); 
         x = 0;  	
         fib = fibRecur(n);      
         end = System.nanoTime();
         System.out.println("\t" + fib + "\t\t" + (end-start)/1000. + "\t");
      }
   }
   
   /**
    * Calculates the nth Fibonacci number by interation
    * @param n A variable of type int representing which Fibonacci number
    *          to retrieve
    * @returns A long data type representing the Fibonacci number
    */
   public static long fibIterate(int n)
   {
      if(n==1 || n==2)
         return 1;
      else
      {
         int x = 1; int y = 1; int a = 0;
         for(int z = 2; z < n; z++)
         { 
            a = y;
            y = x + y;
            x = a;
         }
         return y;
      }  
          
   }

   /**
    * Calculates the nth Fibonacci number by recursion
    * @param n A variable of type int representing which Fibonacci number
    *          to retrieve
    * @returns A long data type representing the Fibonacci number
    */

   public static long fibRecur(int n)
   {
      if(n == 1 || n == 2)
         return 1;
      return fibRecur(n-1) + fibRecur(n-2); 
   }
}