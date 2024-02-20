public class IntegerRevComparator implements java.util.Comparator
{
    public int compare(Object a, Object b)
    {
        Integer ai = (Integer)a;
        Integer bi = (Integer)b;
        return bi - ai;
    }
}
