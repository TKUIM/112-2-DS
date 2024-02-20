import structure5.*;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Scanner;
public class RevComparator<T> implements Comparator<T>
{
    protected Comparator<T> base;

    public RevComparator(Comparator<T> baseCompare)
    {
        base = baseCompare;
    }

    public int compare(T a, T b)
    {
        return -base.compare(a,b);
    }
    public static void main(String args[])
    {
        MyVector<Integer> v = new MyVector<Integer>();
        Scanner s = new Scanner(System.in);

        while (s.hasNextInt())
        {
            v.add(s.nextInt());
        }

        Comparator<Integer> c = new RevComparator<Integer>(new IntegerComparator());
        v.sort(c);
        for (Integer i : v) {
            System.out.println(i);
        }
    }

}
/*
73 92 40 38 51 17 8 31 56 84 21 34 29 16 61 31 7 63 70 32 
69 38 98 93 31 84 45 74 8 33 30 32 76 69 21 27 85 81 9 92 
16 82 26 45 97 38 75 3 53 97 86 21 34 35 65 64 71 9 89 30 
42 70 13 69 90 33 47 75 13 56 18 29 89 95 25 85 33 99 39 37 
95 76 9 29 62 74 44 32 82 85 61 75 54 73 96 44 58 42 70 70 
99
98
97
97
96
95
95
93
92
92
90
89
89
86
85
85
85
84
84
82
82
81
76
76
75
75
75
74
74
73
73
71
70
70
70
70
69
69
69
65
64
63
62
61
61
58
56
56
54
53
51
47
45
45
44
44
42
42
40
39
38
38
38
37
35
34
34
33
33
33
32
32
32
31
31
31
30
30
29
29
29
27
26
25
21
21
21
18
17
16
16
13
13
9
9
9
8
8
7
3
*/
