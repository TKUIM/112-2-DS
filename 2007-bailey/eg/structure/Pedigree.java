import structure.*;

public class Pedigree
{
    public static void main(String args[])
    {
        // ancestors of George H. W. Bush
        // indentation is provided to aid in understanding relations
           BinaryTree JSBush = new BinaryTree("Rev. James");
           BinaryTree HEFay = new BinaryTree("Harriet");
          BinaryTree SPBush = new BinaryTree("Samuel",JSBush,HEFay);

           BinaryTree RESheldon = new BinaryTree("Robert");
            BinaryTree MEButler = new BinaryTree("Mary");
           BinaryTree FSheldon = new BinaryTree("Flora",RESheldon,MEButler);

         BinaryTree PSBush = new BinaryTree("Prescott",SPBush,FSheldon);

           BinaryTree DDWalker = new BinaryTree("David");
           BinaryTree MABeaky = new BinaryTree("Martha");
          BinaryTree GHWalker = new BinaryTree("George",DDWalker,MABeaky);

           BinaryTree JHWear = new BinaryTree("James II");
           BinaryTree NEHolliday = new BinaryTree("Nancy");
          BinaryTree LWear = new BinaryTree("Lucretia",JHWear,NEHolliday);

         BinaryTree DWalker = new BinaryTree("Dorothy",GHWalker,LWear);

        BinaryTree GHWBush = new BinaryTree("George",PSBush,DWalker);

        // Question: What are George H. W. Bush's ancestors' names,
        //   following the mother's side?
        BinaryTree person = GHWBush;
        while (person.right() != BinaryTree.EMPTY)
        {
            person = person.right();    // right branch is mother
            System.out.println(person.value()); // value is name
        }

        // add individual directly
        JHWear.setLeft(new BinaryTree("William"));
        // or keep a reference to the pedigree before the update:
        BinaryTree SAYancey = new BinaryTree("Sarah");
        JHWear.setRight(SAYancey);
    }
}
/*
Dorothy
Lucretia
Nancy
*/


