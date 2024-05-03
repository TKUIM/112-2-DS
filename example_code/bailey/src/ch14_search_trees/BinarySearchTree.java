// This is an implementation of binary search trees.
// (c) 1998, 2001 duane a. bailey
package ch14_search_trees;
import java.util.Iterator;
import java.util.Comparator;
import structure5.AbstractStructure;
import structure5.Assert;
import structure5.BinaryTree;
import structure5.NaturalComparator;
import structure5.OrderedStructure;

/**
 * A binary search tree structure.  This structure maintains data
 * in an ordered tree.  It does not keep the tree balanced, so performance
 * may degrade if the tree height is not optimal.
 *   二分搜索樹，有排序檢索能力，未考慮維持平衡
 *   樹和樹根節點兩者不分，都以樹作代表；空小孩有串接虛節點；親子節點互相指向對方
 * <P>
 * Example usage:
 * <P>
 * To create a Binary search tree containing the months of the year
 * and to print out this tree as it grows we could use the following.
 * <P>
 * <pre>
 * public static void main(String[] argv){
 *     BinarySearchTree test = new {@link  #BinarySearchTree()};
 *       
 *     //declare an array of months
 *     String[] months = new String[]{"March", "May", "November", "August", 
 *                                    "April", "January", "December", "July",
 *                                    "February", "June", "October", "September"};
 *      
 *     //add the months to the tree and print out the tree as it grows
 *     for(int i=0; i < months.length; i++){
 *        test.{@link #add(Object) add(months[i])};
 *        System.out.println("Adding: " + months[i] + "\n" +test.{@link #treeString()});
 *      }
 *  }
 * </pre>
 *
 * @version $Id: BinarySearchTree.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 * @see SplayTree
 * @see BinaryTree
 */
public class BinarySearchTree<E extends Comparable<E>>
    extends AbstractStructure<E> implements OrderedStructure<E>
{
    /**
     * A reference to the root of the tree
     */
    protected BinaryTree<E> root;  // 樹根

    /**
     * The node used as all leaves, in this implementation.
     */
    protected final BinaryTree<E> EMPTY = new BinaryTree<E>();  // 空樹專用虛節點

    /**
     * The number of nodes in the tree
     */ 
    protected int count;  // 元素個數
    /**
     * The ordering used on this search tree.
     */
    protected Comparator<E> ordering;  // 排序比較器


    /**
     * Constructs a binary search tree with no data
     *
     * @post Constructs an empty binary search tree
     */
    // 建空樹，排序用預設的自然比較器
    public BinarySearchTree()
    {
        this(new NaturalComparator<E>());
    }

    /**
     * Constructs a binary search tree with no data
     *
     * @post Constructs an empty binary search tree
     */
    // 建空樹，排序用alternateOrder比較器
    public BinarySearchTree(Comparator<E> alternateOrder)
    {
        root = EMPTY;  // 樹根指向空樹
        count = 0;
        ordering = alternateOrder;
    }

    /**
     * Checks for an empty binary search tree
     *
     * @post Returns true iff the binary search tree is empty
     * 
     * @return True iff the tree contains no data
     */
    // 回傳樹空否布林值
    public boolean isEmpty()
    {
        return root == EMPTY;
    }

    /**
     * Removes all data from the binary search tree
     *
     * @post Removes all elements from binary search tree
     */
    // 清空搜索樹
    public void clear()
    {
        root = new BinaryTree<E>(); // 也可設為EMPTY空樹
        count = 0;
    }

    /**
     * Determines the number of data values within the tree
     *
     * @post Returns the number of elements in binary search tree
     * 
     * @return The number of nodes in the binary search tree
     */
    // 回傳元素個數
    public int size()
    {
        return count;
    }
    
    /**
     * @pre root and value are non-null
     * @post returned: 1 - existing tree node with the desired value, or
     *                 2 - the node to which value should be added
     */
    // 回傳root樹根二分搜索樹存放value值的節點/樹
    // 若不存在，回傳應加入節點/樹
    protected BinaryTree<E> locate(BinaryTree<E> root, E value)
    {
        E rootValue = root.value();
        BinaryTree<E> child;

        // found at root: done
        if (rootValue.equals(value)) return root;
        // look left if less-than, right if greater-than
        if (ordering.compare(rootValue,value) < 0)
        {
            child = root.right();
        } else {
            child = root.left();
        }
        // no child there: not in tree, return this node,
        // else keep searching
        if (child.isEmpty()) {
            return root;
        } else {
            return locate(child, value);
        }
    }

    // 回傳排序下root節點/樹的前一個節點/樹
    protected BinaryTree<E> predecessor(BinaryTree<E> root)
    {
        Assert.pre(!root.isEmpty(), "No predecessor to middle value.");
        Assert.pre(!root.left().isEmpty(), "Root has left child.");
        // 回傳左子樹的連續最右小孩
        BinaryTree<E> result = root.left();
        while (!result.right().isEmpty()) {
            result = result.right();
        }
        return result;
    }
    
    //  回傳排序下root節點/樹的後一個節點/樹
    protected BinaryTree<E> successor(BinaryTree<E> root)
    {
        Assert.pre(!root.isEmpty(), "Tree is non-null.");
        Assert.pre(!root.right().isEmpty(), "Root has right child.");
        // 回傳右子樹的連續最左小孩
        BinaryTree<E> result = root.right();
        while (!result.left().isEmpty()) {
            result = result.left();
        }
        return result;
    }

    /**
     * Add a (possibly duplicate) value to binary search tree
     *
     * @post Adds a value to binary search tree
     * 
     * @param val A reference to non-null object
     */
    // 搜索樹添加value值，值可重複添加
    public void add(E value)
    {
        BinaryTree<E> newNode = new BinaryTree<E>(value,EMPTY,EMPTY);

        // add value to binary search tree 
        // if there's no root, create value at root
        if (root.isEmpty())
        {
            root = newNode;
        } else {
            BinaryTree<E> insertLocation = locate(root,value);
            E nodeValue = insertLocation.value();
            // The location returned is the successor or predecessor
            // of the to-be-inserted value
            if (ordering.compare(nodeValue,value) < 0) {
                insertLocation.setRight(newNode);
            } else {
                if (!insertLocation.left().isEmpty()) {
                    // if value is in tree, we insert just before
                    predecessor(insertLocation).setRight(newNode);
                } else {
                    insertLocation.setLeft(newNode);
                }
            }
        }
        count++;
    }

    /**
     * Determines if the binary search tree contains a value
     *
     * @post Returns true iff val is a value found within the tree
     * 
     * @param val The value sought.  Should be non-null
     * @return True iff the tree contains a value "equals to" sought value
     */
    // 回傳搜索樹包含value值否布林值
    public boolean contains(E value)
    {
        if (root.isEmpty()) return false;

        BinaryTree<E> possibleLocation = locate(root,value);
        return value.equals(possibleLocation.value());
    }

    /**
     * Returns reference to value found within three.  This method can
     * be potentially dangerous if returned value is modified: if 
     * modification would change the relation of value to others within
     * the tree, the consistency of the structure is lost
     * <b>Don't modify returned value</b>
     *
     * @post Returns object found in tree, or null
     * 
     * @param val Value sought from within tree
     * @return A value "equals to" value sought; otherwise null
     */
    // 回傳value值應放節點的值
    // 若存在,回傳該值；否則回傳空
    public E get(E value)
    {
        if (root.isEmpty()) return null;

        BinaryTree<E> possibleLocation = locate(root,value);
        if (value.equals(possibleLocation.value()))
          return possibleLocation.value();
        else
          return null;
    }

    /**
     * Remove an value "equals to" the indicated value.  Only one value
     * is removed, and no guarantee is made concerning which of duplicate
     * values are removed.  Value returned is no longer part of the
     * structure
     *
     * @post Removes one instance of val, if found
     * 
     * @param val Value sought to be removed from tree
     * @return Actual value removed from tree
     */
    // 搜索樹刪除value值
    // 若成功,回傳刪除值
    // 若失敗,回傳
    public E remove(E value) 
    {
        if (isEmpty()) return null;
      
        if (value.equals(root.value())) // delete root value 特例: 刪樹根
        {
            BinaryTree<E> newroot = removeTop(root);
            count--;
            E result = root.value();
            root = newroot;
            return result;
        }
        else
        {
            // 通例: 刪非樹根節點
            BinaryTree<E> location = locate(root,value);

            if (value.equals(location.value())) {
                count--;
                BinaryTree<E> parent = location.parent();
                if (parent.right() == location) {
                    parent.setRight(removeTop(location));
                } else {
                    parent.setLeft(removeTop(location));
                }
                return location.value();
            }
        }
        return null;
    }

    /**
     * Removes the top node of the tree rooted, performs the necissary
     * rotations to reconnect the tree.
     *
     * @pre topNode contains the value we want to remove
     * @post We return an binary tree rooted with the predecessor of topnode.
     * @param topNode Contains the value we want to remove
     * @return The root of a new binary tree containing all of topNodes 
     * descendents and rooted at topNode's predecessor
     */
    // 刪除樹根,回傳刪除節點/樹
    // 新樹根為排序下原樹根的前一個節點
    protected BinaryTree<E> removeTop(BinaryTree<E> topNode)
    {
        // remove topmost BinaryTree from a binary search tree
        BinaryTree<E> left  = topNode.left();
        BinaryTree<E> right = topNode.right();
        // disconnect top node
        topNode.setLeft(EMPTY);
        topNode.setRight(EMPTY);
        // Case a, no left BinaryTree
        //   easy: right subtree is new tree
        if (left.isEmpty()) { return right; }
        // Case b, no right BinaryTree
        //   easy: left subtree is new tree
        if (right.isEmpty()) { return left; }
        // Case c, left node has no right subtree
        //   easy: make right subtree of left
        BinaryTree<E> predecessor = left.right();
        if (predecessor.isEmpty())
        {
            left.setRight(right);
            return left;
        }
        // General case, slide down left tree
        //   harder: successor of root becomes new root
        //           parent always points to parent of predecessor
        BinaryTree<E> parent = left;
        while (!predecessor.right().isEmpty())
        {
            parent = predecessor;
            predecessor = predecessor.right();
        }
        // Assert: predecessor is predecessor of root
        parent.setRight(predecessor.left());
        predecessor.setLeft(left);
        predecessor.setRight(right);
        return predecessor;
    }

    /**
     * Returns an iterator over the binary search tree.  Iterator should
     * not be used if tree is modified, as behavior may be unpredicatable
     * Traverses elements using in-order traversal order
     *
     * @post Returns iterator to traverse BST
     * 
     * @return An iterator over binary search tree
     */
    // 回傳搜索樹的中序迭代器
    public Iterator<E> iterator()
    {
        return root.inorderIterator();
    }

    /**
     * Returns the hashCode of the value stored by this object.
     *
     * @return The hashCode of the value stored by this object.
     */
    // 回傳樹根節點的雜湊碼
    public int hashCode(){
        return root.hashCode();
    } 

    /**
     * Returns a (possibly long) string representing tree.  Differs
     * from {@link #toString()} in that {@link #toString()} outputs 
     * a single line representation of the contents of the tree.
     * <code>treeString</code>, however, prints out a graphical 
     * representations of the tree's <i>structure</i>.
     * 
     * @post Generates a string representation of the AVLST
     * @return String representation of tree
     */
    // 回傳AVL搜索樹(AVLST)多列圖示字串
    public String treeString(){
        return root.treeString();
    }

    /**
     * Returns a string representing tree
     *
     * @post Generates a string representation of the BST
     * 
     * @return String representation of tree
     */
    // 回傳二分搜索樹(BST)單列內容字串
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<BinarySearchTree:");
        if (!root.isEmpty()) {
            s.append(root);
        }
        s.append(">");
        return s.toString();
    }
}
