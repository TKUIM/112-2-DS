# ch12 Binary Trees 二分樹
### Pedigree.java 家譜表  
建立二分樹表示George Bush 四代家譜表

### Calc.java 運算樹  
建立二分樹表示運算樹及求值  
   R = 1 + (L - 1) * 2

### BinaryTree.java 二分樹  
本設計樹和樹根節點兩者不分，都以樹作代表；空小孩有串接虛節點；親子節點互相指向對方

### InfiniteQuestions.java 動態建立yes/no知識樹

### BTPreorderIterator.java 二分樹前序走訪迭代器，無遞迴版  
利用todo堆疊存放待走訪節點。  
初始化: 堆疊壓入樹根  
走訪: 堆疊彈出列印節點,堆疊分別壓入節點右小孩,左小孩

### BTInorderIterator.java 二分樹中序走訪迭代器，無遞迴版  
利用todo堆疊存放待走訪祖先節點   
初始化: 堆疊依序壓入樹根所有連續左小孩  
走訪: 堆疊彈出列印節點,堆疊分別壓入節點右子樹所有連續左小孩

### BTPostorderIterator.java 二分樹後序走訪迭代器，無遞迴版  
利用todo堆疊存放待走訪節點  
初始化: 堆疊依序放入二分樹左包絡節點  
走訪: 堆疊彈出列印節點,若節點為親節點的左小孩,則堆疊依序放入以親節點右小孩為樹根的二分樹左包絡節點
  
### BTLevelorderIterator.java 二分樹逐層走訪迭代器，無遞迴版  
利用todo佇列存放待走訪親戚節點  
初始化: 佇列加入樹根  
走訪: 佇列取出列印節點，佇列依序加入左,右小孩

### RecursiveIterators.java 遞迴版迭代器，含前序，中序，後序走訪

### Huffman.java Huffman壓縮編碼樹

### GameTree.java 遊戲樹
### HexBoard.java 六子棋盤
### HexMove.java 六子棋步
### Player.java 玩家
