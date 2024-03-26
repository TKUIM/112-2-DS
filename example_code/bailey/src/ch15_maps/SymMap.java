import structure5.*;
import java.util.Scanner;

public class SymMap
{
    public static void main(String args[])
    {
        Map<String,String> table = new MapList<String,String>();
        Scanner s = new Scanner(System.in);
        String alias, name;

        // read in the alias-name database
        do
        {
            alias = s.next();
            if (!alias.equals("END"))
            {
                name = s.next();
                table.put(alias,name); // was called add, but may modify
            }
        } while (!alias.equals("END"));

        // enter the alias translation stage
        do
        {
            name = s.next();
            while (table.containsKey(name)) // was contains; more explicit
            {
                name = table.get(name); // translate alias
            }
            System.out.println(name);
        } while (s.hasNext());
    }
}

/*
three 3
one unity
unity 1
pi three
END

one
two
three
pi
1
two
3
3
*/
