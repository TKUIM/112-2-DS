import structure5.*;
public class NumericalAssociation<K extends Number,V>
       extends Association<K,V>
{
    /**
     * Constructs an assocaition between a number and a value.
     * @pre key is a non-null value of type K
     * @post constructs a key-value pair
     * @param key A non-null object of type K
     * @param value A (possibly null) value of type V
     */
    public NumericalAssociation(K key, V value)
    {
        super(key,value);
    }

    /**
     * Returns an integer approximation to the key.
     * @post returns the integer that best approximates the key
     * @return an integer that best represents the association key
     */
    public int keyValue()
    {
        return getKey().intValue();
    }

    public static void main(String[] args)
    {
        NumericalAssociation<Double,String> a =
            new NumericalAssociation<Double,String>(324.5,"Howdy");
        System.out.println(a.keyValue());
    }
}
/*
NumericalAssociation<Integer,String> i;
NumericalAssociation<Double,Integer> d;
NumericalAssociation<Number,Object> n;
NumericalAssociation<BigInteger,Association<String,Object>> I;
}
*/

