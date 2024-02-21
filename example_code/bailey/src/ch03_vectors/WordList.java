/* 
 * WordList.java
 * 
 * 展示如何使用Vector來儲存字串，完成Hangman遊戲
 * Hangman遊戲的規則是：
    不斷從單字列表中隨機選擇一個單字，玩家要猜這個單字是什麼，然後刪除猜過單字

> java  -cp bailey.jar ch03_vector.WordList
Guess the word: clarify
Guess the word: entered
Guess the word: clerk
*/
package ch03_vectors;
import structure5.Vector;

/*
  public class WordList implements Structure
  待完成，利用structure5.Vector容器完成單字清單的功能
*/
public class WordList
{
    // 建構一個空的單字清單，容量為size
    public WordList(int size)
    // pre: size >= 0
    // post: construct a word list capable of holding "size" words
    {
    }
    
    // 測試單字清單是否為空
    public boolean isEmpty()
    // post: return true iff the word list contains no words
    {
        return true;
    }

    // 加入一個字串s到單字清單中
    public void add(String s)
    // post: add a word to the word list, if it is not already there
    {
    }
    
    // 從單字清單隨機挑選回傳一個字串
    public String selectAny()
    // pre: the word list is not empty
    // post: return a random word from the list
    {
        return "";
    }
    
    // 從單字清單中移除一個字串 word
    public void remove(String word)
    // pre: word is not null
    // post: remove the word from the word list
    {
    }

    // 範例，使用Vector容器的Hangman遊戲程式
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
            System.out.printf("Guess the word: %s\n", targetWord);
            
            list.remove(targetWord);
        }
    }

    // 範例，使用WordList容器的Hangman遊戲程式
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
            System.out.printf("Guess the word: %s\n", targetWord);
 
            list.remove(targetWord);            // update
        }
    }
    
    // 局部範例，使用WordList容器的Hangman遊戲程式
    public static void main(String[] args)
    {
        WordList list;
        list = new WordList(100);
        String s = "";
        list.add(s);
        
        list.vecHangman();
    }
}

// 局部範例，利用structure5.Vector容器完成單字清單的功能
class WordList2
{
    protected Vector theList;  // 單字清單
    protected java.util.Random generator;  // 亂數產生器
    
    /*
    public WordList(int n)
     */
    // 建構一個空的單字清單，容量為size
    public WordList2(int n)
    {
        theList = new Vector(n);
        generator = new java.util.Random();
    }
    
    // 從單字清單隨機挑選回傳一個字串
    public String selectAny()
    {
        int i = Math.abs(generator.nextInt())%theList.size();
        return (String)theList.get(i);
    }   
}

