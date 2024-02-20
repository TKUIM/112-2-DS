import java.util.Vector;

@SuppressWarnings({"unchecked"})
public class LongWords2 {
    public static void main(String[] args)
    {
        StringVector longWords = new StringVector();
        int i;
        for (i = 0; i < args.length; i++) {
            if (args[i].length() > 4) {
                longWords.add(args);
            }
        }
        /*
        ...
        */
        for (i = 0; i < longWords.size(); i++) {
            String word = longWords.get(i);
            System.out.println(word+", length "+word.length());
        }
    }
}
/*
LongWords2.java:12: cannot find symbol
symbol  : method add(java.lang.String[])
location: class StringVector
                longWords.add(args);
                         ^
1 error
java LongWords2 George Herbert Walker Bush
George, length 6
Herbert, length 7
Walker, length 6
*/
