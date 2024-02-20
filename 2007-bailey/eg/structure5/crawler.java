import structure5.*;

public class crawler
{
    public static void main(String args[])
    {
        Queue<Association<String,Integer>> todo = new QueueList<Association<String,Integer>>();
        Vector<String> seen = new Vector<String>();
        String first = "http://www.cs.williams.edu/~bailey/JavaStructures";
        if (args.length > 0) first = args[0];
        /*
        HTML p = new HTML("http://www.yahoo.com");
        */
        HTML p = new HTML("http://www.yahoo.com",20*1024);
        String data = p.content();

        todo.add(new Association<String,Integer>(first,0));
        while (!todo.isEmpty())
        {
            Association<String,Integer> a = todo.remove();
            String url = a.getKey();
            int depth = a.getValue();
            if (!seen.contains(url))
            {
                p = new HTML(url);
                Integer d = depth+1;
                System.out.println("depth "+depth+": "+url);
                seen.addElement(url);
                while (p.hasNext())
                {
                    String l = p.nextURL();
                    if (l.startsWith(first) &&
                        !seen.contains(l))
                    {
                        todo.add(new Association<String,Integer>(l,d));
                    }
                }
            }
        }
    }
}
