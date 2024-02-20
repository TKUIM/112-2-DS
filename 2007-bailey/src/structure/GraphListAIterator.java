package structure;
import java.util.Iterator;
/**
 * 
 * @version $Id: GraphListAIterator.java 8 2006-08-02 19:03:11Z bailey $
 * @author, 2001 duane a. bailey
 */
class GraphListAIterator extends AbstractIterator
{
    protected Iterator edges;
    protected Object vertex;

    /**
     * @pre i is an edge iterator
     * @post returns iterator over vertices adjacent to v
     * 
     * @param i 
     * @param v 
     */
    public GraphListAIterator(Iterator i, Object v)
    {
        edges = i;
        vertex = v;
    }

    /**
     * @post resets iterator
     * 
     */
    public void reset()
    {
        ((AbstractIterator)edges).reset();
    }

    /**
     * @post returns true if more adj. vertices to traverse
     * 
     * @return 
     */
    public boolean hasNext()
    {
        return edges.hasNext();
    }

    /**
     * @pre hasNext
     * @post returns the next adjacent vertex
     * 
     * @return 
     */
    public Object next()
    {
        Edge e = (Edge)edges.next();
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
     * @return 
     */
    public Object get()
    {
        Edge e = (Edge)((AbstractIterator)edges).get();
        if (vertex.equals(e.here())) 
        {
            return e.there();
        } else { // NB. could be vertex if self-loop edge
            return e.here();
        }
    }
}
