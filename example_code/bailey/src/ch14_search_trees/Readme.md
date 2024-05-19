# ch14 Search Trees 搜索樹
### BinarySearchTree 二分搜索樹  
於二分樹之上，加入 左子樹 <= 自己 <= 右子樹 的限制，不考慮樹平衡

#### BinaryTree.java 二分樹  
本設計樹和樹根節點兩者不分，都以樹作代表；空小孩有串接虛節點；親子節點互相指向對方

#### BTPreorderIterator.java 二分樹前序走訪迭代器，無遞迴版  
利用todo堆疊存放待走訪節點。  
初始化: 堆疊壓入樹根  
走訪: 堆疊彈出列印節點,堆疊分別壓入節點右小孩,左小孩

#### BTInorderIterator.java 二分樹中序走訪迭代器，無遞迴版  
利用todo堆疊存放待走訪祖先節點   
初始化: 堆疊依序壓入樹根所有連續左小孩  
走訪: 堆疊彈出列印節點,堆疊分別壓入節點右子樹所有連續左小孩

#### BTPostorderIterator.java 二分樹後序走訪迭代器，無遞迴版  
利用todo堆疊存放待走訪節點  
初始化: 堆疊依序放入二分樹左包絡節點  
走訪: 堆疊彈出列印節點,若節點為親節點的左小孩,則堆疊依序放入以親節點右小孩為樹根的二分樹左包絡節點
  
#### BTLevelorderIterator.java 二分樹逐層走訪迭代器，無遞迴版  
利用todo佇列存放待走訪親戚節點  
初始化: 佇列加入樹根  
走訪: 佇列取出列印節點，佇列依序加入左,右小孩

### SymTab.java 符號表  
利用二分搜索樹建立符號表

### SplayTree.java 伸展樹  
於二分樹之上，加入 左子樹 <= 自己 <= 右子樹 的限制，儘量考慮樹平衡

#### SplayTreeIterator.java 伸展樹迭代器
允許伸展樹進行排序走訪

### RedBlackSearchTree.java 紅黑搜索樹  
於紅黑樹之上，加入 左子樹 <= 自己 <= 右子樹 的限制，強制考慮樹平衡

#### RedBlackTree.java 紅黑樹  
於二分樹之上，加入紅黑節點標記

#### RedBlackIterator.java 紅黑樹迭代器  
允許紅黑樹進行排序走訪

### RBSymTab.java 紅黑樹符號表 

