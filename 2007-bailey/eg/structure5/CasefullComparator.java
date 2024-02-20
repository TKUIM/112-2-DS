public class CasefullComparator implements java.util.Comparator<String>
{
    public int compare(String a, String b)
    // pre: a and b are valid Strings
    // post: returns a value less than, equal to, or greater than 0
    //       if a is less than, equal to, or greater than b, without
    //       consideration of case
    {
        String upperA = ((String)a);
        String upperB = ((String)b);
        return upperA.compareTo(upperB);
    }
}
