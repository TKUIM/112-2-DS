// An implementation of a priority queue in a vector.
// (c) 1998, 2001, 2002 duane a. bailey
package ch13_priority_queues;

import structure5.Vector;
/**
 * This class implements a priority queue based on a traditional
 * array-based heap.  Most heap operations, including insert and remove,
 * execute in logarithmic time, but the minimum element can be returned 
 * in constant time. 
 *
 * <P>
 * Example usage:
 * <P>
 * To print out a list of programmers sorted by age we could use the following:
 * <pre>
 * public static void main(String[] argv){
 *      //initialize a new fib heap
 *      VectorHeap programmers = new {@link #VectorHeap()};
 *
 *      //add programmers and their ages to heap
 *      //ages current of 7/22/2002
 *      //add programmers and their ages to heap
 *      //ages current of 7/22/2002
 *        programmers.{@link #add(Comparable) add(new ComparableAssociation(new Integer(22), "Evan"))};
 *      programmers.add(new ComparableAssociation(new Integer(19), "Chris"));
 *      programmers.add(new ComparableAssociation(new Integer(20), "Shimon"));
 *      programmers.add(new ComparableAssociation(new Integer(21), "Diane"));
 *      programmers.add(new ComparableAssociation(new Integer(21), "Lida"));    
 *      programmers.add(new ComparableAssociation(new Integer(20), "Rob"));     
 *      programmers.add(new ComparableAssociation(new Integer(20), "Sean"));    
 *
 *      //print out programmers 
 *      while(!programmers.{@link #isEmpty()}){
 *          ComparableAssociation p = (ComparableAssociation)programmers.{@link #remove()};
 *          System.out.println(p.getValue() + " is " + p.getKey() + " years old.");
 *      }
 * }
 * </pre>
 * @version $Id: VectorHeap.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
// 向量堆積，二分樹須長全，從樹根往葉節點看，每個邊維持上節點 <= 下節點
public class VectorHeap<E extends Comparable<E>> implements PriorityQueue<E>
{
    /**
     * The data, kept in heap order.
     */
    protected Vector<E> data;  // the data, kept in heap order 放元素的向量容器

    /**
     * Construct a new priority queue
     *
     * @post constructs a new priority queue
     */
    // 建空向量堆積
    public VectorHeap()
    {
        data = new Vector<E>();
    }

    /**
     * Construct a new priority queue from an unordered vector
     *
     * @post constructs a new priority queue from an unordered vector
     */
    // 利用向量容器v的無順序元素，建立一個向量堆積
    public VectorHeap(Vector<E> v)
    {
        int i;
        data = new Vector<E>(v.size()); // we know ultimate size
        for (i = 0; i < v.size(); i++)
        {   // add elements to heap
            add(v.get(i));
        }
    }

    /**
     * Returns parent index
     * @param i a node index
     * @return parent of node at i
     * @pre 0 <= i < size
     * @post returns parent of node at location i
     */
    // 回傳下標i節點的親節點下標
    protected static int parent(int i)
    {
        return (i-1)/2;
    }

    /**
     * Returns left child index.
     * @param i a node index
     * @return left child of node at i
     * @pre 0 <= i < size
     * @post returns index of left child of node at location i
     */
    // 回傳下標i節點的左小孩節點下標
    protected static int left(int i)
    {
        return 2*i+1;
    }

    /**
     * Returns right child index.
     * @param i a node index
     * @return right child of node at i
     * @pre 0 <= i < size
     * @post returns index of right child of node at location i
     */
    // 回傳下標i節點的右小孩節點下標
    protected static int right(int i)
    {
        return 2*(i+1);
    }

    /**
     * Fetch lowest valued (highest priority) item from queue.
     *
     * @pre !isEmpty()
     * @post returns the minimum value in priority queue
     * 
     * @return The smallest value from queue.
     */
    // 回傳最小元素
    public E getFirst()
    {
        return data.get(0);
    }

    /**
     * Returns the minimum value from the queue.
     *
     * @pre !isEmpty()
     * @post returns and removes minimum value from queue
     * 
     * @return The minimum value in the queue.
     */
    // 移除最小元素，調整堆積
    public E remove()
    {
        E minVal = getFirst(); // 取得最小元素
        data.set(0,data.get(data.size()-1)); // 將向量尾元素拷貝到向量頭，暫時當成樹根
        data.setSize(data.size()-1); // 向量長度減1
        if (data.size() > 1) pushDownRoot(0); // 從樹根出發，將重元素往下沈沒，直到停在合適位置
        return minVal; // 回傳最小值
    }

    /**
     * Add a value to the priority queue.
     *
     * @pre value is non-null comparable
     * @post value is added to priority queue
     * 
     * @param value The value to be added.
     */
    // 新增value元素
    public void add(E value)
    {
        data.add(value); // 新元素放向量尾
        percolateUp(data.size()-1);  // 從向量尾出發，將輕元素向上浮起，直到停在合適位置
    }

    /**
     * Determine if the queue is empty.
     *
     * @post returns true iff no elements are in queue
     * 
     * @return True if the queue is empty.
     */
    // 回傳堆積空否布林值
    public boolean isEmpty()
    {
        return data.size() == 0;
    }

    /**
     * Moves node upward to appropriate position within heap.
     * @param leaf Index of the node in the heap.
     * @pre 0 <= leaf < size
     * @post moves node at index leaf up to appropriate position
     */
    // 從leaf下標位置出發，將輕元素向上浮起，直到停在合適位置
    protected void percolateUp(int leaf)
    {
        int parent = parent(leaf);
        E value = data.get(leaf);
        while (leaf > 0 &&
          (value.compareTo(data.get(parent)) < 0))
        {
            data.set(leaf,data.get(parent));
            leaf = parent;
            parent = parent(leaf);
        }
        data.set(leaf,value);
    }

    /**
     * Moves node downward, into appropriate position within subheap.
     * @param root Index of the root of the subheap.
     * @pre 0 <= root < size
     * @post moves node at index root down 
     *   to appropriate position in subtree
     */
    // 從樹根root下標位置出發，將重元素往下沈沒，直到停在合適位置
    protected void pushDownRoot(int root)
    {
        int heapSize = data.size();
        E value = data.get(root); // 取出要下沈的重元素，其初始候選位置root
        
        while (root < heapSize) {
            int childpos = left(root);  // 先假設下沈元素候選位置的較輕小孩為左小孩
            if (childpos < heapSize)
            {
                // 若候選位置root有左小孩存在
                if ((right(root) < heapSize) &&
                  ((data.get(childpos+1)).compareTo
                   (data.get(childpos)) < 0))
                {
                    // 若候選位置root也右小孩存在，且右小孩 <  左小孩，
                    // 則較輕小孩為右小孩
                    childpos++;
                }
                // Assert: childpos indexes smaller of two children
                if ((data.get(childpos)).compareTo
                    (value) < 0)
                {
                    // 若下沈元素 > 較輕小孩，表示還要再往下沈，目前候選位置root先放入較輕小孩
                    data.set(root,data.get(childpos));
                    root = childpos; // keep moving down 將候選位置root設為較輕小孩位置，繼續往下找
                } else { // found right location
                    // 若下沈元素 <= 較輕小孩，表示下沈元素放此候選位置root很穩定，不必再下沈
                    data.set(root,value);  // 可於候選位置root放入下沈元素
                    return;
                }
            } else { // at a leaf! insert and halt 
                // 因二分樹須長全，若候選位置root無左小孩，表示候選位置root為葉節點，毋須再下沈
                data.set(root,value);   // 可於候選位置root放入下沈元素
                return;
            }       
        }
    }

    /**
     * Determine the size of the queue.
     *
     * @post returns number of elements within queue
     * 
     * @return The number of elements within the queue.
     */
    // 回傳堆積元素個數
    public int size()
    {
        return data.size();
    }

    /**
     * Remove all the elements from the queue.
     *
     * @post removes all elements from queue
     */
    // 清空堆積
    public void clear()
    {
        data.clear();
    } 
    
    /**
     * Construct a string representation of the heap.
     *
     * @post returns string representation of heap
     * 
     * @return The string representing the heap.
     */
    // 列印向量內容
    public String toString()
    {
        return "<VectorHeap: "+data+">";
    }
}
