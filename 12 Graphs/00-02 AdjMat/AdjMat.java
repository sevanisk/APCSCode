// Name:   Sophia Evanisko
// Date:   4/22/19
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 * Graph1 WarshallDriver,
 * and Graph2 FloydDriver
 */

interface AdjacencyMatrix
{
   void addEdge(int source, int target);//done
   void removeEdge(int source, int target);//done
   boolean isEdge(int from, int to);//done
   String toString();//done   
   int edgeCount();//done
   List<Integer> getNeighbors(int source);//done
   //public List<String> getReachables(String from);  //Warshall extension
}

interface Warshall      
{
   boolean isEdge(String from, String to);//done
   Map<String, Integer> getVertices();//done  
   void readNames(String fileName) throws FileNotFoundException;//done
   void readGrid(String fileName) throws FileNotFoundException;
   void displayVertices();
   void allPairsReachability();  // Warshall's Algorithm
}

interface Floyd
{
   int getCost(int from, int to);
   int getCost(String from, String to);
   void allPairsWeighted(); 
}

public class AdjMat implements AdjacencyMatrix, Warshall, Floyd
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name-->index (for Warshall & Floyd)
  
  //constructor
   public AdjMat(int size)
   {
      grid = new int[size][size];
      vertices = new TreeMap<String, Integer>();
   }
   
   //Floyd
   public int getCost(int from, int to)
   {
      int cost = 0;
      if(from < grid.length && to < grid.length)
         cost = grid[from][to];
      return cost;
   }
   
   public int getCost(String from, String to)
   {
      int source = vertices.get(from);
      int target = vertices.get(to);
      if(source > grid.length || target > grid.length)
         return 0;
      return grid[source][target];
   }
   
   public void allPairsWeighted()
   {
      for (int k = 0; k < grid.length; k++)
         for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid.length; j++)
               if(grid[i][k] + grid[k][j] < grid[i][j])
                  grid[i][j] = grid[i][k] + grid[k][j];  
   
   }
   
  //Warshall interface
   public boolean isEdge(String from, String to)
   {
      int source = vertices.get(from);
      int target = vertices.get(to);
      if(grid[source][target] != 9999 && grid[source][target] > 0)
         return true;
      return false;
   }
  
   public Map<String, Integer> getVertices()
   {
      return vertices;
   }
  
   public void readNames(String fileName) throws FileNotFoundException
   {
      Scanner sc = new Scanner(new File("" + fileName));
      int size = sc.nextInt();
      int curr = 0;
      while(curr < size && sc.hasNextLine())
      {
         vertices.put(sc.next(), curr);
         curr++;
      }
   }
   
   public void readGrid(String fileName) throws FileNotFoundException
   {
      Scanner sc = new Scanner(new File("" + fileName));
      int size = sc.nextInt();
      for(int r = 0; r < size; r++)
         for(int c = 0; c < size; c++)
            grid[r][c] = sc.nextInt();
   }
   
   public void displayVertices()
   {
      for(String vertex: vertices.keySet())
         System.out.println(vertices.get(vertex) + "-" + vertex);
      System.out.println("");
   }
   
   public void allPairsReachability()
   {
      for (int k = 0; k < grid.length; k++)
         for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid.length; j++)
               if(isEdge(k, i) && isEdge(i, j))
                  grid[k][j] = 1;            
   }
   
  
  //Adjacency Matrix interface
   public void addEdge(int source, int target)
   {
      if(source < grid.length && target < grid[0].length)
         grid[source][target] = 1;
   }
   
   public void removeEdge(int source, int target)
   {
      if(source < grid.length && target < grid[0].length)
         grid[source][target] = 0;
      else
         System.out.println("That's not an edge");
   }
   
   public boolean isEdge(int from, int to)
   {
      if(from < grid.length && to < grid[0].length)
         if(grid[from][to] != 9999 && grid[from][to] > 0)
            return true;
      return false;
   }
   
   public int edgeCount()
   {
      int edges = 0;
      for(int r = 0; r < grid.length; r++)
         for(int c = 0; c < grid[0].length; c++)
            if(isEdge(r, c))
               edges++;
      return edges;
   }
   
   public String toString()
   {
      String myMatrix = "";
      for(int r = 0; r < grid.length; r++)
      {
         for(int c = 0; c < grid[0].length; c++)
            myMatrix += (grid[r][c] + "  ");  
         myMatrix += "\n";
      }
      return myMatrix;
   }
   
   public List<Integer> getNeighbors(int source)
   {
      List<Integer> neighbors = new ArrayList<Integer>();
      for(int c = 0; c < grid[0].length; c++)
         if(grid[source][c] == 1)
            neighbors.add(c);
      return neighbors;
   }
   
}
