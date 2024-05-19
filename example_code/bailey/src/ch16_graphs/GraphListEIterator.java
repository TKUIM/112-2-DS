package ch16_graphs;
import java.util.Iterator;
import structure5.AbstractIterator;
import structure5.DoublyLinkedList;
import structure5.List;
import structure5.Map;
/**
 * An iterator over all edges.  Every directed/undirected
 * edge is considered exactly once.  Order is not guaranteed.
 * <P>
 * Typical use:
 * <P>
 * <pre>
 *      Graph g = new GraphList();
 *      // ...list gets built up...
 *      Iterator ei = g.edges();
 *      while (ei.{@link #hasNext() hasNext()})
 *      {
 *          System.out.println(ei.{@link #next() next()});
 *      }
 * </pre>
 *
 * @version $Id: GraphListEIterator.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
// 清單實作圖的邊迭代器，列舉圖所有邊(V,E)
class GraphListEIterator<V,E> extends AbstractIterator<Edge<V,E>>
{
    protected AbstractIterator<Edge<V,E>> edges; // 邊迭代器 edges

    /**
     * @post constructs a new iterator across edges of
     *       vertices within dictionary
     * 
     * @param dict 
     */
    // 建立相鄰邊迭代器，要傳入頂點的邊迭代器i
    public GraphListEIterator(Map<V,GraphListVertex<V,E>> dict)
    {
        // 建立空的邊清單
        List<Edge<V,E>> l = new DoublyLinkedList<>(); // 建立存放邊的雙向鏈結清單
        
        // 取得圖所有頂點迭代器，列舉所有頂點
        Iterator<GraphListVertex<V,E>> dictIterator = dict.values().iterator();
        while (dictIterator.hasNext())
        {
            // 取得頂點
            GraphListVertex<V,E> vtx =
                (GraphListVertex<V,E>)dictIterator.next();
            
            // 取得頂點的相鄰邊迭代器，列舉頂點的所有相鄰邊
            Iterator<Edge<V,E>> vtxIterator = vtx.adjacentEdges();
            while (vtxIterator.hasNext())
            {
                // 取得邊
                Edge<V,E> e = vtxIterator.next();
                
                // 頂點標記和邊的here頂點標記相同，清單才加入邊
                if (vtx.label().equals(e.here())) l.addLast(e);
            }
        }
        
        // 設定邊迭代器
        edges = (AbstractIterator<Edge<V,E>>)l.iterator();
    }

    /**
     * @post resets the iterator to first edge
     * 
     */
    // 重新迭代
    public void reset()
    {
        edges.reset();
    }

    /**
     * @post returns true iff current element is valid
     * 
     * @return True iff current element is valid
     */
    // 回傳還有下一個相鄰邊否
    public boolean hasNext()
    {
        return edges.hasNext();
    }

    /**
     * @pre hasNext()
     * @post returns the current element
     * 
     * @return The current element
     */
    // 回傳目前相鄰邊
    public Edge<V,E> get()
    {
        return edges.get();
    }

    /**
     * @pre hasNext()
     * @post returns current value and increments iterator
     * 
     * @return Current value and increments iterator
     */
    // 回傳下一個相鄰邊
    public Edge<V,E> next()
    {
        return edges.next();
    }
}

