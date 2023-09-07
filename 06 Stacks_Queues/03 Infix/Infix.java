// Name:Sophia Evanisko
// Date:1/10/2019

import java.util.*;

class Infix
{
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
     /*build your list of Infix expressions here  */
      ArrayList<String> infixExp = new ArrayList<String>();
      infixExp.add("3 + 4 * 5");
      infixExp.add("3 * 4 + 5");
      infixExp.add("( -5 + 15 ) - 6 / 3");
      infixExp.add("( 3 + 4 ) * ( 5 + 6 )");
      infixExp.add("( 3 * ( 4 + 5 ) - 2 ) / 5");
      infixExp.add("8 + -1 * 2 - 9 / 3");
      infixExp.add("3 * ( 4 * 5 + 6 )");
   
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);
         System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + Postfix.eval(pf));  //Postfix must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      List<String> infixParts = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      Stack<Character> stk = new Stack<Character>();
      /* enter your code here  */
      String pf = "";
      do
      {
         if(isOperand(infixParts.get(0)))
         {
            pf += infixParts.get(0) + " ";              
            infixParts.remove(0);
         }
         
         else if(infixParts.get(0).equals("("))
         {
            stk.push('(');
            infixParts.remove(0);
         }
         
         else if(infixParts.get(0).equals(")"))
         {
            char c = ' ';
            do
            {
               c = stk.pop();
               pf += c + " ";
            }
            while (c != '(' && !stk.isEmpty());
            infixParts.remove(0);
            pf = pf.substring(0, pf.length() -2);
         }
         
         else if(isOperator(infixParts.get(0)))
            if(stk.isEmpty())
            {
               stk.push(infixParts.get(0).charAt(0));
               infixParts.remove(0);
            }
            else if (isLower(infixParts.get(0).charAt(0), stk.peek()))
            {
               pf += stk.pop() + " ";
               stk.push(infixParts.get(0).charAt(0));
               infixParts.remove(0);
            }
            
            else if (!isLower(infixParts.get(0).charAt(0), stk.peek()))
            {
               stk.push(infixParts.get(0).charAt(0));
               infixParts.remove(0);
            }
      }
      while(!infixParts.isEmpty());
      while(!stk.isEmpty())
         pf += stk.pop() + " ";
      pf.substring(0, pf.length()-1);
    
      return pf;
   }
   
	//returns true if c1 has lower or equal precedence than c2
   public static boolean isLower(char c1, char c2)
   {
      if((c1 == ('*') || c1 == ('/')) && (c2 == ('*') || c2 == ('/')))
         return false;
      if((c1 == ('+') || c1 == ('-')) && (c2 == ('+') || c2 == ('-'))) 
         return false;
      if((c1 == ('*') || c1 == ('/')) && (c2 == ('+') || c2 == ('-')))
         return false;
      if((c1 == ('+') || c1 == ('-')) && (c2 == ('*') || c2 == ('/')))
         return true;
      return false;
   }
   
   public static boolean isOperator(String op)
   {
      if(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("%") || op.equals("^"))
         return true;
      return false;
   }
   public static boolean isOperand(String op)
   {
      for(int x = 0; x < op.length(); x++)
      {
         if(op.charAt(x) == ('1') || op.charAt(x) == ('2') || op.charAt(x) == ('3') || op.charAt(x) == ('4') || op.charAt(x) == ('5'))
            return true;
         if(op.charAt(x) == ('6') || op.charAt(x) == ('7') || op.charAt(x) == ('8') || op.charAt(x) == ('9') || op.charAt(x) == ('0'))
            return true;
      }
      return false;
   }
}

// class Postfix
// {  
//    static int eval(String pf)
//    {
//       List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
//       Stack<Integer> stk = new Stack<Integer>();
//       int int1 = 0;
//       int int2 = 0;
//       
//       do
//       {
//          if(isOperator("" + postfixParts.get(0)))
//          {
//             int1 = stk.pop();
//             int2 = stk.pop();
//             stk.push(eval(int1, int2, postfixParts.get(0)));
//          }
//          else 
//             stk.push(Integer.parseInt(postfixParts.get(0)));
//          postfixParts.remove(0);
//       }
//       while(!postfixParts.isEmpty());
//    
//       return stk.pop();
//    }
//    
//    static int eval(int a, int b, String ch)
//    {
//       if(ch.equals("+"))
//          return a+b;
//       if(ch.equals("-"))
//          return b-a;
//       if(ch.equals("*"))
//          return a*b;
//       if(ch.equals("%"))
//          return b%a;
//       if(ch.equals("^"))
//          return (int)Math.pow(b, a);
//       return b/a;
//    }
//    
// 
//    static boolean isOperator(String op)
//    {
//       if(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("%") || op.equals("^"))
//          return true;
//       return false;
//    }
// }

/********************************************

 Infix  	-->	Postfix		-->	Evaluate
 3 + 4 * 5			3 4 5 * +			23
 3 * 4 + 5			3 4 * 5 +			17
 ( -5 + 15 ) - 6 / 3			-5 15 + 6 3 / -			8
 ( 3 + 4 ) * ( 5 + 6 )			3 4 + 5 6 + *			77
 ( 3 * ( 4 + 5 ) - 2 ) / 5			3 4 5 + * 2 - 5 /			5
 8 + -1 * 2 - 9 / 3			8 -1 2 * + 9 3 / -			3
 3 * ( 4 * 5 + 6 )			3 4 5 * 6 + *			78
 
***********************************************/