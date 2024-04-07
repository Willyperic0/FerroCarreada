package willy.linkedlist.singly.circular;

import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.logging.Level;
import java.util.logging.Logger;
import willy.linkedlist.node.singly.LinkedNode;
import willy.util.collection.Collection;
import willy.util.iterator.Iterator;
import willy.util.list.AbstractList;
import willy.util.list.List;

public class LinkedList<E> extends AbstractList<E> {

    public LinkedNode<E> head;
    public LinkedNode<E> inode;

    public LinkedList() {
        super();
        head = null;
        inode = null;
    }

    public LinkedList(E element) {
        super();
        add(element);
    }

 @Override
public boolean add(E element) {
    try {
        // Crea un nuevo nodo con el elemento dado
        LinkedNode<E> node = new LinkedNode<>(element);
        if (isEmpty()) { // Si la lista está vacía
            head = node; // Establece el nuevo nodo como cabeza y nodo actual
            inode = node;
            node.setNext(head); // Establece el siguiente nodo del nuevo nodo como la cabeza
        } else { // Si la lista no está vacía
            for (int i = 0; i < size; i++) { // Avanza al último nodo
                inode = inode.getNext();
            }
            inode.setNext(node); // Establece el siguiente nodo del último nodo como el nuevo nodo
            inode = node; // Establece el nuevo nodo como nodo actual
            node.setNext(head); // Establece el siguiente nodo del nuevo nodo como la cabeza
        }
        size++; // Incrementa el tamaño de la lista
        return true; // Retorna true para indicar que el elemento fue añadido correctamente
    } catch (Exception e) {
        System.err.println(e.getMessage()); // Muestra un mensaje de error si ocurre una excepción
    }
    return false; // Retorna false si no se pudo añadir el elemento
}

@Override
public boolean add(E[] array) {
    if (array == null || array.length == 0) {
        return false; // Retorna false si el array es nulo o está vacío
    }

    for (E element : array) { // Añade cada elemento del array a la lista
        add(element);
    }
    return true; // Retorna true para indicar que los elementos fueron añadidos correctamente
}

@Override
public boolean add(Collection<E> collection) {
    if (collection == null || collection.isEmpty()) {
        return false; // Retorna false si la colección es nula o está vacía
    }

    Iterator<E> iterator = collection.iterator();
    while (iterator.hasNext()) { // Añade cada elemento de la colección a la lista
        add(iterator.next());
    }
    return true; // Retorna true para indicar que los elementos fueron añadidos correctamente
}

public boolean addFirst(E element) {
    try {
        // Crea un nuevo nodo con el elemento dado
        LinkedNode<E> newNode = new LinkedNode<>(element);
        if (isEmpty()) { // Si la lista está vacía
            head = newNode; // Establece el nuevo nodo como cabeza y nodo actual
            inode = newNode;
            newNode.setNext(head); // Establece el siguiente nodo del nuevo nodo como la cabeza
        } else { // Si la lista no está vacía
            for (int i = 0; i < size; i++) { // Avanza al último nodo
                inode = inode.getNext();
            }
            newNode.setNext(head); // Establece el siguiente nodo del nuevo nodo como la cabeza
            head = newNode; // Establece el nuevo nodo como la nueva cabeza
            inode.setNext(head); // Establece el siguiente nodo del último nodo como la nueva cabeza
        }
        size++; // Incrementa el tamaño de la lista
        return true; // Retorna true para indicar que el elemento fue añadido correctamente
    } catch (Exception e) {
        System.err.println(e.getMessage()); // Muestra un mensaje de error si ocurre una excepción
    }
    return false; // Retorna false si no se pudo añadir el elemento
}

@Override
public boolean addFirst(E[] array) {
    if (array == null || array.length == 0) {
        return false; // Retorna false si el array es nulo o está vacío
    }

    for (E element : array) { // Añade cada elemento del array al principio de la lista
        addFirst(element);
    }
    return true; // Retorna true para indicar que los elementos fueron añadidos correctamente
}

@Override
public boolean addFirst(Collection<E> collection) {
    try {
        Iterator<E> iterator = collection.iterator();
        while (iterator.hasNext()) { // Añade cada elemento de la colección al principio de la lista
            E element = iterator.next();
            addFirst(element);
        }
        return true; // Retorna true para indicar que los elementos fueron añadidos correctamente
    } catch (Exception e) {
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return false; // Retorna false si no se pudo añadir algún elemento
}

@Override
public boolean clear() {
    try {
        head = null; // Establece la cabeza y el nodo actual como nulos
        inode = null;
        size = 0; // Establece el tamaño de la lista como 0
        return true; // Retorna true para indicar que la lista ha sido limpiada
    } catch (Exception e) {
        System.err.println(e.getMessage()); // Muestra un mensaje de error si ocurre una excepción
    }
    return false; // Retorna false si no se pudo limpiar la lista
}

@Override
public boolean contains(E element) {
    LinkedNode<E> current = head;

    if (isEmpty()) {
        return false; // Retorna false si la lista está vacía
    }

    do {
        if (current.get().equals(element)) { // Comprueba si el elemento está en la lista
            return true; // Retorna true si el elemento está presente
        }
        current = current.getNext(); // Avanza al siguiente nodo
    } while (current != head); // Repite hasta que se complete una vuelta completa por la lista

    return false; // Retorna false si el elemento no está presente en la lista
}

@Override
public boolean contains(E[] array) {
    for (E element : array) { // Comprueba si cada elemento del array está en la lista
        if (!contains(element)) {
            return false; // Retorna false si algún elemento no está presente
        }
    }
    return true; // Retorna true si todos los elementos del array están presentes
}

 @Override
public boolean contains(Collection<E> collection) {
    Iterator<E> iterator = collection.iterator();
    while (iterator.hasNext()) {
        if (!contains(iterator.next())) { // Comprueba si la lista contiene cada elemento de la colección
            return false; // Retorna false si algún elemento no está presente en la lista
        }
    }
    return true; // Retorna true si todos los elementos de la colección están presentes en la lista
}

@Override
public boolean isEmpty() {
    return head == null && inode == null && size == 0; // Retorna true si la lista está vacía, es decir, si la cabeza, el nodo actual y el tamaño son nulos
}

public boolean reverse() {
    if (isEmpty() || size == 1) { // Retorna false si la lista está vacía o tiene solo un elemento
        return false;
    }

    LinkedNode<E> current = head;
    LinkedNode<E> previous = null;
    LinkedNode<E> next = null;
    do {
        next = current.getNext();
        current.setNext(previous); // Invierte el orden de los nodos de la lista
        previous = current;
        current = next;
    } while (current != head); // Repite hasta que se complete una vuelta completa por la lista
    head = previous; // Establece la nueva cabeza de la lista

    return true; // Retorna true para indicar que la lista fue invertida correctamente
}

@Override
public void forEach(Function<E, Void> action) {
    if (isEmpty()) {
        return; // Sale del método si la lista está vacía
    }

    LinkedNode<E> current = head;

    do {
        action.apply(current.get()); // Ejecuta la acción proporcionada en cada elemento de la lista
        current = current.getNext();
    } while (current != head); // Repite hasta que se complete una vuelta completa por la lista
}

@Override
public E peek() {
    try {
        if (head != null) {
            return head.get(); // Devuelve el elemento en la cabeza de la lista
        }
    } catch (Exception e) {
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        // Registra cualquier excepción que ocurra durante la operación
    }
    return null; // Si no hay elementos en la lista o se produce una excepción, devuelve null
}


public E peekLast() {
    if (isEmpty()) {
        return null; // Retorna null si la lista está vacía
    }

    LinkedNode<E> current = head;

    do {
        current = current.getNext();
    } while (current.getNext() != head); // Encuentra el último nodo de la lista

    return current.get(); // Retorna el último elemento de la lista
}

 @Override
public E[] peekArray(int n) {
    try {
        Iterator<E> iterator = this.iterator();
        E[] save = (E[]) new Object[n];
        for (int i = 0; i < n && iterator.hasNext(); i++) {
            save[i] = iterator.next(); // Obtiene los primeros 'n' elementos de la lista como un array
        }
        return save;
    } catch (Exception e) {
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return null; // Retorna null si ocurre una excepción o si la lista está vacía
}

@Override
public E[] peekLastArray(int n) {
    try {
        Iterator<E> iterator = this.iterator();
        E[] save = (E[]) new Object[n];
        for (int e = 0; e < size - n; e++) {
            iterator.next(); // Avanza el iterador hasta el elemento 'n' desde el final de la lista
        }
        for (int i = 0; i < n && iterator.hasNext(); i++) {
            save[i] = iterator.next(); // Obtiene los últimos 'n' elementos de la lista como un array
        }
        return save;
    } catch (Exception e) {
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return null; // Retorna null si ocurre una excepción o si la lista está vacía
}

@Override
public List<E> peekCollection(int n) {
    try {
        Iterator<E> iterator = this.iterator();
        List<E> save = new LinkedList<>();
        for (int i = 0; i < n && iterator.hasNext(); i++) {
            save.add(iterator.next()); // Obtiene los primeros 'n' elementos de la lista como una colección
        }
        return save;
    } catch (Exception e) {
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return null; // Retorna null si ocurre una excepción o si la lista está vacía
}

@Override
public List<E> peekLastCollection(int n) {
    try {
        Iterator<E> iterator = this.iterator();
        List<E> save = new LinkedList<>();
        for (int e = 0; e < size - n; e++) {
            iterator.next(); // Avanza el iterador hasta el elemento 'n' desde el final de la lista
        }
        for (int i = 0; i < n && iterator.hasNext(); i++) {
            save.add(iterator.next()); // Obtiene los últimos 'n' elementos de la lista como una colección
        }
        return save;
    } catch (Exception e) {
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return null; // Retorna null si ocurre una excepción o si la lista está vacía
}

@Override
public E poll() {
    if (isEmpty()) {
        return null; // Retorna null si la lista está vacía
    }

    LinkedNode<E> removedNode = head;

    if (size == 1) {
        head = null;
    } else {
        head = head.getNext(); // Elimina y retorna el primer elemento de la lista
    }

    size--;

    return removedNode.get();
}

 @Override
public E pollLast() {
    if (isEmpty()) {
        return null; // Retorna null si la lista está vacía
    }

    LinkedNode<E> current = head;
    LinkedNode<E> previous = null;

    do {
        previous = current;
        current = current.getNext();
    } while (current.getNext() != head); // Avanza hasta el penúltimo nodo en la lista

    if (size == 1) {
        head = null;
    } else {
        previous.setNext(head); // Elimina y retorna el último elemento de la lista
    }

    size--;

    return current.get();
}

@Override
public E[] pollArray(int n) {
    try {
        E[] save = (E[]) new Object[n];
        if (n >= size) {
            n = size;
        }
        for (int i = 0; i < n; i++) {
            save[i] = poll(); // Elimina y retorna los primeros 'n' elementos de la lista como un array
        }
        return save;
    } catch (Exception e) {
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return null; // Retorna null si ocurre una excepción o si la lista está vacía
}

@Override
public E[] pollLastArray(int n) {
    try {
        E[] save = (E[]) new Object[n];
        if (n < size) {
            for (int i = n - 1; i >= 0; i--) {
                save[i] = pollLast(); // Elimina y retorna los últimos 'n' elementos de la lista como un array
            }
        } else {
            System.arraycopy(toArray(), 0, save, 0, size); // Copia todos los elementos de la lista en un array y luego limpia la lista
            head = null;
            size = 0;
        }
        return save;
    } catch (Exception e) {
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return null; // Retorna null si ocurre una excepción o si la lista está vacía
}

@Override
public List<E> pollCollection(int n) {
    try {
        List<E> save = new LinkedList<>();
        if (n >= size) {
            n = size;
        }
        for (int i = 0; i < n; i++) {
            save.add(poll()); // Elimina y retorna los primeros 'n' elementos de la lista como una colección
        }
        return save;
    } catch (Exception e) {
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return null; // Retorna null si ocurre una excepción o si la lista está vacía
}

 @Override
public List<E> pollLastCollection(int n) {
    try {
        List<E> save = new LinkedList<>();
        if (n < size) {
            for (int i = n - 1; i >= 0; i--) {
                save.add(pollLast()); // Elimina y retorna los últimos 'n' elementos de la lista como una colección
            }
        } else {
            save.add(this); // Si se solicita más elementos de los que tiene la lista, se devuelve toda la lista como una colección y luego se limpia la lista
            head = null;
            size = 0;
        }
        return save;
    } catch (Exception e) {
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return null; // Retorna null si ocurre una excepción
}

@Override
public boolean remove(E element) {
    if (isEmpty()) {
        return false; // Retorna false si la lista está vacía
    }

    LinkedNode<E> current = head;
    LinkedNode<E> previous = null;

    do {
        if (current.get().equals(element)) {
            if (previous != null) {
                previous.setNext(current.getNext()); // Elimina el primer elemento igual a 'element' encontrado en la lista
            } else {
                head = current.getNext();
            }

            size--;
            return true;
        }

        previous = current;
        current = current.getNext();
    } while (current != head);

    return false; // Retorna false si 'element' no se encuentra en la lista
}

@Override
public boolean remove(E[] array) {
    boolean result = false;
    for (E element : array) {
        result |= remove(element); // Elimina todos los elementos presentes en el array de la lista
    }
    return result;
}

@Override
public boolean remove(Predicate<E> filter) {
    if (isEmpty()) {
        return false; // Retorna false si la lista está vacía
    }

    LinkedNode<E> current = head;
    LinkedNode<E> previous = null;

    do {
        if (filter.test(current.get())) {
            if (previous != null) {
                previous.setNext(current.getNext()); // Elimina el primer elemento que cumpla con el predicado dado
            } else {
                head = current.getNext();
            }

            size--;
            return true;
        }

        previous = current;
        current = current.getNext();
    } while (current != head);

    return false; // Retorna false si ningún elemento de la lista cumple con el predicado
}

@Override
public boolean replace(E element, E newElement, Predicate<E> comparator) {
    if (isEmpty()) {
        return false; // Retorna false si la lista está vacía
    }

    LinkedNode<E> current = head;

    do {
        if (comparator.test(current.get())) {
            current.set(newElement); // Reemplaza el primer elemento que cumple con el predicado dado
            return true;
        }
        current = current.getNext();
    } while (current != head);

    return false; // Retorna false si ningún elemento de la lista cumple con el predicado
}

@Override
public boolean replace(E[] array, E[] newArray, Predicate<E> comparator) {
    if (array == null || newArray == null || array.length != newArray.length) {
        return false; // Retorna false si los arrays son nulos o tienen longitudes diferentes
    }

    boolean result = false;

    for (int i = 0; i < array.length; i++) {
        result |= replace(array[i], newArray[i], comparator); // Reemplaza cada elemento del primer array con el correspondiente del segundo array si cumple con el predicado
    }

    return result;
}

@Override
public boolean replace(Collection<E> collection, Collection<E> newCollection, Predicate<E> comparator) {
    if (collection == null || newCollection == null || collection.size() != newCollection.size()) {
        return false; // Retorna false si las colecciones son nulas o tienen tamaños diferentes
    }

    boolean result = false;

    Iterator<E> oldIterator = collection.iterator();
    Iterator<E> newIterator = newCollection.iterator();

    while (oldIterator.hasNext() && newIterator.hasNext()) {
        result |= replace(oldIterator.next(), newIterator.next(), comparator); // Reemplaza cada elemento de la primera colección con el correspondiente de la segunda colección si cumple con el predicado
    }

    return result;
}

@Override
public boolean retain(E[] array) {
    if (array == null) {
        return false; // Retorna false si el array es nulo
    }

    LinkedList<E> newList = new LinkedList<>();

    for (E element : array) {
        if (contains(element)) {
            newList.add(element); // Retiene solo los elementos del array que están presentes en la lista
        }
    }

    clear();
    add(newList); // Reemplaza la lista con la nueva lista que contiene solo los elementos retenidos

    return true;
}

@Override
public boolean retain(Collection<E> collection) {
    if (collection == null) {
        return false; // Retorna false si la colección es nula
    }

    LinkedList<E> newList = new LinkedList<>();

    Iterator<E> iterator = iterator();
    while (iterator.hasNext()) {
        E element = iterator.next();
        if (collection.contains(element)) {
            newList.add(element); // Retiene solo los elementos de la colección que están presentes en la lista
        }
    }

    clear();
    add(newList); // Reemplaza la lista con la nueva lista que contiene solo los elementos retenidos

    return true;
}

@Override
public boolean set(E index, E element) {
    if (index == null || element == null) {
        return false; // Retorna false si el índice o el elemento son nulos
    }

    LinkedNode<E> current = head;

    for (int i = 0; i < size; i++) {
        if (current.get().equals(index)) {
            current.set(element); // Establece el nuevo elemento en el índice dado
            return true;
        }
        current = current.getNext();
    }

    return false; // Retorna false si no se encontró el índice
}

@Override
public boolean sort(ToIntFunction<E> toInt) {
    if (size <= 1) {
        return true; // Retorna true si la lista está vacía o tiene un solo elemento
    }

    boolean swapped;
    do {
        swapped = false;
        LinkedNode<E> current = head;
        LinkedNode<E> previous = null;

        do {
            LinkedNode<E> next = current.getNext();

            if (toInt.applyAsInt(current.get()) > toInt.applyAsInt(next.get())) {

                if (previous != null) {
                    previous.setNext(next);
                } else {
                    head = next;
                }

                current.setNext(next.getNext());
                next.setNext(current);

                // Indica que se hizo un intercambio
                swapped = true;
            } else {
                previous = current;
                current = next;
            }
        } while (current.getNext() != head);
    } while (swapped);

    return true; // Retorna true si se realizó la ordenación correctamente
}

@Override
public List<E> subList(E from, E to) {
    try {
        List<E> sublist = new LinkedList<>();
        Iterator<E> iterator = this.iterator();

        while (iterator.hasNext()) {
            E element = iterator.next();
            if (element.equals(from)) {
                sublist.add(element);
                break;
            }
        }

        while (iterator.hasNext()) {
            E element = iterator.next();
            sublist.add(element);
            if (element.equals(to)) {
                break;
            }
        }

        return sublist; // Retorna una sublista desde el elemento "from" hasta el elemento "to"
    } catch (Exception e) {
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return null; // Retorna null si ocurre una excepción
}

@Override
public E[] toArray() {
    if (isEmpty()) {
        return (E[]) new Object[0]; // Retorna un array vacío si la lista está vacía
    }

    E[] array = (E[]) new Object[size];
    LinkedNode<E> current = head;
    int i = 0;

    do {
        array[i++] = current.get(); // Copia los elementos de la lista a un array
        current = current.getNext();
    } while (current != head);

    return array;
}

 @Override
public Iterator<E> iterator() {
    return new Iterator<E>() {
        private LinkedNode<E> current = head; // Inicia el iterador desde el primer nodo
        private int currentIndex = 0; // Índice actual del nodo

        @Override
        public boolean hasNext() {
            return currentIndex < size; // Retorna true si hay más elementos en la lista
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException(); // Lanza una excepción si no hay más elementos
            }

            E element = current.get(); // Obtiene el elemento actual
            current = current.getNext(); // Mueve al siguiente nodo
            currentIndex++; // Incrementa el índice
            return element; // Retorna el elemento actual
        }
    };
}

@Override
public int size() {
    return size; // Devuelve el tamaño actual de la lista
}

@Override
public boolean remove(Collection<E> collection) {
    if (isEmpty() || collection.isEmpty()) {
        return false; // Si la lista o la colección están vacías, no hay nada que eliminar
    }

    boolean modified = false; // Indica si se modificó la lista
    Iterator<E> iterator = collection.iterator(); // Iterador sobre la colección

    while (iterator.hasNext()) {
        E elementToRemove = iterator.next(); // Obtiene el siguiente elemento de la colección
        if (remove(elementToRemove)) { // Intenta eliminar el elemento de la lista
            modified = true; // Si se elimina algún elemento, se marca como modificado
        }
    }

    return modified; // Devuelve true si se eliminó algún elemento de la lista
}

}
