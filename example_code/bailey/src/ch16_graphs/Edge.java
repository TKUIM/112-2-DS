// Generic base class for describing edges in graphs.
// (c) 1998, 2001 duane a. bailey

package ch16_graphs;

/**
 * A class implementing common edge type among graphs.  This class
 * supports both directed and undirected edges.  Edge may also have
 * visited flags set and cleared.
 * <P>
 * Typical usage:
 * <P>
 * <pre>
 *     Graph g = new GraphListDirected();
 *     g.add("harry");
 *     g.add("sally");
 *     g.addEdge("harry","sally","friendly");
 *     Edge e = g.getEdge("harry","sally");
 *     Object label = e.{@link #label()};
 *     if(e.{@link #isDirected()}){
 *          Vertex source = e.{@link #here()};
 *          Vertex destination = e.{@link #there()};
 *     }
 *     e.{@link #visit()};
 *     e.{@link #reset()};
 *     ...
 * </pre>
 * @version $Id: Edge.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 * @see structure.Graph
 */
// 邊類別，其頂點型別 V，邊標記型別 E
public class Edge<V,E>
{
    /**
     * Two element array of vertex labels.
     * When necessary, first element is source.
     */
    protected V here, there;    // labels of adjacent vertices 兩相鄰頂點的標記
    /**
     * Label associated with edge.  May be null.
     */
    protected E label;     // edge label 邊標記，可放權重
    /**
     * Whether or not this edge has been visited.
     */
    protected boolean visited;  // this edge visited 邊拜訪過狀態
    /**
     * Whether or not this edge is directed.
     */
    protected boolean directed; // this edge directed 有向邊否

    /**
     * Construct a (possibly directed) edge between two labeled
     * vertices.  When edge is directed, vtx1 specifies source.
     * When undirected, order of vertices is unimportant.  Label
     * on edge is any type, and may be null.
     * Edge is initially unvisited.
     *
     * @post edge associates vtx1 and vtx2; labeled with label
     *       directed if "directed" set true
     *
     * @param vtx1 The label of a vertex (source if directed).
     * @param vtx2 The label of another vertex (destination if directed).
     * @param label The label associated with the edge.
     * @param directed True iff this edge is directed.
     */
    // 建立邊，由 V 型別的(來源)頂點 vtx1 連向(去處)頂點 vtx2，有向邊否布林值 directed
    // 有向邊 directed 為真，頂點才區別 vtx1 為來源 及 vtx2 為去處
    public Edge(V vtx1, V vtx2, E label,
                boolean directed)
    {
        here = vtx1;
        there = vtx2;
        this.label = label;
        visited = false;
        this.directed = directed;
    }

    /**
     * Returns the first vertex (or source if directed).
     *
     * @post returns first node in edge
     * 
     * @return A vertex; if directed, the source.
     */
    // 無向邊的第1頂點 或 有向邊的來源頂點 
    public V here()
    {
        return here;
    }

    /**
     * Returns the second vertex (or source if undirected).
     *
     * @post returns second node in edge
     * 
     * @return A vertex; if directed, the destination.
     */
    // 無向邊的第2頂點 或 有向邊的去處頂點 
    public V there()
    {
        return there;
    }

    /**
     * Sets the label associated with the edge.  May be null.
     *
     * @post sets label of this edge to label 
     * 
     * @param label Any object to label edge, or null.
     */
    // 修改邊標記為 label
    public void setLabel(E label)
    {
        this.label = label;
    }

    /**
     * Get label associated with edge.
     *
     * @post returns label associated with this edge
     * 
     * @return The label found on the edge.
     */
    // 回傳邊標記
    public E label()
    {
        return label;
    }

    /**
     * Test and set visited flag on vertex.
     *
     * @post visits edge, returns whether previously visited
     * 
     * @return True iff edge was visited previously.
     */
    // 設定邊拜訪過狀態為真，回傳之前拜訪過狀態
    public boolean visit()
    {
        boolean was = visited;
        visited = true;
        return was;
    }

    /**
     * Check to see if edge has been visited.
     *
     * @post returns true iff edge has been visited
     * 
     * @return True iff the edge has been visited.
     */
    // 回傳邊拜訪過狀態
    public boolean isVisited()
    {
        return visited;
    }

    /**
     * Check to see if edge is directed.
     *
     * @post returns true iff edge is directed
     * 
     * @return True iff the edge has been visited.
     */
    // 回傳有向邊否
    public boolean isDirected()
    {
        return directed;
    }

    /**
     * Clear the visited flag associated with edge.
     *
     * @post resets edge's visited flag to initial state
     */
    // 清除邊成為未拜訪過狀態
    public void reset()
    {
        visited = false;
    }

    /**
     * Returns hashcode associated with edge.
     *
     * @post returns suitable hashcode
     * 
     * @return An integer code suitable for hashing.
     */
    // 回傳邊雜湊碼
    public int hashCode()
    {
        if (directed) return here().hashCode()-there().hashCode();
        else          return here().hashCode()^there().hashCode();
    }

    /**
     * Test for equality of edges.  Undirected edges are equal if
     * they connect the same vertices.  Directed edges must have same
     * direction.
     *
     * @post returns true iff edges connect same vertices
     * 
     * @param o The other edge.
     * @return True iff this edge is equal to other edge.
     */
    // 回傳自己和其他o邊是否為同一個邊
    // 若無向邊，兩者須連相同頂點；若有向邊，兩者還須方法相同
    public boolean equals(Object o)
    {
        Edge<?,?> e = (Edge<?,?>)o;
        return ((here().equals(e.here()) && 
                 there().equals(e.there())) ||
                (!directed &&
                 (here().equals(e.there()) && 
                  there().equals(e.here()))));
    }
    
    /**
     * Construct a string representation of edge.
     *
     * @post returns string representation of edge
     * 
     * @return String representing edge.
     */
    // 回傳邊內部欄位字串
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<Edge:");
        if (visited) s.append(" visited");
        s.append(" "+here());
        if (directed) s.append(" ->");
        else s.append(" <->");
        s.append(" "+there()+">");
        return s.toString();
    }
}
