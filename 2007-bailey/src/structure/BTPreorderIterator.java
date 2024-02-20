// Pre-order iterator for binary trees.
// (c) 1998, 2001 duane a. bailey
package structure;


/**
 * This class implements an iterator that traverses a tree in pre-order.
 * Each node is considered before its descendants.
 *
 * @version $Id: BTPreorderIterator.java 19 2006-08-10 04:52:00Z bailey $
 * @author, 2001 duane a. bailey
 */
class BTPreorderIterator extends AbstractIterator
{
    /**
     * The root of the subtree to be considered by traversal.
     */
    protected BinaryTree root; // root of tree to be traversed
    /**
     * The stack that maintains the state of the iterator.
     */
    protected Stack todo; // stack of unvisited nodes whose

    /**
     * Constructs a pre-order traversal of subtree rooted at root.
     *
     * @post constructs an iterator to traverse in preorder
     * 
     * @param root Root of subtree to be traversed.
     */
    public BTPreorderIterator(BinaryTree root)
    {
        todo = new StackList();
        this.root = root;
        reset();
    }   

    /**
     * Resets the iterator to the first node of the traversal.
     *
     * @post resets the iterator to retraverse
     */
    public void reset()
    {
        todo.clear(); // stack is empty; push on root
        if (root != null) todo.push(root);
    }

    /**
     * Returns true if some nodes of subtree have yet to be considered.
     *
     * @post returns true iff iterator is not finished
     * 
     * @return True iff more nodes to be considered in traversal.
     */
    public boolean hasNext()
    {
        return !todo.isEmpty();
    }

    /**
     * Returns the value currently being referenced by iterator.
     *
     * @pre hasNext()
     * @post returns reference to current value
     * 
     * @return The current value.
     */
    public Object get()
    {   
        return ((BinaryTree)todo.get()).value();
    }

    /**
     * Returns the current value and increments the iterator.
     * Iterator is then incremented.
     *
     * @pre hasNext();
     * @post returns current value, increments iterator
     * 
     * @return The value currently being considered.
     */
    public Object next()
    {
        BinaryTree old = (BinaryTree)todo.pop();
        Object result = old.value();
        
        if (!old.right().isEmpty()) todo.push(old.right());
        if (!old.left().isEmpty()) todo.push(old.left());
        return result;
    }
}
