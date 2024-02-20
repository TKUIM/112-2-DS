import structure5.*;
public class NSAssociation<K extends Number, V extends Set<K>>
       extends Association<K,V>
{
    public NSAssociation(K key, V value)
    {
        super(key,value);
    }
}
