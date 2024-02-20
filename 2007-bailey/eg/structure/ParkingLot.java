import structure.*;
import java.util.Scanner;
public class ParkingLot
{
    public static void main(String[] args)
    {
        List free = new SinglyLinkedList();  // available
        List rented = new SinglyLinkedList(); // rented spaces
        for (int number = 0; number < 10; number++) 
        {
            if (number < 3) // three small spaces
                free.add(new Space(number,Space.COMPACT));
            else if (number < 9) // six medium spaces
                free.add(new Space(number,Space.MINIVAN));
            else // one large space
                free.add(new Space(number,Space.TRUCK));
        }
        Scanner s = new Scanner(System.in);
        while (s.hasNext())
        {
            String command = s.next(); // rent/return
            /*
              ...
            */
            Space location;
            if (command.equals("rent"))
            {   // attempt to rent a parking space
                String size = s.next();
                Space request;
                if (size.equals("small")) 
                    request = new Space(0,Space.COMPACT);
                else if (size.equals("medium")) 
                    request = new Space(0,Space.MINIVAN);
                else request = new Space(0,Space.TRUCK);
                // check free list for appropriate-sized space
                if (free.contains(request)) 
                {   // a space is available
                    location = (Space)free.remove(request);
                    String renter = s.next(); // to whom?
                    // link renter with space description
                    rented.add(new Association(renter,location));
                    System.out.println("Space "+location.number+" rented.");
                } else {
                    System.out.println("No space available. Sorry.");
                }
            }
            else
            if (command.equals("return")){
                String renter = s.next(); // from whom?
                // template for finding "rental contract"
                Association query = new Association(renter);
                if (rented.contains(query))
                {   // contract found
                    Association contract =
                        (Association)rented.remove(query);
                    location = (Space)contract.getValue(); // where?
                    free.add(location); // put in free list
                    System.out.println("Space "+location.number+" is now free.");
                } else {
                    System.out.println("No space rented to "+renter);
                }
            }
            else break;
        }
        System.out.println(free.size()+" slots remain available.");
    }
}

class Space
{   // structure describing parking space
    public final static int COMPACT = 0; // small space
    public final static int MINIVAN = 1; // medium space
    public final static int TRUCK = 2;   // large space
    protected int number;       // address in parking lot
    protected int size;         // size of space
    public Space(int n, int s)
    // post: construct parking space #n, size s
    {
        number = n;
        size = s;
    }
    public boolean equals(Object other)
    // pre: other is not null
    // post: true iff spaces are equivalent size
    {
        Space that = (Space)other;
        return this.size == that.size;
    }
}


/*
 Interaction
   rent small Alice
Space 0 rented.
   rent large Bob
Space 9 rented.
   rent small Carol
Space 1 rented.
   return Alice
Space 0 is now free.
   return David
No space rented to David
   rent small David
Space 2 rented.
   rent small Eva
Space 0 rented.
   quit
6 slots remain available.
*/

/*
 Input:
rent small Alice
rent large Bob
rent small Carol
return Alice
return David
rent small David
rent small Eva
quit
*/

/*
 Output:
Space 0 rented.
Space 9 rented.
Space 1 rented.
Space 0 is now free.
No space rented to David
Space 2 rented.
Space 0 rented.
6 slots remain available.
*/
