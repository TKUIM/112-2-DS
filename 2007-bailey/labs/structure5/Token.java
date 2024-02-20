// A class for managing postscript tokens.
// (c) 2001,1996, 2001 duane a. bailey
import structure.*;
import java.util.Iterator;

public class Token
{
    // these constants define the type of token.
    static public final int NumberKind = 1;
    static public final int BooleanKind = 2;
    static public final int SymbolKind = 3;
    static public final int ProcedureKind = 4;

    protected int kind;         // type of token
    protected double number;    // a double token value
    protected boolean bool;     // a boolean token value
    protected String symbol;    // name of a symbol
    protected List procedure;   // a list of tokens for procedures

    public Token(double value)
    // post: constructs a double token with value value
    {
        kind = NumberKind;
        this.number = value;
    }

    public Token(boolean bool)
    // post: constructs a boolean token with value bool
    {
        kind = BooleanKind;
        this.bool = bool;
    }

    public Token(String symbol)
    // post: constructs a symbol token with value symbol
    {
        kind = SymbolKind;
        this.symbol = symbol;
    }

    public Token(List proc)
    // post: constructs a procedure token with values from List
    {
        kind = ProcedureKind;
        this.procedure = proc;
    }

    public int kind()
    // post: returns kind of this token
    //       great for use in switch statements
    {
        return this.kind;
    }

    public boolean isNumber()
    // post: returns true if this token is a number token
    {
        return kind == NumberKind;
    }

    public boolean isBoolean()
    // post: returns true if this token is a boolean token
    {
        return kind == BooleanKind;
    }

    public boolean isSymbol()
    // post: returns true if this token is a symbol token
    {
        return kind == SymbolKind;
    }

    public boolean isProcedure()
    // post: returns true if this token is a procedure token
    {
        return kind == ProcedureKind;
    }

    public double getNumber()
    // pre: isNumber()
    // post: returns value of this token
    {
        Assert.pre(isNumber(),"Is a number.");
        return number;
    }

    public boolean getBoolean()
    // pre: isBoolean()
    // post: returns value of this token
    {
        Assert.pre(isBoolean(),"Is a boolean.");
        return bool;
    }

    public String getSymbol()
    // pre: isSymbol()
    // post: returns value of this token
    {
        Assert.pre(isSymbol(),"Is a string.");
        return symbol;
    }

    public List getProcedure()
    // pre: isProcedure()
    // post: returns value of this token
    {
        Assert.pre(isProcedure(),"Is a procedure.");
        return procedure;
    }

    public boolean equals(Object other)
    // pre: other is a token.
    // post: returns true if this has the same value as other
    //       doesn't quite work for list case, but sufficient for our use.
    {
        Token that = (Token)other;
        boolean result = false;
        // if types are different, the tokens are different
        if (this.kind != that.kind) return false;
        switch (this.kind)
        {
          case NumberKind:
            result = this.number == that.number;
            break;
          case BooleanKind:
            result = this.bool == that.bool;
            break;
          case SymbolKind:
            result = this.symbol.equals(that.symbol);
            break;
          case ProcedureKind:
            result = this.procedure == that.procedure;
            break;
        }
        return result;
    }

    public String toString()
    // post: returns string representation of token.
    {
        String result = "<unknown>";
        switch (kind)
        {
          case NumberKind:
            result = ""+number;
            break;
          case BooleanKind:
            result = ""+bool;
            break;
          case SymbolKind:
            result = symbol;
            break;
          case ProcedureKind:
            result = "{ ";
            // the iterator allows us to visit elements nondestructively
            Iterator i = procedure.iterator();
            while (i.hasNext())
            {
                Token t = (Token)i.next();
                result = result + " " + t;
            }
            result = result + " }";
            break;
        }
        return result;
    }
}

