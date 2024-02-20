// A simple symbol table for a postscript interpreter.
// (c) 2001,1996, 2001 duane a. bailey

import structure.*;
import java.util.Iterator;
import java.util.Scanner;
public class SymTab
{
    protected BinarySearchTree table;
    public SymTab()
    // post: constructs empty symbol table
    {
        table = new BinarySearchTree();
    }

    public boolean contains(Comparable symbol)
    // pre: symbol is non-null string
    // post: returns true iff string in table
    {
        ComparableAssociation a = 
            new ComparableAssociation(symbol,null);
        return table.contains(a);
    }

    public void add(Comparable symbol, Object value)
    // pre: symbol non-null
    // post: adds/replaces symbol-value pair in table
    {
        ComparableAssociation a =
            new ComparableAssociation(symbol,value);
        if (table.contains(a)) table.remove(a);
        table.add(a);
    }

    public Object get(Comparable symbol)
    // pre: symbol non null
    // post: returns token associated with symbol
    {
        ComparableAssociation a =
            new ComparableAssociation(symbol,null);
        if (table.contains(a)) {
            a = (ComparableAssociation)table.get(a);
            return a.getValue();
        } else {
            return null;
        }
    }

    public Object remove(Comparable symbol)
    // pre: symbol non null
    // post: removes value associated with symbol and returns it
    //       if error returns null
    {
        ComparableAssociation a =
            new ComparableAssociation(symbol,null);
        if (table.contains(a)) {
            a = (ComparableAssociation)table.remove(a);
            return a.getValue();
        } else {
            return null;
        }
    }

    public static void main(String args[])
    {
        SymTab table = new SymTab();
        Scanner s = new Scanner(System.in);
        String alias, name;
        // read in the alias-name database
        do
        {
            alias = s.next();
            if (!alias.equals("END"))
            {
                name = s.next();
                table.add(alias,name);
            }
        } while (!alias.equals("END"));
        // enter the alias translation stage
        do
        {
            name = s.next();
            while (table.contains(name))
            {
                // translate alias
                name = (String)table.get(name);
            }
            System.out.println(name);
        } while (s.hasNext());
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
