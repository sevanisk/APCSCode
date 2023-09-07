// Name: Sophia Evanisko
// Date: 3/14/2019

import java.util.*;

interface Fibber
{
   public abstract int fib(int n);
}

public class Fib
{
   public static final int FIBsubN = 40;
   public static void main(String[] args)
   {
      System.out.println("Recursive");
      calculate(new Fib1(), FIBsubN);
      System.out.println("Iterative, stored in an array");
      calculate(new Fib2(FIBsubN + 1), FIBsubN);
      System.out.println("Recursive, stored in an arrayList");
      calculate(new Fib3(), FIBsubN);
      System.out.println("Recursive, stored in a hashMap");
      calculate(new Fib4(), FIBsubN);		
   }
      
   public static void calculate(Fibber fibber, int n)
   {
      long startTime = System.nanoTime();
      int n2 = fibber.fib(n);
      int time = (int)(System.nanoTime() - startTime);
      System.out.println("fib(" + n + ") = " + n2 + " (" + time + " nanoseconds)");
      System.out.println("");
   }
}
class Fib1 implements Fibber
{
   public Fib1()    
   {
   }
   
   public int fib(int n)
   {
      if(n == 1 || n == 2)
         return 1;
      else
         return fib(n - 1) + fib(n - 2);
   }
}
 
   
class Fib2 implements Fibber
{
   private int[] array;
   
   public Fib2(int n)
   {
      array = new int[n+1];
   }
   
   public int fib(int n)
   {
      int x = 1;
      while(x != array.length-1)
      {
         if(x == 1)
         {
            array[x] = 1;
            x++;
         }
         else
         {
            array[x] = array[x-1] + array[x-2];
            x++;
         }
      } 
      int val = array[x-1];
      return val;
   }
   
   public int[] getArray()   //nice to have
   {
      return array;
   }
}
  
   
class Fib3 implements Fibber
{
   private ArrayList<Integer> myFibList;
   
   public Fib3()
   {
      myFibList = new ArrayList<Integer>();
   }
   
   public int fib(int n)
   {
      if(n == 0)
      {
         myFibList.add(1);
         return 1;
      }
      else if (n < myFibList.size()) 
      {
         return myFibList.get(n);
      } 
      
      else if (n == 1) 
      {
         myFibList.add(0);
         return 0;
      }
      
      else
      {
         myFibList.add(fib(n - 1) + fib(n - 2));//recursive calls
      }  
      return myFibList.get(myFibList.size()-1);
      // for(int x = 0;x <= n; x++)
   //       {
   //          if(x <= 1)
   //             myFibList.add(1);
   //          else
   //          {
   //             myFibList.add(myFibList.get(x-1) + myFibList.get(x-2));
   //          }
   //       } 
   //       int size = myFibList.size()-2;
   //       return myFibList.get(size);
   
   
   }
   
   public ArrayList<Integer> getArrayList()   //nice to have
   {
      return myFibList;
   }
}

class Fib4 implements Fibber
{
   private Map<Integer, Integer> myFibMap;
   
   public Fib4()
   {
      myFibMap = new TreeMap<Integer, Integer>();
   }
   
   public int fib(int n)
   {                                                         
      if (n == 0) 
      {
         return n;
      } 
      else if (n <= 2) 
      {
         return 1;
      }
      if (myFibMap.get(n) != null) 
      {
         return myFibMap.get(n);
      } 
      else
      {
         int myValue = fib(n - 1) + fib(n - 2);//recursive calls
         myFibMap.put(n, myValue);
         return myValue;   
      }
   }
   
   public Map<Integer, Integer> getMap()  //nice to have
   {
      return myFibMap;
   }
}
 
	
   
   
   /*
    Recursive
    fib(42) = 267914296 (3276558048 nanoseconds)
    
    Iterative, stored in an array
    fib(42) = 267914296 (4988 nanoseconds)
    
    Recursive, stored in an arrayList
    fib(42) = 267914296 (64025 nanoseconds)
    
    Recursive, stored in a hashMap
    fib(42) = 267914296 (177793 nanoseconds)
    
   	*/
