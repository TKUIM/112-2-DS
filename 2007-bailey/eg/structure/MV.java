// An implementation of extensible arrays with sort add on.
// (c) 1998, 2001, 2002 duane a. bailey
import structure.*;
import java.util.Comparator;
public class MyVector extends structure.Vector
{
    public MyVector()
    // post: constructs a vector with capacity for 10 elements
    {
        this(10); // call one-parameter constructor
    }
    
    public MyVector(int initialCapacity)
    // pre: initialCapacity >= 0
    // post: constructs an empty vector with initialCapacity capacity
    {
        super(initialCapacity);
    }

    public MyVector(int initialCapacity, int capacityIncr)
    // pre: initialCapacity >= 0, capacityIncr >= 0
    // post: constructs an empty vector with initialCapacity capacity
    //    that extends capacity by capacityIncr, or doubles if 0
    {
        super(initialCapacity,capacityIncr);
    }

    public MyVector(int initialCapacity, int capacityIncr, Object initValue)
    // pre: initialCapacity, capacityIncr >= 0
    // post: constructs empty vector with capacity that begins at
    //       initialCapacity and extends by capacityIncr or doubles
    //       if 0.  New entries in vector are initialized to initValue.
    {
        super(initialCapacity,capacityIncr,initValue);
    }

    public void sort()
    // post: sorts this vector in order determined by elements
    {
        sort(new structure.NaturalComparator());
    }

    public void sort(Comparator c)
    // pre: c is a valid comparator
    // post: sorts this vector in order determined by c
    {
        int target, possible;
        int s = size();
        for (target = 1; target < s; target++)
        {
            Object to = get(target);
            for (possible = target-1; possible >= 0; possible--)
            {
                Object po = get(possible);
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

/*
 * $Log: MyVector.java,v $
 * Revision 4.1  2000/12/29 02:25:16  bailey
 * *** empty log message ***
 *
 * Revision 4.0  2000/12/27 20:57:33  bailey
 * Base for second edition release for use with JDK1.2/3
 *
 * Revision 3.2  1998/01/22 02:20:27  bailey
 * Updated comment on insertElementAt.
 *
 * Revision 3.1  1998/01/21 12:52:34  bailey
 * *** empty log message ***
 *
 * Revision 3.0  1998/01/12 16:03:23  bailey
 * Initial JDK 1.2 version.
 *
 * Revision 2.4  1998/01/12 15:47:04  bailey
 * Beta release.
 *
 * Revision 2.3  1998/01/06 17:55:15  bailey
 * Updated copyright for McGraw-Hill
 *
 * Revision 2.2  1997/08/08 12:45:28  bailey
 * Fix versioning problem.
 *
 * Revision 1.17  1997/04/03 18:05:52  bailey
 * Mods including toString, documentation, etc.
 *
 * Revision 1.16  1997/04/03 02:25:42  bailey
 * Removed javadoc comments.
 *
 * Revision 1.15  1997/04/03 02:16:19  bailey
 * *** empty log message ***
 *
 * Revision 1.14  1997/01/09 16:40:06  bailey
 * *** empty log message ***
 *
 * Revision 1.13  1996/09/20 13:16:19  bailey
 * Fixed bad comparison in "contains".
 *
 * Revision 1.12  1996/08/29 16:59:54  bailey
 * Moved from cs136 to structure.
 *
 * Revision 1.11  1996/08/23 02:18:32  bailey
 * Added automatically generated javadoc commenting.
 *
 * Revision 1.10  1996/08/20 02:39:12  bailey
 * Revised pre & post conditions, extract comments, & casts.
 *
 * Revision 1.9  1996/08/07 11:54:51  bailey
 * Changed 'includes' to 'contains'.
 *
 * Revision 1.8  1996/08/02 12:16:17  bailey
 * Added logging comments.
 *
 * Revision 1.7
 * 1996/08/02 11:38:56 bailey
 * Minor mods?
 * 
 * Revision 1.6
 * 1996/07/11 02:19:57 bailey
 * Added extraction methods.
 * 
 * Revision 1.5
 * 1996/06/25 18:43:51 bailey
 * Added basic assertions.
 * 
 * Revision 1.4
 * 1996/06/06 15:23:08 bailey
 * Added versioning information.
 * 
 * Revision 1.3
 * 1996/06/06 01:04:40 bailey
 * Fixed trimToSize and size.
 * 
 * Revision 1.2
 * 1996/06/05 16:41:52 bailey
 * Fixed problem with size.
 * 
 * Revision 1.1
 * 1996/06/05 13:28:17 bailey
 * Initial revision
 */
