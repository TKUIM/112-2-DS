import structure.*;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Scanner;
public class RevComparator implements Comparator
{
    protected Comparator base;

    public RevComparator(Comparator baseCompare)
    {
        base = baseCompare;
    }

    public int compare(Object a, Object b)
    {
        return -base.compare(a,b);
    }
    public static void main(String args[])
    {
        MyVector v = new MyVector();
        Scanner s = new Scanner(System.in);

        while (s.hasNextInt())
        {
            v.add(new Integer(s.nextInt()));
        }

        Comparator c = new RevComparator(new IntegerComparator());
        v.sort(c);
        Iterator i = v.iterator();
        while (i.hasNext())
        {
            System.out.println(i.next());
        }
    }

}
