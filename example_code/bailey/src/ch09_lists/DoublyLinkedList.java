// Implementation of lists, using doubly linked elements.
// (c) 1998, 2001 duane a. bailey
/*
   DoublyLinkedList.java 雙向鏈結清單
     內含長度count，首節點指標head，尾節點指標tail

*/

package ch09_lists;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;

import structure5.Assert;
/**
 * An implementation of lists using doubly linked elements, similar to that of {@link java.util.LinkedList java.util.LinkedList}.
 * <p>       
 * This class is a basic implementation of the {@link List} interface.
 * Operations accessing or modifying either the head or the tail of 
 * the list execute in constant time.
 * Doubly linked lists are less space-efficient than singly linked lists, 
 * but tail-related operations are less costly.
 * <p>
 * Example usage:
 *
 * To place a copy of every unique parameter passed to a program into a 
 * DoublyLinkedList,  we would use the following:
 * <pre>
 * public static void main({@link java.lang.String String[]} arguments)
 * {
 *     {@link DoublyLinkedList} argList = new {@link #DoublyLinkedList()};
 *     for (int i = 0; i < arguments.length; i++){
 *         if (!argList.{@link #contains(Object) contains(arguments[i])}){
 *             argList.{@link #add(Object) add(arguments[i])};
 *         }
 *    }
 *    System.out.println(argList);
 * }
 * </pre>
 * @version $Id: DoublyLinkedList.java 31 2007-08-06 17:19:56Z bailey $
 * @author, 2001 duane a. bailey
 * @see SinglyLinkedList
 * @see CircularList
 */
// 雙向鍵結清單
public class DoublyLinkedList<E> extends AbstractList<E>
{
    /**
     * Number of elements within list.
     */
    protected int count; // 長度
    /**
     * Reference to head of list.
     */
    protected DoublyLinkedNode<E> head;  // 首節點指標
    /**
     * Reference to tail of list.
     */
    protected DoublyLinkedNode<E> tail;  // 尾節點指標

    /**
     * Constructs an empty list.
     *
     * @post constructs an empty list
     * 
     */
    // 建構空清單
    public DoublyLinkedList()
    {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Add a value to head of list.
     *
     * @post adds value to beginning of list
     * 
     * @param value value to be added.
     */
    // 於清單頭位置加入value資料
    public void add(E value)
    {
        addFirst(value);
    }
    
    /**
     * Add a value to head of list.
     *
     * @pre value is not null
     * @post adds element to head of list
     * 
     * @param value value to be added.
     */
    // 於清單頭位置加入value資料
    public void addFirst(E value)
    {
        // construct a new element, making it head
        // 首節點指標指向新節點
        // 新節點的next欄位指向原首節點
        head = new DoublyLinkedNode<E>(value, head, null);

        // fix tail, if necessary
        // 0->1例外處理，
        // 若尾節點指標為空，則尾節點指標指向首節點
        if (tail == null) tail = head;
        
        count++; // 長度加1
    }

    /**
     * Remove a value from head of list.
     * Value is returned.
     *
     * @pre list is not empty
     * @post removes first value from list
     * 
     * @return value removed from list.
     */
    // 刪除清單首元素，回傳刪除的首元素值
    public E removeFirst()
    {
        // 防呆檢查，若清單為空，則拋出例外
        Assert.pre(!isEmpty(),"List is not empty.");

        DoublyLinkedNode<E> temp = head;  // 備份首元素指標

        // 新首節點為舊首節點的下一個節點
        head = head.next();
        
        // 通例處理
        //   若新首節點不為空，則新首節點的前一節點為空
        if (head != null) {
            head.setPrevious(null);
        } 
        // 1->0例外處理
        //   若新首節點為空，則尾節點指標指向空
        else {
            tail = null; // remove final value
        }
        
        // 設定舊首節點的下一節點為空，方便未來垃圾回收之用
        temp.setNext(null);// helps clean things up; temp is free
        
        count--; // 長度減1
        
        return temp.value(); // 回傳刪除的首元素值
    }

    /**
     * Add a value to tail of list.
     *
     * @pre value is not null
     * @post adds new value to tail of list
     * 
     * @param value value to be added.
     */
    // 於清單尾位置加入value資料
    public void addLast(E value)
    {
        // construct new element
        // 新節點沒有下個節點
        // 新節點的上個節點為原來尾節點
        // 新節點成為新尾節點
        tail = new DoublyLinkedNode<E>(value, null, tail);

        // fix up head
        // 0->1例外處理
        //  若原首節點為空，則新首節點等同於尾節點
        if (head == null) head = tail;

        count++; // 長度加1
    }

    /**
     * Remove a value from tail of list.
     *
     * @pre list is not empty
     * @post removes value from tail of list
     * 
     * @return value removed from list.
     */
    // 刪除清單尾元素，回傳刪除的尾元素值
    public E removeLast()
    {
        Assert.pre(!isEmpty(),"List is not empty.");

        DoublyLinkedNode<E> temp = tail; // 備份尾元素指標

        // 新尾節點為舊尾節點的前一個節點
        tail = tail.previous();
        
        // 1->0例外處理
        //   若新尾節點為空，則首節點也為空
        if (tail == null) {
            head = null;
        } 
        // 通例處理
        //   若新尾節點不為空，則新尾節點的下一節點為空
        else {
            tail.setNext(null);
        }
        
        count--; // 長度減1
        
        return temp.value(); // 回傳刪除的尾元素值
    }
    /*
    public void addLast(E value)
    {
        // construct new element
        tail = new DoublyLinkedNode<E>(value, null, tail);
        count++;
    }

    public E removeLast()
    {
        Assert.pre(!isEmpty(),"List is not empty.");
        DoublyLinkedNode<E> temp = tail;
        tail = tail.previous();
        tail.setNext(null);
        count--;
        return temp.value();
    }
    */

    /**
     * Get a copy of first value found in list.
     *
     * @pre list is not empty
     * @post returns first value in list
     * 
     * @return A reference to first value in list.
     */
    // 回傳清單第一個元素值
    public E getFirst()
    {
        return head.value();
    }

    /**
     * Get a copy of last value found in list.
     *
     * @pre list is not empty
     * @post returns last value in list
     * 
     * @return A reference to last value in list.
     */
    // 回傳清單最後一個元素值
    public E getLast()
    {
        return tail.value();
    }

    /**
     * Check to see if a value is within list.
     *
     * @pre value not null
     * @post returns true iff value is in list
     * 
     * @param value A value to be found in list.
     * @return True if value is in list.
     */
    // 回傳清單是否包含value值
    // 須走訪找尋節點，故花費時間 O(n)。
    public boolean contains(E value)
    {
        DoublyLinkedNode<E> finger = head;

        while ((finger != null) && (!finger.value().equals(value)))
        {
            finger = finger.next();
        }
        
        return finger != null; // 回傳是否找到value值
    }

    /**
     * Remove a value from list.  At most one value is removed.
     * Any duplicates remain.  Because comparison is done with "equals,"
     * actual value removed is returned for inspection.
     *
     * @pre value is not null.  List can be empty
     * @post first element matching value is removed from list
     * 
     * @param value value to be removed.
     * @return value actually removed.
     */
    // 刪除清單中第一個值為value的節點
    // 須走訪找尋節點，故花費時間 O(n)。
    public E remove(E value)
    {
        DoublyLinkedNode<E> finger = head;
        
        while (finger != null &&
               !finger.value().equals(value))
        {
            finger = finger.next();
        }
        // 迴圈離開時，finger指向value值所在節點 或 finger為空

        if (finger != null)
        {
            // fix next field of element above
            // 三節點順序: finger.previous <-> finger <-> finger.next
            // 通例處理: 若finger.previous節點非空
            //    讓 finger.previous 下一個節點為 finger.next
            if (finger.previous() != null)
            {
                finger.previous().setNext(finger.next());
            } 
            // 特例處理: 若finger.previous為空，則刪除的finger為首節點
            //    讓首節點指標，跳過finger，指向finger下個節點
            else {
                head = finger.next();
            }
            
            // fix previous field of element below
            // 三節點順序: finger.previous <-> finger <-> finger.next
            // 通例處理: 若finger.next節點非空
            //    讓 finger.next 前一個節點為 finger.previous
            if (finger.next() != null)
            {
                finger.next().setPrevious(finger.previous());
            }
            // 特例處理: 若finger.next為空，則刪除的finger為尾節點
            //  讓尾節點指標，跳過finger，指向finger前一個節點
            else {
                tail = finger.previous();
            }
        
            count--;            // fewer elements 長度減1
        
            return finger.value(); // 回傳刪除的值
        }
        
        return null; // 回傳空，表示沒有刪除任何值
    }

    /**
     * Determine number of elements in list.
     *
     * @post returns number of elements in list
     * 
     * @return number of elements found in list.
     */
    // 回傳清單長度
    public int size()
    {
        return count;
    }

    /**
     * Determine if list is empty.
     *
     * @post returns true iff list has no elements
     * 
     * @return True iff list has no values.
     */
    // 回傳清單是否為空
    public boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     * Remove all values from list.
     *
     * @post removes all elements from list
     */
    // 清空清單
    public void clear()
    {
        head = tail = null;
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
    // 回傳清單由0起算的第i個元素值
    // 須走訪找尋節點，故花費時間 O(n)。
    public E get(int i)
    {
        if (i >= size()) return null;

        DoublyLinkedNode<E> finger = head;

        // search for ith element or end of list
        while (i > 0)
        {
            finger = finger.next();
            i--;
        }

        // not end of list, return value found
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
        DoublyLinkedNode<E> finger = head;

        // search for ith element or end of list
        while (i > 0)
        {
            finger = finger.next();
            i--;
        }
        
        // get old value, update new value
        E result = finger.value(); // 備份原本的值   
        finger.setValue(o); // 覆蓋新值
        
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
    // 於清單第i個位置加入o值
    // 須走訪找尋節點，故花費時間 O(n)。
    public void add(int i, E o)
    {
        Assert.pre((0 <= i) &&
                   (i <= size()), "Index in range.");

        if (i == 0) addFirst(o); // 特例處理，加入首元素
        else if (i == size()) addLast(o); // 特例處理，加入尾元素
        else {
            // 通例處理
            // 由首節點開始，找尋第i個節點
            DoublyLinkedNode<E> before = null; // before為after前一個節點，由空開始
            DoublyLinkedNode<E> after = head; // after從首節點出發，找尋第i個節點的下一個節點

            // search for ith position, or end of list
            // 迴圈跑i次，由首節點開始，找尋第i個節點
            while (i > 0)
            {
                before = after; // 先備份after指標在before指標
                after = after.next(); // 再覆蓋after指標，指向after節點的下個節點
                i--;
            }
            // 迴圈離開時，before指向第i-1個節點，after指向第i個節點
            
            // create new value to insert in correct position
            // 原來順序: before <-> after
            // 目標順序: before <-> current <-> after
            // 新節點的前一個節點為before，後一個節點為after
            DoublyLinkedNode<E> current =
                new DoublyLinkedNode<E>(o,after,before);
            
            count++; // 長度加1
            
            // make after and before value point to new value
            // before節點的下個節點為新節點
            before.setNext(current);
            // after節點的前一個節點為新節點
            after.setPrevious(current);
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
    // 刪除清單第i個元素，回傳刪除的元素值
    // 須走訪找尋節點，故花費時間 O(n)。
    public E remove(int i)
    {
        Assert.pre((0 <= i) &&
                   (i < size()), "Index in range.");

        if (i == 0) return removeFirst(); // 特例處理，刪除首元素
        else if (i == size()-1) return removeLast(); // 特例處理，刪除尾元素

        // 通例處理
        DoublyLinkedNode<E> previous = null; // previous為finger前一個節點，由空開始
        DoublyLinkedNode<E> finger = head; // finger從首節點出發，找尋第i個節點

        // search for value indexed, keep track of previous
        // 迴圈跑i次，由首節點開始，找尋第i個節點
        while (i > 0)
        {
            previous = finger; // 備份finger指標
            finger = finger.next(); // 
            i--;
        }
        // 迴圈離開時，previous指向第i-1個節點，finger指向第i個節點
        
        // 原來順序: previous <-> finger <-> finger.next
        // 目標順序: previous <-> finger.next
        // previous節點的下個節點為，跳過finger節點，接到其下個節點
        previous.setNext(finger.next());

        // finger節點的下個節點，其前一個節點，跳過finger節點，接到previous節點
        finger.next().setPrevious(previous);
        
        count--; // 長度減1
        
        // finger's value is old value, return it
        return finger.value(); // 回傳刪除的元素值
    }

    /**
     * Determine first location of a value in list.
     *
     * @pre value is not null
     * @post returns the (0-origin) index of value,
     *   or -1 if value is not found
     * 
     * @param value value sought.
     * @return index (0 is first element) of value, or -1
     */
    // 回傳清單第一個值為value的節點位置
    // 須走訪找尋節點，故花費時間 O(n)。
    public int indexOf(E value)
    {
        int i = 0;
        DoublyLinkedNode<E> finger = head;
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
    // 回傳清單最後一個值為value的節點位置
    // 須走訪找尋節點，故花費時間 O(n)。
    public int lastIndexOf(E value)
    {
        int i = size()-1;
        DoublyLinkedNode<E> finger = tail;

        // search for last matching value, result is desired index
        while (finger != null && !finger.value().equals(value))
        {
            finger = finger.previous();
            i--;
        }
        
        if (finger == null)
        {   // value not found, return indicator
            return -1;
        } else {
            // value found, return index
            return i;
        }
    }

    /**
     * Construct an iterator to traverse list.
     *
     * @post returns iterator that allows traversal of list
     * 
     * @return An iterator that traverses list from head to tail.
     */
    // 回傳清單的迭代器
    public Iterator<E> iterator()
    {
        return new DoublyLinkedListIterator<E>(head);
    }

    /**
     * Construct a string representation of list.
     *
     * @post returns a string representing list
     * 
     * @return A string representing elements of list.
     */
    // 回傳清單的字串表示
    // 須走訪找尋節點，故花費時間 O(n)。
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<DoublyLinkedList:");

        Iterator li = iterator();
        while (li.hasNext())
        {
            s.append(" "+li.next());
        }

        s.append(">");

        return s.toString();
    }
}


