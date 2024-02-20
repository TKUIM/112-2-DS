public class PokerCard extends AbstractCard
{
    /**
     * Construct card with a particular face and suit.
     * @param face the face value of the card (ACE..KING)
     * @param suit the suit of the card (HEARTS..SPADES)
     * @pre face and suit have valid values
     * @post constructs a card with the particular face value
     */
    public PokerCard(int face, int suit)
    {
        set(suit*13+face-1);
    }

    /**
     * Construct a random poker card.
     * @post construct a random poker card.
     */
    public PokerCard()
    {
        // by default, calls the AbstractCard constructor
    }

    /**
     * Return the face value of the poker card.
     * @post returns rank of card - aces are high
     * @return rank of card - aces are high
     */
    public int value()
    {
        if (face() == ACE) return KING+1;
        else return face();
    }

    /**
     * Return the relationship between this card and other card.
     * @pre other is valid PokerCard
     * @param other another PokerCard
     * @post returns relationship between this card and other
     * @return relationship between this card and other
     */
    public int compareTo(Object other)
    {
        PokerCard that = (PokerCard)other;
        return value()-that.value();
    }

    public static void main(String[] args)
    {
        PokerCard c = new PokerCard();
        System.out.println(c);
        PokerCard d = new PokerCard(KING,HEARTS);
        if (c.compareTo(d) < 0)
        {
            System.out.println("Less than King of Hearts.");
        } else if (c.compareTo(d) == 0)
        {
            System.out.println("Equal to King of Hearts.");
        } else {
            System.out.println("More than King of Hearts.");
        } 

    }
}

