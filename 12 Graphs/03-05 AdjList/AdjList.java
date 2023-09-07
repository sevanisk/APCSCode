// Name: Sophia Evanisko  
// Date:4/27/19
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 * Graphs4: DFS-BFS
 * and Graphs5: EdgeListCities
 */

/* Graphs 3: EdgeList 
 */
interface VertexInterface
{
   String toString(); // Don't use commas in the list.  Example: "C [C D]"
   String getName();
   ArrayList<Vertex> getAdjacencies();
   void addEdge(Vertex v);
} 

class Vertex implements VertexInterface 
{
   private final String name;
   private ArrayList<Vertex> adjacencies;
  
   public Vertex(String v)
   {
      this.name = v;
      adjacencies = new ArrayList<Vertex>();
   }

   public ArrayList<Vertex> getAdjacencies()
   {
      return adjacencies;
   }

   public String getName()
   {
      return name;
   }

   public boolean isEdge(Vertex v)
   {
      if(adjacencies.contains(v))
         return true;
      return false;
   }
   
   public void addEdge(Vertex v)
   {
      adjacencies.add(v);
   }
   
   public Vertex removeEdge(Vertex v)
   {
      if(adjacencies.contains(v))
         adjacencies.remove(v);
      return v;
   }
  
   public String toString()
   {
      String s = "";
      s += (name + " [");
      for(Vertex v : adjacencies)
         s += v.getName() + " ";
      s = s.substring(0, s.length()-1);
      if(s.length() > 3)
         s += "]";
      return s;
   }  
} 

interface AdjListInterface 
{ 
   List<Vertex> getVertices();
   Vertex getVertex(int i);
   Vertex getVertex(String vertexName);
   Map<String, Integer> getVertexMap();
   void addVertex(String v);
   void addEdge(String source, String target);
   String toString();  //returns all vertices with their edges (omit commas)
}

  
/* Graphs 4: DFS and BFS 
 */
interface DFS_BFS
{
   List<Vertex> depthFirstSearch(String name);
   List<Vertex> depthFirstSearch(Vertex v);
   List<Vertex> breadthFirstSearch(String name);
   List<Vertex> breadthFirstSearch(Vertex v);
   //List<Vertex> depthFirstRecur(String name);
   //List<Vertex> depthFirstRecur(Vertex v);
   //void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/* Graphs 5: Edgelist with Cities 
 */
interface EdgeListWithCities
{
   void graphFromEdgeListData(String fileName) throws FileNotFoundException;
   int edgeCount();
   boolean isReachable(String source, String target);
   boolean isFullyReachable();
}


public class AdjList implements AdjListInterface, DFS_BFS, EdgeListWithCities
{
   private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
   private Map<String, Integer> nameToIndex = new TreeMap<String, Integer>();
   //Graphs 5
   public void graphFromEdgeListData(String fileName) throws FileNotFoundException
   {
      File f = new File(fileName);
      Scanner in = new Scanner(f);
      while(in.hasNext())
         addEdge(in.next(), in.next());
   }
   
   public int edgeCount()
   {
      int adj = 0;
      for(int x = 0; x < vertices.size(); x++)
         adj += vertices.get(x).getAdjacencies().size();
      return adj;
   }
   
   public boolean isReachable(String source, String target)
   {
      List<Vertex> myReach = depthFirstSearch(source);
      if(myReach.contains(getVertex(target)))
         return true;
      return false;
   }
   
   public boolean isFullyReachable()
   {
      for(int x = 0; x < vertices.size(); x++)
         if(depthFirstSearch(vertices.get(x).getName()).size() != vertices.size())
            return false;
      return true;
   }
   
   //Graphs 4
   public List<Vertex> depthFirstSearch(String name)
   {
      return depthFirstSearch(getVertex(name));
   }
   
   public List<Vertex> depthFirstSearch(Vertex v)
   {
      List<Vertex> reach = new ArrayList<Vertex>();
      Stack<Vertex> stk = new Stack<Vertex>();
      Vertex curr = v;
      stk.push(v);
      while(!stk.isEmpty())
      {
         curr = stk.pop();
         if(!reach.contains(curr))
         {
            reach.add(curr);
            for(int x = 0; x < curr.getAdjacencies().size(); x++)
               stk.push(curr.getAdjacencies().get(x));
         }
      }
      return reach;
   }
   
   public List<Vertex> breadthFirstSearch(String name)
   {
      return breadthFirstSearch(getVertex(name));
   }
   
   public List<Vertex> breadthFirstSearch(Vertex v)
   {
      List<Vertex> reach = new ArrayList<Vertex>();
      Queue<Vertex> q = new LinkedList<Vertex>();
      Vertex curr = v;
      q.add(v);
      while(!q.isEmpty())
      {
         curr = q.remove();
         if(!reach.contains(curr))
         {
            reach.add(curr);
            for(int x = 0; x < curr.getAdjacencies().size(); x++)
               q.add(curr.getAdjacencies().get(x));
         }
      }
      return reach;
   }
   
   
   //Graphs 3
   public List<Vertex> getVertices()
   {
      return vertices;
   }
  
   public Vertex getVertex(int i)
   {
      return vertices.get(i);
   }
  
   public Vertex getVertex(String vertexName)
   {
      for(Vertex v : vertices)
         if(v.getName().equals(vertexName))
            return v;
      return null;
   }
  
   public Map<String, Integer> getVertexMap()
   {
      Map<String, Integer> m = new TreeMap<String, Integer>();
      int index = 0;
      for(Vertex v: vertices)
      {
         m.put(v.getName(), index);
         index++;
      }
      return m;
   }
  
   public void addVertex(String v)
   {
      boolean already = false;
      for(int x = 0; x < vertices.size(); x++)
         if(vertices.get(x).getName().equals(v))
            already = true;
      if(already == false)
         vertices.add(new Vertex(v));
   }
  
   public void addEdge(String source, String target)
   {
      if(getVertex(target) == null)
         vertices.add(new Vertex(target));
      if(getVertex(source) == null)
         vertices.add(new Vertex(source));
      getVertex(source).addEdge(getVertex(target));
   }
  
   public String toString()
   {
      String x = "";
      for(Vertex v: vertices)
         x += v.toString()  + "\n";
      return x;
   }
 /* enter your code here  */
       
}


