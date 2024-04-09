package willy.util.node;
public abstract class AbstractNode<E> implements Cloneable, Node<E> {
    protected E element;

    protected AbstractNode() {
      this.element = null;
    }

    protected AbstractNode(E element) {
      this.element = element;
    }

    public void set(E element) {
      this.element = element;
    }

    public E get() {
      return element;
    }

    public String toString() {
      return element.toString();
    }
}
