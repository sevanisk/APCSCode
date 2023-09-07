// Name: Sophia Evanisko
// Date: 2/20/19

import java.util.*;

/* Practice with a Binary Search Tree. Uses TreeNode.
 * Prompt the user for an input string.  Build a BST 
 * from the letters. Display it as a sideways tree.  
 * Prompt the user for a target and delete that node, 
 * if it exists. Show the updated tree.
 */
public class BinarySearchTreeDelete
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Input string: ");
      String s = sc.nextLine();
                           //Case 1a:     ECSBPWANR 
                           //Case 1b:     N
                           //Case 2a:     SNTPOR    
                           //Case 2b:     HBRNVJSZIK  
                           //case 2c:     NFAKG
                           //case 2d:     NSPQX
                           //Case 3.a:    DBNACFSEJHM 
                           //Case 3.b:    DBNACFSEJH 
                           //on the handout: HDJAGKFEOLTMNSU
      TreeNode root = buildTree( null, s );
      System.out.println( display(root, 0) );
      System.out.print("Delete? ");
      String target = sc.next();
      if( contains( root, target ) )
      {
         root = delete( root, target );
         System.out.println("\n" + target+" deleted.");
      }
      else
         System.out.println("\n" + target+" not found");
      System.out.println( display(root, 0) );
   }
   
   public static TreeNode buildTree(TreeNode t, String s)
   {
      for(int k = 0; k < s.length(); k++)
         t = insert(t, "" + s.charAt(k));
      return t;
   }
	
   /* Recursive algorithm to build a BST:  if the node is 
    * null, insert the new node.  Else, if the item is less, 
    * set the left node and recur to the left.  Else, if the 
    * item is greater, set the right node and recur to the right.   
	 */
   public static TreeNode insert(TreeNode t, String s)
   {  	
      if(t==null)
         return new TreeNode(s);
      if(s.compareTo(t.getValue()+"") <= 0)
         t.setLeft(insert(t.getLeft(), s));
      else
         t.setRight(insert(t.getRight(), s));
      return t;
   }
   
   private static String display(TreeNode t, int level)
   {
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
   
   public static boolean contains( TreeNode current, String target )
   {
      while(current != null)
      {
         int compare = target.compareTo((String)current.getValue());
         if( compare == 0 )
            return true;
         else if(compare<0)
            current = current.getLeft();
         else if(compare>0)
            current = current.getRight();
      }
      return false;
   }
   
   public static boolean isLeaf(TreeNode t)
   {
      if(t.getRight() == null && t.getLeft() == null)
         return true;
      return false;
   }

   public static TreeNode findP(TreeNode root, TreeNode node)
   {
      TreeNode lh = null, rh = null;
      if (null == root)
         return null;
   
      if (root.getLeft() == node || root.getRight() == node)
         return root;
   
      lh = findP(root.getLeft(), node);
      rh = findP(root.getRight(), node);
   
      return lh != null ? lh : rh;
   }
   
   public static TreeNode find(TreeNode t, Comparable x)
   {
      TreeNode s = t;
      if(s == null)
         return null;
      if(x.compareTo((Comparable)s.getValue()) < 0)
         s = find(s.getLeft(), x);
      if(x.compareTo((Comparable)s.getValue()) > 0)
         s = find(s.getRight(), x);
      return s;
   }
   
   public static TreeNode delete(TreeNode current, String target)
   {
      TreeNode root = current;  //don't lose the root!
      TreeNode parent = null;
      while(current !=null)
      {
         boolean leftP = false;;
         int compare = target.compareTo((String)current.getValue());
        // ------->  your code goes here
         current = find(current, target);
         parent = findP(root, current);
         
         boolean l = isLeaf(current);
         
                  
         //1b
         if(compare == 0 && l)
            return null;
            
         //1a
         if(parent != null && parent.getLeft() != null && parent.getLeft().getValue().equals(target))
            leftP= true;
         if(l && compare != 0)
         {
            if(leftP)
               parent.setLeft(null);
            else
               parent.setRight(null);
            return root;
         }
         
            
         //2a
         else if(compare != 0 && !l && current.getLeft() != null && current.getRight() == null)
         {
            if(leftP)
               parent.setLeft(current.getLeft());
            else
               parent.setRight(current.getLeft());
            return root;
         }
         
         //2b
         else if(compare !=0 && !l && current.getRight() != null && current.getLeft() == null)
         {
            if(leftP)
               parent.setLeft(current.getRight());
            else
               parent.setRight(current.getRight());
            return root;
         }
         
         //2c
         else if(compare == 0 && !l && root.getLeft() != null)
            return current.getLeft();
         
         //2d
         else if(compare == 0 && !l && root.getRight() != null)
            return current.getRight();
            
         //3a
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
            return root;
         }
         
         
         //3b fix
         else if(isLeaf(minRight(current)))
         {
            current.setValue(minRight(current.getRight()).getValue());
            parent = findP(root, minRight(current.getRight()));
            if(!isLeaf(current))
            {
               if(parent.getLeft().getValue().equals(target))
                  parent.setLeft(null);
               else
                  parent.setRight(null);
            }
            return root;
         }
        
      
      
      
      
      }
      return root;  //never reached
   }
   
   public static TreeNode maxLeft(TreeNode t)
   {
      if(t == null)
         return null;
      if(t.getRight() == null)
         return t;
      return maxLeft(t.getRight());
   } 
   
   public static TreeNode minRight(TreeNode t)
   {
      if(t == null)
         return null;
      if(t.getLeft() == null)
         return t;
      return maxLeft(t.getLeft());
   } 
}
    
