// An implementation of extensible arrays with sort add on.
// (c) 1998, 2001, 2002 duane a. bailey

//package structure;
import structure5.*;
import java.util.Comparator;
/**
 * An implemention of extensible arrays, similar to that of java.util.MyVector.
 *
 * This vector class implements a basic extensible array.  It does not implement
 * any of the additional features of the Sun class, including list-like operations.
 * Those operations are available in other implementors of {@link List} in this
 * package.
 * <p>
 * Example usage:
 *
 * To put a program's parameters into a MyVector, we would use the following:
 * <pre>
 * public static void main(String[] arguments)
 * {
 *    {@link MyVector} argVec = new {@link #MyVector()};
 *    for (int i = 0; i < arguments.length; i++)
 *    {
 *       argVec.{@link #add(Object) add(arguments[i])};
 *    }
 *    System.out.println({@link #toString argVec});
 * }
 * </pre>
 *
 * @version $Id: MyVector.java 26 2006-08-24 14:29:13Z bailey $
 * @author, 2001 duane a. bailey (c) 1998, 2001 McGraw-Hill
 * @since JavaElements 1.0
 */
public class MyVector<T> extends structure5.Vector<T>
{
    /**
     * Construct an empty vector.
     * 
     * @post constructs a vector with capacity for 10 elements
     */
    public MyVector()
    // post: constructs an empty vector
    {
        this(10); // call one-parameter constructor
    }
    
    /**
     * Construct an empty vector capable of storing <code>initialCapacity</code>
     * values before the vector must be extended.
     *
     * @pre initialCapacity >= 0
     * @post constructs an empty vector with initialCapacity capacity
     * @param initialCapacity The size of vector before reallocation is necessary
     */
    public MyVector(int initialCapacity)
    // pre: initialCapacity >= 0
    // post: constructs an empty vector with initialCapacity capacity
    {
        super(initialCapacity);
    }

    /**
     * Construct a vector with initial capacity, and growth characteristic.
     *
     * @pre initialCapacity >= 0, capacityIncr >= 0
     * @post constructs an empty vector with initialCapacity capacity
     *    that extends capacity by capacityIncr, or doubles if 0
     * 
     * @param initialCapacity The initial number of slots in vector.
     * @param capacityIncr The size of growth of vector.
     * @see #capacityIncrement
     */
    public MyVector(int initialCapacity, int capacityIncr)
    // pre: initialCapacity >= 0, capacityIncr >= 0
    // post: constructs an empty vector with initialCapacity capacity
    //    that extends capacity by capacityIncr, or doubles if 0
    {
        super(initialCapacity,capacityIncr);
    }

    /**
     * Construct a vector with initial size, growth rate and default
     * value.
     *
     * @pre initialCapacity, capacityIncr >= 0
     * @post constructs empty vector with capacity that begins at
     *       initialCapacity and extends by capacityIncr or doubles
     *       if 0.  New entries in vector are initialized to initValue.
     * 
     * @param initialCapacity The initial number of slots in vector.
     * @param capacityIncr The size of the increment when vector grows.
     * @param initValue The initial value stored in vector elements.
     */
    public MyVector(int initialCapacity, int capacityIncr, T initValue)
    // pre: initialCapacity, capacityIncr >= 0
    // post: constructs empty vector with capacity that begins at
    //       initialCapacity and extends by capacityIncr or doubles
    //       if 0.  New entries in vector are initialized to initValue.
    {
        super(initialCapacity,capacityIncr,initValue);
    }

    public void sort(Comparator<T> c)
    // pre: c is a valid comparator
    // post: sorts this vector in order determined by c
    {
        int target, possible;
        int s = size();
        for (target = 1; target < s; target++)
        {
            T to = get(target);
            for (possible = target-1; possible >= 0; possible--)
            {
                T po = get(possible);
                if (c.compare(po,to) > 0)
                {
                    set(possible+1,po);
                } else break;
            }
            if (possible+1 != target)
            {
                set(possible+1,to);
            }
        }
    }
}

