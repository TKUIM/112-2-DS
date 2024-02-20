import structure5.*;
import java.util.Scanner;

public class StringReader
{
    public static void main(String args[])
    {
        System.out.println("This code is not tested.");
    }
}

class StringReaderSilly
{
    public static void main(String args[])
    {
        // read in n = 4 strings
        Scanner s = new Scanner(System.in);
        String v1, v2, v3, v4;
        v1 = s.next();  // read a space-delimited word
        v2 = s.next();
        v3 = s.next();
        v4 = s.next();
    }
}
class StringReaderA
{
    public static void main(String args[])
    {
        // read in n = 4 strings
        Scanner s = new Scanner(System.in);
        String data[];
        int n = 4;
        // allocate array of n String references:
        data = new String[n];
        for (int i = 0; i < n; i++)
        {
            data[i] = s.next();
        }
    }
}
class StringReaderB
{
    public static void main(String args[])
    {
        // read in up to 1 million Strings
        Scanner s = new Scanner(System.in);
        String data[];
        int n = 0;
        data = new String[1000000];
        // read in strings until we hit end of file
        while (s.hasNext())
        {
            data[n] = s.next();
            n++;
        }
    }
}
class StringReaderC
{
    public static void main(String args[])
    {
        // read in as many Strings as demanded by input
        Scanner s = new Scanner(System.in);
        String data[];
        int n;
        // read in the number of strings to be read
        n = s.nextInt();
        // allocate references for n strings
        data = new String[n];
        // read in the n strings
        for (int i = 0; i < n; i++)
        {
            data[i] = s.next();
        }
    }
}

class StringReaderVector
{
    public static void main(String args[])
    {
        // read in an arbitrary number of strings
        Scanner s = new Scanner(System.in);
        Vector<String> data;
        // allocate vector for storage
        data = new Vector<String>();
        // read strings, adding them to end of vector, until eof
        while (s.hasNext())
        {
            String st = s.next();
            data.add(st);
        }
    }
}

/*
This code is not tested.
*/
