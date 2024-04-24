/*
*  Pedigree.java
*    建立二分樹表示家譜表
*
> java Pedigree  
Dorothy
Lucretia
Nancy
<BinaryTree George <BinaryTree Prescott <BinaryTree Samuel <BinaryTree Rev. James - -> <BinaryTree Harriet - ->> <BinaryTree Flora <BinaryTree Robert - -> <BinaryTree Mary - ->>> <BinaryTree Dorothy <BinaryTree George <BinaryTree David - -> <BinaryTree Martha - ->> <BinaryTree Lucretia <BinaryTree James II <BinaryTree William - -> <BinaryTree Sarah - ->> <BinaryTree Nancy - ->>>>
<George : Root>
	|<Prescott : L>
	|	|<Samuel : L>
	|	|	|<Rev. James : L>
	|	|	|<Harriet : R>
	|	|<Flora : R>
	|	|	|<Robert : L>
	|	|	|<Mary : R>
	|<Dorothy : R>
	|	|<George : L>
	|	|	|<David : L>
	|	|	|<Martha : R>
	|	|<Lucretia : R>
	|	|	|<James II : L>
	|	|	|	|<William : L>
	|	|	|	|<Sarah : R>
	|	|	|<Nancy : R>


-----------------------------------------
Note: George Bush 四代家譜表, # 表父親, - 表母親
                                               - Nancy Holliday
                              - Lucretial Wear # James II Wear   - Sarah Yancey
                                                                 # William Wear
                                               - Martha Beaky
            - Dorothy Walker  # George Walker  # David Walker
George Bush
            # Prescott Bush   - Flora Sheldon  - Mary Butler
                                               # Robert Sheldom
                              # Samuel Bush    - Harriet Fay 
                                               # Rev. James Bush
*/
package ch12_binary_trees;
//import structure5.*;

public class Pedigree
{
    public static void main(String args[])
    {
        // ancestors of George H. W. Bush
        // indentation is provided to aid in understanding relations
          // 祖父
           BinaryTree<String> JSBush = new BinaryTree<String>("Rev. James");
           BinaryTree<String> HEFay = new BinaryTree<String>("Harriet");
          BinaryTree<String> SPBush = new BinaryTree<String>("Samuel",JSBush,HEFay);

          // 祖母
           BinaryTree<String> RESheldon = new BinaryTree<String>("Robert");
           BinaryTree<String> MEButler = new BinaryTree<String>("Mary");
          BinaryTree<String> FSheldon = new BinaryTree<String>("Flora",RESheldon,MEButler);

         // 父
         BinaryTree<String> PSBush = new BinaryTree<String>("Prescott",SPBush,FSheldon);

          // 外祖父
           BinaryTree<String> DDWalker = new BinaryTree<String>("David");
           BinaryTree<String> MABeaky = new BinaryTree<String>("Martha");
          BinaryTree<String> GHWalker = new BinaryTree<String>("George",DDWalker,MABeaky);

          // 外祖母
           BinaryTree<String> JHWear = new BinaryTree<String>("James II");
           BinaryTree<String> NEHolliday = new BinaryTree<String>("Nancy");
          BinaryTree<String> LWear = new BinaryTree<String>("Lucretia",JHWear,NEHolliday);

         // 母
         BinaryTree<String> DWalker = new BinaryTree<String>("Dorothy",GHWalker,LWear);

        // 本人
        BinaryTree<String> GHWBush = new BinaryTree<String>("George",PSBush,DWalker);

        // Question: What are George H. W. Bush's ancestors' names,
        //   following the mother's side?
        // 找出GHWBush的母方直系祖先名字
        BinaryTree<String> person = GHWBush;
        while (!person.right().isEmpty())
        {
            person = person.right();    // right branch is mother
            System.out.println(person.value()); // value is name
        }

        // add individual directly
        // 添加 外祖母 JHWear 的父及母節點
        JHWear.setLeft(new BinaryTree<String>("William"));
        // or keep a reference to the pedigree before the update:
        BinaryTree<String> SAYancey = new BinaryTree<String>("Sarah");
        JHWear.setRight(SAYancey);
        
        System.out.println(GHWBush);
        
        System.out.println(GHWBush.treeString());
    }
}
/*
Dorothy
Lucretia
Nancy
*/


