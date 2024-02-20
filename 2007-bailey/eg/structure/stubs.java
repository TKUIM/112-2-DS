public class stubs
{
    /**
     * <dl>
     * <dt><b>Postcondition:</b><dd> returns true if this object and other are equal
     * </dl>
     * 
     * @param other 
     */
    public boolean equals(Object other)
    // post: returns true if this object and other are equal
    {
        DoublyLinkedNode that = (DoublyLinkedNode)other;
        if (that == null) return false;
        if (that.value == null || this.value == null)
        {
            return this.value == that.value;
        } else {
            return this.value.equals(that.value);
        }
    }

    /**
     * <dl>
     * <dt><b>Postcondition:</b><dd> generates hash code for element
     * </dl>
     * 
     */
    public int hashCode()
    // post: generates hash code for element
    {
        if (value == null) return super.hashCode();
        else return value.hashCode();
    }
}
