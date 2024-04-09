package willy.util.iterator;
public interface Iterator<E> {
    public boolean hasNext();
    //Determines if the iterator has more elements.
    public E next();
    //Gets the next element in the iteration.
}
