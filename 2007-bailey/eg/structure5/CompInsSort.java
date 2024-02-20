// Routines to sort arrays of integers.
// (c) 2001 duane a. bailey

import java.util.Comparator;
import structure5.ReadStream;
import structure5.Vector;

public class CompInsSort
{
    public static void main(String args[])
    {
        ReadStream r = new ReadStream();
        Comparator<String> comp = new CaselessComparator();
        Comparator<String> comp2 = new CasefullComparator();
        Vector<String> v = new Vector<String>();
        for (r.skipWhite(); !r.eof(); r.skipWhite())
        {
            v.add(r.readString());
        }
        int n = v.size();
        String data[] = new String[n];
        int i;

        for (i = 0; i < n; i++)
        {
            data[i] = v.get(i);
        }
        insertionSort(data,comp);
        System.out.println("Caseless:");
        for (i = 0; i < n; i++)
        {
            System.out.print(data[i]+" ");
            if ((i+1)%8 == 0) System.out.println();
        }
        System.out.println();
        insertionSort(data,comp2);
        System.out.println("Casefull:");
        for (i = 0; i < n; i++)
        {
            System.out.print(data[i]+" ");
            if ((i+1)%8 == 0) System.out.println();
        }
        System.out.println();
    }

    public static <T> void insertionSort(T data[], Comparator<T> c)
    // pre: c compares objects found in data
    // post: values in data[0..n-1] are in ascending order
    {
        int numSorted = 1;      // number of values in place
        int index;              // general index
        int n = data.length;    // length of the array
        while (numSorted < n)
        {
            // take the first unsorted value
            T temp = data[numSorted];
            // ...and insert it among the sorted:
            for (index = numSorted; index > 0; index--)
            {
                if (c.compare(temp,data[index-1]) < 0)
                {
                    data[index] = data[index-1];
                } else {
                    break;
                }
            }
            // reinsert value
            data[index] = temp;
            numSorted++;
        }
    }
}
/*
Fuzzy Wuzzy was a bear.
Fuzzy Wuzzy had no hair.
Fuzzy Wuzzy wasn't fuzzy, wuzzy?
Caseless:
a bear. Fuzzy Fuzzy Fuzzy fuzzy, had hair. 
no was wasn't Wuzzy Wuzzy Wuzzy wuzzy? 
Casefull:
Fuzzy Fuzzy Fuzzy Wuzzy Wuzzy Wuzzy a bear. 
fuzzy, had hair. no was wasn't wuzzy? 
*/


