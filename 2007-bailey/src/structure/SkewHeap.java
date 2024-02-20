// Implementation of priority queues/heaps using binary trees.
// (c) 1998, 2001 duane a. bailey
package structure;
import java.util.Iterator;

/**
 * An implementation of a priority queue using skew heaps.  Skew heaps
 * allow one to construct heaps dynamically without explictly balancing
 * the heaps.  Main operation is a merge.
 *
 * @version $Id: SkewHeap.java 8 2006-08-02 19:03:11Z bailey $
 * @author, 2001 duane a. bailey
 */
public class SkewHeap implements PriorityQueue
{
    /**
     * The root of the skew heap.
     */
    protected BinaryTree root;
    /**
     * The number of nodes within heap.
     */
    protected int count;

    /**
     * Constructs an empty priority queue.
     *
     * @post creates an empty priority queue
     */
    public SkewHeap()
    {
        root = BinaryTree.EMPTY;        
        count = 0;
    }

    /**
     * Fetch lowest valued (highest priority) item from queue.
     *
     * @pre !isEmpty()
     * @post returns the minimum value in priority queue
     * 
     * @return The smallest value from queue.
     */
    public Comparable getFirst()
    {
        return (Comparable)(root.value());
    }

    /**
     * Returns the minimum value from the queue.
     *
     * @pre !isEmpty()
     * @post returns and removes minimum value from queue
     * 
     * @return The minimum value in the queue.
     */
    public Comparable remove()
    {
        Comparable result = (Comparable)(root.value());
        root = merge(root.left(),root.right());
        count--;
        return result;  
    }

    /**
     * Add a value to the priority queue.
     *
     * @pre value is non-null comparable
     * @post value is added to priority queue
     * 
     * @param value The value to be added.
     */
    public void add(Comparable value)
    {
        BinaryTree smallTree = new BinaryTree(value);
        root = merge(smallTree,root);
        count++;
    }

    /**
     * Determine the size of the queue.
     *
     * @post returns number of elements within queue
     * 
     * @return The number of elements within the queue.
     */
    public int size()
    {
        return count;
    }

    /**
     * Remove all the elements from the queue.
     *
     * @post removes all elements from queue
     */
    public void clear()
    {
        root = BinaryTree.EMPTY;
    }

    /**
     * Determine if the queue is empty.
     *
     * @post returns true iff no elements are in queue
     * 
     * @return True if the queue is empty.
     */
    public boolean isEmpty()
    {
        return size() == 0;
    }

    protected static BinaryTree merge(BinaryTree left,
                                          BinaryTree right)
    {
        if (left.isEmpty()) return right;
        if (right.isEmpty()) return left;
        Comparable leftVal = (Comparable)(left.value());
        Comparable rightVal = (Comparable)(right.value());
        BinaryTree result;
        if (rightVal.compareTo(leftVal) < 0)
        {
            result = merge(right,left);
        } else {
            result = left;
            // assertion left side is smaller than right
            // left is new root
            if (result.left().isEmpty())
            {
                result.setLeft(right);
            } else {
                BinaryTree temp = result.right();
                result.setRight(result.left());
                result.setLeft(merge(temp,right));
            }
        }
        return result;
    }

    /**
     * Construct a string representation of the heap.
     *
     * @post returns string representation of heap
     * 
     * @return The string representing the heap.
     */
    public String toString()
    {
        if (root.isEmpty()) return "<SkewHeap: >";
        StringBuffer sb = new StringBuffer();
        sb.append("<SkewHeap:");
        if (!root.isEmpty()) {
            Iterator i = root.iterator();
            while (i.hasNext())
            {
                sb.append(" "+i.next());
            }
        }
        return sb+">";
    }

}
