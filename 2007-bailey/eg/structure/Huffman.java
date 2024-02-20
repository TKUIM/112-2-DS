import structure.*;
import java.util.Iterator;
import java.util.Scanner;

public class Huffman
{
    public static void main(String args[])
    {
        // read System.in one character at a time
        Scanner s = new Scanner(System.in).useDelimiter("");
        List freq = new SinglyLinkedList();
    
        // read data from input
        while (s.hasNext())
        {
            // s.next() returns string; we're interested in first char
            char c = s.next().charAt(0);
            if (c == '\n') continue;
            // look up character in frequency list
            leaf query = new leaf(c);
            leaf item = (leaf)freq.remove(query);
            if (item == null)
            {   // not found, add new leaf
                freq.addFirst(query);
            } else { // found, increment leaf
                item.frequency++;
                freq.addFirst(item);
            }
        }
             
        // insert each character into a Huffman tree
        Iterator li = freq.iterator();
        OrderedList trees = new OrderedList();
        while (li.hasNext())
        {
            trees.add(new huffmanTree((leaf)li.next()));
        }
    
        // merge trees in pairs until one remains
        Iterator ti = trees.iterator();
        while (trees.size() > 1)
        {
            // construct a new iterator
            ti = trees.iterator();
            // grab two smallest values
            huffmanTree smallest = (huffmanTree)ti.next();
            huffmanTree small = (huffmanTree)ti.next();
            // remove them
            trees.remove(smallest);
            trees.remove(small);
            // add bigger tree containing both
            trees.add(new huffmanTree(smallest,small));
        }
        // print only tree in list
        ti  = trees.iterator();
        Assert.condition(ti.hasNext(),"Huffman tree exists.");
        huffmanTree encoding = (huffmanTree)ti.next();
        encoding.print();
    }
}

class leaf
{
    int frequency; // frequency of char
    char ch;    // the character

    public leaf(char c)
    // post: construct character entry with frequency 1
    {
        ch = c;
        frequency = 1;
    }

    public boolean equals(Object other)
    // post: return true if leaves represent same character
    {
        leaf that = (leaf)other;
        return this.ch == that.ch;
    }
}

class huffmanTree implements Comparable
{
    BinaryTree root; // root of tree
    int totalWeight;     // weight of tree

    public huffmanTree(leaf e)
    // post: construct a leaf with associated character
    {
        root = new BinaryTree(e);
        totalWeight = e.frequency;
    }

    public huffmanTree(huffmanTree left, huffmanTree right)
    // pre: left and right non-null
    // post: merge two trees together and merge their weights
    {
        this.totalWeight = left.totalWeight + right.totalWeight;
        root = new BinaryTree(null,left.root,right.root);
    }

    public int compareTo(Object other)
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
    
    public void print()
    // post: print out strings associated with characters in tree
    {
        print(this.root,"");
    }

    protected void print(BinaryTree r, String representation)
    // post: print out strings associated with chars in tree r,
    //       prefixed by representation
    {
        if (!r.left().isEmpty())
        {   // interior node
            print(r.left(),representation+"0"); // append a 0
            print(r.right(),representation+"1"); // append a 1
        } else { // leaf; print encoding
            leaf e = (leaf)r.value();
            System.out.println("Encoding of "+e.ch+" is "+
               representation+" (frequency was "+e.frequency+")");
        }
    }
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
