import java.util.Iterator;
abstract public class AbstractGenerator
//    extends AbstractIterator<Integer>
    implements Generator
{
    protected int current;  // the last value saved

    /**
     * A constructor that initializes the current value
     * @param initial the first value generated by the class.
     * @post initialize the current value to initial
     */
    public AbstractGenerator(int initial)
    {
        current = initial;
    }

    /**
     * A constructor that initializes any base class data.
     * @post initialize the current value to zero
     */
    public AbstractGenerator()
    {
        this(0);
    }

    /**
     * A protected method to set the current value.
     * This method is frequently called by implementations of  next.
     * @param next the next value of this generator.
     * @post sets the current value to next, and extends the sequence
     * @return returns the previous value of current
     */
    protected int set(Integer next)
    {
        int result = current;
        current = next;
        return result;
    }

    /**
     * Fetch the current value of the sequence.
     * @post returns the current value of the sequence
     */
    public int get()
    {
        return current;
    }

    /**
     * Reset - does nothing by default
     * @post resets the Generator (by default, does nothing)
     */
    public void reset()
    {
    }

    /**
     * Return true iff there are more values to be generated.
     * @post true iff there are more values to be generated
     * @return true iff there are more values to be generated
     */
    public boolean hasNext()
    {
        return true;
    }
}
