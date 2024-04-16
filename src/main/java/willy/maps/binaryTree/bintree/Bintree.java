package willy.maps.binaryTree.bintree;

import willy.maps.binaryTree.node.Node;

public class Bintree<E> {
    public Node<E> root;
 
    public Bintree(E element) {
        this.root = new Node<>(element);
    }
    
    public Bintree(){
        this.root = null;
    }
    
    public String preOrder(Node<E> n1) {
        if (n1 != null) {
            return n1.getRoot() + " " +
                preOrder(n1.getLeft()) +
                preOrder(n1.getRight());
        }
        return "";
    }
    
    public String inOrder(Node<E> n1) {
        if(n1 != null){
            return inOrder(n1.getLeft()) +
                n1.getRoot() + " " +
                inOrder(n1.getRight());
        }
        return "";
    }
    
    public String postOrder(Node<E> n1){
        if (n1 != null){
            return postOrder(n1.getLeft()) +
                postOrder(n1.getRight()) +
                n1.getRoot() + " ";
        }
        return "";
    }
 
    public int[] countNodesPerLevel() {
        if (root == null) {
            return new int[0];
        }
 
        int width = getWidth(root);
        int[] counts = new int[width + 1];
 
        countNodesPerLevelHelper(root, counts, 0);
 
        return counts;
    }
 
    public void insert(E element) {
        Node<E> newNode = new Node<>(element);
        if (root == null) {
            root = newNode;
            return;
        }
        Node<E> current = root;
        Node<E> parent = null;
        while (current != null) {
            parent = current;
            if ((int) element < (int) current.getRoot()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        if ((int) element < (int) parent.getRoot()) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }
    }
 
    public void remove(E element) {
        if (root == null) {
            return;
        }
        Node<E> current = root;
        Node<E> parent = null;
        // Find the node to remove
        while (current != null && current.getRoot() != element) {
            parent = current;
            if ((int) element < (int) current.getRoot()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        // If node not found, return
        if (current == null) {
            return;
        }
        // Handle cases for node with 0, 1, or 2 children
        if (current.getLeft() == null) {
            if (parent == null) {
                root = current.getRight();
            } else if (parent.getLeft() == current) {
                parent.setLeft(current.getRight());
            } else {
                parent.setRight(current.getRight());
            }
        } else if (current.getRight() == null) {
            if (parent == null) {
                root = current.getLeft();
            } else if (parent.getLeft() == current) {
                parent.setLeft(current.getLeft());
            } else {
                parent.setRight(current.getLeft());
            }
        } else {
            // Node with 2 children - find successor
            Node<E> successor = current.getRight();
            Node<E> successorParent = current;
            while (successor.getLeft() != null) {
                successorParent = successor;
                successor = successor.getLeft();
            }
            // Replace current node with successor
            if (successorParent == current) {
                successor.setRight(current.getRight());
            } else {
                successorParent.setLeft(successor.getRight());
            }
            current.setRoot(successor.getRoot()); //era setData, el setRoot esta para que no de error JAJSKAJAK recuerda cambiarlo
        }
    }
 
    private int getWidth(Node<E> node) {
        if (node == null) {
            return -1; // Un árbol vacío tiene ancho -1
        } else {
            int leftWidth = getWidth(node.getLeft());
            int rightWidth = getWidth(node.getRight());
            int maxChildWidth;
 
            if (leftWidth > rightWidth) {
                maxChildWidth = leftWidth;
            } else {
                maxChildWidth = rightWidth;
            }
 
            return 1 + maxChildWidth;
        }
    }
 
    private void countNodesPerLevelHelper(Node<E> node, int[] counts, int level) {
        if (node == null) {
            return;
        }
 
        counts[level]++;
 
        countNodesPerLevelHelper(node.getLeft(), counts, level + 1);
        countNodesPerLevelHelper(node.getRight(), counts, level + 1);
    }
 
    public int getHeight() {
        return getHeight(root);
    }
 
    private int getHeight(Node<E> node) {
        if (node == null) {
            return -1; // Un árbol vacío tiene altura -1
        } else {
            int leftHeight = getHeight(node.getLeft());
            int rightHeight = getHeight(node.getRight());
            if (leftHeight > rightHeight) {
                return leftHeight + 1;
            } else {
                return rightHeight + 1;
            }
        }
    }
}
    
    //Siguiente calcular altura
    
    //Siguiente calcular si el arbol esta completo, si cada rama tiene sus 2 hijos
    
    //Metodo de insercion con cualquiera de estos 4 (add) tiene que basarse en alguno de los anteriores

    //Metodo para eliminar

    //metodos de hashmap add, delete, funcion de dispersion 

    //LIBRO QUE EXPLICA ARBOLES OSWALDO ESTRUCTURAD DE DATOS DE OSWALDO 

