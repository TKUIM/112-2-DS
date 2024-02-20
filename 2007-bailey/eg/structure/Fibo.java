import structure.*;
public class Fibo
{
    static public int fibo2(int n)
    // pre: n is a nonnegative integer
    // post: result is the ith term from the sequence
    //     0, 1, 1, 2, 3, 5, 8, 13, 21, 34, . . .
    {
        Assert.pre(n >= 0, "Index is nonnegative.");
        int a = 0;
        int b = 1;
        if (n == 0) return a;      // line 1
        if (n == 1) return b;      // line 2
        // for large values of n, iteratively compute sequence
        int i=2,F;
        do
        {
            // Assertion: b is the i-1st member of the sequence
            //            a is the i-2nd member
            F = a + b;             // line 3
            // Assertion: F is the ith member 
            // update previous two values:
            a = b;                 // line 4
            b = F;                 // line 5
            i++;                   // line 6
        } while (i <= n);          // line 7
        return F;                  // line 8
    }

    static public int fibo(int n)
    // pre: n is a nonnegative integer
    // post: result is the ith term from the sequence
    //     0, 1, 1, 2, 3, 5, 8, 13, 21, 34, . . .
    {
        Assert.pre(n >= 0, "Index is nonnegative.");
        // when n < 2, return n
        if (n == 0) return 0;                   // line 1
        else if (n == 1) return 1;              // line 2
        // complex, self-referential case:
        else return fibo(n-2)+fibo(n-1);        // line 3
    }
    public static void main(String args[])
    {
        System.out.println(fibo2(0));
        System.out.println(fibo2(1));
        System.out.println(fibo2(2));
        System.out.println(fibo2(3));
        System.out.println(fibo2(4));
        System.out.println(fibo2(5));
        System.out.println(fibo2(6));
        System.out.println(fibo2(7));
    }
}

/*
0
1
1
2
3
5
8
13
*/
