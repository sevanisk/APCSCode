//mlbillington@fcps.edu       July 2014
//lesson:  Graphs3: EdgeList
//uses AdjList

import java.util.*;
import java.io.*;
 
public class AdjList_3_Driver
{  
   public static void main(String[] args)throws FileNotFoundException
   {
      System.out.println("Edge List Representation! ");
      AdjList g = new AdjList();
      g.addVertex("A");      //if it's not there, add it.
      g.addVertex("B");
      g.addEdge("A", "C"); // <-- warning!  Be sure to add all the Vertices first; 
                           // or else deal with it. 
      g.addVertex("C");
      g.addVertex("D");
      g.addEdge("B", "A");
      g.addEdge("C", "C");
      g.addEdge("C", "D");
      g.addEdge("D", "C");
      g.addEdge("D", "A");
      Map<String, Integer> m = g.getVertexMap();  //look at the map in the debugger
      System.out.println(g.getVertex("C").toString());  //print one vertex
      System.out.println("-----------------");
      System.out.println(g.toString());  //print the whole graph
   }
}


/***************************
 Edge List Representation! 
 C [C D]
 -----------------
 A [C]
 B [A]
 C [C D]
 D [C A]
 
 ************************/