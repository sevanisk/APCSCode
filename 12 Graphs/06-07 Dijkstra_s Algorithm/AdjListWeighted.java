// Name:   Sophia Evanisko
// Date:   5/09/19
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs6: Dijkstra
 * and Graphs7: Dijkstra with Cities
 */

class Edge 
{
   //public fields not common on AP exam
   public final wVertex target;  
   public final double weight;   
  
   public Edge(wVertex argTarget, double argWeight) 
   {
      target = argTarget;
      weight = argWeight;
   }
}

interface wVertexInterface 
{
   String getName();
   double getMinDistance();
   void setMinDistance(double m);
   wVertex getPrevious();   //for Dijkstra 7
   void setPrevious(wVertex v);  //for Dijkstra 7
   ArrayList<Edge> getAdjacencies();
   void addEdge(wVertex v, double weight);   
   int compareTo(wVertex other);
}

class wVertex implements Comparable<wVertex>, wVertexInterface
{
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;  
   private wVertex previous;  //for building the actual path in Dijkstra 7
   public wVertex(String argName)
   {
      this.name = argName;
      adjacencies = new ArrayList<Edge>();
      previous = null;
   }
   //Graphs 7
   public wVertex getPrevious()
   {
      return this.previous;
   }
   
   public void setPrevious(wVertex v)
   {
      this.previous = v;
   }
   
   //Graphs 6 
   public String getName()
   {
      return this.name;
   }
   
   public double getMinDistance()
   {
      return minDistance;
   }
   
   public void setMinDistance(double m)
   {
      this.minDistance = m;
   }
   
   public ArrayList<Edge> getAdjacencies()
   {
      return this.adjacencies;
   }
   
   public void addEdge(wVertex v, double weight)
   {
      this.adjacencies.add(new Edge(v, weight));
   }
   
   public int compareTo(wVertex other)
   {
      if(minDistance < other.getMinDistance())
         return -1;
      if(minDistance > other.getMinDistance())
         return 1;
      return 0;
   }
}

interface AdjListWeightedInterface 
{
   List<wVertex> getVertices();
   Map<String, Integer> getNameToIndex();
   wVertex getVertex(int i);  
   wVertex getVertex(String vertexName);
   void addVertex(String v);
   void addEdge(String source, String target, double weight);
   void minimumWeightPath(String vertexName);   //Dijkstra's
}

/* Interface for Graphs 7:  Dijkstra with Cities 
 */

interface AdjListWeightedInterfaceWithCities 
{       
   List<String> getShortestPathTo(wVertex v);
   AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException;
}


public class AdjListWeighted implements AdjListWeightedInterface, AdjListWeightedInterfaceWithCities
{
   private List<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
   //the constructor is a no-arg constructor
   public AdjListWeighted()
   {
      nameToIndex = getNameToIndex(); 
   }
   
   //Graphs 7
   public List<String> getShortestPathTo(wVertex v)
   {
      List<String> myPath = new ArrayList<String>();
      wVertex previous = v.getPrevious();
      while(previous != null)
      {
         myPath.add(previous.getName());
         previous = previous.getPrevious();
      } 
      return myPath;
   }
   
   public AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException
   {
      AdjListWeighted graph = new AdjListWeighted();
      Map<Integer, String> m = new TreeMap<Integer, String>();
      String curr = ""; int index = 0;
      
      Scanner cities = new Scanner(vertexNames);
      int size = cities.nextInt();
      while(cities.hasNext())
      {
         curr = cities.next();
         graph.addVertex(curr);
         m.put(index, curr);
         index++; 
      }
      cities.close();
      
      Scanner edges = new Scanner(edgeListData);
      size = edges.nextInt();
      String source = "";
      String target = "";
      int curr1 = 0;
      int[][] weights = new int[size][size];
      for(int r = 0; r < size; r++)
         for(int c = 0; c < size; c++)
         {
            curr1 = edges.nextInt();
            source = m.get(r);
            target = m.get(c);
            weights[r][c] = curr1;
            if(curr1 != 9999)
               graph.addEdge(source, target, curr1);
         }
      edges.close();
      return graph;
   }
   
   //Graphs 6
   public List<wVertex> getVertices()
   {
      return this.vertices;
   }
   
   public Map<String, Integer> getNameToIndex()
   {
      Map<String, Integer> m = new HashMap<String, Integer>();
      int index = 0;
      for(wVertex v: vertices)
      {
         m.put(v.getName(), index);
         index++;
      }
      return m;
   }
   
   public wVertex getVertex(int i)
   {
      return vertices.get(i);
   }
   
   public wVertex getVertex(String vertexName)
   {
      for(int x = 0; x < vertices.size(); x++)
         if(vertices.get(x).getName().equals(vertexName))
            return vertices.get(x);
      return null;
   }
   
   public void addVertex(String v)
   {
      boolean already = false;
      for(int x = 0; x < vertices.size(); x++)
         if(vertices.get(x).getName().equals(v))
            already = true;
      if(already == false)
         vertices.add(new wVertex(v));
      nameToIndex = getNameToIndex();
   }
   
  
   public void addEdge(String source, String target, double weight)
   {
   //edit!! see adjlist
      wVertex source1, target1;
      if(getVertex(source) == null)
         source1 = new wVertex(source);
      else
         source1 = getVertex(source);
      if(getVertex(target) == null)
         target1 = new wVertex(target); 
      else
         target1 = getVertex(target);
      source1.addEdge(target1, weight);
      nameToIndex = getNameToIndex();
   }
   
   public void minimumWeightPath(String vertexName)
   {
      Queue<wVertex> pq = new PriorityQueue<wVertex>();
   //set A's distance to itself to 0
      wVertex a = getVertex(vertexName);
      wVertex curr = getVertex(vertexName);
      double totalDist = 0;
      Edge myEdge = null;
      a.setMinDistance(0);
      pq.add(a);
      while(!pq.isEmpty())
      {
         curr = pq.remove();
         for(int x = 0; x < curr.getAdjacencies().size(); x++)
         {
            myEdge = curr.getAdjacencies().get(x);
            totalDist = myEdge.weight + curr.getMinDistance();
            if(totalDist < myEdge.target.getMinDistance());
            {
               myEdge.target.setPrevious(curr);
               myEdge.target.setMinDistance(totalDist);
               pq.add(myEdge.target);
            }
         }
      
      }
   //Graphs 6
   //process the neighbors of A to find the distance from a to them
   //place them in the priority queue
   //remove the nearest object from the priority queue
   //process its neighbors (possibly updating their min distance) 
   //add the neighbors to the priority queue
   //continue until the priority queue is empty--make sure all elements
   //processed and reachable from A have been added to the adjacencies array list
   
   }
   /*  enter your code for two new methods in Graphs 7 */
   
   
}   


