//name:   date: 
//resource classes for Graphs6: Dijkstra
//                     Graphs7: Dijkstra with Cities

import java.io.*;
import java.util.*;

class Edge {
   public final wVertex target;  //if it's public, you don't need accessor methods
   public final double weight;   //if it's public, you don't need accessor methods
  
   public Edge(wVertex argTarget, double argWeight) {
      target = argTarget;
      weight = argWeight;
   }
}

interface wVertexInterface 
{
   public String getName();
   public double getMinDistance();
   public void setMinDistance(double m);
   //public wVertex getPrevious();   //for Dijkstra 7
   //public void setPrevious(wVertex v);  //for Dijkstra 7
   public ArrayList<Edge> getAdjacencies();
   public void addEdge(wVertex v, double weight);   
   public int compareTo(wVertex other);
}

class wVertex implements Comparable<wVertex>, wVertexInterface
{
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;
   //private wVertex previous;  //for building the actual path in Dijkstra 7
   
   /*  enter your code for this class here   */ 
    
      
}


interface AdjListWeightedInterface 
{
   public List<wVertex> getVertices();
   public Map<String, Integer> getNameToIndex();
   public wVertex getVertex(int i);   
   public wVertex getVertex(String vertexName);
   public void addVertex(String v);
   public void addEdge(String source, String target, double weight);
   public void minimumWeightPath(String vertexName);   //Dijkstra's
}

 /*  Interface for Graphs 7:  Dijkstra with Cities */

// interface AdjListWeightedInterfaceWithCities 
// {       
   // public List<String> getShortestPathTo(wVertex v);
   // public AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException ;
// } 

public class AdjListWeighted implements AdjListWeightedInterface//, AdjListWeightedInterfaceWithCities
{
   private List<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
  //the constructor is no-arg 
  
   /*  enter your code for Graphs 6 */ 
   
   
   
   
   /*  enter your code for two new methods in Graphs 7 */
   
   
}   


