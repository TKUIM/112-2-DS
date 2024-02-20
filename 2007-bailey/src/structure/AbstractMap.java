package structure;
import java.util.Iterator;

public abstract class AbstractMap implements Map
{
    /**
     * @pre other is a valid map
     * @post adds the map entries of other map into this, possibly
     * replacing value
     */
    public void putAll(Map other)
    {
        Iterator i = other.keySet().iterator();
        while (i.hasNext())
        {
            Object k = i.next();
            put(k,other.get(k));
        }
    }

    /**
     * Compute the hashCode for elements of this map
     */
    public int hashCode()
    {
        return values().hashCode();
    }
}
