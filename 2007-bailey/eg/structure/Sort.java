// A general sorting algorithm.
// (c) 1996, 2001 duane a. bailey

import structure.*;
import java.util.Iterator;
import java.util.Scanner;

public class Sort
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        OrderedStructure o = new OrderedVector();
        // read in integers
        while (s.hasNextInt())
        {
            o.add(new Integer(s.nextInt()));
        }
        // and print them out, in order
        Iterator i = o.iterator();
        while (i.hasNext())
        {
            System.out.println(i.next());
        }
    }
}
/*
73 92 40 38 51 17 8 31 56 84 21 34 29 16 61 31 7 63 70 32 
69 38 98 93 31 84 45 74 8 33 30 32 76 69 21 27 85 81 9 92 
16 82 26 45 97 38 75 3 53 97 86 21 34 35 65 64 71 9 89 30 
42 70 13 69 90 33 47 75 13 56 18 29 89 95 25 85 33 99 39 37 
95 76 9 29 62 74 44 32 82 85 61 75 54 73 96 44 58 42 70 70 
3
7
8
8
9
9
9
13
13
16
16
17
18
21
21
21
25
26
27
29
29
29
30
30
31
31
31
32
32
32
33
33
33
34
34
35
37
38
38
38
39
40
42
42
44
44
45
45
47
51
53
54
56
56
58
61
61
62
63
64
65
69
69
69
70
70
70
70
71
73
73
74
74
75
75
75
76
76
81
82
82
84
84
85
85
85
86
89
89
90
92
92
93
95
95
96
97
97
98
99
*/
