import structure5.*;
import java.util.Random;

public class SubsetIterator<T> extends AbstractIterator<Vector<T>>
{
    protected long rank;
    protected int N;
    protected Vector<T> data;

    public SubsetIterator(Vector<T> d)
    {
        data = d;
        N = data.size();
        reset();
    }

    public void reset()
    {
        rank = 0;
    }

    public void reset(long n)
    {
        rank = (Math.abs(n) % (1L<< N));
    }

    public void remove()
    {
    }

    public boolean hasNext()
    {
        return rank < (1L<<N);
    }

    public Vector<T> get()
    {
        Vector<T> result = new Vector<T>();
        int i;
        long r = rank;
        for (i = 0; i < N; i++)
        {
            if (r % 2 == 1) result.add(data.get(i));
            r = r/2;
        }
        return result;
    }

    public Vector<T> next()
    {
        Vector<T> result = new Vector<T>();
        int i;
        long r = rank++;
        for (i = 0; i < N; i++)
        {
            if (r % 2 == 1) result.add(data.get(i));
            r = r/2;
        }
        return result;
    }

    public static void main(String args[])
    {
        int i;
        Vector<Double> d = new Vector<Double>();
        SubsetIterator<Double> it;
        int n = Integer.parseInt(args[0]);
        double total = 0.0;
        for (i = 1; i <= n; i++)
        {
            double sq = Math.sqrt((double)i);
            d.add(new Double(sq));
            total += sq;
        }
        System.out.println(total);
        it = new SubsetIterator<Double>(d);
        double best = 0;
        Vector<Double> bestSet = null;
        Random gen = new Random();
        long stopTime = System.nanoTime()+1000000;
        /*
        while (stopTime > System.nanoTime())
        */
        while (it.hasNext())
        {
            /* it.reset(gen.nextLong());*/
            Vector<Double> s = it.next();

            double sum = 0.0;
            for (i = 0; i < s.size(); i++)
            {
                sum += s.get(i);
            }
            if (sum <= 0.5*total)
            {
                System.out.print(sum+":");
                for (i = 0; i < s.size(); i++)
                {
                    double x = s.get(i);
                    System.out.print(" "+(int)(0.5+(x*x)));
                }
                System.out.println();
            }
            /*

            if (sum > best && sum <= 0.5*total) {
                bestSet = s;
                best = sum;
            }
            */
        }
        /*
        System.out.println(best+" (of "+total+"): "+bestSet);
        */
    }
}

/*
java SubsetIterator 6
10.83182209022494
0.0:
1.0: 1
1.4142135623730951: 2
2.414213562373095: 1 2
1.7320508075688772: 3
2.732050807568877: 1 3
3.1462643699419726: 2 3
4.146264369941973: 1 2 3
2.0: 4
3.0: 1 4
3.414213562373095: 2 4
4.414213562373095: 1 2 4
3.732050807568877: 3 4
4.732050807568877: 1 3 4
5.146264369941973: 2 3 4
2.23606797749979: 5
3.23606797749979: 1 5
3.6502815398728847: 2 5
4.650281539872885: 1 2 5
3.968118785068667: 3 5
4.9681187850686666: 1 3 5
5.382332347441762: 2 3 5
4.23606797749979: 4 5
5.23606797749979: 1 4 5
2.449489742783178: 6
3.449489742783178: 1 6
3.863703305156273: 2 6
4.863703305156273: 1 2 6
4.1815405503520555: 3 6
5.1815405503520555: 1 3 6
4.449489742783178: 4 6
4.685557720282968: 5 6
*/
