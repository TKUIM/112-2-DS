import structure.*;
import java.util.Iterator;
public class GameTree
{
    protected char player;
    protected HexBoard pos;
    protected Vector moves;
    protected Vector next;
    static int moveCount = 0;
    
    public GameTree(HexBoard pos, char player)
    {
        moveCount++;
        this.pos = pos;
        this.player = player;
        if (!pos.win(player))
        {
            moves = pos.moves(player);
            Iterator i = moves.iterator();
            next = new Vector();
            while (i.hasNext())
            {
                next.add(new GameTree(new HexBoard(pos,(HexMove)i.next()),
                                      HexBoard.opponent(player)));
            }
        }
    }

    public static void main(String[] args)
    {
        moveCount = 0;
        GameTree t = new GameTree(new HexBoard(),HexBoard.WHITE);
        System.out.println(moveCount);
    }
}
