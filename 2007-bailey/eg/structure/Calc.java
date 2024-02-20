import structure.*;
public class Calc
{
    static int eval(BinaryTree expr)
    {
        if (expr == null) return 0;
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
        BinaryTree v1a,v1b,v2,vL,vR,t;

        // set up values 1 and 2, and declare variables
        v1a = new BinaryTree(new value(1));
        v1b = new BinaryTree(new value(1));
        v2 = new BinaryTree(new value(2));
        vL = new BinaryTree(new variable("L",0));// L=0
        vR = new BinaryTree(new variable("R",0));// R=0

        // set up expression
        t = new BinaryTree(new operator('-'),vL,v1a);
        t = new BinaryTree(new operator('*'),t,v2);
        t = new BinaryTree(new operator('+'),v1b,t);
        t = new BinaryTree(new operator('='),vR,t);

        // evaluate and print expression
        System.out.println(eval(t));
    }
}
/*
-1
 */


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
}

class value
{
    int val;
    public value(int v)
    {
        val = v;
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
}

