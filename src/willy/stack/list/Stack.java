package willy.stack.list;

import java.util.function.Function;
import willy.linkedlist.singly.LinkedList;
import willy.util.collection.Collection;
import willy.util.iterator.Iterator;
import willy.util.stack.AbstractStack;

public class Stack<E> extends AbstractStack<E> {
// Declara un atributo de tipo LinkedList<E> para almacenar los elementos de la pila
LinkedList<E> stack;

// Constructor predeterminado que inicializa la pila como una LinkedList vacía
public Stack() {
    this.stack = new LinkedList<>();
}

// Método para eliminar todos los elementos de la pila
@Override
public boolean clear() {
    // Llama al método clear de la instancia de LinkedList y devuelve su resultado
    return stack.clear();
}

// Método para verificar si la pila contiene un elemento específico
@Override
public boolean contains(E element) {
    // Llama al método contains de la instancia de LinkedList y devuelve su resultado
    return stack.contains(element);
}

// Método para verificar si la pila contiene todos los elementos de un array
@Override
public boolean contains(E[] array) {
    // Llama al método contains de la instancia de LinkedList y devuelve su resultado
    return stack.contains(array);
}

// Método para verificar si la pila contiene todos los elementos de una colección
@Override
public boolean contains(Collection<E> collection) {
    // Agrega la colección a la pila y devuelve el resultado de la operación de agregado
    return stack.add(collection);
}

// Método para verificar si la pila está vacía
@Override
public boolean isEmpty() {
    // Devuelve true si la pila está vacía, de lo contrario devuelve false
    return stack.isEmpty();
}

// Método para invertir el orden de los elementos de la pila
@Override
public boolean reverse() {
    // Llama al método reverse de la instancia de LinkedList y devuelve su resultado
    return stack.reverse();
}

// Método para obtener el número de elementos en la pila
@Override
public int size() {
    // Devuelve el número de elementos en la pila
    return stack.size();
}

// Método para realizar una acción sobre cada elemento de la pila
@Override
public void forEach(Function<E, Void> action) {
    // Llama al método forEach de la instancia de LinkedList
    stack.forEach(action);
}

// Método para obtener un iterador sobre los elementos de la pila
@Override
public Iterator<E> iterator() {
    // Devuelve un iterador sobre los elementos de la instancia de LinkedList
    return stack.iterator();
}

// Método para obtener el elemento en la parte superior de la pila sin eliminarlo
@Override
public E peek() {
    // Devuelve el primer elemento de la pila (el que está en la parte superior)
    return stack.peek();
}

// Método para eliminar y devolver el elemento en la parte superior de la pila
@Override
public E pop() {
    // Elimina y devuelve el primer elemento de la pila (el que está en la parte superior)
    return stack.poll();
}

// Método para agregar un elemento en la parte superior de la pila
@Override
public boolean push(E element) {
    // Agrega el elemento al principio de la pila
    return stack.addFirst(element);
}

}
