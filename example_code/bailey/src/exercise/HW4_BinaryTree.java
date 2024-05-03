/*
*  HW4_BinaryTree.java
*    區別樹及節點物件，不用泛型，節點只放整數。共實作如下方法，
*     手足節點否，節點深度，後代節點，樹長滿否，及測試。
*
* > java HW4_BinaryTree
* 輸出結果補在此處
*
*/
/*
HW4個人作業：二分樹方法  W10~W13. 5/15 (三)

課本二分樹 W10:BinaryTree.java，記錄根節點值，左小孩、右小孩、親節點共4個欄位，
其設計不分樹及節點，樹物件其實記錄的是樹根節點的相關資訊。
請變更設計如下，區別樹及節點物件，不用泛型元素，節點固定放整數元素。
實作如下方法，手足節點否，節點深度，後代節點，樹長滿否，及測試。
*/
package exercise;
import java.util.Collection;

public class HW4_BinaryTree
{
  static class Node
  {
    Node parent, leftChild, rightChild;
    int value;
  }
  
  Node root;
   
  // 回傳節點n1及n2是否為手足節點，擁有共同親節點
  public static boolean isSibling(Node n1, Node n2)
  {
     return false;
  }
   
  // 回傳節點n到其樹根的深度(邊個數)
  public static int depth(Node n)
  {
     return -1;
  }

  // 回傳節點n為樹根的二分樹所有後代節點，包含自己
  public static Collection<Node> descendants(Node n)
  {
     return null;
  }

  // 回傳二分樹t是否長滿
  public static boolean isFull(HW4_BinaryTree t)
  {
     return false;
  }

  // 測試
  public static void main(String args[])
  {
    // 各建1棵長滿樹及不長滿樹
    // 任挑參數，測試各方法正確否
  }
}