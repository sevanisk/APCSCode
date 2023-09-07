// Name:Sophia Evanisko
// Date:10/01/2018

import java.util.*;
import java.io.*;

public class AreaFill
{
   private static char[][] grid = null;
   private static String filename = null;

   public static void main(String[] args) 
   {
      Scanner sc = new Scanner(System.in);
      while(true)  // what does this do?
      {
         System.out.print("Fill the Area of (-1 to exit): ");
         filename = sc.next();
         if(filename.equals("-1"))
         {
            sc.close();
            System.out.println("Good-bye");
            //System.exit(0);   
         }
         grid = read(filename);
         String theGrid = display(grid);
         System.out.println( theGrid );
         System.out.print( "1-Fill or 2-Fill-and-Count: ");
         int choice = sc.nextInt();
         switch(choice)
         {
            case 1:
               {
                  System.out.print("\nEnter ROW COL to fill from: ");
                  int row = sc.nextInt();
                  int col = sc.nextInt(); 
                  fill(grid, row, col, grid[row][col]);
                  System.out.println( display(grid) );
                  break;
               }
            case 2:
               {
                  System.out.print("\nEnter ROW COL to fill from: ");
                  int row = sc.nextInt();
                  int col = sc.nextInt();
                  int count = fillAndCount(grid, row, col, grid[row][col]);
                  System.out.println( display(grid) );
                  System.out.println("count = " + count);
                  System.out.println();
                  break;
               }
            default:
               System.out.print("\nTry again! ");
         }
      }
   }
   
   /**
    * Reads the contents of the file into a matrix.
    * Uses try-catch.
    * @param filename The string representing the filename.
    * @returns A 2D array of chars.
    */
   public static char[][] read(String filename)
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(filename));
      }
      catch (Exception e)
      {
         System.out.println("File not found");
         return null;
      }
   
      int y = 0;
      int x = 0;
      String s = "";
      String b = infile.nextLine();
      x = Integer.parseInt(b.substring(0, b.indexOf(' ')));
      y = Integer.parseInt(b.substring(b.indexOf(' ') + 1));
      
      char[][] matrix = new char[x][y];
      
      for(int z = 0; z < matrix.length; z++)
      {
         s = infile.nextLine();
         for(int a = 0; a < matrix[0].length; a++)
            matrix[z][a] = s.charAt(a);
      }
      return matrix; 
   }
   
   /**
    * @param g A 2-D array of chars.
    * @returns A string representing the 2D array.
    */
   public static String display(char[][] g)
   {
      String s = "";
      for(int x = 0; x < g.length; x++)
      {
         for(int y = 0; y < g[0].length; y++)
            s += g[x][y];
         System.out.println("" + s);
         s = "";
      }
      return s;
   }
   
   /**
    * Fills part of the matrix with 
    a different character.
    * @param g A 2D char array.
    * @param r An int representing the row of the cell to be filled.
    * @param c An int representing the column of the cell to be filled.
    * @param ch A char representing the replacement character.
    */
   public static void fill(char[][] g, int r, int c, char ch)
   {
      if(g[r][c] == ch)
      {
         g[r][c] = '*';
         if(r < g.length - 1)
            fill(g, r+1, c, ch);
         if(r > 0)
            fill(g, r-1, c, ch);
         if(c < g[0].length -1)
            fill(g, r, c+1, ch);
         if(c > 0)
            fill(g, r, c-1, ch);
      }
   }
   
   /**
    * Fills part of the matrix with a different character.
    * Counts as you go.  Does not use a static variable.
    * @param g A 2D char array.
    * @param r An int representing the row of the cell to be filled.
    * @param c An int representing the column of the cell to be filled.
    * @param ch A char representing the replacement character.
    * @return An int representing the number of characters that were replaced.
    */
   public static int fillAndCount(char[][] g, int r, int c, char ch)
   {
      int x = 0;
      if(g[r][c] == ch)
      {
         x = 1;
         g[r][c] = '*';
         if(r < g.length-1)
            x+= fillAndCount(g, r+1, c, ch);
         if(r > 0)
            x+= fillAndCount(g, r-1, c, ch);
         if(c < g[0].length -1)
            x += fillAndCount(g, r, c+1, ch);
         if(c > 0)
            x+= fillAndCount(g, r, c-1, ch);
      }
      return x;
   }
}