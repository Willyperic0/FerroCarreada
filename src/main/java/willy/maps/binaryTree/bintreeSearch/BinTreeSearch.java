package willy.maps.binaryTree.bintreeSearch;

import willy.maps.binaryTree.bintree.Bintree;
import willy.maps.binaryTree.node.Node;

// Clase que extiende Bintree y proporciona funcionalidad adicional para buscar y manipular árboles binarios de búsqueda
public class BinTreeSearch<E extends Comparable<E>> extends Bintree<E> {

    // Método para agregar un elemento al árbol binario de búsqueda
    public void add(E element) {
        root = insertIntoTree(root, element);
    }

    // Método auxiliar para insertar un elemento en el árbol de manera recursiva
    private Node<E> insertIntoTree(Node<E> current, E element) {
        if (current == null) {
            return new Node<>(element);
        }

        int compareResult = element.compareTo(current.getRoot());

        if (compareResult < 0) {
            current.setLeft(insertIntoTree(current.getLeft(), element));
        } else if (compareResult > 0) {
            current.setRight(insertIntoTree(current.getRight(), element));
        }

        return current;
    }

    // Método para buscar un elemento en el árbol binario de búsqueda
    public Node<E> find(E target) {
        return searchInTree(root, target);
    }

    // Método auxiliar para buscar un elemento en el árbol de manera recursiva
    private Node<E> searchInTree(Node<E> current, E target) {
        if (current == null) {
            return null;
        }

        int compareResult = target.compareTo(current.getRoot());

        if (compareResult == 0) {
            return current;
        } else if (compareResult < 0) {
            return searchInTree(current.getLeft(), target);
        } else {
            return searchInTree(current.getRight(), target);
        }
    }

    // Método para eliminar un elemento del árbol binario de búsqueda
    public void remove(E value) {
        root = removeRecursive(root, value);
    }

    // Método auxiliar para eliminar un elemento del árbol de manera recursiva
    private Node<E> removeRecursive(Node<E> currentNode, E value) {
        if (currentNode == null) {
            return null;
        }

        int compareResult = value.compareTo(currentNode.getRoot());

        if (compareResult < 0) {
            currentNode.setLeft(removeRecursive(currentNode.getLeft(), value));
        } else if (compareResult > 0) {
            currentNode.setRight(removeRecursive(currentNode.getRight(), value));
        } else {
            if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                return null;
            } else if (currentNode.getLeft() == null) {
                return currentNode.getRight();
            } else if (currentNode.getRight() == null) {
                return currentNode.getLeft();
            } else {
                E minValue = findMinValue(currentNode.getRight());
                currentNode.setRoot(minValue);
                currentNode.setRight(removeRecursive(currentNode.getRight(), minValue));
            }
        }

        return currentNode;
    }

    // Método auxiliar para encontrar el valor mínimo en un subárbol
    private E findMinValue(Node<E> node) {
        if (node.getLeft() == null) {
            return node.getRoot();
        } else {
            return findMinValue(node.getLeft());
        }
    }

    // Método para imprimir el árbol en orden
    public void printInOrder() {
        printInOrderRecursive(root);
        System.out.println(); // Agrega una nueva línea al final
    }

    // Método auxiliar para imprimir el árbol en orden de manera recursiva
    private void printInOrderRecursive(Node<E> node) {
        if (node != null) {
            printInOrderRecursive(node.getLeft());
            System.out.print(node.getRoot() + " ");
            printInOrderRecursive(node.getRight());
        }
    }


     
        public static void main(String[] args) {
            BinTreeSearch<Integer> binarySearchTree = new BinTreeSearch<>();
            binarySearchTree.add(10);
            binarySearchTree.add(5);
            binarySearchTree.add(15);
            binarySearchTree.add(3);
            binarySearchTree.add(7);
     
            System.out.println("Árbol antes de la eliminación:");
            binarySearchTree.printInOrder();
     
            binarySearchTree.remove(5);
     
            System.out.println("Árbol después de la eliminación:");
            binarySearchTree.printInOrder();
        }
    }