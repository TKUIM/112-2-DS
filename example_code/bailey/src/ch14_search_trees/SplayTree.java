// This is a implementation of splay trees, in Java.
// (c) 1996, 1997, 1998, 2001 duane a. bailey
// (c) 1998, 2001 duane a. bailey
package ch14_search_trees;
import java.util.Iterator;
import java.util.Comparator;
import structure5.NaturalComparator;
import structure5.OrderedStructure;

/**
 * An implementation of binary search trees, based on a splay operation
 * by Tarjan et al.  An extension of the binary search tree class that decreases
 * the likelyhood of a binary tree becomming degenerate.
 *
 * Example usage:
 * <P>
 * To create a  splay tree containing the months of the year
 * and to print out this tree as it grows we could use the following.
 * <P>
 * <pre>
 * public static void main(String[] argv){
 *     SplayTree test = new {@link  #SplayTree()};
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
 * @version $Id: SplayTree.java 35 2007-08-09 20:38:38Z bailey $
 * @author, 2001 duane a. bailey
 */
// 伸展樹
public class SplayTree<E extends Comparable<E>>
    extends BinarySearchTree<E> implements OrderedStructure<E>
{
    /**
     * Construct an empty search tree.
     *
     * @post construct a new splay tree
     * 
     */
    // 建立空伸展樹,排序用自然比較器
    public SplayTree()
    {
        this(new NaturalComparator<E>());
    }

    /**
     * Construct an empty search tree.
     *
     * @post construct a new splay tree
     * @param alternateOrder the ordering imposed on the values inserted
     * 
     */
    // 建立空伸展樹,排序用alternateOrder比較器
    public SplayTree(Comparator<E> alternateOrder)
    {
        super(alternateOrder);
    }

    /**
     * Add a value to the splay tree.
     *
     * @post adds a value to the binary search tree
     * 
     * @param val The value to be xadded.
     */
    // 添加val元素
    // 若val元素非樹根,將其伸展成新樹根
    public void add(E val)
    {
        BinaryTree<E> newNode = new BinaryTree<E>(val,EMPTY,EMPTY);

        // add value to binary search tree 
        // if there's no root, create value at root.
        if (root.isEmpty())
        {
            root = newNode;
        }
        else
        {
            BinaryTree<E> insertLocation = locate(root,val);
            E nodeValue = insertLocation.value();

            // The location returned is the successor or predecessor
            // of the to-be-inserted value.

            if (ordering.compare(nodeValue,val) < 0) {
                insertLocation.setRight(newNode);
            } else {
                if (!insertLocation.left().isEmpty()) {
                    // if value is in tree, we insert just before
                    predecessor(insertLocation).setRight(newNode);
                } else {
                    insertLocation.setLeft(newNode);
                }
            }
            splay(root = newNode);
        }
        count++;
    }

    /**
     * Determine if a particular value is within the search tree.
     *
     * @post returns true iff val is a value found within the tree
     * 
     * @param val The comparable value to be found.
     * @return True iff the comparable value is within the tree.
     */
    // 回傳包含val元素否布林值
    // 若真,將val節點伸展成新樹根
    public boolean contains(E val)
    {
        if (root.isEmpty()) return false;

        BinaryTree<E> possibleLocation = locate(root,val);
        if (val.equals(possibleLocation.value())) {
            splay(root = possibleLocation);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Fetch a reference to the comparable value in the tree.
     * Resulting value may be inspected, but should not be modified in
     * a way that might change its position within tree.
     *
     * @post returns object found in tree, or null
     * 
     * @param val The value to be sought in tree.
     * @return A reference to the value within the tree.
     */
    // 回傳val元素
    // 若成功, 回傳val元素
    // 若失敗, 回傳空
    public E get(E val)
    {
        if (root.isEmpty()) return null;

        BinaryTree<E> possibleLocation = locate(root,val);
        splay(root = possibleLocation);
        if (val.equals(possibleLocation.value()))
            return possibleLocation.value();
        else
            return null;
    }

    /**
     * Remove a comparable value from the tree.
     *
     * @post removes one instance of val, if found
     * 
     * @param val The value to be removed.
     * @return The actual value removed.
     */
    // 刪除val元素
    // 若成功,回傳刪除值,將其親節點伸展成新樹根
    // 若失敗,回傳空
    public E remove(E val) 
    {
        if (isEmpty()) return null;
      
        if (val.equals(root.value())) // delete root value 刪除根節點
        {
            BinaryTree<E> newroot = removeTop(root);
            count--;
            E result = root.value();
            root = newroot;
            return result;
        }
        else
        {
            // 刪除非根節點
            BinaryTree<E> location = locate(root,val);

            if (val.equals(location.value())) {
                count--;
                BinaryTree<E> parent = location.parent();
                if (parent.right() == location) {
                    parent.setRight(removeTop(location));
                } else {
                    parent.setLeft(removeTop(location));
                }
                // 將刪除節點的親節點伸展成新樹根
                splay(root = parent);
                return location.value();
            }
        }
        return null;
    }

    // 將splayedNode節點/樹，伸展到樹根，方便下回存取
    protected void splay(BinaryTree<E> splayedNode)
    {
        BinaryTree<E> parent,grandParent;

        // 直到splayedNode成為樹根才停止伸展
        while ((parent = splayedNode.parent()) != null)
        {
            if ((grandParent = parent.parent()) == null)
            {
                if (splayedNode.isLeftChild()) parent.rotateRight();
                else parent.rotateLeft();
            }
            else
            {
                if (parent.isLeftChild())
                {
                    if (splayedNode.isLeftChild())
                    {
                        // notice the order of this rotation.
                        // not doing this in order works, but not
                        // efficiently.
                        grandParent.rotateRight();
                        parent.rotateRight();
                    }
                    else
                    {
                        parent.rotateLeft();
                        grandParent.rotateRight();
                    }
                }
                else
                {
                    if (splayedNode.isRightChild()) {
                        grandParent.rotateLeft();
                        parent.rotateLeft();
                    }
                    else
                    {
                        parent.rotateRight();
                        grandParent.rotateLeft();
                    }
                }
            }
        }
    }

    /**
     * Construct an inorder traversal of the elements in the splay tree.
     *
     * @post returns iterator that traverses tree nodes in order
     * 
     * @return An iterator to traverse the tree.
     */
    // 回傳迭代器
    public Iterator<E> iterator()
    {
        return new SplayTreeIterator<E>(root,EMPTY);
    }

    /**
     * Construct a string that represents the splay tree.
     *
     * @post returns string representation
     * 
     * @return A string representing the tree.
     */
    // 回傳伸展樹單列內容字串
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<SplayTree: size="+count+" root="+root+">");
        return s.toString();
    }
}

