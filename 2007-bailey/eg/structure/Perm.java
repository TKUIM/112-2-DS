import structure.*;
import java.util.Iterator;

public class Perm extends AbstractIterator
{
    protected long r;
    protected int N;
    protected long Nf;
    protected int[] data;
    protected int[] values;

    public Perm(int n)
    {
        N = n;
        Nf = fact(n);
        data = new int[N];
        values = new int[N];
        reset();
    }

    public void reset()
    {
        r = 0;
    }

    public boolean hasNext()
    {
        return r < Nf;
    }

    public Object get()
    {
        return unrank(r);
    }

    public Object next()
    {
        return unrank(r++);
    }

    protected int[] unrank(long r)
    {
        int i, j;
        for (i = 0; i < N; i++)
        {
            values[i] = i;
        }

        for (i = N-1; i >= 0; i--)
        {
            long f = fact(i);
            int d = (int)(r/f);
            r = r%f;
            data[i] = values[d];
            for (j = d; j < N-1; j++)
            {
                values[j] = values[j+1];
            }
        }

        return data;
    }

    protected long fact(int n)
    {
        long result = 1;
        int i;
        for (i = 2; i <= n; i++) result *= i;
        return result;
    }


    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        int data[];
        java.util.Iterator i = new Perm(n);
        while (i.hasNext())
        {
            data = (int[])i.next();
            for (int j = 0; j < data.length; j++)
            {
                System.out.print(data[j]+" ");
            }
            System.out.println();
        }
    }
}

/*
java Perm 3
2 1 0 
1 2 0 
2 0 1 
0 2 1 
1 0 2 
0 1 2 
*/
