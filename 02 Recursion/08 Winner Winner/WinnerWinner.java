// Name: Sophia Evanisko
// Date: 10/18/2018
public class WinnerWinner
{
   public static void main(String[] args)
   {
      Board b = null;
      b = new Board(3,4,"W-S-------X-"); 
      b.display();
      System.out.println("Shortest path is " + b.win());  //2
         
      b = new Board(4,3,"S-W-----X-W-"); 
      b.display();
      System.out.println("Shortest path is " + b.win());  //4
         
      b = new Board(3,4,"X-WS--W-W---"); 
      b.display();
      System.out.println("Shortest path is " + b.win());  //7
         
      b = new Board(3,5,"W--WW-X----SWWW"); 
      b.display();
      System.out.println("Shortest path is " + b.win());  //1
      
      b = new Board(3,3,"-SW-W-W-X");     //no path exists
      b.display();
      System.out.println("Shortest path is " + b.win());  //-1
      
      b = new Board(5,7,"-W------W-W-WX--S----W----W-W--W---");     //Example Board 1
      b.display();
      System.out.println("Shortest path is " + b.win());  //5
      
      b = new Board(4,4,"-WX--W-W-WW-S---");     //Example Board -1
      b.display();
      System.out.println("Shortest path is " + b.win());  //5
   
      //what other test cases should you test?
      
   }
}

class Board
{
   private char[][] board;  
   private int maxPath;

   public Board(int rows, int cols, String line)  
   {
      int z = 0;
      board = new char [rows][cols];
      for(int x = 0; x < rows; x++)
         for(int y = 0; y < cols; y++)
         {
            board[x][y] = line.charAt(z);
            z++;
         }
      maxPath = rows*cols;
   }

	/**	returns the length of the longest possible path in the Board   */
   public int getMaxPath()		
   {  
      return maxPath; 
   }	
   
   public void display()
   {
      if(board==null) 
         return;
      System.out.println();
      for(int a = 0; a<board.length; a++)
      {
         for(int b = 0; b<board[0].length; b++)
         {
            System.out.print(board[a][b]);
         }
         System.out.println();
      }
   }

   /**	
    *  calculates and returns the shortest path from S to X, if it exists   
    *  @param r is the row of "S"
    *  @param c is the column of "S"
    */
   public boolean finish = false;
   int up = 0, down = 0, left = 0, right = 0;
   public int check(int r, int c)
   {
     // display();
      int min = 30;
      if((r < 0 || c < 0 || r > board.length-1 || c > board[0].length -1))
         return maxPath;
      
      if(board[r][c] == 'X')
      {
         finish = true;
         return 0;
      }
         
      if(board[r][c] == 'S' || board[r][c] == '-')
      {
         if(finish == false)
         {
            board[r][c] = 'o';
            up = 1 + check(r-1, c);
            down = 1 + check (r+1, c);
            left = 1 + check(r, c-1);
            right = 1 + check(r, c+1);
            board[r][c] = '-'; 
              
         } 
         if (up < min)
            min = up;
      
         if(down < min)
            min = down;
      
         if(right < min)
            min = right;
      
         if(left < min)
            min = left; 
         
      } 
      return min;
     // return -1;
   }

   public int check2(int r, int c)
   {
      int x = 0;
      if((r < 0 || c < 0 || r > board.length-1 || c > board[0].length -1))
         x = 0;
      
      if(board[r][c] == 'X')
      {
         finish = true;
         return x;
      }
      if(board[r][c] == 'S' || board[r][c] == '-')
      {
         board[r][c] = 'o';
         x = 1;
      
         if(finish == false)
            if(r < board.length-1)
               x+=(check(r+1, c));
         
         if(finish == false)
            if(c < board[0].length-1)
               x+= (check(r, c+1));
            
         if(finish == false)
            if(r > 0)
               x+=(check(r-1, c));
         
         if(finish == false)
            if(c>0)
               x+=(check(r, c-1));
         
         board[r][c] = '*';
      }
      if(finish == false)
         return 0;
      return x;
   
   }   
   /**	
    *  precondition:  S and X exist in board
    *  postcondition:  returns either the length of the path
    *                  from S to X, or -1, if no path exists.    
    */
   public int win()
   {
      int z = 0;
      int a = 0;
      for(int x = 0; x < board.length; x++)
         for(int y = 0; y < board[0].length; y++)
            if(board[x][y] == 'S')
            {

               z = (check(x, y));
               a = check2(x, y);
            }
      if(z == maxPath + 1)
         return -1;
      else if(a == 0)
         return -1;
      else
         return z;
   }
}





/************************ output ************
 W-S-
 ----
 --X-
 Shortest path is 2
 
 S-W
 ---
 --X
 -W-
 Shortest path is 4
 
 X-WS
 --W-
 W---
 Shortest path is 7
 
 W--WW
 -X---
 -SWWW
 Shortest path is 1
 
 -SW
 -W-
 W-X
 Shortest path is -1
 
 -W-----
 -W-W-WX
 --S----
 W----W-
 W--W---
 Shortest path is 5
 
 -WX-
 -W-W
 -WW-
 S---
 Shortest path is -1 
 ***************************************/