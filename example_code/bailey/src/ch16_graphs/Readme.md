# ch16 Graphs 圖形
### Graph.java 圖形介面  
介面家規，定義圖形必須提供的方法

### Vertex.java 頂點   
圖形頂點，具有標記 label，拜訪過否 visited，共兩欄位

### Edge.java 邊  
圖形邊，具有邊標記 label，兩頂點標記 here, there，有向邊否 directed，拜訪過否，共五欄位

#### ComparableEdge.java 可比較邊  
圖形邊，同樣具有五欄位，提供compareTo方法作邊標記的比較

### GraphMatrix.java 矩陣版圖形  
利用相鄰矩陣記錄邊關係的圖形，具有五欄位:  
頂點數 size，相鄰矩陣 data，(標記,頂點)字典 dict，閒置下標清單 freeList，有向圖否 directed

#### GraphMatrixVertex.java 矩陣版圖形頂點  
圖形頂點，具有標記 label，拜訪過否 visited，陣列下標 index，共三欄位

#### GraphMatrixDirected.java 矩陣版有向圖形  
利用相鄰矩陣記錄邊關係的有向圖形

#### GraphMatrixUndirected.java 矩陣版無向圖形  
利用相鄰矩陣記錄邊關係的無向圖形

### GraphList.java 清單版圖形  
利用相鄰頂點清單記錄邊關係的圖形，具有(頂點，相鄰頂點)字典 dict，有向圖否 directed，共兩欄位

#### GraphListVertex.java 清單版圖形頂點  
圖形頂點，具有標記 label，拜訪過否 visited，相鄰邊結構 adjacencies，共三欄位

#### GraphListAIterator.java 清單版圖形相鄰頂點迭代器  
圖形頂點迭代器，列舉某頂點V所有邊E的相鄰頂點V

#### GraphListEIterator.java 清單版圖形邊迭代器  
圖形邊迭代器，利用雙向鏈結清單存放所有圖形邊，供列舉用

#### GraphListDirected.java 清單版有向圖形  
利用相鄰頂點清單記錄邊關係的有向圖形

#### GraphListUndirected.java 清單版無向圖形  
利用相鄰頂點清單記錄邊關係的無向圖形

### Reachability.java 可到達頂點  
參考16.4.1, p422 程式碼

### TopoSort.java 拓樸排序
參考16.4.2, p426 程式碼

### Warshall.java 遞移封閉頂點   
參考16.4.3, p427 程式碼

### Floyd.java 所有配對最短距離   
參考16.4.4, pp428-429 程式碼

### MCSF.java 最小成本生成森林  
利用堆積找出圖形骨幹邊組成的生成森林，其邊總成本最小

### MCST.java 找圖形元件
利用最小成本生成森林找出圖的所有元件，統計元件數，各元件邊數及權重和

### Dijkstra.java 單來源最短路徑  
利用堆積找出圖形由單來源節點到所有節點的最短路徑
