// Example of an iterator for generating permutations of a vector of elements.
// (c) 1997, 1998, 2001 duane a. bailey
import structure.*;
import java.util.Iterator;

/*
 * Given an initial vector of values, this Iterator generates a sequence
 * of vectors that span the permutations of the original vector.
 *
 * The "original" field references the original vector, which
 * is never touched.  "size" indicates the length of this vector.
 *
 * The "current" field is a similar sized vector with the references 
 * within the orignal vector permuted.
 *
 * The "map" field keeps track of the location of the i-th element of the
 * current vector in the original.  Current[i] == original[map[i]].
 *
 * The "free" field indicates whether, for each i, the value at current[i]
 * is free to be moved somewhere else.
 *
 * Variables "done" and "doneNext" keep track of the progress through
 * the iteration.  When the elements of the original vector are reversed
 * in the current array, that is the last permutation; we will be done
 * on the next pass.
 */
public class Permutation extends AbstractIterator
{
    protected Vector original;  // reference to original vector
    protected int size;         // size of original vector
    protected Vector current;   // reference to current permutation
    protected int map[];        // map of values from current to original
    protected boolean free[];   // which are free for reassignment
    protected boolean done, doneNext; // state variables

    public static void main(String args[]) // testing code
    {
        Vector seq = new Vector();
        ReadStream r = new ReadStream();
        for (r.skipWhite(); !r.eof(); r.skipWhite())
        {
            seq.add(r.readString());
        }
        Permutation p = new Permutation(seq);
        while (p.hasNext())
        {
            System.out.println(p.next());
        }
    }

    public Permutation(Vector base)
    // pre: base is a vector of Objects
    // post: constructs iterator that considers permutation of vector values.
    {
        original = base;
        size = base.size();
        current = new Vector(size);
        for (int i = 0; i < size; i++)
        {
            current.add(original.get(i));
        }
        map = new int[size];
        free = new boolean[size];
        reset();
    }

    public void reset()
    // post: resets iterator to beginning of permutation sequence
    {
        done = size == 0;       // no permutations of empty vector
        doneNext = size == 1;   // one permutation of scalar value
        for (int i = 0; i < size; i++)
        {
            free[i] = false;
            map[i] = i;
            current.set(i,original.get(i));
        }
    }

    public boolean hasNext()
    // post: returns true if more unconsidered permutations are to be
    //       considered.
    {
        int i;
        int ideal;
        if (done) return false;
        doneNext = true;
        for (i = 0, ideal = size-1; i < size; i++, ideal--)
        {
            if (map[i] != ideal) { doneNext = false; break; }
        }
        return true;
    }

    protected void bump()
    // post: rearranges the elements of the original vector
    {
        int digit = 0;
        int i, place;
        free[map[digit]] = true;
        while ((digit+1 < size) && map[digit] > map[digit+1]) {
            digit++;
            free[map[digit]] = true;
        }
        // map[digit] < map[digit+1]
        digit++;
        free[map[digit]] = true;
        for (i = map[digit]-1; i >= 0; i--)
        {
            if (free[i]) {
                map[digit] = i;
                free[i] = false;
                break;
            }
        }
        for (i = place = 0; place < digit; i++)
        {
            if (free[i])
            {
                map[place++] = i;
                free[i] = false;
            }
        }
    }
    public Object next()
    {
        Vector result = current;
        if (doneNext) done = true;
        if (!done)
        {
            bump();
            current = new Vector(size);
            for (int i = 0; i < size; i++)
            {
                current.add(original.get(map[i]));
            }
        }
        return result;
    }
    public Object get()
    {
        return current;
    }
}
/*
dad was sad
<Vector: dad was sad>
<Vector: was dad sad>
<Vector: dad sad was>
<Vector: sad dad was>
<Vector: was sad dad>
<Vector: sad was dad>
*/
