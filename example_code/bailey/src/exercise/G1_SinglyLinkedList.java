/*
 */
package exercise;
public class G1_SinglyLinkedList
{
   int length;  // 長度
   Node head;    // 首元素指標
   Node tail;    // 尾元素指標

   class Node
   {
      int data;
      Node next;
   }

   // 建立鏈結清單,初始長度0
   public G1_SinglyLinkedList()
   {
      head = tail = null;
      length = 0;
   }

   // 於清單頭位置加入element資料
   public void addFirst(int element)
   {
      Node n = new Node();

   }

   // 於清單尾位置加入element資料
   public void addLast(int element)
   {
      Node n = new Node();

   }

   // 測試程式
   public static void main(String args[])
   {
      // 於頭位置加入從1到1,000,000的整數，
      // 統計列印加入1個首元素平均時間

      // 於尾位置加入從1到1,000,000的整數，
      // 統計列印加入1個尾元素平均時間

   }
}
