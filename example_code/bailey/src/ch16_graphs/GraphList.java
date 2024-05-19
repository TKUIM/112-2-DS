/*
*  GraphList.java
*  清單實作圖，利用(頂點標記，相鄰頂點)映射，存放某標記頂點的相鄰頂點結構
*
* > java GraphList
Images Cinema is 0.0 miles away.
Movie Plex 3 is 12.6 miles away.
Cinema 7 is 12.9 miles away.
Cinema 1,2,&3 is 12.9 miles away.
Berkshire Mall Cinemas is 14.7 miles away.
Hathaway's Drive Inn Theatre is 16.5 miles away.
Hollywood Drive-In Theatre is 18.0 miles away.

theaters graph is
<GraphListDirected: <Hashtable: size=8 capacity=997 key=Cinema 7, value=<GraphListVertex: Cinema 7> key=Images Cinema, value=<GraphListVertex: Images Cinema> key=Movie Plex 3, value=<GraphListVertex: Movie Plex 3> key=Cinema 1,2,&3, value=<GraphListVertex: Cinema 1,2,&3> key=Berkshire Mall Cinemas, value=<GraphListVertex: Berkshire Mall Cinemas> key=TCL 312, value=<GraphListVertex: TCL 312> key=Hathaway's Drive Inn Theatre, value=<GraphListVertex: Hathaway's Drive Inn Theatre> key=Hollywood Drive-In Theatre, value=<GraphListVertex: Hollywood Drive-In Theatre>>>

註:
<GraphListDirected: <Hashtable: size=8 capacity=997 
                    key=Cinema 7, value=<GraphListVertex: Cinema 7> 
                    key=Images Cinema, value=<GraphListVertex: Images Cinema> 
                    key=Movie Plex 3, value=<GraphListVertex: Movie Plex 3> 
                    key=Cinema 1,2,&3, value=<GraphListVertex: Cinema 1,2,&3> 
                    key=Berkshire Mall Cinemas, value=<GraphListVertex: Berkshire Mall Cinemas> 
                    key=TCL 312, value=<GraphListVertex: TCL 312> 
                    key=Hathaway's Drive Inn Theatre, value=<GraphListVertex: Hathaway's Drive Inn Theatre> 
                    key=Hollywood Drive-In Theatre, value=<GraphListVertex: Hollywood Drive-In Theatre>>>

*/
// Graph, implemented with an Adjacency List
// (c) 1998, 2001 duane a. bailey

package ch16_graphs;
import structure5.Map;
import java.util.Iterator;
import structure5.AbstractStructure;
import structure5.Assert;
import structure5.ComparableAssociation;
import structure5.Hashtable;
import structure5.SkewHeap;

/**
 * Implementation of graph using adjacency lists.
 * Edges are stored in lists off of vertex structure.
 * Class is abstract: you must use GraphListDirected or 
 * GraphListUndirected to construct particular instances of graphs.
 *
 * Typical usage:
 * <pre>
 *     Graph g = new GraphListDirected();
 *     g.add("harry");
 *     g.add("sally");
 *     g.addEdge("harry","sally","friendly");
 *     ...
 * </pre>
 *
 * @version $Id: GraphList.java 35 2007-08-09 20:38:38Z bailey $
 * @author, 2001 duane a. bailey and kimberly tabtiang
 * @see GraphListDirected
 * @see GraphListUndirected
 * @see GraphMatrix
 */
// 清單實作圖，利用(頂點標記，相鄰頂點)映射，存放某標記頂點的相鄰頂點結構
abstract public class GraphList<V,E> extends AbstractStructure<V> implements Graph<V,E>
{
    /**
     * Map associating vertex labels with vertex structures.
     */
    // (頂點標記,相鄰頂點)映射
    protected Map<V,GraphListVertex<V,E>> dict; // label -> vertex 
    /**
     * Whether or not graph is directed.
     */
    protected boolean directed; // is graph directed? 有向圖否

    /**
     * Constructor of directed/undirected GraphList. Protected constructor.
     *
     * @param dir True if graph is to be directed.
     */
    // 建立清單實作圖，dir表示有向圖否
    protected GraphList(boolean dir)
    {
        dict = new Hashtable<V,GraphListVertex<V,E>>();
        directed = dir;
    }
    /**
     * Add a vertex to the graph.
     *
     * @pre label is a non-null label for vertex
     * @post a vertex with label is added to graph;
     *       if vertex with label is already in graph, no action
     * 
     * @param label Label of the vertex; should be non-null.
     */
    public void add(V label)
    {
        if (dict.containsKey(label)) return; // vertex exists
        GraphListVertex<V,E> v = new GraphListVertex<>(label);
        dict.put(label,v);
    }

    /**
     * Add an edge between two vertices within the graph.  Edge is directed
     * iff graph is directed.  Duplicate edges are silently replaced.
     * Labels on edges may be null.
     *
     * @pre vtx1 and vtx2 are labels of existing vertices
     * @post an edge (possibly directed) is inserted between
     *       vtx1 and vtx2.
     * 
     * @param vtx1 First (or source, if directed) vertex.
     * @param vtx2 Second (or destination, if directed) vertex.
     * @param label Label associated with the edge.
     */
    // 圖新增標記 label 的邊，其頂點為 vtx1, vtx2
    abstract public void addEdge(V v1, V v2, E label);

    /**
     * Remove a vertex from the graph.  Associated edges are also 
     * removed.  Non-vertices are silently ignored.
     *
     * @pre label is non-null vertex label
     * @post vertex with "equals" label is removed, if found
     * 
     * @param label The label of the vertex within the graph.
     * @return The label associated with the vertex.
     */
    // 圖移除標記 label 頂點，回傳移除頂點標記
    abstract public V remove(V label);

    /**
     * Remove possible edge between vertices labeled vLabel1 and vLabel2.
     * Directed edges consider vLabel1 to be the source.
     *
     * @pre vLabel1 and vLabel2 are labels of existing vertices
     * @post edge is removed, its label is returned
     * 
     * @param vLabel1 First (or source, if directed) vertex.
     * @param vLabel2 Second (or destination, if directed) vertex.
     * @return The label associated with the edge removed.
     */
    // 圖移除標記為 vLabel1，vLabel2 兩頂點的邊
    abstract public E removeEdge(V vLabel1, V vLabel2);

    /**
     * Get reference to actual label of vertex.  Vertex labels are matched
     * using their equals method, which may or may not test for actual
     * equivalence.  Result remains part of graph.
     *
     * @post returns actual label of indicated vertex
     * 
     * @param label The label of the vertex sought.
     * @return The actual label, or null if none is found.
     */
    // 回傳標記 label 頂點，若失敗回傳空
    public V get(V label)
    {
        Assert.condition(dict.containsKey(label), "Vertex exists");
        return dict.get(label).label();
    }

    /**
     * Get reference to actual edge.  Edge is identified by
     * the labels on associated vertices.  If edge is directed, the
     * label1 indicates source.
     *
     * @post returns actual edge between vertices
     * 
     * @param label1 The first (or source, if directed) vertex.
     * @param label2 The second (or destination, if directed) vertex.
     * @return The edge, if found, or null.
     */
    // 回傳標記為 vLabel1，vLabel2 兩頂點的邊，若失敗回傳空
    public Edge<V,E> getEdge(V label1, V label2)
    {
        Assert.condition(dict.containsKey(label1), "Vertex exists");
        Edge<V,E> e = new Edge<V,E>(get(label1),get(label2), null, directed); 
        return dict.get(label1).getEdge(e);
    }

    /**
     * Test for vertex membership.
     *
     * @post returns true iff vertex with "equals" label exists
     * 
     * @param label The label of the vertex sought.
     * @return True iff vertex with matching label is found.
     */
    // 回傳圖包含標記 label 頂點否
    public boolean contains(V label)
    {
        return dict.containsKey(label);   
    }

    /**
     * Test for edge membership.  If edges are directed, vLabel1
     * indicates source.
     *
     * @post returns true iff edge with "equals" label exists
     * 
     * @param vLabel1 First (or source, if directed) vertex.
     * @param vLabel2 Second (or destination, if directed) vertex.
     * @return True iff the edge exists within the graph.
     */
    // 回傳圖包含標記為 vLabel1，vLabel2 兩頂點的邊否
    public boolean containsEdge(V vLabel1, V vLabel2)
    {
        Assert.condition(dict.containsKey(vLabel1), "Vertex exists");
        Edge<V,E> e = new Edge<V,E>(vLabel1, vLabel2, null, directed); 
        return dict.get(vLabel1).containsEdge(e);
    }

    /**
     * Test and set visited flag of vertex.
     *
     * @post sets visited flag on vertex, returns previous value
     * 
     * @param label Label of vertex to be visited.
     * @return Previous value of visited flag on vertex.
     */
    // 設定標記 label 頂點拜訪過，回傳之前拜訪過狀態
    public boolean visit(V label)
    {
        return dict.get(label).visit();
    }
    
    /**
     * Test and set visited flag of edge.
     *
     * @pre sets visited flag on edge; returns previous value
     * 
     * @param e Edge object that is part of graph.
     * @return Previous value of the Edge's visited flag.
     */
    // 回傳邊 e 拜訪過否
    public boolean visitEdge(Edge<V,E> e)
    {
        return e.visit();
    }

    /**
     * Return visited flag of vertex.
     *
     * @post returns visited flag on labeled vertex
     * 
     * @param label Label of vertex.
     * @return True if vertex has been visited.
     */
    // 回傳標記 label 頂點拜訪過否
    public boolean isVisited(V label)
    {
        return dict.get(label).isVisited();
    }

    /**
     * Return visited flag of edge.
     *
     * @post returns visited flag on edge between vertices
     * 
     * @param e Edge of graph to be considered.
     * @return True if the edge has been visited.
     */
    // 回傳邊 e 拜訪過否
    public boolean isVisitedEdge(Edge<V,E> e)
    {
        return e.isVisited();
    }

    /**
     * Clear visited flags of edges and vertices.
     *
     * @post resets visited flags to false
     */
    // 清除所有邊及頂點為未拜訪過狀態
    public void reset()
    {
        // reset the vertices
        Iterator<GraphListVertex<V,E>> vi = dict.values().iterator();
        while (vi.hasNext())
        {
            GraphListVertex<V,E> vtx = vi.next();
            vtx.reset();
        }
        // reset the edges
        Iterator<Edge<V,E>> ei = edges();
        while (ei.hasNext())
        {
            Edge<V,E> e = ei.next();
            e.reset();
        }
    }

    /**
     * Determine number of vertices within graph.
     *
     * @post returns the number of vertices in graph
     * 
     * @return The number of vertices within graph.
     */
    // 回傳圖的頂點個數
    public int size()
    {
        return dict.size(); 
    }

    /**
     * Determine out degree of vertex.
     *
     * @pre label labels an existing vertex
     * @post returns the number of vertices adjacent to vertex
     *
     * @param label Label associated with vertex.
     * @return The number of edges with this vertex as source.
     */
    // 回傳標記 label 頂點的相鄰邊個數
    public int degree(V label)
    {
        Assert.condition(dict.containsKey(label), "Vertex exists.");
        return dict.get(label).degree();
    }

    /**
     * Determine the number of edges in graph.
     *
     * @post returns the number of edges in graph
     * 
     * @return Number of edges in graph.
     */
    // 回傳圖的邊個數
    abstract public int edgeCount();

    /**
     * Construct vertex iterator.  Vertices are not visited in 
     * any guaranteed order.
     *
     * @post returns iterator across all vertices of graph
     * 
     * @return AbstractIterator traversing vertices in graph.
     */
    // 回傳圖的頂點迭代器
    public Iterator<V> iterator()
    {
        return dict.keySet().iterator();
    }

    /**
     * Construct an adjacent vertex iterator.   Adjacent vertices
     * (those on destination of edge, if directed) are considered,
     * but not in any guaranteed order.
     *
     * @pre label is label of vertex in graph
     * @post returns iterator over vertices adj. to vertex
     *       each edge beginning at label visited exactly once
     * 
     * @param label Label of the vertex.
     * @return Iterator traversing the adjacent vertices of labeled vertex.
     */
    // 回傳標記 label 頂點的鄰居迭代器
    public Iterator<V> neighbors(V label)
    {
        // return towns adjacent to vertex labeled label
        Assert.condition(dict.containsKey(label), "Vertex exists");
        return dict.get(label).adjacentVertices();
    }

    /**
     * Construct an iterator over all edges.  Every directed/undirected
     * edge is considered exactly once.  Order is not guaranteed.
     *
     * @post returns iterator across edges of graph
     *       iterator returns edges; each edge visited once
     * 
     * @return Iterator over edges.
     */
    // 回傳圖的邊迭代器
    public Iterator<Edge<V,E>> edges()
    {
        Iterator<Edge<V,E>> iterEdges =  new GraphListEIterator<V,E>(dict);
        
        return iterEdges;
    }

    /**
     * Remove all vertices (and thus, edges) of the graph.
     *
     * @post removes all vertices from graph
     */
    // 清除圖所有頂點及邊
    public void clear()
    {
        dict.clear();
    }

    /**
     * Determine if graph is empty.
     *
     * @post returns true if graph contains no vertices
     * 
     * @return True iff there are no vertices in graph.
     */
    // 回傳圖含空頂點否
    public boolean isEmpty()
    {
      return dict.isEmpty();
    }

    /**
     * Determine if graph is directed.
     *
     * @post returns true if edges of graph are directed
     * 
     * @return True iff the graph is directed.
     */
    // 回傳圖為有向圖，其邊皆有向邊否
    public boolean isDirected()
    {
        return directed;
    }
    
    // 測試
    public static void main(String[] argv){
        // 建立空的影院有向圖，頂點標記為影院名，邊標記為實數距離
        Graph<String,Double> theaters = new GraphListDirected<>();
        
        // 建立空偏斜堆積，元素為可比較(距離，影院名)配對
        SkewHeap<ComparableAssociation<Double,String>> heap = new SkewHeap<>();
        
        //instantiate array of locations 
        // 建立影院名陣列[0 ~ 7]，其中第0格為"TCL 312"實驗室
        String[] locations = new String[]{"TCL 312", "Images Cinema", 
                                          "Movie Plex 3", "Cinema 1,2,&3", 
                                          "Cinema 7", "Berkshire Mall Cinemas"
                                          ,"Hathaway's Drive Inn Theatre",
                                          "Hollywood Drive-In Theatre"};

        //instantiate array of distances between <code>location[0]</code> 
        //and movie theaters
        // 建立影院距離陣列[0 ~ 7]，第0格存-1表示不算
        double[] distances =  new double[]{-1, 0.0, 12.6, 12.9, 12.9, 
                                           14.7, 16.5, 18.0};
        
        //build graph 建立影院有向圖
        // 有向圖新增影院名頂點
        for(int i=0; i < locations.length; i++) theaters.add(locations[i]);
        
        // 以"TCL 312"實驗室為中心，新增到各影院的有向邊，邊標記為距離
        for(int i=1; i < distances.length; i++){
            theaters.addEdge(locations[0],locations[i],
                             new Double(distances[i]));
        }
        
        //place neighbors of lab in into priority queue
        // 列舉"TCL 312"實驗室的所有鄰居影院，將其加入堆積，用距離排序
        for(Iterator<String> i=theaters.neighbors(locations[0]); i.hasNext();){
            String theater = i.next(); // 取得下個鄰居影院
            Double distance = theaters.getEdge(locations[0], theater).label(); // 取得距離
            
            // 將(距離,影院名)配對加入堆積
            heap.add(new ComparableAssociation<Double,String>(distance,theater));
        }
        
        //print out theaters in order of distance
        // 由小到大距離,列舉所有鄰居影院
        while(!heap.isEmpty()){
            ComparableAssociation<Double,String> show = heap.remove();  // 取得次小配對
            System.out.println(show.getValue()+" is "+show.getKey()+" miles away.");
        }
        
        System.out.println("\ntheaters graph is\n" + theaters.toString());
    }
}
