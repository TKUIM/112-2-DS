// A private class implementing an iterator over a StringVector.
// (c) 1998, 2001 duane a. bailey

import structure5.AbstractIterator;
/**
 * A private class for implementing an iterator over a StringVector.
 * <p>
 * Typical usage:
 * <pre>
 *     import structure5.StringVector;
 *     import java.util.Iterator;
 *     public static void main(String[] args)
 *     {
 *         StringVector argVec = new StringVector();
 *         for (int i = 0; i < args.length; i++)
 *         {
 *             argVec.addElement(args[i]);
 *         }
 *         for (String it : argVec)
 *         {
 *             System.out.println(it);
 *         }
 *     }
 * </pre>
 * @version $Id: StringVectorIterator.java 26 2006-08-24 14:29:13Z bailey $
 * @author, 2001 duane a. bailey
 */
class StringVectorIterator extends AbstractIterator<String>
{
    /**
     * The associated vector
     */
    protected StringVector theVector;
    /**
     * The index of the current value.
     */
    protected int current;

    /**
     * Construct a vector iterator to traverse vector v
     *
     * @post constructs an initialized iterator associated with v
     * 
     * @param v The underlying vector.
     */
    public StringVectorIterator(StringVector v)
    {
        theVector = v;
        reset();
    }

    /**
     * Reset the vector iterator to the first value in the vector.
     *
     * @post the iterator is reset to the beginning of the traversal
     */
    public void reset()
    {
        current = 0;
    }

    /**
     * Determine if some of the elements have yet to be considered.
     *
     * @post returns true if there is more structure to be traversed
     * 
     * @return True if more elements are to be considered.
     */
    public boolean hasNext()
    {
        return current < theVector.size();
    }

    /**
     * Fetch a reference to the current value.
     *
     * @pre traversal has more elements
     * @post returns the current value referenced by the iterator 
     * 
     * @return A reference to the current value being considered.
     */
    public String get()
    {
        return theVector.get(current);
    }
    
    /**
     * Return current value, and increment iterator.
     *
     * @pre traversal has more elements
     * @post increments the iterated traversal
     * 
     * @return A reference to the current value, before increment.
     */
    public String next()
    {
        return theVector.get(current++);
    }
}

