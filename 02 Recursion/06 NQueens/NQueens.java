//Name:Sophia Evanisko   
//Date: 10/05/2018
// License Information:
//   This class is free software; you can redistribute it and/or modify
//   it under the terms of the GNU General Public License as published by
//   the Free Software Foundation.
//   This class is distributed in the hope that it will be useful,
//   but WITHOUT ANY WARRANTY; without even the implied warranty of
//   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//   GNU General Public License for more details.

import edu.kzoo.grid.BoundedGrid;
import edu.kzoo.grid.Grid;
import edu.kzoo.grid.Location;
import edu.kzoo.grid.display.GridDisplay;

/**
 *  Environment-Based Applications:<br>
 *
 *    The NQueens class implements the N Queens problem.
 *
 *  @author Your Name (based on a template provided by Alyce Brady)
 *  @version 1 September 2002
 **/

public class NQueens
{
   // Instance Variables: Encapsulated data for EACH NQueens problem
   private Grid board;
   private GridDisplay display;

 // constructor

   /** Constructs an object that solves the N Queens Problem.
    *    @param n the number of queens to be placed on an
    *              <code>n</code> x <code>n</code> board
    *    @param d an object that knows how to display an 
    *              <code>n</code> x <code>n</code> board and
    *              the queens on it
    **/
   public NQueens(int n, GridDisplay d)
   {
      board = new BoundedGrid(n, n);
      display = d;
      display.setGrid(board);
      display.showGrid();
   }

 // methods

   /** Returns the number of queens to be placed on the board. **/
   public int numQueens()
   {
      return board.numRows();
   }

   /** Solves (or attempts to solve) the N Queens Problem. **/
   public boolean solve()
   {
      if( placeQueen(0) ) 
      {
         display.showGrid();         
         return true;    
      }    
      else        
         return false;  
   }

   /** Attempts to place the <code>q</code>th queen on the board.
    *  (Precondition: <code>0 <= q < numQueens()</code>)
    *    @param q index of next queen to place
    **/
   Location badl = new Location(8, 8);
   Location lastl;
   private boolean placeQueen(int q)
   {
      if(q == numQueens())
      {
         display.showGrid();
         return true;
      }
      if(q < numQueens())
      {
         for(int x = 0; x < numQueens(); x++)
         {
            Location lo = new Location(q, x);
            if(locationIsOK(lo))
            {
               addQueen(lo);
               display.showGrid();
               lastl = lo;
               return placeQueen(q+1);
            }
            
            else if(lo.col() == numQueens()-1 && !locationIsOK(lo))
            {
               if(board.objectAt(lo) != null)
                  removeQueen(lo);
               if(lastl.col() == numQueens()-1)
               {
                  Location qu = new Location(q-1, numQueens()-1);
                  removeQueen(qu);
                  display.showGrid();
                  return placeQueen(q-2);
               }
               return placeQueen(q-1);
            }
            
            else if(board.objectAt(lo) != null)
            {
               removeQueen(lo);
               display.showGrid();
            }   
            
            // if(locationIsOK(lo) == true && board.objectAt(lo) == null)
         //             {
         //                addQueen(lo);
         //                display.showGrid();
         //                return placeQueen(q+1);
         //             }
         //             
         //             else if((x == numQueens() - 1) && (!locationIsOK(lo))) 
         //             {
         //                removeQueen(badl);
         //                display.showGrid();
         //                return placeQueen(q-1);
         //             }
         //             
         //             else if(board.objectAt(lo) != null)
         //             {
         //                removeQueen(lo);
         //                display.showGrid();
         //                badl = lo;
         //             }
         }
      // Queen q is placed in row q.  The only question is
      // which column she will be in.  Try them in turn.
      // Whenever we find a column that could work, put her
      // there and see if we can place the rest of the queens.
      }
      return false;
   }

   /** Determines whether a queen can be placed at the specified
    *  location.
    *    @param loc  the location to test
    **/
   private boolean locationIsOK(Location loc)
   {
      int lc = loc.col();
      int lr = loc.row();
      
      //nothing placed in the same spot
      if(board.objectAt(loc) != null)
         return false;
         
      //nothing in the same row
      for(int z = 0; z < numQueens(); z++)
      {
         Location lp = new Location(lr, z);
         if(!(board.objectAt(lp) == null))
            return false;
      }
      
      //nothing in same column?
      for(int x = 0; x < numQueens(); x++)
      {
         Location l = new Location(x, lc);
         if(!(board.objectAt(l) == null))
            return false;
      }
      
     //diagonals?      
      for(int a = 0; lr - a >= 0 && lc - a >= 0; a++)
      {
         Location ldn = new Location(lr - a, lc - a);
         if(!(board.objectAt(ldn) == null))
            return false;
      }
      
      for(int a = 0; lr - a >= 0 && lc + a >= 0; a++)
      {
         Location ldnr = new Location(lr - a, lc + a);
         if(!(board.objectAt(ldnr) == null))
            return false;
      }
      return true;
   }

   /** Adds a queen to the specified location.
    *    @param loc  the location where the queen should be placed
    **/
   private void addQueen(Location loc)
   {
      new Queen(board, loc);      // queens add themselves to the board
   }

   /** Removes a queen from the specified location.
    *    @param loc  the location where the queen should be removed
    **/
   private void removeQueen(Location loc)
   {
      board.remove(loc);
      display.showGrid();
   }

}