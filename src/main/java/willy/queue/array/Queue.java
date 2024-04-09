package willy.queue.array;

import java.util.function.Function;
import willy.array.Array;
import willy.util.collection.Collection;
import willy.util.iterator.Iterator;
import willy.util.queue.AbstractQueue;

public class Queue<E> extends AbstractQueue<E>{
   // Declara un atributo de tipo Array<E> para almacenar los elementos de la cola
private Array<E> list;

// Constructor que inicializa la cola con una dimensión especificada
public Queue(int dimension) {
    // Inicializa el atributo list con una nueva instancia de Array con la dimensión especificada
    list = new Array<>(dimension);
}

// Método para eliminar todos los elementos de la cola
@Override
public boolean clear() {
    // Llama al método clear de la instancia de Array y devuelve su resultado
    return list.clear();
}

// Método para verificar si la cola contiene un elemento específico
@Override
public boolean contains(E element) {
    // Llama al método contains de la instancia de Array y devuelve su resultado
    return list.contains(element);
}

// Método para verificar si la cola contiene todos los elementos de un array
@Override
public boolean contains(E[] array) {
    // Llama al método contains de la instancia de Array y devuelve su resultado
    return list.contains(array);
}

// Método para verificar si la cola contiene todos los elementos de una colección
@Override
public boolean contains(Collection<E> collection) {
    // Llama al método contains de la instancia de Array y devuelve su resultado
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
    // Llama al método reverse de la instancia de Array y devuelve su resultado
    return list.reverse();
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
    // Llama al método forEach de la instancia de Array
    list.forEach(action);
}

// Método para obtener un iterador sobre los elementos de la cola
@Override
public Iterator<E> iterator() {
    // Devuelve un iterador sobre los elementos de la instancia de Array
    return list.iterator();
}

// Método para obtener el primer elemento de la cola sin eliminarlo
@Override
public E peek() {
    // Devuelve el primer elemento de la cola
    return list.get(0);
}

// Método para extraer y devolver el primer elemento de la cola
@Override
public E extract() {
    // Elimina el último elemento de la cola
    list.remove(list.size() - 1);
    // Devuelve el primer elemento de la cola
    return peek();
}

// Método para insertar un elemento al final de la cola
@Override
public boolean insert(E element) {
    // Agrega el elemento al final de la cola
    return list.add(element);
}

}
