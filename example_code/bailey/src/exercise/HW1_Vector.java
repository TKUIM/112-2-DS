/*
* HW1_Vector.java
*   提供動態伸縮容器功能，包括自動調整大小和元素插入/刪除功能
*   及添加和刪除元素的測試場景。
*
* > java HW1_Vector
*
*/
/*
HW1個人作業：向量製作 W2~W4. 3/13 (三)

相對於中括號陣列容器,其容量固定，不具伸縮彈性,
向量容器乃基於中括號陣列，並加了如下功能，方便使用:
1.區別用量及容量,允許容量內格子未使用,及容量不夠時自動擴充
2.提供元素連續放置功能,允許於合適位置,加入及刪除元素
        同時其位置後面的元素將自動後移及前移一格
請實作如下向量類別,並展示
其最差情況下,增刪元素時間的平均時間,及容量變化情形
*/
package exercise;

public class HW1_Vector
{
   int capacity;  // 容量
   int length;    // 用量
   int data[];    // 資料空間
   
   // 建立向量容器,初始容量16,長度0,配置資料空間
   public HW1_Vector()
   {
      capacity = 16;
      length = 0;
      data = new int[capacity];
   }
   
   // 確保向量容器的容量至少為size，不然以2倍作擴充及拷貝
   // 成功回傳真,否則回傳假
   public boolean ensureCapacity(int size)
   {

      return true;
   }
   
   // 於位置loc加入元素data,其後元素往後推移1格位置
   // 成功回傳真,否則回傳假
   public boolean add(int loc, int data)
   {
      return true;
   }
   
   // 於位置loc刪除元素,其後元素往前遞補1格位置
   // 成功回傳刪除元素,否則回傳Integer.MIN_VALUE
   public int remove(int loc)
   {
   
      return Integer.MIN_VALUE;
   }
   
   // 
   public static void main(String args[])
   {
      // 於位置1加入從1到1,000,000的整數，統計列印加入1個元素平均時間    
      // 每加10000次元素列印一次向量容量

      // 於位置1刪除元素1,000,000次，統計列印刪除1個元素平均時間
      // 列印刪除完的向量容量
   }
}

