package willy.util.array;

import java.util.function.Predicate;
import willy.util.collection.Collection;

public interface Array<E> {
    public boolean add (E element);
    //Inserts the specified element at the clear position in this collection.
    public boolean add (int index,E[] array);
    //Inserts all of the elements in the specified array into this collection, starting at the specified position.
    public boolean add (int index,Collection<E> collection);
    //Inserts all of the elements in the specified collection into this collection, starting at the specified position.
    public void defragment();
    //Moves all the elements to the left.
    public E get (int index) ;
    //Returns the element at the specified position in this collection.
    public int indexOf(E element);
    //Returns the index of the first occurrence of the specified element in this collection.
    public int lastIndexOf(E element);
    //Returns the index of the last occurrence of the specified element in this collection.
    public boolean remove (int Index);
    //Removes the element at the specified position in this collection.
    public boolean remove (Predicate<E> filter);
    //Removes the first occurrence of the specified element from this collection that depends on a predicate.
    public boolean remove (int to, int from);
    //Removes from this collection all of the elements whose index is between "from" and "to".
    public boolean dimension (int newDimension);
    //Resizes the array to the specified dimension. If the specified dimension is less than the current dimension, the array is truncated.
    public boolean set (int index, E element);
    //Replaces the element at the specified position in this collection with the specified element.
}
