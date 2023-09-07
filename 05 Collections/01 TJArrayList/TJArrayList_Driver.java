// Name:Sophia Evanisko
// Date:12/04/2018

/**
 * Implements the List interface
 * with a backing array of Objects.
 * @override toString()
 */       
public class TJArrayList_Driver
{  
   public static void main(String [] args)
   {
      TJArrayList myList = new TJArrayList();	
      //myList.add("Apple");
      myList.add("Banana");
      myList.add("Fig");
      //myList.add("Cucumber");
      //myList.add("Dates");
      myList.add(2, "Cucumber");
      myList.add(3, "Dates");
      myList.add(0, "Apple");
      System.out.println(myList);
      System.out.println("Size is " + myList.size());
      try
      {
         myList.add(12, "Peach");
      }
      catch(IndexOutOfBoundsException e)
      {
         System.out.println(e);
      }
      System.out.println("Get 2: " + myList.get(2));
      System.out.print("Set at 2: ");
      Object previous = myList.set(2, "Cherry");
      System.out.println(previous +" " +myList);
      Object obj = myList.remove(2);
      System.out.println("Removed " + obj+ " from " + myList);
      System.out.println("Size is " + myList.size());
      System.out.print("Add too many items: ");
      for(int i = 3; i <= 10; i++)
         myList.add(new Integer(i));
      System.out.println(myList);
      System.out.println("Size is " + myList.size());   	
      System.out.println("Contains \"Breadfruit\"?  " + myList.contains("Breadfruit"));
      System.out.println("Contains \"Banana\"?  " + myList.contains("Banana"));
   }
}
   
class TJArrayList
{
   private int size;							//stores the number of objects
   private Object[] myArray;
   
   public TJArrayList()                //default constructor makes 10 cells
   {
      myArray = new Object[10];
      this.size = 0; 
   }
   
   public int size()
   {
      size = 0;
      for(int x = 0; myArray[x] != null; x++)
         size++; 
      return size; 
   }
 	
   /**
    * Appends obj to end of list. 
    * Increases size.
    * Must be an O(1) operation when size < array.length 
    * and O(n) when it doubles the length of the array.
    * @return true
    */       
   public boolean add(Object obj)
   {
      if(size < myArray.length)
      {
         myArray[size] = obj;
         size++;
         return true;
      }
      Object[] newArray = new Object[size * 2];
      for(int x = 0; x < size; x++)
         newArray[x] = myArray[x];
      newArray[size] = obj;
      size++;
      myArray = newArray;
      return true; 
   }
       
   /**
    * Inserts obj at position index. 
    * Increases size.
    */         
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real ArrayList is coded
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      if(size >= myArray.length)
      {
         Object[] newArray = new Object[size * 2];
         for(int x = 0; x < size; x++)
            newArray[x] = myArray[x];
         myArray = newArray;
      }
      
      for(int x = size; x > index; x--)
         myArray[x] = myArray[x-1];
      myArray[index] = obj;
      size++;
      
   }
   
   /**
    * Retrieves the obj at a given index.
    * @return obj at position index 
    */  
   public Object get(int index)
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      return myArray[index];
   }
   
   /**
    * Replaces obj at position index. 
    * @return the original object (the one being replaced)
    */        
   public Object set(int index, Object obj)
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      Object o = myArray[index];
      myArray[index] = obj;
      return o;
   }
       
   /**
    * Removes node from position index.  
    * Shifts elements to the left. 
    * Decreases size.
    * @return the object at position index
    */       
   public Object remove(int index)
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      Object o = myArray[index];
      set(index, null);
      for(int x = index; x < size; x++)
         myArray[x] = myArray[x+1];
      size--;
      return o; 
   }
	
   /**
    * This method compares objects.
    * Must use .equals, not ==. 
    */     
   public boolean contains(Object obj)
   {
      for(int x = 0; x < size; x++)
         if(myArray[x].equals(obj))
            return true;
      return false; 
   }
   
 
   /**
    * This method returns a String of objects in the array.
    * Includes the outer square projects and comma separators. 
    */   
   public String toString()
   {
      String s = "[";
      for(int x = 0; x < size-1; x++)
         s += (myArray[x] + ", ");
      s+= myArray[size-1] + "]";
      return s;
   }
}

/*************************************

 [Apple, Banana, Fig, Cucumber, Dates]
 Size is 5
 java.lang.IndexOutOfBoundsException: Index: 12, Size: 5
 Get 2: Fig
 Set at 2: Fig [Apple, Banana, Cherry, Cucumber, Dates]
 Removed Cherry from [Apple, Banana, Cucumber, Dates]
 Size is 4
 Add too many items: [Apple, Banana, Cucumber, Dates, 3, 4, 5, 6, 7, 8, 9, 10]
 Size is 12
 Contains "Breadfruit"?  false
 Contains "Banana"?  true
 
 ************************************/