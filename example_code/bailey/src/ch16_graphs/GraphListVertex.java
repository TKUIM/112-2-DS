package ch16_graphs;
import java.util.Iterator;
import structure5.SinglyLinkedList;
import structure5.Structure;

/**
 * A private implementation of a vertex for use in graphs that
 * are internally represented as a list.  A vertex
 * is capable of holding a label and has a flag that can be set  
 * to mark it as visited.   
 * <P>
 * Typical Usage:
 * <P>
 * <pre>
 *     Vertex v = new {@link #GraphListVertex(Object) Vertex(someLabel)};
 *     //...several graph related operations occur
 *     if(!v.{@link #isVisited() isVisited()}){
 *         Object label = v.label();
 *         v.{@link #visit() visit()};
 *     }
 * </pre>
 * @see GraphListVertex
 * @see Vertex
 * @version $Id: GraphListVertex.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
// 清單實作圖的相鄰頂點類別，利用邊結構，存放某頂點的所有相鄰邊
class GraphListVertex<V,E> extends Vertex<V>
{
    protected Structure<Edge<V,E>> adjacencies; // adjacent edges 存放所有相鄰邊的結構
    /**
     * @post constructs a new vertex, not incident to any edge
     * 
     * @param key 
     */
    // 建立標記 key 的頂點， 擁有初始空的邊結構
    public GraphListVertex(V key)
    {
        super(key); // init Vertex fields
        adjacencies = new SinglyLinkedList<Edge<V,E>>(); // new list 建立存放邊的單向鏈結清單
    }

    /**
     * @pre e is an edge that mentions this vertex
     * @post adds edge to this vertex's adjacency list
     * 
     * @param e 
     */
    // 頂點加邊e，若重複不加兩次
    public void addEdge(Edge<V,E> e)
    {
        if (!containsEdge(e)) adjacencies.add(e);
    }

    /**
     * @post returns true if e appears on adjacency list
     * 
     * @param e 
     * @return 
     */
    // 頂點包含邊e否
    public boolean containsEdge(Edge<V,E> e)
    {
        return adjacencies.contains(e);
    }

    /**
     * @post removes and returns adjacent edge "equal" to e
     * 
     * @param e 
     * @return 
     */
    // 頂點移除邊e
    public Edge<V,E> removeEdge(Edge<V,E> e)
    {
        return adjacencies.remove(e);
    }

    /**
     * @post returns the edge that "equals" e, or null
     * 
     * @param e 
     * @return 
     */
    // 回傳頂點的邊e，若e不屬於頂點邊，回傳空
    public Edge<V,E> getEdge(Edge<V,E> e)
    {
        Iterator<Edge<V,E>> edges = adjacencies.iterator();
        while (edges.hasNext())
        {
            Edge<V,E> adjE = edges.next();
            if (e.equals(adjE)) return adjE;
        }
        return null;
    }

    /**
     * @post returns the degree of this node
     * 
     * @return 
     */
    // 回傳頂點的邊數
    public int degree()
    {
        return adjacencies.size(); 
    }

    /**
     * @post returns iterator over adj. vertices
     * 
     * @return 
     */
    // 回傳頂點的相鄰頂點迭代器
    public Iterator<V> adjacentVertices()
    {
        return new GraphListAIterator<V,E>(adjacentEdges(), label());
    }

    /**
     * @post returns iterator over adj. edges
     * 
     * @return 
     */
    // 回傳頂點的相鄰邊迭代器
    public Iterator<Edge<V,E>> adjacentEdges()
    {
        return adjacencies.iterator();
    }

    /**
     * @post returns string representation of vertex
     * 
     * @return String representation of vertex
     */
    // 回傳頂點標記字串
    public String toString()
    {
        return "<GraphListVertex: "+label()+">";
    }
}
