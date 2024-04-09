// An implementation of Dictionaries, using hash tables. 
// Keys need not be comparable, but they must have hashcode methods.
// (c) 1998, 2001 duane a. bailey

package ch15_maps;
import java.util.Iterator;
import java.lang.Iterable;
import java.lang.Math;
import structure5.AbstractIterator;
import structure5.Assert;
import structure5.Association;
import structure5.List;
import structure5.Set;
import structure5.SetList;
import structure5.SinglyLinkedList;
import structure5.Structure;
import structure5.Vector;
/**
 * Implements a dictionary as a table of hashed key-value pairs.
 * Collisions are resolved through linear probing.  Values used
 * as keys in this structure must have a hashcode method that returns
 * the same value when two keys are "equals".  Initially, a table of suggested
 * size is allocated.  It will be expanded as the load factor (ratio of
 * pairs to entries) grows to meet maximumLoadFactor.
 * <P>
 * Example Usage:
 * <P>
 * To create a hashtable by reading a collection of words and 
 * definitions from System.in we could use the following:
 * <P> 
 * <pre>
 * public static void main (String[] argv){
 *      Hashtable dict = new {@link #Hashtable()};
 *      ReadStream r = new ReadStream();
 *      String word, def;
 *      System.out.println("Enter a word: ");
 *      while(!r.eof()){
 *          word = r.readLine();
 *          System.out.println("Enter a definition: ");
 *          def = r.readLine();
 *          dict.{@link #put(Object,Object) put(word,def)};
 *          System.out.println("Enter a word: ");
 *      }
 *      System.out.println(dict);
 * }
 * </pre>
 * @version $Id: Hashtable.java 34 2007-08-09 14:43:44Z bailey $
 * @author, 2001 duane a. bailey
 * @see ChainedHashtable
 */
// 雜湊表
public class Hashtable<K,V> implements Map<K,V>, Iterable<V>
{
    /**
     * A single key-value pair to be used as a token
     * indicating a reserved location in the hashtable.
     * Reserved locations are available for insertion,
     * but cause collisions on lookup.
     */
    protected static final String RESERVED = "RESERVED";  // 保留格專用字串，程式未使用
    /**
     * The data associated with the hashtable.
     */
    protected Vector<HashAssociation<K,V>> data; // 存放雜湊配對元素(K,V)的向量
    /**
     * The number of key-value pairs in table.
     */
    protected int count;  // 筆數

    /**
     * The maximum load factor that causes rehashing of the table.
     */
    protected final double maximumLoadFactor = 0.6;

    /**
     * Construct a hash table that is capable of holding at least
     * initialCapacity values.  If that value is approached, it will
     * be expanded appropriately.  It is probably best if the capacity
     * is prime.  Table is initially empty.
     *
     * @pre initialCapacity > 0
     * @post constructs a new Hashtable
     *       holding initialCapacity elements
     * 
     * @param initialCapacity The initial capacity of the hash table.
     */
    //  建立容量initialCapacity的空雜湊表
    public Hashtable(int initialCapacity)
    {
        Assert.pre(initialCapacity > 0, "Hashtable capacity must be positive.");
        data = new Vector<HashAssociation<K,V>>();
        data.setSize(initialCapacity);
        count = 0;
    }

    /**
     * Construct a hash table that is initially empty.
     *
     * @post constructs a new Hashtable
     */
    // 建立容量997的空雜湊表
    public Hashtable()
    {
        this(997);
    }

    /**
     * Remove all key-value pairs from hashtable.
     *
     * @post removes all elements from Hashtable
     */
    // 清空
    public void clear()
    {
        int i;
        for (i = 0; i < data.size(); i++) {
            data.set(i,null); // 每格填null，還原成空白格
        }
        count = 0;
    }

    /**
     * Return the number of key-value pairs within the table.
     *
     * @post returns number of elements in hash table
     * 
     * @return The number of key-value pairs currently in table.
     */
    // 回傳筆數
    public int size()
    {
        return count;
    }

    /**
     * Determine if table is empty.
     *
     * @post returns true iff hash table has 0 elements
     * 
     * @return True if table is empty.
     */
    // 回傳空否
    public boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     * Returns true if a specific value appears within the table.
     *
     * @pre value is non-null Object
     * @post returns true iff hash table contains value
     * 
     * @param value The value sought.
     * @return True iff the value appears within the table.
     */
    // 包含value值否
    public boolean containsValue(V value)
    {
        for (V tableValue : this) {  // 呼叫iterator()，進行值的迭代
            if (tableValue.equals(value)) return true;
        }
        // no value found
        return false;
    }

    /**
     * Returns true iff a specific key appears within the table.
     *
     * @pre key is a non-null Object
     * @post returns true if key appears in hash table
     * 
     * @param key The key sought.
     * @return True iff the key sought appears within table.
     */
    // 包含key鍵否
    public boolean containsKey(K key)
    {
        int hash = locate(key); // 回傳可能存放key鍵格子的下標
        
        // 若下標所在格非空白格，且非保留格
        // 表示找到，回傳真；否則回傳假
        return data.get(hash) != null && !data.get(hash).reserved();
    }   

    /**
     * Returns a traversal that traverses over the values of the
     * hashtable.
     *
     * @post returns traversal to traverse hash table
     * 
     * @return A value traversal, over the values of the table.
     */
    // 回傳值迭代器
    public Iterator<V> iterator()
    {
        return new ValueIterator<K,V>((AbstractIterator<Association<K,V>>)new HashtableIterator<K,V>(data));
    }

    /**
     * Get the value associated with a key.
     *
     * @pre key is non-null Object
     * @post returns value associated with key, or null
     * 
     * @param key The key used to find the desired value.
     * @return The value associated with the desired key.
     */
    // 回傳key鍵對應的值
    public V get(K key)
    {
        int hash = locate(key);  // 回傳可能存放key鍵格子的下標
        
        // 若下標所在格為空白格或保留格，表示找不到，回傳null
        if (data.get(hash) == null ||
            data.get(hash).reserved()) return null;
        
        // 否則，表示找到，回傳下標所在格的配對值
        return data.get(hash).getValue();
    }

    /**
     * Get a traversal over the keys of the hashtable.
     *
     * @post returns traversal to traverse the keys of hash table;
     * 
     * @return a traversal over the key values appearing within table.
     */
    // 回傳鍵迭代器
    public Iterator<K> keys()
    {
        return new KeyIterator<K,V>(new HashtableIterator<K,V>(data));
    }

    // 若找到key鍵所在格，回傳所在格的下標位置
    // 若找不到key鍵所在格，回傳其應放保留格或空白格的下標位置
    protected int locate(K key)
    {
        // compute an initial hash code
        // 從key鍵取得雜湊值，當成初始下標位置
        int hash = Math.abs(key.hashCode() % data.size());
        
        // keep track of first unused slot, in case we need it
        // 進入迴圈，找尋key鍵所在格(若找得到)，或應放置的保留格或空白格(若找不到)
        int reservedSlot = -1;
        boolean foundReserved = false;  // 假設從未遇過保留格
        while (data.get(hash) != null)
        {
            if (data.get(hash).reserved()) {
                // remember reserved slot if we fail to locate value
                if (!foundReserved) {
                    // 如果目前下標格為第1次遇到的保留格，則記錄目前下標位置
                    reservedSlot = hash;  // 記錄目前下標位置
                    foundReserved = true;  // 設定遇過保留格
                }
            } else  {
                // value located? return the index in table
                // 如果目前下標格非空白格，也非保留格，且儲存的鍵符合key，則表示找到，回傳下標值
                if (key.equals(data.get(hash).getKey())) return hash;
            }
            
            // 如果目前下標格非空白格，也非保留格，但儲存的鍵不符合key，表示發生踫撞
            // 則採用線性找空位法，往下一格找空白格
            // linear probing; other methods would change this line:
            hash = (1+hash)%data.size();
        }
        // 遇到目前下標格為空白格，迴圈跳出

        // return first empty slot we encountered
        // 如果從未遇過保留格，則回傳目前空白格下標位置
        if (!foundReserved) return hash;
        // 如果曾遇過保留格，則回傳第1次遇到的保留格下標位置
        else return reservedSlot;
    }

    /**
     * Place a key-value pair within the table.
     *
     * @pre key is non-null object
     * @post key-value pair is added to hash table
     * 
     * @param key The key to be added to table.
     * @param value The value associated with key.
     * @return The old value associated with key if previously present.
     */
    // 存放鍵值配對(key,value)
    // 若新增，回傳null
    // 若覆蓋，回傳舊值
    public V put(K key, V value)
    {
        if (maximumLoadFactor*data.size() <= (1+count)) {
            extend();  // 若用量超過容量60%負載比，則雜湊表容量進行倍增擴展
        }
        int hash = locate(key); // 回傳可能存放key鍵格子的下標
        
        // 如果目前下標格為空白格，或保留格
        // 則於該位置放入鍵值(key,value)配對
        if (data.get(hash) == null || data.get(hash).reserved())
        {   // logically empty slot; just add association
            data.set(hash,new HashAssociation<K,V>(key,value));
            count++;
            return null; // 若新增，回傳null
        } else {
            // 否則，
            // full slot; add new and return old value
            HashAssociation<K,V> a = data.get(hash);
            V oldValue = a.getValue();
            a.setValue(value);
            return oldValue;  // 若覆蓋，回傳舊值
        }
    }
    /**
     * Put all of the values found in another map into this map,
     * overriding previous key-value associations.
     * @param other is the source mapping
     * @pre other map is valid
     * @post this hashtable is augmented by the values found in other
     */
    // 匯入other雜湊映射
    public void putAll(Map<K,V> other)
    {
        // 列舉other映射的項目集合，逐一加入雜湊表
        for (Association<K,V> e : other.entrySet())
        {
            put(e.getKey(),e.getValue());
        }
    }


    /**
     * Remove a key-value pair from the table.
     *
     * @pre key is non-null object
     * @post removes key-value pair associated with key
     * 
     * @param key The key of the key-value pair to be removed.
     * @return The value associated with the removed key.
     */
    // 移除key鍵值配對
    // 若移除成功，回傳舊值
    // 若移除失敗,回傳空值
    public V remove(K key)
    {
        int hash = locate(key); // 回傳可能存放key鍵格子的下標
        
        // 如果目前下標格為空白格，或保留格
        // 表示找不到，回傳空值
        if (data.get(hash) == null || data.get(hash).reserved()) {
            return null;
        }
        count--;
        V oldValue = data.get(hash).getValue(); // 備份舊值
        data.get(hash).reserve(); // in case anyone depends on us 設定該格為保留格
        return oldValue; // 回傳舊值
    }

    /**
     * @post expands the hashtable to reduce loading
     */
    // 2倍擴展雜湊表
    protected void extend()
    {
        // extends the hashtable for larger capacity.
        int i;
        AbstractIterator<Association<K,V>> it = new HashtableIterator<K,V>(data);
        // BE AWARE: at this point, we can change the hash table,
        // but changes to the hashtable traversal implementation might
        // be problematic.
        int newSize = 2*data.size();  // 2倍容量
        Assert.condition(newSize > 0, "Hashtable vector size must be greater than 0.");
        data = new Vector<HashAssociation<K,V>>();
        data.setSize(newSize);
        count = 0;
        while (it.hasNext())  // 拷貝迴圈
        {
            Association<K,V> a = it.next(); // 迭代取出下個配對
            put(a.getKey(),a.getValue()); // 加入下個配對
        }
    }

    /**
     * @post returns a set of Associations associated with this Map
     */
    // 回傳所有鍵值配對的條目集合
    public Set<Association<K,V>> entrySet()
    {
        Set<Association<K,V>> result = new SetList<Association<K,V>>(); // 建立空的集合清單
        Iterator<Association<K,V>> i = new HashtableIterator<K,V>(data);
        
        // 拷貝迴圈
        while (i.hasNext())
        {
            result.add(i.next()); // 迭代取出下個配對，加入集合
        }
        return result;
    }

    /**
     * @post returns a Set of keys used in this Map
     */
    // 回傳存放所有鍵的集合
    public Set<K> keySet()
    {
        Set<K> result = new SetList<K>();  // 建立空的集合清單
        Iterator<K> i = new KeyIterator<K,V>(new HashtableIterator<K,V>(data));
        
        // 拷貝迴圈
        while (i.hasNext())
        {
            result.add(i.next()); // 逐一列舉鍵，加入集合清單
        }
        return result;
    }

    /**
     * @post returns a Structure that contains the (possibly repeating) 
     * values of the range of this map.
     */
    // 回傳存放所有值的結構
    public Structure<V> values()
    {
        List<V> result = new SinglyLinkedList<V>(); // 建立空的單鏈結清單
        Iterator<V> i = new ValueIterator<K,V>(new HashtableIterator<K,V>(data));
        
        // 拷貝迴圈
        while (i.hasNext())
        {
            result.add(i.next()) ;// 逐一列舉值，加入集合清單
        }
        return result;
    }


    /**
     * Generate a string representation of the hash table.
     *
     * @post returns a string representation of hash table
     * 
     * @return The string representing the table.
     */
    // 回傳雜湊表內容字串
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        int i;

        s.append("<Hashtable: size="+size()+" capacity="+data.size());
        Iterator<Association<K,V>> hi = new HashtableIterator<K,V>(data);
        
        // 列舉迴圈
        while (hi.hasNext()) {
            Association<K,V> a = hi.next();
            s.append(" key="+a.getKey()+", value="+a.getValue());
        }
        s.append(">");
        return s.toString();
    }
}
