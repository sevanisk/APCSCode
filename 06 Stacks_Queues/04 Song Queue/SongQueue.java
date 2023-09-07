// Name: Sophia Evanisko
// Date: 1/19/2019

import java.io.*;
import java.util.*;

public class SongQueue
{
   private static Scanner keyboard;  //use this global Scanner for this lab only
   private static Queue<String> songQueue;
   
   public static void main(String[] args) throws Exception
   {
      keyboard = new Scanner(System.in);
      songQueue = readPlayList();
      printSongList();
      
      String prompt = "Add song (A), Play song (P), Delete song (D), Quit (Q):  ";
      String str = "";
      do{      
         System.out.print(prompt);
         str = keyboard.nextLine().toUpperCase();
         processRequest( str );
         System.out.println();
      }while(!str.equals("Q"));
   }
   
   public static Queue<String> readPlayList() throws IOException
   {
      Queue<String> songQueue = new LinkedList<String>();
      Scanner infile = new Scanner (new File("songs.txt")); 
      String s = " "; 
      while(infile.hasNextLine())
      {
         s = infile.nextLine();
         s = s.substring(0, s.indexOf('-') -1); 
         songQueue.add(s);
      } 
      infile.close();
      return songQueue; 
   }
   public static Object e = null;
   public static boolean torf = false;
   
   public static void processRequest(String str)
   {
      String str1 = str;
      if(str1.equals("Q"))
      {
         System.out.println("");
         System.out.println("No more music today!");
      }
      
      else if(str1.equals("A"))
      {
         System.out.print("Song to add? ");
         str1 = keyboard.nextLine();
         torf = songQueue.add(str1);
      }
      
      else if(str1.equals("P"))
      {
         if(songQueue.isEmpty())
            System.out.println("Empty queue!");
         else
         {
            str1 = songQueue.peek();
            System.out.println("Now Playing: " + str1);
            e = songQueue.remove();
         }
      }
      
      else if(str1.equals("D"))
      {
         int length = 0;
         boolean dup = false;
         String str2 = "";
         printSongList();
         System.out.print("Delete which song (exact match)? ");
         str1 = keyboard.nextLine();
         str2 = songQueue.peek();
         if(!songQueue.isEmpty())
            while(length <= songQueue.size())
            {
               e = songQueue.remove();
               if(!e.equals(str1))
                  torf = songQueue.add(e + "");
               else
                  dup = true;
               length++;
            }
         if(dup == false)
            System.out.println("Error:  Song not in list.");
      
      }
      if(!str.equals("Q"))
         printSongList();
   }
   public static void printSongList()
   {
   
      System.out.print("Your music queue: ");
      String print = "[";
      String str2 = songQueue.peek();
      String old = " ";
      for(int x = 0; x < songQueue.size(); x++)
      {
         old = songQueue.remove();
         print += (old + ", ");
         torf = songQueue.add(old);
      }
      if(print.length() >= 2)
         print = print.substring(0, print.length()-2) + "]";
      else 
         print = "[]";
      System.out.println(print);          
   }
   
   public static Queue<String> getQueue()
   {
      return songQueue;
   }
}

/*********************************************

 Your music queue: [Really Love, Uptown Funk, Thinking Out Loud, Alright, Traveller, Alright]
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Really Love
 Your music queue: [Uptown Funk, Thinking Out Loud, Alright, Traveller, Alright]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Uptown Funk
 Your music queue: [Thinking Out Loud, Alright, Traveller, Alright]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  d
 Your music queue: [Thinking Out Loud, Alright, Traveller, Alright]
 Delete which song (exact match)?  Alright
 Your music queue: [Thinking Out Loud, Traveller]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  d
 Your music queue: [Thinking Out Loud, Traveller]
 Delete which song (exact match)?  xxx
 Error:  Song not in list.
 Your music queue: [Thinking Out Loud, Traveller]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  a
 Song to add? Girl Crush
 Your music queue: [Thinking Out Loud, Traveller, Girl Crush]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Thinking Out Loud
 Your music queue: [Traveller, Girl Crush]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Traveller
 Your music queue: [Girl Crush]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Girl Crush
 Your music queue: []
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Empty queue!
 Your music queue: []
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  q
 
 No more music today!

**********************************************/