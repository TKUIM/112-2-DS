import structure.*;
import java.util.Iterator;
import java.util.Collection;

public class VectorList extends AbstractStructure
{
    protected Vector data;

    public VectorList()
    {
        data = new Vector();
    }

    public Iterator iterator()
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

    public void add(Object value)
    // post: value is added to beginning of list (see addToHead)
    {
        addToHead(value);
    }

    public void addToHead(Object value)
    // post: value is added to beginning of list
    {
        data.add(0,value);
    }

    public void addToTail(Object value)
    // post: value is added to end of list
    {
        data.add(value);
    }

    public Object peek()
    // pre: list is not empty
    // post: returns first value in list
    {
        return data.get(0);
    }

    public Object tailPeek()
    // pre: list is not empty
    // post: returns last value in list
    {
        return data.get(size()-1);
    }

    public Object removeFromHead()
    // pre: list is not empty
    // post: removes first value from the list
    {
        Object o = data.get(0);
        data.remove(0);
        return o;
    }

    public Object removeFromTail()
    // pre: list is not empty
    // post: removes the last value from the list
    {
        Object o = data.get(size()-1);
        data.remove(size()-1);
        return o;
    }

    public boolean contains(Object value)
    // pre: value is not null
    // post: returns true iff list contains an object equal to value
    {
        for (int i = 0; i < size(); i++)
        {
            if (data.get(i).equals(value)) return true;
        }
        return false;
    }

    public Object remove(Object value)
    // post: removes and returns element equal to value
    //       otherwise returns null
    {
        for (int i = 0; i < size(); i++)
        {
            if (data.get(i).equals(value))
            {
                Object o = data.get(i);
                data.remove(i);
                return o;
            }
        }
        return null;
    }
}
