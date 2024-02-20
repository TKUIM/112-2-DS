// An example of a class.
// (c) 2001 duane a. bailey
import structure.*;
/**
 * A class to maintain the ratio of two integers.
 *
 * @author, 2001 duane a. bailey
 * @version $Id: Ratio.java 34 2007-08-09 14:43:44Z bailey $
 */
public class Ratio
       implements Comparable<Ratio>
{
    protected int numerator;   // numerator of ratio
    protected int denominator; // denominator of ratio

    /**
     * @pre bottom != 0
     * @post constructs a ratio equivalent to top::bottom
     * @param top 
     * @param bottom 
     */
    public Ratio(int top, int bottom)
    {
        numerator = top;
        denominator = bottom;
        reduce();
    }

    /**
     * @post return the numerator of the fraction
     */
    public int getNumerator()
    {
        return numerator;
    }

    /**
     * @post return the denominator of the fraction
     * @return denominator of the fraction
     */
    public int getDenominator()
    {
        return denominator;
    }

    /**
     * @post return the double equivalent of the ratio
     */
    public double getValue()
    {
        return (double)numerator/(double)denominator;
    }

    /**
     * @pre other is nonnull
     * @post return new fraction--the sum of this and other
     */
    public Ratio add(Ratio other)
    {
        return new Ratio(this.numerator*other.denominator+
                         this.denominator*other.numerator,
                         this.denominator*other.denominator);
    }

    /**
     * Reduce this fraction to lowest terms.
     * @post numerator and denominator are set so that
     * the greatest common divisor of the numerator and denominator is 1
     */
    protected void reduce()
    {
        int divisor = gcd(numerator,denominator);
        if (denominator < 0) divisor = -divisor;
        numerator /= divisor;
        denominator /= divisor;
    }

    /**
     * Compute the greatest common divistor of two values.
     * @param a one value
     * @param b another value
     * @post computes the greatest integer value that divides a and b
     * @return the greatest common divisor of a and b
     */
    protected static int gcd(int a, int b)
    {
        if (a < 0) return gcd(-a,b);
        if (a == 0) {
            if (b == 0) return 1;
            else return b;
        }
        if (b < a) return gcd(b,a);
        return gcd(b%a,a);
    }

    /**
     * @post returns a string that represents this fraction.
     * @return a string representation of this fraction.
     */
    public String toString()
    {
        return getNumerator()+"/"+getDenominator();
    }

    /**
     * main example method for testing Ratio objects
     */
    public static void main(String[] args)
    {
        Ratio r = new Ratio(1,1);      // r == 1.0
        r = new Ratio(1,2);            // r == 0.5
        r.add(new Ratio(1,3));         // sum computed, but r still 0.5
        r = r.add(new Ratio(2,8));     // r == 0.75
        System.out.println(r.getValue()); // 0.75 printed
        System.out.println(r.toString());  // calls toString()
        System.out.println(r);  // calls toString()
    }

    /**
     * Compare two Ratio values: returns value <, ==, or > 0 if 
     * this value is <, ==, or > the other value.
     * @param other the value compared to this
     * @pre other is non-null and type Ratio
     * @post returns value <, ==, > 0 if this value is <, ==, > that
     * @return value <, ==, > 0 if this value is <, ==, > that
     */
    public int compareTo(Ratio that)
    {
        return this.getNumerator()*that.getDenominator()-
               that.getNumerator()*this.getDenominator();
    }

    /**
     * Returns true iff this ratio is the same as the that ratio.
     * @param that the Ratio being compared to
     * @pre that is type Ratio
     * @post returns true iff this ratio is the same as that ratio
     * @return true iff this ratio and that are the same
     */
    public boolean equals(Object that)
    {
        return compareTo((Ratio)that) == 0;
    }
}
/*
0.75
3/4
3/4
*/
