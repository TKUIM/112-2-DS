// A trivial application.
// Save this application under a different name, and change the name of the class below.
import structure5.*;
import java.util.Iterator;
public class Circuit
{
    static PriorityQueue<Event> eventQueue; // main event queue

    public static double simulate()
    // post: run simulation until event queue is empty;
    //       returns final clock time
    {
        double low = 0.0;       // voltage of low logic
        double high = 3.0;      // voltage of high logic
        double clock = 0.0;
        while (!eventQueue.isEmpty())
        {   // remove next event
            Event e = eventQueue.remove();
            //System.out.println(e);
            // int i = eventQueue.size();
            // keep track of time
            clock = e.time;
            // simulate the event
            e.go();
            //int j = eventQueue.size()-i;
            //if (j != 0)
            //System.out.println(j+" events added");
        }
        System.out.println("-- circuit stable after "+clock+" ns --");
        return clock;
    }
    public static void main(String[] args)
    {
        int low = 0;    // voltage of low logic
        int high = 3;   // voltage of high logic
        eventQueue = new SkewHeap<Event>();
        double time;

        // set up circuit
        Inverter not = new Inverter(0.2);
        And and = new And(0.8);
        Probe output = new Probe("output");
        Source input = new Source("input",not.pin(1));

        input.connectTo(and.pin(2));
        not.connectTo(and.pin(1));
        and.connectTo(output.pin(1));
                
        // simulate circuit
        time = simulate();
        input.set(time+1.0,0,high); // first: set input high
        time = simulate();
        input.set(time+1.0,0,low);  // later: set input low
        time = simulate();
        input.set(time+1.0,0,high); // later: set input high
        time = simulate();
        input.set(time+1.0,0,low);  // later: set input low
        simulate();
    }
}

class Connection
{
    protected Component gate;
    protected int pin;
    public Connection(Component gate, int pin)
    {
        this.gate = gate;
        this.pin = pin;
    }
    public Component component()
    {
        return gate;
    }
    public int pin()
    {
        return this.pin;
    }
}

class Event implements Comparable<Event>
{
    protected double time;    // time of event
    protected int level;      // voltage level
    protected Connection c;   // gate/pin

    public Event(Connection c, double t, int l)
    // pre: c is a valid pin on a gate
    // post: constructs event for time t to set pin to level l
    {
        this.c = c;
        time = t;
        level = l;
    }

    public void go()
    // post: informs target component of updated logic on pin
    {
        c.component().set(time,c.pin(),level);
    }
        
    public int compareTo(Event other)
    // pre: other is non-null
    // post: returns integer representing relation between values
    {
        Event that = (Event)other;
        if (this.time < that.time) return -1;
        else if (this.time == that.time) return 0;
        else return 1;
    }

    public String toString()
    {
        return (time+" ns: voltage="+level+" connection"+c.component()+":"+c.pin());
    }
}

abstract class Component
{
    int pin[];
    List<Connection> neighbors;
    double delay;
    public Component(double delay, int degree)
    {
        this.delay = delay;
        pin = new int[degree];
        for (int i = 0; i < degree; i++)
        {
            pin[i] = -1;
        }
        neighbors = new SinglyLinkedList<Connection>();
    }
    public Connection pin(int i)
    {
        return new Connection(this,i);
    }
    abstract public void set(double t, int pinNum, int level);

    public void update(double t)
    {
        for (Connection c : neighbors) 
        {
            Circuit.eventQueue.add(new Event(c,t,pin[0]));
        }
    }
    public void connectTo(Connection c)
    {
        neighbors.add(c);
    }
}

class Wire extends Component
{
    public Wire(double delay)
    {
        super(delay,2);
    }
    public void set(double t, int pinNum, int level)
    {
        if (pin[0] != -1 && pin[1] == level) return;
        pin[1] = pin[0] = level;
        update(t+delay);
    }
}

class Inverter extends Component
{
    public Inverter(double delay)
    {
        super(delay,3);
    }
    public void set(double t, int pinNum, int level)
    {
        if (pin[1] == level) return;
        pin[1] = level;
        if (level == 0)
        {
            pin[0] = 3;
        } else {
            pin[0] = 0;
        }
        update(t+delay);
    }
}

class Nor extends Component
{
    public Nor(double delay)
    {
        super(delay,3);
    }
    public void set(double t, int pinNum, int level)
    {
        if (pin[pinNum] == level) return;
        pin[pinNum] = level;
        if (pin[1] == 0 && pin[2] == 0)
        {
            pin[0] = 3;
        } else {
            pin[0] = 0;
        }
        update(t+delay);
    }
}

class And extends Component
{
    public And(double delay)
    // pre: delay >= 0.0ns
    // post: constructs and gate with indicated gate delay
    {
        /*
        ...
        */
        super(delay,3);
    }

    public void set(double time, int pinNum, int level)
    // pre: pinNum = 1 or 2, level = 0/3
    // post: updates inputs and generates events on
    //       devices connected to output
    {
        // no change on input: do nothing
        if (pin[pinNum] == level) return;

        // update input
        pin[pinNum] = level;

        // output true iff both inputs true
        if (pin[1] == 3 && pin[2] == 3)
        {
            pin[0] = 3;
        } else {
            pin[0] = 0;
        }

        // neighbors is a list of gates connected
        // to our output
        for (Connection c : neighbors) 
        {
            // enqueue new event on one of our neighbors
            Circuit.eventQueue.add(new Event(c,time+delay,pin[0]));
        }
    }
}



class Probe extends Component
{
    String name;
    public Probe(String name)
    {
        super(0,1);
        this.name = name;
    }
    public void set(double t, int pinNum, int level)
    {
        if (pin[0] == level) return;
        pin[0] = level;
        System.out.println(t+" ns: "+name+" now "+pin[0]+" volts");
    }
    public String toString()
    {
        return name;
    }

}

class Source extends Component
{
    String name;
    public Source(String name, Connection c)
    {
        super(0,1);
        this.name = name;
        neighbors.add(c);
        pin[0] = 0;
        update(0.0);
    }
    public void set(double t, int pinNum, int level)
    {
        pin[0] = level;
        System.out.println(t+" ns: "+name+" set to "+pin[0]+" volts");
        update(t);
    }
    public void toggle(double t)
    {
        if (pin[0] == 0)
        {
            set(t,0,3);
        } else {
            set(t,0,0);
        }
    }
}
/*
1.0 ns: output now 0 volts
-- circuit stable after 1.0 ns --
2.0 ns: input set to 3 volts
2.8 ns: output now 3 volts
3.0 ns: output now 0 volts
-- circuit stable after 3.0 ns --
4.0 ns: input set to 0 volts
-- circuit stable after 5.0 ns --
6.0 ns: input set to 3 volts
6.8 ns: output now 3 volts
7.0 ns: output now 0 volts
-- circuit stable after 7.0 ns --
8.0 ns: input set to 0 volts
-- circuit stable after 9.0 ns --
*/
