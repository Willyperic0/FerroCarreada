package willy.linkedlist.node.singly;

import willy.util.node.AbstractNode;

public class LinkedNode<E> extends AbstractNode<E>{
// Declara un atributo de tipo LinkedNode<E> para almacenar el nodo siguiente
public LinkedNode<E> next;

// Constructor predeterminado que inicializa el nodo siguiente como nulo
public LinkedNode() {
    // Llama al constructor de la clase base (si es que existe)
    super();
    // Inicializa el nodo siguiente como nulo
    this.next = null;
}

// Constructor que inicializa el nodo con un elemento y el nodo siguiente como nulo
public LinkedNode(E element) {
    // Llama al constructor de la clase base (si es que existe)
    super(element);
    // Inicializa el nodo siguiente como nulo
    this.next = null;
}

// Método para establecer el nodo siguiente
public void setNext(LinkedNode<E> next) {
    // Asigna el nodo siguiente especificado
    this.next = next;
}

// Método para obtener el nodo siguiente
public LinkedNode<E> getNext() {
    // Devuelve el nodo siguiente
    return next;
}
    
}
