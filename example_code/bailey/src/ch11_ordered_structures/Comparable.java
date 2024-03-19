package ch11_ordered_structures;

/*
  Comparable.java 可比較介面
*/

public interface Comparable<T>
{
  // 回傳(自己 - that物件)的比較結果
  //   正數 表示自己 > that物件
  //   零   表示自己 = that物件
  //   負數 表示自己 < that物件
  public int compareTo(T that);
}