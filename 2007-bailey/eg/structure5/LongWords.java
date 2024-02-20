// An example for a chapter on Generics -*- compile-command: "javac LongWords.java" -*-
import java.util.Vector;

@SuppressWarnings({"unchecked"})
public class LongWords {
    public static void main(String[] args)
    {
        Vector longWords = new Vector();
        int i;
        for (i = 0; i < args.length; i++) {
            if (args[i].length() > 4) {
                longWords.add(args[i]);  // line 12
            }
        }
        /*
        for (i = 0; i < args.length; i++) {
            if (args[i].length() > 4) {
                longWords.add(args);  // line 12
            }
        }
           ...
        */
        for (i = 0; i < longWords.size(); i++) {
            String word = (String)longWords.get(i); // line 31
            System.out.println(word+", length "+word.length());
        }
    }
}
/*
java LongWords Fuzzy Wozzie was a bear
java LongWords Fuzzy Wozzie had no hair
Fuzzy, length 5
Wozzie, length 6
Exception in thread "main" java.lang.ClassCastException: [Ljava.lang.String;
        at LongWords.main(LongWords.java:31)
java LongWords aye but what a bear that Fuzz was
*/
