package willy.maps.binaryTree.node;

public class Node<E> implements Cloneable{
    private E root;
    private Node<E> right;
    private Node<E> left;
    
    public Node(E root){
        this.root = root;
        this.left = null;
        this.right = null;
    }
   
    public Node(E root, Node<E> right, Node<E> left){
        this.root = root;
        this.left = left;
        this.right = right;
    }
    
    public Node(){
        this.root = null;
        this.left = null;
        this.right = null;
    }

    public E getRoot() {
        return root;
    }

    public void setRoot(E root) {
        this.root = root;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }
    
}
