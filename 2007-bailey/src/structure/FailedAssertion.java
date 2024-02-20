package structure;
/**
 * A class of error indicating failure.
 *
 * @version $Id: FailedAssertion.java 23 2006-08-21 20:50:47Z bailey $
 * @author, 2001 duane a. bailey
 * @see Assert#fail
 */
class FailedAssertion extends Error
{
    final static long serialVersionUID = 0;
    /**
     * Constructs an error indicating failure to meet condition.
     *
     * @post Constructs a new failed assertion error
     * 
     * @param reason String describing failed condition.
     */
    public FailedAssertion(String reason)
    {
        super("\nAssertion that failed: " + reason);
    }
}
