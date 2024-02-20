// An Iterator-like class for consuming streams of postscript tokens.
// (c) 2001,1996, 2001 duane a. bailey
// See Token.java for more help.

import structure.*;
import java.util.Iterator;
public class Reader extends AbstractIterator
{

    protected boolean isStream; // true if this is input; else from list
    protected ReadStream r;     // ReadStream associated with input
    protected AbstractIterator listIterator; // list-based streams use an iterator

    public Reader()
    // post: constructs a reader of postscript tokens from System.in
    {
        isStream = true;
        r = new ReadStream(System.in);
    }

    public Reader(List l)
    // post: constructs a reader of postscript tokens from a list of tokens
    {
        isStream = false;
        listIterator = (AbstractIterator)l.iterator();
    }

    public Reader(Token t)
    // post: constructs a that returns a single token
    {
        isStream = false;
        List l;
        if (t.isProcedure())
        {
            l = t.getProcedure();
        } else {
            l = new SinglyLinkedList();
            l.add(t);
        }
        listIterator = (AbstractIterator)l.iterator();
    }

    public boolean hasNext()
    // post: returns true iff there are more tokens on input stream
    {
        boolean result = false;
        if (isStream)
        {
            r.skipWhite();
            result = !r.eof();
        } else {
            result = listIterator.hasNext();
        }
        return result;
    }

    public Object get()
    // NOT YET IMPLEMENTED.
    // pre: hasNext();
    // post: returns the value of the current token
    {
        Assert.pre(false,"Not implemented.");
        return null;
    }   

    public void reset()
    // pre: this is a list-based stream
    // post: reader set back to beginning of input
    {
        if (!isStream)
        {
            listIterator.reset();
        }
    }

    public Object next()
    // pre: hasNext()
    // post: consumes and returns next Token from input stream.
    {
        Token result;
        if (isStream)
        {
            result = readToken();
        } else {
            result = (Token)listIterator.next();
        }
        return result;
    }

    protected Token readToken()
    // post: returns next token from input stream, or null if error
    {
        Token result = null;
        String s;
        r.skipWhite();
        // exit if we have no more input
        do
        {
            if (r.eof()) return null;
            s = r.readString();
            if (s.equals("%")){
                r.readLine();
                s = null;
            }
        } while (s == null);
            // exit if we're at end of procedure
        if (s.equals("}")) return null;
        else if (s.equals("{")) {  // procedure start
            List l = new DoublyLinkedList();
            Token t;
            // consume tokens until end of procedure
            for (t = readToken(); t != null; t = readToken())
            {
                l.addLast(t);
            }
            result = new Token(l);
        } else if (s.equals("true") || s.equals("false")) { // booleans
            result = new Token(s.equals("true"));
        } else try {
            Double d = new Double(s); // doubles (numbers)
            result = new Token(d.doubleValue());
        } catch (NumberFormatException e) {
            result = new Token(s);  // all others are symbols
        }
        return result;
    }

    public static void main(String[] args)
    {
        int i=0;
        Reader r = new Reader();
        Token t;
        while (r.hasNext())
        {
            t = (Token)r.next();
            if (t.isSymbol() && // only if symbol:
                t.getSymbol().equals("quit")) break;
            // process token
            System.out.println(i+": "+t);
            i++;
        }
    }
}

