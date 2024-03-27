/*
*  HW3_OrderedList.java
*    採用虛首節點設計的單向有序鏈結清單，可減少增刪例外情況。共實作如下方法，
*     加元素，刪元素，列印清單，清單相等否，及測試。
*
* > java HW3_OrderedList
* 輸出結果補在此處
*
*/
/*
HW3個人作業：有序清單相等否  W6~W8. 4/10  (三)

課本有序清單W06: OrderedList.java，內含長度及首節點2欄位，
其設計有許多例外情況要考量，容易出錯。
請參考有序清單簡化版W06: MyOrderedList.java，為其變更設計，
增加虛首節點，並改成順序由大排到小。
實作如下方法，加元素，刪元素，列印清單，清單相等否，及測試。
*/
package exercise;
public class HW3_OrderedList    // 單向鏈結有序清單，由大排到小
{
   int length;  // 長度
   Node head;    // 首節點指標

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

   // 建立有序鏈結清單,有虛首節點,初始長度0
   public HW3_OrderedList()
   {
     Node n = new Node();
     n.data = Integer.MAX_VALUE;  // 由大排到小，虛首節點放最大元素
     n.next = null;
     
     head = n;
     length = 0;
   }

   // 清單加入value元素，維持由大到小排序
   public void add(int value)
   {   
   }
   
   // 清單刪除value元素
   // 成功回傳刪除元素；失敗回傳Integer.MIN_VALUE
   public int remove(int value)
   {
     return Integer.MIN_VALUE;
   }
   
   // 回傳清單內容
   public String toString()
   {
     return "<HW3_OrderedList: >";
   }
   
   // 回傳ol1, ol2兩清單長度及元素順序相等否
   public static boolean equals(HW3_OrderedList ol1, HW3_OrderedList ol2)
   {
     return false;
   }
 
   // 測試程式
   public static void main(String args[])
   {
     // 清單測試
     // 依不同順序加元素1到9，分別製作清單1及清單2，兩者長度相等
     // 再從清單1隨機刪除元素1-10，直到清單變空為止
     // 增刪過程隨時列印清單1及清單2內容，及兩者相等否結果,供確認
   }
}
