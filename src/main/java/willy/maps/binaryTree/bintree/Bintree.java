package willy.maps.binaryTree.bintree;
import willy.linkedlist.singly.LinkedList;
import willy.maps.binaryTree.node.Node;
import willy.queue.list.Queue;

// Clase principal que representa un árbol binario
public class Bintree<E> {
    // Raíz del árbol
    public Node<E> root;
    
    // Constructor que inicializa un árbol vacío
    public Bintree() {
        this.root = null;
    }
    
    // Constructor que inicializa un árbol con una raíz dada
    public Bintree(E element) {
        this.root = new Node<>(element);
    }
    
    // Constructor que inicializa un árbol con una raíz dada
    public Bintree(Node<E> element) {
        this.root = element;
    }
    
    // Método para realizar el recorrido en preorden del árbol
    public LinkedList<E> preOrder(Node<E> r) {
        LinkedList<E> list = new LinkedList<>();
        if (r != null) {
            preOrderHelper(r, list);
        }
        return list;
    }
    
    // Método auxiliar para realizar el recorrido en preorden del árbol
    private void preOrderHelper(Node<E> r, LinkedList<E> list) {
        if (r != null) {
            list.add(r.getRoot());
            preOrderHelper(r.getLeft(), list);
            preOrderHelper(r.getRight(), list);
        }
    }
    
    // Método para realizar el recorrido en inorden del árbol
    public LinkedList<E> inOrder(Node<E> r) {
        LinkedList<E> list = new LinkedList<>();
        if (r != null) {
            inOrderHelper(r, list);
        }
        return list;
    }
    
    // Método auxiliar para realizar el recorrido en inorden del árbol
    private void inOrderHelper(Node<E> r, LinkedList<E> list) {
        if (r != null) {
            inOrderHelper(r.getLeft(), list);
            list.add(r.getRoot());
            inOrderHelper(r.getRight(), list);
        }
    }
    
    // Método para realizar el recorrido en postorden del árbol
    public LinkedList<E> postOrder(Node<E> r) {
        LinkedList<E> list = new LinkedList<>();
        if (r != null) {
            postOrderHelper(r, list);
        }
        return list;
    }
    
    // Método auxiliar para realizar el recorrido en postorden del árbol
    private void postOrderHelper(Node<E> r, LinkedList<E> list) {
        if (r != null) {
            postOrderHelper(r.getLeft(), list);
            postOrderHelper(r.getRight(), list);
            list.add(r.getRoot());
        }
    }
    
    // Método para obtener el ancho del árbol
    private int getWidth(Node<E> nodo) {
        if (nodo == null) {
            return -1;
        } else {
            int anchoIzquierdo = getWidth(nodo.getLeft());
            int anchoDerecho = getWidth(nodo.getRight());
            int anchoHijoMaximo;
    
            if (anchoIzquierdo > anchoDerecho) {
                anchoHijoMaximo = anchoIzquierdo;
            } else {
                anchoHijoMaximo = anchoDerecho;
            }
    
            return 1 + anchoHijoMaximo;
        }
    }
    
    // Método para obtener el número de nodos en el árbol
    public int numNodes(Node<E> raiz) {
        if (raiz == null) {
            return 0;
        } else {
            return 1 + numNodes(raiz.getLeft()) + numNodes(raiz.getRight());
        }
    }
    
    // Método para realizar la búsqueda por anchura en el árbol
    private LinkedList<E> breadthFirstSearch(Node<E> r) {
        LinkedList<E> list = new LinkedList<>();
        if (r != null) {
            breadthFirstSearchHelper(r, list);
        }
        list.reverse();
        return list;
    }
    // Método para realizar el recorrido en anchura del árbol
    public Queue<Node<E>> breadthFirstTraversal(Node<E> root) {
        if (root == null) {
            System.out.println("El árbol está vacío");
            return null;
        }
    
        Queue<Node<E>> cola = new Queue<>();
        cola.insert(root);
    
        while (!cola.isEmpty()) {
            Node<E> currentNode = cola.extract();
            System.out.print(currentNode.getRoot() + " ");
    
            if (currentNode.getLeft() != null) {
                cola.insert(currentNode.getLeft());
            }
    
            if (currentNode.getRight() != null) {
                cola.insert(currentNode.getRight());
            }
        }
        return cola;
    }
    // Método auxiliar para realizar la búsqueda por anchura en el árbol
    private void breadthFirstSearchHelper(Node<E> r, LinkedList<E> list) {
        if (r != null) {
            postOrderHelper(r.getRight(), list);
            postOrderHelper(r.getLeft(), list);
            list.add(r.getRoot());
            System.out.println(r.getRoot());
        }
    }
    
    // Método para obtener la altura del árbol
    public int getHeight() {
        return getHeight(root);
    }
    
    // Método auxiliar para obtener la altura del árbol
    private int getHeight(Node<E> subtreeRoot) {
        if (subtreeRoot == null) {
            return -1;
        } else {
            int leftSubtreeHeight = getHeight(subtreeRoot.getLeft());
            int rightSubtreeHeight = getHeight(subtreeRoot.getRight());
            if (leftSubtreeHeight > rightSubtreeHeight) {
                return leftSubtreeHeight + 1;
            } else {
                return rightSubtreeHeight + 1;
            }
        }
    }
    public int minHeight() {
        return minHeight(root);
    }
    
    private int minHeight(Node<E> node) {
        if (node == null) {
            return 0;
        } else {
            int leftHeight = minHeight(node.getLeft());
            int rightHeight = minHeight(node.getRight());
            return Math.min(leftHeight, rightHeight) + 1;
        }
    }
    
    // Método para verificar si el árbol es completo
    private boolean isComplete(Node<E> node) {
        if (node == null) {
            return true;
        }
    
        boolean hasBothChildren = node.getLeft() != null && node.getRight() != null;
        boolean hasNoChildren = node.getLeft() == null && node.getRight() == null;
    
        if (hasNoChildren) {
            return true;
        }
    
        if (hasBothChildren) {
            return isComplete(node.getLeft()) && isComplete(node.getRight());
        }
    
        return false;
    } 
    // Método para insertar un valor en el árbol
    public void insert(E value) {
        if (root == null) {
            root = new Node<>(value);
        } else {
            insertRecursive(root, value);
        }
    }
    
    // Método auxiliar para insertar un valor en el árbol de forma recursiva
    private void insertRecursive(Node<E> current, E value) {
        if (current.getLeft() == null) {
            current.setLeft(new Node<>(value));
        } else if (current.getRight() == null) {
            current.setRight(new Node<>(value));
        } else {
            insertRecursive(current.getLeft(), value);
        }
    }
    
    // Método para eliminar un valor del árbol
    public void remove(E value) {
        Node<E> nodeToRemove = findNode(root, value);
        if (nodeToRemove != null) {
            Node<E> deepestNode = findDeepestNode(root);
            if (deepestNode != null) {
                E temp = nodeToRemove.getRoot();
                nodeToRemove.setRoot(deepestNode.getRoot());
                deepestNode.setRoot(temp);
                removeDeepestNode(root, deepestNode);
            }
        }
    }
    
    // Método para encontrar un nodo con un valor dado en el árbol
    private Node<E> findNode(Node<E> current, E value) {
        if (current == null) {
            return null;
        }
    
        if (current.getRoot().equals(value)) {
            return current;
        }
    
        Node<E> left = findNode(current.getLeft(), value);
        if (left != null) {
            return left;
        }
    
        return findNode(current.getRight(), value);
    }
    // Método para encontrar el nodo más profundo en el árbol
    private Node<E> findDeepestNode(Node<E> current) {
        if (current == null) {
            return null;
        }
        Queue<Node<E>> queue = new Queue<>();
        queue.insert(current);
        Node<E> node = null;
        while (!queue.isEmpty()) {
            node = queue.extract();
            if (node.getLeft() != null) {
                queue.insert(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.insert(node.getRight());
            }
        }
        return node;
    }
        // Método para imprimir el árbol de manera visual
        public void printTree() {
            printTree(root, 0);
        }
    
        // Método auxiliar para imprimir el árbol de manera recursiva
        private void printTree(Node<E> node, int level) {
            if (node != null) {
                printTree(node.getRight(), level + 1);
                for (int i = 0; i < level; i++) {
                    System.out.print("\t"); // Usamos tabulaciones para indentar según el nivel del nodo
                }
                System.out.println(node.getRoot()); // Imprimimos el valor del nodo
                printTree(node.getLeft(), level + 1);
            }
        }
    // Método para eliminar el nodo más profundo del árbol
    private void removeDeepestNode(Node<E> current, Node<E> deepestNode) {
        if (current == null) {
            return;
        }
    
        if (current == deepestNode) {
            current = null;
            return;
        }
    
        if (current.getLeft() == deepestNode) {
            current.setLeft(null);
            return;
        }
    
        if (current.getRight() == deepestNode) {
            current.setRight(null);
            return;
        }
    
        removeDeepestNode(current.getLeft(), deepestNode);
        removeDeepestNode(current.getRight(), deepestNode);
        
    }
        public static void main(String[] args) {
            Bintree<Integer> bintree = new Bintree<>();
     
            // Insertar elementos en el árbol
            bintree.insert(1);
            bintree.insert(2);
            bintree.insert(3);
            bintree.insert(4);
            bintree.insert(5);
     
            // Ejemplo de recorrido del árbol
            System.out.println("Recorrido InOrder del árbol:");
            LinkedList tree = bintree.inOrder(bintree.root);
            for (int i = 0; i < tree.size(); i++) {
                System.out.println("tree:" + tree.get(i));
            }
            bintree.printTree();
            // Eliminar un elemento del árbol
            bintree.remove(2);
     
            // Recorrido del árbol después de la eliminación
            System.out.println("Recorrido InOrder del árbol después de la eliminación:");
            tree = bintree.inOrder(bintree.root);
            for (int i = 0; i < tree.size(); i++) {
                System.out.println("tree:" + tree.get(i));
            }
            bintree.printTree();
        }
    }