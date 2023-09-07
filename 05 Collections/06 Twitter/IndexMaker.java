// Name: Sophia Evanisko
// Date: 12/11/18

import java.io.*;
import java.util.*;

/**
 * Program takes a text file then creates an index (by line numbers)
 * for all the words in the file.
 * Writes the index into output file.
 * Program prompts user for file names.
 */  
public class IndexMaker
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(inFileName));
      String outFileName = "fishIndex.txt";
      PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
      indexDocument(inputFile, outputFile);
      inputFile.close();                  
      outputFile.close();
      System.out.println("Done.");
   }
   
   public static void indexDocument(Scanner inputFile, PrintWriter outputFile)
   {
      DocumentIndex index = new DocumentIndex();
      String line = null;
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      for(IndexEntry entry : index.myList)
         outputFile.println(entry);
   }   
}

class DocumentIndex extends ArrayList<IndexEntry>
{
    //constructors
   ArrayList<IndexEntry> myList;
   public DocumentIndex()
   {
      myList = new ArrayList<IndexEntry>(0);
   }
    
   public DocumentIndex(int n)
   {
      ArrayList<IndexEntry> z = new ArrayList<IndexEntry>(n);
      myList = z;
   }
   
   /**
    * Calls foundOrInserted, which returns an index.
    * At that position, updates that IndexEntry's 
    * list of line numbers with num.   
    */
   public void addWord(String word, int num)
   {
      int index = foundOrInserted(word);
      myList.get(index).add(num);
   }
      
   /**
    * Extracts all words from str, skipping 
    * punctuation and whitespace.
    * For each word calls addWord(word, num).
    * Use split with punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()" 
    */
   public void addAllWords(String str, int num) 
   {
      String empty = "";
      str = punctuation(str);
      String[] words = str.split(" ");
      for(String word : words)
         if(!(word.equals(empty)))
            addWord(word, num);
   }
    
   static String punctuation = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static String punctuation(String s)
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
    * Traverses this DocumentIndex comparing word to the words in the 
    * IndexEntry objects in list, looking for correct position of word. 
    * If an IndexEntry with word is not already in that position, the 
    * method creates and inserts a new IndexEntry at that position. 
    * @return position of either the found or the inserted IndexEntry.
    */
   private int foundOrInserted(String word)
   {
   word = word.toUpperCase();
      IndexEntry n = new IndexEntry(word);
      int x = 0;
      if(myList.size() == 0)
      {
         myList.add(n);
         return 0;
      }
         
      ListIterator<IndexEntry> i = myList.listIterator();
      while(i.hasNext() && n.compareTo(myList.get(x)) >= 0)
      {
      if(n.compareTo(myList.get(x)) == 0)
            return x;
         x++;
         i.next();
      }
      //String z = ((myList.get(x)).getWord());
      myList.add(x, new IndexEntry(word));
      return x; 
   }
}
   
class IndexEntry implements Comparable<IndexEntry>
{
   //fields
   private String word;
   private ArrayList<Integer> numList;
   
   //constructors
   public IndexEntry(String word)
   {
      this.word = word.toUpperCase();
      this.numList = new ArrayList<Integer>(0);
   }
   
   public IndexEntry(String word, int index)
   {
      this.word = word.toUpperCase();
      this.numList.add(index);
   }
   
   /** 
    * Appends num to numList but only if not already in list. 
    */
   public void add(int num)
   {
      boolean alreadyThere = false;
      for(int x = 0; x < numList.size(); x++)
         if(numList.get(x) == num)
            alreadyThere = true;
      if(alreadyThere == false)
         numList.add(num);
   }
      
   public String getWord()
   {
   String x = word;
      return x;
   }
      
   public String toString()
   {
      String x = word + " ";
      x += (numList.get(0) + "");
      for(int y = 1; y < numList.size(); y++)
         x += ", " + numList.get(y) ;
      return x;
   }
   
   public int compareTo(IndexEntry x)
   {
   int a = word.compareTo(x.getWord());
      return a;
   }
}