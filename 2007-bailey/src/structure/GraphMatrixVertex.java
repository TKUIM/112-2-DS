package structure;
/**
 * 
 * @version $Id: GraphMatrixVertex.java 8 2006-08-02 19:03:11Z bailey $
 * @author, 2001 duane a. bailey
 */
class GraphMatrixVertex extends Vertex 
{
    protected int index;

    /**
     * @post constructs a new augmented vertex
     * 
     * @param label 
     * @param idx 
     */
    public GraphMatrixVertex(Object label, int idx)
    {
        super(label);
        index = idx;
    }

    /**
     * @post returns index associated with vertex
     * 
     * @return 
     */
    public int index()
    {
        return index;
    }

    /**
     * @post returns string representation of vertex
     * 
     * @return 
     */
    public String toString()
    {
        return "<GraphMatrixVertex: "+label()+">";
    }
}
