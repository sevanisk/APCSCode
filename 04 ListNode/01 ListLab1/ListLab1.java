// Name: Sophia Evanisko    
// Date: 11/12/21018
import java.util.*;
public class ListLab1
{
   public static ListNode pointer1;
   public static void main(String[] args)
   {
      ListNode head = new ListNode("hello", null);
      head = new ListNode("foo", head);
      head = new ListNode("boo", head);
      head = new ListNode("nonsense", head);
      head = new ListNode("computer",
         	new ListNode("science",
         		new ListNode("java",
         			new ListNode("coffee", head)
         		)
         	)
         );
      print(head);
      print(head);
      //ListNode r = rest(head);
      //print(r);
      /**** uncomment the code below for ListLab1 Extension  ****/
      System.out.println("First = " + first(head));
      System.out.println("Second = " + second(head));
      ListNode p = pointerToLast(head);
      System.out.println("Pointer to Last = " + p.getValue()+ " at " + p);
      ListNode c = copyOfLast(head);
      System.out.println("Copy of Last =    " + c.getValue()+ " at " + c);	
      Scanner in = new Scanner(System.in);
      System.out.print("Insert what? ");
      String x = in.next();
      head = insertFirst(head, x);
      head = insertLast(head, x);
      print(head);
   }
   
   public static ListNode copyNode(ListNode arg)
   {
      if(arg == null)
         return null;
      return new ListNode(arg.getValue(), arg.getNext());
   }
   
   public static ListNode copyList(ListNode arg)
   {
      if(arg == null)
         return null;
      return new ListNode(arg.getValue(), copyList(arg.getNext()));
   }
   
   static int x = 0;
   public static ListNode rest (ListNode h)
   {
      if(x == 0)
         h = h.getNext();
         
      if(h.getNext() == null )
         return h;
   
      x++;
      rest(h.getNext());
      return h;
   }
   
   public static void print(ListNode head)
   {
      System.out.print("[");
      while(head != null)
      {
         System.out.print(head.getValue());
         head = head.getNext();
         if(head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   }

   public static Object first(ListNode head)
   {
      if(head == null)
         return null;
      return head.getValue();
   }
   
   public static Object second(ListNode head)
   {
      ListNode pointer;
      pointer =copyList(head);
      if(pointer.getNext() == null)
         return null;
      return pointer.getNext().getValue();
   }
   
   public static ListNode pointerToLast(ListNode head)
   {
      if(head.getValue() == null)
         return null;
      ListNode pointer = copyList(head);
      while(pointer.getNext().getNext() != null)
         pointer = pointer.getNext();
      return pointer.getNext();
   }
   
   //fix
   public static ListNode copyOfLast(ListNode head)
   {
      if(head == null)
         return null;
      ListNode pointer = copyList(head);
      while(pointer.getNext().getNext() != null)
         pointer = pointer.getNext();
      return new ListNode(pointer.getNext().getValue(), null);
   }
   
   public static ListNode insertFirst(ListNode head, Object arg)
   {
      if(arg == null)
         return head;
      return new ListNode(arg, head.getNext()); 
   }
   
   public static ListNode insertLast(ListNode head, Object arg)
   {
      if(arg == null)
         return head;
      ListNode pointer = head;
      if(head == null)
         return new ListNode(arg, null);
      else
         while(pointer.getNext().getNext() != null)
         {
            pointer = pointer.getNext();
         }
      pointer.setNext(copyNode(new ListNode(arg, null)));
      return head; 
   }
   /* enter your code here */
      
      
}

/*****************************************
 
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 First = computer
 Second = science
 Pointer to Last = hello at ListNode@18767ad
 Copy of Last =    hello at ListNode@a7bdcd
 Insert what? abc
 [abc, computer, science, java, coffee, nonsense, boo, foo, hello, abc]
 

  **********************************************/
