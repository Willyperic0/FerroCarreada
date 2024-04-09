package willy.util.list;
import willy.util.collection.AbstractCollection;

public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {
    protected int size;
    protected AbstractList(){
        size = 0;
    }
    
}
