// Elements used in implementation of doubly linked lists.
// (c) 1998, 2001 duane a. bailey
/*  
    DoublyLinkedNode.java 節點，供雙向鍵結清單使用
*    內部結構含data元素，previousElement前個節點指標，nextElement下個節點指標
*/
package ch09_lists;

/**
 * A class supporting a doubly linked list element.  Each element
 * contains a value and maintains references to the previous and next 
 * nodes in the list.
 * <P> 
 * 
 * 
 * @version $Id: DoublyLinkedNode.java 31 2007-08-06 17:19:56Z bailey $
 * @author, 2001 duane a. bailey
 * @see structure.DoublyLinkedList
 */
// 雙向鍵結清單節點
public class DoublyLinkedNode<E>
{
    /**
     * The actual value stored within element; provided by user.
     */
    protected E data;  // 元素
    /**
     * The reference of element following.
     */
    protected DoublyLinkedNode<E> nextElement;  // 下個節點指標
    /**
     * The reference to element preceding.
     */
    protected DoublyLinkedNode<E> previousElement; // 前個節點指標

    

    /**
     * Construct a doubly linked list element.
     *
     * @param v The value associated with the element.
     * @param next The reference to the next element.
     * @param previous The reference to the previous element.
     */
    // 節點建構子，data欄位值v，nextElement欄位值next，previousElement欄位值previous
    public DoublyLinkedNode(E v,
                            DoublyLinkedNode<E> next,
                            DoublyLinkedNode<E> previous)
    {
        data = v;

        nextElement = next;
        if (nextElement != null) // 若下個節點非空，則設定下個節點的前個節點為本節點
            nextElement.previousElement = this;

        previousElement = previous;
        if (previousElement != null) // 若前個節點非空，則設定前個節點的下個節點為本節點
            previousElement.nextElement = this;
    }

    /**
     * Construct a doubly linked list element containing a value.
     * Not part of any list (references are null).
     *
     * @post constructs a single element
     * 
     * @param v The value referenced by this element.
     */
    public DoublyLinkedNode(E v)
    {
        this(v,null,null);
    }

    /**
     * Access the reference to the next value.
     *
     * @post returns the element that follows this
     *
     * @return Reference to the next element of the list.
     */
    public DoublyLinkedNode<E> next()
    {
        return nextElement;
    }

    /**
     * Get a reference to the previous element of the list.
     *
     * @post returns element that precedes this
     *
     * @return Reference to the previous element.
     */
    public DoublyLinkedNode<E> previous()
    {
        return previousElement;
    }

    /**
     * Get value stored within the element.
     *
     * @post returns value stored here
     * 
     * @return The reference to the value stored.
     */
    public E value()
    {
        return data;
    }

    /**
     * Set reference to the next element.
     *
     * @post sets value associated with this element
     * 
     * @param next The reference to the new next element.
     */
    public void setNext(DoublyLinkedNode<E> next)
    {
        nextElement = next;
    }

    /**
     * Set the reference to the previous element.
     *
     * @post establishes a new reference to a previous value
     * 
     * @param previous The new previous element.
     */
    public void setPrevious(DoublyLinkedNode<E> previous)
    {
        previousElement = previous;
    }

    /**
     * Set the value of the element.
     *
     * @post sets a new value for this object
     * 
     * @param value The new value associated with the element.
     */
    public void setValue(E value)
    {
        data = value;
    }

    /**
     * Determine if this element equal to another.
     *
     * @post returns true if this object and other are equal
     * 
     * @param other The other doubly linked list element.
     * @return True iff the values within elements are the same.
     */
    public boolean equals(Object other)
    {
        DoublyLinkedNode that = (DoublyLinkedNode)other;
        if (that == null) return false;
        if (that.value() == null || value() == null)
        {
            return value() == that.value();
        } else {
            return value().equals(that.value());
        }
    }

    /**
     * Generate hash code associated with the element.
     *
     * @post generates hash code for element
     * 
     * @return The hash code associated with the value in element.
     */
    public int hashCode()
    {
        if (value() == null) return super.hashCode();
        else return value().hashCode();
    }

    /**
     * Construct a string representation of the element.
     *
     * @post returns string representation of element
     * 
     * @return The string representing element.
     */
    // 回傳節點內容字串
    public String toString()
    {
        return "<DoublyLinkedNode: "+value()+">";
    }
}       

