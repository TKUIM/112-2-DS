# ch15 Maps 映射
### SymMap.java 符號表
  利用映射清單 MapList<String,String> 存放(鍵,值)=(別名,姓名)配對資料  
  mapList.put/containsKey/get
### Map 映射介面
  size/isEmpty/equals/hashCode/containsKey/containsValue/get/keySet/values/entrySet  
  put/putAll/remove/clear
### MapList.java 映射清單
  keySet/entrySet/values  
  put
### Hashtable.java 雜湊表
    利用向量容器存放(K,V)配對資料 Vector<HashAssociation<K,V>>  
    格子初值為null表示空白格
        放置未設定保留的配對物件表示佔用格，放置設定保留的配對物件表示保留格  
    int locate(K key) 回傳可能放置key鍵的所在格下標  
        若找不到，下標格為空白格或保留格；若找到，下標格為佔用格。  
    get/containsValue/containsKey/remove/values  
    put/remove
### AbstractStructure.java 抽象結構
  hashCode 利用迭代元素計算雜湊碼
### BinaryTree.java 二分樹
  hashCode 利用左右子樹雜湊碼計算自己雜湊碼
### OrderedMap.java 有序映射
  可比較鍵
### Table.java (有序映射)表格
  利用二分伸展樹存放可比較(鍵,值)配對  
  containsValue/keySet/entrySet  
  put
### Index.java 索引
  利用表格存放(字,行號)配對資料

  
