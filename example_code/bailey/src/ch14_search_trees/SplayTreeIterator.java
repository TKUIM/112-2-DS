// An implementation of an iterator for SplayTrees
// (c) 1998, 2001 duane a. bailey

package ch14_search_trees;
import structure5.AbstractIterator;

/**
 * 
 * An iterator for traversing splay trees constructed from
 * BinaryTrees.  The iterator performs minimal work before
 * traversal.  Every node is considered after every left descendant,
 * but before any right descendant.  SplayTreeIterator finishes when
 * all descendants of the start node have been considered.
 * <P>
 * Example usage:
 * <P>
 * <pre>
 *      SplayTree t = new SplayTree();
 *      // ...tree is grown
 *      Iterator ti = t.iterator();
 *      while (ti.hasNext())
 *      {
 *          System.out.println(ti.next());
 *      }
 *      ti.reset();
 *      while (ti.hasNext())
 *      { .... }
 * </pre>
 *
 * @version $Id: SplayTreeIterator.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
// 伸展樹迭代器
class SplayTreeIterator<E extends Comparable<E>> extends AbstractIterator<E>
{
    /**
     * A reference to the root of a splay tree.
     */
    protected BinaryTree<E> tree; // node of splay tree, root computed 樹根
    protected final BinaryTree<E> LEAF;  // 葉節點
    /**
     * The current node being considered in tree.
     */
    protected BinaryTree<E> current; // current node 將拜訪節點
    // In this iterator, the "stack" normally used is implied by 
    // looking back up the path from the current node.  Those nodes
    // for which the path goes left are on the stack

    /**
     * Construct an iterator that traverses the binary search 
     * tree based at the root.
     *
     * @pre root is the root of the tree to be traversed
     * @post constructs a new iterator to traverse splay tree
     * 
     * @param root The root of the subtree to be traversed.
     */
    // 建立伸展樹迭代器,root為樹根,leaf為葉節點
    public SplayTreeIterator(BinaryTree<E> root, BinaryTree<E> leaf)
    {
        tree = root;
        LEAF = leaf;
        reset();
    }

    /**
     * Reset the iterator to reference the root of the tree.
     *
     * @post resets iterator to smallest node in tree
     */
    // 重新迭代
    public void reset()
    {
        current = tree;  // 樹根開始拜訪
        if (!current.isEmpty()) {
            current = current.root();  // 從樹根開始拜訪

            // 往連續左小孩找最小節點
            while (!current.left().isEmpty()) current = current.left();  
        }
    }

    /**
     * Determine if the iterator has more nodes to be considered.
     *
     * @post returns true if there are unvisited nodes
     * 
     * @return True iff the iterator has more nodes to be considered.
     */
    // 回傳是否有下個元素的布林值
    public boolean hasNext()
    {
        return !current.isEmpty();
    }

    /**
     * Returns reference to the current element, and increments iterator.
     *
     * @pre hasNext()
     * @post returns current element and increments iterator
     * 
     * @return The reference to the current element before incrementing.
     */
    // 取走及回傳下一個元素
    public E next()
    {
        E result = current.value();
        
        // 若current有右子樹,要找current略大值,
        // 可從右子樹找其連續最左小孩,即右子樹最小節點,當成下回拜訪節點
        if (!current.right().isEmpty()) {
            current = current.right();
            while (!current.left().isEmpty())
            {
                current = current.left();
            }
        }
        // 若current無右子樹,表示左子樹已拜訪完畢,要找比左子樹略大元素
        // 可往上一代不斷遷移,停在擁有拜訪完左子樹的親節點上,當成下回拜訪節點
        else {
            // we're finished with current's subtree.  We now pop off
            // nodes until we come to the parent of a leftchild ancestor
            // of current
            boolean lefty;
            do
            {
                lefty = current.isLeftChild();
                current = current.parent();
            } while (current != null && !lefty);
            if (current == null) current = new BinaryTree<E>();
        }
        return result;
    }
    
    /**
     * Return a reference to the current value.
     *
     * @pre hasNext()
     * @post returns current value
     * 
     * @return A reference to the current value.
     */
    // 回傳下一個元素,不取走
    public E get()
    {
        return current.value();
    }
}
