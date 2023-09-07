 //Name: Sophia Evanisko  
 //Date: 3/29/19
 
import java.util.*;


/* implement the API for java.util.PriorityQueue
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public boolean add(E obj)
   {
      if(myHeap.size() == 0)
         myHeap.add(1, obj);
      myHeap.add(myHeap.size(), obj);
      heapUp(myHeap.size()-1); 
      return true;
   }
   
   public E remove()
   {
      if(myHeap.size() == 0)
         return null;
      heapDown(1, myHeap.size()-1);
      E object = myHeap.remove(1);
      return object;
   }
   
   public E peek()
   {
      int x = 0;
      while(x < myHeap.size() && myHeap.get(x) == null)
         x++;
      if(x == myHeap.size())
         return myHeap.get(x-1);
      return myHeap.get(x);
   }
		
   
   public boolean isEmpty()
   {
      if(myHeap.size() == 0)
         return true;
      int indexes = 0; 
      while(indexes < myHeap.size())
      {
         if(myHeap.get(indexes) != null)
            return false;
         indexes++;
      }
      return true; 
   }
   
   private void heapUp(int k)
   {   
      while (k > 0 && (myHeap.get(parent(k)) != null) && myHeap.get(k).compareTo(myHeap.get(parent(k))) < 0) 
      {
         swap(k, parent(k));
         k = parent(k);
      }
   }
   
   private static int parent(int i) 
   {
      return (i)/2;
   }

   private void swap(int a, int b)
   {
      E object = myHeap.get(a);
      myHeap.set(a, myHeap.get(b));
      myHeap.set(b, object);
   }
   
   private void heapDown(int k, int size)
   {
      int l = k*2;
      int r = (k*2) + 1;
      if(k*2 < size)
      {
         if(myHeap.get(k).compareTo(myHeap.get(l)) > 0 || myHeap.get(l) == null)
            swap(k, l);
         if(myHeap.get(k).compareTo(myHeap.get(r)) > 0 || myHeap.get(r) == null)
            swap(k, r);
         if(myHeap.get(l).compareTo(myHeap.get(r)) > 0)
            swap(l, r);
         heapDown(l, size);
         heapDown(r, size);
      }
   }
   
   public String toString()
   {
      return myHeap.toString();
   }  
}
