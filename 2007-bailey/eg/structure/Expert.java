import structure.*;

public class Expert
{
    public static void play(ReadStream human, BinaryTree database)
    {
        if (!database.left().isEmpty())
        { // further choices; must ask a question to distinguish them
            System.out.println(database.value());
            if (human.readLine().equals("yes"))
            {
                play(human,database.left());
            } else {
                play(human,database.right());
            }
        } else { // must be a statement node
            System.out.println("Is it "+database.value()+"?");
            if (human.readLine().equals("yes"))
            {
                System.out.println("I guessed it!");
            } else {
                System.out.println("Darn.  What were you thinking of?");
                // learn!
                BinaryTree newObject = new BinaryTree(human.readLine());
                BinaryTree oldObject = new BinaryTree(database.value());
                database.setLeft(newObject);
                database.setRight(oldObject);
                System.out.println("What question would distinguish "+
                                   newObject.value()+" from "+
                                   oldObject.value()+"?");
                database.setValue(human.readLine());
            }
        }
    }

    public static void main(String args[])
    {
        ReadStream human = new ReadStream();
        // construct a simple database - knows only about a computer
        BinaryTree database = new BinaryTree("a computer");

        System.out.println("Do you want to play a game?");
        while (human.readLine().equals("yes"))
        {
            System.out.println("Think of something...I'll guess it");
            play(human,database);
            System.out.println("Do you want to play again?");
        }
        System.out.println("Have a good day!");
    }
}

/*
Do you want to play a game?
yes
Think of something...I'll guess it
Is it a computer?
no
Darn.  What were you thinking of?
a car
What question would distinguish a car from a computer?
Does it have a horn?
Do you want to play again?
yes
Think of something...I'll guess it
Does it have a horn?
yes
Is it a car?
no
Darn.  What were you thinking of?
a unicorn
What question would distinguish a unicorn from a car?
Is it magical?
Do you want to play again?
yes
Think of something...I'll guess it
Does it have a horn?
yes
Is it magical?
no
Is it a car?
yes
I guessed it!
Do you want to play again?
no
Have a good day!
*/
