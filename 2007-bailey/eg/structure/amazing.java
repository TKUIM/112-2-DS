import structure.*;
import java.util.Random;
public class amazing
{
    public static int gcd(int a, int b)
    {
        if (a == 0) return b;
        if (b < a) return gcd(b,a);
        return gcd(b%a,a);
    }

    public static void main(String args[])
    {
        final int samples = 1000000;
        int relPrime = 0;
        Random r = new Random();

        for (int k = 0; k < samples; k++)
        {
                    int i = Math.abs(r.nextInt());
                    int j = Math.abs(r.nextInt());
            if (gcd(i,j) == 1) relPrime++;
        }
        System.out.println("#"+Math.sqrt((double)samples/relPrime*6.0));
    }
}
