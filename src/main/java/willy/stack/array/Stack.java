package willy.stack.array;

import java.util.function.Function;
import willy.array.Array;
import willy.util.collection.Collection;
import willy.util.iterator.Iterator;
import willy.util.stack.AbstractStack;

public class Stack<E> extends AbstractStack<E> {
// Declara un atributo de tipo Array<E> para almacenar los elementos de la pila
private Array<E> list;

// Constructor que inicializa la pila con una dimensión especificada
public Stack(int dimension) {
    // Inicializa el atributo list con una nueva instancia de Array con la dimensión especificada
    list = new Array(dimension);
}

// Método para eliminar todos los elementos de la pila
@Override
public boolean clear() {
    // Llama al método clear de la instancia de Array y devuelve su resultado
    return list.clear();
}

// Método para verificar si la pila contiene un elemento específico
@Override
public boolean contains(E element) {
    // Llama al método contains de la instancia de Array y devuelve su resultado
    return list.contains(element);
}

// Método para verificar si la pila contiene todos los elementos de un array
@Override
public boolean contains(E[] array) {
    // Llama al método contains de la instancia de Array y devuelve su resultado
    return list.contains(array);
}

// Método para verificar si la pila contiene todos los elementos de una colección
@Override
public boolean contains(Collection<E> collection) {
    // Llama al método contains de la instancia de Array y devuelve su resultado
    return list.contains(collection);
}

// Método para verificar si la pila está vacía
@Override
public boolean isEmpty() {
    // Llama al método isEmpty de la instancia de Array y devuelve su resultado
    return list.isEmpty();
}

// Método para invertir el orden de los elementos de la pila
@Override
public boolean reverse() {
    // Llama al método reverse de la instancia de Array y devuelve su resultado
    return list.reverse();
}

// Método para obtener el número de elementos en la pila
@Override
public int size() {
    // Llama al método size de la instancia de Array y devuelve su resultado
    return list.size();
}

// Método para realizar una acción sobre cada elemento de la pila
@Override
public void forEach(Function<E, Void> action) {
    // Llama al método forEach de la instancia de Array
    list.forEach(action);
}

// Método para obtener un iterador sobre los elementos de la pila
@Override
public Iterator<E> iterator() {
    // Devuelve un iterador sobre los elementos de la instancia de Array
    return list.iterator();
}

// Método para obtener el elemento en la parte superior de la pila sin eliminarlo
@Override
public E peek() {
    // Devuelve el último elemento en la instancia de Array
    return list.get(list.size() - 1);
}

// Método para eliminar y devolver el elemento en la parte superior de la pila
@Override
public E pop() {
    // Obtiene el elemento en la parte superior de la pila
    E r = peek();
    // Elimina el elemento en la parte superior de la pila
    list.remove(list.size() - 1);
    // Devuelve el elemento eliminado
    return r;
}

// Método para agregar un elemento en la parte superior de la pila
@Override
public boolean push(E element) {
    // Agrega el elemento a la parte superior de la pila usando el método add de la instancia de Array
    return list.add(element);
}
    
}
