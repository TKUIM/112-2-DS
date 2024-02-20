import java.util.Comparator;
import java.util.Iterator;
import structure.*;
import java.util.Iterator;

public class intComparatorTest
{
    public static void main(String args[])
    {
        MyVector v = new MyVector();
        ReadStream r = new ReadStream();

        for (r.skipWhite(); !r.eof(); r.skipWhite())
        {
            v.add(new Integer(r.readInt()));
        }

        Comparator c = new IntegerComparator();
        v.sort(c);
        Iterator i = v.iterator();
        while (i.hasNext())
        {
            System.out.println(i.next());
        }
        System.out.println("---");
        c = new RevComparator(new IntegerComparator());
        v.sort(c);
        i = v.iterator();
        while (i.hasNext())
        {
            System.out.println(i.next());
        }
    }
}
