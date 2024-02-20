package structure;
import java.util.Iterator;
/**
 * 
 * @version $Id: GraphListEIterator.java 8 2006-08-02 19:03:11Z bailey $
 * @author, 2001 duane a. bailey
 */
class GraphListEIterator extends AbstractIterator
{
    protected Iterator edges;

    /**
     * @post constructs a new iterator across edges of
     *       vertices within dictionary
     * 
     * @param dict 
     */
    public GraphListEIterator(Map dict)
    {
        List l = new DoublyLinkedList();
        Iterator dictIterator = dict.values().iterator();
        while (dictIterator.hasNext())
        {
            GraphListVertex vtx =
                (GraphListVertex)dictIterator.next();
            Iterator vtxIterator = vtx.adjacentEdges();
            while (vtxIterator.hasNext())
            {
                Edge e = (Edge)vtxIterator.next();
                if (vtx.label().equals(e.here())) l.addLast(e);
            }
        }
        edges = l.iterator();
    }

    /**
     * @post resets the iterator to first edge
     * 
     */
    public void reset()
    {
        ((AbstractIterator)edges).reset();
    }

    /**
     * @post returns true iff current element is valid
     * 
     * @return 
     */
    public boolean hasNext()
    {
        return edges.hasNext();
    }

    /**
     * @pre hasNext()
     * @post returns the current element
     * 
     * @return 
     */
    public Object get()
    {
        return ((AbstractIterator)edges).get();
    }

    /**
     * @pre hasNext()
     * @post returns current value and increments iterator
     * 
     * @return 
     */
    public Object next()
    {
        return edges.next();
    }
}

