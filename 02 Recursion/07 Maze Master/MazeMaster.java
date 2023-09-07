//name: Sophia Evanisko    date: 10/11/2018
import java.util.*;
import java.io.*;
public class MazeMaster
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the maze's filename (no .txt): ");
      Maze m = new Maze(sc.next());
      // Maze m = new Maze();     //extension:  generate a random maze
      m.display();      //display maze
      System.out.println("Options: ");
      System.out.println("1: Mark all paths.");
      System.out.println("2: Mark all paths, and display the count of all STEPs.");
      System.out.println("3: Mark only the correct path.");
      System.out.println("4: Mark only the correct path. If no path exists, say so.");
      System.out.println("5: Mark only the correct path and display the count of STEPs.\n\tIf no path exists, say so.");
      System.out.print("Please make a selection: ");
      m.solve(sc.nextInt());
      m.display();      //display solved maze
   
   } 
}


class Maze
{
   //Constants
   private final char WALL = 'W';
   private final char DOT = '.';
   private final char START = 'S';
   private final char EXIT = 'E';
   private final char STEP = '*';
   private final char O = 'o';
   private final char B = 'b';
   //fields
   private char[][] maze;
   private int startRow, startCol;
  
  //constructors
   public Maze()  //extension:  generate a random maze
   {
   }
   public Maze(char[][] m)  //copy constructor
   {
      maze = m;
      for(int r = 0; r < maze.length; r++)
      {
         for(int c = 0; c < maze[0].length; c++)
         { 
            if(maze[r][c] == START)      //identify start
            {
               startRow = r;
               startCol = c;
            }
         }
      }
   } 
   public Maze(String filename)    
   {
      Scanner infile = null;
   
      try
      {
         infile = new Scanner(new File(filename + ".txt"));
      }
      catch (Exception e)
      {
         System.out.println("That's not a file! Try again!");
      }
   
      int rows = 0;
      int col = 0;
      rows = infile.nextInt();
      col = infile.nextInt();
      
      maze = new char[rows][col];
      for(int y = 0; y < rows; y++)
      {
         String s1 = infile.next();
         for(int x = 0; x < col; x++)
         {
            maze[y][x] = s1.charAt(x);
            if(maze[y][x] == START)
            {
               startRow = y;
               startCol = x;
            }
         }
      }
      
   }
   
   public char[][] getMaze()
   {
      return maze;
   }
   
   public void display()
   {
      if(maze==null) 
         return;
      for(int a = 0; a<maze.length; a++)
      {
         for(int b = 0; b<maze[0].length; b++)
         {
            System.out.print(maze[a][b]);
         }
         System.out.println("");
      }
      System.out.println("");
   }
   
   public void solve(int n)
   {
      switch(n)
      {
         case 1:
            {
               markAllPaths(startRow, startCol);
               break;
            }
         case 2:
            {
               int count = markAllPathsAndCountStars(startRow, startCol);
               System.out.println("Number of steps = " + count);
               break;
            }
         case 3:
            {
               markTheCorrectPath(startRow, startCol);
               break;
            }
         case 4:         //use mazeNoPath.txt 
            {
               if( !markTheCorrectPath(startRow, startCol) && !check2(startRow, startCol))
                  System.out.println("No path exists."); 
               break;
            }
         case 5:
            {
               if( !markCorrectPathAndCountSteps(startRow, startCol, 0) )
                  System.out.println("No path exists."); 
               break;
            }
         default:
            System.out.println("File not found");   
      }
   }
   public void markAllPaths(int r, int c)
    /*  1  almost like AreaFill*/
   {
      if(maze[r][c] != WALL)
      {
         if(!(maze[r][c] == START))
            maze[r][c] = STEP;
         
         if(r < maze.length -1)
            if(maze[r+1][c] == DOT)
               markAllPaths(r+1, c);
            
         if(r > 0)      
            if(maze[r-1][c] == DOT)
               markAllPaths(r-1,c);
            
         if(c > 0)      
            if(maze[r][c-1] == DOT)
               markAllPaths(r, c-1);
            
         if(c < maze[0].length -1)
            if(maze[r][c+1] == DOT)
               markAllPaths(r, c+1);
      }
   }
   
    /*  2  like AreaFill's counting without a static variable */  
   public int markAllPathsAndCountStars(int r, int c)
   {
      int total = 0;
      if(maze[r][c] == START)
      {
         if(r < maze.length-1)
            total +=markAllPathsAndCountStars(r+1, c);
         if(r > 0)
            total +=markAllPathsAndCountStars(r-1, c);
         if(c > 0)
            total +=markAllPathsAndCountStars(r, c-1);
         if(c < maze[0].length -1)
            total +=markAllPathsAndCountStars(r, c+1);
      }
      
      if(maze[r][c] == DOT)
      {
         total = 1;
         maze[r][c] = STEP;
            
         if(r < maze.length -1)
            total += markAllPathsAndCountStars(r+1, c);
         
         if(c > 0)
            total += markAllPathsAndCountStars(r, c-1);
         
         if(r > 0)
            total += markAllPathsAndCountStars(r-1, c);
         
         if(c < maze[0].length -1)
            total += markAllPathsAndCountStars(r, c+1);
      }
      return total;
   }

   public void star(int r, int c)
   {
      if(okLoc(r, c))
      {
         if(maze[r+1][c] == STEP || maze[r-1][c] == STEP || maze[r][c-1] == STEP || maze[r][c+1] == STEP || maze[r][c] == STEP)
         {
            if(maze[r][c] != START)
               maze[r][c] = STEP;
            if(maze[r+1][c] == O)
               star(r+1, c);
            if(maze[r-1][c] == O)
               star(r-1, c);
            if(maze[r][c+1] == O)
               star(r, c+1);
            if(maze[r][c-1] == O)
               star(r, c-1);
            //display1();
         }
      }
   }
   
   boolean star = false;
   /*  3   recur until you find E, then mark the True path */
   public boolean markTheCorrectPath(int r, int c)
   {
      //display1();
      boolean up , down, left, right;
      while(star != true)
      {
         if(okLoc(r, c))
         {
            if(maze[r][c] == O)
            {
               if(maze[r+1][c] != DOT && maze[r-1][c] != DOT && maze[r][c-1] != DOT && maze[r][c+1] != DOT)
                  return false;
               return true;
            }
            
            else 
            {
               maze[r][c] = O;
            }
         } 
      
      //base case
         if(okLoc(r, c))
         {
            if(maze[r][c] == EXIT || maze[r+1][c] == EXIT || maze[r-1][c] == EXIT || maze[r][c+1] == EXIT || maze[r][c-1] == EXIT)
            {
               maze[r][c] = O;
               return true;
            }
         }
         else if(c > 0)
         {
            if(maze[r][c-1] == EXIT)
            {
               maze[r][c] = O;
               return true;
            }
         }
         
         else if(r > 0)
         {
            if(maze[r-1][c] == EXIT)
            {
               maze[r][c] = O;
               return true;
            }
         }
         
         if(r< maze.length-1)
            down = (maze[r+1][c] == DOT);
         else
            down = false;
         
         if(r > 0)
            up = (maze[r-1][c] == DOT);
         else
            up = false;
         
         if(c < maze[0].length -1)
            right = (maze[r][c+1] == DOT);
         else
            right = false;
         
         if(c > 0)
            left = (maze[r][c-1] == DOT);
         else
            left = false;
      
         if(star != true)
            if(right)
            {
               if(check(r, c+1) == true)
                  if(markTheCorrectPath(r, c+1) == false && star == false)
                     maze[r][c+1] = DOT;
                  else
                  {
                     maze[r][c+1] = STEP;
                     star(r, c);
                     star = true;
                  }
            } 
      
         if(star != true)
            if(down)
            {
               if(check(r+1, c) == true)
                  if(markTheCorrectPath(r+1, c) == false && star == false)
                     maze[r+1][c] = DOT;
                  else
                  {
                     maze[r+1][c] = STEP;
                     star(r, c);
                     star = true;
                  }
            }
      
         if(star != true)     
            if(up)
            {
               if(check(r-1, c) == true)
               {
                  if(markTheCorrectPath(r-1,c) == false && star == false)
                     maze[r-1][c] = DOT;
                  else
                  {
                     maze[r-1][c] = STEP;
                     star(r, c);
                     star = true;
                  }
               }
            }
      
         if(star != true)
            if(left)
            {
               if(check(r, c-1) == true)
                  if(markTheCorrectPath(r, c-1) == false && star == false)
                     maze[r][c-1] = DOT;
                  else
                  {
                     maze[r][c-1] = STEP;
                     star(r, c);
                     star = true;
                  }
            }
         return false;
      }
      
      return true;
   }
   public void display1()
   {
      for(int x = 0; x< maze.length; x++)
      {
         System.out.println("");
         for(int y = 0; y < maze[0].length; y++)
         {
            System.out.print(maze[x][y] + "");
         }
      }
      System.out.println("");
   }
   
   public boolean okLoc(int r, int c)
   {
      if(r > 0 && c>0)
         if(r < maze.length-1 && c < maze[0].length-1)
            if(maze[r][c] != WALL)
               return true;
      return false;
   }
   public boolean check(int r, int c)
   {
      if(maze[r][c] == O)
         return false;
      return true;
   }
   
   public boolean check2(int r, int c)
   {
      boolean t = false;
      if(r < maze.length-1);
      if(maze[r+1][c] == STEP)
         t = true;
         
      if(r > 0)
         if(maze[r-1][c] == STEP)
            t = true;
            
      if(c < maze[0].length-1)
         if(maze[r][c+1] == STEP)
            t = true;
         
      if(c > 0)
         if(maze[r][c-1] == STEP)
            t = true;
      return t;
   }
   /*  4   Mark only the correct path. If no path exists, say so.
           Hint:  the method above returns the boolean that you need.  */

   /*  5  Mark only the correct path and display the count of STEPs.
          If no path exists, say so. */
   public boolean markCorrectPathAndCountSteps(int r, int c, int count)
   {
      count = 0;
      boolean t = markTheCorrectPath(r, c);
      if(r < maze.length-1);
      if(maze[r+1][c] == STEP)
         t = true;
         
      if(r > 0)
         if(maze[r-1][c] == STEP)
            t = true;
            
      if(c < maze[0].length-1)
         if(maze[r][c+1] == STEP)
            t = true;
         
      if(c > 0)
         if(maze[r][c-1] == STEP)
            t = true;
   
      for(int x = 0; x < maze.length; x++)
         for(int y = 0; y < maze[0].length; y++)
            if(maze[x][y] == STEP)
               count++;
               
      if(t == true)
         System.out.println("Number of steps = " + (count + 1));
      return t;
   }
}
