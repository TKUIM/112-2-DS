import java.util.Comparator;
public class IntegerComparator implements Comparator
{
    public int compare(Object a, Object b)
    {
        Integer ai = (Integer)a;
        Integer bi = (Integer)b;
        return ai.intValue() - bi.intValue();
    }
}
