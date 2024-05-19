package ch16_graphs;
import java.util.Iterator;
import structure5.AbstractIterator;
/**
 * An adjacent vertex iterator.   Adjacent vertices
 * (those on destination of edge, if directed) are considered,
 * but not in any guaranteed order.
 * Typical use:
 * <P>
 * <pre>
 *      Graph g = new GraphList();
 *      // ...list gets built up...
 *      Iterator ai = g.neighbors(someVertex);
 *      while (ai.{@link #hasNext() hasNext()})
 *      {
 *          System.out.println(ai.{@link #next() next()});
 *      }
 * </pre>
 * @version $Id: GraphListAIterator.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
// 清單實作圖的相鄰頂點迭代器，列舉頂點V所有邊E的相鄰頂點V
class GraphListAIterator<V,E> extends AbstractIterator<V>
{
    protected AbstractIterator<Edge<V,E>> edges;  // 頂點vertex的邊迭代器 edges
    protected V vertex;  // 頂點vertex

    /**
     * @pre i is an edge iterator
     * @post returns iterator over vertices adjacent to v
     * 
     * @param i 
     * @param v 
     */
    // 建立頂點v的相鄰頂點迭代器，要傳入頂點v的邊迭代器i
    public GraphListAIterator(Iterator<Edge<V,E>> i, V v)
    {
        edges = (AbstractIterator<Edge<V,E>>)i;
        vertex = v;
    }

    /**
     * @post resets iterator
     * 
     */
    // 重新迭代
    public void reset()
    {
        edges.reset();
    }

    /**
     * @post returns true if more adj. vertices to traverse
     * 
     * @return True if more adj. vertices to traverse
     */
    // 回傳還有下一個頂點否
    public boolean hasNext()
    {
        return edges.hasNext();  // 回傳還有下一個邊否
    }

    /**
     * @pre hasNext
     * @post returns the next adjacent vertex
     * 
     * @return The next adjacent vertex
     */
    // 回傳下一個邊的相鄰頂點
    public V next()
    {
        Edge<V,E> e = edges.next();  // 取得下一個邊
        if (vertex.equals(e.here())) 
        {
            return e.there();
        } else { // N.B could be vertex if self-loop edge
            return e.here();
        }
    }

    /**
     * @pre hasNext
     * @post returns the current adj. vertex
     * 
     * @return The current adj. vertex
     */
    // 回傳目前邊的相鄰頂點
    public V get()
    {
        Edge<V,E> e = edges.get(); // 取得目前邊
        if (vertex.equals(e.here())) 
        {
            return e.there();
        } else { // NB. could be vertex if self-loop edge
            return e.here();
        }
    }
}
