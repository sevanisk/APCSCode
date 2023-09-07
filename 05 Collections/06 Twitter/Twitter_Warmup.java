// Name:Sophia Evanisko
// Date:12/19/2018

import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.*;

public class Twitter_Warmup
{
   public static void main (String []args) throws IOException
   {      
      TJTwitter2 twitter = new TJTwitter2();
      
      //  testing remove punctuation
      String s1 = "abcd?";
      String s2 = "!abc$d";
      String s3 = "ab:cd..";
      String s4 = "abc'd";
      System.out.println( s1 + " without puncutation is " + twitter.removePunctuation(s1) );
      System.out.println( s2 + " without puncutation is " + twitter.removePunctuation(s2) );
      System.out.println( s3 + " without puncutation is " + twitter.removePunctuation(s3) );
      System.out.println( s4 + " without puncutation is " + twitter.removePunctuation(s4) );
      
      System.out.println();
      
      String f1 = "story.txt";
      String f2 = "test.txt";
      System.out.println("For the file: " + f1);
      twitter.queryHandle("story.txt");
      System.out.println("Most popular word: " + twitter.mostPopularWord());
      System.out.println("Frequency: " + twitter.getFrequencyMax());
      System.out.println();
      
      System.out.println("For the file: " + f2);
      twitter.queryHandle("test.txt");
      System.out.println("Most popular word: " + twitter.mostPopularWord());
      System.out.println("Frequency: " + twitter.getFrequencyMax());     
   }             
}        

class TJ_Status2
{
   private String text;
   
   public TJ_Status2(String s)
   {
      text = s;
   }
   public String getText()
   {
      return text;
   }   
}
      
class TJTwitter2 
{
   private List<TJ_Status2> statuses;
   private int numberOfTweets; 
   private List<String> terms;
   private String popularWord;
   private int frequencyMax;
   
   public TJTwitter2() throws IOException
   {
      statuses = new ArrayList<TJ_Status2>();
      terms = new ArrayList<String>();
   }
   
   public List<String> getTerms()
   {
      return terms;
   }
   
   public int getNumberOfTweets()
   {
      return numberOfTweets;
   }
   
   public String getMostPopularWord()
   {
      return popularWord;
   }
   
   public int getFrequencyMax()
   {
      return frequencyMax;
   }
   
   @SuppressWarnings("unchecked")
   public void queryHandle(String handle)throws IOException   
   {
      statuses.clear();
      terms.clear();
      fetchTweets(handle);
      System.out.println("Number of tweets: " + getNumberOfTweets());
      splitIntoWords(); 
      System.out.println("All the words: " + terms);
      removeCommonEnglishWords();
      System.out.println("Remove common words: " +terms);
      sortAndRemoveEmpties();
      System.out.println("Sorted: " + terms);
      mostPopularWord();
   }
    
   /** 
    * This method reads a file of tweets and 
    * stores them in an arrayList of TJ_Status2 objects.  
    * Populates statuses.
    * @param String  the text file
    */
   public void fetchTweets(String handle) throws IOException
   {
      Scanner scan = new Scanner(new File(handle));
      while(scan.hasNext())
         statuses.add(new TJ_Status2(scan.nextLine()));
      numberOfTweets = statuses.size();  
   }   

   /** 
    * This method takes each status and splits them into individual words.   
    * Remove punctuation by calling removePunctuation, then store the word in terms.  
    */
   public void splitIntoWords()
   {
      String[] s;
      for(int x = 0; x < statuses.size(); x++)
      {
         s = statuses.get(x).getText().split(" ");
         for(String term : s)
         {
            term = removePunctuation(term);
            term = term.toLowerCase();
            terms.add(terms.size(), term);
         } 
      
      }
   }
   /** 
    * This method removes common punctuation (but not apostrophes) from each individual word.
    * This method removes empty strings.
    * This method changes everything to lower case.
    * Consider reusing code you wrote for a previous lab.     
    * Consider if you want to remove the # or @ from your words. Could be interesting to keep (or remove).
    * @ param String  the word you wish to remove punctuation from
    * @ return String the word without any punctuation, all lower case  
    */
   static String punctuation = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public String removePunctuation(String s)
   {
      for(int x = 0; x < s.length(); x ++)
         for(int y = 0; y < punctuation.length(); y ++)
            if(s.charAt(x) == punctuation.charAt(y))
            {
               if(x !=0 && x != s.length()-1)
               {
                  s = s.substring(0,x) + s.substring(x+1);
                  y--;
               }
               else if(x == s.length() -1)
                  return s.substring(0, s.length()-1);
               else if(x == 0)
               {
                  s = s.substring(1);
                  y--;
               }
            }
      return s;           
   }

   /** 
    * This method removes common English words from the list of terms.
    * Remove all words found in commonWords.txt  from the argument list.    
    * The count will not be given in commonWords.txt. You must count the number of words in this method.  
    * This method should NOT throw an excpetion.  Use try/catch.   
    */
   @SuppressWarnings("unchecked")
   public void removeCommonEnglishWords()
   {  
      Scanner sc = null;
      try
      {
         sc = new Scanner (new File("commonWords.txt"));
      }
      catch(FileNotFoundException e)
      {
         System.out.println("not a valid file");
      }
      
      String s = "";
      while(sc.hasNextLine())
      {
         s= sc.nextLine();
         for(int termz = 0; termz < terms.size(); termz++)
            if(terms.get(termz).equalsIgnoreCase(s))
               terms.remove(termz);
      }
            
      
   }

   /** 
    * This method sorts the words in terms in alphabetically (and lexicographic) order.  
    * You should use your sorting code you wrote earlier this year.
    * Remove all empty strings while you are at it.  
    */
   @SuppressWarnings("unchecked")
   public void sortAndRemoveEmpties()
   {
      for(int x = 0; x < terms.size(); x++)
         if(terms.get(x).isEmpty())
            terms.remove(x);
            
      Comparable[] alist = new Comparable[terms.size()];
      for(int x = 0; x < terms.size(); x++)
         alist[x] = terms.get(x);
      
      sort(alist);
      
      for(int x = 0; x < terms.size(); x++)
         terms.set(x, alist[x] + "");
      
   }
   
   private static void sort(Comparable[] array)
   {
      int a = 0;
      for(int x = 0; x < array.length; x++)
      {
         swap(array, findMax(array, x), array.length-1-a);
         a++;
      }
   }
  
   @SuppressWarnings("unchecked")
    public static int findMax(Comparable[] array, int upper)
   {
      Object max = array[0];
      int maxPos = 0;
      for(int x = 0; x < array.length - upper; x ++)
         if(array[x].compareTo(max) > 0)
         {
            max = array[x];
            maxPos = x;
         }
      return maxPos;
   }
   
   public static void swap(Object[] array, int a, int b)
   {
      Object x = array[a];
      array[a] = array[b];
      array[b] = x;
   }
   
   /** 
    * This method returns the most common word from terms.    
    * Consider case - should it be case sensitive?  The choice is yours.
    * @return String the word that appears the most times
    * @post will popopulate the frequencyMax variable with the frequency of the most common word 
    */
   @SuppressWarnings("unchecked")
   public String mostPopularWord()
   {
      int count = 0;
      int max = 0;
      int maxIndex = 0;
      String s = "";
      for(int st = 0; st < terms.size(); st++)
      {
         count = 0;
         s = terms.get(st);
         for(int st2 = 0; st2 < terms.size(); st2++)
            if(terms.get(st2).equals(s))
               count++;
         if(count > max)
         {
            max = count;
            maxIndex = st;
         }
      }
      frequencyMax = max;
      return terms.get(maxIndex);   
   }
}  

/******************************** Sample output
 
 abcd
 abcd
 abcd
 abc'd
 
 Number of tweets = 28
 All the words: [as, a, 20-year-old, pfc, in, the, air, force, oct, 27, 1949, was, a, day, , i'll, always, remember, i, was, stationed, at, chanute, field, illinois, after, finishing, basic, training, at, sheppard, air, force, base, in, texas, i, was, transferred, to, chanute, to, attend, aircraft, , engine, and, general, aircraft, training, while, on, barracks, cleanup, duty, i, found, a, copy, of, the, , vancouver, sun, newspaper, from, , british, columbia, the, front-page, article, was, about, the, pacific, national, exhibition, beauty, contest, with, a, photo, of, the, winner, miss, vancouver, the, article, also, listed, the, 11, other, contestants, and, the, cities, they, represented, well, , i, got, the, bright, idea, of, writing, a, letter, to, the, winner, hoping, to, get, some, mail, in, return, since, i, had, been, away, from, home, for, almost, a, year, the, highlight, of, my, day, was, mail, call, i, wrote, to, two, other, contestants, as, well, but, had, only, their, , cities, to, use, for, the, address, i, was, shocked, when, i, got, return, letters, from, all, three, contestants, i, was, very, impressed, with, the, letter, from, miss, port, moody, kay, ronco, and, we, began, writing, regularly, by, this, time, , i, had, finished, the, tech, school, programs, and, was, transferred, to, a, base, in, omaha, nebraska, , kay, and, i, continued, to, write, after, seven, months, i, was, made, a, crew, member, on, a, b-29, bomber, , scheduled, to, fly, to, seattle, washington, for, modification]
 Remove common words: [20-year-old, pfc, air, force, oct, 27, 1949, day, , i'll, always, remember, stationed, chanute, field, illinois, after, finishing, basic, training, sheppard, air, force, base, texas, transferred, chanute, attend, aircraft, , engine, general, aircraft, training, while, barracks, cleanup, duty, found, copy, , vancouver, sun, newspaper, , british, columbia, front-page, article, pacific, national, exhibition, beauty, contest, photo, winner, miss, vancouver, article, also, listed, 11, other, contestants, cities, represented, well, , got, bright, idea, writing, letter, winner, hoping, mail, return, since, away, home, almost, year, highlight, day, mail, call, wrote, other, contestants, well, only, , cities, address, shocked, got, return, letters, three, contestants, very, impressed, letter, miss, port, moody, kay, ronco, began, writing, regularly, time, , finished, tech, school, programs, transferred, base, omaha, nebraska, , kay, continued, write, after, seven, months, made, crew, member, b-29, bomber, , scheduled, fly, seattle, washington, modification]
 Sorted: [11, 1949, 20-year-old, 27, address, after, after, air, air, aircraft, aircraft, almost, also, always, article, article, attend, away, b-29, barracks, base, base, basic, beauty, began, bomber, bright, british, call, chanute, chanute, cities, cities, cleanup, columbia, contest, contestants, contestants, contestants, continued, copy, crew, day, day, duty, engine, exhibition, field, finished, finishing, fly, force, force, found, front-page, general, got, got, highlight, home, hoping, i'll, idea, illinois, impressed, kay, kay, letter, letter, letters, listed, made, mail, mail, member, miss, miss, modification, months, moody, national, nebraska, newspaper, oct, omaha, only, other, other, pacific, pfc, photo, port, programs, regularly, remember, represented, return, return, ronco, scheduled, school, seattle, seven, sheppard, shocked, since, stationed, sun, tech, texas, three, time, training, training, transferred, transferred, vancouver, vancouver, very, washington, well, well, while, winner, winner, write, writing, writing, wrote, year]
 Most popular word: contestants
 Frequency: 3
 
 Number of tweets = 6
 All the words: [this, is, a, test, to, check, mia, if, mia, it's, working, or, a, a, a, not, mia]
 Remove common words: [test, check, mia, mia, it's, working, mia]
 Sorted: [check, it's, mia, mia, mia, test, working]
 Most popular word: mia
 Frequency: 3
 

*********************************************************/
