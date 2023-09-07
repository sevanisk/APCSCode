// Name: Sophia Evanisko
// Date: 09/07/2018
  
import java.util.*;
import java.io.*;

public class PigLatin1
{
   public static void main(String[] args) 
   {
      part_1_using_pig();
      //part_2_using_piglatenizeFile();
   }
   
   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if (s.equals("-1")) 
            System.exit(0);
         String p = pig(s);
         System.out.println( p );
      }		
   }
   
   public static boolean punct = true;
   public static int punctCount = 0;
   public static char q = ' ';
   public static boolean quote = true;
   
   public static String pig(String s)
   {
   
   //SETUP
      quote = findQuotation(s);
      if(quote == true)
      {
         q = s.charAt(0);
         s = s.substring(1,s.length()-1);
      }
      punct = true;
      punctCount = 0;
      
      boolean caps = findCapital(s);
      s = s.substring(0,1).toLowerCase() + s.substring(1);
      int index = findVowel(s);
      
      
   //NO VOWEL
      if(index == -1)
         return "****NO VOWEL****";
           
   //FIRST LETTER VOWEL
      if(index == 0)
      {
         
      //CAPITALIZATION CHECK
         if(caps == true)
            s = s.substring(0,1).toUpperCase() + s.substring(1) + "way";
         else
            s = "" + s + "way";
            
      //PUNCTUATION CHECK
         while(punct == true)
         {
            s = punctuation(s);
            punct = findPunctuation(s);
         }
         
      //QUOTATION CHECK
         if(quote == true)
            s = q + s + q + ""; 
          
         return s;
      }
         
   //QU CASE  
      char u = s.charAt(index);
      char qu = s.charAt(index-1);   
      if(((u=='u')||(u == 'U')) && ((qu=='q')||(qu == 'Q')) )
      {
         s = s.substring(0,2).toLowerCase() + s.substring(2);
         String cons = s.substring(0, index+1);
         s = s.substring(index+1);
         s = s + cons + "ay";
         
         //CAPITALIZATION CHECK
         if(caps == true)
            s = s.substring(0,1).toUpperCase() + s.substring(1);
            
         //PUNCTUATION CHECK
         while(punct == true)
         {
            s = punctuation(s);
            punct = findPunctuation(s);
         }
         
         //QUOTATION CHECK
         if(quote == true)
            s = q + s + q + "";     
         return s;
      }
      
   //REGULAR
      String cons1 = s.substring(0, index);
      s = s.substring(index);
      s = s + cons1 + "ay";
      
      //CAPITALIZATION CHECK
      if(caps == true)
         s = s.substring(0,1).toUpperCase() + s.substring(1);
      
      //PUNCTUATION CHECK
      while(punct == true)
      {
         s = punctuation(s);
         punct = findPunctuation(s);
      }
      
      //QUOTATION CHECK
      if(quote == true)
         s = q + s + q + ""; 
         
      return s;
   }
   
   
   static String vowels = "yaeiouYAEIOU";
   public static int findVowel(String s)
   {
      for(int x = 0; x < s.length(); x ++)
         for(int y = 0; y < vowels.length(); y ++)
            if(s.substring(x, x+1).equals(vowels.substring(y,y+1)))
               if(!(x == 0 && (y == 0 || y == 6)))
                  return x;
      return -1;
   }
   
   
   public static boolean findCapital(String s)
   {
      char s1 = s.charAt(0);
      if(Character.isUpperCase(s1))
         return true;
      return false;
   }
   
   
   static String punctuation = ".,?!:;"; 
   public static boolean findPunctuation(String s)
   {
      for(int x = 0; x < s.length()-punctCount-1; x ++)
         for(int y = 0; y < punctuation.length(); y ++)
            if(s.charAt(x) == punctuation.charAt(y))
            {
               punctCount++;
               return true;
            }
      return false;
   }
   
   
   public static String punctuation(String s)
   {
      for(int x = 0; x < s.length(); x ++)
         for(int y = 0; y < punctuation.length(); y ++)
            if(s.charAt(x) == punctuation.charAt(y))
               return s = s.substring(0,x) + s.substring(x+1) + "" + punctuation.charAt(y);
      return s;
   }
   
   
   public static boolean findQuotation(String s)
   {
      for(int x = 0; x < s.length(); x ++)
         if(s.charAt(x) == '"')
            return true;
      return false;
   }


   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/****************************** 
*  precondition:  both Strings include .txt
*  postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      int wordCount = 0;
      int lineCount = 1;
      while(infile.hasNext() && infile.hasNextLine())
      {
         String s = infile.next();
         wordCount ++;
         if(s == infile.nextLine())
         {
            System.out.println("");
            infile.nextLine();
            lineCount ++;
         }
         else
         {
            infile.reset();
            for(int x = 0; x <= lineCount; x++)
               infile.nextLine();
            for(int y = 0; y < wordCount; y++)
               infile.next();
            s = infile.next();
            s = pig(s);
            outfile.println(s + " ");
         }
      }
      
      
   
   
   
   
   
      outfile.close();
      infile.close();
   }
}