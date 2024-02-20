public interface Card
{
    public static final int ACE = 1;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int JOKER = 14;
    public static final int CLUBS = 0;
    public static final int DIAMONDS = 1;
    public static final int HEARTS = 2;
    public static final int SPADES = 3;
    /**
     * Determine the suit of a card.
     * @post returns the suit of the card
     * @return the suit of the card
     */
    public int suit();

    /**
     * Determine the number of pips on a card.
     * @post returns the face of the card, e.g., ACE, 3, JACK
     * @return the face of the card, e.g,. ACE, 3, JACK
     */
    public int face();

    /**
     * Returns true if this card is wild.
     * @post returns true iff this card is a wild card
     * @return true iff this card serves as a wild card
     */
    public boolean isWild();

    /**
     * Determine the point value of the card
     * @post return the point value of the card
     * @return the point value of the card
     */
    public int value();

    /**
     * Compare two cards.
     * @param other a valid Card
     * @pre other is valid Card
     * @post returns int <,==,> 0 if this card is <,==,> other
     * @return int <,==,> 0 if this card is <,==,> other
     */
    public int compareTo(Object other);

    /**
     * Generate a printable version of the card.
     * @post returns a printable version of this card
     * @return a printable version of this card
     */
    public String toString();
}
