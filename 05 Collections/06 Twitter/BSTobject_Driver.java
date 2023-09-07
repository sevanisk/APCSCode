
// Name: Sophia Evanisko
// Date: 2/22/19

import java.util.*;
import java.io.File;
import java.util.Scanner;

public class BSTobject_Driver
{
   public static BSTobject<String> tree = null, tree2 = null;
   public static BSTobject<Widget> treeOfWidgets = null;
   public static int numberOfWidgets = 10;
   public static void main( String[] args ) 
   {
      // Day one 
      tree = new BSTobject<String>();
      tree = build(tree, "T");
      System.out.print(tree);
      System.out.println("Size: " + tree.size());
      System.out.println("Is empty: "+ tree.isEmpty());
      System.out.println(""); 
      
      // Day two
      tree2 = new BSTobject<String>();
      Scanner myString = new Scanner(System.in);
      System.out.println("What string of letters?");
      tree2 = build(tree2,  myString.next());
      System.out.println(tree2);
      System.out.println("Size: " + tree2.size());
      
      // Day two, Widgets  
      treeOfWidgets = new BSTobject<Widget>();
      treeOfWidgets = build(treeOfWidgets,  new File("widgets.txt"));
      System.out.println(treeOfWidgets);
      Scanner myW = new Scanner(System.in);
      System.out.println("How many cubits?");
      int cub = myW.nextInt();
      System.out.println("How many hands?");
      Widget target = new Widget(cub, myW.nextInt());
      if(treeOfWidgets.contains(target))
      {
         treeOfWidgets.delete(target);
         System.out.println(treeOfWidgets);
         System.out.println("Size: " + treeOfWidgets.size());
      }    
      else
         System.out.println("not found");    
      // Next, fill your BST with 10 Widget objects from widgets.txt.  Display 
      // the tree. Then prompt the user to enter cubits and hands.  If the tree 
      // contains that Widget, delete it, of course restoring the BST order. 
      // Display the new tree and its size. If the tree does not contain that 
      // Widget, print "Not found".*/
   
      // Day three -- AVL tree  -----------------------
   }
  
   /* Build the tree for Strings, Day 1
    */
   public static BSTobject<String> build(BSTobject<String> tree, String str)
   {
      String[] str2 = str.split("");
      for(String a: str2)
         tree.add(a); 
      return tree;        
   }
   
   /* Build the tree for Widgets, Day 2
    */
   public static BSTobject<Widget> build(BSTobject<Widget> tree, File file)
   {
      Scanner infile = null;
      try{
         infile = new Scanner( file  );
      }
      catch (Exception e)
      {
         System.out.println("File not found.");
      }        
      
      for(int i = 0; i < numberOfWidgets; i++)   
      {
         tree.add(new Widget(infile.nextInt(), infile.nextInt()));
      }
      infile.close();
      return tree;
   }
}

interface BSTinterface<E extends Comparable<E>>
{

   public E add( E element );            //returns the object
   public boolean contains( E element );
   public boolean isEmpty();
   public E delete( E element );  //fix       //returns the object, not a Node<E>
   public int size();
   public String toString();
}

class BSTobject <E extends Comparable<E>> implements BSTinterface<E>
{ 
   // Declare 2 fields 
   private Node<E> root;
   private int size;
   // Create a default constructor
    
   //instance methods
   public E add( E obj )
   {
      root = add( root, obj );
      size++;
      return obj;
   }
   
   //recursive helper method
   private Node<E> add( Node<E> t, E obj )
   {
      if(t == null)
         return new Node(obj);
      else if(obj.compareTo((E)t.getValue()) <= 0)
      {
         t.setLeft(add(t.getLeft(), obj));
         return t;
      }
      else
      {
         t.setRight(add(t.getRight(), obj));
         return t;
      }
      
   }
   
   /* Implement the interface here.  Use TreeNode as an example,
    * but root is a field. You need add, contains, isEmpty, 
    * delete, size, and toString.  
    */
    
   public boolean contains( E element )
   {
      Node<E> current = root;
      while(current != null)
      {
         int compare = element.compareTo((E)current.getValue());
         if( compare == 0 )
            return true;
         else if(compare<0)
            current = current.getLeft();
         else if(compare>0)
            current = current.getRight();
      }
      return false;
   }
   
   public boolean isEmpty()
   {
      if(size() == 0)
         return true;
      return false;
   }


   public E delete(E target)
   {
      Node<E> current = root;  //don't lose the root!
      Node<E> parent = null;
      while(current !=null)
      {
         boolean leftP = false;;
         int compare = target.compareTo((E)current.getValue());
        // ------->  your code goes here
         current = find(current, target);
         parent = findP(root, current);
         
         boolean l = isLeaf(current);
         
                  
         //1b
         if(compare == 0 && l)
         {
            root= null;
            return target;
         }
            
         //1a
         if(parent != null && parent.getLeft() != null && parent.getLeft().getValue().equals(target))
            leftP= true;
         if(l && compare != 0)
         {
            if(leftP)
               parent.setLeft(null);
            else
               parent.setRight(null);
            return target;
         }
         
            
         //2a
         else if(compare != 0 && !l && current.getLeft() != null && current.getRight() == null)
         {
            if(leftP)
               parent.setLeft(current.getLeft());
            else
               parent.setRight(current.getLeft());
            return target;
         }
         
         //2b
         else if(compare !=0 && !l && current.getRight() != null && current.getLeft() == null)
         {
            if(leftP)
               parent.setLeft(current.getRight());
            else
               parent.setRight(current.getRight());
            return target;
         }
         
         //2c
         else if(compare == 0 && !l && root.getLeft() != null && root.getRight() == null)
         {
            current = current.getLeft();
            return target;
         }
         
         //2d
         else if(compare == 0 && !l && root.getRight() != null && root.getLeft() == null)
         {
            current = current.getRight();
            return target;
         }
         
         
         //3b
         else if(isLeaf(minRight(current)))
         {
            current.setValue(minRight(current.getRight()).getValue());
            parent = current;
            Widget target2 = (Widget)current.getValue();
           // parent = findP(root, minRight(current.getRight()));
            if(!isLeaf(current))
            {
               if(parent.getRight().getValue().equals(target2))
                  parent.setRight(null);
               else
                  parent.setLeft(null);
            }
            return target;
         }
         
                 //3a fix
         else if(isLeaf(maxLeft(current)))
         {
            current.setValue(maxLeft(current.getLeft()).getValue());
            parent = findP(root,maxLeft(current.getLeft()));
            if(!isLeaf(current))
            {
               if(parent.getLeft().getValue().equals(target))
                  parent.setLeft(null);
               else
                  parent.setRight(null);
            }
            return target;
         }
      
      
      
      
      }
      return target;  //never reached
   }


   public int size()
   {
      int x = countNodes(root);
      return x;
   }

   public int countNodes(Node<E> t)
   {
      int count = 0;
      if(t == null)
         return 0;
      count++;
      count += countNodes(t.getLeft());
      count += countNodes(t.getRight());
      return count;
   }
    
    
   public String toString()
   {
      return display(root, 0);
   }
    
   private String display(Node<E> t, int level)
   {
      // turn your head towards left shoulder visualize tree
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   public boolean isLeaf(Node<E> t)
   {
      if(t.getRight() == null && t.getLeft() == null)
         return true;
      return false;
   }

   public Node<E> findP(Node<E> root, Node<E> node)
   {
      Node lh = null, rh = null;
      if (null == root)
         return null;
   
      if (root.getLeft() == node || root.getRight() == node)
         return root;
   
      lh = findP(root.getLeft(), node);
      rh = findP(root.getRight(), node);
   
      return lh != null ? lh : rh;
   }
   
   public Node<E> find(Node<E> t, Comparable x)
   {
      Node s = t;
      if(s == null)
         return null;
      if(x.compareTo((Comparable)s.getValue()) < 0)
         s = find(s.getLeft(), x);
      if(x.compareTo((Comparable)s.getValue()) > 0)
         s = find(s.getRight(), x);
      return s;
   }

   public Node<E> maxLeft(Node<E> t)
   {
      if(t == null)
         return null;
      if(t.getRight() == null)
         return t;
      return maxLeft(t.getRight());
   } 
   
   public Node<E> minRight(Node<E> t)
   {
      if(t == null)
         return null;
      if(t.getLeft() == null)
         return t;
      return minRight(t.getLeft());
   } 
   /* Private inner class 
    */  
   private class Node<E>
   {
      // 3 fields 
      // 2 constructors, one-arg and three-arg
      //methods--Use TreeNode as an example. See Quick Reference Guide.
      private Object value; 
      private Node<E> left, right;
   
      public Node(Object initValue)
      { 
         value = initValue; 
         left = null; 
         right = null; 
      }
   
      public Node(Object initValue, Node<E> initLeft, Node<E> initRight)
      { 
         value = initValue; 
         left = initLeft; 
         right = initRight; 
      }
   
      public Object getValue()
      { 
         return value; 
      }
   
      public Node<E> getLeft() 
      { 
         return left; 
      }
   
      public Node<E> getRight() 
      { 
         return right; 
      }
   
      public void setValue(Object theNewValue) 
      { 
         value = theNewValue; 
      }
   
      public void setLeft(Node<E> theNewLeft) 
      { 
         left = theNewLeft;
      }
   
      public void setRight(Node<E> theNewRight)
      { 
         right = theNewRight;
      }
   }
}
