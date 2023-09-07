
// Name:Sophia Evanisko
// Date:2/9/19

import java.util.*;

public class TreeLab
{
   public static TreeNode root = null;
   public static String s = "XCOMPUTERSCIENCE";
   //public static String s = "XThomasJeffersonHighSchool"; 
   //public static String s = "XAComputerScienceTreeHasItsRootAtTheTop";
   //public static String s = "XA";   //comment out lines 44-46 below
   //public static String s = "XAF";  //comment out lines 44-46 below
   //public static String s = "XAFP";  //comment out lines 44-46 below
   //public static String s = "XDFZM";  //comment out lines 44-46 below 
   
   public static void main(String[] args)
   {
      root = buildTree( root, s );
      System.out.print( display( root, 0) );
   
      System.out.print("\nPreorder: " + preorderTraverse(root));
      System.out.print("\nInorder: " + inorderTraverse(root));
      System.out.print("\nPostorder: " + postorderTraverse(root));
   
      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Only children = " + countOnlys(root));
      System.out.println("Grandparents = " + countGrandParents(root));
   
      System.out.println("\nHeight of tree = " + height(root));
      System.out.println("Longest path = " + longestPath(root));
      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));	
   
      System.out.println("\nBy Level: ");
      System.out.println(displayLevelOrder(root));
   }
 
   public static TreeNode buildTree(TreeNode root, String s)
   {
      root = new TreeNode("" + s.charAt(1), null, null);
      for(int pos = 2; pos < s.length(); pos++)
         insert(root, "" + s.charAt(pos), pos, 
            (int)(1 + Math.log(pos) / Math.log(2)));
      insert(root, "A", 17, 5); 
      insert(root, "B", 18, 5); 
      insert(root, "C", 37, 6); //B's right child
      return root;
   }

   public static void insert(TreeNode t, String s, int pos, int level)
   {
      TreeNode p = t;
      for(int k = level - 2; k > 0; k--)
      {
         if((pos & (1 << k)) == 0)
            p = p.getLeft();
         else
            p = p.getRight();
      }
      if((pos & 1) == 0)
         p.setLeft(new TreeNode(s, null, null));
      else
         p.setRight(new TreeNode(s, null, null));
   }
   
   private static String display(TreeNode t, int level)
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
   
   //TRAVERSALS
   public static String preorderTraverse(TreeNode t)
   { 
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += t.getValue() + " ";              //preorder visit
      toReturn += preorderTraverse(t.getLeft());   //recurse left
      toReturn += preorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }
   
   public static String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return " ";
      toReturn += inorderTraverse(t.getLeft()); 
      toReturn += t.getValue();
      toReturn += inorderTraverse(t.getRight());
      
      return toReturn;
   }

   public static String postorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += postorderTraverse(t.getLeft()); 
      toReturn += postorderTraverse(t.getRight());
      toReturn += t.getValue() + " ";
      return toReturn;
   }
   
   //COUNTING NODES
   public static int countNodes(TreeNode t)
   {
      int count = 0;
      if(t == null)
         return 0;
      count++;
      count += countNodes(t.getLeft());
      count += countNodes(t.getRight());
      return count;
   }
   
   public static int countLeaves(TreeNode t)
   {
      int leaves = 0;
      if(t == null)
         return 0;
      if(t.getLeft() == null && t.getRight() == null)
         return 1;
      else
      {
         leaves += countLeaves(t.getLeft());
         leaves += countLeaves(t.getRight());
      }
      return leaves;
   }
   
   /*  there are clever ways and hard ways to count grandparents  */ 
   public static int countGrandParents(TreeNode t)
   {
      int x= 0;
      if(t == null)
         return 0;
      else if (height(t) == 1)
         return 1;
      x += countGrandParents(t.getLeft());
      x += countGrandParents(t.getRight());
      return x;
   }
   
   
   //fixxxxxx
   public static int countOnlys(TreeNode t)
   {

      int only = 1;
      if(t == null)
         return -1;
      else if((t.getLeft() == null && t.getRight() != null) || (t.getLeft() != null && t.getRight() == null))
         return 1;
      else
      {
         only += countOnlys(t.getLeft());
         only += countOnlys(t.getRight());
      }
      return only;
   }
   
   /* Returns the max of the heights on both sides of the tree
	 */
   //STATISTICAL INFO
   public static int height(TreeNode t)
   {
      if(t == null)
         return -1;
      return 1 + Math.max(height(t.getLeft()), height(t.getRight()));
   }
   
   /* Returns the max of sum of heights on both sides of tree
	 */  
    //FIX 
   public static int longestPath(TreeNode t)
   {
      int h1 = height(t.getLeft()) + 1;
      int h2 = height(t.getRight()) + 1;
      return h1 + h2;
   }
   
   /*  Object must be cast to Comparable in order to call .compareTo  
    */
   @SuppressWarnings("unchecked")
   public static Object min(TreeNode t)
   {
      Comparable<Object> r, lr, rr;
   
      if(t == null)
         return " ";
      else
      {
         r = (Comparable<Object>)(t.getValue());
         lr = (Comparable<Object>)(min(t.getLeft()));
         rr = (Comparable<Object>)(min(t.getRight()));
         if(r.compareTo(lr) > 0 && !lr.equals(" "))
            r = lr;
         if(r.compareTo(rr) > 0 && !rr.equals(" "))
            r = rr;
      }
      return r;
   }
   //    
   //    /*  Object must be cast to Comparable in order to call .compareTo  
   //     */
   //    @SuppressWarnings("unchecked")
   public static Object max(TreeNode t)
   {
      Comparable<Object> r, lr, rr;
      if(t == null)
         return " ";
      r = (Comparable)t.getValue();
      lr = (Comparable)max(t.getLeft());
      rr = (Comparable)max(t.getRight());
      if(lr.compareTo(r) > 0)
         r = lr;
      if(rr.compareTo(r) > 0)
         r = rr;
      return r;
   }
      
   /* This method is not recursive.  Use a local queue
    * to store the children of the current TreeNode.
    */
   public static String displayLevelOrder(TreeNode t)
   {
      Queue<TreeNode> q = new LinkedList<TreeNode>();
      TreeNode t1 = t;
      String s = "";
      while(t1 != null)
      {
         s += t1.getValue();
         q.add(t1.getLeft());
         q.add(t1.getRight());
         t1 = q.remove();
         while(t1 == null && !q.isEmpty())
            t1 = q.remove();
      }
   
      return s;
   }
}


/***************************************************
    ----jGRASP exec: java TreeLab
 		  	E
 		E
 			C
 	M
 			N
 		T
 			E
 C
 			I
 		U
 			C
 	O
 			S
 					C
 				B
 		P
 				A
 			R
 
 Preorder: C O P R A S B C U C I M T E N E C E 
 Inorder: R A P B C S O C U I C E T N M C E E 
 Postorder: A R C B S P C I U O E N T C E E M C 
 
 Nodes = 18
 Leaves = 8
 Only children = 3
 Grandparents = 5
 
 Height of tree = 5
 Longest path = 8
 Min = A
 Max = U
 
 By Level: 
 COMPUTERSCIENCEABC    
 *******************************************************/
