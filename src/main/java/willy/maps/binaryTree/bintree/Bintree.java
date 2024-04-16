package willy.maps.binaryTree.bintree;

import willy.maps.binaryTree.node.Node;
import willy.queue.list.Queue;

public class Bintree<E> {

    protected Node<E> root;
   
    public Bintree() {
       this.root = null;
   }

   public Bintree(E element) {
       this.root = new Node<>(element);
   }

   public Bintree(Node element) {
       this.root = element;
   }

   public void preOrder(Node<E> nodo) {
       // raiz izquierda derecha
       if (nodo == null) {
           return;
       }
       System.out.println(nodo.getRoot()); 
       preOrder(nodo.getLeft());
       preOrder(nodo.getRight());
   }

   public void inOrder(Node<E> nodo) {
       // izquierda raiz derecha
       if (nodo == null) {
           return;
       }
       inOrder(nodo.getLeft());
       System.out.println(nodo.getRoot()); 
       inOrder(nodo.getRight());

   }

   public void posOrder(Node<E> nodo) {
       // izquierda derecha raiz
       if (nodo == null) {
           return;
       }
       posOrder(nodo.getLeft());
       posOrder(nodo.getRight());
       System.out.println(nodo.getRoot()); 
   }

   public void anchura(Node<E> root) {
       if (root == null) {
           System.out.println("El árbol está vacío");
           return;
       }
   
       Queue<Node<E>> queue = new Queue<>();
       queue.insert(root);
   
       while (!queue.isEmpty()) {
           Node<E> currentNode = queue.extract();
           System.out.print(currentNode.getRoot() + " ");
   
           if (currentNode.getLeft() != null) {
               queue.insert(currentNode.getLeft());
           }
   
           if (currentNode.getRight() != null) {
               queue.insert(currentNode.getRight());
           }
       }
   }

   public int altura(Node<E> nodo) {
       int altura = 0;
       if (nodo == null) {
           return -1; 
       }

       int alturaIzquierda = altura(nodo.getLeft());
       int alturaDerecha = altura(nodo.getRight());

       return Math.max(alturaIzquierda, alturaDerecha) + 1;

   }

   public void insert(E element) {
       if (root == null) {
           root = new Node<>(element); // Si el árbol está vacío, creamos un nuevo nodo como raíz
       } else {
           insertRecursive(root, element);
       }
   }

   private void insertRecursive(Node<E> current, E element) {
       if (current.getLeft() == null) {
           current.setLeft(new Node<>(element)); // Insertar en el subárbol izquierdo si está vacío
       } else if (current.getRight() == null) {
           current.setRight(new Node<>(element)); // Insertar en el subárbol derecho si está vacío
       } else {
           // Si ambos subárboles están ocupados, seguir insertando en el subárbol izquierdo recursivamente
           insertRecursive(current.getLeft(), element);
       }
   }
   private boolean esCompleto(Node<E> nodo) {
       if (nodo == null) {
           return true;
       }

       // Verificar la cantidad de hijos (izquierdo y derecho) del nodo actual
       if (nodo.getLeft() == null && nodo.getRight() == null) {
           return true; // Nodo hoja (sin hijos) es considerado completo
       }

       if (nodo.getLeft() != null && nodo.getRight() != null) {
           // Ambos hijos presentes, verificar recursivamente si los subárboles son completos
           return esCompleto(nodo.getLeft()) && esCompleto(nodo.getRight());
       }

       // Si llegamos aquí, significa que uno de los hijos es null y el otro no
       return false; // No es un árbol completo
   }
   
  
   public static void main(String[] args) {
       Bintree<Integer> bintree = new Bintree<>();

       // Insertar elementos en el árbol
       bintree.insert(1);
       bintree.insert(8);
       bintree.insert(7);
       bintree.insert(4);
       bintree.insert(5);
       bintree.insert(9);
       bintree.insert(12);

       // Ejemplo de recorrido del árbol
       System.out.println("Recorrido InOrder del árbol:");
       bintree.inOrder(bintree.root);}
}
    
    //Siguiente calcular altura
    
    //Siguiente calcular si el arbol esta completo, si cada rama tiene sus 2 hijos
    
    //Metodo de insercion con cualquiera de estos 4 (add) tiene que basarse en alguno de los anteriores

    //Metodo para eliminar

    //metodos de hashmap add, delete, funcion de dispersion 

    //LIBRO QUE EXPLICA ARBOLES OSWALDO ESTRUCTURAD DE DATOS DE OSWALDO 

