import structure5.AbstractIterator;
public class PFGenerator extends AbstractIterator<Integer>
{
    // the original number to be factored
    protected int base;
    // base, reduced by the prime factors discovered
    protected int n;
    // the current prime factor
    protected int f;

    /**
     * Construct an iterator for generating prime factors.
     * @param value the number to be factored
     * @post an iterator is constructed that factors numbers
     */
    public PFGenerator(int value)
    {
        base = value;
        reset();
    }

    /**
     * Resets the iterator back to the task of factoring the original
     * value.
     * @post the iterator is reset to factoring the original value
     */
    public void reset()
    {
        n = base;
        // initial guess at prime factor
        f = 2;
    }

    /**
     * Returns true if the currently reduced value has more prime factors.
     * This can be determined by checking to see if f is no greater than n
     * @post returns true iff there are more prime factors to be considered
     */
    public boolean hasNext()
    {
        return f <= n;          // there is a factor <= n
    }

    /**
     * Returns the current prime factor, and "increments" the iterator
     * @post returns the current prime factor and "increments" the iterator
     */
    public Integer next()
    {
        Integer result = get();  // factor to return
        n /= f;                 // reduce n by factor
        return result;
    }

    /**
     * Returns the current prime factor.
     * @pre hasNext()
     * @post returns the current prime factor
     */
    public Integer get()
    {
        // make sure f is a factor of n
        while (f <= n && n%f != 0) f++;
        return f;
    }

    public static void main(String[]args)
    {
        // for each of the command line arguments
        for (int i = 0; i < args.length; i++)
        {
            // determine the value
            int n = Integer.parseInt(args[i]);
            PFGenerator g = new PFGenerator(n);
            System.out.print(n+": ");
            // and print the prime factors of n
            while (g.hasNext()) System.out.print(g.next()+" ");
            System.out.println();
        }
    }
}
/*
java PFGenerator 3 7 238 31134
3: 3 
7: 7 
238: 2 7 17 
31134: 2 3 5189 
*/



