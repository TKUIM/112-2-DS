import structure5.*;
import java.util.Iterator;
import java.util.Collection;

public class VectorList<T> extends AbstractList<T>
{
    protected Vector<T> data;

    public VectorList()
    {
        data = new Vector<T>();
    }

    public Iterator<T> iterator()
    // post: returns an iterator allowing 
    //   ordered traversal of elements in list
    {
        return data.iterator();
    }

    public int size()
    // post: returns number of elements in list
    {
        return data.size();
    }

    public boolean isEmpty()
    // post: returns true iff list has no elements
    {
        return size() == 0;
    }

    public void clear()
    // post: empties list
    {
        data.clear();
    }

    public void add(T value)
    // post: value is added to beginning of list (see addToHead)
    {
        addToHead(value);
    }

    public void add(int i, T value)
    {
        data.add(i,value);
    }

    public void addToHead(T value)
    // post: value is added to beginning of list
    {
        data.add(0,value);
    }

    public void addToTail(T value)
    // post: value is added to end of list
    {
        data.add(value);
    }

    public T peek()
    // pre: list is not empty
    // post: returns first value in list
    {
        return data.get(0);
    }

    public T tailPeek()
    // pre: list is not empty
    // post: returns last value in list
    {
        return data.get(size()-1);
    }

    public T remove(int i)
    {
        Assert.pre((0 <= i) && (i < size()),
                   "Index in range.");
        return data.remove(i);
    }

    public T removeFromHead()
    // pre: list is not empty
    // post: removes first value from the list
    {
        T o = data.get(0);
        data.remove(0);
        return o;
    }

    public T removeFromTail()
    // pre: list is not empty
    // post: removes the last value from the list
    {
        T o = data.get(size()-1);
        data.remove(size()-1);
        return o;
    }

    public T set(int i, T value)
    {
        return data.set(i,value);
    }

    public T get(int i)
    {
        return data.get(i);
    }

    public int indexOf(T value)
    {
        return data.indexOf(value);
    }

    public int lastIndexOf(T value)
    {
        return data.lastIndexOf(value);
    }

    public boolean contains(T value)
    // pre: value is not null
    // post: returns true iff list contains an object equal to value
    {
        for (int i = 0; i < size(); i++)
        {
            if (data.get(i).equals(value)) return true;
        }
        return false;
    }

    public T remove(T value)
    // post: removes and returns element equal to value
    //       otherwise returns null
    {
        for (int i = 0; i < size(); i++)
        {
            if (data.get(i).equals(value))
            {
                T o = data.get(i);
                data.remove(i);
                return o;
            }
        }
        return null;
    }
}
