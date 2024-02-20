import structure.*;
import java.util.Scanner;

public class SymMap
{
    public static void main(String args[])
    {
        Map table = new MapList();
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
        System.out.println("Table contains aliases: "+
                           table.keySet());
        // enter the alias translation stage
        do
        {
            name = s.next();
            while (table.containsKey(name)) // was contains; more explicit
            {
                name = (String)table.get(name); // translate alias
            }
            System.out.println(name);
        } while (s.hasNext());
    }
}
