// Graph, implemented with an adjacency matrix
// (c) 1998, 2001 duane a. bailey

package ch16_graphs;
import java.util.Iterator;
import structure5.List;
import structure5.SinglyLinkedList;

/**
 * A GraphMatrixUndirected is a matrix-based graph representation that 
 * consists of a collection of vertices and undirected edges.  Portions of 
 * the graph may be marked visited to support iterative algorithms.  
 * Iteration is provided over vertices, edges, and vertices adjacent to a
 * particular vertex.
 * GraphMatrix differs from GraphList in that the user must commit to 
 * an upper bound on number of vertices in graph.
 * <P>
 * Example Usage: 
 * <P> 
 * To create a graph representation of the movie theaters nearest
 * the Williams College Department of Computer Science's unix laboratory, 
 * and to print these theaters out in order of increasing distance,
 * we could use the following:
 * <P>
 * <pre>
 *  public static void main(String[] argv){
 *      Graph theaters = new {@link #GraphMatrixUndirected(int) GraphMatrixUndirected(9)};
 *      FibHeap heap = new FibHeap();
 *      
 *      //instantiate array of locations 
 *      String[] locations = new String[]{"TCL 312", "Images Cinema", 
 *                                        "Movie Plex 3", "Cinema 1,2,&3", 
 *                                        "Cinema 7", "Berkshire Mall Cinemas"
 *                                        ,"Hathaway's Drive Inn Theatre",
 *                                        "Hollywood Drive-In Theatre"};
 *
 *      //instantiate array of distances between <code>location[0]</code> 
 *      //and movie theaters
 *      double[] distances =  new double[]{-1, 0.0, 12.6, 12.9, 12.9, 
 *                                         14.7, 16.5, 18.0};
 *      
 *      //build graph
 *      for(int i=0; i < locations.length; i++) theaters.add(locations[i]);
 *      for(int i=1; i < distances.length; i++){
 *        theaters.{@link #addEdge(Object, Object, Object) addEdge(locations[0],locations[i],new Double(distances[i]))};
 *      }
 *      
 *      //place neighbors of lab in into priority queue
 *      for(Iterator i=theaters.{@link #neighbors(Object) neighbors(locations[0])}; i.hasNext();){
 *          Object theater = i.next();
 *          Object distance = theaters.{@link #getEdge(Object,Object) getEdge(locations[0], theater).label()};
 *          heap.add(new ComparableAssociation((Comparable)distance,theater));
 *      }
 *      
 *      //print out theaters in order of distance
 *      while(!heap.isEmpty()){
 *          ComparableAssociation show = (ComparableAssociation)heap.remove();
 *          System.out.println(show.getValue()+" is "+show.getKey()+" miles away.");
 *      }
 *  }
 * </pre>
 *
 *
 * @version $Id: GraphMatrixUndirected.java 35 2007-08-09 20:38:38Z bailey $
 * @author, 2001 duane a. bailey and kimberly tabtiang
 * @see GraphMatrix
 * @see GraphMatrixDirected
 * @see GraphListUndirected
 */
public class GraphMatrixUndirected<V,E> extends GraphMatrix<V,E>
{
    /**
     * Construct an undirected, adjacency-matrix based graph.
     *
     * @pre size > 0
     * @post constructs an empty graph that may be expanded to
     *       at most size vertices.  Graph is undirected.
     * 
     * @param size Maximum number of vertices in graph.
     */
    public GraphMatrixUndirected(int size)
    {
        super(size,false);
    }
    /**
     * Add an edge between two vertices within the graph.  Edge is undirected.
     * Duplicate edges are silently replaced.
     * Labels on edges may be null.
     *
     * @pre vLabel1 and vLabel2 are labels of existing vertices, v1 & v2
     * @post an edge (undirected) is inserted between v1 and v2;
     *       if edge is new, it is labeled with label (can be null)
     * 
     * @param vLabel1 One vertex.
     * @param vLabel2 Another vertex.
     * @param label Label associated with the edge.
     */
    public void addEdge(V vLabel1, V vLabel2, E label)
    {
        GraphMatrixVertex<V> vtx1,vtx2;
        // get vertices
        vtx1 = dict.get(vLabel1);
        vtx2 = dict.get(vLabel2);
        // update matrix with new edge
        Edge<V,E> e = new Edge<V,E>(vtx1.label(),vtx2.label(),label,false);
        data[vtx1.index()][vtx2.index()] = e;
        data[vtx2.index()][vtx1.index()] = e;
    }

    /**
     * Remove possible edge between vertices labeled vLabel1 and vLabel2.
     *
     * @pre vLabel1 and vLabel2 are labels of existing vertices
     * @post edge is removed, its label is returned
     * 
     * @param vLabel1 One vertex.
     * @param vLabel2 Another vertex.
     * @return The label associated with the edge removed.
     */
    @SuppressWarnings("unchecked")
    public E removeEdge(V vLabel1, V vLabel2)
    {
        // get indices
        int row = dict.get(vLabel1).index();
        int col = dict.get(vLabel2).index();
        // cache old value
        Edge<V,E> e = (Edge<V,E>)data[row][col];
        // update matrix
        data[row][col] = null;
        data[col][row] = null;
        if (e == null) return null;
        else return e.label();
    }

    /**
     * Determine the number of edges in graph.
     *
     * @post returns the number of edges in graph
     * 
     * @return Number of edges in graph.
     */
    public int edgeCount()
    {
        // count non-null entries in table
        int sum = 0;                
        for (int row=0; row<size; row++) 
            for (int col=row; col<size; col++)
                if (data[row][col] != null) sum++;
        return sum;
    }
          
    /**
     * Construct an traversal over all edges.
     * edge is considered exactly once.  Order is not guaranteed.
     *
     * @post returns traversal across all edges of graph (returns Edges)
     * 
     * @return AbstractIterator over edges.
     */
    @SuppressWarnings("unchecked")
    public Iterator<Edge<V,E>> edges()
    {
        List<Edge<V,E>> list = new SinglyLinkedList<>();
        for (int row=size-1; row>=0; row--) 
            for (int col=size-1; col >= row; col--) {
                Edge<V,E> e = (Edge<V,E>)data[row][col];
                if (e != null) list.add(e);
            }
        return list.iterator();
    }

    /**
     * Construct a string representation of graph.
     *
     * @post returns string representation of graph
     * 
     * @return String representing graph.
     */
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        Iterator<V> source = iterator();
        Iterator<V> dest;

        s.append("<GraphMatrixUndirected:");
        while (source.hasNext()) {
            V srcValue = source.next();
            s.append(" ("+srcValue+"->");
            dest = neighbors(srcValue);
            while (dest.hasNext()) {
                s.append(srcValue+"->"+dest.next());
            }
            s.append(")");
        }
        s.append(">");
        return s.toString();
    }
}
