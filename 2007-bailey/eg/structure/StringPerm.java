import structure.*;
import java.util.Iterator;
public class StringPerm implements Iterator
{
    protected Iterator t;
    protected String theString;

    public void remove()
    {}

    public StringPerm(String s)
    {
        t = new Perm2(s.length());
        theString = s;
    }

    public boolean hasNext()
    {
        return t.hasNext();
    }

    public Object next()
    {
        String result = "";
        int shuffle[] = (int[])t.next();

        for (int i = 0; i < theString.length(); i++)
        {
            result += theString.charAt(shuffle[i]);
        }

        return result;
    }

    public static void main(String[] args)
    {
        java.util.Iterator i = new StringPerm(args[0]);
        while (i.hasNext())
        {
            System.out.println(i.next());
        }
    }
}
