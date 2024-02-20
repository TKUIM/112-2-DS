// A comparator that implements the reverse of another Comparator.
// (c) 2001 duane a. bailey

package structure;

import java.util.Comparator;
public class ReverseComparator implements Comparator
{
    protected Comparator base; // comparator whose ordering is reversed

    /**
     * Construct a comparator that generates reverse natural comparison
     * @post constructs a comparator that orders in reverse order
     */
    public ReverseComparator()
    {
        base = new NaturalComparator();
    }

    /**
     * Construct a comparator that generates reverse of another comparator
     * @param base the ordering to be reversed
     * @post constructs a Comparator that orders in reverse order of base
     */
    public ReverseComparator(Comparator base)
    {
        this.base = base;
    }

    /**
     * Compare two values, a and b.  Simply calls the default
     * compareTo method for a on b.
     * @param a object performing the compare
     * @param b the object being compared
     * @pre a, b non-null, and b is of type of a
     * @post returns value <, ==, > 0 if a <, ==, > b
     * @return value <, ==, > 0 if a <, ==, > b using a.compareTo
     */
    public int compare(Object a, Object b)
    {
        return -base.compare(a,b);
    }

    /**
     * Returns true if the other object is a NaturalComparator.
     * @param b a possible NaturalComparator
     * @post returns true if b is a NaturalComparator
     * @return true if b is a NaturalComparator
     */
    public boolean equals(Object b)
    {
        if (b == null) return false;
        if (!(b instanceof ReverseComparator)) return false;
        ReverseComparator that = (ReverseComparator)b;
        return this.base.equals(that.base);
    }
}
