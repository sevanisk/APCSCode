// Name:  Sophia Evanisko            
// Date:  4/10/19
import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      File f = new File("message."+middlePart+".txt");
      Scanner sc = new Scanner(f); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      TreeNode myT = new TreeNode(null);
      while(huffLines.hasNextLine())
      {
         String source = huffLines.nextLine();
         myT = build(source, myT);
      }
      return myT;
   }
   
   public static TreeNode build( String source, TreeNode myT)
   {
      TreeNode root = myT;
      String path = "0";
      String letter = "a";
      if(source.length() < 2)
         return myT;
      else
      {
         letter = source.substring(0, 1);
         path = source.substring(1);
      }
      while(!path.isEmpty())
      {
         int lOrR = Integer.parseInt(path.substring(0,1));
         if(lOrR == 0 && myT.getLeft() == null)
         {
            myT.setLeft(new TreeNode(""));
            myT = myT.getLeft();
         } 
         else if (lOrR == 0)
            myT = myT.getLeft();
         else if(lOrR == 1 && myT.getRight() == null)
         {
            myT.setRight(new TreeNode(""));
            myT = myT.getRight();
         }
         else
            myT = myT.getRight();
         path = path.substring(1);
      }
      myT.setValue(letter);
      return root;
   
   }
   
   public static String dehuff(String text, TreeNode root)
   {
      TreeNode myT = root;
      String result = "";
      int path = 0;
      while(!text.isEmpty())
      {
         path = Integer.parseInt(text.substring(0,1));
         if(path == 0)
            myT = myT.getLeft();
         else if(path == 1)
            myT = myT.getRight();
         if(myT.getLeft() == null && myT.getRight() == null)
         {
            result += myT.getValue(); 
            myT = root;
         }
         text = text.substring(1);
      }
      return result;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
