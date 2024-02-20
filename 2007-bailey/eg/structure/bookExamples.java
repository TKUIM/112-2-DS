import java.lang.Math;
import structure.*;
import java.util.Iterator;
/**
 * 
 * @version $Id: bookExamples.java 19 2006-08-10 04:52:00Z bailey $
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

    static void reachableFrom(Graph g, Object vertexLabel)
    // pre: g is a non-null graph, vertexLabel labels a vertex of g
    // post: unvisited vertices reachable from vertex are visited
    {
        g.visit(vertexLabel);   // visit this vertex

        // recursively visit unvisited neighbor vertices
        Iterator ni = g.neighbors(vertexLabel);
        while (ni.hasNext())
        {
            Object neighbor = ni.next(); // adjacent node label
            if (!g.isVisited(neighbor))
            {
                reachableFrom(g,neighbor); // depth-first search
            }
        }
    }
    static void ReachableFromTest()
    {
        Graph g = new GraphMatrixUndirected(100);
        String destinationLabel = "there";
        String sourceLabel = "here";
        boolean canGetThere;
    g.reset();
    reachableFrom(g,sourceLabel);
    canGetThere = g.isVisited(destinationLabel);
    }
    static void warshall(Graph g)
    // pre: g is non-null
    // post: g contains edge (a,b) if there is a path from a to b
    {
        Iterator witer = g.iterator();

        while (witer.hasNext())
        {   
            Iterator uiter = g.iterator();
            Object w = witer.next();
            while (uiter.hasNext())
            {
                Iterator viter = g.iterator();
                Object u = uiter.next();
                while (viter.hasNext())
                {
                    Object v = viter.next();
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
    static void floyd(Graph g)
    // post: g contains edge (a,b) if there is a path from a to b
    {
        Iterator witer = g.iterator();

        while (witer.hasNext())
        {
            Iterator uiter = g.iterator();
            Object w = witer.next();
            while (uiter.hasNext())
            {
                Iterator viter = g.iterator();
                Object u = uiter.next();
                while (viter.hasNext())
                {
                    Object v = viter.next();
                    if (g.containsEdge(u,w) && g.containsEdge(w,v))
                    {
                        Edge leg1 = g.getEdge(u,w);
                        Edge leg2 = g.getEdge(w,v);
                        int leg1Dist = 
                             ((Integer)leg1.label()).intValue();
                        int leg2Dist = 
                             ((Integer)leg2.label()).intValue();
                        int newDist = leg1Dist+leg2Dist;

                        if (g.containsEdge(u,v))
                        {
                            Edge across = g.getEdge(u,v);
                            int acrossDist =
                                ((Integer)across.label()).intValue();
                            if (newDist < acrossDist)
                                across.setLabel(new Integer(newDist));
                        } else {
                            g.addEdge(u,v,new Integer(newDist));
                        }
                    }   
                }
            }
        }
    }
    public static void subtypingTest()
{
    GraphMatrix g = new GraphMatrixDirected();

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
    public static List topoSort(Graph g)
    // pre: g is non-null
    // post: returns list of all vertices of g, topologically ordered
    {
        // construct result list
        List l = new DoublyLinkedList();
        Iterator vi = g.elements();
        while (vi.hasNext())
        {
            Object v = vi.next();
            // perform depth-first search on unvisited vertices
            if (!g.isVisited(v))
            {
                DFS(g,v,l);
            }
        }
        // result is queue of vertex labels
        return l;
    }

    static protected void DFS(Graph g, Object n, List l)
    // post: performs depth-first search enqueuing
    //       unvisited descendants of node n into l
    {
        g.visit(n); // mark node visited
        Iterator ei = g.neighbors(n); // get neighbors
        while (ei.hasNext())
        {
            Object neighbor = ei.next();
            // potentially deepen search if neighbor not visited
            if (!g.isVisited(neighbor)) {
                DFS(g,neighbor,l);
            }
        }
        l.addLast(n); // add this value once decendants added
    }
static void main(String args[])
{
    OrderedVector v = new OrderedVector();

    v.add("Michael's Pizza");
    v.add(1,"Cozy Pizza");
    v.add(0,"Hot Tomatoes Pizza");;
}
    // an iterator to view values of structure s
    Iterator sIter;
    
    // construct iterator and print out values of s
    sIter = s.iterator();
    while (sIter.hasNext())
    {           
        System.out.println(sIter.next());
    }
    Vector v = ...;
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
