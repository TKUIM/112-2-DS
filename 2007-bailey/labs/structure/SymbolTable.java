// A simple symbol table for a postscript interpreter.
// (c) 2001,1996, 2001 duane a. bailey

import structure.*;
import java.util.Iterator;
public class SymbolTable
{
    protected List table;     // the table is a list of associations.

    public SymbolTable()
    // post: constructs empty symbol table
    {
        table = new DoublyLinkedList();
    }

    public boolean contains(String symbol)
    // pre: symbol is non-null string
    // post: returns true iff string in table
    {
        Association a = new Association(symbol,null);
        return table.contains(a);
    }

    public void add(String symbol, Token value)
    // pre: symbol non-null
    // post: adds/replaces symbol-value pair in table
    {
        Association a = new Association(symbol,value);
        if (table.contains(a)) table.remove(a);
        table.addFirst(a);
    }

    public Token get(String symbol)
    // pre: symbol non null
    // post: returns token associated with symbol
    {
        Association a = new Association(symbol,null);
        if (table.contains(a)) {
            a = (Association)table.remove(a);
            table.addFirst(a);
            return (Token)a.getValue();
        } else {
            return null;
        }
    }

    public Token remove(String symbol)
    // pre: symbol non null
    // post: removes value associated with symbol and returns it
    //       if error returns null
    {
        Association a = new Association(symbol,null);
        if (table.contains(a)) {
            a = (Association)table.remove(a);
            return (Token)a.getValue();
        } else {
            return null;
        }
    }

    public String toString()
    // pre: returns printable version of symbol table.  Use with ptable.
    {
        Iterator i = table.iterator();
        String result = "";
        while (i.hasNext())
        {
            Association a = (Association)i.next();
            result = result + a.getKey() + "=" + a.getValue() + "\n";
        }
        return result;
    }

    public static void main(String args[])
    {
        SymbolTable table = new SymbolTable();
        // sometime later:
        table.add("pi",new Token(3.141592653));
        // sometime even later:
        if (table.contains("pi"))
        {
            Token token = table.get("pi");
            System.out.println(token.getNumber());
        }
    }
}

/*
3.141592653
*/
