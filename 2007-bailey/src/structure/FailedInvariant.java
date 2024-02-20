package structure;
/**
 * A class of error indicating failure.
 *
 * @version $Id: FailedInvariant.java 23 2006-08-21 20:50:47Z bailey $
 * @author, 2001 duane a. bailey
 * @see Assert#fail
 */
class FailedInvariant extends FailedAssertion
{
    final static long serialVersionUID = 0;
    /**
     * Constructs an error indicating failure to meet an invariant
     *
     * @post Constructs a new failed invariant error
     * @param reason String describing failed condition.
     */
    public FailedInvariant(String reason)
    {
        super("\nInvariant that failed: " + reason);
    }
}
