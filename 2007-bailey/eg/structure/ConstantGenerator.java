public class ConstantGenerator extends AbstractGenerator
{
    /**
     * Construct a generator that generates infinite numbers 
     * of constant values.  The constant value is determined in the
     * initial call to the construcotr.
     * @pre c is the value to be generated.
     * @post constructs a generator that returns a constant value
     */
    public ConstantGenerator(int c)
    {
        set(c);
    }

    /**
     * Generate the next value in the constant sequence (ie. do nothing)
     * @post returns the constant value
     * @return the constant value
     */
    public int next()
    {
        return get();
    }
}


