// Name: Sophia Evanisko
// Date: 3/7/19

import java.util.*;
import java.io.*;

public class SetsOfLetters
{
   public static void main(String[] args) throws FileNotFoundException
   {
      String fileName = "declarationLast.txt";
      fillTheSets(fileName);
   }
   
 
   public static void fillTheSets(String fn) throws FileNotFoundException
   {
      Scanner infile = new Scanner(new File(fn));
      /*  enter your code here  */
      Set<Character> lcSet = new HashSet<Character>();
      Set<Character> ucSet = new HashSet<Character>();
      Set<Character> otherSet = new HashSet<Character>();
      
      Set<Character> commonLC = new HashSet<Character>();
      Set<Character> commonUC = new HashSet<Character>();
      Set<Character> commonOTH = new HashSet<Character>();
      int first = 0;
      
      while(infile.hasNextLine())
      {
         String line = infile.nextLine();
      
      //make arrayList of chars from the line
         ArrayList<Character> chars = new ArrayList<Character>();
         for(int x = 0; x < line.length(); x++)
         {
            char atX = line.charAt(x);
            if(atX != ' ')
               chars.add(atX);
         }
      
      //analyze each char
         System.out.println(line);
         for(Character myChar: chars)
         {
            if(Character.isLowerCase(myChar))
               lcSet.add(myChar);
            else if(Character.isUpperCase(myChar))
               ucSet.add(myChar);
            else
               otherSet.add(myChar);
         }
      
      //common letters 
         if(first == 0)
         {
            for(Character com: lcSet)
               commonLC.add(com);
         
            for(Character com2: ucSet)
               commonUC.add(com2);
         
            for(Character com3: otherSet)
               commonOTH.add(com3);
         }
         
         else
         {
            Iterator c = commonLC.iterator();
            while(c.hasNext())
            {
               Object a = c.next();
               if(!lcSet.contains(a))
                  c.remove();
            }
         
            Iterator c2 = commonUC.iterator();
            while(c2.hasNext())
            {
               Object a = c2.next();
               if(!ucSet.contains(a))
                  c2.remove();
            }
            
            Iterator c3 = commonOTH.iterator();
            while(c3.hasNext())
            {
               Object a = c3.next();
               if(!otherSet.contains(a))
                  c3.remove();
            }
         }
         
       
       //print sets
         System.out.println("Lower Case: " + lcSet);
         System.out.println("Upper Case: " + ucSet);
         System.out.println("Other: " + otherSet);
         System.out.println("");
         first++;
         
      //reset each set
         Iterator i = lcSet.iterator();
         while(i.hasNext())
         {
            i.next();
            i.remove();
         }
         
         Iterator i2 = ucSet.iterator();
         while(i2.hasNext())
         {
            i2.next();
            i2.remove();
         }
         
         Iterator i3 = otherSet.iterator();
         while(i3.hasNext())
         {
            i3.next();
            i3.remove();
         }
      }
      
      System.out.println("Common Lower Case: " + commonLC);
      System.out.println("Common Upper Case: " + commonUC);
      System.out.println("Common Other: " + commonOTH);
      
   }
}

/***********************************
  ----jGRASP exec: java SetsOfLetters_teacher
 
 We, therefore, the Representatives of the united States of 
 Lower Case: [a, d, e, f, h, i, n, o, p, r, s, t, u, v]
 Upper Case: [R, S, W]
 Other: [ , ,]
 
 America, in General Congress, Assembled, appealing to the 
 Lower Case: [a, b, c, d, e, g, h, i, l, m, n, o, p, r, s, t]
 Upper Case: [A, C, G]
 Other: [ , ,]
 
 Supreme Judge of the world for the rectitude of our intentions,
 Lower Case: [c, d, e, f, g, h, i, l, m, n, o, p, r, s, t, u, w]
 Upper Case: [J, S]
 Other: [ , ,]
 
 do, in the Name, and by the Authority of the good People of 
 Lower Case: [a, b, d, e, f, g, h, i, l, m, n, o, p, r, t, u, y]
 Upper Case: [A, N, P]
 Other: [ , ,]
 
 these Colonies, solemnly publish and declare, That these United 
 Lower Case: [a, b, c, d, e, h, i, l, m, n, o, p, r, s, t, u, y]
 Upper Case: [C, T, U]
 Other: [ , ,]
 
 Colonies are, and of Right ought to be Free and Independent 
 Lower Case: [a, b, d, e, f, g, h, i, l, n, o, p, r, s, t, u]
 Upper Case: [C, F, I, R]
 Other: [ , ,]
 
 States; that they are Absolved from all Allegiance to the 
 Lower Case: [a, b, c, d, e, f, g, h, i, l, m, n, o, r, s, t, v, y]
 Upper Case: [A, S]
 Other: [ , ;]
 
 British Crown, and that all political connection between them 
 Lower Case: [a, b, c, d, e, h, i, l, m, n, o, p, r, s, t, w]
 Upper Case: [B, C]
 Other: [ , ,]
 
 and the State of Great Britain, is and ought to be totally 
 Lower Case: [a, b, d, e, f, g, h, i, l, n, o, r, s, t, u, y]
 Upper Case: [B, G, S]
 Other: [ , ,]
 
 dissolved; and that as Free and Independent States, they have 
 Lower Case: [a, d, e, h, i, l, n, o, p, r, s, t, v, y]
 Upper Case: [F, I, S]
 Other: [ , ,, ;]
 
 full Power to levy War, conclude Peace, contract Alliances, 
 Lower Case: [a, c, d, e, f, i, l, n, o, r, s, t, u, v, w, y]
 Upper Case: [A, P, W]
 Other: [ , ,]
 
 establish Commerce, and to do all other Acts and Things which 
 Lower Case: [a, b, c, d, e, g, h, i, l, m, n, o, r, s, t, w]
 Upper Case: [A, C, T]
 Other: [ , ,]
 
 Independent States may of right do. And for the support of this 
 Lower Case: [a, d, e, f, g, h, i, m, n, o, p, r, s, t, u, y]
 Upper Case: [A, I, S]
 Other: [ , .]
 
 Declaration, with a firm reliance on the protection of divine 
 Lower Case: [a, c, d, e, f, h, i, l, m, n, o, p, r, t, v, w]
 Upper Case: [D]
 Other: [ , ,]
 
 Providence, we mutually pledge to each other our Lives, our 
 Lower Case: [a, c, d, e, g, h, i, l, m, n, o, p, r, s, t, u, v, w, y]
 Upper Case: [L, P]
 Other: [ , ,]
 
 Fortunes and our sacred Honor.
 Lower Case: [a, c, d, e, n, o, r, s, t, u]
 Upper Case: [F, H]
 Other: [ , .]
 
 Common Lower Case: [d, e, n, o, r, t]
 Common Upper Case: []
 Common Other: [ ]
  ----jGRASP: operation complete.
  ************************************/