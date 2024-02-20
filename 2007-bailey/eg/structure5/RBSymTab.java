// A simple symbol table for a postscript interpreter.
// (c) 2001,1996, 2001 duane a. bailey

import structure5.*;
import java.util.Iterator;
public class RBSymTab<S extends Comparable<S>,T>
{
    protected RedBlackTree<ComparableAssociation<S,T>> table;

    public RBSymTab()
    // post: constructs empty symbol table
    {
        table = new RedBlackTree<ComparableAssociation<S,T>>();
    }

    public boolean contains(S symbol)
    // pre: symbol is non-null string
    // post: returns true iff string in table
    {
        return table.contains(new ComparableAssociation<S,T>(symbol,null));
    }

    public void add(S symbol, T value)
    // pre: symbol non-null
    // post: adds/replaces symbol-value pair in table
    {
        ComparableAssociation<S,T> a = new ComparableAssociation<S,T>(symbol,value);
        if (table.contains(a)) table = table.remove(a);
        table = table.add(a);
    }

    public T get(S symbol)
    // pre: symbol non-null
    // post: returns token associated with symbol
    {
        ComparableAssociation<S,T> a = new ComparableAssociation<S,T>(symbol,null);
        if (table.contains(a)) {
            a = table.get(a);
            return a.getValue();
        } else {
            return null;
        }
    }

    public T remove(S symbol)
    // pre: symbol non-null
    // post: removes value associated with symbol and returns it
    //       if error returns null
    {
        ComparableAssociation<S,T> a = new ComparableAssociation<S,T>(symbol,null);
        if (table.contains(a)) {
            a = table.get(a);
            table = table.remove(a);
            return a.getValue();
        } else {
            return null;
        }
    }

    public static void main(String args[])
    {
        RBSymTab<String,String> table = new RBSymTab<String,String>();
        ReadStream r = new ReadStream();
        String alias, name;
        // read in the alias-name database
        do
        {
            alias = r.readString();
            if (!alias.equals("END"))
            {
                name = r.readString();
                table.add(alias,name);
            }
        } while (!alias.equals("END"));
        // enter the alias translation stage
        do
        {
            name = r.readString();
            while (table.contains(name))
            {
                name = (String)table.get(name); // translate alias
            }
            System.out.println(name);
            r.skipWhite();
        } while (!r.eof());
    }
}

/*
three 3
one unity
unity 1
pi three
END

one
two
three
pi
1
two
3
3
*/
