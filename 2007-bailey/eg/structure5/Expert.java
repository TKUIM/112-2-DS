import structure5.*;

public class Expert
{
    public static void play(ReadStream human, BinaryTree<String> database)
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
                BinaryTree<String> newObject = new BinaryTree<String>(human.readLine());
                BinaryTree<String> oldObject = new BinaryTree<String>(database.value());
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
        BinaryTree<String> empty = new BinaryTree<String>();
        BinaryTree<String> database = new BinaryTree<String>("a computer",empty,empty);

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
yes
no
a car
Does it have a horn?
yes
yes
no
a unicorn
Is it magical?
yes
yes
no
yes
no
Do you want to play a game?
Think of something...I'll guess it
Is it a computer?
Darn.  What were you thinking of?
What question would distinguish a car from a computer?
Do you want to play again?
Think of something...I'll guess it
Does it have a horn?
Is it a car?
Darn.  What were you thinking of?
What question would distinguish a unicorn from a car?
Do you want to play again?
Think of something...I'll guess it
Does it have a horn?
Is it magical?
Is it a car?
I guessed it!
Do you want to play again?
Have a good day!
*/
