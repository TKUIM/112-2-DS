package structure;
/**
 * A class of error indicating failed precondition.
 *
 * @version $Id: FailedPrecondition.java 23 2006-08-21 20:50:47Z bailey $
 * @author, 2001 duane a. bailey
 * @see Assert#pre
 */
class FailedPrecondition extends FailedAssertion
{
    final static long serialVersionUID = 0;
    /**
     * Constructs an error indicating failure to meet a precondition.
     *
     * @post Constructs a new failed precondition
     * 
     * @param reason String describing precondition.
     */
    public FailedPrecondition(String reason)
    {
        super("\nA precondition: " + reason);
    }
}

