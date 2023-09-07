// Name: Sophia Evanisko
// Date: 09/12/2018
  
import java.util.*;
import java.io.*;

public class PigLatin
{
   public static void main(String[] args) 
   {
      //part_1_using_pig();
      part_2_using_piglatenizeFile();
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
   public static char p = '"';
   public static boolean quote = true;
   public static boolean dash, dash1, quote1 = false;
   public static boolean middash = false;
   
   
   public static String pig(String s)
   {
      dash = false;
      dash1 = false;
      quote1 = false;
      punctCount = 0;
      middash = false;
   //SETUP
      if(s.charAt(0) == '"' && s.charAt(1) == '"' && s.charAt(s.length()-2) == '"' && s.charAt(s.length()-1) == '"')
      {
         s = s.substring(1, s.length()-1);
         quote1 = true;
      }
      
      quote = findQuotation(s);
      if(quote == true)
      {
         q = s.charAt(0);
         s = s.substring(1,s.length()-1);
      }
      
      if(s.length() > 1)
         if(s.substring(0,2).equals("--"))
         {
            dash1 = true;
            s = s.substring(2);
         }
         
         else if(s.substring(0,1).equals("-"))
         {
            dash = true;
            s = s.substring(1);
         }
         
      for(int x = 0; x < s.length(); x++)
         if(s.charAt(x) == '-')
            if ((Character.isLetter(s.charAt(x-1)) == true) && Character.isLetter(s.charAt(x+1)) == true)
               middash = true;
      if(middash == false)
         punct = findPunctuation(s);
      else 
         punct = afindPunctuation(s);
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
            if(middash == false)
            {
               s = punctuation(s);
               punct = findPunctuation(s);
            }
            else
            {
               s = altPunctuation(s); 
               punct = afindPunctuation(s);
            }
         }
         
         if(dash1 == true)
            s = "--" + s;
         
         if(dash == true)
            s = "-" + s;
            
      //QUOTATION CHECK
         if(quote == true)
            s = q + s + q + ""; 
         if(quote1 == true)
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
            if(middash == false)
            {
               s = punctuation(s);
               punct = findPunctuation(s);
            }
            else
            {
               s = altPunctuation(s); 
               punct = afindPunctuation(s);
            }
         }
         
         if(dash1 == true)
            s = "--" + s;
                  
         if(dash == true)
            s = "-" + s; 
            
         //QUOTATION CHECK
         if(quote == true)
            s = q + s + q + "";
         if(quote1 == true)
            s = q + s + q + ""; 
        
         return s;
      }
      
   //REGULAR
      String cons1 = s.substring(0, index);
      s = s.substring(index);
      s = s + cons1 + "ay";
      
      //PUNCTUATION CHECK
      while(punct == true)
      {
         if(middash == false)
         {
            s = punctuation(s);
            punct = findPunctuation(s);
         }
         else
         {
            s = altPunctuation(s); 
            punct = afindPunctuation(s);
         }
      }
      
      //CAPITALIZATION CHECK
      if(caps == true)
         s = s.substring(0,1).toUpperCase() + s.substring(1);
      
      if(dash1 == true)
         s = "--" + s;
            
      if(dash == true)
         s = "-" + s; 
         
      //QUOTATION CHECK
      if(quote == true)
         s = q + s + q + "";
      if(quote1 == true)
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
   
   
   static String punctuation = ".,?!:;-[]"; 
   static String altpunctuation = ".,?!:;[]";
   public static boolean findPunctuation(String s)
   {
      for(int x = 0; x < s.length()-punctCount; x ++)
         for(int y = 0; y < punctuation.length(); y ++)
            if(s.charAt(x) == punctuation.charAt(y))
            {
               if((x==0 || x== s.length()-1-punctCount) && (!(Character.isLetter(x-1))))
               {
                  punctCount++;
                  return true;
               }
            } 
      return false;
   }
   
   public static boolean afindPunctuation(String s)
   {
      for(int x = 0; x < s.length()-punctCount; x ++)
         for(int y = 0; y < altpunctuation.length(); y ++)
            if(s.charAt(x) == altpunctuation.charAt(y))
            {
               if((x==0 || x== s.length()-1-punctCount) && (!(Character.isLetter(x-1))))
               {
                  punctCount++;
                  return true;
               }
            } 
      return false;
   }
   
   public static String punctuation(String s)
   {
      for(int x = 0; x < s.length(); x ++)
         for(int y = 0; y < punctuation.length(); y ++)
            if(s.charAt(x) == punctuation.charAt(y))
               if(x !=0 && x != s.length()-1)
                  return s = s.substring(0,x) + s.substring(x+1) + "" + punctuation.charAt(y);
      return s;
   }
   
   public static String altPunctuation(String s)
   {
      for(int x = 0; x < s.length(); x ++)
         for(int y = 0; y < altpunctuation.length(); y ++)
            if(s.charAt(x) == altpunctuation.charAt(y))
               if(x !=0 && x != s.length()-1)
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
   public static boolean rslash = false;
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
   
      Scanner infile = null;
      Scanner line = null;
      try
      {
         infile = new Scanner(new File(fileNameIn)); 
         line = new Scanner(new File(fileNameIn)).useDelimiter("\n"); 
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
      
      
      while(infile.hasNext() && infile.hasNextLine())
      {
         String line1 = "";
         String s = "";
         int wordCount = 0;
         do
         {
            rslash = false;
            line1 = line.next();
            String[] str = line1.split(" ");
            for(String r : str)
            {
            
               rslash = findr(r);
               
               if(r.equals("\r"))
                  outfile.print("\r");
                  
               else if(rslash == true)
               {
                  String[] split = r.split("\r");
                  for(String sp : split)
                     s = pig(sp);
                  outfile.println(s + " ");
               }
                 
               else if(r.length() > 0)
               {
                  s = pig(r);
                  outfile.print(s + " ");
               }
               
               else
               {
                  outfile.println("");
                  outfile.println("");
               }
            }
            infile.nextLine();
         }
         while(infile.hasNextLine());
      }
   
      outfile.close();
      infile.close();
   }
   
   public static boolean findr(String s)
   {
      for(int x = 0; x < s.length(); x++)
         if(s.charAt(x) == '\r')
            return true;
      return false;
   }
}