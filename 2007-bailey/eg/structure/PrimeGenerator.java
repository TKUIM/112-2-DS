public class PrimeGenerator extends AbstractGenerator
{
    /**
     * @post construct a generator that delivers primes starting at 2.
     */
    public PrimeGenerator()
    {
        reset();
    }
    
    /**
     * @post reset the generator to return primes starting at 2
     */
    public void reset()
    {
        set(2);
    }
    
    /**
     * Generate the next prime
     * @post generate the next prime
     */
    public int next()
    {
        int f,n = get();
        do
        {
            if (n == 2) n = 3;
            else n += 2;
        
            // check the next value
            for (f = 2; f*f <= n; f++)
            {
                if (0 ==(n % f)) break;
            }
        } while (f*f <= n);
        set(n);
        return n;
    }
}
