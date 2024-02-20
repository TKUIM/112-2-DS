import java.util.Random;
public abstract class AbstractCard implements Card
{
    protected int cardIndex;
    protected static Random gen = new Random();

    /**
     * Construct a random card from the standard deck
     * @post constructs a random card in a standard deck
     * @return a random card from a standard deck
     */
    public AbstractCard()
    {
        set(randomIndex(52));
    }

    /**
     * Pick a random value.
     * @param max the maximum allowable index
     * @pre max > 0
     * @post returns a random number n, 0 <= n < max
     * @return a random number n, 0 <= n < max
     */
    protected static int randomIndex(int max)
    {
        return Math.abs(gen.nextInt()) % max;
    }

    /**
     * Set this card's index to a particular value.
     * @param index this card's future index
     * @post this card has cardIndex index
     */
    protected void set(int index)
    {
        cardIndex = index;
    }

    /**
     * Get this card's index.
     * @post returns this card's card index
     * @return this card's card index
     */
    protected int get()
    {
        return cardIndex;
    }

    /**
     * Determine the suit of a card.
     * @post returns the suit of the card
     * @return the suit of the card
     */
    public int suit()
    {
        return cardIndex / 13;
    }

    /**
     * Determine the number of pips on a card.
     * @post returns the face of the card, e.g. ACE, 3, JACK
     * @return the face of the card, e.g. ACE, 3, JACK
     */
    public int face()
    {
        return (cardIndex % 13)+1;
    }

    /**
     * Returns true if this card is wild (default: no wild cards).
     * @post returns true iff this card is a wild card
     * (default is false)
     * @return true iff this card serves as a wild card
     * (default is false)
     */
    public boolean isWild()
    {
        return false;
    }

    /**
     * Determine the point value of the card
     * @post return the point value of the card, Ace..King
     * @return the point value of the card, Ace..King
     */
    public int value()
    {
        return face();
    }

    /**
     * Generate a printable version of the card.
     * @post returns a printable version of this card
     * @return a printable version of this card
     */
    public String toString()
    {
        String cardName = "";
        switch (face())
        {
          case ACE: cardName = "Ace"; break;
          case JACK: cardName = "Jack"; break;
          case QUEEN: cardName = "Queen"; break;
          case KING: cardName = "King"; break;
          default: cardName = cardName + face(); break;
        }
        switch (suit())
        {
          case HEARTS: cardName += " of Hearts"; break;
          case DIAMONDS: cardName += " of Diamonds"; break;
          case CLUBS: cardName += " of Clubs"; break;
          case SPADES: cardName += " of Spades"; break;
        }
        return cardName;
    }
}
