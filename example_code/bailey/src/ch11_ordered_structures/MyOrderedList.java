/*
*  MyOrderedList.java 簡化版有序清單
*    省去比較器，泛型的設計
*
* > java MyOrderedList
<MyOrderedList:1>
<MyOrderedList:3>
contains(1): false
*/

package ch11_ordered_structures;

// 有序清單，鏈結版
public class MyOrderedList
{
    Node head; // smallest value 單向鏈結清單的首節點指標

    int count; // number of values in list 長度

    class Node  // 節點
    {
        int value; // 元素
        Node next; // 下一個節點指標
        
        public String toString()  // 回傳節點內容字串
        {
            return String.format("%d", value);
        }
    }
    
    // 建立空的有序清單
    public MyOrderedList()
    {
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
        head = null;
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
    public void add(int value)
    {
        Node previous = null; // element to adjust, 指向finger前一個節點
        Node finger = head;   // target element, finger從首節點找起，停在加入位置的下一個節點
        
        // search for the correct location
        // 原來: previous -> finger
        // 目標: previous -> value -> finger
        while (true)
        {
            if(finger == null)
            {
                break; // finger 為空表示加入尾節點
            }
            
            if(finger.value - value > 0)
            {
                break; // 
            }
            
            // 指標遞移到下一組節點
            previous = finger;  // previous指向finger節點
            finger = finger.next;  // finger指向下一個節點
        }
        // 跳出條件: finger 為空 或 finger.value >= value
        
        // spot is found, insert
        // 原來: previous -> previous.next
        // 目標: previous -> n -> previous.next
        if (previous == null) // check for insert at top
        {
            // 特例，加入首節點
            Node n = new Node();
            n.value = value;
            n.next = head;
            head = n;
        } else {
            // 通例，加入中間或尾節點
            Node n = new Node();
            n.value = value;
            n.next = previous.next;
            previous.next = n;
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
    public boolean contains(int value)
    {
        Node finger = head; // target 從首節點找起
        // search down list until we fall off or find bigger value
        // 繼續條件: finger 非空  且  finger.value < value
        //while ((finger != null) &&
        //       ordering.compare(finger.value(),value) < 0)
        while(true)
        {
            if(finger==null)
            {
                break;  // 到尾找不到跳出
            }
            
            if(finger.value > value)
            {
                break;  // 在中間確定後面不會有跳出
            }

            if(finger.value == value)
            {
                return true;  // finger指向找到節點跳出
            }
            
            // 指標遞移到下一個節點
            finger = finger.next; // finger指向下一個節點
        }
        // 跳出條件: finger 為空 或 finger.value >= value

        return false;
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
    public int remove(int value)
    {
        Node previous = null; // element to adjust 指向finger前一個節點
        Node finger = head;   // target element finger從首節點找起
        // search for value or fall off list
        // 繼續條件: finger 非空  且  finger.value < value
        // while ((finger != null) &&
        //       ordering.compare(finger.value(),value) < 0)
        while(true)
        {
            if(finger==null)
            {
                break;  // 到尾找不到跳出
            }
            
            if(finger.value > value)
            {
                break;  // 在中間確定後面不會有跳出
            }

            if(finger.value == value)
            {
                break;  // finger指向找到節點跳出
            }

            // 指標遞移到下一組節點
            previous = finger;  // previous指向finger節點
            finger = finger.next; // finger指向下一個節點
        }
        // 跳出條件: finger 為空 或 finger.value >= value

        // did we find it?
        //if ((finger != null) && value.equals(finger.value())) {
        if(finger != null && finger.value == value)
        {
           // yes, remove it
           // 找到時,finger指向欲刪除節點
           // 原來:  previous -> finger -> finger.next
           // 目標:  previous -> finger.next
           if (previous == null)  // at top? 
            {
                // 特例, 首節點指標指向finger.next
                head = finger.next;
            } else {
                // 通例, previous指向finger.next
                previous.next = finger.next;
            }
            count--; // 長度減1
            // return value
            return finger.value; // 回傳刪除值
        }
        // return nonvalue
        return Integer.MIN_VALUE; // 未找到
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
        s.append("<MyOrderedList:");

        // 待補程式碼，以迴圈列印有序清單內容
        s.append(head);
         
        s.append(">");
        return s.toString();
    }
    
    public static void main(String args[])
    {
        MyOrderedList ol = new MyOrderedList();
        ol.add(5);
        ol.add(3);
        ol.add(1);
        System.out.println(ol);
        ol.remove(1);
        System.out.println(ol);
        System.out.println("contains(1): " + ol.contains(1));
    }
}
