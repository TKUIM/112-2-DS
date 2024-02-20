import structure5.*;

public class RadixSort
{
    /**
     * @pre n >= 0 and d >= 0
     * @post returns the value of the dth decimal place of n
     *  where the units place has position 0
     */
    public static int digit(int n, int d)
    {
        if (d == 0) return n % 10;
        else return digit(n/10,d-1);
    }
    
    /**
     * @pre data is an array of data values, and d >= 0
     * @post data is sorted by the digit found in location d;
     * if two values have the same digit in location d, their
     * relative positions do not change; i.e., they are not swapped
     */
    public static void bucketPass(int data[], int d)
    {
        int i,j;
        int value;
        // allocate some buckets
        Vector<Vector<Integer>> bucket = new Vector<Vector<Integer>>(10);
        bucket.setSize(10);
        // allocate Vectors to hold values in each bucket
        for (j = 0; j < 10; j++) bucket.set(j,new Vector<Integer>());
        // distribute the data among buckets
        int n = data.length;
        for (i = 0; i < n; i++)
        {
            value = data[i];
            // determine the d'th digit of value
            j = digit(value,d);
            // add data value to end of vector; keeps values in order
            bucket.get(j).add(value);
        }
        // collect data from buckets back into array
        // collect in reverse order to unload Vectors
        // in linear time
        i = n;
        for (j = 9; j >= 0; j--)
        {
            // unload all values in bucket j
            while (!bucket.get(j).isEmpty())
            {
                i--;
                value = bucket.get(j).remove();
                data[i] = value;
            }
        }
    }

    /**
     * @pre data is array of values; each is less than 1,000,000
     * @post data in the array are sorted into increasing order
     */
    public static void radixSort(int data[])
    {
        for (int i = 0; i < 6; i++)
        {
            bucketPass(data,i);
        }
    }

    public static void main(String args[])
    {
        ReadStream r = new ReadStream();
        int n = r.readInt();
        int data[] = new int[n];
        int i;
        for (i = 0; i < n; i++)
        {
            data[i] = r.readInt();
        }
        for (i = 0; i < 6; i++)
        {
            bucketPass(data,i);
        }
        for (i = 0; i < n; i++)
        {
            System.out.print(data[i]+" ");
            if (((i+1) % 15) == 0) System.out.println();
        }
        System.out.println();
    }

}
/*
100
73 92 40 38 51 17 8 31 56 84 21 34 29 16 61 31 7 63 70 32 
69 38 98 93 31 84 45 74 8 33 30 32 76 69 21 27 85 81 9 92 
16 82 26 45 97 38 75 3 53 97 86 21 34 35 65 64 71 9 89 30 
42 70 13 69 90 33 47 75 13 56 18 29 89 95 25 85 33 99 39 37 
95 76 9 29 62 74 44 32 82 85 61 75 54 73 96 44 58 42 70 70 
3 7 8 8 9 9 9 13 13 16 16 17 18 21 21 
21 25 26 27 29 29 29 30 30 31 31 31 32 32 32 
33 33 33 34 34 35 37 38 38 38 39 40 42 42 44 
44 45 45 47 51 53 54 56 56 58 61 61 62 63 64 
65 69 69 69 70 70 70 70 71 73 73 74 74 75 75 
75 76 76 81 82 82 84 84 85 85 85 86 89 89 90 
92 92 93 95 95 96 97 97 98 99 
*/
