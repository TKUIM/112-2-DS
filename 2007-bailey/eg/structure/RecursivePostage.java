// A program to determine the minimum number of coins needed to make change.
// (c) 1997, 2001 duane a. bailey
import structure.*;

public class RecursivePostage
{
    static int count1;
    public final static int LETTER=41;
    public final static int CARD=26;
    public final static int PENNY=1;

    public static int stampCount(int amount)
    // pre: amount >= 0
    // post: return *number* of stamps needed to make change
    //       (only use letter, card, and penny stamps)
    {
        int minStamps;
        count1++;
        Assert.pre(amount >= 0,"Reasonable amount of change.");
        if (amount == 0) return 0;
        // consider use of a penny stamp
        minStamps = 1+stampCount(amount-PENNY);
        // consider use of a post card stamp
        if (amount >= CARD) {
            int possible = 1+stampCount(amount-CARD);
            if (minStamps > possible) minStamps = possible;
        }
        // consider use of a letter stamp
        if (amount >= LETTER) {
            int possible = 1+stampCount(amount-LETTER);
            if (minStamps > possible) minStamps = possible;
        }
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

    public static coins change(int amount)
    {
        Assert.pre(amount >= 0, "Amount must be positive.");
        coins best, possible;
        if (amount == 0) best = new coins();
        else {
            possible = change(amount-1);
            possible.addPenny();
            best = possible;
            if (amount >= 5) {
                possible = change(amount-5);
                possible.addNickle();
                if (best.total() > possible.total()) best = possible;
            }
            if (amount >= 10) {
                possible = change(amount-10);
                possible.addDime();
                if (best.total() > possible.total()) best = possible;
            }
            if (amount >= 25) {
                possible = change(amount-25);
                possible.addQuarter();
                if (best.total() > possible.total()) best = possible;
            }
        }
        return new coins(best);
    } 
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
    public void addPenny() { pennies++; }
    public void addNickle() { nickles++; }
    public void addDime() { dimes++; }
    public void addQuarter() { quarters++; }
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
Stamps returned to make 27 cents: 2 (31 calls)
Stamps returned to make 28 cents: 3 (35 calls)
Stamps returned to make 29 cents: 4 (40 calls)
Stamps returned to make 30 cents: 5 (46 calls)
Stamps returned to make 31 cents: 6 (53 calls)
Stamps returned to make 32 cents: 7 (61 calls)
Stamps returned to make 33 cents: 8 (70 calls)
Stamps returned to make 34 cents: 9 (80 calls)
Stamps returned to make 35 cents: 10 (91 calls)
Stamps returned to make 36 cents: 11 (103 calls)
Stamps returned to make 37 cents: 12 (116 calls)
Stamps returned to make 38 cents: 13 (130 calls)
Stamps returned to make 39 cents: 14 (145 calls)
Stamps returned to make 40 cents: 15 (161 calls)
Stamps returned to make 41 cents: 1 (179 calls)
Stamps returned to make 42 cents: 2 (199 calls)
Stamps returned to make 43 cents: 3 (221 calls)
Stamps returned to make 44 cents: 4 (245 calls)
Stamps returned to make 45 cents: 5 (271 calls)
Stamps returned to make 46 cents: 6 (299 calls)
Stamps returned to make 47 cents: 7 (329 calls)
Stamps returned to make 48 cents: 8 (361 calls)
Stamps returned to make 49 cents: 9 (395 calls)
Stamps returned to make 50 cents: 10 (431 calls)
Stamps returned to make 51 cents: 11 (469 calls)
Stamps returned to make 52 cents: 2 (510 calls)
Stamps returned to make 53 cents: 3 (555 calls)
Stamps returned to make 54 cents: 4 (605 calls)
Stamps returned to make 55 cents: 5 (661 calls)
Stamps returned to make 56 cents: 6 (724 calls)
Stamps returned to make 57 cents: 7 (795 calls)
Stamps returned to make 58 cents: 8 (875 calls)
Stamps returned to make 59 cents: 9 (965 calls)
Stamps returned to make 60 cents: 10 (1066 calls)
Stamps returned to make 61 cents: 11 (1179 calls)
Stamps returned to make 62 cents: 12 (1305 calls)
Stamps returned to make 63 cents: 13 (1445 calls)
Stamps returned to make 64 cents: 14 (1600 calls)
Stamps returned to make 65 cents: 15 (1771 calls)
Stamps returned to make 66 cents: 16 (1959 calls)
Stamps returned to make 67 cents: 2 (2167 calls)
Stamps returned to make 68 cents: 3 (2398 calls)
Stamps returned to make 69 cents: 4 (2655 calls)
Stamps returned to make 70 cents: 5 (2941 calls)
Stamps returned to make 71 cents: 6 (3259 calls)
Stamps returned to make 72 cents: 7 (3612 calls)
Stamps returned to make 73 cents: 8 (4003 calls)
Stamps returned to make 74 cents: 9 (4435 calls)
Stamps returned to make 75 cents: 10 (4911 calls)
Stamps returned to make 76 cents: 11 (5434 calls)
Stamps returned to make 77 cents: 12 (6007 calls)
Stamps returned to make 78 cents: 3 (6634 calls)
Stamps returned to make 79 cents: 4 (7320 calls)
Stamps returned to make 80 cents: 5 (8071 calls)
Stamps returned to make 81 cents: 6 (8894 calls)
Stamps returned to make 82 cents: 2 (9798 calls)
Stamps returned to make 83 cents: 3 (10793 calls)
Stamps returned to make 84 cents: 4 (11890 calls)
Stamps returned to make 85 cents: 5 (13101 calls)
Stamps returned to make 86 cents: 6 (14439 calls)
Stamps returned to make 87 cents: 7 (15918 calls)
Stamps returned to make 88 cents: 8 (17553 calls)
Stamps returned to make 89 cents: 9 (19360 calls)
Stamps returned to make 90 cents: 10 (21356 calls)
Stamps returned to make 91 cents: 11 (23559 calls)
Stamps returned to make 92 cents: 12 (25988 calls)
Stamps returned to make 93 cents: 3 (28666 calls)
Stamps returned to make 94 cents: 4 (31620 calls)
Stamps returned to make 95 cents: 5 (34881 calls)
Stamps returned to make 96 cents: 6 (38484 calls)
Stamps returned to make 97 cents: 7 (42468 calls)
Stamps returned to make 98 cents: 8 (46876 calls)
Stamps returned to make 99 cents: 9 (51755 calls)
*/
