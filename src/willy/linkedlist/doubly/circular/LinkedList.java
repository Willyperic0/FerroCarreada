
package willy.linkedlist.doubly.circular;

import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.logging.Level;
import java.util.logging.Logger;
import willy.linkedlist.node.doubly.LinkedNode;
import willy.util.collection.Collection;
import willy.util.iterator.Iterator;
import willy.util.list.AbstractList;
import willy.util.list.List;

public class LinkedList<E> extends AbstractList<E> {

    private LinkedNode<E> head;
    private LinkedNode<E> node;

    public LinkedList() {
        super();
        this.head = null;
    }

    public LinkedList(E element) {
        super();
        add(element);
    }

    public LinkedList(E[] array) {
        super();
        for (E element : array) {
            add(element);
        }
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
 
        LinkedNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.get();
    }

@Override
public boolean add(E element) {
    try {
        // Creamos un nuevo nodo con el elemento proporcionado
        LinkedNode<E> newNode = new LinkedNode<>(element);

        // Caso: La lista está vacía
        if (head == null) {
            // Establecemos el nuevo nodo como el único nodo en la lista
            head = newNode;
            head.setNext(head);  // El siguiente y el anterior apuntan al mismo nodo
            head.setPrevious(head);
        } else {
            // Caso: La lista no está vacía
            LinkedNode<E> current = head;
            
            // Buscamos el último nodo en la lista
            while (current.getNext() != head) {
                current = current.getNext();
            }
            
            // Agregamos el nuevo nodo al final de la lista
            current.setNext(newNode);
            newNode.setPrevious(current);
            newNode.setNext(head);
            head.setPrevious(newNode);
        }
        
        // Incrementamos el tamaño de la lista
        size++;
        
        // Indicamos que la operación fue exitosa
        return true;
    } catch (Exception e) {
        // Manejo de excepciones: registramos cualquier excepción ocurrida
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    
    // Indicamos que la operación no fue exitosa
    return false;
}


@Override
public boolean addFirst(E element) {
    try {
        // Creamos un nuevo nodo con el elemento proporcionado
        LinkedNode<E> newNode = new LinkedNode<>(element);
        
        // Caso: La lista está vacía
        if (head == null) {
            // Establecemos el nuevo nodo como el único nodo en la lista
            head = newNode;
            newNode.setNext(head);  // El siguiente y el anterior apuntan al mismo nodo
            head.setPrevious(head);
        } else {
            // Caso: La lista no está vacía
            LinkedNode<E> current = head;
            
            // Buscamos el último nodo en la lista
            while (current.getNext() != head) {
                current = current.getNext();
            }
            
            // Agregamos el nuevo nodo al principio de la lista
            current.setNext(newNode);
            newNode.setPrevious(current);
            newNode.setNext(head);
            head.setPrevious(newNode);
            
            // Establecemos el nuevo nodo como la nueva cabeza de la lista
            head = newNode;
        }
        
        // Incrementamos el tamaño de la lista
        size++;
        
        // Indicamos que la operación fue exitosa
        return true;
    } catch (Exception e) {
        // Manejo de excepciones: registramos cualquier excepción ocurrida
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    
    // Indicamos que la operación no fue exitosa
    return false;
}


@Override
public boolean addFirst(Collection<E> collection) {
    try {
        // Creamos un iterador para recorrer la colección
        Iterator<E> iterator = collection.iterator();
        
        // Creamos una lista enlazada invertida para agregar los elementos
        LinkedNode<E> reversedList = null;
        
        // Construimos la lista enlazada invertida
        while (iterator.hasNext()) {
            E element = iterator.next();
            LinkedNode<E> newNode = new LinkedNode<>(element, reversedList, null);
            reversedList = newNode;
        }
        
        // Agregamos los elementos de la lista enlazada invertida al principio de la lista original
        LinkedNode<E> current = reversedList;
        while (current != null) {
            addFirst(current.get());
            current = current.getNext();
        }
        
        // Indicamos que la operación fue exitosa
        return true;
    } catch (Exception e) {
        // Manejo de excepciones: registramos cualquier excepción ocurrida
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    
    // Indicamos que la operación no fue exitosa
    return false;
}


 @Override
public E peek() {
    try {
        // Devuelve el elemento almacenado en la cabeza de la lista
        return head.get();
    } catch (Exception e) {
        // Manejo de excepciones: registramos cualquier excepción ocurrida
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    
    // Si ocurre una excepción, devolvemos null
    return null;
}


@Override
public E peekLast() {
    try {
        // Verificamos si la lista no está vacía
        if (head != null) {
            LinkedNode<E> current = head;
            
            // Buscamos el último nodo en la lista
            while (current.getNext() != head) {
                current = current.getNext();
            }
            
            // Devolvemos el elemento almacenado en el último nodo
            return current.get();
        }
    } catch (Exception e) {
        // Manejo de excepciones: registramos cualquier excepción ocurrida
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    
    // Si la lista está vacía o ocurre una excepción, devolvemos null
    return null; 
}


@Override
public List<E> peekCollection(int n) {
    try {
        LinkedNode<E> current = head;
        LinkedNode<E> previous = null;
        List<E> save = new LinkedList<>(); 
        for (int i = 0; i < n && !(current == head && previous != null); i++) {
            // Agregamos el elemento actual a la lista
            save.add(current.get());
            // Movemos los punteros al siguiente nodo
            previous = current;
            current = current.getNext();
        }
        return save;
    } catch (Exception e) {
        // Manejo de excepciones: registramos cualquier excepción ocurrida
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    // Si ocurre una excepción, devolvemos null
    return null;
}


@Override
public List<E> peekLastCollection(int n) {
    try {
        LinkedNode<E> current = head;
        LinkedNode<E> previous = null;
        LinkedList<E> save = new LinkedList<>();
        
        // Avanzamos hasta el nodo n-ésimo desde el final
        for (int e = 0; e < size - n; e++) {
            previous = current;
            current = current.getNext();
        }
        
        // Agregamos los siguientes n elementos a la lista
        for (int i = 0; i < n && !(current == head && previous != null); i++) {
            save.add(current.get());
            previous = current;
            current = current.getNext();
        }
        
        return save;
    } catch (Exception e) {
        // Manejo de excepciones: registramos cualquier excepción ocurrida
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    // Si ocurre una excepción, devolvemos null
    return null;
}


@Override
public E poll() {
    E r = null;
    // Verificamos si la lista no está vacía
    if (head != null) {
        // Obtenemos el elemento en la cabeza de la lista
        r = head.get();
        // Removemos el elemento de la lista
        remove(head.get());
    }
    // Devolvemos el elemento removido o null si la lista está vacía
    return r;
}



@Override
public E pollLast() {
    // Verificamos si la lista está vacía
    if (isEmpty()) {
        return null;
    }
    // Obtenemos el último elemento de la lista
    E r = peekLast();
    // Removemos el último elemento de la lista
    remove(r);
    // Devolvemos el último elemento removido
    return r;
}


@Override
public E[] pollLastArray(int n) {
    // Obtenemos el arreglo de los últimos 'n' elementos de la lista
    E[] r = peekLastArray(n);
    LinkedNode<E> current = head;
    int i = 0;
    // Si 'n' es igual al tamaño de la lista, la lista se vacía y se devuelven los elementos
    if (n == size()) {
        clear();
        return r;
    }
    // Iteramos sobre la lista para eliminar los elementos que no son parte de los últimos 'n'
    do {
        // Si el índice actual es mayor o igual a (tamaño - 'n'), eliminamos el elemento
        if (i >= (size - n)) {
            remove((E) current.get());
        }
        // Movemos al siguiente nodo y aumentamos el índice
        current = current.getNext();
        i++;
    } while (current != head);
    // Si quedó un nodo después de la eliminación, lo ajustamos para que sea circular
    if (current != head) {
        current.setNext(head);
        head.setPrevious(current);
    }
    // Devolvemos el arreglo de los últimos 'n' elementos
    return r;
}


@Override
public List<E> pollCollection(int n) {
    // Obtenemos una lista con los primeros 'n' elementos de la lista
    List<E> list = peekCollection(n);
    LinkedNode<E> current = head;
    // Iteramos 'n' veces para eliminar los primeros 'n' elementos de la lista
    for (int i = 0; i < n; i++) {
        // Removemos el primer elemento de la lista
        remove((E) current.get());
        // Movemos al siguiente nodo
        current = current.getNext();
    }
    // Devolvemos la lista de los primeros 'n' elementos
    return list;
}


@Override
public List<E> pollLastCollection(int n) {
    // Creamos una lista para almacenar los elementos eliminados
    List<E> resultList = new LinkedList<>();
    // Verificamos si la lista está vacía o si 'n' es menor o igual a cero
    if (isEmpty() || n <= 0) {
        return resultList; // Devolvemos la lista vacía
    }
    // Obtenemos un arreglo con los últimos 'n' elementos de la lista
    E[] array = (E[]) pollLastArray(n);
    // Agregamos los elementos del arreglo a la lista resultante
    for (int i = 0; i < n; i++) {
        resultList.add(array[i]);
    }
    // Devolvemos la lista con los últimos 'n' elementos eliminados
    return resultList;
}


@Override
public boolean remove(E element) {
    try {
        // Inicializamos los nodos para recorrer la lista
        LinkedNode<E> current = head;
        LinkedNode<E> previous = null;
        LinkedNode<E> tail = null;
        
        // Buscamos el nodo que contiene el elemento a eliminar
        while (current != null && !current.get().equals(element)) {
            previous = current;
            current = current.getNext();
        }
        
        // Si no se encuentra el elemento, retornamos false
        if (current == null) {
            return false;
        }
        
        // Si el nodo a eliminar es el primero de la lista
        if (current == head) {
            // Movemos el puntero tail al último nodo
            tail = head;
            while (tail.getNext() != head) {
                tail = tail.getNext();
            }
            // Si la lista solo contiene un elemento
            if (head == head.getNext()) {
                head = null;
                tail = null;
            } else {
                // Si la lista contiene más de un elemento
                head = head.getNext();
                tail.setNext(head);
                head.setPrevious(tail);
            }
        } else {
            // Si el nodo a eliminar no es el primero de la lista
            previous.setNext(current.getNext());
            current.setPrevious(previous);
        }
        
        // Reducimos el tamaño de la lista y retornamos true
        size--;
        return true;
    } catch (Exception e) {
        // Manejo de excepciones: registramos cualquier excepción ocurrida
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        return false;
    }
}


@Override
public boolean replace(E element, E newElement, Predicate<E> comparator) {
    // Inicializamos el nodo actual como la cabeza de la lista
    LinkedNode<E> current = head;
    // Variable para indicar si se realizó la sustitución
    boolean replaced = false;
    // Iteramos sobre la lista circular
    do {
        // Verificamos si el elemento actual satisface el predicado y es igual al elemento que queremos reemplazar
        if (comparator.test((E) current.get()) && current.get().equals(element)) {
            // Si se cumple, reemplazamos el elemento actual por el nuevo elemento
            current.set(newElement);
            // Actualizamos la bandera de sustitución
            replaced = true;
        }
        // Avanzamos al siguiente nodo
        current = current.getNext();
    } while (current != head); // Continuamos hasta que volvemos a la cabeza de la lista
    // Retornamos true si se realizó la sustitución, de lo contrario, false
    return replaced;
}


 @Override
public boolean retain(E[] array) {
    // Verificamos si la lista está vacía
    if (isEmpty()) {
        return false; // No hay elementos para retener, retornamos false
    }
    // Creamos una lista para almacenar los elementos que se deben retener
    LinkedList<E> retainedElements = new LinkedList<>();
    // Iteramos sobre el arreglo de elementos
    for (E element : array) {
        // Si la lista contiene el elemento del arreglo, lo añadimos a la lista de elementos retenidos
        if (contains(element)) {
            retainedElements.add(element);
        }
    }
    // Vaciamos la lista original
    clear();
    // Añadimos los elementos retenidos a la lista original
    for (E element : retainedElements.toArray()) {
        add(element);
    }
    // Indicamos que se ha retenido al menos un elemento
    return true;
}


 @Override
public boolean retain(Collection<E> collection) {
    // Verificamos si la lista está vacía
    if (isEmpty()) {
        return false; // No hay elementos para retener, retornamos false
    }
    // Convertimos la colección en una lista
    LinkedList<E> list = (LinkedList<E>) collection;
    // Creamos una lista para almacenar los elementos que se deben retener
    LinkedList<E> retainedElements = new LinkedList<>();
    // Iteramos sobre la lista convertida
    for (E element : list.toArray()) {
        // Si la lista original contiene el elemento, lo añadimos a la lista de elementos retenidos
        if (contains(element)) {
            retainedElements.add(element);
        }
    }
    // Vaciamos la lista original
    clear();
    // Añadimos los elementos retenidos a la lista original
    for (E element : retainedElements.toArray()) {
        add(element);
    }
    // Indicamos que se ha retenido al menos un elemento
    return true;
}


@Override
public boolean set(E index, E element) {
    try {
        // Verificamos si el índice está dentro del rango válido
        if ((int) index < 0 || (int) index >= size) {
            return false; // Si no está en el rango válido, retornamos false
        }
        // Inicializamos el nodo actual como la cabeza de la lista
        LinkedNode<E> current = head;
        // Iteramos hasta alcanzar el nodo en la posición del índice
        for (int i = 0; i < (int) index; i++) {
            current = current.getNext(); // Avanzamos al siguiente nodo
        }
        // Establecemos el nuevo elemento en el nodo actual
        current.set(element);
        return true; // Indicamos que se ha realizado la operación exitosamente
    } catch (Exception e) {
        // Manejo de excepciones: registramos cualquier excepción ocurrida
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        return false; // Indicamos que ha ocurrido un error
    }
}


 @Override
public boolean sort(ToIntFunction<E> toInt) {
    // Si la lista tiene 0 o 1 elemento, ya está ordenada
    if (size <= 1) {
        return true;
    }
    
    boolean swapped;
    do {
        swapped = false;
        LinkedNode<E> current = head;
        do {
            LinkedNode<E> nextNode = current.getNext();
            // Comparamos los valores de los nodos y los intercambiamos si están en el orden incorrecto
            if (toInt.applyAsInt(current.get()) > toInt.applyAsInt(nextNode.get())) {
                E temp = current.get();
                current.set(nextNode.get());
                nextNode.set(temp);
                swapped = true;
            }
            current = nextNode;
        } while (current.getNext() != head); // Iteramos hasta volver a la cabeza de la lista
    } while (swapped); // Continuamos hasta que no haya más intercambios necesarios

    return true; // Indicamos que la lista ha sido ordenada correctamente
}


@Override
public List<E> subList(E from, E to) {
    // Inicializamos el nodo actual como la cabeza de la lista
    LinkedNode<E> current = head;
    // Creamos una lista para almacenar los elementos de la sublista
    List<E> list = new LinkedList<>();
    // Iteramos sobre la lista enlazada
    for (int i = 0; i <= (int) to; i++) {
        // Si el índice está dentro del rango especificado
        if (i >= (int) from) {
            // Añadimos el elemento actual a la sublista
            list.add((E) current.get());
        }
        // Avanzamos al siguiente nodo
        current = current.getNext();
    }
    // Devolvemos la sublista creada
    return list;
}


@Override
public boolean clear() {
    // Asignamos el nodo de la cabeza como la cola de la lista
    LinkedNode<E> tail = head;
    // Verificamos si la lista no está vacía
    if (size != 0) {
        // Desvinculamos todos los nodos de la lista
        tail.setNext(null);
        head.setNext(null);
        head.setNext(null);
        tail.setPrevious(null);
        // Establecemos la cabeza de la lista y el nodo actual como null
        head = null;
        node = null;
        // Establecemos el tamaño de la lista como 0
        size = 0;
        // Indicamos que la limpieza se realizó correctamente
        return true;
    }
    // Si la lista ya estaba vacía, retornamos false
    return false;
}


@Override
public boolean reverse() {
    // Inicializamos el nodo actual como la cabeza de la lista
    LinkedNode<E> current = head;
    // Verificamos si la lista está vacía
    if (size == 0) {
        return false; // No hay elementos para revertir, retornamos false
    }
    // Obtenemos un arreglo con los elementos de la lista en orden inverso
    E[] array = (E[]) peekLastArray(size);
    // Iteramos sobre el arreglo y asignamos los elementos al nodo actual en orden inverso
    for (int i = 0; i < array.length; i++) {
        current.set(array[i]);
        current = current.getNext();
    }
    return true; // Indicamos que la lista ha sido revertida correctamente
}


@Override
public Iterator<E> iterator() {
    return new Iterator<E>() {
        // Inicializamos los nodos para el iterador
        private LinkedNode<E> previous = null;
        private LinkedNode<E> current = head;
        private LinkedNode<E> next;
        {
            // Si la lista no está vacía, establecemos el siguiente nodo
            if (head != null) {
                next = head.getNext();
            }
        }

        @Override
        public boolean hasNext() {
            // Verificamos si hay más elementos por recorrer
            if (size == 0) {
                return false; // Si la lista está vacía, no hay más elementos
            }
            // Retornamos true si el nodo actual no es la cabeza o si hay más nodos por recorrer
            return !(current == head && previous != null);
        }

        @Override
        public E next() {
            // Verificamos si hay más elementos por recorrer
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements to list");
            }
            // Obtenemos el elemento del nodo actual
            E element = current.get();
            // Movemos los punteros al siguiente nodo
            previous = current;
            current = next;
            if (current != null) {
                next = next.getNext();
            }
            return element;
        }
    };
}


 @Override
public boolean contains(E element) {
    // Inicializamos el nodo actual como la cabeza de la lista
    LinkedNode<E> current = head;
    // Iteramos sobre la lista enlazada
    do {
        // Verificamos si el elemento actual es igual al elemento buscado
        if (current.get().equals(element)) {
            return true; // Si es así, retornamos true
        }
        // Avanzamos al siguiente nodo
        current = current.getNext();
    } while (current != head); // Continuamos hasta volver a la cabeza de la lista
    // Si no encontramos el elemento, retornamos false
    return false;
}


@Override
public boolean contains(E[] array) {
    // Iteramos sobre cada elemento del arreglo
    for (E element : array) {
        // Verificamos si el elemento está contenido en la lista
        if (!contains(element)) {
            return false; // Si un elemento no está contenido, retornamos false
        }
    }
    // Si todos los elementos están contenidos, retornamos true
    return true;
}


@Override
public boolean contains(Collection<E> collection) {
    // Inicializamos el nodo actual como la cabeza de la lista
    LinkedNode<E> current = head;
    // Obtenemos un iterador para recorrer la colección
    Iterator<E> iter = collection.iterator();
    int count = 0; // Contador para llevar el número de elementos coincidentes
    
    // Iteramos sobre la colección
    while (iter.hasNext()) {
        E element = iter.next();
        // Verificamos si el elemento actual de la lista es igual al elemento de la colección
        if (current != null && current.get().equals(element)) {
            count++; // Incrementamos el contador
        }
        // Avanzamos al siguiente nodo en la lista
        current = current.getNext();
    }
    
    // Verificamos si el número de elementos coincidentes es igual al tamaño de la colección
    if (count == collection.size()) {
        return true; // Si es así, la lista contiene todos los elementos de la colección
    }
    return false; // Si no, la lista no contiene todos los elementos de la colección
}


@Override
public boolean isEmpty() {
    // Verificamos si la cabeza y el nodo actual son nulos y el tamaño es 0
    return head == null && node == null && size == 0;
}


@Override
public int size() {
    return size; // Retorna el tamaño actual de la lista
}


@Override
public void forEach(Function<E, Void> action) {
    // Inicializamos el nodo actual como la cabeza de la lista
    LinkedNode<E> current = head;
    // Iteramos sobre la lista enlazada
    do {
        // Aplicamos la función de acción al elemento actual
        action.apply(current.get());
        // Avanzamos al siguiente nodo
        current = current.getNext();
    } while (current != head); // Continuamos hasta volver a la cabeza de la lista
}


@Override
public boolean add(E[] array) {
    boolean added = false;
    // Iteramos sobre el arreglo
    for (E element : array) {
        // Intentamos agregar cada elemento a la lista
        added |= add(element);
    }
    return added; // Retornamos true si al menos un elemento fue agregado, de lo contrario false
}


@Override
public boolean add(Collection<E> collection) {
    boolean added = false;
    // Obtenemos un iterador para recorrer la colección
    Iterator<E> iterator = collection.iterator();
    // Iteramos sobre la colección
    while (iterator.hasNext()) {
        // Intentamos agregar cada elemento a la lista
        added |= add(iterator.next());
    }
    return added; // Retornamos true si al menos un elemento fue agregado, de lo contrario false
}


@Override
public E[] peekArray(int n) {
    // Inicializamos el nodo actual como la cabeza de la lista
    LinkedNode<E> current = head;
    // Creamos un arreglo para almacenar los elementos
    E array[] = (E[]) new Object[n];
    int i = 0;
    // Iteramos sobre la lista enlazada y llenamos el arreglo con los primeros n elementos
    do {
        array[i] = current.get();
        current = current.getNext();
        i++;
    } while (current != head && i < n); // Continuamos hasta volver a la cabeza de la lista o alcanzar n elementos
    return array; // Retornamos el arreglo lleno
}


@Override
public E[] peekLastArray(int n) {
    // Creamos un arreglo para almacenar los elementos
    E array[] = (E[]) new Object[n];
    // Obtenemos los últimos n elementos de la lista en un arreglo auxiliar
    Object[] aux = peekArray(size);
    int j = 0;
    // Llenamos el arreglo con los elementos en orden inverso desde el arreglo auxiliar
    for (int i = aux.length - 1; i >= 0 && j < n; i--) {
        array[j] = (E) aux[i];
        j++;
    }
    return array; // Retornamos el arreglo lleno
}

@Override
public E[] pollArray(int n) {
    // Limitamos n al tamaño actual de la lista
    if (n > size()) {
        n = size();
    }
    // Obtenemos los primeros n elementos de la lista en un arreglo
    E[] r = peekArray(n);
    // Removemos los primeros n elementos de la lista
    LinkedNode<E> current = head;
    int i = 0;
    while (current != null && i < n) {
        remove((E) current.get());
        current = current.getNext();
        i++;
    }
    return r; // Retornamos el arreglo con los elementos removidos
}


@Override
public boolean remove(E[] array) {
    boolean removed = false;
    // Iteramos sobre el arreglo
    for (E element : array) {
        // Intentamos remover cada elemento de la lista
        if (remove(element)) {
            removed = true;
        }
    }
    return removed; // Retornamos true si al menos un elemento fue removido, de lo contrario false
}


@Override
public boolean remove(Collection<E> collection) {
    // Verificamos si la lista o la colección están vacías
    if (isEmpty() || collection.isEmpty()) {
        return false; // Si alguna está vacía, no hay elementos para remover, retornamos false
    }
    boolean removedAny = false;
    // Obtenemos un iterador para recorrer la colección
    Iterator<E> iterator = collection.iterator();
    // Iteramos sobre la colección
    while (iterator.hasNext()) {
        E elementToRemove = iterator.next();
        // Intentamos remover cada elemento de la colección de la lista
        if (remove(elementToRemove)) {
            removedAny = true; // Si removemos al menos un elemento, actualizamos removedAny a true
        }
    }
    return removedAny; // Retornamos true si al menos un elemento fue removido, de lo contrario false
}


@Override
public boolean remove(Predicate<E> filter) {
    boolean removed = false;
    // Iteramos sobre la lista usando un iterador
    for (Iterator<E> iter = this.iterator(); iter.hasNext();) {
        E element = iter.next();
        // Aplicamos el predicado al elemento actual y si es verdadero, lo removemos
        if (filter.test(element)) {
            remove(element);
            removed = true; // Indicamos que al menos un elemento fue removido
        }
    }
    return removed; // Retornamos true si al menos un elemento fue removido, de lo contrario false
}


@Override
public boolean replace(E[] array, E[] newArray, Predicate<E> comparator) {
    boolean replaced = false;
    // Iteramos sobre los arreglos de elementos y sus nuevos valores
    for (int i = 0; i < array.length; i++) {
        // Intentamos reemplazar cada elemento en la lista con su nuevo valor
        replaced |= replace(array[i], newArray[i], comparator);
    }
    return replaced; // Retornamos true si al menos un elemento fue reemplazado, de lo contrario false
}


@Override
public boolean replace(Collection<E> collection, Collection<E> newCollection, Predicate<E> comparator) {
    boolean replaced = false;
    // Obtenemos iteradores para recorrer ambas colecciones
    Iterator<E> iterator = collection.iterator();
    Iterator<E> newIterator = newCollection.iterator();
    // Iteramos sobre ambas colecciones
    while (iterator.hasNext() && newIterator.hasNext()) {
        // Obtenemos el elemento actual y su nuevo valor
        E current = iterator.next();
        E newElement = newIterator.next();
        // Intentamos reemplazar el elemento actual con su nuevo valor en la lista
        replaced |= replace(current, newElement, comparator);
    }
    return replaced; // Retornamos true si al menos un elemento fue reemplazado, de lo contrario false
}


@Override
public E[] toArray() {
    // Creamos un arreglo para almacenar todos los elementos de la lista
    E[] array = (E[]) new Object[size];
    // Iteramos sobre la lista y llenamos el arreglo con los elementos
    LinkedNode<E> current = head;
    int i = 0;
    do {
        array[i] = (E) current.get();
        current = current.getNext();
        i++;
    } while (current != head);
    return array; // Retornamos el arreglo lleno
}


@Override
public boolean addFirst(E[] array) {
    boolean added = false;
    // Iteramos sobre el arreglo en orden inverso para agregar los elementos al principio de la lista
    for (int i = array.length - 1; i >= 0; i--) {
        // Intentamos agregar cada elemento al principio de la lista
        added |= addFirst(array[i]);
    }
    return added; // Retornamos true si al menos un elemento fue agregado, de lo contrario false
}

    }