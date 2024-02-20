import java.util.Enumeration;
import structure5.Vector;
import structure5.AbstractIterator;
import java.util.Iterator;
public class HelloWorld
{
    public static void main2(String args[])
    /*
    public static void main(String args[])
    */
    {
        // construct a vector containing two strings:
        Vector<String> v = new Vector<String>();
        v.add("Hello");
        v.add("world!");
        
        // construct an enumeration to view values of v
        Enumeration i = (Enumeration)v.elements();
        while (i.hasMoreElements())
        {
            // SILLY: v.add(1,"silly");
            System.out.print(i.nextElement()+" ");
        }
        System.out.println();
    }
    public static void main(String args[])
    {
        // construct a vector containing two strings:
        Vector<String> v = new Vector<String>();
        AbstractIterator<String> i;
        v.add("Hello");
        v.add("world!");
        
        // construct an iterator to view values of v
        for (i = (AbstractIterator<String>)v.iterator(); i.hasNext(); i.next())
        {
            System.out.print(i.get()+" ");
        }
        System.out.println();
    }
    /*
    public static void main3(String args[])
    public static void main(String args[])
    {
        // construct a vector containing two strings:
        Vector<String> v = new Vector<String>();
        ...
        for (String word : v)
        {
            System.out.print(word+" ");
        }
        System.out.println();
    }
    */
}

/*
Hello world! 
Hello silly silly silly silly silly silly
*/
