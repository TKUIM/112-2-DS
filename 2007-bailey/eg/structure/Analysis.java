import structure.*;
public class Analysis
{
    public static void modTable(int n)
    // pre: n >= 0
    // post: print modulo table of width n
    {
        for (int row = 1; row <= n; row++)       // 1 
        {
            for (int col = 1; col <= n; col++)   // 2
            {
                System.out.print(row%col+" ");   // 3
            }
            System.out.println();                // 4
        }
    }
    public static void diffTable(int n)
    // pre: n >= 0
    // post: print difference table of width n
    {
        for (int row = 1; row <= n; row++)       // 1 
        {
            for (int col = 1; col <= n; col++)   // 2
            {
                System.out.print(row-col+" ");   // 3
            }
            System.out.println();                // 4
        }
    }

    public static void multTable(int n)
    // pre: n >= 0
    // post: print multiplication table
    {
        for (int row = 1; row <= n; row++)       // 1
        {
            for (int col = 1; col <= row; col++) // 2
            {
                System.out.print(row*col+" ");   // 3
            }
            System.out.println();                // 4
        }
    }

    /**
     * @pre n > 0
     * @post returns a table of factors of values 1 through n
     */
    public static Vector factTable(int n)
    {
        Vector table = new Vector();
        for (int i = 1; i <= n; i++)
        {
            Vector factors = new Vector();
            for (int f = 1; f <= i; f++)
            {
                if ((i % f) == 0) {
                    factors.add(new Integer(f));
                    System.out.print(" "+f);
                }
            }
            table.add(factors);
            System.out.println();
        }
        return table;
    }

    public static Vector buildVector1(int n)
    // pre: n >= 0
    // post: construct a vector of size n of 1..n
    {
        Vector v = new Vector(n);               // 1
        for (int i = 0; i < n; i++)             // 2
        {
            v.add(new Integer(i));              // 3
        }
        return v;                               // 4
    }

    public static Vector buildVector2(int n)
    // pre: n >= 0
    // post: construct a vector of size n of 1..n
    {
        Vector v = new Vector(n);               // 1
        for (int i = 0; i < n; i++)             // 2
        {
            v.add(0,new Integer(i));            // 3
        }
        return v;                               // 4
    }

    static int findSpace(String s)
    // pre: s is a string, possibly containing a space
    // post: returns index of first space, or -1 if none found
    {
        int i;
        for (i = 0; i < s.length(); i++)
        {
            if (' ' == s.charAt(i)) return i;
        }
        return -1;
    }


    public static void main(String args[])
    {
        System.out.println(findSpace("Hello, world."));
        System.out.println(findSpace("Hello-world!"));
        modTable(10);
        diffTable(10);
        factTable(10);
        multTable(10);
        System.out.println(buildVector1(10));
        System.out.println(buildVector2(10));
    }

    /*
    public static int reduce(int n)
    {
        int result = 0;
        while (n > 1)
        {
            n = n/2;
            result = result+1;
        }
        return result;
    }
    */

    public static void multiply()
    {
        int n = 10;
        int m1[][] = new int[10][10];
        int m2[][] = new int[10][10];
        int result[][] = new int[10][10];
        // square matrix multiplication
        // m1, m2, and result are n by n arrays
        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {
                int sum = 0;
                for (int entry = 0; entry < n; entry++)
                {
                    sum = sum + m1[row][entry]*m2[entry][col];
                }
                result[row][col] = sum;
            }
        }
    }

    

    public static int reduce(int n)
    {
        if (n <= 1) return 0;
        else return reduce(n/2);
    }
}
/*
6
-1
0 1 1 1 1 1 1 1 1 1 
0 0 2 2 2 2 2 2 2 2 
0 1 0 3 3 3 3 3 3 3 
0 0 1 0 4 4 4 4 4 4 
0 1 2 1 0 5 5 5 5 5 
0 0 0 2 1 0 6 6 6 6 
0 1 1 3 2 1 0 7 7 7 
0 0 2 0 3 2 1 0 8 8 
0 1 0 1 4 3 2 1 0 9 
0 0 1 2 0 4 3 2 1 0 
0 -1 -2 -3 -4 -5 -6 -7 -8 -9 
1 0 -1 -2 -3 -4 -5 -6 -7 -8 
2 1 0 -1 -2 -3 -4 -5 -6 -7 
3 2 1 0 -1 -2 -3 -4 -5 -6 
4 3 2 1 0 -1 -2 -3 -4 -5 
5 4 3 2 1 0 -1 -2 -3 -4 
6 5 4 3 2 1 0 -1 -2 -3 
7 6 5 4 3 2 1 0 -1 -2 
8 7 6 5 4 3 2 1 0 -1 
9 8 7 6 5 4 3 2 1 0 
 1
 1 2
 1 3
 1 2 4
 1 5
 1 2 3 6
 1 7
 1 2 4 8
 1 3 9
 1 2 5 10
1 
2 4 
3 6 9 
4 8 12 16 
5 10 15 20 25 
6 12 18 24 30 36 
7 14 21 28 35 42 49 
8 16 24 32 40 48 56 64 
9 18 27 36 45 54 63 72 81 
10 20 30 40 50 60 70 80 90 100 
<Vector: 0 1 2 3 4 5 6 7 8 9>
<Vector: 9 8 7 6 5 4 3 2 1 0>
*/
