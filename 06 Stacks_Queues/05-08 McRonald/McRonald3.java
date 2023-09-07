// Name: Sophia Evanisko
// Date: 1/18/19

import java.util.*;

public class McRonald3
{
   public static final int TIME = 1080;  //18 hrs * 60 min
   public static Queue<Customer> customerQueue;
   public static void main(String[] args)
   {
      customerQueue = new LinkedList<Customer>();
      int maxQueueLength = 0;
      int customers = 0;
      int currWait = 0;
      Customer a = null, b = null, c = null;
      int longestWait = 0;
      double totalWait = 0;
      boolean already = false;
      for(int x =0; x < TIME + 7; x++)
      {
      
      //adds new customers
         int z = (int)(Math.random() * 2);
         if(z == 1 && x < 1080) 
         {
            customerQueue.add(new Customer(x));
            //figures out total number of customers
            customers++;
         }
         
                  //removes customers that have waited at the service window
         
         if((a == null && customerQueue.size() != 0))
         {
            customerQueue.peek().timeAdjust(x);
            a = customerQueue.remove();
         }
         else if (a != null && a.getD() == x)
         {
            currWait = a.getD() - a.getA();
            //finds average wait time
            totalWait += currWait;
                   //finds longest wait time
            if(currWait > longestWait)
               longestWait = currWait;
            a = null;
         }
         
         if((b == null && customerQueue.size() != 0))
         {
            customerQueue.peek().timeAdjust(x);
            b = customerQueue.remove();
         }
         else if (b != null && b.getD() == x)
         {
            currWait = b.getD() - b.getA();
            //finds average wait time
            totalWait += currWait;
                   //finds longest wait time
            if(currWait > longestWait)
               longestWait = currWait;
            b = null;
         }
      
         if((c == null && customerQueue.size() != 0))
         {
            customerQueue.peek().timeAdjust(x);
            c = customerQueue.remove();
         }
         else if (c != null && c.getD() == x)
         {
            currWait = c.getD() - c.getA();
            //finds average wait time
            totalWait += currWait;
                   //finds longest wait time
            if(currWait > longestWait)
               longestWait = currWait;
            c = null;
         }
         
         //finds max length of queue
         if(customerQueue.size() > maxQueueLength)
            maxQueueLength = customerQueue.size();
            
         //displays Queue
         Queue<Customer> newQ = customerQueue;
         display(newQ, a, b, c, x);
         
      
      }
      System.out.println("Total customers served = " + customers);
      System.out.println("Average wait time = " + (double)(totalWait/customers));
      System.out.println("Longest wait time = " + (longestWait));
      System.out.println("Longest queue = " + maxQueueLength);
   }
   
   public static void display(Queue<Customer> q, Customer a1, Customer a2, Customer a3, int min) //if you have a Customer class
   {
      System.out.print(min + ": ");
      String print = "[";
      Customer old = new Customer(0);
      boolean torf = false;
      if(q != null)
      {
         if(a1 != null)
            print += (a1.getA() + ", ");
         if(a2 != null)
            print += (a2.getA() + ", ");
         if(a3 != null)
            print += (a3.getA() + ", ");
         for(int x = 0; x < q.size(); x++)
         {
            old = q.remove();
            print += (old.getA() + ", ");
            torf = q.add(old);
         }
         if(print.length() >= 2)
            print = print.substring(0, print.length()-2) + "]";
         else 
            print = "[]";
         System.out.println(print); 
      }
      else
         System.out.println("[]");
   }
}

class Customer      // if you want a Customer class
{
   private int arriveT, departT; 
   private final int waitT;
   private boolean atWindow;
   public Customer(int arrivalTime)
   {
      this.arriveT = arrivalTime;
      this.waitT = (int)(Math.random() * 6 + 2);
      this.departT= arriveT + waitT;
   }
   
   public int getA()
   {
      return arriveT;
   }
  
   public int getW()
   {
      return waitT;
   }
   
   public int getD()
   {
      return departT;
   }
  
  //returns the new departure time after adjusting it and the wait time
   public void timeAdjust(int whenAtWindow)
   {
      this.departT = waitT + whenAtWindow;
      //return departT; 
   }

}
