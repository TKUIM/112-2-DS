public class BaccaratCard extends AbstractCard
{
    /**
     * Construct card with a particular face and suit.
     * @param face the face value of the card (ACE..KING)
     * @param suit the suit of the card (HEARTS..SPADES)
     * @pre face and suit have valid values
     * @post constructs a card with the particular face value
     */
    public BaccaratCard(int face, int suit)
    {
        set(suit*13+face-1);
    }

    /**
     * Construct a random baccarat card.
     */
    public BaccaratCard()
    {
    }

    /**
     * Return the face value of the baccarat card.
     * @post returns rank of card - 10 and above worth 0
     * @return rank of card - 10 and above worth 0
     */
    public int value()
    {
        if (face() > 9) return 0;
        else return face();
    }

    /**
     * Return the relationship between this card and other card.
     * @pre other is valid BaccaratCard
     * @param other another BaccaratCard
     * @post returns relationship between this card and other
     * @return relationship between this card and other
     */
    public int compareTo(Object other)
    {
        BaccaratCard that = (BaccaratCard)other;
        return value()-that.value();
    }

    public static void main(String[] args)
    {
        BaccaratCard c = new BaccaratCard();
        System.out.println(c);
        BaccaratCard d = new BaccaratCard(5,HEARTS);
        if (c.compareTo(d) < 0)
        {
            System.out.println("Less than 5 of Hearts.");
        } else if (c.compareTo(d) == 0)
        {
            System.out.println("Equal to 5 of Hearts.");
        } else {
            System.out.println("More than 5 of Hearts.");
        } 

    }
}
