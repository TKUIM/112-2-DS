package java.util;
public interface Comparator 
{
    public abstract int compare(Object a, Object b);
    // pre: a and b are valid objects, likely of similar type
    // post: returns a value less than, equal to, or greater than 0
    //       if a is less than, equal to, or greater than b
}
