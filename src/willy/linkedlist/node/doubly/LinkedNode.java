package willy.linkedlist.node.doubly;

import willy.util.node.AbstractNode;

public class LinkedNode<E> extends AbstractNode<E> {

// Declara dos atributos de tipo LinkedNode<E> para almacenar el nodo siguiente y el nodo anterior
LinkedNode<E> next;
LinkedNode<E> previous;

// Constructor predeterminado que inicializa los nodos siguiente y anterior como nulos
public LinkedNode() {
    this.next = null;
    this.previous = null;
}

// Constructor que inicializa los nodos siguiente y anterior con los valores especificados
public LinkedNode(E element, LinkedNode<E> next, LinkedNode<E> previous) {
    // Llama al constructor de la clase base (si es que existe)
    super(element);
    // Asigna los valores especificados a los nodos siguiente y anterior
    this.next = next;
    this.previous = previous;
}

// Constructor que inicializa el nodo con un elemento, y los nodos siguiente y anterior como nulos
public LinkedNode(E element) {
    // Llama al constructor de la clase base (si es que existe)
    super(element);
    // Inicializa los nodos siguiente y anterior como nulos
    this.next = null;
    this.previous = null;
}

// Método para obtener el nodo siguiente
public LinkedNode<E> getNext() {
    // Devuelve el nodo siguiente
    return next;
}

// Método para establecer el nodo siguiente
public void setNext(LinkedNode<E> next) {
    // Asigna el nodo siguiente especificado
    this.next = next;
}

// Método para obtener el nodo anterior
public LinkedNode<E> getPrevious() {
    // Devuelve el nodo anterior
    return previous;
}

// Método para establecer el nodo anterior
public void setPrevious(LinkedNode<E> previous) {
    // Asigna el nodo anterior especificado
    this.previous = previous;
}

}
