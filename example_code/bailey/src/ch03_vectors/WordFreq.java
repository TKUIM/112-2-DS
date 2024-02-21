/* 
 * WordFreq.java
 * 
 * 展示如何使用Vector容器儲存(字串,整數)配對，統計單字出現次數

 * 
> java  -cp bailey.jar ch03_vector.WordFreq
four score and seven years ago our fathers brought forth on this
continent a new nation conceived in liberty and dedicated to the
proposition that all men are created equal now we are engaged in a
great civil war testing whether that nation or any nation so conceived
and so dedicated can long endure we are met on a great battlefield of
that war we have come to dedicate a portion of that field as a final
resting place for those who here gave their lives that that nation
might live it is altogether fitting and proper that we should do this
but in a larger sense we cannot dedicate we cannot consecrate we
cannot hallow this ground the brave men living and dead who struggled
here have consecrated it far above our poor power to add or detract
the world will little note nor long remember what we say here but it
can never forget what they did here it is for us the living rather to
be dedicated here to the unfinished work which they who fought here
have thus far so nobly advanced it is rather for us to be here
dedicated to the great task remaining before us that from these
honored dead we take increased devotion to that cause for which they
gave the last full measure of devotion that we here highly resolve
that these dead shall not have died in vain that this nation under God
shall have a new birth of freedom and that government of the people by
the people for the people shall not perish from the earth EOF

four 出現 1 次.
score 出現 1 次.
and 出現 6 次.
seven 出現 1 次.
years 出現 1 次.
ago 出現 1 次.
our 出現 2 次.
fathers 出現 1 次.
brought 出現 1 次.
forth 出現 1 次.
on 出現 2 次.
this 出現 4 次.
continent 出現 1 次.
a 出現 7 次.
new 出現 2 次.
nation 出現 5 次.
conceived 出現 2 次.
in 出現 4 次.
liberty 出現 1 次.
dedicated 出現 4 次.
to 出現 8 次.
the 出現 11 次.
proposition 出現 1 次.
that 出現 13 次.
all 出現 1 次.
men 出現 2 次.
are 出現 3 次.
created 出現 1 次.
equal 出現 1 次.
now 出現 1 次.
we 出現 10 次.
engaged 出現 1 次.
great 出現 3 次.
civil 出現 1 次.
war 出現 2 次.
testing 出現 1 次.
whether 出現 1 次.
or 出現 2 次.
any 出現 1 次.
so 出現 3 次.
can 出現 2 次.
long 出現 2 次.
endure 出現 1 次.
met 出現 1 次.
battlefield 出現 1 次.
of 出現 5 次.
have 出現 5 次.
come 出現 1 次.
dedicate 出現 2 次.
portion 出現 1 次.
field 出現 1 次.
as 出現 1 次.
final 出現 1 次.
resting 出現 1 次.
place 出現 1 次.
for 出現 5 次.
those 出現 1 次.
who 出現 3 次.
here 出現 8 次.
gave 出現 2 次.
their 出現 1 次.
lives 出現 1 次.
might 出現 1 次.
live 出現 1 次.
it 出現 5 次.
is 出現 3 次.
altogether 出現 1 次.
fitting 出現 1 次.
proper 出現 1 次.
should 出現 1 次.
do 出現 1 次.
but 出現 2 次.
larger 出現 1 次.
sense 出現 1 次.
cannot 出現 3 次.
consecrate 出現 1 次.
hallow 出現 1 次.
ground 出現 1 次.
brave 出現 1 次.
living 出現 2 次.
dead 出現 3 次.
struggled 出現 1 次.
consecrated 出現 1 次.
far 出現 2 次.
above 出現 1 次.
poor 出現 1 次.
power 出現 1 次.
add 出現 1 次.
detract 出現 1 次.
world 出現 1 次.
will 出現 1 次.
little 出現 1 次.
note 出現 1 次.
nor 出現 1 次.
remember 出現 1 次.
what 出現 2 次.
say 出現 1 次.
never 出現 1 次.
forget 出現 1 次.
they 出現 3 次.
did 出現 1 次.
us 出現 3 次.
rather 出現 2 次.
be 出現 2 次.
unfinished 出現 1 次.
work 出現 1 次.
which 出現 2 次.
fought 出現 1 次.
thus 出現 1 次.
nobly 出現 1 次.
advanced 出現 1 次.
task 出現 1 次.
remaining 出現 1 次.
before 出現 1 次.
from 出現 2 次.
these 出現 2 次.
honored 出現 1 次.
take 出現 1 次.
increased 出現 1 次.
devotion 出現 2 次.
cause 出現 1 次.
last 出現 1 次.
full 出現 1 次.
measure 出現 1 次.
highly 出現 1 次.
resolve 出現 1 次.
shall 出現 3 次.
not 出現 2 次.
died 出現 1 次.
vain 出現 1 次.
under 出現 1 次.
God 出現 1 次.
birth 出現 1 次.
freedom 出現 1 次.
government 出現 1 次.
people 出現 3 次.
by 出現 1 次.
perish 出現 1 次.
earth 出現 1 次.
*/
package ch03_vectors;
import structure5.Vector;
import structure5.Association;
import java.util.Scanner;

public class WordFreq
{
    public static void main(String args[])
    {
        // 建立字典，以配對物件(單字配次數)為元素的向量容器
        Vector<Association<String,Integer>> vocab = new Vector<Association<String,Integer>>(1000);
        
        Scanner s = new Scanner(System.in);
        int i;
          
        // for each word on input
        while (s.hasNext())
        {
            Association<String,Integer> wordInfo; // word-frequency association
            String vocabWord;     // word in the list

            // read in and tally instance of a word
            String word = s.next();
            if(word.equals("EOF"))
            {
                break;  // 遇EOF表示結束
            }
            // System.out.printf("word: <%s>\n", word);
            
            // 窮舉法查字典，若有word單字，單字配對的次數加1
            for (i = 0; i < vocab.size(); i++)
            {
                // get the association 取配對
                wordInfo = vocab.get(i);
                // get the word from the association  取配對的單字
                vocabWord = wordInfo.getKey();
                if (vocabWord.equals(word))  // 若查到
                {   // match: increment integer in association 增加單字的次數
                    Integer f = wordInfo.getValue();
                    wordInfo.setValue(wordInfo.getValue()+1);
                    break;
                }
            }
            
            // mismatch: add new word, frequency 1.
            // 若無word單字，單字加入字典vocab，配對的次數為1
            if (i == vocab.size())
            {
                vocab.add(new Association<String,Integer>(word,1));
            }
            
            // System.out.printf("vocab: %s\n", vocab);
        }
        
        // print out the accumulated word frequencies
        // 列印累計的單字出現次數
        for (i = 0; i < vocab.size(); i++)
        {
            Association<String,Integer> wordInfo = vocab.get(i);
            System.out.println(
               wordInfo.getKey()+" 出現 "+
               wordInfo.getValue()+" 次.");
        }
    }
}
/*
four score and seven years ago our fathers brought forth on this
continent a new nation conceived in liberty and dedicated to the
proposition that all men are created equal now we are engaged in a
great civil war testing whether that nation or any nation so conceived
and so dedicated can long endure we are met on a great battlefield of
that war we have come to dedicate a portion of that field as a final
resting place for those who here gave their lives that that nation
might live it is altogether fitting and proper that we should do this
but in a larger sense we cannot dedicate we cannot consecrate we
cannot hallow this ground the brave men living and dead who struggled
here have consecrated it far above our poor power to add or detract
the world will little note nor long remember what we say here but it
can never forget what they did here it is for us the living rather to
be dedicated here to the unfinished work which they who fought here
have thus far so nobly advanced it is rather for us to be here
dedicated to the great task remaining before us that from these
honored dead we take increased devotion to that cause for which they
gave the last full measure of devotion that we here highly resolve
that these dead shall not have died in vain that this nation under God
shall have a new birth of freedom and that government of the people by
the people for the people shall not perish from the earth EOF
four occurs 1 times.
score occurs 1 times.
and occurs 6 times.
seven occurs 1 times.
years occurs 1 times.
ago occurs 1 times.
our occurs 2 times.
fathers occurs 1 times.
brought occurs 1 times.
forth occurs 1 times.
on occurs 2 times.
this occurs 4 times.
continent occurs 1 times.
a occurs 7 times.
new occurs 2 times.
nation occurs 5 times.
conceived occurs 2 times.
in occurs 4 times.
liberty occurs 1 times.
dedicated occurs 4 times.
to occurs 8 times.
the occurs 11 times.
proposition occurs 1 times.
that occurs 13 times.
all occurs 1 times.
men occurs 2 times.
are occurs 3 times.
created occurs 1 times.
equal occurs 1 times.
now occurs 1 times.
we occurs 10 times.
engaged occurs 1 times.
great occurs 3 times.
civil occurs 1 times.
war occurs 2 times.
testing occurs 1 times.
whether occurs 1 times.
or occurs 2 times.
any occurs 1 times.
so occurs 3 times.
can occurs 2 times.
long occurs 2 times.
endure occurs 1 times.
met occurs 1 times.
battlefield occurs 1 times.
of occurs 5 times.
have occurs 5 times.
come occurs 1 times.
dedicate occurs 2 times.
portion occurs 1 times.
field occurs 1 times.
as occurs 1 times.
final occurs 1 times.
resting occurs 1 times.
place occurs 1 times.
for occurs 5 times.
those occurs 1 times.
who occurs 3 times.
here occurs 8 times.
gave occurs 2 times.
their occurs 1 times.
lives occurs 1 times.
might occurs 1 times.
live occurs 1 times.
it occurs 5 times.
is occurs 3 times.
altogether occurs 1 times.
fitting occurs 1 times.
proper occurs 1 times.
should occurs 1 times.
do occurs 1 times.
but occurs 2 times.
larger occurs 1 times.
sense occurs 1 times.
cannot occurs 3 times.
consecrate occurs 1 times.
hallow occurs 1 times.
ground occurs 1 times.
brave occurs 1 times.
living occurs 2 times.
dead occurs 3 times.
struggled occurs 1 times.
consecrated occurs 1 times.
far occurs 2 times.
above occurs 1 times.
poor occurs 1 times.
power occurs 1 times.
add occurs 1 times.
detract occurs 1 times.
world occurs 1 times.
will occurs 1 times.
little occurs 1 times.
note occurs 1 times.
nor occurs 1 times.
remember occurs 1 times.
what occurs 2 times.
say occurs 1 times.
never occurs 1 times.
forget occurs 1 times.
they occurs 3 times.
did occurs 1 times.
us occurs 3 times.
rather occurs 2 times.
be occurs 2 times.
unfinished occurs 1 times.
work occurs 1 times.
which occurs 2 times.
fought occurs 1 times.
thus occurs 1 times.
nobly occurs 1 times.
advanced occurs 1 times.
task occurs 1 times.
remaining occurs 1 times.
before occurs 1 times.
from occurs 2 times.
these occurs 2 times.
honored occurs 1 times.
take occurs 1 times.
increased occurs 1 times.
devotion occurs 2 times.
cause occurs 1 times.
last occurs 1 times.
full occurs 1 times.
measure occurs 1 times.
highly occurs 1 times.
resolve occurs 1 times.
shall occurs 3 times.
not occurs 2 times.
died occurs 1 times.
vain occurs 1 times.
under occurs 1 times.
God occurs 1 times.
birth occurs 1 times.
freedom occurs 1 times.
government occurs 1 times.
people occurs 3 times.
by occurs 1 times.
perish occurs 1 times.
earth occurs 1 times.
*/
