/*
*  BTPostorderIterator.java
*    二分樹的後序走訪迭代器
*    利用todo待走訪節點堆疊，輔助走訪
*    初始化: 堆疊依序放入二分樹左包絡節點  
*    走訪: 堆疊彈出列印節點,若節點為親節點的左小孩,則堆疊依序放入以親節點右小孩為樹根的二分樹左包絡節點
*/
// Post-order iterator for binary trees.
// (c) 1998, 2001 duane a. bailey
package ch12_binary_trees;

import structure5.AbstractIterator;
import structure5.Stack;
import structure5.StackList;

/**
 * This class implements a post-order traversal of a binary tree.
 * This iterator considers every node after its non-trivial descendants.
 * <P>
 * Example usage:
 * <P>
 * <pre>
 *      {@link structure.BinaryTree BinaryTree} t = new {@link structure.BinaryTree#BinaryTree() BinaryTree()};
 *      // ...tree is grown
 *      {@link java.util.Iterator Iterator} ti = t.{@link structure.BinaryTree#postorderIterator() postorderIterator()};
 *      while (ti.{@link #hasNext() hasNext()})
 *      {
 *          System.out.println(ti.{@link #next() next()});
 *      }
 *      ti.{@link #reset() reset()};
 *      while (ti.{@link #hasNext() hasNext()})
 *      { .... }
 * </pre>
 *
 * @see BinaryTree#PostorderElements
 * @version $Id: BTPostorderIterator.java 35 2007-08-09 20:38:38Z bailey $
 * @author, 2001 duane a. bailey
 */
class BTPostorderIterator<E> extends AbstractIterator<E>
{
    /**
     * The root of the tree currently being traversed.
     */
    protected BinaryTree<E> root; // root of traversed subtree
    /**
     * The stack the maintains the state of the iterator.
     * Elements of the stack are nodes whose descendants are still being
     * considered.
     */
    protected Stack<BinaryTree<E>> todo;  // stack of nodes
    // whose descendants are currently being visited

    /**
     * Construct an iterator to traverse subtree rooted at root
     * in post-order.
     *
     * @post constructs an iterator to traverse in postorder
     * 
     * @param root The root of the subtree to be traversed.
     */
    public BTPostorderIterator(BinaryTree<E> root)
    {
        todo = new StackList<BinaryTree<E>>();
        this.root = root;
        reset();
    }   

    /**
     * Reset the iterator to the first node of the traversal.
     *
     * @post Resets the iterator to retraverse
     */
    public void reset()
    {
        todo.clear();
        
        // stack is empty; push on nodes from root to
        // leftmost descendant
        // 堆疊依序放入以root為樹根的二分樹左包絡節點,作法如下
        // 1.由上到下放入樹根所有連續左小孩
        // 2.若連續左小孩的最後一個節點有右小孩，則視其右小孩為樹根，重覆1,2
        BinaryTree<E> current = root;
        while (!current.isEmpty()) {
            todo.push(current);
            if (!current.left().isEmpty())
                // 有左小孩,優先拜訪左小孩
                current = current.left();
            else
                // 沒左小孩,才拜訪右小孩
                current = current.right();
        }
    }

    /**
     * Return true iff more nodes are to be considered in traversal.
     *
     * @post Returns true iff iterator is not finished
     * 
     * @return True iff more nodes are to be considered in traversal.
     */
    public boolean hasNext()
    {
        return !todo.isEmpty();
    }

    /**
     * Return the value of the current node.
     *
     * @pre hasNext()
     * @post returns reference to current value
     * 
     * @return The value referenced by current node.
     */
    public E get()
    {   
        return todo.get().value();
    }

    /**
     * Return current value and increment iterator.
     *
     * @pre hasNext();
     * @post returns current value, increments iterator
     * 
     * @return The value currently considered by iterator, increment.
     */
    public E next()
    {
        // 堆疊彈出列印目前節點
        BinaryTree<E> current = todo.pop();
        E result = current.value();
        
        // 若目前節點為親節點的左小孩，
        // 則堆疊依序放入以親節點右小孩為樹根的二分樹左包絡節點,作法如下
        // 1.由上到下放入樹根所有連續左小孩
        // 2.若連續左小孩的最後一個節點有右小孩，則視其右小孩為樹根，重覆1,2
    if (!todo.isEmpty())
        {
            BinaryTree<E> parent = todo.get();
            if (current == parent.left()) {
                current = parent.right();
                while (!current.isEmpty())
                {
                    todo.push(current);
                    if (!current.left().isEmpty())
                        // 有左小孩,優先拜訪左小孩
                         current = current.left();
                    else 
                        // 沒左小孩,才拜訪右小孩
                        current = current.right();
                }
            }
        }
        return result;
    }
}
