// Name:Sophia Evanisko
// Date:1/2/2019

import java.util.*;

public class ParenMatch
{
   public static final String left  = "([{<";
   public static final String right = ")]}>";
   
   public static void main(String[] args)
   {
      System.out.println("Parentheses Match");
      ArrayList<String> parenExp = new ArrayList<String>();
      /* enter test cases here */
      parenExp.add("5+7");
      parenExp.add("(5+7)");
      parenExp.add(")5+7(");
      parenExp.add("((5+7)*3)");
      parenExp.add(" <{5+7}*3>");
      parenExp.add("[(5+7)*]3");
      parenExp.add("(5+7)*3");
      parenExp.add("5+(7*3)");
      parenExp.add("((5+7)*3");
      parenExp.add("[(5+7]*3)");
      parenExp.add("[(5+7)*3])");
      parenExp.add("([(5+7)*3]");
   
      for( String s : parenExp )
      {
         boolean good = checkParen(s);
         if(good)
            System.out.println(s + "\t good!");
         else
            System.out.println(s + "\t BAD");
      }
   }
   
   public static boolean checkParen(String exp)
   {
      int lIndex = 0;
      int rIndex = 0;
      Stack<String> stk = new Stack<String>();
      for(int s = 0; s < exp.length(); s++)
         for(int sym = 0; sym < left.length(); sym++)
         {
            char c = exp.charAt(s);
            if(c == (left.charAt(sym)))
               stk.push("" + exp.charAt(s));
             
            if(c == (right.charAt(sym)))
            {
               if(stk.isEmpty())
                  return false;
                  
               switch(sym)
               {
               case 0: 
               if(stk.peek().equals("("))
               stk.pop();
               else 
               return false;
               break;
               
               case 1:
               if(stk.peek().equals("["))
               stk.pop();
               else 
               return false;
               break;
               
               case 2:
               if(stk.peek().equals("{"))
               stk.pop();
               else 
               return false;
               break;
               
               case 3:
               if(stk.peek().equals("<"))
               stk.pop();
               else 
               return false;
               break;
               }

                  
            }
         }
   
      if(stk.isEmpty())
         return true;
      return false;
   }
}

/*
 Parentheses Match
 5+7	 good!
 (5+7)	 good!
 )5+7(	 BAD
 ((5+7)*3)	 good!
 <{5+7}*3>	 good!
 [(5+7)*]3	 good!
 (5+7)*3	 good!
 5+(7*3)	 good!
 ((5+7)*3	 BAD
 [(5+7]*3)	 BAD
 [(5+7)*3])	 BAD
 ([(5+7)*3]	 BAD
 */
