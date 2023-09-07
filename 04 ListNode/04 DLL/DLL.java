// Name:Sophia Evanisko
// Date:11/29/2018

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
   private int size;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   public int size()
   {
      size = 0;
      DLNode r = head;
      while(r.getNext() != head)
      {
         r = r.getNext();
         size++;
      }
      return size;
   
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index.  increments size. 	*/
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode r = new DLNode(obj, null, null);
      DLNode x = head;
      for(int y = 0; y < index; y++)
         x = x.getNext();
      r.setPrev(x);
      r.setNext(x.getNext());
      x.getNext().setPrev(r);
      x.setNext(r);
      size++;           
   }
   
   /* return obj at position index. 	*/
   public Object get(int index)
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      
      DLNode new2 = head;
      for(int x = 0; x < index; x++)
         new2 = new2.getNext();
      return new2.getNext().getValue();
   }
   
   /* replaces obj at position index. 
        returns the obj that was replaced*/
   public Object set(int index, Object obj)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode new3 = head;
      
      for(int x = 0; x <= index; x++)
         new3 = new3.getNext();
      Object old = new3.getValue();
      new3.setValue(obj);
      return old; 
   }
   
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object of the node that was removed.    */
   public Object remove(int index)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode r = head;
      for(int x = 0; x <= index; x++)
         r = r.getNext();
      Object obj = r.getValue();
      r.getPrev().setNext(r.getNext());
      r.getNext().setPrev(r.getPrev());
      size--;
      return obj;
   }
   

   /* inserts obj at front of list, increases size   */
   public void addFirst(Object obj)
   {
      DLNode z = new DLNode(obj, null, null);
      head.getNext().setPrev(z);
      z.setNext(head.getNext());
      z.setPrev(head);
      head.setNext(z);
      size++;
   }
   
   /* appends obj to end of list, increases size    */
   public void addLast(Object obj)
   {
      DLNode z = head;
      DLNode newl = new DLNode(obj, null, null);
      head.setPrev(newl);
      newl.setNext(head);
      for(int x = 0; x < size; x++)
         z = z.getNext();
      z.setNext(newl);
      newl.setPrev(z);
      size++;
   }
   
   /* returns the first element in this list  */
   public Object getFirst()
   {
      return head.getNext().getValue();
   }
   
   /* returns the last element in this list  */
   public Object getLast()
   {
      return head.getPrev().getValue();
   }
   
   /* returns and removes the first element in this list, or
       returns null if the list is empty  */
   public Object removeFirst()
   {
      if(head.getNext() == null)
         return null;
      Object obj = head.getNext().getValue();
      DLNode first = head;
      first = first.getNext();
      head.setNext(first.getNext());
      first.getNext().setPrev(head);
      return obj;
   }
 
  
   /* returns and removes the last element in this list, or
       returns null if the list is empty  */
   public Object removeLast()
   {
      if(head.getNext() == null)
         return null;
      Object obj = head.getPrev().getValue();
      DLNode abc = head;
      for(int x= 0; x <= size - 1; x++)
         abc = abc.getNext();
      abc.getPrev().setNext(head);
      head.setPrev(abc.getPrev());
      size--;
      return obj;
   }
//    
//    /*  returns a String with the values in the list in a 
//        friendly format, for example   [Apple, Banana, Cucumber]
//        The values are enclosed in [], separated by one comma and one space.
//     */
   public String toString()
   {
      String x = "[";
      DLNode y = head;
      for(int a = 0; a < size-1; a++)
      {
         y = y.getNext();
         x += y.getValue();
         x += ", ";
      }
      y = y.getNext();
      x += y.getValue();
      return x + "]";
   }
}