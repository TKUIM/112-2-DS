// List unique words from input.
// (c) 1996, 2001 duane a. bailey

import structure5.*;
import java.io.*;

/**
 * 
 * @version $Id: U.java 26 2006-08-24 14:29:13Z bailey $
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
        //      List<String> words = new SinglyLinkedList<String>(); // list of unique words
        List<String> words = new VectorList<String>(); // list of unique words

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
