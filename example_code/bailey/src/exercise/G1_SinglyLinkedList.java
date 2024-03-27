/*
* G1_SinglyLinkedList.java
*   單向鏈結清單內含長度、首節點、尾節點三欄位，
*   添加首元素和尾元素的測試場景。
*
* > java G1_SinglyLinkedList
*/
/*
G1分組練習: 單向鏈結清單 W3~W4. 3/13 (三)

課本單向鏈結清單內含長度及首節點兩欄位，
其設計不利於尾部增刪元素運算。
請為其變更設計，增加尾節點欄位。
評估在此首尾節點設計下，清單新增節點效能。
以分別添加首尾元素1百萬次為例,
列印新增首元素及尾元素的平均時間。

補充題:
1.為清單撰寫是否包含某元素方法contains
2.為清單撰寫特定位置查改元素方法get,set
3.為清單撰寫首尾找元素方法indexOf,lastIndexOf
4.為清單撰寫刪除首元素,尾元素方法removeFirst,removeLast
5.為清單撰寫特定位置增刪元素方法add,remove
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
