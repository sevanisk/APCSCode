// Name: Sophia Evanisko
// Date: 3/29/19
import java.text.DecimalFormat;
public class HeapSort
{
   public static int SIZE;  //9 or 100
	
   public static void main(String[] args)
   {
      //Part 1: Given a heap, sort it. Do this part first. 
      SIZE = 9;  
      double heap[] = {-1,99,80,85,17,30,84,2,16,1};
      
      display(heap);
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));
      
      //Part 2:  Generate 100 random numbers, make a heap, sort it.
      SIZE = 100;
      double[] heap2 = new double[SIZE + 1];
      heap2 = createRandom(heap2);
      display(heap2);
      makeHeap(heap2, SIZE);
      display(heap2); 
      sort(heap2);
      display(heap2);
      System.out.println(isSorted(heap2));
   }
   
	//******* Part 1 ******************************************
   public static void display(double[] array)
   {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");	
   }
   
   public static void sort(double[] array)
   {
      /* enter your code here */
      int lastI = array.length-1;
      for(int x = 1; x < array.length; x++)
      {
         swap(array, 1, lastI);
         lastI--;
         heapDown(array, 1, lastI);
      }
      
   
      if(array[1] > array[2])   //just an extra swap, if needed.
         swap(array, 1, 2);
   }
  
   public static void swap(double[] array, int a, int b)
   {
      double fill = array[a];
      array[a] = array[b];
      array[b] = fill;
   }
   
   public static void heapDown(double[] array, int k, int size)
   {
      int l = k*2;
      int r = (k*2) + 1;
      if(k*2 < size)
      {
         if(array[k] < array[l])
            swap(array, k, l);
         if(array[k] < array[r])
            swap(array, k, r);
         if(array[l] < array[r])
            swap(array, l, r);
         heapDown(array, l, size);
         heapDown(array, r, size);
      }
   }
   
   public static boolean isSorted(double[] arr)
   {
      for(int x = 0; x < arr.length-1; x++)
      {
         if(!(arr[x] <= arr[x+1]))
            return false;
      }
      return true;
   }
   
   //****** Part 2 *******************************************

   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
   public static double[] createRandom(double[] array)
   {  
      array[0] = -1;   //because it will become a heap
      DecimalFormat d = new DecimalFormat("##.##");
      for(int x = 1; x < 101; x++)
      {
         array[x] = (double)(((Math.random() *99) + 1) * 10000) / 10000;
         array[x] = Double.parseDouble(d.format(array[x]));
      }
      return array;
   }
   
   //turn the random array into a heap
   public static void makeHeap(double[] array, int size)
   {  
      for (int y = array.length-1; y >= 1; y--) 
         heapMaker(array, y);
   }
   
   public static void heapMaker(double[] arr, int i) 
   {
      int largest;
      int l = i * 2;
      int r = (i * 2) + 1;
      
      if (((l < arr.length) && (arr[l] > arr[i]))) 
         largest = l;
      else 
         largest = i;
         
      if (((r < arr.length) && (arr[r] > arr[largest]))) 
         largest = r;
         
      if (largest != i) 
      {
         swap(arr, i, largest);
         heapMaker(arr, largest);
      }
   }
}

