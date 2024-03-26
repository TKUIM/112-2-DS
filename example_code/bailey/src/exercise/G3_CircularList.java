/*
* G3_CircularList.java
*   雙向鍵結循環清單，內含長度、尾節點指標兩欄位，並有尾虛節點設計。
*   實作添加首元素和尾元素的測試場景。
*
* > java G3_CircularList
* 輸出結果補在此處
* G3_CircularList: []
*/
/*
G3分組練習: 雙向鏈結循環清單 W5~W6. 3/27 (三)

課本單鏈結循環清單(W05:CircularList.java)，
內含長度及尾節點兩欄位，其設計在增刪上仍有諸多特例，也不利刪尾。
請為其變更設計，將單鏈結改成雙鏈結節點，並加入虛尾節點設計。
實作如下方法，加首元素，刪尾元素，查詢中間元素，列印清單，及測試。
*/
package exercise;

public class G3_CircularList
{
   int length;   // 長度
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

   // 建立鏈結清單,有尾虛節點,初始長度0
   public G3_CircularList()
   {
      tail = new Node();  // 尾虛節點

      tail.data = Integer.MIN_VALUE;
      tail.next = tail;
      tail.prev = tail;

      length = 0;
   }

   // 加頭元素e
   public void addFirst(int e)
   {
   }
   
   // 刪尾元素
   // 成功回傳其值，失敗回傳Integer.MIN_VALUE
   public int removeLast()
   {
      return Integer.MIN_VALUE;
   }
   
   // 取index位置的中間元素
   // 成功回傳其值，失敗回傳Integer.MIN_VALUE
   public int get(int index)
   {
      return Integer.MIN_VALUE;
   }
   
   // 回傳清單內容
   public String toString()
   {
      return "G3_CircularList: []";
   }
   
   public static void main(String args[])
   {
     // 清單測試
     // 從頭加10個元素
     // 詢問列印0-10位置的元素值    
     // 從尾刪除元素,直到清單變空，刪除失敗為止
     // 過程隨時列印清單內容,供確認
     
       G3_CircularList cl = new G3_CircularList();
       System.out.println(cl);
   }
}
