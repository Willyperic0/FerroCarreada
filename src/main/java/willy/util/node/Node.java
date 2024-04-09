package willy.util.node;
public interface Node<E> {
    public void set (E element);
    //Sets an element in the node.
    public E get();
    //Gets the element contained in the node.
    public String toString();
    //Gets a string representation of the node.
}
