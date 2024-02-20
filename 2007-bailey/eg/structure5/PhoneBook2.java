import structure5.*;

public class PhoneBook2
{
    
    public static void main(String args[])
    {
        PhoneEntry data[] = new PhoneEntry[30];
        ReadStream r = new ReadStream();
        int count = 0;
        while (!r.eof())
        {
            String name, title, building;
            int room, extension;
            name = r.readString()+" "+r.readString();
            title = r.readString();
            extension = r.readInt();
            room = r.readInt();
            building = r.readString();
            r.readLine();
            data[count++] = new PhoneEntry(name,title,extension,building,room);
        }
        insertionSort(data,count);
        for (int i = 0; i < count; i++)
        {
            System.out.println(data[i]);
        }
    }

    protected static void swap(PhoneEntry data[], int i, int j)
    // pre: 0 <= i,j < data.length
    // post: data[i] and data[j] are exchanged
    {
        PhoneEntry temp;
        temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void insertionSort(PhoneEntry data[], int n)
    // pre: n <= data.length
    // post: values in data[0..n-1] are in ascending order
    {
        int numSorted = 0;      // number of values in place
        int index;              // general index
        while (numSorted < n)
        {
            // take the first unsorted value
            PhoneEntry temp = data[numSorted];
            // ...and insert it among the sorted:
            for (index = numSorted; index > 0; index--)
            {
                if (temp.compareTo(data[index-1]) < 0)
                {
                    data[index] = data[index-1];
                } else {
                    break;
                }
            }
            // reinsert value
            data[index] = temp;
            numSorted++;
        }
    }
}

class PhoneEntry implements Comparable<PhoneEntry>
{
    String name;        // person's name
    String title;       // person's title
    int extension;      // telephone number
    String building;    // office building
    int room;           // number of room
    
    public PhoneEntry(String n, String t, int e,
                      String b, int r)
    // post: construct a new phone entry
    {
    /*
      ...
     */
        name = n;
        title = t;
        building = b;
        room = r;
        extension = e;
    }

    public int compareTo(PhoneEntry other)
    // pre: other is non-null
    // post: return integer reflecting relation between objects
    {
        Assert.pre(other instanceof PhoneEntry,
            "other implements phone entry");
        return this.extension - other.extension;
    }

    /*
    public int compareTo(PhoneEntry other)
    // pre: other is non-null
    // post: return integer reflecting relation between objects
    {
        Assert.pre(other instanceof PhoneEntry,
            "other implements phone entry");
        if (this.extension != other.extension)
            return this.extension - other.extension;
        else return this.name.compareTo(other.name);
    }
    */

    public String toString()
    {
        return name+" "+title+" "+extension+" "+room+" "+building;
    }
}

class PhoneVector extends Vector<PhoneEntry>
{
    protected void swap(int i, int j)
    // pre: 0 <= i,j < this.size
    // post: elements i and j are exchanged within the vector
    {
        PhoneEntry temp;
        temp = get(i);
        set(i,get(j));
        set(j,temp);
    }

    public void insertionSort()
    // post: values of vector are in ascending order
    {
        int numSorted = 0;      // number of values in place
        int index;              // general index
        while (numSorted < size())
        {
            // take the first unsorted value
            PhoneEntry temp = (PhoneEntry)get(numSorted);
            // ...and insert it among the sorted:
            for (index = numSorted; index > 0; index--)
            {
                if (temp.compareTo((PhoneEntry)get(index-1)) < 0)
                {
                    set(index,get(index-1));
                } else {
                    break;
                }
            }
            // reinsert value
            set(index,temp);
            numSorted++;
        }
    }
}

/*
Dicks Norman      Representative 55916 2467 Rayburn
Dunn Jennifer     Representative 57761  432 Cannon
Gorton Slade      Senator        43441  730 Hart
Hastings Doc      Representative 55816 1323 Longworth
McDermott Jim     Representative 53106 2349 Rayburn
Metcalf Jack      Representative 52605 1510 Longworth
Murray Patty      Senator        42621  111 Russell
Nethercutt George Representative 52006 1527 Longworth
Smith Adam        Representative 58901 1505 Longworth
Smith Linda       Representative 53526 1317 Longworth
White Rick        Representative 56311  116 Cannon
Murray Patty Senator 42621 111 Russell
Gorton Slade Senator 43441 730 Hart
Nethercutt George Representative 52006 1527 Longworth
Metcalf Jack Representative 52605 1510 Longworth
McDermott Jim Representative 53106 2349 Rayburn
Smith Linda Representative 53526 1317 Longworth
Hastings Doc Representative 55816 1323 Longworth
Dicks Norman Representative 55916 2467 Rayburn
White Rick Representative 56311 116 Cannon
Dunn Jennifer Representative 57761 432 Cannon
Smith Adam Representative 58901 1505 Longworth
 */
