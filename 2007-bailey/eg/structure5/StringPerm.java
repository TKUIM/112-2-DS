import structure5.*;
import java.util.Iterator;
public class StringPerm implements Iterator<String>
{
    protected Iterator t;
    protected String theString;

    public void remove()
    {}

    public StringPerm(String s)
    {
        t = new Perm2(s.length());
        theString = s;
    }

    public boolean hasNext()
    {
        return t.hasNext();
    }

    public String next()
    {
        String result = "";
        int shuffle[] = (int[])t.next();

        for (int i = 0; i < theString.length(); i++)
        {
            result += theString.charAt(shuffle[i]);
        }

        return result;
    }

    public static void main(String[] args)
    {
        java.util.Iterator<String> i = new StringPerm(args[0]);
        while (i.hasNext())
        {
            System.out.println(i.next());
        }
    }
}
/*
java StringPerm wowza
wowza
wowaz
wozwa
wozaw
woawz
woazw
wwoza
wwoaz
wwzoa
wwzao
wwaoz
wwazo
wzowa
wzoaw
wzwoa
wzwao
wzaow
wzawo
waowz
waozw
wawoz
wawzo
wazow
wazwo
owwza
owwaz
owzwa
owzaw
owawz
owazw
owwza
owwaz
owzwa
owzaw
owawz
owazw
ozwwa
ozwaw
ozwwa
ozwaw
ozaww
ozaww
oawwz
oawzw
oawwz
oawzw
oazww
oazww
wwoza
wwoaz
wwzoa
wwzao
wwaoz
wwazo
wowza
wowaz
wozwa
wozaw
woawz
woazw
wzwoa
wzwao
wzowa
wzoaw
wzawo
wzaow
wawoz
wawzo
waowz
waozw
wazwo
wazow
zwowa
zwoaw
zwwoa
zwwao
zwaow
zwawo
zowwa
zowaw
zowwa
zowaw
zoaww
zoaww
zwwoa
zwwao
zwowa
zwoaw
zwawo
zwaow
zawow
zawwo
zaoww
zaoww
zawwo
zawow
awowz
awozw
awwoz
awwzo
awzow
awzwo
aowwz
aowzw
aowwz
aowzw
aozww
aozww
awwoz
awwzo
awowz
awozw
awzwo
awzow
azwow
azwwo
azoww
azoww
azwwo
azwow
*/
