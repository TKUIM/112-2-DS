/*
*  HW5_HeapSort.java
*    區別樹及節點物件，不用泛型，節點只放整數。共實作如下方法，
*     建樹,前序,中序,樹長全否,及測試方法。
*
* > java HW5_HeapSort
* 輸出結果補在此處
*
*/
package exercise;

import structure5.Vector;

public class HW5_HeapSort
{
   // 回傳下標i節點的親節點下標
   static int parent(int i)
   {
      return 0;
   }
   
   // 回傳下標i節點的左小孩節點下標
   static int left(int i)
   {
      return 0;
   }
   
   // 回傳下標i節點的右小孩節點下標
   static int right(int i)
   {
      return 0;
   }

   
   // 針對data陣列表示的堆積，從leaf下標位置出發，將輕元素向上浮起，直到停在合適位置
   static void percolateUp(Vector<Integer> data, int leaf)
   {
   
   }
   
   // 針對data陣列表示的堆積，從root下標位置出發，將重元素向下沈沒，直到停在合適位置
   static void percolateDown(Vector<Integer> data, int root)
   {
   
   }
   
   // 針對data陣列表示的堆積，由後往前逐一加入元素當根節點，進行向下調整，形成堆積
   static void bottomUpHeapify(Vector<Integer> data)
   {
   
   }

   // 針對data陣列表示的堆積，由前往後逐一加入元素在後，進行向上調整，形成堆積
   static void topDownHeapify(Vector<Integer> data)
   {
   
   }

   // 針對data[0~size-1]陣列表示的最小堆積
   // 逐一刪除最小首元素，將刪除元素與陣列尾元素對調
   static void remove(Vector<Integer> data, int size)
   {
   
   }

   // 針對data陣列表示的堆積，進行原陣列排序
   public static void inPlaceHeapSort(Vector<Integer> data)
   {
      // 呼叫任一Heapify堆積化方法,對data陣列進行堆積化動作


      // 利用迴圈,呼叫remove方法,對data陣列表示的堆積,進行刪除到空的動作
      // 一邊刪除，一邊由後往前，逐一於陣列放上由小到大的元素


      // 離開後，呼叫者看到的data陣列已排序好
   }   
   
   public static void main(String args[])
   {
      // 給定 3,4,7,0,2,8,6 順序的陣列
      // 呼叫inPlaceHeapSort對其進行原陣列排序
      // 列印原始陣列,堆積化後初始陣列,每次刪除過程部份排序好陣列
   }
}
