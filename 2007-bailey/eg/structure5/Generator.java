import java.util.Iterator;
public interface Generator
//       extends Iterator
{
    /**
     * Reset this generator to the beginning of the sequence.
     * @post the generator is reset to the beginning of the sequence;
     *   the current value can be immediately retrieved with get.
     */
    public void reset();

    /**
     * Generate and return the next integer in the sequence to be generated.
     * @post returns true iff more elements are to be generated.
     * @return the next element.
     */
    public int next();

    /**
     * Return the current value of the generator
     * @post returns the current value of the generator.
     * @return the current element.
     */
    public int get();

    /**
     * Return true iff there are more values to be generated.
     * @post true iff there are more values to be generated
     * @return true iff there are more values to be generated
     */
    public boolean hasNext();
}

