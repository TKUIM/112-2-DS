/*
*  Calc.java
*    建立二分樹表示運算樹及求值
*        R = 1 + (L - 1) * 2
*
*                =
*           R            +
*                   1             *
*                            -         2
*                           L   1
*    註: R, L 初值為0
*
> java Calc
-1
<BinaryTree ch12_binary_trees.operator@614c5515 <BinaryTree ch12_binary_trees.variable@77b52d12 - -> <BinaryTree ch12_binary_trees.operator@2d554825 <BinaryTree ch12_binary_trees.value@68837a77 - -> <BinaryTree ch12_binary_trees.operator@6be46e8f <BinaryTree ch12_binary_trees.operator@3567135c <BinaryTree ch12_binary_trees.variable@327471b5 - -> <BinaryTree ch12_binary_trees.value@4157f54e - ->> <BinaryTree ch12_binary_trees.value@90f6bfd - ->>>>
<ch12_binary_trees.operator@614c5515 : Root>
	|<ch12_binary_trees.variable@77b52d12 : L>
	|<ch12_binary_trees.operator@2d554825 : R>
	|	|<ch12_binary_trees.value@68837a77 : L>
	|	|<ch12_binary_trees.operator@6be46e8f : R>
	|	|	|<ch12_binary_trees.operator@3567135c : L>
	|	|	|	|<ch12_binary_trees.variable@327471b5 : L>
	|	|	|	|<ch12_binary_trees.value@4157f54e : R>
	|	|	|<ch12_binary_trees.value@90f6bfd : R>

*/
package ch12_binary_trees;
//import structure5.*;
import structure5.Assert;

public class Calc
{
    // 對expr運算樹求值，回傳整數
    static int eval(BinaryTree expr)
    {
        if (expr == null) return 0;
        
        // 取樹根節點的項次物件v，
        // 其型別可能是 value，variable，operator，term，Object
        Object v = expr.value();  

        // 若v物件為variable型別，回傳其整數值
        // 若v物件為value型別，回傳其整數值
        if (v instanceof variable) return ((variable)v).val;
        if (v instanceof value) return ((value)v).val;
        
        // must be an operator
        // 若v物件不是運算元variable或value型別，
        // 必為運算子型別，轉型為運算子型別後，取其運算子代碼
        switch (((operator)v).code)
        {
          case operator.ASSIGN:
            variable leftV = (variable)expr.left().value();
            return leftV.val = eval(expr.right());
          case operator.PLUS:
            return eval(expr.left()) + eval(expr.right());
          case operator.MINUS:
            return eval(expr.left()) - eval(expr.right());
          case operator.TIMES:
            return eval(expr.left()) * eval(expr.right());
          case operator.DIVIDE:
            return eval(expr.left()) / eval(expr.right());
        }
        return 0;
    }

    // 建立運算樹及求值
    //   R = 1 + (L - 1) * 2
    public static void main(String args[])
    {
        BinaryTree<term> v1a,v1b,v2,vL,vR,t;

        // set up values 1 and 2, and declare variables
        // 利用常數項1,2及變數項L,R，建立各樹根運算樹
        v1a = new BinaryTree<term>(new value(1));
        v1b = new BinaryTree<term>(new value(1));
        v2 = new BinaryTree<term>(new value(2));
        vL = new BinaryTree<term>(new variable("L",0));// L=0
        vR = new BinaryTree<term>(new variable("R",0));// R=0

        // set up expression
        // 利用運算子建立運算樹 R = 1 + (L - 1) * 2
        t = new BinaryTree<term>(new operator('-'),vL,v1a);  // L - 1
        t = new BinaryTree<term>(new operator('*'),t,v2); // t * 2
        t = new BinaryTree<term>(new operator('+'),v1b,t); // 1 + t
        t = new BinaryTree<term>(new operator('='),vR,t); // R = t

        // evaluate and print expression
        // 求值及列印
        System.out.println(eval(t));
        
        System.out.println(t);
        System.out.println(t.treeString());
        
        System.out.println("t.depth():" + t.depth());
        System.out.println("t.height():" + t.height());
    }
}
/*
-1
 */


// 項次當共同祖先
class term
{
}

// 運算子項次
//  term <- operator
class operator extends term
{
    final static int ASSIGN = 0;
    final static int PLUS = 2;
    final static int MINUS = 3;
    final static int TIMES = 4;
    final static int DIVIDE = 5;
    int code;  // 運算子代碼
    
    public operator(char c)
    {
        switch (c)
        { case '+': code = PLUS; break;
          case '-': code = MINUS; break;
          case '*': code = TIMES; break;
          case '/': code = DIVIDE; break;
          case '=': code = ASSIGN; break;
          default: Assert.fail("Bad operator.");
        }
    }
}

// 常數項次，含整數值
//  term <- value
class value extends term
{
    int val; // 常數值
    
    public value(int v)
    {
        val = v;
    }
}

// 變數項次，含名稱及整數值
//  term <- value <- variable
class variable extends value
{
    String name; // 變數名
    
    public variable(String n,int v)
    {
        super(v); // 整數初始值為 v
        name = n;
    }
}
