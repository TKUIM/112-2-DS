/*
* G2_SinglyLinkedList.java
*   單鍵結單向鏈結清單內含長度、首節點、尾節點三欄位，
*   實作添加首元素和尾元素的測試場景。
*
* > java G2_SinglyLinkedList
* 輸出結果補在此處
*/
/*
G2分組練習: 單向鏈結清單2 W4~W5. 3/20 (三)

課本單向鏈結清單內含長度及首節點兩欄位，
其設計不利於尾部增刪元素運算。
請為其變更設計，增加尾節點欄位。
實作如下方法，加中間元素，刪首元素，刪尾元素，列印清單，及測試。
*/
package exercise;
public class G2_SinglyLinkedList
{
   int length;  // 長度
   Node head;    // 首節點指標
   Node tail;    // 尾節點指標

   class Node
   {
      int data; // 元素
      Node next; // 下個節點指標
      
      // 回傳節點元素
      public String toString()
      {
        return String.valueOf(data);
      }
   }

   // 建立鏈結清單,初始長度0
   public G2_SinglyLinkedList()
   {
      head = tail = null;
      length = 0;
   }

   // 於清單index位置加入element資料
   public void add(int index, int element)
   {
      Node n = new Node();

   }

   // 於清單尾位置刪除元素，
   // 成功回傳刪除尾元素；失敗回傳Integer.MIN_VALUE
   public int removeFirst()
   {
     return Integer.MIN_VALUE; 
   }
   
   // 於清單尾位置刪除元素，
   // 成功回傳刪除尾元素；失敗回傳Integer.MIN_VALUE
   public int removeLast()
   {
     return Integer.MIN_VALUE; 
   }
   
   public String toString()
   {
     return "G2_SinglinlyLinkedList: []";
   }

   // 測試程式
   public static void main(String args[])
   {
     // 清單測試
     // 加10個元素,再隨機從頭或尾刪除元素,直到失敗為止
     // 過程隨時列印清單內容,供確認
   }
}
