/*
*  GameTree.java
*    利用樹結構建立遊戲樹，不限定二分樹，有n種可能棋步就有n個小孩節點
*    樹節點放目前棋盤，目前下手玩家
*    可依據目前下手玩家可下棋步，建立下一層樹節點及棋盤
*    分析九宫格的六子棋有幾種棋步
*
* > java GameTree
370
*/
package ch12_binary_trees;

import structure5.Vector;
import java.util.Iterator;

// 遊戲樹
public class GameTree
{
    protected char player; // 本節點目前下手玩家
    protected HexBoard pos; // 本節點目前棋盤
    protected Vector<HexMove> moves; // 可下棋步容器
    protected Vector<GameTree> next; // 下一步節點容器
    static int moveCount = 0;
    
    public GameTree(HexBoard pos, char player)
    {
        moveCount++;
        this.pos = pos;
        this.player = player;
        
        // 若目前pos棋盤，player下手玩家沒有勝利
        // 則分析玩家可下棋步，一一建立其下一層小孩節點及棋盤
        if (!pos.win(player))
        {
            // 就目前pos棋盤，回傳player玩家可下棋步容器
            moves = pos.moves(player); 
            
            // 針對每種可下棋步，建立新棋盤，玩家換為player對手
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
