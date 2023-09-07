public class SmartCard
{
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
