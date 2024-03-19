/*
*  HW2_DoublyLinkedList.java
*    採用首尾虛節點設計的雙向鏈結清單，可減少增刪例外情況。共實作如下方法，
*     加首元素，刪尾元素，刪中間元素，列印清單，及測試。
*
* > java HW2_DoublyLinkedList
* 輸出結果補在此處
*
*/
/*
HW2個人作業：雙向鏈結清單 W4~W6. 3/27 (三)

課本雙向鏈結清單內含長度及首尾節點三欄位，
其設計雖有利於尾部刪元素運算，卻有許多例外情況要考量，容易出錯。
請為其變更設計，增加首尾虛節點。
實作如下方法，加首元素，刪尾元素，刪中間元素，列印清單，及測試。
*/
package exercise;
public class HW2_DoublyLinkedList
{
   int length;  // 長度
   Node head;    // 首節點指標
   Node tail;    // 尾節點指標

   class Node
   {
      int data; // 元素
      Node prev; // 上個節點指標
      Node next; // 下個節點指標
      
      
      // 回傳節點元素
      public String toString()
      {
        return String.valueOf(data);
      }
   }

   // 建立鏈結清單,有首尾虛節點,初始長度0
   public HW2_DoublyLinkedList()
   {
      head = new Node();
      tail = new Node();

      head.data = tail.data = Integer.MIN_VALUE;
      head.prev = tail.next = null;
      head.next = tail;
      tail.prev = head;
      
      length = 0;
   }

   // 於清單頭位置加入element資料
   public void addFirst(int element)
   {
      Node n = new Node();

   }

   // 於清單尾位置刪除元素，
   // 成功回傳刪除尾元素；失敗回傳Integer.MIN_VALUE
   public int removeLast()
   {
     return Integer.MIN_VALUE; 
   }
     
   // 於清單index位置刪除元素
   // 成功回傳刪除元素；失敗回傳Integer.MIN_VALUE
   public int remove(int index)
   {
     return Integer.MIN_VALUE; 
   }
  
   // 回傳清單內容
   public String toString()
   {
     return "HW2_DoublyLinkedList: []";
   }

   // 測試程式
   public static void main(String args[])
   {
     // 清單測試
     // 從頭加10個元素,再隨機從中間或尾部刪除元素,直到失敗為止
     // 過程隨時列印清單內容,供確認
   }
}
