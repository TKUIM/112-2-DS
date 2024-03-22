# ch11 Ordered Structures 有序結構
### Comparable.java 可比較介面
  compareTo
### Ratio.java 比例類別 (有理數)，內含分子,分母整數
  getValue/add/toString/equals/compareTo
### ComparableAssociation.java 可比較配對
  compareTo
### OrderedStructure.java 有序結構介面
  所放元素必須實作可比較介面
### OrderedVector.java 有序向量
  locate/contains/iterator/isEmpty/size  
  add/remove
### Sort.java 排序
  利用有序向量於加入數字時依序放置，列印排序結果
### NaturalComparator.java 自然比較器
  compare/equals
### ReverseComparator.java 反序比較器
  compare
### OrderedList.java 有序清單，內含長度，首節點指標，比較器
  contains/iterator  
  clear/add/remove
### ParkingLot2.java  停車格應用
  1大6中3小型停車格的車位管理，含出租，歸還，列印合約作業  
  利用rented有序清單存放(人名,車位)配對資料，可依據人名排序存放及查詢列印
