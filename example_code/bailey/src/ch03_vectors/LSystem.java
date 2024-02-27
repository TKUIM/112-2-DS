/*
  LSystem.java

  展示如何使用Vector字元容器，實作符合L系統文法的簡單字元序列生成器
  L系統文法的規則如下：
    S -> L
    L -> S L
  這個程式會讀取一個整數n，然後產生n個字元序列，每個字元序列都是由上述規則產生的

> java -cp bailey.jar ch03_vectors.LSystem.java
6
<Vector: S>
<Vector: L>
<Vector: S L>
<Vector: L S L>
<Vector: S L L S L>
<Vector: L S L S L L S L>
<Vector: S L L S L L S L S L L S L>
*/
package ch03_vectors;
import structure5.*;
import java.util.Scanner;
public class LSystem
{   // constants that define the alphabet
    final static Character L = 'L';
    final static Character S = 'S';

    /**
     * Write write the string, according to productions.
     * @pre s is a string of L and S values
     * @post returns a string rewritten by productions
     */
    public static Vector<Character> rewrite(Vector<Character> s)
    {
        Vector<Character> result = new Vector<Character>();
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
        Vector<Character> string = new Vector<Character>();
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
