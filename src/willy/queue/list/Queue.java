package willy.queue.list;

import java.util.function.Function;
import willy.linkedlist.singly.LinkedList;
import willy.util.collection.Collection;
import willy.util.iterator.Iterator;
import willy.util.queue.AbstractQueue;

public class Queue<E> extends AbstractQueue<E> {
   // Declara un atributo de tipo LinkedList<E> para almacenar los elementos de la cola
private LinkedList<E> list;

// Constructor predeterminado que inicializa la cola como una LinkedList vacía
public Queue() {
    // Inicializa el atributo list con una nueva instancia de LinkedList vacía
    list = new LinkedList<>();
}

// Constructor que inicializa la cola con un elemento especificado
public Queue(E element) {
    // Inicializa el atributo list con una nueva instancia de LinkedList que contiene el elemento especificado
    list = new LinkedList<>(element);
}

// Método para eliminar todos los elementos de la cola
@Override
public boolean clear() {
    // Llama al método clear de la instancia de LinkedList y devuelve su resultado
    return list.clear();
}

// Método para verificar si la cola contiene un elemento específico
@Override
public boolean contains(E element) {
    // Llama al método contains de la instancia de LinkedList y devuelve su resultado
    return list.contains(element);
}

// Método para verificar si la cola contiene todos los elementos de un array
@Override
public boolean contains(E[] array) {
    // Llama al método contains de la instancia de LinkedList y devuelve su resultado
    return list.contains(array);
}

// Método para verificar si la cola contiene todos los elementos de una colección
@Override
public boolean contains(Collection<E> collection) {
    // Llama al método contains de la instancia de LinkedList y devuelve su resultado
    return list.contains(collection);
}

// Método para verificar si la cola está vacía
@Override
public boolean isEmpty() {
    // Devuelve true si la cola está vacía, de lo contrario devuelve false
    return list.isEmpty();
}

// Método para invertir el orden de los elementos de la cola
@Override
public boolean reverse() {
    // No existe un método reverse en LinkedList, por lo que este método no realiza ninguna acción
    // Aquí podrías implementar tu propia lógica para invertir la cola si es necesario
    return false;
}

// Método para obtener el número de elementos en la cola
@Override
public int size() {
    // Devuelve el número de elementos en la cola
    return list.size();
}

// Método para realizar una acción sobre cada elemento de la cola
@Override
public void forEach(Function<E, Void> action) {
    // Llama al método forEach de la instancia de LinkedList
    list.forEach(action);
}

// Método para obtener un iterador sobre los elementos de la cola
@Override
public Iterator<E> iterator() {
    // Devuelve un iterador sobre los elementos de la instancia de LinkedList
    return list.iterator();
}

// Método para obtener el primer elemento de la cola sin eliminarlo
@Override
public E peek() {
    // Devuelve el primer elemento de la cola (el que está en la parte frontal)
    return list.peek();
}

// Método para extraer y devolver el primer elemento de la cola
@Override
public E extract() {
    // Elimina y devuelve el primer elemento de la cola (el que está en la parte frontal)
    return list.poll();
}

// Método para insertar un elemento al final de la cola
@Override
public boolean insert(E element) {
    // Agrega el elemento al final de la cola
    return list.add(element);
}

}
