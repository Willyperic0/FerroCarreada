package willy.util.iterable;
import java.util.function.Function;
import willy.util.iterator.Iterator;
public interface Iterable<E> {
    public void forEach(Function<E, Void> action);
    //For each element in the iterator, executes the specified action.
    public Iterator<E> iterator();
    //Gets an iterator over the elements in the iterator.
}

