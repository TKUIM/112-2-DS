// An interface for an ordered structure that allows you to remove min elts
// (c) 1998, 2001 duane a. bailey
package structure;

// ideally this would extend linear, but there are problems....
/**
 * Interface describing an queue of prioritized values.
 * This linear-like structure has values that
 * are inserted in such a way as to allow them to be removed in
 * increasing order.
 *
 * @version $Id: PriorityQueue.java 8 2006-08-02 19:03:11Z bailey $
 * @author, 2001 duane a. bailey
 */
public interface PriorityQueue
{
    /**
     * Fetch lowest valued (highest priority) item from queue.
     *
     * @pre !isEmpty()
     * @post returns the minimum value in priority queue
     * 
     * @return The smallest value from queue.
     */
    public Comparable getFirst();

    /**
     * Returns the minimum value from the queue.
     *
     * @pre !isEmpty()
     * @post returns and removes minimum value from queue
     * 
     * @return The minimum value in the queue.
     */
    public Comparable remove();

    /**
     * Add a value to the priority queue.
     *
     * @pre value is non-null comparable
     * @post value is added to priority queue
     * 
     * @param value The value to be added.
     */
    public void add(Comparable value);

    /**
     * Determine if the queue is empty.
     *
     * @post returns true iff no elements are in queue
     * 
     * @return True if the queue is empty.
     */
    public boolean isEmpty();

    /**
     * Determine the size of the queue.
     *
     * @post returns number of elements within queue
     * 
     * @return The number of elements within the queue.
     */
    public int size();

    /**
     * Remove all the elements from the queue.
     *
     * @post removes all elements from queue
     */
    public void clear();
}
