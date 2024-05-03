/*
*  G6_BinaryTree.java
*    區別樹及節點物件，不用泛型，節點只放整數。共實作如下方法，
*     建樹,前序,中序,樹長全否,及測試方法。
*
* > java G6_BinaryTree
* 輸出結果補在此處
*
*/
/*
G6分組練習: 還原二分樹   W11~W12. 5/08 (三)

參考HW4二分樹設計，還原一棵二分樹,
存放6個整數,滿足如下兩種走訪順序,
  前序: 4,7,1,5,9,8
  中序: 1,5,7,4,8,9
撰寫如下建樹,前序,中序,樹長全否,及測試方法.
*/
package exercise;

public class G6_BinaryTree
{
  static class Node
  {
    Node parent, leftChild, rightChild;
    int value;
  }
  
  Node root;
   
  // 建立一棵二分樹,存放6個整數,滿足如下兩種走訪順序,
  //   前序: 4,7,1,5,9,8
  //   中序: 1,5,7,4,8,9
  public static G6_BinaryTree buildTree()
  {
     return null;
  }
   
  // 列印節點n為樹根的二分樹前序走訪順序
  public static void preOrder(Node n)
  {
  }

  // 列印節點n為樹根的二分樹中序走訪順序
  public static void inOrder(Node n)
  {
  }


  // 回傳二分樹t是否長全
  public static boolean isComplete(G6_BinaryTree t)
  {
     return false;
  }

  // 測試
  public static void main(String args[])
  {
    // 呼叫buildTree建立1棵二分樹t
    // 呼叫preOrder列印t的前序走訪順序
    // 呼叫inOrder列印t的中序走訪順序
    // 呼叫isComplete回傳t長全否
  }
}
