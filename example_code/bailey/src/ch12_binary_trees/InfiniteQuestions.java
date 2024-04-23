/*
*  InifiniteQuestions.java
*    動態建立yes/no知識樹，回答任意問題
*    節點分為答案節點，及提問節點
*    答案節點無小孩，內含答案字串
*    提問節點有兩小孩，
*       左小孩為提問答覆yes的答案節點
*       右小孩為提問答覆no的答案節點
* 
* > java InifiteQuestions
Do you want to play a game?
yes
Think of something...I'll guess it
Is it a computer?
no
Darn.  What were you thinking of?
a car
What question would distinguish a car from a computer?
Does it have a horn
=====
Do you want to play again?
yes
Think of something...I'll guess it
Does it have a horn
yes
Is it a car?
no
Darn.  What were you thinking of?
a unicorn
What question would distinguish a unicorn from a car?
is it magical?
=====
Do you want to play again?
yes
Think of something...I'll guess it
Does it have a horn
yes
is it magical?
no
Is it a car?
yes
I guessed it!
=====
Do you want to play again?
no
Have a good day!
-------------------------------
Note: 以上對答建立的知識樹成長過程如下

(1)
a computer

(2)
Does it have a horn -no- a computer
                    -yes- a car

(3)
Does it have a horn -no- a computer
                    -yes- Is it magical -no- a car
                                        -yes- a unicorn
*/
package ch12_binary_trees;
//import structure5.*;
import java.util.Scanner;

public class InfiniteQuestions
{
    // 依據掃瞄器接收答覆，遞迴走訪database知識樹
    public static void play(Scanner human, BinaryTree<String> database)
    // pre: database is non-null
    // post: the game is finished, and if we lost, we expanded the database
    {
        if (!database.left().isEmpty())
        { // further choices; must ask a question to distinguish them
          // 若目前節點有左子樹，表示為提問節點，列印提問，等對方答覆
            System.out.println(database.value());
            
            if (human.nextLine().equals("yes"))
            {
                // 若對方答覆yes，遞迴走訪左小孩節點
                play(human,database.left());
            } else {
                // 若對方答覆非yes，遞迴走訪右小孩節點
                play(human,database.right());
            }
        } else { // must be a statement node
            // 若目前節點無左子樹，表示為答案節點，列印答案，等對方確認
            System.out.println("Is it "+database.value()+"?");
            
            if (human.nextLine().equals("yes"))
            {
                // 若對方答覆yes，表示我方猜中，列印猜中訊息
                System.out.println("I guessed it!");
            } else {
                // 若對方答覆非yes，表示我方猜錯
                // 則請教如何提問，區別本節點目前答案，及對方心中答案
                System.out.println("Darn.  What were you thinking of?");
                
                // learn!
                // 利用對方心中答案進行學習
                // 詢問如何提問，將本節點由答案節點改為提問節點，長出左,右小孩節點
                // 其左小孩節點為對方心中答案，右小孩節點為原來本節點答案
                
                // 詢問對方心中答案，將其建為左子樹
                // 將原來本節點答案建為右子樹
                BinaryTree<String> newObject = new BinaryTree<String>(human.nextLine());
                BinaryTree<String> oldObject = new BinaryTree<String>(database.value());
                database.setLeft(newObject);
                database.setRight(oldObject);
                
                // 詢問如何提問，區別左節點及右節點答案
                System.out.println("What question would distinguish "+
                                   newObject.value()+" from "+
                                   oldObject.value()+"?");
                database.setValue(human.nextLine()); // 接收及設定為提問節點的提問字串
            }
        }
    }

    public static void main(String args[])
    {
        Scanner human = new Scanner(System.in);
        
        // construct a simple database -- knows only about a computer
        // 建立初始database知識樹，只含答案 a computer
        BinaryTree<String> database = new BinaryTree<String>("a computer");

        System.out.println("Do you want to play a game?");
        // 從新開頭，依據現有database知識樹進行走訪問答，直到用戶答覆非yes，不玩為止
        while (human.nextLine().equals("yes"))
        {
            System.out.println("Think of something...I'll guess it");
            play(human,database); // 走訪知識樹，進行問答
            System.out.println("\n======\nDo you want to play again?");
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
