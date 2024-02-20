// (c) 1997, 2001 duane a. bailey
import structure.*;
import java.util.Iterator;
import java.io.*;

public class Index
{
    public static void main(String args[])
    {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            java.io.Reader r = new BufferedReader(isr);
            StreamTokenizer s = new StreamTokenizer(r);
            /*
              ...
             */
            // allocate the symbol table (uses comparable keys)
            Map t = new Table();
            int token;
            // we'll not consider period as part of identifier
            s.ordinaryChar('.');
            // read in all the tokens from file
            for (token = s.nextToken();
                 token != StreamTokenizer.TT_EOF;
                 token = s.nextToken())
            {
                // only tokens we care about are whole words
                if (token == StreamTokenizer.TT_WORD)
                {
                    // get wrapper for integer
                    Integer line = new Integer(s.lineno());
                    // each set of lines is maintained in a List
                    List l;

                    // look up symbol
                    if (t.containsKey(s.sval))
                    {   // symbol is there, get line # list
                        l = (List)t.get(s.sval);
                        l.addLast(line);
                    } else {
                        // not found, create new list
                        l = new DoublyLinkedList();
                        l.addLast(line);
                        t.put(s.sval,l);
                    }
                }
            }
            // printing table involves tandem key-value iterators
            Iterator ti = t.keySet().iterator();
            Iterator ki = t.values().iterator();
            while (ti.hasNext())
            {
                // print symbol
                System.out.print(ti.next()+": ");
                // print out (and consume) each line number
                List l = (List)ki.next();
                while (!l.isEmpty())
                {
                    System.out.print(l.removeFirst()+" ");
                }
                System.out.println();
                // increment iterators
            }
        } catch (java.io.IOException e) {
            Assert.fail("Got an I/O exception.");
        }
    }
}
/*
      politics without principle
      pleasure without conscience
        wealth without work
     knowledge without character
      business without morality
       science without humanity
                 and
       worship without sacrifice
*/
/*
and: 7 
business: 5 
character: 4 
conscience: 2 
humanity: 6 
knowledge: 4 
morality: 5 
pleasure: 2 
politics: 1 
principle: 1 
sacrifice: 8 
science: 6 
wealth: 3 
without: 1 2 3 4 5 6 8 
work: 3 
worship: 8 
 */
