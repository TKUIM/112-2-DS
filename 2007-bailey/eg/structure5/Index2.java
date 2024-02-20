// (c) 1997, 2001 duane a. bailey
import structure5.*;
import java.util.Iterator;
import java.io.*;

public class Index2
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
            Map<String,List<Integer>> t = new Hashtable<String,List<Integer>>();
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
                    // each set of lines is maintained in a List
                    List<Integer> l;

                    // look up symbol
                    if (t.containsKey(s.sval))
                    {   // symbol is there, get line # list
                        l = t.get(s.sval);
                        l.addLast(s.lineno());
                    } else {
                        // not found, create new list
                        l = new DoublyLinkedList<Integer>();
                        l.addLast(s.lineno());
                        t.put(s.sval,l);
                    }
                }
            }
            // printing table involves tandem key-value iterators
            Iterator<List<Integer>> ki = t.values().iterator();
            for (String sym: t.keySet())
            {
                // print symbol
                System.out.print(sym+": ");
                // print out (and consume) each line number
                for (Integer lineno : ki.next())
                {
                    System.out.print(lineno+" ");
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
output under 1.1:
politics: 1 
wealth: 3 
morality: 5 
humanity: 6 
principle: 1 
and: 7 
science: 6 
conscience: 2 
character: 4 
without: 1 2 3 4 5 6 8 
knowledge: 4 
business: 5 
work: 3 
worship: 8 
pleasure: 2 
sacrifice: 8 

humanity: 6 
and: 7 
worship: 8 
sacrifice: 8 
conscience: 2 
wealth: 3 
science: 6 
knowledge: 4 
without: 1 2 3 4 5 6 8 
character: 4 
work: 3 
politics: 1 
pleasure: 2 
business: 5 
principle: 1 
morality: 5 
 */
