import structure.*;

public class crawler
{
    public static void main(String args[])
    {
        Queue todo = new QueueList();
        Vector seen = new Vector();
        String first = "http://www.cs.williams.edu/~bailey/JavaStructures";
        if (args.length > 0) first = args[0];
        /*
        HTML p = new HTML("http://www.yahoo.com");
        */
        HTML p = new HTML("http://www.yahoo.com",20*1024);
        String data = p.content();

        todo.add(new Association(first,new Integer(0)));
        while (!todo.isEmpty())
        {
            Association a = (Association)todo.remove();
            String url = (String)a.getKey();
            int depth = ((Integer)a.getValue()).intValue();
            if (!seen.contains(url))
            {
                p = new HTML(url);
                Integer d = new Integer(depth+1);
                System.out.println("depth "+depth+": "+url);
                seen.addElement(url);
                while (p.hasNext())
                {
                    String l = p.nextURL();
                    if (l.startsWith(first) &&
                        !seen.contains(l))
                    {
                        todo.add(new Association(l,d));
                    }
                }
            }
        }
    }
}
