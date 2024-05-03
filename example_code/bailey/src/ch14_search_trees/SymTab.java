/*
   SymTab.java
     搜索樹符號表，未考慮左右平衡
     元素為鍵值配對,鍵放別名字串,值放對應名字字串
                            three=3
        one=unity                          unity=1
                  pi=three

> java SymTab
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

<BinarySearchTree:<BinaryTree <ComparableAssociation: three=3> <BinaryTree <ComparableAssociation: one=unity> - <BinaryTree <ComparableAssociation: pi=three> - ->> <BinaryTree <ComparableAssociation: unity=1> - ->>>
<<ComparableAssociation: three=3> : Root>
	|<<ComparableAssociation: one=unity> : L>
	|	|<<ComparableAssociation: pi=three> : R>
	|<<ComparableAssociation: unity=1> : R>

Note: 
  <BinarySearchTree:
      <BinaryTree <ComparableAssociation: three=3> 
          <BinaryTree <ComparableAssociation: one=unity> 
             - 
             <BinaryTree <ComparableAssociation: pi=three> - ->> 
          <BinaryTree <ComparableAssociation: unity=1> - ->>>
  or
                            three=3
        one=unity                          unity=1
                  pi=three

*/
// A simple symbol table for a postscript interpreter.
// (c) 2001,1996, 2001 duane a. bailey
package ch14_search_trees;
import structure5.*;
import java.util.Iterator;
import java.util.Scanner;
public class SymTab<S extends Comparable<S>,T>
{
    protected BinarySearchTree<ComparableAssociation<S,T>> table;
    public SymTab()
    // post: constructs empty symbol table
    {
        table = new BinarySearchTree<ComparableAssociation<S,T>>();
    }

    public boolean contains(S symbol)
    // pre: symbol is non-null string
    // post: returns true iff string in table
    {
        ComparableAssociation<S,T> a = 
            new ComparableAssociation<S,T>(symbol,null);
        return table.contains(a);
    }

    public void add(S symbol, T value)
    // pre: symbol non-null
    // post: adds/replaces symbol-value pair in table
    {
        ComparableAssociation<S,T> a =
            new ComparableAssociation<S,T>(symbol,value);
        if (table.contains(a)) table.remove(a);
        table.add(a);
    }

    public T get(S symbol)
    // pre: symbol non null
    // post: returns token associated with symbol
    {
        ComparableAssociation<S,T> a =
            new ComparableAssociation<S,T>(symbol,null);
        if (table.contains(a)) {
            a = table.get(a);
            return a.getValue();
        } else {
            return null;
        }
    }

    public T remove(S symbol)
    // pre: symbol non null
    // post: removes value associated with symbol and returns it
    //       if error returns null
    {
        ComparableAssociation<S,T> a =
            new ComparableAssociation<S,T>(symbol,null);
        if (table.contains(a)) {
            a = table.remove(a);
            return a.getValue();
        } else {
            return null;
        }
    }

    public static void main(String args[])
    {
        // 建立符號表，鍵放別名,值放對應名字
        SymTab<String,String> table = new SymTab<String,String>();
        Scanner s = new Scanner(System.in);
        String alias, name;
        
        // read in the alias-name database 建表
        System.out.println("Build table");
        System.out.println("Input (END for finish): alias name ");
        do
        {
            alias = s.next(); // 讀別名
            if (!alias.equals("END"))
            {
                name = s.next(); // 讀名字
                table.add(alias,name);  // 符號表加入(別名,名字)配對
            }
        } while (!alias.equals("END"));

        // enter the alias translation stage 查表
        System.out.println("\nLoookup table");
        System.out.println("Input (END for finish): alias");
        do
        {
            name = s.next(); // 讀別名
            if(name.equals("END")) break;
            
            // 不斷迭代查出所有別名的對應名字，直到查不出別名為止
            while (table.contains(name))
            {
                // 查對應名字，覆蓋成別名
                // translate alias
                name = table.get(name);
            }
            System.out.println(name); // 列印最後查出名字
        } while (s.hasNext());
        
        System.out.println();
        System.out.println(table.table.toString());
        System.out.println(table.table.treeString());
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
