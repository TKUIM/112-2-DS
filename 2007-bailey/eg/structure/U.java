// List unique words from input.
// (c) 1996, 2001 duane a. bailey

import structure.*;
import java.io.*;

/**
 * 
 * @version $Id: U.java 8 2006-08-02 19:03:11Z bailey $
 * @author, 2001 duane a. bailey
 */
public class U {
    /**
     * @param args 
     */
    public static void main(String[] args)
    {
        // input is read from System.in
        ReadStream s = new ReadStream();
        String current;                  // current line
        //      List words = new SinglyLinkedList(); // list of unique words
        List words = new VectorList(); // list of unique words

        // read a list of possibly duplicated words
        for (s.skipWhite(); !s.eof(); s.skipWhite()) {
            current = s.readString();
            // check to see if we need to add it
            if (!words.contains(current)) {
                System.out.println(current);
                words.add(current);
            }
        }
    }
}

/*
madam
I'm
Adam!
...
Adam!
I'm
Ada!
...
mad
am I...
madam
madam
I'm
Adam!
...
Ada!
mad
am
I...
*/
