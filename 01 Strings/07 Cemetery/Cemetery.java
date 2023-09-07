// Name: Sophia Evanisko       Date: 09/17/2018
import java.util.Scanner;
import java.io.File;
import java.text.DecimalFormat;
import java.io.FileNotFoundException;

public class Cemetery
{
   public static void main (String [] args)
   {
      File file = new File("cemetery.txt");
      int numEntries = countEntries(file);
      Person[] cemetery = readIntoArray(file, numEntries); 
      int min = locateMinAgePerson(cemetery);
      int max = locateMaxAgePerson(cemetery); 
      //for testing only
      for (int i = 0; i < cemetery.length; i++) 
         System.out.println(cemetery[i]);
      System.out.println("In the St. Mary Magdelene Old Fish Cemetery --> ");
      System.out.println("Name of youngest person: " + cemetery[min].getName());
      System.out.println("Age of youngest person: " + cemetery[min].getAge());    
      System.out.println("Name of oldest person: " + cemetery[max].getName());
      System.out.println("Age of oldest person: " + cemetery[max].getAge());     
   }
   
   /* Counts and returns the number of entries in File f.
      Uses a try-catch block.   
      @param f -- the file object
   */
   public static int countEntries(File f)
   {
      int count = 0;
      Scanner line = null;
      try
      {
         line = new Scanner(f);
      }
      
      catch(FileNotFoundException e)
      {
         System.out.println("not a valid file");
         System.exit(0);
      }
      
      while(line.hasNextLine() == true)
      {
         line.nextLine();
         count++;
      }
      return count;
   }


   /* Reads the data.
      Fills the array with Person objects.
      Uses a try-catch block.   
      @param f -- the file object 
      @param num -- the number of lines in the File f  
   */
   public static Person[] readIntoArray (File f, int num)
   {
      Person[] array = new Person[num];
      Scanner sc = null;
      try
      {
         sc = new Scanner (f);
      }
      catch(FileNotFoundException e)
      {
         System.out.println("not a valid file");
      }
      String s = "";
      for(int y = 0; y < array.length; y++)
      {
         s = sc.nextLine();
         array[y] = (makeObjects(s));
      }
      return array;
      
   }
   
   
   /* A helper method that instantiates one Person object.
      @param entry -- one line of the input file.
   */
   private static Person makeObjects(String entry)
   {
      String date = entry.substring(25, 37).trim();
      String age = (entry.substring(37, 42).trim());
      String name = entry.substring(0, 25).trim();
      return new Person(name, date, age);
   }  
   
   /* Finds and returns the location (the index) of the Person
      who is the youngest.
      @param arr -- an array of Person objects.
   */
   public static int locateMinAgePerson(Person[] arr)
   {
      int youngPos = 0;
      for(int x = 0; x < arr.length; x++)
         if((arr[x].getAge()) < (arr[youngPos].getAge()))
            youngPos = x;
      return youngPos;
   }   
   
   /* Finds and returns the location (the index) of the Person
      who is the oldest.
      @param arr -- an array of Person objects.
   */
   public static int locateMaxAgePerson(Person[] arr)
   {
      int oldPos = 0;
      for(int x = 0; x < arr.length; x++)
         if((arr[x].getAge()) > (arr[oldPos].getAge()))
            oldPos = x;
      return oldPos;
   
   }        
} 

class Person
{
   /* private fields  */
   private DecimalFormat df = new DecimalFormat("0.0000");
   private double myAge;
   private String myName, myDate;
      
   /* a three-arg constructor  */
   public Person(String name, String date, String age)
   {
      this.myName = name;
      this.myDate = date;
      this.myAge = Double.parseDouble(df.format(calculateAge(age)));
   }
   
   /* any necessary accessor methods */
   public String getName()
   {
      return this.myName;
   }
   
   public String getDate()
   {
      return this.myDate;
   }
   
   public double getAge()
   {
      return Double.parseDouble(myAge + "");
   }
   
   public double calculateAge(String a)
   {
      if(a.indexOf('w') != -1)
         return (Double.parseDouble(a.substring(0, a.indexOf('w'))) * 7 / 365);
      
      else if(a.indexOf('d') != -1)
         return (Double.parseDouble(a.substring(0, a.indexOf('d'))) /365);
      
      else
         return (Double.parseDouble(a));
   }
   
   public String toString()
   {
      return "" + myName + " " + myDate + " " + myAge;
   }
}