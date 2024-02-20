// (c) 2001 duane a. bailey
import structure.Vector;
/**
 * Help with estimating the capabilities of a computer.
 */
public class Timing
{
    final static double NANOSPERMILLI=1000000.0;
    final static int lowDur=50;
    final static int highDur=500;
    static double loopSpeed;
    static double asgnSpeed;
    static double aaSpeed;
    static double vaSpeed;
    static double addSpeed;
    static double mulSpeed;
    public static void speed0()
    {
        int i, loops;
        double speed;
        loops = 10000000;
        long start,stop,duration;

        start = System.currentTimeMillis();
        for (i = 0; i < loops; i++)
        {
            // code to be timed goes here
        }
        stop = System.currentTimeMillis();

        duration = stop-start;
        System.out.println("# Elapsed time: "+duration+"ms");
        System.out.println("# Mean time: "+
                           (((double)duration)/loops*NANOSPERMILLI)+
                           "nanoseconds");
        /*
        System.out.println("# Elapsed time: "+duration+"ms");
        System.out.println("# Mean time: "+
                           (((double)duration)/loops*NANOSPERMILLI)+
                           "nanoseconds");
        */
    }

    public static void speed()
    {
        int i, loops, trial;
        double speed;
        loops = 10000;
        loopSpeed = 1000000000.0;
        long start,stop,duration;
        for (trial = 0; trial < 10; trial++)
        {
            do
            {
                start = System.currentTimeMillis();
                for (i = 0; i < loops; i++)
                {
                    // code to be timed goes here
                }
                stop = System.currentTimeMillis();
                duration = stop-start;
                if (duration < lowDur) loops *= 2;
                if (duration > highDur) loops /= 2;
            } while (duration < lowDur || duration > highDur);
            speed = duration/(double)loops*NANOSPERMILLI;
            if (speed < loopSpeed) loopSpeed = speed;
        }
    }

    public static void speed2()
    {
        long start,stop,duration;
        int i, loops, trial,k;
        double speed;
        loops = 10000;
        asgnSpeed = 1000000000.0;
        for (trial = 0; trial < 10; trial++)
        {
            do
            {
                start = System.currentTimeMillis();
                for (i = 0; i < loops; i++) k = 42;
                stop = System.currentTimeMillis();
                duration = stop-start;
                if (duration < lowDur) loops *= 2;
                if (duration > highDur) loops /= 2;
            } while (duration < lowDur || duration > highDur);
            speed = duration/(double)loops*NANOSPERMILLI - loopSpeed;
            if (speed < asgnSpeed) asgnSpeed = speed;
        }
    }

    public static void speed3()
    {
        long start,stop,duration;
        int i, loops, trial,k;
        double speed;
        loops = 10000;
        aaSpeed = 1000000000.0;
        int v[] = new int[3];
        for (trial = 0; trial < 10; trial++)
        {
            do
            {
                start = System.currentTimeMillis();
                for (i = 0; i < loops; i++) v[0] = 42;
                stop = System.currentTimeMillis();
                duration = stop-start;
                if (duration < lowDur) loops *= 2;
                if (duration > highDur) loops /= 2;
            } while (duration < lowDur || duration > highDur);
            speed = duration/(double)loops*NANOSPERMILLI - loopSpeed;
            if (speed < aaSpeed) aaSpeed = speed;
        }
    }

    public static void speed4()
    {
        long start,stop,duration;
        int i, loops, trial,k;
        double speed;
        loops = 10000;
        vaSpeed = 1000000000.0;
        Vector v = new Vector(3);
        Integer in = new Integer(10);
        v.add(in);
        for (trial = 0; trial < 10; trial++)
        {
            do
            {
                start = System.currentTimeMillis();
                for (i = 0; i < loops; i++)
                {
                    v.setElementAt(in,0);
                }
                stop = System.currentTimeMillis();
                duration = stop-start;
                if (duration < lowDur) loops *= 2;
                if (duration > highDur) loops /= 2;
            } while (duration < lowDur || duration > highDur);
            speed = duration/(double)loops*NANOSPERMILLI - loopSpeed;
            if (speed < vaSpeed) vaSpeed = speed;
        }
    }

    public static void speed5()
    {
        long start,stop,duration;
        int i, loops, trial,k;
        double speed;
        loops = 10000;
        addSpeed = 1000000000.0;
        k=1;
        for (trial = 0; trial < 10; trial++)
        {
            do
            {
                start = System.currentTimeMillis();
                for (i = 0; i < loops; i++) {k = k + 9;}
                stop = System.currentTimeMillis();
                duration = stop-start;
                if (duration < lowDur) loops *= 2;
                if (duration > highDur) loops /= 2;
            } while (duration < lowDur || duration > highDur);
            speed = duration/(double)loops*NANOSPERMILLI - loopSpeed;
            if (speed < addSpeed) addSpeed = speed;
        }
    }
    public static void speed6()
    {
        long start,stop,duration;
        int i, loops, trial,k;
        double speed;
        loops = 10000;
        mulSpeed = 1000000000.0;
        k=1;
        for (trial = 0; trial < 10; trial++)
        {
            do
            {
                start = System.currentTimeMillis();
                for (i = 0; i < loops; i++) {k = k * 9;}
                stop = System.currentTimeMillis();
                duration = stop-start;
                if (duration < lowDur) loops *= 2;
                if (duration > highDur) loops /= 2;
            } while (duration < lowDur || duration > highDur);
            speed = duration/(double)loops*NANOSPERMILLI - loopSpeed;
            if (speed < mulSpeed) mulSpeed = speed;
        }
    }

    public static void main(String[] args)
    {
        speed();
        speed2();
        speed3();
        speed4();
        speed5();
        speed6();
        speed0();
        System.out.println("# loop="+loopSpeed+" nsec per loop.");
        System.out.println("# assignment="+asgnSpeed+" nsec per loop.");
        System.out.println("# array assignment="+aaSpeed+" nsec per loop.");
        System.out.println("# vector assignment="+vaSpeed+" nsec per loop.");
        System.out.println("# add="+addSpeed+" nsec per loop.");
        System.out.println("# mul="+mulSpeed+" nsec per loop.");
        //
        System.out.println("# loop="+loopSpeed/asgnSpeed+" assignments per loop.");
        System.out.println("# assignment=1 assignment per loop.");
        System.out.println("# array assignment="+aaSpeed/asgnSpeed+" assignment per loop.");
        System.out.println("# vector assignment="+vaSpeed/asgnSpeed+" assignment per loop.");
        System.out.println("# add="+addSpeed/asgnSpeed+" assignment per loop.");
        System.out.println("# mul="+mulSpeed/asgnSpeed+" assignment per loop.");
    }
}
 
