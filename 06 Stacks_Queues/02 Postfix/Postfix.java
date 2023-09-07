// Name:Sophia Evanisko
// Date: 1/08/2019

import java.util.*;

public class Postfix
{
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      //expressions
      postfixExp.add("3 4 5 * +");
      postfixExp.add("3 4 * 5 +");
      postfixExp.add("10 20 + -6 6 * +");
      postfixExp.add("3 4 + 5 6 + *");
      postfixExp.add("3 4 5 + * 2 - 5 /");
      postfixExp.add("8 1 2 * + 9 3 / -");
         //extensions
      postfixExp.add("2 3 ^");
      postfixExp.add("20 3 %");
      postfixExp.add("21 3 %");
      postfixExp.add("22 3 %");
      postfixExp.add("23 3 %");
      postfixExp.add("5 !");
      postfixExp.add("1 1 1 1 1 + + + + !");
      
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static int eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      Stack<Integer> stk = new Stack<Integer>();
      int int1 = 0;
      int int2 = 0;
      
      do
      {
         if(postfixParts.get(0).equals("!"))
         {
            int1 = stk.pop();
            stk.push(factorial(int1));
         }
         else if(isOperator("" + postfixParts.get(0)))
         {
            int1 = stk.pop();
            int2 = stk.pop();
            stk.push(eval(int1, int2, postfixParts.get(0)));
         }
         else 
            stk.push(Integer.parseInt(postfixParts.get(0)));
         postfixParts.remove(0);
      }
      while(!postfixParts.isEmpty());
   
      return stk.pop();
   }
   
   public static int factorial(int n)
   {
      if(n == 1)
         return 1;
      return n * factorial(n-1);
   }
   
   public static int eval(int a, int b, String ch)
   {
      if(ch.equals("+"))
         return a+b;
      if(ch.equals("-"))
         return b-a;
      if(ch.equals("*"))
         return a*b;
      if(ch.equals("%"))
         return b%a;
      if(ch.equals("^"))
         return (int)Math.pow(b, a);
      return b/a;
   }
   

   public static boolean isOperator(String op)
   {
      if(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("%") || op.equals("^"))
         return true;
      return false;
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/