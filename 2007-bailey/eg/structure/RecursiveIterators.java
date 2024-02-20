 import structure.*;
import java.util.Iterator;
public class RecursiveIterators
{
    static int eval(BinaryTree expr)
    {
        if (expr.isEmpty()) return 0;
        Object v = expr.value();
        if (v instanceof variable) return ((variable)v).val;
        if (v instanceof value) return ((value)v).val;
        // must be an operator
        switch (((operator)v).code)
        {
          case operator.ASSIGN:
            variable leftV = (variable)expr.left().value();
            return leftV.val = eval(expr.right());
          case operator.PLUS:
            return eval(expr.left())+eval(expr.right());
          case operator.MINUS:
            return eval(expr.left())-eval(expr.right());
          case operator.TIMES:
            return eval(expr.left())*eval(expr.right());
          case operator.DIVIDE:
            return eval(expr.left())/eval(expr.right());
        }
        return 0;
    }

    public static void main(String args[])
    {
        BinaryTree v1,v2,vL,vR,t;

        // set up values 1 and 2, and declare variables.
        v1 = new BinaryTree(new value(1));
        v2 = new BinaryTree(new value(2));
        vL = new BinaryTree(new variable("L",0));// L=0
        vR = new BinaryTree(new variable("R",0));// R=0

        // set up expression
        t = new BinaryTree(new operator('-'),vL,v1);
        t = new BinaryTree(new operator('*'),t,v2);
        t = new BinaryTree(new operator('+'),v1,t);
        t = new BinaryTree(new operator('='),vR,t);

        // evaluate and print expression
        BTInorderIteratorR i = new BTInorderIteratorR(t);
        for (i.reset(); i.hasNext(); i.next())
        {
            System.out.println(i.get());
        }
    }
}

class operator
{
    final static int ASSIGN = 0;
    final static int PLUS = 2;
    final static int MINUS = 3;
    final static int TIMES = 4;
    final static int DIVIDE = 5;
    int code;
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

    public String toString()
    {
        switch (code)
        { case PLUS: return "operator +";
          case MINUS: return "operator -";
          case TIMES: return "operator *";
          case DIVIDE: return "operator /";
          case ASSIGN: return "operator =";
          default: return "Bad operator.";
        }
        
    }
}

class value
{
    int val;
    public value(int v)
    {
        val = v;
    }

    public String toString()
    {
        return "value "+val;
    }
}

class variable extends value
{
    String name;
    public variable(String n,int v)
    {
        super(v);
        name = n;
    }

    public String toString()
    {
        return "variable "+name+" with value "+val;
    }
}

class BTInorderIteratorR extends AbstractIterator
{
    protected BinaryTree root; // root of traversed subtree
    protected Queue todo;  // queue of unvisited elements

    public BTInorderIteratorR(BinaryTree root)
    // post: constructs an iterator to traverse in in-order
    {
        todo = new QueueList();
        this.root = root;
        reset();
    }   

    public void reset()
    // post: resets the iterator to retraverse
    {
        todo.clear();
        enqueueInorder(root);
    }
    
    protected void enqueueInorder(BinaryTree current)
    // pre: current is non-null
    // post: enqueue all values found in tree rooted at current
    //       in in-order
    {
        if (current.isEmpty()) return;
        enqueueInorder(current.left());
        todo.enqueue(current);
        enqueueInorder(current.right());
    }

    protected void enqueuePreorder(BinaryTree current)
    // pre: current is non-null
    // post: enqueue all values found in tree rooted at current
    //       in preorder
    {
        if (current.isEmpty()) return;
        todo.enqueue(current);
        enqueuePreorder(current.left());
        enqueuePreorder(current.right());
    }

    protected void enqueuePostorder(BinaryTree current)
    // pre: current is non-null
    // post: enqueue all values found in tree rooted at current
    //       in postorder
    {
        if (current.isEmpty()) return;
        enqueuePostorder(current.left());
        enqueuePostorder(current.right());
        todo.enqueue(current);
    }


    public boolean hasNext()
    // post: returns true iff iterator is not finished
    {
        return !todo.isEmpty();
    }

    public Object get()
    // pre: hasNext()
    // post: returns reference to current value
    {   
        return ((BinaryTree)todo.getFirst()).value();
    }

    public Object next()
    // pre: hasNext();
    // post: returns current value, increments iterator
    {
        BinaryTree current = (BinaryTree)todo.dequeue();
        return current.value();
    }
}
/*
variable R with value 0
operator =
value 1
operator +
variable L with value 0
operator -
value 1
operator *
value 2
*/
