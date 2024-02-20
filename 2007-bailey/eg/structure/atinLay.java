// A pig-latin translator using Associations.
// (c) 2001 duane a. bailey
import structure.*;
/**
 * A simple pig-latin translator that makes use of Associations.
 *
 * @version $Id: atinLay.java 8 2006-08-02 19:03:11Z bailey $
 * @author, 2001 duane a. bailey
 *
 */
public class atinLay {
    // a pig latin translator for nine words
    /**
     * @param args 
     */
    public static void main(String args[])
    {
        // build and fill out an array of nine translations
        Association dict[] = new Association[9];
        dict[0] = new Association("a","aay");
        dict[1] = new Association("bad","adbay");
        dict[2] = new Association("had","adhay");
        dict[3] = new Association("dad","adday");
        dict[4] = new Association("day","ayday");
        dict[5] = new Association("hop","ophay");
        dict[6] = new Association("on","onay");
        dict[7] = new Association("pop","oppay");
        dict[8] = new Association("sad","adsay");

        for (int argn = 0; argn < args.length; argn++)
        {   // for each argument
            for (int dictn = 0; dictn < dict.length; dictn++)
            {   // check each dictionary entry
                if (dict[dictn].getKey().equals(args[argn]))
                    System.out.println(dict[dictn].getValue());
            }
        }
    }
}
/*
java atinLay hop on pop
ophay
onay
oppay
*/
