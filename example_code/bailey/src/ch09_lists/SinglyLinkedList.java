// Implementation of lists, using singly linked elements.
// (c) 1998, 2001 duane a. bailey
/*
   SinglyLinkedList.java 單向鏈結清單
     內含長度count，首節點指標head

*/

package ch09_lists;
import java.util.Iterator;
import java.util.List;
import java.util.AbstractList;
import java.util.Enumeration;
import structure5.Assert;
/**
 * An implementation of lists using singly linked elements, similar to that of {@link java.util.LinkedList java.util.LinkedList}.
 * <p>       
 * This class is a basic implementation of the {@link List} interface.
 * Operations accessing or modifying the head of the list execute in constant
 * time.
 * Operations accessing or modifying the tail of the list execute in a time
 * proportional to the length of the list.
 * Singly linked lists are space-efficient, but tail-related operations may be more
 * costly than with doubly linked lists.
 * <p>
 * Example usage:
 *
 * To place a copy of every unique parameter passed to a program into a 
 * SinglyLinkedList,  we would use the following:
 * <pre>
 * public static void main({@link java.lang.String String[]} arguments)
 * {
 *     {@link SinglyLinkedList} argList = new {@link #SinglyLinkedList()};
 *     for (int i = 0; i < arguments.length; i++){
 *         if (!argList.{@link #contains(Object) contains(arguments[i])}){
 *             argList.{@link #add(Object) add(arguments[i])};
 *         }
 *    }
 *    System.out.println(argList);
 * }
 * </pre>
 * @version $Id: SinglyLinkedList.java 31 2007-08-06 17:19:56Z bailey $
 * @author, 2001 duane a. bailey
 * @see DoublyLinkedList
 * @see CircularList
 */
// 單向鏈結清單
public class SinglyLinkedList<E> extends AbstractList<E>
{
    /**
     * The number of elements in list.
     */
    protected int count;                    // list size 長度
    /**
     * The head of the list.  A reference to a singly linked list element.
     */
    protected Node<E> head; // ref. to first element 首節點指標

    /**
     * Construct an empty list.
     *
     * @post generates an empty list
     */
    // 建構空清單
    public SinglyLinkedList()
    {
        head = null;
        count = 0;
    }

    /**
     * Add an object to tail of list.
     *
     * @post value is added to end of list (see addLast)
     * 
     * @param value The value to be added to tail of list.
     */
    // 加尾元素value
    public void add(E value)
    {
        addLast(value);
    }
    
    /**
     * Add a value to head of list.
     *
     * @post value is added to beginning of list
     * 
     * @param value The value to be added to head of list.
     */
    // 加首元素value
    public void addFirst(E value)
    {
        // 目前順序: head -> ...
        // 目標順序: temp -> head -> ...

        // note order that things happen:
        // head is parameter, then assigned
        // 建立新節點，其data值為value，其nextElement指標指向 head 節點
        // head指標指向新節點
        head = new Node<E>(value, head);
        
        // 清單長度加1
        count++;
    }

    /**
     * Remove a value from first element of list.
     *
     * @pre list is not empty
     * @post removes and returns value from beginning of list
     * 
     * @return The value actually removed.
     */
    // 刪首元素
    public E removeFirst()
    {
        Node<E> temp = head;  // 備份刪除的首節點

        // 目前順序: head -> head.next ...
        // 目標順序: head.next ...
        // 新首節點指標 指向 舊首節點的下一個節點
        head = head.next(); // move head down list
        
        count--; // 長度減1

        return temp.value(); // 回傳首元素
    }

    /**
     * Add a value to tail of list.
     *
     * @post adds value to end of list
     * 
     * @param value The value to be added to tail of list.
     */
    // 加尾元素value
    public void addLast(E value)
    {
        // location for new value
        // 配置 temp 新節點，其data值為value，其nextElement指標為空
        Node<E> temp = new Node<E>(value,null);
        
        // 若清單有首節點
        if (head != null)
        {
            // pointer to possible tail
            // 有首節點，則迭代找尾節點，其特徵為節點的nextElement指標為空
            Node<E> finger = head; // finger指標從指向head首節點開始
            while (finger.next() != null) // 若finger節點的nextElement指標非空
            {
                finger = finger.next(); // finger指標指向下一個節點
            }
            // 離開迴圈時，finger指標指向尾節點
            // 目前順序: finger -> null
            // 目標順序: finger -> temp -> null
            
            // finger指標找到清單尾節點
            finger.setNext(temp); // 尾節點的指標指向temp新節點
        } 
        // 0->1例外處理
        // 若清單無首節點，讓temp當首節點
        else head = temp;
        
        // 清單長度加1
        count++;
    }

    /**
     * Remove last value from list.
     *
     * @pre list is not empty
     * @post removes last value from list
     * 
     * @return The value actually removed.
     */
    // 刪尾元素，回傳刪除的尾元素。
    // 因為沒有尾指標，須走訪找尋尾節點，故花費時間 O(n)。
    public E removeLast()
    {
        Node<E> finger = head;  // finger指標從首節點出發，找尋舊尾節點
        Node<E> previous = null; // previous指標指向finger前一個節點，初值為空，代表新尾節點

        // 防呆檢查，首節點非空須成立，否則拋出例外
        Assert.pre(head != null,"List is not empty.");

        // 迴圈利用finger/previous兩相鄰指標同步往後遞移，找尾節點及其前一個節點，即新尾節點
        while (finger.next() != null) // find end of list 只要finger指標非尾節點
        {
            previous = finger; // previous指標 指向 舊finger物件；覆蓋finger前，讓previous留其備份
            finger = finger.next();  // 新finger指標 指向 舊finger物件的下一個節點；讓finger往後遞移
        }
        // finger is null, or points to end of list
        // 迴圈結束時，finger指標指向舊尾節點，previous指標指向尾節點前一個節點，即新尾節點
        // 目前順序: previous -> finger -> null
        // 目標順序: previous -> null

        // 1->0例外處理
        //   若previous指標為空，表示清單僅有一個節點，刪尾後，無新尾節點
        if (previous == null)
        {
            // has exactly one element
            head = null; // 則將清單首節點指標指向空，讓清單變成空清單
        }
        // 通例處理
        //   若previous指標非空，表示清單有兩個以上節點，刪尾後，previous物件為新的尾節點
        else  
        {
            // pointer to last element is reset
            previous.setNext(null); // 則將新尾節點的下一個節點指標指向空
        }
        
        count--;  // 長度減1
        return finger.value(); // 回傳刪除的尾元素
    }

    /**
     * Fetch first element of list.
     *
     * @pre list is not empty
     * @post returns first value in list
     * 
     * @return A reference to first element of list.
     */
    // 回傳首元素
    public E getFirst()
    {
        return head.value();
    }

    /**
     * Fetch last element of list.
     *
     * @pre list is not empty
     * @post returns last value in list
     * 
     * @return A reference to last element of list.
     */
    // 回傳尾元素
    // 因為沒有尾指標，須走訪找尋尾節點，故花費時間 O(n)。
    public E getLast()
    {
        Node<E> finger = head;
        Assert.condition(finger != null,"List is not empty.");

        while (finger != null &&
               finger.next() != null)
        {
            finger = finger.next();
        }

        return finger.value();
    }

    /**
     * Check to see if a value is in list.
     *
     * @pre value is not null
     * @post returns true iff value is found in list
     * 
     * @param value The value sought.
     * @return True if value is within list.
     */
    // 檢查清單是否包含value元素,回傳布林值
    // 須走訪找尋節點，故花費時間 O(n)。
    public boolean contains(E value)
    {
        Node<E> finger = head;

        while (finger != null &&
               !finger.value().equals(value))
        {
            finger = finger.next();
        }
        // 迴圈結束時，finger指標指向value元素，或指向空

        return finger != null;
    }

    /**
     * Remove a value from list.  At most one value will be removed.
     *
     * @pre value is not null
     * @post removes first element with matching value, if any
     * 
     * @param value The value to be removed.
     * @return The actual value removed.
     */
    // 刪除清單中第一個value元素
    // 須走訪找尋節點，故花費時間 O(n)。
    public E remove(E value)
    {
        Node<E> finger = head;
        Node<E> previous = null;

        while (finger != null &&
               !finger.value().equals(value))
        {
            previous = finger;
            finger = finger.next();
        }

        // finger points to target value
        if (finger != null) {
            // 若finger節點找到value元素
            // 目前順序: previous -> finger -> finger.next
            // 目標順序: previous -> finger.next

            // we found element to remove
            // 1->0例外處理
            //   若previous節點為空，表示清單僅有一個節點，刪除後，無新首節點
            //   則將清單新首節點，設為finger的下一個節點
            if (previous == null) // it is first
            {
                head = finger.next();
            } 
            // 通例處理
            //   若previous指標非空，表示清單有兩個以上節點，刪除後，previous物件為新的首節點
            //   則將previous節點的下一個節點指標，設為finger的下一個節點
            else {              // it's not first
                previous.setNext(finger.next());
            }
            count--;
            return finger.value();
        }

        // didn't find it, return null
        return null;
    }

    /**
     * Determine number of elements in list.    
     *
     * @post returns number of elements in list
     * @return The number of elements in list.
     */
    // 回傳清單長度
    public int size()
    {
        return count;
    }
    
    /**
     * Remove all values from list.
     *
     * @post removes all elements from list
     */
    // 清空清單
    public void clear()
    {
        head = null;
        count = 0;
    }

    /**
     * Get value at location i.
     *
     * @pre 0 <= i < size()
     * @post returns object found at that location
     *
     * @param i position of value to be retrieved.
     * @return value retrieved from location i (returns null if i invalid)
     */
    // 回傳由0起算第i個元素
    // 須走訪找尋節點，故花費時間 O(n)。
    public E get(int i)
    {
        if (i >= size()) return null;
        
        Node<E> finger = head;

        // search for ith element or end of list
        while (i > 0)
        {
            finger = finger.next();
            i--;
        }

        return finger.value();
    }

    /**
     * Set value stored at location i to object o, returning old value.
     *
     * @pre 0 <= i < size()
     * @post sets ith entry of list to value o, returns old value
     * @param i location of entry to be changed.
     * @param o new value
     * @return former value of ith entry of list.
     */
    // 覆蓋由0起算第i個元素為o，回傳原本的值
    // 須走訪找尋節點，故花費時間 O(n)。
    public E set(int i, E o)
    {
        if (i >= size()) return null;
        Node<E> finger = head;
        
        // search for ith element or end of list
        while (i > 0)
        {
            finger = finger.next();
            i--;
        }
        
        // get old value, update new value
        E result = finger.value(); // 備份原本的值
        finger.setValue(o); // 覆蓋新的值
        
        return result;
    }

    /**
     * Insert value at location.
     *
     * @pre 0 <= i <= size()
     * @post adds ith entry of list to value o
     * @param i index of this new value
     * @param o value to be stored
     */
    // 於0起算第i個位置加入value元素
    // 須走訪找尋節點，故花費時間 O(n)。
    public void add(int i, E o)
    {
        Assert.pre((0 <= i)  && (i <= size()),
                   "Index in range.");
        
        if (i == size()) {
            addLast(o); // 特例處理，加尾元素
        } else if (i == 0) {
            addFirst(o); // 特例處理，加首元素
        } else {  
            // 通例處理，於中間位置加元素
            Node<E> previous = null; // previous指標指向finger前一個節點
            Node<E> finger = head; // finger指標從首節點出發，找尋第i個節點
            
            // search for ith position, or end of list
            // 迴圈遞移i次找尋第i個節點finger，及其前一個節點previous
            while (i > 0)
            {
                previous = finger;
                finger = finger.next();
                i--;
            }
            // 迴圈離開時，previous指標指向finger前一個節點，finger指標指向第i個節點
            // 目前順序: previous -> finger -> finger.next
            // 目標順序: previous -> current -> finger

            // create new value to insert in correct position
            // 配置新節點current，其data值為o，其nextElement指標指向finger節點
            // 即新節點成為第i個節點，其下一個節點為finger
            Node<E> current =
                new Node<E>(o,finger);
            
            count++; // 長度加1
            
            // make previous value point to new value
            // previous節點的下一個節點指標指向current新節點
            // 即previous節點的下一個節點為current
            previous.setNext(current);
        }
    }

    /**
     * Remove and return value at location i.
     *
     * @pre 0 <= i < size()
     * @post removes and returns object found at that location
     *
     * @param i position of value to be retrieved.
     * @return value retrieved from location i (returns null if i invalid)
     */
    // 刪除0起算第i個元素，回傳刪除的元素
    // 須走訪找尋節點，故花費時間 O(n)。
    public E remove(int i)
    {
        Assert.pre((0 <= i) && (i < size()),
                   "Index in range.");

        if (i == 0) return removeFirst(); // 特例處理，刪首元素
        else if (i == size()-1) return removeLast(); // 特例處理，刪尾元素

        // 通例處理，於中間位置刪除元素
        Node<E> previous = null; // previous指標指向finger前一個節點
        Node<E> finger = head; // finger指標從首節點出發，找尋第i個節點

        // search for value indexed, keep track of previous
        // 迴圈遞移i次找尋第i個節點finger，及其前一個節點previous
        while (i > 0)
        {
            previous = finger;
            finger = finger.next();
            i--;
        }
        // 迴圈離開時，previous指標指向finger前一個節點，finger指標指向第i個節點
        // 目前順序: previous -> finger -> finger.next
        // 目標順序: previous -> finger.next

        // in list, somewhere in middle
        // previous節點的下一個節點指標指向finger的下一個節點
        // 即previous節點跳過finger，直接接向finger的下一個節點
        previous.setNext(finger.next());
        
        count--; // 長度減1
        
        // finger's value is old value, return it
        return finger.value(); // 回傳刪除的元素
    }

    /**
     * Determine first location of a value in list.
     *
     * @pre value is not null
     * @post returns the (0-origin) index of value,
     *   or -1 if value is not found
     * 
     * @param value value sought
     * @return index (0 is first element) of value, or -1
     */
    // 回傳value元素的第一次出現位置
    // 須走訪找尋節點，故花費時間 O(n)。
    public int indexOf(E value)
    {
        int i = 0;
        Node<E> finger = head;

        // search for value or end of list, counting along way
        while (finger != null && !finger.value().equals(value))
        {
            finger = finger.next();
            i++;
        }

        // finger points to value, i is index
        if (finger == null)
        {   // value not found, return indicator
            return -1;
        } else {
            // value found, return index
            return i;
        }
    }

    /**
     * Determine last location of a value in list.
     *
     * @pre value is not null
     * @post returns the (0-origin) index of value,
     *   or -1 if value is not found
     * 
     * @param value value sought.
     * @return index (0 is first element) of value, or -1
     */
    // 回傳value元素的最後一次出現位置
    // 須走訪找尋節點，故花費時間 O(n)。
    public int lastIndexOf(E value)
    {
        int result = -1;        // assume not found, return -1
        int i = 0;
        Node<E> finger = head;

        // search for last matching value, result is desired index
        while (finger != null)
        {
            // a match? keep track of location
            if (finger.value().equals(value)) result = i;
            finger = finger.next();
            i++;
        }

        // return last match
        return result;
    }

    /**
     * Returns an iterator traversing list from head to tail.
     *
     * @post returns enumeration allowing traversal of list
     * 
     * @return An iterator to traverse list.
     */
    // 回傳清單的迭代器
    public Iterator<E> iterator()
    {
        return new SinglyLinkedListIterator<E>(head);
    }
    /*
    // THIS CODE IS NOT AVAILABLE
    public int size()
    // post: returns number of elements in list
    {
        // number of elements we've seen in list
        int elementCount = 0;

        // reference to potential first element
        Node<E> finger = head;
 
        while (finger != null) {
            // finger references a new element, count it
            elementCount++;
            // reference possible next element
            finger = finger.next();
        }

        return elementCount;
    }
    */

    /**
     * Construct a string representing list.
     *
     * @post returns a string representing list
     * 
     * @return A string representing list.
     */
    // 回傳清單的字串表示
    // 須走訪找尋節點，故花費時間 O(n)。
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<SinglyLinkedList:");

        Enumeration li = (Enumeration)iterator();
        while (li.hasMoreElements())
        {
            s.append(" "+li.nextElement());
        }

        s.append(">");

        return s.toString();
    }
}

