// Name: Sophia Evanisko
// Date: 3/10/19

import java.io.*;
import java.util.*;  

/* This program takes a text file, creates an index (by line numbers)
 * for all the words in the file and writes the index
 * into the output file.  The program prompts the user for the file names.
 */
public class IndexMakerMap
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String infileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(infileName));
      
      DocumentIndex index = makeIndex(inputFile);
      
      //System.out.println( index.toString() );
      PrintWriter outputFile = new PrintWriter(new FileWriter("fishIndex.txt"));
      outputFile.println(index.toString());
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }
   
   public static DocumentIndex makeIndex(Scanner inputFile)
   {
      DocumentIndex index = new DocumentIndex(); 	
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      return index;  
   }
}

class DocumentIndex extends TreeMap<String, TreeSet<Integer>>
{
   //constructors
   TreeMap<String, TreeSet<Integer>> t;
   
   public DocumentIndex()
   {
      t = new TreeMap<String, TreeSet<Integer>>();
   }


   public void addWord(String word, int num)
   {
      word = word.toUpperCase();
      if(containsKey(word))
      {
         get(word).add(num);
      }
      else
      {
         ArrayList<Integer> a = new ArrayList<Integer>();
         a.add(num);
         put(word, new TreeSet<Integer>(a));
      }
   }
      

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


   public String toString()
   {
      String s = "";
      for (String treeKey : keySet())
      {
         s+=(treeKey + " ");
         TreeSet<Integer> t = get(treeKey);
         Iterator<Integer> it = t.iterator();
         while(it.hasNext())
            s += it.next() + ", ";
         s = s.substring(0, s.length()-2);
       s += "\n";
      }
      return s;
   
   }
}

   


//  
/**********************************************
     ----jGRASP exec: java -ea IndexMakerMap
 
 Enter input file name: fish.txt
 Done.
 
  ----jGRASP: operation complete.
  
************************************************/
/****************** fishIndex.txt  **************
A 12, 14, 15
ARE 16
BLACK 6
BLUE 4, 7
CAR 14
FISH 1, 2, 3, 4, 6, 7, 8, 9, 16
HAS 11, 14
LITTLE 12, 14
LOT 15
NEW 9
OF 16
OLD 8
ONE 1, 11, 14
RED 3
SAY 15
STAR 12
THERE 16
THIS 11, 14
TWO 2
WHAT 15   
   ************************/
