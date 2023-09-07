// Name:Sophia Evanisko
// Date:09/13/2018
  
public class Sentence_Driver
{
   public static void main(String[] args)
   {
      System.out.println("PALINDROME TESTER");
      
      Sentence s = new Sentence("bananab");
      System.out.println(s.isPalindrome());
      
      
      s = new Sentence( "\"Hello there!\" she said." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
      s = new Sentence( "A Santa lived as a devil at NASA." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
     
   
      s = new Sentence( "Flo, gin is a sin! I golf." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
   
      s = new Sentence( "Eva, can I stab bats in a cave?" );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
   
      s = new Sentence( "Madam, I'm Adam." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
   // Lots more test cases.  Test every line of code.  Test
   // the extremes, test the boundaries.  How many test cases do you need?
   
   }
}

class Sentence
{
   private String mySentence;
   private int myNumWords;
   
   //Precondition:  str is not empty.
   //               Words in str separated by exactly one blank.
   public Sentence( String str )
   { 
      mySentence = str;
      for(int x = 0; x < str.length(); x++)
         if(str.charAt(x) == ' ')
            myNumWords++;
      myNumWords++;  
   }
   
   public int getNumWords()
   {  
      return myNumWords;  
   }
   
   public String getSentence()
   {
      return mySentence; 
   }
   
   //Returns true if mySentence is a palindrome, false otherwise.
   String s = "";
   public boolean isPalindrome()
   {
      mySentence = removePunctuation(getSentence());
      mySentence = lowerCase(getSentence());
      s = removeBlanks(getSentence());
      mySentence = s;
      return (isPalindrome(getSentence(), 0, s.length()-1));  
   }
   //Precondition: s has no blanks, no punctuation, and is in lower case.
   //Returns true if s is a palindrome, false otherwise.
   public static boolean isPalindrome( String s, int start, int end )
   {
      if(start >= end)
         return true;
      if (s.charAt(start) == s.charAt(end))
         return isPalindrome(s, start+1, end-1);
      else
         return false; 
   }
   //Returns copy of String s with all blanks removed.
   //Postcondition:  Returned string contains just one word.
   public static String removeBlanks( String s )
   {  
      String x = ""; 
      String[] split = s.split(" ");
      for( String r : split)
         x = x + r; 
      return x; 
   }
   
   //Returns copy of String s with all letters in lowercase.
   //Postcondition:  Number of words in returned string equals
   //						number of words in s.
   public static String lowerCase( String s )
   {  
      return s.toLowerCase();  
   }
   
   //Returns copy of String s with all punctuation removed.
   //Postcondition:  Number of words in returned string equals
   //						number of words in s.
   public static String punct = ",./;:'?<>[]{}|`~!@#$%^&*()\"";
   public static int punctCount = 0;
   public static String[] x;
   public static String a = "";
   public static char quotes = '"';
   public static String removePunctuation( String s )
   { 
      for(int y = 0; y < punct.length(); y++)
         if(s.indexOf(punct.charAt(y)) != -1)
            s = s.replace(punct.substring(y, y+1), "" );
   
      return s; 
   }
}

 /*****************************************
   
 PALINDROME TESTER
 "Hello there!" she said.
 4
 false
 
 A Santa lived as a devil at NASA.
 8
 true
 
 Flo, gin is a sin! I golf.
 7
 true
 
 Eva, can I stab bats in a cave?
 8
 true
 
 Madam, I'm Adam.
 3
 true

 **********************************************/
