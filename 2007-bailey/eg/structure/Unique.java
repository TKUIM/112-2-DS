// List unique lines from input.
// (c) 1996, 2001 duane a. bailey

import structure.*;
import java.io.*;
import java.util.Scanner;
/**
 * 
 * @version $Id: Unique.java 8 2006-08-02 19:03:11Z bailey $
 * @author, 2001 duane a. bailey
 */
public class Unique {
    /**
     * @param args 
     */
    public static void main(String[] args)
    {
        // input is read from System.in
        Scanner s = new Scanner(System.in);
        String current;                  // current line
        List lines = new SinglyLinkedList(); // list of unique lines
        /* replacement, for lab:
        List lines = new VectorList(); // list of unique lines
        */

        // read a list of possibly duplicated lines
        while (s.hasNextLine()) {
            current = s.nextLine();
            // check to see if we need to add it
            if (!lines.contains(current)) {
                System.out.println(current);
                lines.add(current);
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
am I...
*/
