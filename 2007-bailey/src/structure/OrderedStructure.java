// Interface for container classes that manipulated ordered structures.
// (c) 1998, 2001 duane a. bailey

package structure;

/**
 * An interface the supports a Collection whose values are kept
 * in increasing order.  Values stored within ordered structures
 * should implement Comparable; ie. they should have an implemented
 * lessThan method.
 * 
 * @see java.lang.Comparable
 * @see java.lang.Comparable#compareTo
 * @version $Id: OrderedStructure.java 8 2006-08-02 19:03:11Z bailey $
 * @author, 2001 duane a. bailey
 */
public interface OrderedStructure extends Structure
{
}
