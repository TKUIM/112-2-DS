import java.util.Comparator;
public class IntegerComparator implements Comparator<Integer>
{
    public int compare(Integer a, Integer b)
    {
        Integer ai = (Integer)a;
        Integer bi = (Integer)b;
        return ai - bi;
    }
}
