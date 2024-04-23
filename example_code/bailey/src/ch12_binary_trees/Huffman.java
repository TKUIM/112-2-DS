/*
*  Huffman.java
*    分析字串的字元頻度，建立壓縮該字串專用的Huffman二分編碼樹
*    列印字元編碼表
//   待補: 列印字串原來長度，字串壓縮長度，壓縮比
*
* > java Huffman
If a woodchuck could chuck wood!
Encoding of ! is 0000 (frequency was 1)
Encoding of a is 00010 (frequency was 1)
Encoding of l is 00011 (frequency was 1)
Encoding of u is 001 (frequency was 3)
Encoding of d is 010 (frequency was 3)
Encoding of k is 0110 (frequency was 2)
Encoding of w is 0111 (frequency was 2)
Encoding of I is 10000 (frequency was 1)
Encoding of f is 10001 (frequency was 1)
Encoding of h is 1001 (frequency was 2)
Encoding of c is 101 (frequency was 5)
Encoding of   is 110 (frequency was 5)
Encoding of o is 111 (frequency was 5)
*/
package ch12_binary_trees;
import structure5.List;
import structure5.SinglyLinkedList;
import java.util.Iterator;
import java.util.Scanner;
import structure5.Assert;
import structure5.OrderedList;

public class Huffman
{
    public static void main(String args[])
    {
        // read System.in one character at a time
        String testString = "If a woodchuck could chuck wood!";
        //Scanner s = new Scanner(System.in).useDelimiter("");
        Scanner s = new Scanner(testString).useDelimiter("");
  
        System.out.println(testString);
        
        // 建立空字元節點清單
        List<node> freq = new SinglyLinkedList<node>();
    
        // read data from input
        // 統計字串的字元頻度，存放在字元節點清單中
        while (s.hasNext())
        {
            // s.next() returns string; we're interested in first char
            char c = s.next().charAt(0);
            if (c == '\n') continue;
            
            // look up character in frequency list
            // 移除式找尋清單是否有c字元
            // 若成功，移除回傳該字元節點
            // 若失敗，回傳空
            node query = new node(c);
            node item = freq.remove(query);
            if (item == null)
            {   // not found, add new node
                freq.addFirst(query);
            } else { // found, increment node
                item.frequency++;
                freq.addFirst(item);
            }
        }
             
        // insert each character into a Huffman tree
        // 利用每個字元建立一棵初始huffman樹，
        // 將所有hufmman樹，依權重排序由小到大，放入trees有序清單容器
        OrderedList<huffmanTree> trees = new OrderedList<huffmanTree>();
        for (node n : freq)
        {
            trees.add(new huffmanTree(n));
        }
    
        // merge trees in pairs until one remains
        // 依序合併兩樹直到長成1棵樹為止
        Iterator ti = trees.iterator();
        while (trees.size() > 1)
        {
            // construct a new iterator
            ti = trees.iterator();
            // grab two smallest values
            // 取權重最小兩棵樹
            huffmanTree smallest = (huffmanTree)ti.next();
            huffmanTree small = (huffmanTree)ti.next();
            
            // remove them
            // 移除最小兩棵樹
            trees.remove(smallest);
            trees.remove(small);
            
            // add bigger tree containing both
            // 最小左樹及次小右樹，兩者合併成新樹，放回有序清單
            trees.add(new huffmanTree(smallest,small));
        }
        
        // print only tree in list
        // 列印剩1棵的編碼樹
        ti  = trees.iterator();
        Assert.condition(ti.hasNext(),"Huffman tree exists.");
        huffmanTree encoding = (huffmanTree)ti.next();
        encoding.print();
    }
}

// 字元節點，放字元及其頻度
class node
{
    int frequency; // frequency of char
    char ch;    // the character

    public node(int f)
    // post: construct an entry with frequency f
    {
        ch = 0;
        frequency = f;
    }

    public node(char c)
    // post: construct character entry with frequency 1
    {
        ch = c;
        frequency = 1;
    }

    public boolean equals(Object other)
    // post: return true if leaves represent same character
    {
        node that = (node)other;
        return this.ch == that.ch;
    }
}

// 可比較大小的 Huffman二分編碼樹
class huffmanTree implements Comparable<huffmanTree>
{
    BinaryTree<node> empty;
    BinaryTree<node> root; // root of tree
    int totalWeight;     // weight of tree

    public huffmanTree(node e)
    // post: construct a node with associated character
    {
        empty = new BinaryTree<node>();
        root = new BinaryTree<node>(e,empty,empty);
        totalWeight = e.frequency; // 權重為節點頻度
    }

    // 左右兩樹合併成新樹，新樹權重為兩樹權重相加
    public huffmanTree(huffmanTree left, huffmanTree right)
    // pre: left and right non-null
    // post: merge two trees together and merge their weights
    {
        this.totalWeight = left.totalWeight + right.totalWeight;
        root = new BinaryTree<node>(new node(totalWeight),left.root,right.root);
    }

    public int compareTo(huffmanTree other)
    // pre: other is non-null
    // post: return integer reflecting relation between values
    {
        huffmanTree that = (huffmanTree)other;
        return this.totalWeight - that.totalWeight;
    }

    public boolean equals(Object that)
    // post: return true if this and that are same tree instance
    {
        return this == that;
    }
    
    // 列印樹的編碼表
    public void print()
    // post: print out strings associated with characters in tree
    {
        print(this.root,"");
    }

    // 遞迴列印樹的編碼表
    protected void print(BinaryTree r, String representation)
    // post: print out strings associated with chars in tree r,
    //       prefixed by representation
    {
        if (!r.left().isEmpty())
        {   // interior node
            print(r.left(),representation+"0"); // append a 0
            print(r.right(),representation+"1"); // append a 1
        } else { // node; print encoding
            node e = (node)r.value();
            System.out.println("Encoding of "+e.ch+" is "+
               representation+" (frequency was "+e.frequency+")");
        }   }
}

/*
If a woodchuck could chuck wood!
*/
/*
    Encoding of ! is 0000 (frequency was 1)
    Encoding of a is 00010 (frequency was 1)
    Encoding of l is 00011 (frequency was 1)
    Encoding of u is 001 (frequency was 3)
    Encoding of d is 010 (frequency was 3)
    Encoding of k is 0110 (frequency was 2)
    Encoding of w is 0111 (frequency was 2)
    Encoding of I is 10000 (frequency was 1)
    Encoding of f is 10001 (frequency was 1)
    Encoding of h is 1001 (frequency was 2)
    Encoding of c is 101 (frequency was 5)
    Encoding of   is 110 (frequency was 5)
    Encoding of o is 111 (frequency was 5)
    Old length=256 new length=111 57% compression.
*/
