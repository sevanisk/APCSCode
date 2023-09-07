// Name:Sophia Evanisko
// Date:4/10/19
import java.util.*;
import java.io.*;
public class Huffman
{
   public static Scanner keyboard = new Scanner(System.in);
   public static void main(String[] args) throws IOException
   {
      //Prompt for two strings 
      System.out.print("Encoding using Huffman codes");
      System.out.print("\nWhat message? ");
      String message = keyboard.nextLine();
   
      System.out.print("\nEnter middle part of filename:  ");
      String middlePart = keyboard.next();
   
      huffmanize( message, middlePart );
   }
   
   //public static HuffmanTreeNode t;
   public static void huffmanize(String message, String middlePart) throws IOException
   {
      String hold = message;
      Map<Character, Integer> freq = new TreeMap<Character, Integer>();
      Queue<HuffmanTreeNode> myPairs = new PriorityQueue<HuffmanTreeNode>();
      char letter = ' ';
               //Make a frequency table of the letters *
      while(!message.isEmpty())
      {
         letter = message.charAt(0);
         if(freq.containsKey(letter))
         {
            freq.put(letter, freq.get(letter) + 1);
         }
         else
            freq.put(letter, 1);
            
         message = message.substring(1);
      }
   
      //Put each letter-frequency pair into a HuffmanTreeNode. Put each 
      //        node into a priority queue (or a min-heap). * 
      HuffmanTreeNode t;
      for(char key: freq.keySet())
      {
         t = new HuffmanTreeNode(key, freq.get(key));
         myPairs.add(t);
      }
         
      //Use the priority queue of nodes to build the Huffman tree*
      while(myPairs.size() > 1)
      {
         myPairs.add(new HuffmanTreeNode('*', 0, myPairs.remove(), myPairs.remove()));
      }
      
      HuffmanTreeNode myTree = myPairs.remove();
      HuffmanTreeNode point = myTree;
      TreeMap<Character, String> schemes = genCodes(point, "", hold, new TreeMap<Character, String>());
      
            	//Process the string letter-by-letter and search the tree for the 
   		//       letter. It's recursive. As you recur, build the path through the tree, 
   		//       where going left is 0 and going right is 1.
      String binMess = "";   
      while(!hold.isEmpty())
      {
         letter = hold.charAt(0);
         binMess += (schemes.get(letter) + "");
         hold = hold.substring(1);
      }
      
        //Write the binary message to the hard drive using the file name ("message." + middlePart + ".txt")
      PrintWriter outputFile2 = new PrintWriter(new FileWriter("message." + middlePart + ".txt"));
      outputFile2.println(binMess);
      outputFile2.close();
   
              
      //System.out.println the binary message
      System.out.println(binMess);
      System.out.println(""); 
      
      for(Character c1: schemes.keySet())
      {
         String s2 = c1 + schemes.get(c1);
         System.out.println(s2); 
      }
       
      //Write the scheme to the hard drive using the file name ("scheme." + middlePart + ".txt") 
      PrintWriter outputFile = new PrintWriter(new FileWriter("scheme." + middlePart + ".txt"));
      for(Character c: schemes.keySet())
      {
         String s = c + schemes.get(c);
         outputFile.println(s); 
      }
      outputFile.close();
   }
   
   public static String find(HuffmanTreeNode point, char letter, int lorr)
   {
      String x = "";
      if(point.getLeft() == null && point.getRight() == null)
         return "";
      x += find(point.getLeft(), letter, 0);
      x += find(point.getRight(), letter, 1); 
      if (point.getLetter() == '*')
         x += (lorr + "");
      return x; 
      
   }
  
   //System.out.println the scheme from the tree--needs a recursive helper method
   public static TreeMap<Character, String> genCodes(HuffmanTreeNode node, String s, String mess, TreeMap<Character, String> codes) {
      if (node != null)
      { 
         if (node.getLeft() != null)
            genCodes(node.getLeft(), s + "0", mess, codes);
            
         if (node.getRight() != null)
            genCodes(node.getRight(), s + "1", mess, codes);
      
         if (node.getLeft() == null && node.getRight() == null)
            codes.put(node.getLetter(), s);
      }
      return codes;
   }
               
         
            
   
}
	/*
	  * This tree node stores two values.  Look at TreeNode's API for some help.
	  * The compareTo method must ensure that the lowest frequency has the highest priority.
	  */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
   private char letter;
   private int frequency; 
   private HuffmanTreeNode left, right;
   
   public HuffmanTreeNode(char letter, int freq)
   { 
      this.letter = letter; 
      this.frequency = freq;
      left = null; 
      right = null; 
   }
   
   public HuffmanTreeNode(char letter, int freq, HuffmanTreeNode initLeft, HuffmanTreeNode initRight)
   { 
      this.letter = letter; 
      this.frequency = freq;
      right = initLeft; 
      left = initRight; 
   }
   
   public char getLetter()
   { 
      return letter; 
   }
   
   public int getFrequency()
   { 
      return frequency; 
   }
   
   public HuffmanTreeNode getLeft() 
   { 
      return left; 
   }
   
   public HuffmanTreeNode getRight() 
   { 
      return right; 
   }
   
   public void setLetter(char newLetter) 
   { 
      this.letter = newLetter; 
   }
   
   public void setFrequency(int newFreq) 
   { 
      this.frequency = newFreq; 
   }
   
   public void addFreq()
   {
      this.frequency++;
   }
   
   public void setLeft(HuffmanTreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(HuffmanTreeNode theNewRight)
   { 
      right = theNewRight;
   }
   
   public int compareTo(HuffmanTreeNode h)
   {
      if(this.frequency > h.getFrequency())
         return 1;
      if(this.frequency < h.getFrequency())
         return -1;
      return 0;
   }
   
}
