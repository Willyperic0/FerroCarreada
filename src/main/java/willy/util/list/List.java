package willy.util.list;

import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import willy.util.collection.Collection;

public interface List<E> {
    public boolean add (E element);
    //Appends the specified element to the end of this list.
    public boolean add (E[] array);
    public boolean add (Collection<E> collection);
    //Appends all of the elements in the specified collection to the end of this list.
    public boolean addFirst (E element);
    //Appends the specified element at the beginning of this list.
    public boolean addFirst (E[] array);
    /*
    Appends all of the elements in the specified array at the beginning of this list, 
    in the order that they are returned by the specified collection's iterator.
    */
    public boolean addFirst (Collection<E> collection);
    /*  	
    Appends all of the elements in the specified collection at the beginning of this list, 
    in the order that they are returned by the specified collection's iterator.
    */
    public E peek();
    //Retrieves, but does not remove, the head (first element) of this list.
    public E peekLast();
    //Retrieves, but does not remove, the head (first element) of this list.
    public E[] peekArray (int n);
    //Retrieves, but does not remove, the first n elements of the list.
    public E[] peekLastArray (int n);
    //Retrieves, but does not remove, the last n elements of the list.
    public List<E> peekCollection (int n);
    //Retrieves, but does not remove, the first n elements of the list.
    public List<E> peekLastCollection (int n);
    //Retrieves, but does not remove, the last n elements of the list.
    public E poll();
    //Retrieves and removes the head (first element) of this list.
    public E pollLast();
    //Retrieves and removes the last element of this list.
    public E[] pollArray(int n);
    //Retrieves and removes the first n elements of the list.
    public E[] pollLastArray(int n);
    //Retrieves and removes the last n elements of the list.
    public List<E> pollCollection(int n);
    //Retrieves and removes the first n elements of the list.
    public List<E> pollLastCollection (int n);
    //Retrieves and removes the last n elements of the list.
    public boolean remove (E element);
    //Removes the first occurrence of the specified element from this list, if it is present.
    public boolean remove (E[] array);
    //Removes from this list all of its elements that are contained in the specified collection.
    public boolean remove (Collection<E> collection);
    //Removes from this list all of its elements that are contained in the specified collection.
    public boolean remove (Predicate<E> filter);
    /*
    Removes all of the elements of this list that satisfy the given predicate. Errors or 
    runtime exceptions thrown during iteration or by the predicate are relayed to the caller.
    */
    public boolean replace (E element, E newElement,Predicate<E> comparator);
    //Replaces each element of this list with the result of applying the function to that element.
    public boolean replace (E[] array, E[] newArray, Predicate<E> comparator);
    //Replaces each element of this list with the result of applying the function to that element.
    public boolean replace (Collection<E> collection,Collection<E> newCollection,Predicate<E> comparator);
    //Replaces each element of this list with the result of applying the function to that element.
    public boolean retain (E[] array);
    //Retains only the elements in this list that are contained in the specified collection.
    public boolean retain (Collection<E> collection);
    //Retains only the elements in this list that are contained in the specified collection.
    public boolean set (E index, E element);
    //Replaces the specified element by a new element in this list. Only the first occurrence is replaced.
    public boolean sort (ToIntFunction<E> toInt);
    //Sorts this list according to the order induced by the specified Comparator.
    public List<E> subList (E from, E to);
    //Returns a sub list of this list between the specified "from" and "to".
    public E[] toArray();
    //Returns an array containing all of the elements in this list.
    
}
