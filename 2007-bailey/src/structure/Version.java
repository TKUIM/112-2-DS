// The version of this release.
// (c) 2001 duane a. bailey
package structure;
/**
 * A utility class that can be used to determine the version of software
 * currently being used.
 */
public class Version
{
    public final static int major = 2;
    public final static int minor = 9120000;
    public final static String name = "structure";
    public final static int year = 2001;
    public final static String author = "duane a. bailey";
    public final static String info = "package "+name+", version "+major+"."+minor+", (c) "+year+" "+author;

    public static void main(String args[])
    {
        System.out.println(info);
    }
}
