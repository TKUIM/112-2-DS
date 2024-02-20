// A pig-latin translator using Associations.
// (c) 2001 duane a. bailey
import structure5.*;
/**
 * A simple pig-latin translator that makes use of Associations.
 *
 * @version $Id: atinLay.java 26 2006-08-24 14:29:13Z bailey $
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
        dict[0] = new Association<String,String>("a","aay");
        dict[1] = new Association<String,String>("bad","adbay");
        dict[2] = new Association<String,String>("had","adhay");
        dict[3] = new Association<String,String>("dad","adday");
        dict[4] = new Association<String,String>("day","ayday");
        dict[5] = new Association<String,String>("hop","ophay");
        dict[6] = new Association<String,String>("on","onay");
        dict[7] = new Association<String,String>("pop","oppay");
        dict[8] = new Association<String,String>("sad","adsay");

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
