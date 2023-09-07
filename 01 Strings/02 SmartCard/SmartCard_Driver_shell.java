// Name:
// Date:
 
import java.text.DecimalFormat;
public class SmartCard_Driver
{
   public static void main(String[] args) 
   {
      Station downtown = new Station("Downtown", 1);
      Station center = new Station("Center City", 1);
      Station uptown = new Station("Uptown", 2);
      Station suburbia = new Station("Suburb", 4);
     
      SmartCard jimmy = new SmartCard(20.00); 
      jimmy.board(center);                    //Boarded at Center City.  SmartCard has $20.00
      jimmy.disembark(suburbia);              //From Center City to Suburb costs $2.75.  SmartCard has $17.25
      jimmy.disembark(uptown);				//Error:  did not board?!
      System.out.println();
   			
      SmartCard susie = new SmartCard(1.00); 
      susie.board(uptown);            		//Boarded at Uptown.  SmartCard has $1.00
      susie.disembark(suburbia);				//Insuffient funds to exit. Please add more money.
      System.out.println();
    
      SmartCard kim = new SmartCard(.25);    
      kim.board(uptown);            		    //Insuffient funds to board. Please add more money.
      System.out.println();
    
      SmartCard b = new SmartCard(10.00);   
      b.board(center);            		    //Boarded at Center City.  SmartCard has $10.00
      b.disembark(downtown);					//From Center City to Downtown costs $0.50.  SmartCard has $9.50
      System.out.println();
          
      SmartCard mc = new SmartCard(10.00);  
      mc.board(suburbia);            		    //Boarded at Suburbia.  SmartCard has $10.00
      mc.disembark(downtown);					//From Suburbia to Downtown costs $2.75.  SmartCard has $7.25
      System.out.println();
      
        //Make more test cases.  What else needs to be tested?
   }
} 	

//Note SmartCard is not denoted as public.  Why?
class SmartCard 
{
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
   private double myBalance;
   private boolean boarded;
   private int myBoarding, myDisembarking;

   public SmartCard(double balance)
   {
      this.myBalance = balance;
   }


   public double addMoney(double d)
   {
      this.myBalance = this.myBalance + d;
      return myBalance;
   }
   
   
   public String getBalance()
   {
      return myBalance + "";
   }
   
   
   public boolean isBoarded()
   {
      if(boarded==true)
         return true;
      return false;
   }
   
   
   public void board(Station s)
   {
   
      if(isBoarded() == true)
      {
         System.out.println("Error: already boarded!");
         return;
      }
      
      else if(myBalance < 0.50)
      {
         System.out.println("Insufficient funds to board.  Please add more money.");
         return;
      }
      
      else
      {
         costMoney(0.50);
         System.out.println("Boarded at" + s.getName() + ".  Smartcard has " + myBalance);
         this.myBoarding= s.getZone();
         boarded = true;
      }
   }
   
   
   public double costMoney(double d)
   {
      this.myBalance = this.myBalance - d;
      return myBalance;
   }
   
   
   public void disembark(Station s)
   {
      double cost1 = cost(s);
      if((myBalance - cost1)<0)
      {
         System.out.println("Insufficient funds to exit.  Please add more money.");
         return;
      }
      else if(isBoarded() == false) 
      {
         System.out.println("Error: Did not board.");
         return;
      }
      else
      {
         costMoney(cost1);
         System.out.println("From " + this.myBoarding + " to " + this.myDisembarking + " costs " + (cost1+0.50) + ".  ");
         System.out.print("Smartcard has " + this.myBalance);
         boarded = false;
      }
   }
   
   
   public double cost (Station s)
   {
      this.myDisembarking = s.getZone();
      int difference = Math.abs(myBoarding-myDisembarking);
      double cost = difference * 0.75;
      return cost;
   }
      
}
   
//Note Station is not a public class.  Why?
class Station
{
   private String myName;
   private int myZone;
   public Station()//default constructor
   {
      this.myName = "...";
      this.myZone = 0;
   }
  
   public Station(String name, int zone)
   {
      this.myName = name;
      this.myZone = zone;
   }

   public String getName()
   {
      return myName;
   }

   public int getZone()
   {
      return myZone;
   }
   
   public String toString()//practice writing toString method
   {
      return "The station's name is " + myName + " and the station's zone is " + myZone;
   }
   
   public void setName(String name)//practice writing modifier methods
   {
      this.myName= name;
   }
   
   public void setZone(int zone)//practice writing modifier methods
   {
      this.myZone = zone;
   }    
}

 /*******************  Sample Run ************

 Boarded at Center City.  SmartCard has $20.00
 From Center City to Suburb costs $2.75.  SmartCard has $17.25
 Error: did not board?!
 
 Boarded at Uptown.  SmartCard has $1.00
 Insufficient funds to exit. Please add more money.
 
 Insufficient funds to board. Please add more money.
 
 Boarded at Center City.  SmartCard has $10.00
 From Center City to Downtown costs $0.50.  SmartCard has $9.50
 
 Boarded at Suburb.  SmartCard has $10.00
 From Suburb to Downtown costs $2.75.  SmartCard has $7.25
 
 ************************************************/