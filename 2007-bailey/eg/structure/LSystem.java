import structure.*;
import java.util.Scanner;
public class LSystem
{   // constants that define the alphabet
    final static Character L = new Character('L');
    final static Character S = new Character('S');

    /**
     * Write write the string, according to productions.
     * @pre s is a string of L and S values
     * @post returns a string rewritten by productions
     */
    public static Vector rewrite(Vector s)
    {
        Vector result = new Vector();
        for (int pos = 0; pos < s.size(); pos++)
        {
            // rewrite according to two different rules
            if (S == s.get(pos)) {
                result.add(L);
            } else if (L == s.get(pos)) {
                result.add(S); result.add(L);
            }
        }
        return result;
    }

    public static void main(String[] args)
    {
        Vector string = new Vector();
        string.add(S);

        // determine the number of strings
        Scanner s = new Scanner(System.in);
        int count = s.nextInt();

        // write out the start string
        System.out.println(string);
        for (int i = 1; i <= count; i++)
        {
            string = rewrite(string);   // rewrite the string
            System.out.println(string); // print it out
        }
    }
}
/*
6
<Vector: S>
<Vector: L>
<Vector: S L>
<Vector: L S L>
<Vector: S L L S L>
<Vector: L S L S L L S L>
<Vector: S L L S L L S L S L L S L>
*/
