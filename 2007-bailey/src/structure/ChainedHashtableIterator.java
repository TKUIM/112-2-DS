package structure;
import java.util.Iterator;

/**
 * An iterator to traverse chained hash tables.
 *
 * @version $Id: ChainedHashtableIterator.java 8 2006-08-02 19:03:11Z bailey $
 * @author, 2001 duane a. bailey
 */
class ChainedHashtableIterator extends AbstractIterator
{
    /**
     * The list of values within the table.
     */
    protected List data;
    /**
     * The iterator over the elements of the list.
     */
    protected Iterator elements;

    /**
     * Construct an iterator over a chained hashtable.
     *
     * @post constructs a new hash table iterator
     * @param table The array of lists to be traversed.
     */
    public ChainedHashtableIterator(List[] table)
    {
        int i;
        int capacity = table.length;
        data = new SinglyLinkedList();
        for (i = 0; i < capacity; i++) {
            if (table[i] != null) {
                Iterator els = table[i].iterator();
                while (els.hasNext())
                {
                    data.addFirst(els.next());
                }
            }
        }
        elements = data.iterator();
    }

    /**
     * Resets the iterator to point to the beginning of the chained table.
     *
     * @post resets iterator to beginning of hash table
     */
    public void reset()
    {
        ((AbstractIterator)elements).reset();
    }

    /**
     * Returns true iff there are unconsidered elements within the table.
     *
     * @post returns true if there are unvisited elements
     * 
     * @return True iff there are elements yet to be considered within table.
     */
    public boolean hasNext()
    {
        return elements.hasNext();
    }

    /**
     * Returns current value and increments iterator.
     *
     * @pre hasNext()
     * @post returns current element, increments iterator
     * 
     * @return The current value, before incrementing.
     */
    public Object next()
    {
        return elements.next();
    }

    /**
     * Get current value of iterator.
     *
     * @post returns current element
     * 
     * @return The current value.
     */
    public Object get()
    {
        return ((AbstractIterator)elements).get();
    }
}
