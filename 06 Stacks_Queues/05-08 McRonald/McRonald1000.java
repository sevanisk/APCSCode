// Name: Sophia Evanisko
// Date: 1/18/19

import java.util.*;

public class McRonald1000
{
   public static final int TIME = 1080;  //18 hrs * 60 min
   public static Queue<Customer> customerQueue;
   public static int customers = 0, maxQueueLength = 0, longestWait = 0, maxDay = 0;
   public static double totalWait = 0;
   public static void main(String[] args)
   {
      customerQueue = new LinkedList<Customer>();
      for(int x = 0; x < 100; x++)
      {
         int custPerDay = oneDay();
         if(custPerDay > maxDay)
            maxDay = custPerDay;
         while(!customerQueue.isEmpty())
            customerQueue.remove();
      }
      
      System.out.println("Total customers served = " + customers);
      System.out.println("Average wait time = " + (double)(totalWait/customers));
      System.out.println("Longest wait time = " + (longestWait));
      System.out.println("Longest queue = " + maxQueueLength);
      System.out.println("Average served per day = " + (double)(customers/1000.00));
      System.out.println("Largest day = " + maxDay);
   } 
   public static int oneDay()
   {
      boolean already = false;
      int currWait = 0;
      int custDay = 0;
      for(int x = 0; x < TIME + 7; x++)
      {
      //adds new customers
         int z = (int)(Math.random() * 5);
         if(z == 1 && x < 1080) 
         {
            customerQueue.add(new Customer(x));
            //figures out total number of customers
            customers++;
            custDay++;
         }
         
         //removes customers that have waited at the service window
         if((customerQueue.size() == 1 && already == false))
         {
            customerQueue.peek().timeAdjust(x);
            already = true;
         }
         else if(customers != 0 && customerQueue.size() != 0 && customerQueue.peek().getD() == x)
         {
            currWait = customerQueue.peek().getD() - customerQueue.peek().getA();
           //finds average wait time
            totalWait += currWait;
            customerQueue.remove();
            if(customerQueue != null && !customerQueue.isEmpty())
               customerQueue.peek().timeAdjust(x);
            else
               already = false;   
         }
      
                    
         //finds longest wait time
         if(currWait > longestWait)
            longestWait = currWait;
         
         //finds max length of queue
         if(customerQueue.size() > maxQueueLength)
            maxQueueLength = customerQueue.size();
      
      }
      return custDay;
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
   }

}