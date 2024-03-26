// An implementation of an ordered structure, using lists
// (c) 1998, 2001 duane a. bailey

package ch11_ordered_structures;
import java.util.Iterator;
import java.util.Comparator;
import structure5.AbstractStructure;
import structure5.Node;

/**
 * A class that implements a collection of values that are kept in order.
 * Base values must be comparable.  Unlike Lists there is no notion of head
 * or tail.
 * <P>
 * Example Usage:
 * <P>
 * To determine the effect of the original Starwars&trade; movie on the careers
 * of its stars, we could place ComparableAssociations between each star's
 * name and the number of movies they have been in since Starwars&trade;
 * into an ordered vector and print our the results, as follows:
 * <pre>
 * public static void main(String[] argv){
 *      //instantiate an ordered vector
 *      OrderedList<ComparableAssociation<Integer,String>> v = new {@link #OrderedList<ComparableAssociation<Integer,String>>()};
 *      
 *      //add the cast members of the original star wars along with
 *      //the number of films in which the have subsequently appeared
 *      v.{@link #add(Object) add(new ComparableAssociation<Integer,String>(new Integer(12),"Sir Alec Guiness"))};
 *      v.{@link #add(Object) add(new ComparableAssociation<Integer,String>(new Integer(24),"Carrie Fisher"))};
 *      v.{@link #add(Object) add(new ComparableAssociation<Integer,String>(new Integer(28),"Harrison Ford"))}; 
 *      v.{@link #add(Object) add(new ComparableAssociation<Integer,String>(new Integer(28),"Mark Hamill"))};
 *
 *      //print out the results
 *      for(Iterator<ComparableAssociation<Integer,String>> i = v.{@link #iterator()}; i.hasNext();){
 *          ComparableAssociation<Integer,String> actor = i.next();
 *          System.out.println(actor.getValue() + " has been in " + 
 *                             actor.getKey() + " movies since Star Wars"); 
 *      }
 *   }
 * </pre>
 * @see structure.Vector
 *
 * @version $Id: OrderedList.java 31 2007-08-06 17:19:56Z bailey $
 * @author, 2001 duane a. bailey
 */
// 有序清單，鏈結版
public class OrderedList<E extends Comparable<E>>
    extends AbstractStructure<E> implements OrderedStructure<E>
{
    /**
     * Pointer to the smallest element, maintained as a singly linked list
     */
    protected Node<E> data; // smallest value 單向鏈結清單的首節點指標
    /**
     * Number of elements in list
     */
    protected int count;        // number of values in list 長度
    /**
     * The ordereding used to arange the values
     */
    protected Comparator<? super E> ordering;   // the comparison function 比較器

    /**
     * Construct an empty ordered list
     *
     * @post constructs an empty ordered list
     */
    // 利用自然比較器建立空的有序清單
    public OrderedList()
    {
        this(new NaturalComparator<E>());
    }

    /**
     * Construct an empty ordered list with alternative ordering
     *
     * @param ordering the Comparator to be used in comparison
     * @post constructs an empty ordered list ordered by ordering
     */
    // 利用ordering比較器,建立空的有序清單
    public OrderedList(Comparator<? super E> ordering)
    {
        this.ordering = ordering;
        clear();
    }

    /**
     * Remove all the elements from the ordered list
     *
     * @post the ordered list is empty
     */
    // 清單清空
    public void clear()
    {
        data = null;
        count = 0;
    }
    
    /**
     * Add a value to the ordered list, keeping values in order
     *
     * @pre value is non-null
     * @post value is added to the list, leaving it in order
     * 
     * @param value The value to be added to the list
     */
    // 清單加入 value 元素,保持排序
    public void add(E value)
    {
        Node<E> previous = null; // element to adjust, 指向finger前一個節點
        Node<E> finger = data;   // target element, finger從首節點找起
        
        // search for the correct location
        // 繼續條件: finger 非空  且  finger.value < value
        while ((finger != null) &&
               ordering.compare(finger.value(),value) < 0)
        {
            previous = finger;  // previous指向finger節點
            finger = finger.next();  // finger指向下一個節點
        }
        // 跳出條件: finger 為空 或 finger.value >= value
        
        // spot is found, insert
        // 原來: previous -> previous.next
        // 目標: previous -> data -> previous.next
        if (previous == null) // check for insert at top
        {
            // 特例
            data = new Node<E>(value,data);
        } else {
            // 通例
            previous.setNext(
               new Node<E>(value,previous.next()));
        }
        count++; // 長度加1
    }

    /**
     * Determine if the ordered list contains a value
     *
     * @pre value is a non-null comparable object
     * @post returns true iff contains value
     * 
     * @param value The value sought in the list
     * @return The actual value found, or null, if not
     */
    // 回傳清單是否含有value元素的布林值
    public boolean contains(E value)
    {
        Node<E> finger = data; // target 從首節點找起
        // search down list until we fall off or find bigger value
        // 繼續條件: finger 非空  且  finger.value < value
        while ((finger != null) &&
               ordering.compare(finger.value(),value) < 0)
        {
            finger = finger.next(); // finger指向下一個節點
        }
        // 跳出條件: finger 為空 或 finger.value >= value

        // finger指向null表示找不到, finger指向value節點表示找到
        return finger != null && value.equals(finger.value());
    }

    /**
     * Remove a value from the ordered list.  At most one value
     * is removed.
     *
     * @pre value is non-null
     * @post an instance of value is removed, if in list
     * 
     * @param value The value to be removed
     * @return The actual value removed from the list
     */
    // 清單刪除value元素
    public E remove(E value)
    {
        Node<E> previous = null; // element to adjust 指向finger前一個節點
        Node<E> finger = data;   // target element finger從首節點找起
        // search for value or fall off list
        // 繼續條件: finger 非空  且  finger.value < value
        while ((finger != null) &&
               ordering.compare(finger.value(),value) < 0)
        {
            previous = finger;  // previous指向finger節點
            finger = finger.next(); // finger指向下一個節點
        }
        // 跳出條件: finger 為空 或 finger.value >= value

        // did we find it?
         if ((finger != null) && value.equals(finger.value())) {
           // yes, remove it
           // 找到時,finger指向欲刪除節點
           // 原來:  previous -> finger -> finger.next
           // 目標:  previous -> finger.next
           if (previous == null)  // at top? 
            {
                // 特例, 首節點指標指向finger.next
                data = finger.next();
            } else {
                // 通例, previous指向finger.next
                previous.setNext(finger.next());
            }
            count--; // 長度減1
            // return value
            return finger.value(); // 回傳刪除值
        }
        // return nonvalue
        return null; // 未找到
    }

    /**
     * Determine the number of elements in the list
     *
     * @pre returns the number of elements in the ordered list
     * 
     * @return The number of elements in the list
     */
    // 回傳清單長度
    public int size()
    {
        return count;
    }

    /**
     * Determine if the list is empty
     *
     * @post returns true iff the size is non-zero
     * 
     * @return True if the ordered list is empty
     */
    // 回傳清單空否布林值
    public boolean isEmpty()
    {
        return size() == 0;
    }
	
    /**
     * Construct an iterator for traversing elements of ordered list
     * in ascending order
     *
     * @post returns an iterator over ordered list
     * 
     * @return An iterator traversing elements in ascending order
     */
    // 回傳清單迭代器
    public Iterator<E> iterator()
    {
        return new SinglyLinkedListIterator<E>(data);
    }

    /**
     * Generate string representation of the ordered list
     *
     * @post returns string representation of list
     * 
     * @return String representing ordered list
     */
    // 回傳有序清單內容字串，元素間用空格隔開
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<OrderedList:");
        Iterator si = iterator();
        while (si.hasNext())
        {
            s.append(" "+si.next());
        }
        s.append(">");
        return s.toString();
    }
}
