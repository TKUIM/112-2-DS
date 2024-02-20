import structure5.*;
import java.util.Iterator;
public class GameTree
{
    protected char player;
    protected HexBoard pos;
    protected Vector<HexMove> moves;
    protected Vector<GameTree> next;
    static int moveCount = 0;
    
    public GameTree(HexBoard pos, char player)
    {
        moveCount++;
        this.pos = pos;
        this.player = player;
        if (!pos.win(player))
        {
            moves = pos.moves(player);
            next = new Vector<GameTree>();
            for (HexMove m : moves)
            {
                next.add(new GameTree(new HexBoard(pos,m),
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
