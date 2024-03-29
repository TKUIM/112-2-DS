// An implementation of extensible arrays.
// (c) 1998, 2001 duane a. bailey
// $Id: Vector.java 31 2007-08-06 17:19:56Z bailey $

package ch03_vectors;
// package structure5;
import structure5.Assert;
import java.util.Iterator;
import java.util.Collection;
import structure5.AbstractList;

/**
 * An implementation of extensible arrays, similar to that of {@link java.util.Vector java.util.Vector}.
 *
 * This vector class implements a basic extensible array.  It does not implement
 * any of the additional features of the Sun class, including list-like operations.
 * Those operations are available in other implementors of {@link List} in this
 * package.
 * <p>
 * Example usage:
 *
 * To put a program's parameters into a Vector, we would use the following:
 * <pre>
 * public static void main(String[] arguments)
 * {
 *    {@link #Vector Vector&lt;String&gt;} argVec = new {@link #Vector Vector&lt;String&gt;()};
 *    for (String arg : arguments)
 *    {
 *       argVec.{@link #add(Object) add(arg)};
 *    }
 *    System.out.println({@link #toString argVec});
 * }
 * </pre>
 *
 * @version $Id: Vector.java 31 2007-08-06 17:19:56Z bailey $
 * @since JavaStructures 1.0
 */
// 向量
public class Vector<E> extends AbstractList<E> implements Cloneable
{
    /**
     * The data associated with the vector.  The size of the
     * array is always at least as large as the vector.  The array
     * is only extended when necessary.
     * Declared private for type safety.
     */
    private Object elementData[];       // the data 放元素的陣列
    /**
     * The actual number of elements logically stored within the
     * vector.  May be smaller than the actual length of the array.
     */
    protected int elementCount;         // number of elements in vector 使用量
    /**
     * The size of size increment, should the vector become full.
     * 0 indicates the vector should be doubled when capacity of
     * the array is reached.
     */
    protected int capacityIncrement;    // the rate of growth for vector 容量擴充增加量
    /**
     * The initial value of any new elements that are appended to the
     * vector.  Normally null.  Be aware that references used in this
     * way will result in multiple references to a single object.
     */
    protected E initialValue;   // new elements have this value 空格初值
    /**
     * The default size of the vector; may be overridden in
     * the {@link #Vector(int)} constructor.
     */
    protected final static int defaultCapacity = 10; // def't capacity, must be>0 初始容量

    /**
     * Construct an empty vector.
     * 
     * @post constructs a vector with capacity for 10 elements
     */
    // 建構空向量，初始容量defaultCapacity
    public Vector()
    {
        this(defaultCapacity); // call one-parameter constructor
    }
    
    /**
     * Construct an empty vector capable of storing <code>initialCapacity</code>
     * values before the vector must be extended.
     *
     * @pre initialCapacity >= 0
     * @post constructs an empty vector with initialCapacity capacity
     * @param initialCapacity The size of vector before reallocation is necessary
     */
    // 建構空向量，初始容量initialCapacity
    public Vector(int initialCapacity)
    {
        Assert.pre(initialCapacity >= 0,"Capacity must not be negative");
        elementData = new Object[initialCapacity];
        elementCount = 0;
        capacityIncrement = 0;
        initialValue = null;
    }

    /**
     * Construct a vector with initial capacity, and growth characteristic.
     *
     * @pre initialCapacity >= 0, capacityIncr >= 0
     * @post constructs an empty vector with initialCapacity capacity
     *    that extends capacity by capacityIncr, or doubles if 0
     * 
     * @param initialCapacity The initial number of slots in vector.
     * @param capacityIncr The size of growth of vector.
     * @see #capacityIncrement
     */
    // 建構空向量，初始容量initialCapacity，容量增量capcityIncr
    public Vector(int initialCapacity, int capacityIncr)
    {
        Assert.pre(initialCapacity >= 0, "Capacity must not be negative.");
        Assert.pre(capacityIncr >= 0, "The capacity increment must be 0,"+
                   " for doubling, or positive for incremental growth.");
        elementData = new Object[initialCapacity];
        elementCount = 0;
        capacityIncrement = capacityIncr;
        initialValue = null;
    }
    /**

     * Construct a vector with initial size, growth rate and default
     * value.
     *
     * @pre initialCapacity, capacityIncr >= 0
     * @post constructs empty vector with capacity that begins at
     *       initialCapacity and extends by capacityIncr or doubles
     *       if 0.  New entries in vector are initialized to initValue.
     * 
     * @param initialCapacity The initial number of slots in vector.
     * @param capacityIncr The size of the increment when vector grows.
     * @param initValue The initial value stored in vector elements.
     */
    // 建構空向量，初始容量initialCapacity，容量增量capcityIncr，初值initValue
    public Vector(int initialCapacity, int capacityIncr, E initValue)
    {
        Assert.pre(initialCapacity >= 0, "Nonnegative capacity.");
        capacityIncrement = capacityIncr;
        elementData = new Object[initialCapacity];
        elementCount = 0;
        initialValue = initValue;
    }
    
    // 匯入that向量，建立向量
    public Vector(Vector<E> that)
    {
        this(that.values());
    }

    // 匯入c收藏，建立向量
    public Vector(Collection<E> c)
    {
        this(c.size());
        Iterator<E> i = c.iterator();
        while (i.hasNext())
        {
            add(i.next());
        }
    }


    /**
     * Ensure that the vector is capable of holding at least
     * minCapacity values without expansion. 
     * 確保容量滿足 minCapacity 值以上，必要時擴展容量
     *
     * @post the capacity of this vector is at least minCapacity
     * 
     * @param minCapacity The minimum size of array before expansion. 擴展前最小容量
     */
    // 確保容量滿足 minCapacity 值以上，必要時擴展容量
    public void ensureCapacity(int minCapacity)
    {
        // 若陣列容量不足，進行容量擴充
        if (elementData.length < minCapacity) {
            // 計算滿足minCapacity的擴充新容量值
            int newLength = elementData.length; // initial guess
            if (capacityIncrement == 0) {
                // increment of 0 suggests doubling (default)
                // 若擴充增量為0，採 2倍數 成長
                if (newLength == 0) newLength = 1;
                while (newLength < minCapacity) {
                    newLength *= 2;
                }
            } else {
                // increment != 0 suggests incremental increase
                // 若擴充增量非0，採累加 擴充增量 成長
                while (newLength < minCapacity)
                {
                    newLength += capacityIncrement;
                }
            }
            
            // assertion: newLength > elementData.length.
            // 配置新容量值陣列
            Object newElementData[] = new Object[newLength];
            
            int i;
            // copy old data to array
            // 拷貝舊元素到新容器
            for (i = 0; i < elementCount; i++) {
                newElementData[i] = elementData[i];
            }
            
            // 置換陣列物件為新容量陣列，舊陣列待自動回收
            elementData = newElementData;
            // garbage collector will (eventually) pick up old elementData
        }
        // assertion: capacity is at least minCapacity
    }

    /**
     * Add an element to the high end of the array, possibly expanding
     * vector.
     *
     * @post adds new element to end of possibly extended vector
     * 
     * @param obj The object to be added to the end of the vector.
     */
    // 新增尾元素obj
    public void add(E obj)
    {
        ensureCapacity(elementCount+1);
        elementData[elementCount] = obj;
        elementCount++;
    }
    
    /**
     * Add an element to the high end of the array, possibly expanding
     * vector.
     *
     * @post adds new element to end of possibly extended vector
     * 
     * @param obj The object to be added to the end of the vector.
     */
    // 新增尾元素o
    public void addElement(E o)
    {
        add(o);
    }
    
    /**
     * Remove an element, by value, from vector.
     *
     * @post element equal to parameter is removed and returned
     * @param element the element to be removed.
     * @return the element actually removed, or if none, null.
     */
    // 刪除第一個element元素
    public E remove(E element)
    {
        E result = null;
        int i = indexOf(element);
        if (i >= 0)
        {
            result = get(i);
            remove(i);
        }
        return result;
    }

    /**
     * Determine the capacity of the vector.  The capacity is always
     * at least as large as its size.
     *
     * @post returns allocated size of the vector
     * 
     * @return The size of the array underlying the vector.
     */
    // 回傳容量
    public int capacity()
    {
        return elementData.length;
    }

    /**
     * Construct a shallow copy of the vector.  The vector
     * store is copied, but the individual elements are shared
     * objects.
     *
     * @post returns a copy of the vector, using same objects
     * 
     * @return A copy of the original vector.
     */
    @SuppressWarnings("unchecked")
    // 拷貝回傳新向量
    public Object clone()
    {
        Vector<E> copy = null;
        try {
            copy = (Vector<E>)super.clone();
            copy.elementData = elementData.clone();
        } catch (java.lang.CloneNotSupportedException e) { Assert.fail("Vector cannot be cloned."); }
        return copy;
    }

    /**
     * Determine if a value appears in a vector.
     *
     * @post returns true iff Vector contains the value
     *       (could be faster, if orderedVector is used)
     * 
     * @param elem The value sought.
     * @return True iff the value appears in the vector.
     */
    // 包含elem元素否
    public boolean contains(E elem)
    {
        int i;
        for (i = 0; i < elementCount; i++) {
            if (elem.equals(elementData[i])) return true;
        }
        return false;
    }

    /**
     * Copy the contents of the vector into an array.
     * The array must be large enough to accept all the values in
     * the vector.
     *
     * @pre dest has at least size() elements
     * @post a copy of the vector is stored in the dest array
     * 
     * @param dest An array of size at least size(). 
     */
    // 拷貝元素到dest陣列，dest陣列假設夠大
    public void copyInto(Object dest[])
    {
        int i;
        for (i = 0; i < elementCount; i++) {
            dest[i] = elementData[i];
        }
    }

    /**
     * Fetch the element at a particular index.
     * The index of the first element is zero.
     *
     * @pre 0 <= index && index < size()
     * @post returns the element stored in location index
     * 
     * @param index The index of the value sought.
     * @return A reference to the value found in the vector.
     */
    //  回傳index位置的元素
    public E elementAt(int index)
    {
        Assert.pre(0 <= index && index < size(),"index is within bounds");
        return get(index);
    }


    /**
     * Fetch the element at a particular index.
     * The index of the first element is zero.
     *
     * @pre 0 <= index && index < size()
     * @post returns the element stored in location index
     * 
     * @param index The index of the value sought.
     * @return A reference to the value found in the vector.
     */
    @SuppressWarnings("unchecked")
    //  回傳index位置的元素
    public E get(int index)
    {
        return (E)elementData[index];
    }

    /**
     * Construct a iterator over the elements of the vector.
     * The iterator considers elements with increasing
     * index.
     *
     * @post returns an iterator allowing one to
     *       view elements of vector
     * @return an iterator to traverse the vector.
     */
    // 回傳向量的迭代器
    public Iterator<E> iterator()
    {
        return new VectorIterator<E>(this);
    }

    /**
     * Get access to the first element of the vector.
     *
     * @pre vector contains an element
     * @post returns first value in vector
     * 
     * @return Access to the first element of the vector.
     */
    // 回傳首元素
    public E firstElement()
    {
        return get(0);
    }

    /**
     * Assuming the data is not in order, find the index of a
     * value, or return -1 if not found.
     *
     * @post returns index of element equal to object, or -1; starts at 0
     * 
     * @param elem The value sought in vector.
     * @return The index of the first occurrence of the value.
     */
    // 回傳第一個elem元素位置
    public int indexOf(E elem)
    {
        return indexOf(elem,0);
    }

    /**
     * Assuming the data is not in order, find the index of a value
     * or return -1 if the value is not found.  Search starts at index.
     *
     * @post returns index of element equal to object, or -1; starts at index
     * 
     * @param elem The value sought.
     * @param index The first location considered.
     * @return The index of the first location, or -1 if not found.
     */
    // 回傳從index位置開始的第一個elem元素位置
    public int indexOf(E elem, int index)
    {
        int i;
        for (i = index; i < elementCount; i++)
        {
            if (elem.equals(elementData[i])) return i;
        }
        return -1;
    }

    /**
     * Insert an element at a particular location.
     * Vector is grown as needed
     *
     * @pre 0 <= index <= size()
     * @post inserts new value in vector with desired index,
     *   moving elements from index to size()-1 to right
     * 
     * @param obj The value to be inserted.
     * @param index The location of the new value.
     *
     */
    // 在index位置加入obj元素
    public void insertElementAt(E obj, int index)
    {
        add(index,obj);
    }

    /**
     * Insert an element at a particular location. 在位置index加入元素obj
     * Vector is grown as needed
     *
     * @pre 0 <= index <= size() 假設參數 index 介於合法範圍。若不合法，不處理，可能當掉
     * @post inserts new value in vector with desired index,
     *   moving elements from index to size()-1 to right
     * 
     * @param obj the value to be inserted. 元素
     * @param index the location of the new value. 加入位置
     */
    // 在index位置加入obj元素
    public void add(int index, E obj)
    {
        int i;
        
        ensureCapacity(elementCount+1);  // 確保容量夠加1格，必要時可容量擴充
        
        // must copy from right to left to avoid destroying data
        // 資料往後推移一格:
        //   從長度位置開始，加入位置後一格結束，由後往前，將資料推移拷貝到後面一格
        for (i = elementCount; i > index; i--) {
            elementData[i] = elementData[i-1];
        }

        // assertion: i == index and element[index] is available
        elementData[index] = obj;  // 騰出空格加入obj元素
        elementCount++;  // 使用量加1
    }
    
    /* A recursive version 遞迴版 of insertion of element at
    public void add(int index, E value)
    // pre: 0 <= index <= size()
    // post: inserts new value in vector with desired index
    //   moving elements from index to size()-1 to right
    {
        if (index >= size()) {
            add(value); // base case: add at end
        } else {
            E previous = get(index); // work
            add(index+1,previous);  // progress through recursion
            set(index,value);  // work
        }
    }
    */

    /**
     * Determine if the Vector contains no values.      
     *
     * @post returns true iff there are no elements in the vector
     * 
     * @return True iff the vector is empty.
     */
    // 回傳向量空否
    public boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     * Fetch a reference to the last value in the vector.
     *
     * @pre vector is not empty
     * @post returns last element of the vector
     * 
     * @return A reference to the last value.
     */
    // 回傳尾元素
    public E lastElement()
    {
        return get(elementCount-1);
    }

    /**
     * Search for the last occurrence of a value within the
     * vector.  If none is found, return -1.
     *
     * @post returns index of last occurrence of object in the vector, or -1
     * 
     * @param obj The value sought.
     * @return The index of the last occurrence in the vector.
     */
    // // 回傳由尾往前第一個elem元素位置
    public int lastIndexOf(E obj)
    {
        return lastIndexOf(obj,elementCount-1);
    }

    /**
     * Find the index of the last occurrence of the value in the vector before
     * the indexth position.
     *
     * @pre index >= 0
     * @post returns the index of last occurrence of object at or before
     *       index
     * 
     * @param obj The value sought.
     * @param index The last acceptable index.
     * @return The index of the last occurrence of the value, or -1 if none.
     */
    // 回傳從index位置開始，由尾往前，第一個elem元素位置
    public int lastIndexOf(E obj, int index)
    {
        int i;
        for (i = index; i >= 0; i--) {
            if (obj.equals(elementData[i])) return i;
        }
        return -1;
    }

    /**
     * Remove all the values of the vector.
     *
     * @post vector is empty
     */
    // 刪除所有元素
    public void clear()
    {
        setSize(0);
    }

    /**
     * Remove all the elements of the vector.
     * Kept for compatibility with java.util.Vector.
     *
     * @post vector is empty
     *
     * @see #clear
     */
    // 刪除所有元素
    public void removeAllElements()
    {
        setSize(0);
    }

    /*
     * Remove an element, by value, from vector.
     *
     * @post element equal to parameter is removed
     * 
     * @param element The element to be removed.
     * @return The element actually removed, or if none, null.

    public boolean removeElement(E element)
    {
        int where = indexOf(element);
        if (where == -1) return false;
        remove(where);
        return true;
    }
*/

    /**
     * Remove an element at a particular location.
     *
     * @pre 0 <= where && where < size()
     * @post indicated element is removed, size decreases by 1
     * 
     * @param where The location of the element to be removed.
     */
    // 刪除向量where位置的值，後面值往前遞補，最後位置填null
    public void removeElementAt(int where)
    {
        remove(where);
    }

    /**
     * Remove an element at a particular location.
     *
     * @pre 0 <= where && where < size()
     * @post indicated element is removed, size decreases by 1
     * 
     * @param where The location of the element to be removed.
     */
    // 刪除向量where位置的值，後面值往前遞補，最後位置填null，回傳刪除值
    public E remove(int where)
    {
        E result = get(where);
        elementCount--;
        while (where < elementCount) {
            elementData[where] = elementData[where+1];
            where++;
        }
        elementData[elementCount] = null; // free reference
        return result;
    }

    /**
     * Change the value stored at location index.
     *
     * @pre 0 <= index && index < size()
     * @post element value is changed to obj
     * 
     * @param obj The new value to be stored.
     * @param index The index of the new value.
     */
    // 覆蓋向量index位置的值為obj
    public void setElementAt(E obj, int index)
    {
        set(index,obj);
    }

    /**
     * Change the value stored at location index.
     *
     * @pre 0 <= index && index < size()
     * @post element value is changed to obj; old value is returned
     * 
     * @param obj The new value to be stored.
     * @param index The index of the new value.
     */
    @SuppressWarnings("unchecked")
    // 覆蓋向量index位置的值為obj，回傳舊值
    public E set(int index, E obj)
    {
        Assert.pre(0 <= index && index < elementCount,"index is within bounds");
        E previous = (E)elementData[index];
        elementData[index] = obj;
        return previous;
    }

    /**
     * Explicitly set the size of the array.
     * Any new elements are initialized to the default value.
     *
     * @pre newSize >= 0
     * @post vector is resized, any new elements are initialized
     * 
     * @param newSize The ultimate size of the vector.
     */
    // 設定向量新長度為newSize
    //   若新長度 < 使用量, 則新長度之後設為空值
    //   若新長度 >= 使用量, 則使用量之後設為initialValue
    public void setSize(int newSize)
    {
        int i;
        if (newSize < elementCount) {
            for (i = newSize; i < elementCount; i++) elementData[i] = null;
        } else {
            ensureCapacity(newSize);
            for (i = elementCount; i < newSize; i++)
                elementData[i] = initialValue;
        }
        elementCount = newSize;
    }

    /**
     * Determine the number of elements in the vector.
     *
     * @post returns the size of the vector
     * 
     * @return The number of elements within the vector.
     */
    // 回傳長度
    public int size()
    {
        return elementCount;
    }

    /**
     * Trim the vector to exactly the correct size.
     *
     * @post minimizes allocated size of vector
     */
    // 容量縮為使用量
    public void trimToSize()
    {
        Object newElementData[] = new Object[elementCount];
        copyInto(newElementData);
        elementData = newElementData;
    }

    /**
     * Determine a string representation for the vector.
     *
     * @post returns a string representation of vector
     * 
     * @return A string representation for the vector.
     */
    // 回傳向量內容字串
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        int i;

        sb.append("<Vector:");
        for (i = 0; i < size(); i++)
        {
            sb.append(" "+get(i));
        }
        sb.append(">");
        return sb.toString();
    }
    /*
    public void print()
    // post: print the elements of the vector
    {
        printFrom(0);
    }
    
    protected void printFrom(int index)
    // pre: index <= size()
    // post: print elements indexed between index and size()
    {
        if (index < size()) {
            System.out.println(get(index));
            printFrom(index+1);
        }
    }
    */
    
    /* 測試程式，用Vector儲存及列印命令列參數
    > java Vector
    argVec: <Vector:>
    */
    public static void main(String[] arguments)
    {
       Vector<String> argVec = new Vector<>();
     
       for (String arg : arguments)
       {
          argVec.add(arg);
       }
       
       System.out.printf("argVec: %s",argVec);
    }
}

