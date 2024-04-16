package willy.maps.binaryTree.bintreeSearch;

import willy.maps.binaryTree.bintree.Bintree;
import willy.maps.binaryTree.node.Node;

public class BinTreeSearch<E extends Comparable<E>> extends Bintree<E>{

    public void insert(E element) {
        root = insertRecursive(root, element);
    }

    private Node<E> insertRecursive(Node<E> current, E element) {
        if (current == null) {
            return new Node<>(element); // Insertar un nuevo nodo si alcanzamos una posición vacía
        }

        // Insertar en el subárbol izquierdo
        if (current.getLeft() == null) {
            current.setLeft(new Node<>(element));
        } 
        // Si el subárbol izquierdo está ocupado, intentar insertar en el subárbol derecho
        else if (current.getRight() == null) {
            current.setRight(new Node<>(element));
        } 
        // Si ambos subárboles están ocupados, seguir insertando en el subárbol izquierdo recursivamente
        else {
            insertRecursive(current.getLeft(), element);
        }

        return current;
    }

    public Node<E> search(E key) {
        return searchRecursive(root, key);
    }

    // Método recursivo para buscar un elemento en el árbol
    private Node<E> searchRecursive(Node<E> current, E key) {
        if (current == null) {
            return null;  // Si llegamos a un nodo nulo, la clave no está en el árbol
        }

        int compareResult = key.compareTo(current.getRoot());
        
        if (compareResult == 0) {
            return current;  // Se encontró el nodo con la clave buscada
        } else if (compareResult < 0) {
            return searchRecursive(current.getLeft(), key);  // Buscar en el subárbol izquierdo
        } else {
            return searchRecursive(current.getRight(), key);  // Buscar en el subárbol derecho
        }
    }

    public static void main(String[] args) {
        BinTreeSearch<String> bst = new BinTreeSearch<>();
        bst.insert("apple");
        bst.insert("banana");
        bst.insert("orange");
        bst.insert("pear");
        bst.insert("kiwi");

        // Buscar un nodo con valor "orange" en el árbol
        Node<String> result = bst.search("orange");
        if (result != null) {
            System.out.println("Elemento encontrado: " + result.getRoot());
        } else {
            System.out.println("Elemento no encontrado");
        }

        // Buscar un nodo con valor "grape" en el árbol
        result = bst.search("grape");
        if (result != null) {
            System.out.println("Elemento encontrado: " + result.getRoot());
        } else {
            System.out.println("Elemento no encontrado");}}
}