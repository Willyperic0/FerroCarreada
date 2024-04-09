package willy.util.collection;
import willy.util.iterable.Iterable;
public interface Collection<E> extends Iterable<E>{
    public boolean clear();
    //Removes all elements from the collection.
    public boolean contains(E element);
    //Determines if the collection contains the specified element.
    public boolean contains (E[] array);
    //Determines if the collection contains the specified elements in the array.
    public boolean contains (Collection<E> collection);
    //Determines if the collection contains the specified elements.
    public boolean isEmpty();
    //Determines if the collection contains any element.
    public boolean reverse();
    //Redistributes the elements in the collection in reverse order.
    public int size();
    //Gets the size of the collection.
}
