// Name: Sophia Evanisko
// Date: 3/13/2019

import java.io.*;
import java.util.*;

public class Dictionary
{
   public static void main(String[] args) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e)
      {
      }
      
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
   
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
   }
   
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
      String english = "";
      String spanish = "";
      Map<String, Set<String>> m = new TreeMap<String, Set<String>>();
      while(infile.hasNextLine())
      {
         english = infile.nextLine();
         spanish = infile.nextLine();
         add(m, english, spanish);
      }
      return m;
   }
   
   public static void add(Map<String, Set<String>> dictionary, String word, String translation)
   { 
      if(dictionary.containsKey(word))
         dictionary.get(word).add(translation);
      else
      {
         ArrayList<String> a = new ArrayList<String>();
         a.add(translation);
         dictionary.put(word, new TreeSet<String>(a));
      } 
   }
   
   public static void display(Map<String, Set<String>> m)
   {
      for (String treeKey : m.keySet())
      {
         System.out.println(treeKey + " " + m.get(treeKey));
      }
      System.out.println("");
   }
   
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
      String english = "";
      String spanish = "";
      String holdplace = "";
      Map<String, Set<String>> dict2 = new TreeMap<String, Set<String>>();
      for(String k : dictionary.keySet())
      {
         Set<String> t = dictionary.get(k);
         Iterator it = t.iterator();
         while(it.hasNext())
            add(dict2, (String)it.next(), k);
      }
      return dict2;
   }
}


   /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/