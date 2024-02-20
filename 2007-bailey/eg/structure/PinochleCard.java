public class PinochleCard extends AbstractCard
{
    // cardIndex        face    suit
    // 0                9       clubs
    // 1                9       clubs (duplicate)
    // ...
    // 10               ACE     clubs
    // 11               ACE     clubs (duplicate)
    // 12               9       diamonds
    // 13               9       diamonds (duplicate)
    // ...
    // 47               ACE     spades (duplicate)

    /**
     * Construct card with a particular face and suit.
     * @param face the face value of the card (ACE..KING)
     * @param suit the suit of the card (HEARTS..SPADES)
     * @pre face and suit have valid values
     * @post constructs a card with the particular face value
     */
    public PinochleCard(int face, int suit, int copy)
    {
        if (face == ACE) face = KING+1;
        set((suit*2+copy)*6+face-9);
    }

    /**
     * @post Construct a random Pinochle card.
     */
    public PinochleCard()
    {
        set(randomIndex(48));
    }

    /**
     * Return the face value of the card.
     * @post returns the face value of the card (9 thru Ace)
     * @return the face value of the card (9 thru Ace)
     */
    public int face()
    {
        int result = get()%6 + 9;
        if (result == 14) result = ACE;
        return result;
    }

    /**
     * Determine the suit of a card
     * @post returns the suit of the card (there are duplicates!)
     * @return the suit of the card (there are duplicates!)
     */
    public int suit()
    {
        // this is tricky; we divide by 12 cards (including duplicates)
        // per suit, and again by 2 to remove the duplicate
        return cardIndex / 12 / 2;
    }

    /**
     * Return the face value of the pinochle card.
     * @post returns rank of card - aces are high
     * @return rank of card - aces are high
     */
    public int value()
    {
        if (face() == ACE) return KING+2;
        else if (face() == 10) return KING+1;
        else return face();
    }

    /**
     * Return the relationship between this card and other card.
     * @pre other is valid PinochleCard
     * @param other another PinochleCard
     * @post returns relationship between this card and other
     * @return relationship between this card and other
     */
    public int compareTo(Object other)
    {
        PinochleCard that = (PinochleCard)other;
        return value()-that.value();
    }

    public static void main(String[] args)
    {
        PinochleCard c = new PinochleCard();
        System.out.println(c);
        PinochleCard d = new PinochleCard(KING,HEARTS,0);
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


