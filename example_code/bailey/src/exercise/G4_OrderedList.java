/*
*  G4_OrderedList.java
*    採用虛首尾節點設計的雙向鏈結有序清單，可減少增刪例外情況。共實作如下方法，
*     加元素，刪元素，列印清單，清單複製，最大元素，及測試。
*
* > java G4_OrderedList
* 輸出結果補在此處
*
*/
/*
G4分組練習: 清單複製及求最大    W6~W8. 4/10 (三)

課本有序清單W06: OrderedList.java，內含長度，首節點2欄位，
其設計無法快速查找最大值,也有許多例外情況要考量，容易出錯。
請參考有序清單簡化版W06: MyOrderedList.java，為其變更設計，
增加尾節點，虛首節點，虛尾節點。
實作如下方法，加元素，刪元素，列印清單，清單複製，最大元素，及測試。
*/
package exercise;
public class G4_OrderedList  // 雙向鏈結有序清單，由小排到大
{
   int length;  // 長度
   Node head;    // 首節點指標
   Node tail;    // 尾節點指標

   class Node
   {
      int data; // 元素
      Node prev; // 上一個節點指標
      Node next; // 下一個節點指標
      
      // 回傳節點元素
      public String toString()
      {
        return String.valueOf(data);
      }
   }

   // 建立有序鏈結清單,有虛首尾節點,初始長度0
   public G4_OrderedList()
   {
    head = new Node();
    tail = new Node();

    // 由小排到大，虛首/尾節點放最小/大元素
    head.data = Integer.MIN_VALUE;
    tail.data = Integer.MAX_VALUE;
    
    head.prev = tail.next = null;
    head.next = tail;
    tail.prev = head;
    length = 0;
   }

   // 清單加入value元素，維持由小到大排序
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
     return "<G4_OrderedList: >";
   }
   
   // 回傳一個長度及順序一模一樣的有序清單
   public G4_OrderedList clone()
   {
     return null;
   }

   // 回傳最大元素，若無回傳Integer.MIN_VALUE
   public int top()
   {
     return Integer.MIN_VALUE;
   }
 
   // 測試程式
   public static void main(String args[])
   {
     // 清單測試
     // 依隨意順序加元素1到9，製作有序清單
     // 拷貝成另一份清單作備份，列印備份清單
     // 再將原清單隨機刪除元素1-10，直到清單變空為止
     // 再列印備份清單一次，確認內容未異動
     // 增刪過程隨時列印清單內容,及當時最大元素,供確認
   }
}
