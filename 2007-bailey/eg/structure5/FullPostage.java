// A program to determine the minimum number of coins needed to make change.
// (c) 1997, 2001 duane a. bailey
import structure5.*;

public class FullPostage
{
    static int count1, count2;
    public static final int LETTER = 41; // letter rate
    public static final int CARD = 26;   // post card rate
    public static final int PENNY = 1;   // penny stamp

    public static int stampCount(int amount)
    // pre: amount >= 0
    // post: return *number* of stamps needed to make change
    //       (only use letter, post card, and penny stamps)
    {
        return stampCount(amount, new int[amount+1]);
    }

    protected static int stampCount(int amount, int answer[])
    // pre: amount >= 0; answer array has length >= amount
    // post: return *number* of stamps needed to make change
    //       (only use letter, post card, and penny stamps)
    {
        int minStamps;
        count1++;
        Assert.pre(amount >= 0,"Reasonable amount of change.");
        if (amount == 0) return 0;
        if (answer[amount] != 0) return answer[amount];
        // consider use of a penny stamp
        minStamps = 1+stampCount(amount-1,answer);
        // consider use of a post card stamp
        if (amount >= CARD) {
            int possible = 1+stampCount(amount-CARD,answer);
            if (minStamps > possible) minStamps = possible;
        }
        // consider use of a letter stamp
        if (amount >= LETTER) {
            int possible = 1+stampCount(amount-LETTER,answer);
            if (minStamps > possible) minStamps = possible;
        }
        answer[amount] = minStamps;
        return minStamps;
    } 

    public static void main(String args[])
    {
        for (int amount = 1; amount < 100; amount++)
        {
            count1 = 0;
            System.out.println("Stamps returned to make "+amount+" cents: "+
                               stampCount(amount)+" ("+count1+" calls)");
        }
    }

    static Vector<coins> amounts;
    public static coins change(int amount)
    {
        Assert.pre(amount >= 0, "Amount must be positive.");
        amounts.ensureCapacity(amount+1);
        if (amounts.get(amount) == null)
        {       
            coins best, possible;
            if (amount == 0) best = new coins();
            else {
                possible = change(amount-1).addPenny();
                best = possible;
                if (amount >= 5) {
                    possible = change(amount-5).addNickle();
                    if (best.total() > possible.total()) best = possible;
                }
                if (amount >= 10) {
                    possible = change(amount-10).addDime();
                    if (best.total() > possible.total()) best = possible;
                }
                if (amount >= 25) {
                    possible = change(amount-25).addQuarter();
                    if (best.total() > possible.total()) best = possible;
                }
            }
            amounts.set(amount,best);
        }
        return new coins((coins)amounts.get(amount));
    } 

    /*    public static void main(String args[]) {
        amounts = new Vector();
        for (int i = 0; i < 100; i++)
        {
            System.out.println("Change for "+i+" cents: "+change(i));
        }
    }*/

}

class coins
{
    int pennies, nickles, dimes, quarters;
    public coins()
    {
        pennies = nickles = dimes = quarters = 0;
    }
    public coins(coins that)
    {
        this.pennies = that.pennies;
        this.nickles = that.nickles;
        this.dimes = that.dimes;
        this.quarters = that.quarters;
    }
    public coins addPenny() { pennies++; return this;}
    public coins addNickle() { nickles++; return this;}
    public coins addDime() { dimes++; return this;}
    public coins addQuarter() { quarters++; return this;}
    public int total() { return pennies+nickles+dimes+quarters; }
    public String toString() {
        return "pennies: "+pennies+", nickles: "+nickles+
               ", dimes: "+dimes+", quarters: "+quarters;
    }
}

/*
Stamps returned to make 1 cents: 1 (2 calls)
Stamps returned to make 2 cents: 2 (3 calls)
Stamps returned to make 3 cents: 3 (4 calls)
Stamps returned to make 4 cents: 4 (5 calls)
Stamps returned to make 5 cents: 5 (6 calls)
Stamps returned to make 6 cents: 6 (7 calls)
Stamps returned to make 7 cents: 7 (8 calls)
Stamps returned to make 8 cents: 8 (9 calls)
Stamps returned to make 9 cents: 9 (10 calls)
Stamps returned to make 10 cents: 10 (11 calls)
Stamps returned to make 11 cents: 11 (12 calls)
Stamps returned to make 12 cents: 12 (13 calls)
Stamps returned to make 13 cents: 13 (14 calls)
Stamps returned to make 14 cents: 14 (15 calls)
Stamps returned to make 15 cents: 15 (16 calls)
Stamps returned to make 16 cents: 16 (17 calls)
Stamps returned to make 17 cents: 17 (18 calls)
Stamps returned to make 18 cents: 18 (19 calls)
Stamps returned to make 19 cents: 19 (20 calls)
Stamps returned to make 20 cents: 20 (21 calls)
Stamps returned to make 21 cents: 21 (22 calls)
Stamps returned to make 22 cents: 22 (23 calls)
Stamps returned to make 23 cents: 23 (24 calls)
Stamps returned to make 24 cents: 24 (25 calls)
Stamps returned to make 25 cents: 25 (26 calls)
Stamps returned to make 26 cents: 1 (28 calls)
Stamps returned to make 27 cents: 2 (30 calls)
Stamps returned to make 28 cents: 3 (32 calls)
Stamps returned to make 29 cents: 4 (34 calls)
Stamps returned to make 30 cents: 5 (36 calls)
Stamps returned to make 31 cents: 6 (38 calls)
Stamps returned to make 32 cents: 7 (40 calls)
Stamps returned to make 33 cents: 8 (42 calls)
Stamps returned to make 34 cents: 9 (44 calls)
Stamps returned to make 35 cents: 10 (46 calls)
Stamps returned to make 36 cents: 11 (48 calls)
Stamps returned to make 37 cents: 12 (50 calls)
Stamps returned to make 38 cents: 13 (52 calls)
Stamps returned to make 39 cents: 14 (54 calls)
Stamps returned to make 40 cents: 15 (56 calls)
Stamps returned to make 41 cents: 1 (59 calls)
Stamps returned to make 42 cents: 2 (62 calls)
Stamps returned to make 43 cents: 3 (65 calls)
Stamps returned to make 44 cents: 4 (68 calls)
Stamps returned to make 45 cents: 5 (71 calls)
Stamps returned to make 46 cents: 6 (74 calls)
Stamps returned to make 47 cents: 7 (77 calls)
Stamps returned to make 48 cents: 8 (80 calls)
Stamps returned to make 49 cents: 9 (83 calls)
Stamps returned to make 50 cents: 10 (86 calls)
Stamps returned to make 51 cents: 11 (89 calls)
Stamps returned to make 52 cents: 2 (92 calls)
Stamps returned to make 53 cents: 3 (95 calls)
Stamps returned to make 54 cents: 4 (98 calls)
Stamps returned to make 55 cents: 5 (101 calls)
Stamps returned to make 56 cents: 6 (104 calls)
Stamps returned to make 57 cents: 7 (107 calls)
Stamps returned to make 58 cents: 8 (110 calls)
Stamps returned to make 59 cents: 9 (113 calls)
Stamps returned to make 60 cents: 10 (116 calls)
Stamps returned to make 61 cents: 11 (119 calls)
Stamps returned to make 62 cents: 12 (122 calls)
Stamps returned to make 63 cents: 13 (125 calls)
Stamps returned to make 64 cents: 14 (128 calls)
Stamps returned to make 65 cents: 15 (131 calls)
Stamps returned to make 66 cents: 16 (134 calls)
Stamps returned to make 67 cents: 2 (137 calls)
Stamps returned to make 68 cents: 3 (140 calls)
Stamps returned to make 69 cents: 4 (143 calls)
Stamps returned to make 70 cents: 5 (146 calls)
Stamps returned to make 71 cents: 6 (149 calls)
Stamps returned to make 72 cents: 7 (152 calls)
Stamps returned to make 73 cents: 8 (155 calls)
Stamps returned to make 74 cents: 9 (158 calls)
Stamps returned to make 75 cents: 10 (161 calls)
Stamps returned to make 76 cents: 11 (164 calls)
Stamps returned to make 77 cents: 12 (167 calls)
Stamps returned to make 78 cents: 3 (170 calls)
Stamps returned to make 79 cents: 4 (173 calls)
Stamps returned to make 80 cents: 5 (176 calls)
Stamps returned to make 81 cents: 6 (179 calls)
Stamps returned to make 82 cents: 2 (182 calls)
Stamps returned to make 83 cents: 3 (185 calls)
Stamps returned to make 84 cents: 4 (188 calls)
Stamps returned to make 85 cents: 5 (191 calls)
Stamps returned to make 86 cents: 6 (194 calls)
Stamps returned to make 87 cents: 7 (197 calls)
Stamps returned to make 88 cents: 8 (200 calls)
Stamps returned to make 89 cents: 9 (203 calls)
Stamps returned to make 90 cents: 10 (206 calls)
Stamps returned to make 91 cents: 11 (209 calls)
Stamps returned to make 92 cents: 12 (212 calls)
Stamps returned to make 93 cents: 3 (215 calls)
Stamps returned to make 94 cents: 4 (218 calls)
Stamps returned to make 95 cents: 5 (221 calls)
Stamps returned to make 96 cents: 6 (224 calls)
Stamps returned to make 97 cents: 7 (227 calls)
Stamps returned to make 98 cents: 8 (230 calls)
Stamps returned to make 99 cents: 9 (233 calls)
*/
