/*
* G5_SinglyLinkedList.java
*   單向鏈結清單內含長度、首節點、尾節點三欄位，並採用虛節點設計，
*   實作匯入元素，匯出元素，列印清單，反轉清單及測試。
*
* > java G5_SinglyLinkedList
* 輸出結果補在此處
*/
/*
G5分組練習: 清單反轉及匯出入 W7~W8. 4/10 (三)

課本單向鏈結清單 W03:SinglyLinkedList.java，內含長度及首節點兩欄位，
其設計不利於尾部增加元素運算。
請為其變更設計，增加尾節點欄位，虛首節點，虛尾節點。
實作如下方法，匯入元素，匯出元素，列印清單，反轉清單及測試。

補充題:
1.為清單撰寫是否包含某元素方法contains
2.為清單撰寫特定位置查改元素方法get,set
3.為清單撰寫首尾找元素方法indexOf,lastIndexOf
4.為清單撰寫增刪首元素,尾元素方法addFirst,addLast,removeFirst,removeLast
5.為清單撰寫特定位置增刪元素方法add,remove
6.為清單撰寫複製,相等否方法clone,equals
*/
package exercise;
import java.util.Collection;

public class G5_SinglyLinkedList  // 單鏈結清單
{
   int length;  // 長度
   Node head;    // 首節點指標
   Node tail;    // 尾節點指標

   class Node // 節點
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
   public G5_SinglyLinkedList()
   {
      head = new Node();  // 虛首節點
      tail = new Node();  // 虛尾節點

      head.data = tail.data = Integer.MIN_VALUE;      
      head.next = tail;
      tail.next = null;

      length = 0;
   }
   
   // 將co收藏內元素循序加入清單尾
   public void importFrom(Collection co)
   {
   }
   
   // 回傳清單元素循序匯出於的新收藏容器內
   public Collection export()
   {
     return null;
   }
   
   // 回傳清單內容字串
   public String toString()
   {
     return "<G5_SinglyLinkedList: >";   
   }
   
   // 將原清單節點順序反轉
   public void reverse()
   {
   
   }
   
   // 測試程式
   public static void main(String args[])
   {
     // 清單測試
     // 隨意建立由範圍1到10共10個元素組成的收藏容器
     // 將收藏容器元素匯入空清單
     // 將清單反轉
     // 將清單元素匯出於另一收藏容器
     // 隨時列印匯入,反轉,匯出前後的清單,收藏內容,供確認
   }
}
