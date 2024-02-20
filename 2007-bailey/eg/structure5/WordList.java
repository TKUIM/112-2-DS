import structure5.*;
/*
  public class WordList implements Structure
*/
public class WordList
{
    public WordList(int size)
    // pre: size >= 0
    // post: construct a word list capable of holding "size" words
    {
    }
    
    public boolean isEmpty()
    // post: return true iff the word list contains no words
    {
        return true;
    }

    public void add(String s)
    // post: add a word to the word list, if it is not already there
    {
    }
    
    public String selectAny()
    // pre: the word list is not empty
    // post: return a random word from the list
    {
        return "";
    }
    
    public void remove(String word)
    // pre: word is not null
    // post: remove the word from the word list
    {
    }

    public void vecHangman()
    {
        Vector<String> list;
        String targetWord;
        java.util.Random generator = new java.util.Random();
        
        list = new Vector<String>(10);
        list.add("clarify");
        list.add("entered");
        list.add("clerk");
        while (list.size() != 0)
        {
            {   // select a word from the list
                int index = Math.abs(generator.nextInt())%list.size();
                targetWord = (String)list.get(index);
            }
            // ... play the game using targetWord ...
            list.remove(targetWord);
        }
    }

    public static void hangman()
    {
        WordList list;                          // declaration
        String targetWord;
                
        list = new WordList(10);                // construction
        list.add("disambiguate");  // is this a word? how about ambiguate?
        list.add("inputted");      // really? what verbification!
        list.add("subbookkeeper"); // now that's coollooking!
        while (!list.isEmpty())                 // game loop
        {
            targetWord = list.selectAny();      // selection
            // ...play the game using target word...
            list.remove(targetWord);            // update
        }
    }
        
    public static void main(String[] args)
    {
        WordList list;
        list = new WordList(100);
        String s = "";
        list.add(s);
    }
}
class WordList2
{
    protected Vector theList;
    protected java.util.Random generator;
    
    /*
    public WordList(int n)
     */
    public WordList2(int n)
    {
        theList = new Vector(n);
        generator = new java.util.Random();
    }
    
    public String selectAny()
    {
        int i = Math.abs(generator.nextInt())%theList.size();
        return (String)theList.get(i);
    }   
}


