import java.lang.Math;
import structure5.*;
import java.util.Iterator;
/**
 * 
 * @version $Id: bookExamples.java 26 2006-08-24 14:29:13Z bailey $
 * @author, 2001 duane a. bailey
 */
// Image compression barrel for downlink to robotic cow tipper.
// (c) 2001, 2002 duane r. bailey
public class bookExamples {
    public static void main(String[] args)
    {
        double x;
        for (x = 0.0; x <= 2000.0; x += 2.5) {
            double y = sqrt(x);
            Assert.condition(Math.abs(y*y-x)<.00000001,"sqrt function works.");
            System.out.print("Square root of ");
            System.out.print(x);
            System.out.print(" is ");
            System.out.println(sqrt(x));
        }
        sqrt(-1);
    }

// This example used in describe preconditions and postconditions.
    /**
     * @param x 
     * @return 
     */
    public static double sqrt(double x)
    // pre: x is nonnegative
    // post: returns the square root of x
    {
        Assert.pre(x >= 0,"the value is nonnegative.");
        double guess = 1.0;
        double guessSquared = guess * guess;

        while (Math.abs(x-guessSquared) >= 0.00000001) {
            // guess is off a bit, adjust
            guess += (x-guessSquared)/2.0/guess;
            guessSquared = guess*guess;
        }
        return guess;
    }

/*
structure.FailedPrecondition:
Assertion that failed: A precondition: the value is nonnegative.
        at Assert.pre(Assert.java:17)
        at sqrt(examples.java:24)
        at main(examples.java:15)
 */

    static void reachableFrom(Graph<V,E> g, V vertexLabel)
    // pre: g is a non-null graph, vertexLabel labels a vertex of g
    // post: unvisited vertices reachable from vertex are visited
    {
        g.visit(vertexLabel);   // visit this vertex

        // recursively visit unvisited neighbor vertices
        Iterator<V> ni = g.neighbors(vertexLabel);
        while (ni.hasNext())
        {
            V neighbor = ni.next(); // adjacent node label
            if (!g.isVisited(neighbor))
            {
                reachableFrom(g,neighbor); // depth-first search
            }
        }
    }
    static void ReachableFromTest()
    {
        Graph<V,E> g = new GraphMatrixUndirected<V,E>(100);
        String destinationLabel = "there";
        String sourceLabel = "here";
        boolean canGetThere;
    g.reset();
    reachableFrom(g,sourceLabel);
    canGetThere = g.isVisited(destinationLabel);
    }
    static void warshall(Graph<V,E> g)
    // pre: g is non-null
    // post: g contains edge (a,b) if there is a path from a to b
    {
        Iterator<V> witer = g.iterator();

        while (witer.hasNext())
        {   
            Iterator<V> uiter = g.iterator();
            V w = witer.next();
            while (uiter.hasNext())
            {
                Iterator<V> viter = g.iterator();
                V u = uiter.next();
                while (viter.hasNext())
                {
                    V v = viter.next();
                    // check for edge from u to v via w
                    if (g.containsEdge(u, w) &&
                        g.containsEdge(w, v))
                    {
                        g.addEdge(u, v, null);
                    }   
                }
            }
        }
    }
    static void floyd(Graph<V,E> g)
    // post: g contains edge (a,b) if there is a path from a to b
    {
        Iterator<V> witer = g.iterator();

        while (witer.hasNext())
        {
            Iterator<V> uiter = g.iterator();
            V w = witer.next();
            while (uiter.hasNext())
            {
                Iterator<V> viter = g.iterator();
                V u = uiter.next();
                while (viter.hasNext())
                {
                    V v = viter.next();
                    if (g.containsEdge(u,w) && g.containsEdge(w,v))
                    {
                        Edge<V,E> leg1 = g.getEdge(u,w);
                        Edge<V,E> leg2 = g.getEdge(w,v);
                        int leg1Dist = leg1.label();
                        int leg2Dist = leg2.label();
                        int newDist = leg1Dist+leg2Dist;

                        if (g.containsEdge(u,v))
                        {
                            Edge<V,E> across = g.getEdge(u,v);
                            int acrossDist = across.label();
                            if (newDist < acrossDist)
                                across.setLabel(newDist);
                        } else {
                            g.addEdge(u,v,newDist);
                        }
                    }   
                }
            }
        }
    }
    public static void subtypingTest()
{
    GraphMatrix<String,String> g = new GraphMatrixDirected<String,String>();

    g.add("Alice");
    g.add("Bob");
    g.addEdge("Alice","Bob","helps"); // "Alice helps Bob!"
}
    /**
     * <dl>
     * <dt><b>Precondition:</b><dd> g is non-null
     * <dt><b>Postcondition:</b><dd> returns list of all vertices of g, topologically ordered
     * </dl>
     * 
     * @param g 
     * @return 
     */
    public static List<V> topoSort(Graph<V,E> g)
    // pre: g is non-null
    // post: returns list of all vertices of g, topologically ordered
    {
        // construct result list
        List<V> l = new DoublyLinkedList<V>();
        Iterator<V> vi = g.elements();
        while (vi.hasNext())
        {
            V v = vi.next();
            // perform depth-first search on unvisited vertices
            if (!g.isVisited(v))
            {
                DFS(g,v,l);
            }
        }
        // result is queue of vertex labels
        return l;
    }

    static protected void DFS(Graph<V,E> g, V n, List<V> l)
    // post: performs depth-first search enqueuing
    //       unvisited descendants of node n into l
    {
        g.visit(n); // mark node visited
        Iterator<V> ei = g.neighbors(n); // get neighbors
        while (ei.hasNext())
        {
            V neighbor = ei.next();
            // potentially deepen search if neighbor not visited
            if (!g.isVisited(neighbor)) {
                DFS(g,neighbor,l);
            }
        }
        l.addLast(n); // add this value once decendants added
    }
static void main(String args[])
{
    OrderedVector<String> v = new OrderedVector<String>();

    v.add("Michael's Pizza");
    v.add(1,"Cozy Pizza");
    v.add(0,"Hot Tomatoes Pizza");;
}
    // an iterator to view values of structure s
    Iterator<T> sIter;
    
    // construct iterator and print out values of s
    sIter = s.iterator();
    while (sIter.hasNext())
    {           
        System.out.println(sIter.next());
    }
    Vector<T> v = ...;
        ...
    for (int i = 0; i < v.size(); i++) {
        System.out.println(v.get(i));
    }
    /**
     * <dl>
     * <dt><b>Postcondition:</b><dd> returns true iff the tree rooted at n is full.
     * </dl>
     * 
     * @param n 
     * @return 
     */
    public boolean isFull()
    // post: returns true iff the tree rooted at n is full
    {
        int h = height();
        int s = size();
        return s == (1<<(h+1))-1;
    }
}
