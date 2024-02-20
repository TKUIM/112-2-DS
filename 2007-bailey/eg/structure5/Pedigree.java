import structure5.*;

public class Pedigree
{
    public static void main(String args[])
    {
        // ancestors of George H. W. Bush
        // indentation is provided to aid in understanding relations
           BinaryTree<String> JSBush = new BinaryTree<String>("Rev. James");
           BinaryTree<String> HEFay = new BinaryTree<String>("Harriet");
          BinaryTree<String> SPBush = new BinaryTree<String>("Samuel",JSBush,HEFay);

           BinaryTree<String> RESheldon = new BinaryTree<String>("Robert");
            BinaryTree<String> MEButler = new BinaryTree<String>("Mary");
           BinaryTree<String> FSheldon = new BinaryTree<String>("Flora",RESheldon,MEButler);

         BinaryTree<String> PSBush = new BinaryTree<String>("Prescott",SPBush,FSheldon);

           BinaryTree<String> DDWalker = new BinaryTree<String>("David");
           BinaryTree<String> MABeaky = new BinaryTree<String>("Martha");
          BinaryTree<String> GHWalker = new BinaryTree<String>("George",DDWalker,MABeaky);

           BinaryTree<String> JHWear = new BinaryTree<String>("James II");
           BinaryTree<String> NEHolliday = new BinaryTree<String>("Nancy");
          BinaryTree<String> LWear = new BinaryTree<String>("Lucretia",JHWear,NEHolliday);

         BinaryTree<String> DWalker = new BinaryTree<String>("Dorothy",GHWalker,LWear);

        BinaryTree<String> GHWBush = new BinaryTree<String>("George",PSBush,DWalker);

        // Question: What are George H. W. Bush's ancestors' names,
        //   following the mother's side?
        BinaryTree<String> person = GHWBush;
        while (!person.right().isEmpty())
        {
            person = person.right();    // right branch is mother
            System.out.println(person.value()); // value is name
        }

        // add individual directly
        JHWear.setLeft(new BinaryTree<String>("William"));
        // or keep a reference to the pedigree before the update:
        BinaryTree<String> SAYancey = new BinaryTree<String>("Sarah");
        JHWear.setRight(SAYancey);
    }
}
/*
Dorothy
Lucretia
Nancy
*/


