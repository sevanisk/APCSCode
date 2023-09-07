//Name:Sophia Evanisko   
//Date:12/20/2018
//The Twitter API is at http://twitter4j.org

import twitter4j.*;       //set the classpath to lib\twitter4j-core-4.0.7.jar
import java.util.*;
import java.io.*;

public class Twitter_Driver
{
   private static PrintStream consolePrint;

   public static void main (String []args) throws TwitterException, IOException
   {
      consolePrint = System.out; // this preserves the standard output so we can get to it later      
   
      // PART 1
      // set up classpath and properties file
          
      TJTwitter bigBird = new TJTwitter(consolePrint);
      
      // Create and set a String called message here
   
      String message = "hi my name is sophia. :D";
      bigBird.tweetOut(message);
   
       
   
      // PART 2
      // Choose a public Twitter user's handle 
   
      Scanner scan = new Scanner(System.in);
      consolePrint.print("Please enter a Twitter handle, do not include the @symbol --> ");
      String twitter_handle = scan.next();
       
      // Find and print the most popular word they tweet 
      while (!twitter_handle.equals("done"))
      {
         bigBird.queryHandle(twitter_handle);
         consolePrint.println("The most common word from @" + twitter_handle + " is: " + bigBird.mostPopularWord()+ ".");
         consolePrint.println("The word appears " + bigBird.getFrequencyMax() + " times.");
         consolePrint.println();
         consolePrint.print("Please enter a Twitter handle, do not include the @ symbol --> ");
         twitter_handle = scan.next();
      }
   
   //PART 3
      bigBird.investigate();
      
      
   }//end main         
      
}//end driver        
      
class TJTwitter 
{
   private Twitter twitter;
   private PrintStream consolePrint;
   private List<Status> statuses;
   private int numberOfTweets; 
   private List<String> terms;
   private String popularWord;
   private int frequencyMax;
  
   public TJTwitter(PrintStream console)
   {
      // Makes an instance of Twitter - this is re-useable and thread safe.
      // Connects to Twitter and performs authorizations.
      twitter = TwitterFactory.getSingleton(); 
      consolePrint = console;
      statuses = new ArrayList<Status>();
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
  /******************  Part 1 *******************/
  /** 
   * This method tweets a given message.
   * @param String  a message you wish to Tweet out
   */
   public void tweetOut(String message) throws TwitterException, IOException
   {
      twitter.updateStatus(message);  
   }

   
  /******************  Part 2 *******************/
  /** 
   * This method queries the tweets of a particular user's handle.
   * @param String  the Twitter handle (username) without the @sign
   */
   @SuppressWarnings("unchecked")
   public void queryHandle(String handle) throws TwitterException, IOException
   {
      statuses.clear();
      terms.clear();
      fetchTweets(handle);
      splitIntoWords();	
      removeCommonEnglishWords();
      sortAndRemoveEmpties();
      mostPopularWord(); 
   }
	
  /** 
   * This method fetches the most recent 2,000 tweets of a particular user's handle and 
   * stores them in an arrayList of Status objects.  Populates statuses.
   * @param String  the Twitter handle (username) without the @sign
   */
   public void fetchTweets(String handle) throws TwitterException, IOException
   {
      // Creates file for dedebugging purposes
      PrintStream fileout = new PrintStream(new FileOutputStream("tweets.txt")); 
      Paging page = new Paging (1,200);
      int p = 1;
      while (p <= 10)
      {
         page.setPage(p);
         statuses.addAll(twitter.getUserTimeline(handle,page)); 
         p++;        
      }
      numberOfTweets = statuses.size();
      fileout.println("Number of tweets = " + numberOfTweets);
   
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
   * This method removes punctuation (but not apostrophes) from each individual word.
   * Consider reusing code you wrote for a previous lab.     
   * Consider if you want to remove the # or @ from your words. Could be interesting to keep (or remove).
   * @ param String  the word you wish to remove punctuation from
   * @ return String the word without any punctuation  
   */
   static String punctuation = ",./;:\"?<>[]{}|`~!$^*()";
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


  /******************  Part 3 *******************/
   public void investigate ()
   {
   //how many people tweet about TJ?
      System.out.println("How many people near TJ tweet about TJHSST? What word do they use most often?");
      System.out.println("");
      Query query = new Query("TJHSST");
      query.setCount(100);
      query.setGeoCode(new GeoLocation(38.8170,-77.1679), 100, Query.MILES);
      try{
         QueryResult results = twitter.search(query);
         System.out.println("Count: " + results.getTweets().size());
         for (Status tweet : results.getTweets()) {
            System.out.println("@"+tweet.getUser().getName()+ ": " + tweet.getText()); 
         }
      } 
      catch (TwitterException e) {
         e.printStackTrace();
      } 
      System.out.println(); 
      
      //how many people tweet at Donald Trump?
      System.out.println("How many people tweet to Donald Trump?");
      System.out.println("");
      Query query2 = new Query("@realDonaldTrump");
      query.setCount(1000);
      query.setSince("2017-1-1");
      try{
         QueryResult results = twitter.search(query2);
         System.out.println("Count: " + results.getTweets().size());
         for (Status tweet : results.getTweets()) {
            System.out.println("@"+tweet.getUser().getName()+ ": " + tweet.getText()); 
         }
      } 
      catch (TwitterException e) {
         e.printStackTrace();
      } 
      System.out.println();
      
      System.out.println("How many people tweet about Miami Dolphins?");
      sampleInvestigate();
   }
      

 
  /** 
   * This method determines how many people in Arlington, VA 
   * tweet about the Miami Dolphins.  Hint:  not many. :(
   */
   public void sampleInvestigate()
   {
      Query query = new Query("Miami Dolphins");
      query.setCount(100);
      query.setGeoCode(new GeoLocation(38.8372839,-77.1082443), 5, Query.MILES);
      query.setSince("2015-12-1");
      try {
         QueryResult result = twitter.search(query);
         System.out.println("Count : " + result.getTweets().size()) ;
         for (Status tweet : result.getTweets()) {
            System.out.println("@"+tweet.getUser().getName()+ ": " + tweet.getText());  
         }
      } 
      catch (TwitterException e) {
         e.printStackTrace();
      } 
      System.out.println(); 
   }  
}  


