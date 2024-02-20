import structure5.*;
import java.util.Iterator;

public class Huffman2
{
    public static void main(String args[])
    {
        ReadStream r = new ReadStream();
        List<node> freq = new SinglyLinkedList<node>();

        // read data from input
        while (!r.eof())
        {
            char c = r.readChar();
            if (c == '\n') continue;
            // look up character in frequency list
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
             
        // insert each character into a huffman tree
        PriorityQueue<huffmanTree> trees = new PriorityVector<huffmanTree>();
        for (node n : freq)
        {
            trees.add(new huffmanTree(n));
        }

        // merge trees in pairs until one remains
        while (trees.size() > 1)
        {
            // grab two smallest values
            huffmanTree smallest = (huffmanTree)trees.remove();
            huffmanTree small = (huffmanTree)trees.remove();
            // add bigger tree containing both
            trees.add(new huffmanTree(smallest,small));
        }
        huffmanTree encoding = trees.remove();
        encoding.print();
    }
}

class node
{
    int frequency; // frequency of char
    char ch;    // the character

    public node(int freq)
    // post: construct entry with frequency freq
    {
        ch = 0;
        frequency = freq;
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
        totalWeight = e.frequency;
    }

    public huffmanTree(huffmanTree left, huffmanTree right)
    // pre: left and right non-null
    // post: merge two trees together and total weights
    {
        this.totalWeight = left.totalWeight + right.totalWeight;
        root = new BinaryTree<node>(new node(this.totalWeight),left.root,right.root);
    }

    public int compareTo(huffmanTree other)
    // pre: other is not null
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

    protected void print(BinaryTree<node> r, String representation)
    // post: print out strings associated with chars in tree r,
    //       prefixed by representation
    {
        if (!r.left().isEmpty())
        {   // interior node
            print(r.left(),representation+"0"); // append a 0
            print(r.right(),representation+"1"); // append a 1
        } else { // node; print encoding
            node e = r.value();
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
