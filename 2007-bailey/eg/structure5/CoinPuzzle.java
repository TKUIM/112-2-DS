// A simple little puzzle played with a dime, penny, nickel, and quarter.
// The coins are layed out from quarter down to penny leftmost in four of
// five spaces.  The goal is to reverse their order.  Valid moves are to
//   1. Move any coin to an adjacent empty spot,
//   2. Move any coin atop any adjacent larger coin.
//
// The structure we use has four fields, that indicates which of the
// five positions the coin is in.  The coin is free if any smaller coin
// is not in the same position.
//
// STATS:                                       moves    qsize   loops
// Shifted one slot to "right":                    4        56      36
// To reverse order of coins, same positions:     18       172     524
// To reverse order of coins, first slot free:    22       172     593
// All stacked on "left":                          6        73      80
// All stacked on "right":                        28       172     624
// All stacked on "right"; avoid right position:  28        52     255

import structure5.*;

public class CoinPuzzle {
    public static void main(String args[])
    {   
        int maxQSize = 0;
        int movesConsidered = 0;
        Queue<State> pool = new QueueList<State>();
        State board = new State();
        BitSet seen = new BitSet(5*5*5*5);
        pool.add(board);
        while (!pool.isEmpty())
        {
            if (pool.size() > maxQSize) maxQSize = pool.size();
            board = (State)pool.remove();
            if (board.done()) break;
            int moveCode = board.id();
            if (seen.contains(moveCode)) continue;
            movesConsidered++;
            seen.add(moveCode);
            for (int coin = State.DIME; coin <= State.QUARTER; coin++)
            {
                if (board.validMove(coin,State.LEFT))
                    pool.add(board.move(coin,State.LEFT));
                if (board.validMove(coin,State.RIGHT))
                    pool.add(board.move(coin,State.RIGHT));
            }
        }
        System.out.println("Max queue size = "+maxQSize+"; "+movesConsidered+" moves considered.");
        board.printMoves();
    }
        
}

class State
{
    public static final int DIME=0;    // coins
    public static final int PENNY=1;
    public static final int NICKEL=2;
    public static final int QUARTER=3;
    public static final int LEFT = -1; // directions
    public static final int RIGHT = 1;
    private int position[];  // the position, by coin
    private State previous;  // reference to previous state

    public State()
    // post: construct initial layout of coins
    {
        position = new int[4];
        position[DIME] = 3;
        position[PENNY] = 2;
        position[NICKEL] = 1;
        position[QUARTER] = 0;
        previous = null;
    }

    public State(State prior)
    // pre: prior is a non-null state
    // post: constructs a copy of that state to be successor state
    {
        position = new int[4];
        for (int coin = DIME; coin <= QUARTER; coin++)
        {
            this.position[coin] = prior.position[coin];
        }
        this.previous = prior;
    }

    public boolean done()
    // post: returns true if state is the finish state
    {
        return position[DIME]==1 && position[PENNY]==2 &&
               position[NICKEL]==3 && position[QUARTER]==4;
    }
    
    public boolean free(int coin)
    // pre: State.DIME <= coin <= State.QUARTER
    // post: returns true iff coin is on top of its pile
    {
        for (int smaller = DIME; smaller < coin; smaller++)
        {
            if (position[smaller] == position[coin]) return false;
        }
        return true;
    }

    public boolean fits(int coin, int where)
    // pre: State.DIME <= coin <= State.QUARTER
    // post: returns true if coin fits in where'th location
    {
        for (int smaller = DIME; smaller < coin; smaller++)
        {
            if (position[smaller] == where) return false;
        }
        return true;
    }

    public boolean validMove(int coin, int direction)
    // pre: State.DIME <= coin <= State.QUARTER
    //      direction = State.left or State.right
    // post: returns true if coin can be moved in desired direction
    {
        int p = position[coin];
        int np = p + direction;
        return (np >= 0) && (np <= 4) && free(coin) && fits(coin,np);
    }

    public State move(int coin, int direction)
    // pre: coin and direction describe valid move
    // post: coin is moved in that direction
    {
        State target = new State(this);
        target.position[coin] += direction;
        return target;
    }

    public int printMoves()
    // post: print moves up to and including this point
    {
        int moveCount = 0;
        if (previous != null)
        {
            moveCount = 1 + previous.printMoves();
        }
        System.out.println(moveCount+". "+this);
        return moveCount;
    }

    public int id()
    // post: construct an integer representing state
    //       states of coins are equal iff id's are equal
    {
        return (((position[QUARTER]*5)+position[DIME])*5+
                position[NICKEL])*5+position[PENNY];
    }
        
    public String toString()
    // post: return string representation of this move.
    {
        return "dime="+position[DIME]+" penny="+
            position[PENNY]+" nickel="+position[NICKEL]+
            " quarter="+position[QUARTER];
    }
}
/*
Max queue size = 172; 593 moves considered.
0. dime=3 penny=2 nickel=1 quarter=0
1. dime=4 penny=2 nickel=1 quarter=0
2. dime=4 penny=3 nickel=1 quarter=0
3. dime=3 penny=3 nickel=1 quarter=0
4. dime=3 penny=3 nickel=2 quarter=0
5. dime=2 penny=3 nickel=2 quarter=0
6. dime=2 penny=4 nickel=2 quarter=0
7. dime=2 penny=4 nickel=2 quarter=1
8. dime=1 penny=4 nickel=2 quarter=1
9. dime=0 penny=4 nickel=2 quarter=1
10. dime=0 penny=4 nickel=3 quarter=1
11. dime=0 penny=3 nickel=3 quarter=1
12. dime=0 penny=3 nickel=3 quarter=2
13. dime=0 penny=2 nickel=3 quarter=2
14. dime=0 penny=1 nickel=3 quarter=2
15. dime=0 penny=1 nickel=4 quarter=2
16. dime=0 penny=1 nickel=4 quarter=3
17. dime=0 penny=1 nickel=3 quarter=3
18. dime=0 penny=1 nickel=2 quarter=3
19. dime=0 penny=1 nickel=2 quarter=4
20. dime=0 penny=1 nickel=3 quarter=4
21. dime=0 penny=2 nickel=3 quarter=4
22. dime=1 penny=2 nickel=3 quarter=4
 */
