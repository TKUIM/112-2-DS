/* ParkingLot2.java
*   停車格應用2，1大6中3小停車格的空位管理，含出租，歸還，列印合約
*
* > java -cp structure5.jar;. ParkingLot
rent small Alice
rent large Bob
rent small Carol
return Alice
return David
rent small David
rent small Eva
contracts
quit

Space 0 rented.
Space 9 rented.
Space 1 rented.
Space 0 is now free.
No space rented to David.
Space 2 rented.
Space 0 rented.
Bob is renting 9
Carol is renting 1
David is renting 2
Eva is renting 0
6 slots remain available.
*/
//import structure5.*;
package ch11_ordered_structures;
import structure5.ReadStream;
import structure5.ComparableAssociation;
import structure5.OrderedStructure;
import structure5.OrderedList;
import java.util.Iterator;
import structure5.List;
import structure5.SinglyLinkedList;


public class ParkingLot2
{
    public static void main(String[] args)
    {
    	// 空位清單，記錄所有10格空位，含1大、6中、3小停車格
        List<Space> free = new SinglyLinkedList<Space>();  // available
        
        // 有序租借清單，記錄所有(人名,空位)配對，人名排序
        OrderedStructure<ComparableAssociation<String,Space>> rented =
           new OrderedList<ComparableAssociation<String,Space>>(); // rented spaces
           
        // 配置10格空位，含1大、6中、3小停車格
        for (int number = 0; number < 10; number++) 
        {
            if (number < 3) // three small spaces
                free.add(new Space(number,Space.COMPACT));
            else if (number < 9) // six medium spaces
                free.add(new Space(number,Space.MINIVAN));
            else // one large space
                free.add(new Space(number,Space.TRUCK));
        }
        
        // 作業指令
        //   rent {small | medium | large} name
        //   return name
        //   contracts name
        ReadStream r = new ReadStream();
        for (r.skipWhite(); !r.eof(); r.skipWhite())
        {
            String command = r.readString(); // rent/return/contracts 接收作業指令
            /*
              ...
            */
            Space location;
            
            // 租借作業
            if (command.equals("rent"))
            {   // attempt to rent a parking space
                String size = r.readString();// 詢問空位型別
                
                // 製作查詢型別的空位
                Space request;
                if (size.equals("small")) request = new Space(0,Space.COMPACT);
                else if (size.equals("medium")) request = new Space(0,Space.MINIVAN);
                else request = new Space(0,Space.TRUCK);
                
                // check free list for appropriate sized space
                // 查詢空位清單，找尋符合型別的空位
                if (free.contains(request)) 
                {   // a space is available
                    // 若有空位，從空位清單，移除該型別的空位
                    location = free.remove(request);
                    String renter = r.readString(); // to whom? 詢問人名
                    
                    // link renter with space description
                    // 添加(人名,空位)配對到租借清單
                    rented.add(new ComparableAssociation<String,Space>(renter,location));
                    System.out.println("Space "+location.number+" rented.");
                } else {
                    // 若無空位，印抱歉訊息
                    System.out.println("No space available. Sorry.");
                }
            }
            else
            // 列印合約作業
            if (command.equals("contracts"))
            {   // print out contracts in alphabetical order
                // 列印有序租借清單
                for (ComparableAssociation<String,Space> contract : rented) {
                    // extract contract from iterator
                    // extract person from contract  取得人名
                    String person = contract.getKey();
                    // extract parking slot description from contract 取得空位
                    Space slot = contract.getValue();
                    // print it out
                    System.out.println(person+" is renting "+slot.number);

                }
            }
            else
            // 歸還作業
            if (command.equals("return")){
                String renter = r.readString(); // from whom? 詢問人名
                
                // template for finding "rental contract"
                // 以租借人名製作查詢配對
                ComparableAssociation<String,Space> query = 
                    new ComparableAssociation<String,Space>(renter);
                    
                //  查詢有序租借清單，找尋符合租借人名的(人名,空位)配對
                if (rented.contains(query))
                {   // contract found
                    // 若找到，從租借清單，移除取出(人名,空位)配對
                    ComparableAssociation<String,Space> contract = 
                        rented.remove(query);
                    location = contract.getValue(); // where? 從配對取得人名對應的空位
                    free.add(location); // put in free list  將空位加回空位清單
                    System.out.println("Space "+location.number+" is now free.");
                } else {
                    // 若未找到，印錯誤訊息
                    System.out.println("No space rented to "+renter+".");
                }
            }
            // 遇rent,return,contracts之外指令，則退出
            else break;
        }
        System.out.println(free.size()+" slots remain available.");
    }
}

// 車位空格
class Space
{   // structure describing parking space
    public final static int COMPACT = 0; // small space 小型
    public final static int MINIVAN = 1; // medium space 中型
    public final static int TRUCK = 2;   // large space 大型
    protected int number;       // address in parking lot 車位編號
    protected int size;         // size of space 車位型別, 0,1,2
    
    // 建立一格車位，車位編號n，型別s=0,1,2
    public Space(int n, int s)
    // post: construct parking space #n, size s
    {
        number = n;
        size = s;
    }
    
    // 判別本車位和other車位兩者型別是否相同
    public boolean equals(Object other)
    // pre: other is not null
    // post: true iff spaces are equivalent size
    {
        Space that = (Space)other; // 轉型為車位才能取型別欄位
        return this.size == that.size; // 只比較兩者型別相同否
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
No space rented to David.
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
No space rented to David.
Space 2 rented.
Space 0 rented.
6 slots remain available.
*/
