/*
  Ratio.java 比例類別
	可代表有理數
*/
package ch11_ordered_structures;

public class Ratio implements Comparable<Ratio>
{
	protected int numerator; // numerator of ratio 分子
	protected int denominator; // denominator of ratio 分母
	
	public Ratio(int top, int bottom)
	// pre:  bottom != 0
	// post: constructs a ratio equivalent to top::bottom
	{
		numerator = top;
		denominator = bottom;
		reduce(); // 約分化簡
	}
	
	// 取分子
	public int getNumerator()
	// post: return the numerator of the fraction
	{
		return numerator;
	}
	
	// 取分母
	public int getDenominator()
	// post: return the denominator of the fraction
	{
		return denominator;
	}
	
	// 取實數值
	public double getValue()
	// post: return the double equivalent of the ratio
	{
		return (double) numerator / denominator;
	}
	
	// 自己加上other比例,回傳兩比例和為新比例
	public Ratio add(Ratio other)
	// pre: other is nonnull
	// post: return new fraction-- the sum of this and other
	{
		return null;
	}
	
	// 回傳比例列印字串
	public String toString()
	// post: returns a string taht represents this fraction.
	{
		return null;
	}
	
	// 回傳(自己-that比例)的正,零,負比較結果
	public int compareTo(Ratio that)
	// pre: other is non-null and type Ratio
	// post: returns value <, ==, > 0 if this value is <, ==, > that
	{
		return this.getNumerator() * that.getDenominator() -
			that.getNumerator() * this.getDenominator();
	}
	
	// 回傳自己和that比例兩比例相等否
	public boolean equals(Object that)
	// pre: that is type Ratio
	// post: returns true iff this ratio is the same as that ratio
	{
		return compareTo((Ratio) that) == 0;
	}
	
	// 約分化簡
	public void reduce()
	// post: 分子分母約分化簡為互質
	{
	}
}