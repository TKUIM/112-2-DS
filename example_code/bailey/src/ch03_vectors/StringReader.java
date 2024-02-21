/* 
 * StringReader.java
 * 
 *  以讀取字串放到記憶體為例，說明各種輸入方式下，使用向量容器存放字串的好處。
 *  StringReaderSilly 讀取固定數量n=4個字串，放到4個變數中
 *  StringReaderA 讀取固定數量n=4個字串，放到一個4格字串陣列中
 *  StringReaderB 讀取不固定數量n個字串，直到輸入Ctrl-d結束，放到一個1000000格字串陣列中
 *  StringReaderC 先讀取n，再讀取n個字串，放到一個n格字串陣列中
 *  StringReaderVector 讀取不固定數量n個字串，直到輸入Ctrl-d結束，放到一個Vector字串容器中

> java  -cp bailey.jar ch03_vector.StringReader
a b c d e f g
<Vector: a>
<Vector: a b>
<Vector: a b c>
<Vector: a b c d>
<Vector: a b c d e>
<Vector: a b c d e f>
<Vector: a b c d e f g>

*/
package ch03_vectors;
import structure5.Vector;
import java.util.Scanner;

// Main class
public class StringReader
{
    public static void main(String args[])
    {
        System.out.println("This code is not tested.");
        // 讀取固定數量n=4個字串，放到4個變數中
        // StringReaderSilly.main(args);
        
        // 讀取固定數量n=4個字串，放到一個4格字串陣列中
        // StringReaderA.main(args);
        
        // 讀取不固定數量n個字串，直到輸入Ctrl-d結束，放到一個1000000格字串陣列中
        // StringReaderB.main(args);
        
        // 先讀取n，再讀取n個字串，放到一個n格字串陣列中
        // StringReaderC.main(args);

        // 讀取不固定數量n個字串，直到輸入Ctrl-d結束，放到一個Vector字串容器中
        StringReaderVector.main(args);
    }
}

// 讀取固定數量n=4個字串，放到4個變數中
class StringReaderSilly
{
    public static void main(String args[])
    {
        // read in n = 4 strings
        Scanner s = new Scanner(System.in);
        String v1, v2, v3, v4;
        v1 = s.next();  // read a space-delimited word
        v2 = s.next();
        v3 = s.next();
        v4 = s.next();
    }
}

// 讀取固定數量n=4個字串，放到一個4格字串陣列中
class StringReaderA
{
    public static void main(String args[])
    {
        // read in n = 4 strings
        Scanner s = new Scanner(System.in);
        String data[];
        int n = 4;
        // allocate array of n String references:
        data = new String[n];
        for (int i = 0; i < n; i++)
        {
            data[i] = s.next();
        }
    }
}

// 讀取不固定數量n個字串，直到輸入Ctrl-d結束，放到一個1000000格字串陣列中
class StringReaderB
{
    public static void main(String args[])
    {
        // read in up to 1 million Strings
        Scanner s = new Scanner(System.in);
        String data[];
        int n = 0;
        data = new String[1000000];
        // read in strings until we hit end of file
        while (s.hasNext())
        {
            data[n] = s.next();
            n++;
        }
    }
}

// 先讀取n，再讀取n個字串，放到一個n格字串陣列中
class StringReaderC
{
    public static void main(String args[])
    {
        // read in as many Strings as demanded by input
        Scanner s = new Scanner(System.in);
        String data[];
        int n;
        // read in the number of strings to be read
        n = s.nextInt();
        // allocate references for n strings
        data = new String[n];
        // read in the n strings
        for (int i = 0; i < n; i++)
        {
            data[i] = s.next();
        }
    }
}

// 讀取不固定數量n個字串，直到輸入Ctrl-d結束，放到一個Vector字串容器中
class StringReaderVector
{
    public static void main(String args[])
    {
        // read in an arbitrary number of strings
        Scanner s = new Scanner(System.in);
        Vector<String> data;
        // allocate vector for storage
        data = new Vector<String>();
        // read strings, adding them to end of vector, until eof
        while (s.hasNext())
        {
            String st = s.next();
            data.add(st);
            System.out.println(data);
        }    
    }
}

/*
This code is not tested.
*/
