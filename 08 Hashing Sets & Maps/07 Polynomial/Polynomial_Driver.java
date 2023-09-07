 // Name: Sophia Evanisko    
 // Date: 3/16/19

import java.util.*;

public class Polynomial_Driver
{
   public static void main(String[] args)
   {
      Polynomial poly = new Polynomial();    // 2x^3 + -4x + 2
      poly.makeTerm(1, -4);
      poly.makeTerm(3, 2);
      poly.makeTerm(0, 2);
      System.out.println("Map:  " + poly.getMap());
      System.out.println("String:  " + poly.toString());
      double evaluateAt = 2.0;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
      
      System.out.println("-----------");
      Polynomial poly2 = new Polynomial();  // 2x^4 + x^2 + -5x + -3
      poly2.makeTerm(1, -5);
      poly2.makeTerm(4, 2);
      poly2.makeTerm(0, -3);
      poly2.makeTerm(2, 1);
      System.out.println("Map:  " + poly2.getMap()); 
      System.out.println("String:  " +poly2.toString());
      evaluateAt = -10.5;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
      
      
      System.out.println("-----------");
      System.out.println("Sum: " + poly.add(poly2));
      System.out.println("Product:  " + poly.multiply(poly2));
      
      /*  Another case:   (x+1)(x-1) -->  x^2 + -1    */
      System.out.println("===========================");
      Polynomial poly3 = new Polynomial();   // (x+1)
      poly3.makeTerm(1, 1);
      poly3.makeTerm(0, 1);
      System.out.println("Map:  " + poly3.getMap());
      System.out.println("String:  " + poly3.toString());
         
      Polynomial poly4 = new Polynomial();    // (x-1)
      poly4.makeTerm(1, 1);
      poly4.makeTerm(0, -1);
      System.out.println("Map:  " + poly4.getMap());
      System.out.println("String:  " + poly4.toString());
      System.out.println("Product:  " + poly4.multiply(poly3));   // x^2 + -1 
   //    
   //    /*  testing the one-arg constructor  */
      // System.out.println("==========================="); 
      // Polynomial poly5 = new Polynomial("2x^3 + 4x^2 + 6x^1 + -3");
      // System.out.println("Map:  " + poly5.getMap());  
      // System.out.println(poly5);
   
   }
}
interface PolynomialInterface
{
   public void makeTerm(Integer exp, Integer coef);
   public Map<Integer, Integer> getMap();
   public double evaluateAt(double x);
   public Polynomial add(Polynomial other);
   public Polynomial multiply(Polynomial other);
   public String toString();
}

class Polynomial implements PolynomialInterface
{
   Map<Integer, Integer> myMap;
   public Polynomial()
   {
      myMap = new HashMap<Integer, Integer>();
   }
   
   public void makeTerm(Integer exp, Integer coef)
   {
      myMap.put(exp, coef);
   }
   
   public Map<Integer, Integer> getMap()
   {
      return myMap;
   }
   
   public double evaluateAt(double x)
   {
      double multiplied = 0;
      double total = 0;
      int power = 0;
      Iterator powers = myMap.keySet().iterator();
      while(powers.hasNext())
      {
         power = (Integer)powers.next();
         multiplied = Math.pow(x, power);
         total += multiplied * (myMap.get(power));
      }
      return total;
   }
   
   public Polynomial add(Polynomial other)
   {
      Polynomial add = new Polynomial();
      int pow = 0;
      int newVal = 0;
      Iterator toS = myMap.keySet().iterator();
      while(toS.hasNext())
      {
         pow = (Integer)toS.next();
         add.makeTerm(pow, myMap.get(pow));
      }
   
      Iterator toS2 = other.getMap().keySet().iterator();
      while(toS2.hasNext())
      {
         pow = (Integer)toS2.next();
         if(myMap.containsKey(pow))
         {
            newVal = other.getMap().get(pow) + myMap.get(pow);
            add.getMap().remove(pow);
            add.makeTerm(pow, newVal);
         }
         else
            add.makeTerm(pow, other.getMap().get(pow));
      }
      return add;
   }
   
   public Polynomial multiply(Polynomial other)
   {
      int newPow = 0;
      int newCoef = 0;
      Polynomial mult1 = new Polynomial();
      for(int key1: myMap.keySet())
         for(int key2: other.getMap().keySet())
         {
            newPow = key1 + key2;
            newCoef = myMap.get(key1) * other.getMap().get(key2);
            mult1 = newAdd(newPow, newCoef, mult1);
         }
      return mult1;
   }
   //HELPERS
   public Polynomial newAdd(int newPow, int newCoef, Polynomial addOnTo)
   {
      Polynomial p = new Polynomial();
      p.makeTerm(newPow, newCoef);
      return addOnTo.add(p);
   }
   
   public String toString()
   {
      Iterator toS = myMap.keySet().iterator();
      Stack<String> str = new Stack<String>();
      int pow = 0;
      String curr = "";
      while(toS.hasNext())
      {
         pow = (Integer)toS.next();
         if(myMap.get(pow) != 0)
         {
            curr = (myMap.get(pow) + "x^" + pow + " + "); 
            if(myMap.get(pow) == 1)
               curr = "x^" + pow + " + "; 
            if(pow == 1 || pow == 0)
               curr = myMap.get(pow) + "x + ";
            if(pow == 1 && myMap.get(pow) == 1)
               curr = "x + ";
            str.push(curr);
         }
      }
      String newS = "";
      while(!str.isEmpty())
      {
         newS += str.pop();
      }
      return newS.substring(0, newS.length()-4); 
   }
}


/***************************************  
  ----jGRASP exec: java Polynomial_teacher
 Map:  {0=2, 1=-4, 3=2}
 String:  2x^3 + -4x + 2
 Evaluated at 2.0: 10.0
 -----------
 Map:  {0=-3, 1=-5, 2=1, 4=2}
 String:  2x^4 + x^2 + -5x + -3
 Evaluated at -10.5: -2271.25
 -----------
 Sum: 2x^4 + 2x^3 + x^2 + -9x + -1
 Product:  4x^7 + -6x^5 + -6x^4 + -10x^3 + 22x^2 + 2x + -6
 
  ----jGRASP: operation complete.
 ********************************************/