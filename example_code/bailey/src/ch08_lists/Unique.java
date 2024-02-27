// List unique lines from input.
// (c) 1996, 2001 duane a. bailey

import structure5.*;
import java.io.*;
import java.util.Scanner;
/**
 * 
 * @version $Id: Unique.java 34 2007-08-09 14:43:44Z bailey $
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
        // list of unique lines
        List<String> lines = new SinglyLinkedList<String>();
        /* replacement, for lab:
        List<String> lines = new VectorList<String>(); // list of unique lines
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
