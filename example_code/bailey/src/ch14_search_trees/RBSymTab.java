/*
   RBSymTab.java
     紅黑樹符號表，有考慮左右平衡，維持查找效率
     元素為鍵值配對,鍵放別名字串,值放對應名字字串

> java RBSymTab
Build table
Input (END for finish): alias name 
three 3
one unity
unity 1
pi three
END

Loookup table
Input (END for finish): alias
one
1
two
two
three
3
pi
3
END

[[<ComparableAssociation: one=unity>(<ComparableAssociation: pi=three>)]<ComparableAssociation: three=3>[<ComparableAssociation: unity=1>]]
*/
// A simple symbol table for a postscript interpreter.
// (c) 2001,1996, 2001 duane a. bailey
package ch14_search_trees;
import structure5.*;
import java.util.Iterator;

public class RBSymTab<S extends Comparable<S>,T>
{
    // protected RedBlackTree<ComparableAssociation<S,T>> table;
    RedBlackTree<ComparableAssociation<S,T>> table;

    public RBSymTab()
    // post: constructs empty symbol table
    {
        table = new RedBlackTree<ComparableAssociation<S,T>>();
    }

    public boolean contains(S symbol)
    // pre: symbol is non-null string
    // post: returns true iff string in table
    {
        return table.contains(new ComparableAssociation<S,T>(symbol,null));
    }

    public void add(S symbol, T value)
    // pre: symbol non-null
    // post: adds/replaces symbol-value pair in table
    {
        ComparableAssociation<S,T> a = new ComparableAssociation<S,T>(symbol,value);
        if (table.contains(a)) table = table.remove(a);
        table = table.add(a);
    }

    public T get(S symbol)
    // pre: symbol non-null
    // post: returns token associated with symbol
    {
        ComparableAssociation<S,T> a = new ComparableAssociation<S,T>(symbol,null);
        if (table.contains(a)) {
            a = table.get(a);
            return a.getValue();
        } else {
            return null;
        }
    }

    public T remove(S symbol)
    // pre: symbol non-null
    // post: removes value associated with symbol and returns it
    //       if error returns null
    {
        ComparableAssociation<S,T> a = new ComparableAssociation<S,T>(symbol,null);
        if (table.contains(a)) {
            a = table.get(a);
            table = table.remove(a);
            return a.getValue();
        } else {
            return null;
        }
    }

    public static void main(String args[])
    {
        // 建立符號表，鍵放別名,值放對應名字
        RBSymTab<String,String> table = new RBSymTab<String,String>();
        ReadStream r = new ReadStream();
        String alias, name;
        
        // read in the alias-name database 建表
        System.out.println("Build table");
        System.out.println("Input (END for finish): alias name ");
        do
        {
            alias = r.readString(); // 讀別名
            if (!alias.equals("END"))
            {
                name = r.readString();  // 讀名字
                table.add(alias,name);  // 符號表加入(別名,名字)配對
            }
        } while (!alias.equals("END"));
        
        // enter the alias translation stage 查表
        System.out.println("\nLoookup table");
        System.out.println("Input (END for finish): alias");
        do
        {
            name = r.readString(); // 讀別名
            if(name.equals("END")) break;

            // 不斷迭代查出所有別名的對應名字，直到查不出別名為止
            while (table.contains(name))
            {
                // 查對應名字，覆蓋成別名
                name = (String)table.get(name); // translate alias 
            }
            System.out.println(name); // 列印最後查出名字
            r.skipWhite();
        } while (!r.eof());
        
        System.out.println();
        System.out.println(table.table.toString());
        //System.out.println(table.table.treeString());
    }
}

/*
three 3
one unity
unity 1
pi three
END

one
two
three
pi
1
two
3
3
*/
