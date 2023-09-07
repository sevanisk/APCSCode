import java.util.*;
import java.io.*;
public class MazeMaster
{
   public static void main(String[] args)
   {
      System.out.println("" + sumTo(0));
   }

   public double sumTo(int n)
   {
      int x = 0;
    
      if( n < 0 ) throw new IllegalArgumentException();
      if(x != 0)
         x = 1/n;
      else
         x = 0;
      if(n == 0)
         return x;
    
      if(n == 1)
           
         return x;
       
      else
       {
        
         return x += sumTo(n-1);  
       }
   }
}
 public boolean isReverse(String s1, String s2)
{
  if(s1.equals("") && s2.equals(""))
      return true;
  else if(s1.length() <= 1 && !(s1.equalsIgnoreCase(s2)))
      return false;
  else
  {
    if(s1.substring(0,1).equalsIgnoreCase(s2.substring(s2.length()-1)))  
        return isReverse(s1.substring(1), s2.substring(0, s2.length()-1));
    return false;
  }
    
} 


public String repeat(String s, int n)
{
 String x = "";
 if( n < 0 ) throw new IllegalArgumentException();
 if(n == 0)
     return x;
 else
     return x += s + repeat(s, n-1);
    
}

public String starString(int n)
{
 if( n < 0 ) throw new IllegalArgumentException();
 if(n == 0)
     return "*";
 if(n==1)
     return "**";
 else
 {
  return starString(n-1) + starString(n-1);
 }
}

  