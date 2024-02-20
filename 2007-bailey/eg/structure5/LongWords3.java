// An example for a chapter on Generics -*- compile-command: "javac LongWords.java" -*-
import java.util.Vector;

@SuppressWarnings({"unchecked"})
public class LongWords3 {
    public static void main(String[] args)
    {
        Vector<String> longWords = new Vector<String>();
        int i;
        for (i = 0; i < args.length; i++) {
            if (args[i].length() > 4) {
                longWords.add(args[i]);  // line 12
            }
        }
        /*
        for (i = 0; i < args.length; i++) {
            if (args[i].length() > 4) {
                longWords.add(args);  // line 12: woops!
            }
        }
           ...
        */
        for (i = 0; i < longWords.size(); i++) {
            String word = longWords.get(i);  // line 31
            System.out.println(word+", length "+word.length());
        }
    }
}
/*
java LongWords George Herbert Walker Bush
George, length 6
Herbert, length 7
Walker, length 6
LongWords3.java:12: cannot find symbol
symbol  : method add(java.lang.String[])
location: class java.util.Vector<java.lang.String>
                longWords.add(args);
                         ^
java LongWords T is not Java
*/
