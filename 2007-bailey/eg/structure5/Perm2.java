import structure5.AbstractIterator;
import java.util.Iterator;
public class Perm2 extends AbstractIterator<Object>
{
    protected int data[];
    protected int N;
    protected boolean done;

    public Perm2(int n)
    {
        N = n;
        data = new int[n];
        reset();
    }

    public void reset()
    {
        for (int i = 0; i < N; i++)
        {
            data[i] = i;
        }
        done = false;
    }

    public boolean hasNext()
    {
        return !done;
    }

    public Object get()
    {
        int result[] = new int[N];
        for (int i = 0; i < N; i++)
        {
            result[i] = data[i];
        }
        return result;
    }

    public Object next()
    {
        int result[] = (int[])get();
        increment();
        return result;
    }
    
    protected void increment()
    {
        int i,j,temp;
        for (i = N-2; i >= 0; i--)
        {
            if (data[i] < data[i+1])
            {
                // entry i should be moved out
                int nextLarger = i+1;
                for (j = i+2; j < N; j++)
                {
                    if (data[j] > data[i]) nextLarger = j;
                }
                temp = data[i];
                data[i] = data[nextLarger];
                data[nextLarger] = temp;
                int left = i+1;
                int right = N-1;
                while (left < right)
                {
                    temp = data[left];
                    data[left] = data[right];
                    data[right] = temp;
                    left++; right--;
                }
                break;
            }
        }
        done = i < 0;
    }

    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        int data[];
        Iterator<?> i = new Perm2(n);
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
java Perm2 3
0 1 2 
0 2 1 
1 0 2 
1 2 0 
2 0 1 
2 1 0 
*/
