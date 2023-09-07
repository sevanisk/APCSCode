// Name:   
// Date:
 
import java.util.*;
import java.io.*;

/* For use with Graphs11: State Graphs,
   Heads-Tails-Heads
 */

class HTH_Driver
{
   public static void main(String[] args) throws FileNotFoundException
   {
      System.out.print("Enter the initial state, three H and/or T:  ");
      Scanner in = new Scanner(System.in);
      String initial = in.next().toUpperCase();
      Vertex v = makeGraph(initial);
      //printGraph(v);
      System.out.println("The state graph has been made.");
      
      while(true)
      {
         System.out.print("Enter the final state, three H and/or T:  ");
         String finalState = in.next().toUpperCase();;
         if( finalState.equals("-1") )
            break;
         v = findBreadth(v, finalState);
         System.out.println("The shortest path from "+initial+" to "+ finalState+ " is: ");
         System.out.println(initial);
         String s = "";
         while(v.previous != null)
         {
            s = v+"\n"+s;
            v = v.previous;
         }
         System.out.println(s);
      }
   }
   
   public static Vertex makeGraph(String s)
   {
      Vertex start = new Vertex(s);
      List<Vertex> possible = new ArrayList<Vertex>();
      allPossible("", s.length(), possible);
      System.out.println(possible);
      for(int first = 0; first < possible.size(); first++)
         for(int compTo = 0; compTo < possible.size(); compTo++)
         {
            if(possible.get(first).canBe(possible.get(compTo)))
               possible.get(first).addEdge(possible.get(compTo));
          
         }
      
      //"find" the start vertex
      for(int x = 0; x < possible.size(); x++)
         if(possible.get(x).equals(start))
            return possible.get(x);
      return start;
   }
   
   //helper
   public static void allPossible(String s, int n, List<Vertex> v)
   {
      if(s.length() == n)
         v.add(new Vertex(s));
      else
      {
         allPossible(s + "H", n, v);
         allPossible(s + "T", n, v);
      }
   }
   
   //helper
   //public static void printGraph()
   
   public static Vertex findBreadth(Vertex v, String goal)
   {
      Queue<Vertex> s = new LinkedList<Vertex>();
      List<Vertex> visited = new ArrayList<Vertex>();
      Vertex curr = v;
      s.add(v);
      visited.add(v);
      while(!s.isEmpty())
      {
         curr = s.remove();
         if(curr.toString().equals(goal))
            return curr;
         for(int x = 0; x < curr.getEdges().size(); x++)
            if(!visited.contains(curr.getEdges().get(x)))
            {
               s.add(curr.getEdges().get(x));
               visited.add(curr.getEdges().get(x));
            }
      
      //       Queue<Vertex> q = new LinkedList<Vertex>();
      //       List<Vertex> reach = new ArrayList<Vertex>();
      //       Vertex curr = null;
      //       Vertex myEdge = null;
      //       q.add(v);
      //       while(!q.isEmpty() && !q.peek().toString().equals(goal) )
      //       {
      //          curr = q.remove();
      //          for(int x = 0; x < curr.getEdges().size(); x++)
      //          {
      //             myEdge = curr.getEdges().get(x);
      //             if(!reach.contains(curr))
      //             {
      //                reach.add(curr);
      //                q.add(myEdge);
      //             }
      //          }
      
      }
      return curr; 
   }
}

class Vertex
{
   private final boolean[] state; //true is heads, false is tails
   private List<Vertex> edges = new ArrayList<Vertex>();
   public Vertex previous;
   
   public Vertex(String hth)
   {
      state = new boolean[3];
      makeStates(hth);
      previous = null;
   }
   
   public void makeStates(String s)
   {
      char c = ' ';
      int index = 0;
      while(index < 3)
      {
         c = s.charAt(index);
         if(c == 'H')
            state[index] = true;
         else
            state[index] = false;
         index++;
      }
   }
   
   public String toString()
   {
      String st = "";
      int index = 0;
      while(index < 3)
      {
         if(state[index] == true)
            st += "H";
         else
            st += "T";
         index++;
      }
      return st;
   }
   
   public String altString()
   {
      String st = toString() + "[ ";
      for(Vertex v: getEdges())
         st += v.toString() + " ";
      return st + "]";
   }
   
   //getter and setter methods
   public boolean[] getStates()
   {
      return state;
   }
   public boolean getStates(int index)
   {
      return state[index];
   }
   public List<Vertex> getEdges()
   {
      return edges;
   }
   // public Vertex getPrevious()
   // {
      // return previous;
   // }   
   public void addEdge(Vertex a)
   {
      edges.add(a);
   }
   // public void setPrevious(Vertex p)
   // {
      // previous = p;
   // }
   
   
   //coin states
   public boolean flipFirst()
   {
      if(state[1] == state[2])
         return true;
      return false;
   }
   public boolean flipLast()
   {
      if(state[0] == state[1])
         return true;
      return false;
   }
   
   public boolean equals(Vertex o)
   {
      if(state[0] == o.getStates(0))
         if(state[1] == o.getStates(1))
            if(state[2] == o.getStates(2))
               return true;
      return false;
   }
   public boolean midChange(Vertex o)
   {
      if(state[0] == o.getStates(0))
         if(state[1] != o.getStates(1))
            if(state[2] == o.getStates(2))
               return true;
      return false;
   }
   public boolean firstChange(Vertex o)
   {
      if(state[0] != o.getStates(0))
         if(state[1] == o.getStates(1))
            if(state[2] == o.getStates(2))
               return true;
      return false;
   }
   public boolean lastChange(Vertex o)
   {
      if(state[0] == o.getStates(0))
         if(state[1] == o.getStates(1))
            if(state[2] != o.getStates(2))
               return true;
      return false;
   }


   public boolean canBe(Vertex o)
   {
      if(equals(o))
         return false;
      if(midChange(o))
         return true;
      if(flipFirst() && firstChange(o))
         return true;
      if(flipLast() && lastChange(o))
         return true;
      return false;
   }    
}

/************************
 Enter the initial state, three H and/or T:  HTH
 The state graph has been made.
 Enter the final state, three H and/or T:  THT
 The shortest path from HTH to THT is: 
 HTH
 HHH
 HHT
 HTT
 TTT
 THT
 
 Enter the final state, three H and/or T:  HHH
 The shortest path from HTH to HHH is: 
 HTH
 HHH
 
 Enter the final state, three H and/or T:  -1
 

 *************************************/