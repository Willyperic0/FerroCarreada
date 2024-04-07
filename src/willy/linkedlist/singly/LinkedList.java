package willy.linkedlist.singly;

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
    public LinkedNode<E> tail;
    public LinkedNode<E> inode;

    public LinkedList() {
        super();
        head = null;
        tail = null;
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
        // Si la lista está vacía, establece el nuevo nodo como la cabeza y la cola
        if (isEmpty()) {
            head = node;
            tail = node;
            size++;
        } else {
            // Si la lista no está vacía, agrega el nuevo nodo al final de la lista
            tail.setNext(node);
            tail = node;
            size++;
        }
        return true;
    } catch (Exception e) {
        // Si ocurre una excepción, imprime el mensaje de error
        System.err.println(e.getMessage());
    }
    return false;
}

@Override
public boolean add(E[] array) {
    try {
        // Agrega cada elemento del array llamando al método add(element) definido anteriormente
        for (E element : array) {
            add(element);
        }
        return true;
    } catch (Exception e) {
        // Si ocurre una excepción, imprime el mensaje de error
        System.err.println("Error: " + e.getMessage());
        return false;
    }
}

@Override
public boolean add(Collection<E> collection) {
    try {
        // Obtiene un iterador para la colección y agrega cada elemento llamando al método add(element)
        Iterator<E> iterator = collection.iterator();
        while (iterator.hasNext()) {
            add(iterator.next());
        }
        return true;
    } catch (Exception e) {
        // Si ocurre una excepción, imprime el mensaje de error
        System.err.println("Error: " + e.getMessage());
        return false;
    }
}

@Override
public boolean clear() {
    try {
        // Elimina todas las referencias de nodos y restablece el tamaño de la lista a cero
        head = null;
        tail = null;
        size = 0;
        return true;
    } catch (Exception e) {
        // Si ocurre una excepción, imprime el mensaje de error
        System.err.println(e.getMessage());
    }
    return false;
}

@Override
public boolean contains(E element) {
    // Inicializa un nodo actual para recorrer la lista comenzando desde la cabeza
    LinkedNode<E> current = head;

    // Itera sobre la lista
    while (current != null) {
        // Verifica si el elemento del nodo actual es igual al elemento proporcionado
        if (current.get().equals(element)) {
            return true; // Devuelve true si se encuentra el elemento
        }
        // Avanza al siguiente nodo en la lista
        current = current.getNext();
    }
    return false; // Devuelve false si no se encuentra el elemento en la lista
}

@Override
public boolean contains(E[] array) {
    // Verifica si cada elemento del arreglo está contenido en la lista
    for (E element : array) {
        if (!contains(element)) {
            return false; // Devuelve false si algún elemento no está en la lista
        }
    }
    return true; // Devuelve true si todos los elementos están en la lista
}

@Override
public boolean contains(Collection<E> collection) {
    // Verifica si cada elemento de la colección está contenido en la lista
    boolean found = true;
    Iterator<E> iterator = collection.iterator();
    while (iterator.hasNext() && found) {
        E element = iterator.next();
        found = contains(element); // Actualiza found según si el elemento actual está en la lista
    }
    return found; // Devuelve true si todos los elementos de la colección están en la lista
}

@Override
public boolean isEmpty() {
    // Devuelve true si la lista está vacía (cabeza, cola, y tamaño son nulos o cero)
    return head == null && tail == null && size == 0;
}

 @Override
public boolean reverse() {
    // Verifica si la lista está vacía o contiene solo un elemento, en cuyo caso ya está invertida
    if (isEmpty() || size == 1) {
        return true; // Devuelve true sin hacer cambios si la lista es vacía o tiene solo un elemento
    }

    // Inicializa los nodos para revertir la lista
    LinkedNode<E> prev = null;
    LinkedNode<E> current = head;
    LinkedNode<E> next;

    // Itera sobre la lista, invirtiendo los enlaces de los nodos
    while (current != null) {
        next = current.getNext(); // Guarda el siguiente nodo para no perderlo
        current.setNext(prev); // Establece el siguiente nodo del nodo actual como el anterior
        prev = current; // Actualiza el nodo previo al nodo actual
        current = next; // Avanza al siguiente nodo en la lista
    }
    // Actualiza la cabeza y la cola de la lista después de invertirla
    tail = head; // La nueva cola es la cabeza original de la lista
    head = prev; // La nueva cabeza es el último nodo procesado (anterior)
    return true; // Devuelve true después de invertir la lista
}

@Override
public void forEach(Function<E, Void> action) {
    // Itera sobre cada elemento de la lista y aplica la función proporcionada
    Iterator<E> iter = this.iterator();
    while (iter.hasNext()) {
        action.apply(iter.next());
    }
}

@Override
public boolean addFirst(E element) {
    try {
        // Crea un nuevo nodo con el elemento proporcionado
        LinkedNode<E> newNode = new LinkedNode<>(element);
        // Si la lista está vacía, el nuevo nodo se convierte en la cabeza y la cola
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            // Si la lista no está vacía, el nuevo nodo se enlaza como el primer nodo y se convierte en la nueva cabeza
            newNode.setNext(head);
            head = newNode;
        }
        size++; // Incrementa el tamaño de la lista
        return true; // Devuelve true después de agregar el elemento correctamente
    } catch (Exception e) {
        // Si ocurre una excepción, imprime un mensaje de error
        System.err.println("Error: " + e.getMessage());
        return false; // Devuelve false si ocurre un error al agregar el elemento
    }
}

@Override
public boolean addFirst(E[] array) {
    try {
        // Agrega cada elemento del arreglo al principio de la lista llamando al método addFirst(element)
        for (E element : array) {
            addFirst(element);
        }
        return true; // Devuelve true después de agregar todos los elementos correctamente
    } catch (Exception e) {
        // Si ocurre una excepción, imprime un mensaje de error
        System.err.println("Error: " + e.getMessage());
        return false; // Devuelve false si ocurre un error al agregar los elementos
    }
}

@Override
public boolean addFirst(Collection<E> collection) {
    try {
        // Crea una nueva lista enlazada temporal para almacenar los elementos de la colección
        LinkedList<E> newList = new LinkedList<>();
        // Obtiene un iterador para recorrer los elementos de la colección
        Iterator<E> iterator = collection.iterator();
        // Itera sobre la colección y agrega cada elemento a la nueva lista enlazada
        while (iterator.hasNext()) {
            E element = iterator.next();
            newList.add(element);
            size++; // Incrementa el tamaño de la lista
        }
        // Enlaza la cola de la nueva lista al principio de la lista original
        newList.tail.setNext(head);
        // Establece la cabeza de la lista original como la cabeza de la nueva lista
        this.head = newList.head;
        return true; // Devuelve true después de agregar los elementos correctamente
    } catch (Exception e) {
        // Si ocurre una excepción, registra un mensaje de advertencia
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return false; // Devuelve false si ocurre un error al agregar los elementos
}

@Override
public E peek() {
    // Establece el nodo actual en la cabeza de la lista
    inode = head;
    
    // Mientras el nodo actual no sea nulo
    while (inode != null) {
        // Imprime el elemento del nodo actual
        System.out.println(inode.get());
        
        // Devuelve el elemento del nodo actual
        return inode.get(); 
    }
    
    // Si la lista está vacía, devuelve null
    return null;
}

@Override
public E peekLast() {
    // Verifica si la lista está vacía, en cuyo caso lanza una excepción NoSuchElementException
    if (isEmpty()) {
        throw new NoSuchElementException("The linked list is empty, cannot peek last");
    }
    // Devuelve el último elemento de la lista sin eliminarlo
    return tail.get();
}

@Override
public E[] peekArray(int n) {
    // Itera sobre los primeros 'n' elementos de la lista y los almacena en un arreglo
    LinkedNode<E> current = head;
    E[] array = (E[]) new Object[n];
    for (int i = 0; i < n && current != null; i++) {
        array[i] = current.get();
        current = current.getNext();
    }
    return array; // Devuelve el arreglo de 'n' elementos
}

@Override
public E[] peekLastArray(int n) {
    // Almacena los últimos 'n' elementos de la lista en un arreglo
    E[] array = (E[]) new Object[n];
    Object[] aux = peekArray(size); // Obtiene todos los elementos de la lista en un arreglo auxiliar
    int j = 0;
    // Itera sobre los elementos del arreglo auxiliar en orden inverso y los agrega al arreglo final
    for (int i = aux.length - 1; i >= 0 && j < n; i--) {
        array[j] = (E) aux[i];
        j++;
    }
    return array; // Devuelve el arreglo de 'n' elementos
}

 @Override
public List<E> peekCollection(int n) {
    try {
        // Inicia desde la cabeza de la lista enlazada
        LinkedNode<E> current = head;
        // Crea una lista para almacenar los primeros 'n' elementos
        List<E> save = new LinkedList<>();
        // Itera sobre los primeros 'n' elementos de la lista y los agrega a la lista de guardado
        for (int i = 0; i < n && current != null; i++) {
            save.add(current.get());
            current = current.getNext();
        }
        return save; // Devuelve la lista de elementos
    } catch (Exception e) {
        // Si ocurre una excepción, registra un mensaje de advertencia
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return null; // Devuelve null si hay un error al obtener los elementos
}

@Override
public List<E> peekLastCollection(int n) {
    try {
        // Ajusta 'n' al tamaño de la lista si es mayor que el tamaño
        if (n > size) {
            n = size;
        }
        // Inicia desde la cabeza de la lista enlazada
        LinkedNode<E> current = this.head;
        // Crea una nueva lista enlazada para almacenar los últimos 'n' elementos
        LinkedList<E> save = new LinkedList<>();
        // Mueve el puntero 'current' al nodo 'n' posiciones antes del final de la lista
        for (int e = 0; e < size - n; e++) {
            current = current.getNext();
        }
        // Itera sobre los últimos 'n' elementos de la lista y los agrega a la lista de guardado
        for (int i = 0; i < n && current != null; i++) {
            save.add(current.get());
            current = current.getNext();
        }
        return save; // Devuelve la lista de elementos
    } catch (Exception e) {
        // Si ocurre una excepción, registra un mensaje de advertencia
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return null; // Devuelve null si hay un error al obtener los elementos
}

@Override
public E poll() {
    try {
        // Verifica si la lista está vacía y devuelve null si lo está
        if (isEmpty()) {
            return null;
        }
        // Elimina el primer elemento de la lista y lo devuelve
        E removedElement = head.get();
        head = head.getNext();
        // Actualiza la cola si la lista tiene un solo elemento
        if (head == null) {
            tail = null;
        }
        size--; // Reduce el tamaño de la lista
        return removedElement; // Devuelve el elemento eliminado
    } catch (Exception e) {
        // Si ocurre una excepción, registra un mensaje de advertencia
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return null; // Devuelve null si hay un error al eliminar el elemento
}

@Override
public E pollLast() {
    // Verifica si la lista está vacía y devuelve null si lo está
    if (isEmpty()) {
        return null;
    }
    // Elimina el último elemento de la lista y lo devuelve
    E removedElement = (E) tail.get();
    // Actualiza la cabeza y la cola si la lista tiene un solo elemento
    if (size == 1) {
        head = null;
        tail = null;
    } else {
        LinkedNode<E> current = head;
        // Encuentra el nodo justo antes de la cola y actualiza la cola
        while (current.getNext() != tail) {
            current = current.getNext();
        }
        current.setNext(null);
        tail = current;
    }
    size--; // Reduce el tamaño de la lista
    return removedElement; // Devuelve el elemento eliminado
}

  @Override
public E[] pollArray(int n) {
    try {
        // Inicia desde la cabeza de la lista enlazada
        LinkedNode<E> current = head;
        // Crea un array para almacenar los elementos eliminados
        E[] save = (E[]) new Object[n];
        // Itera sobre los primeros 'n' elementos de la lista y los elimina
        for (int i = 0; i < n && current != null; i++) {
            save[i] = current.get();
            current = current.getNext();
            size--; // Reduce el tamaño de la lista
        }
        // Actualiza la cabeza y la cola si todos los elementos fueron eliminados
        if (size == 0) {
            head = null;
            tail = null;
        } else {
            if (size == 1) {
                head = current;
                tail = current;
            } else {
                head = current;
            }
        }
        return save; // Devuelve el array de elementos eliminados
    } catch (Exception e) {
        // Si ocurre una excepción, registra un mensaje de advertencia
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return null; // Devuelve null si hay un error al eliminar los elementos
}

@Override
public E[] pollLastArray(int n) {
    // Verifica si la lista está vacía o 'n' es inválido
    if (isEmpty() || n <= 0 || n > size) {
        throw new IllegalArgumentException("Invalid 'n' value");
    }
    // Crea un array para almacenar los elementos eliminados
    E[] removedArray = (E[]) new Object[n];
    // Itera sobre los últimos 'n' elementos de la lista y los elimina
    for (int i = 0; i < n && tail != null; i++) {
        removedArray[i] = pollLast();
    }
    return removedArray; // Devuelve el array de elementos eliminados
}

@Override
public List<E> pollCollection(int n) {
    // Obtiene una lista de los primeros 'n' elementos sin eliminarlos
    List<E> list = peekCollection(n);
    // Itera sobre los primeros 'n' elementos de la lista y los elimina
    LinkedNode<E> current = head;
    for (int i = 0; i < n; i++) {
        remove((E) current.get());
        current = current.getNext();
    }
    return list; // Devuelve la lista de elementos eliminados
}

@Override
public List<E> pollLastCollection(int n) {
    // Crea una lista para almacenar los elementos eliminados
    List<E> resultList = new LinkedList<>();
    // Verifica si la lista está vacía o 'n' es inválido
    if (isEmpty() || n <= 0) {
        return resultList;
    }
    // Inicia desde la cabeza de la lista enlazada y mantiene una referencia al nodo anterior
    LinkedNode<E> current = head;
    LinkedNode<E> previous = null;
    // Mueve 'current' al nodo 'n' posiciones antes del final de la lista
    for (int i = 0; i < size - n; i++) {
        previous = current;
        current = current.getNext();
    }
    // Itera sobre los últimos 'n' elementos de la lista y los elimina
    for (int i = 0; i < n && current != null; i++) {
        resultList.add((E) current.get());
        if (previous != null) {
            previous.setNext(current.getNext());
        } else {
            head = current.getNext();
        }
        current = current.getNext();
        if (current == null) {
            tail = previous;
        }
        size--; // Reduce el tamaño de la lista
    }
    return resultList; // Devuelve la lista de elementos eliminados
}

 @Override
public boolean remove(E element) {
    try {
        // Verifica si la lista está vacía o el elemento es nulo
        if (head == null || element == null) {
            return false;
        }
        // Verifica si el elemento está en la cabeza de la lista
        if (head.get().equals(element)) {
            head = head.getNext(); // Elimina el primer nodo
            size--;
            // Actualiza la cola si la lista queda vacía
            if (head == null) {
                tail = null;
            }
            return true;
        }
        // Busca el elemento en la lista y lo elimina si se encuentra
        LinkedNode<E> current = head;
        LinkedNode<E> last = null;
        while (current != null) {
            if (current.get().equals(element)) {
                if (last != null) {
                    last.setNext(current.getNext());
                }
                size--;
                // Actualiza la cola si se eliminó el último nodo
                if (current.getNext() == null) {
                    tail = last;
                }
                return true;
            }
            last = current;
            current = current.getNext();
        }
    } catch (Exception e) {
        // Registra una advertencia si ocurre una excepción
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return false; // Retorna false si no se encuentra el elemento o hay un error
}

@Override
public boolean remove(E[] array) {
    // Itera sobre el array y elimina cada elemento de la lista
    boolean removedAny = false;
    for (E element : array) {
        if (remove(element)) {
            removedAny = true;
        }
    }
    return removedAny; // Retorna true si se eliminó al menos un elemento
}

@Override
public boolean remove(Predicate<E> filter) {
    // Itera sobre los elementos de la lista y elimina el primero que cumple con el predicado
    for (Iterator<E> iter = this.iterator(); iter.hasNext();) {
        E element = iter.next();
        if (filter.test(element)) {
            remove(element);
            return true;
        }
    }
    return false; // Retorna false si ningún elemento cumple con el predicado
}

@Override
public boolean replace(E element, E newElement, Predicate<E> comparator) {
    // Reemplaza un elemento con otro si cumple con el predicado
    LinkedNode<E> current = head;
    boolean replaced = false;
    while (current != null) {
        if (comparator.test((E) current.get()) && current.get().equals(element)) {
            current.set(newElement);
            replaced = true;
        }
        current = current.getNext();
    }
    return replaced; // Retorna true si se realizó el reemplazo
}

@Override
public boolean replace(E[] array, E[] newArray, Predicate<E> comparator) {
    // Reemplaza cada elemento del array con el nuevo array si cumple con el predicado
    if (array.length != newArray.length) {
        return false; // Retorna false si los arrays tienen diferente longitud
    }
    boolean replaced = false;
    for (int i = 0; i < array.length; i++) {
        E currentElement = array[i];
        E newElement = newArray[i];
        if (comparator.test(currentElement)) {
            if (replace(currentElement, newElement, comparator)) {
                replaced = true;
            }
        }
    }
    return replaced; // Retorna true si se realizó al menos un reemplazo
}

@Override
public boolean replace(Collection<E> collection, Collection<E> newCollection, Predicate<E> comparator) {
    // Verifica si las colecciones tienen la misma cantidad de elementos
    if (collection.size() != newCollection.size()) {
        return false; // Retorna false si las colecciones tienen diferentes tamaños
    }
    boolean replaced = false;
    Iterator<E> iterator = collection.iterator();
    Iterator<E> newIterator = newCollection.iterator();
    // Itera sobre los elementos de las colecciones
    while (iterator.hasNext()) {
        E currentElement = iterator.next();
        E newElement = newIterator.next();
        // Reemplaza cada elemento si cumple con el predicado
        if (comparator.test(currentElement)) {
            if (replace(currentElement, newElement, comparator)) {
                replaced = true;
            }
        }
    }
    return replaced; // Retorna true si se realizó al menos un reemplazo
}

@Override
public boolean retain(E[] array) {
    LinkedNode<E> current = head;
    LinkedNode<E> previous = null;
    boolean elementsRetained = false;
    // Itera sobre los nodos de la lista
    while (current != null) {
        boolean elementContained = false;
        // Verifica si el elemento actual está contenido en el array
        for (E arrayElement : array) {
            if (arrayElement.equals(current.get())) {
                elementContained = true;
            }
        }
        // Si el elemento actual no está contenido en el array, lo elimina de la lista
        if (!elementContained) {
            if (previous == null) {
                head = current.getNext();
            } else {
                previous.setNext(current.getNext());
            }
            if (current == tail) {
                tail = previous;
                tail.setNext(null);
            }
            current = current.getNext();
            elementsRetained = true;
            size--;
        } else {
            previous = current;
            current = current.getNext();
        }
    }
    return elementsRetained; // Retorna true si se retuvieron al menos un elemento
}

@Override
public boolean retain(Collection<E> collection) {
    LinkedNode<E> current = head;
    LinkedNode<E> previous = null;
    boolean elementsRetained = false;

    // Itera sobre los nodos de la lista
    while (current != null) {
        // Verifica si el elemento actual está contenido en la colección
        boolean elementContained = collection.contains((E) current.get());
        // Si el elemento actual no está contenido en la colección, lo elimina de la lista
        if (!elementContained) {
            if (previous == null) {
                head = current.getNext();
            } else {
                previous.setNext(current.getNext());
            }
            if (current == tail) {
                tail = previous;
                tail.setNext(null);
            }
            current = current.getNext();
            elementsRetained = true;
            size--;
        } else {
            previous = current;
            current = current.getNext();
        }
    }
    return elementsRetained; // Retorna true si se retuvieron al menos un elemento
}


 @Override
public boolean set(E index, E element) {
    // Inicializa el nodo actual y el índice
    LinkedNode<E> current = head;
    int i = 0;
    // Itera sobre la lista hasta encontrar el nodo en el índice deseado
    while (current != null && i <= (int) index) {
        if (i == (int) index) {
            // Actualiza el elemento del nodo en el índice dado
            current.set(element);
            return true;
        }
        current = current.getNext();
        i++;
    }
    return false; // Retorna false si el índice está fuera de rango
}

@Override
public boolean sort(ToIntFunction<E> toInt) {
    // Verifica si la lista está vacía o tiene solo un elemento
    if (size <= 1) {
        return true; // Retorna true si la lista ya está ordenada
    }
    boolean swapped;
    do {
        swapped = false;
        // Inicializa los nodos actuales y anteriores
        LinkedNode<E> current = head;
        LinkedNode<E> next = head.getNext();
        LinkedNode<E> previous = null;
        // Itera sobre la lista
        while (next != null) {
            // Obtiene los valores enteros correspondientes a los elementos actuales y siguientes
            int currentIntValue = toInt.applyAsInt(current.get());
            int nextIntValue = toInt.applyAsInt(next.get());
            // Compara los valores enteros y realiza el intercambio si es necesario
            if (currentIntValue > nextIntValue) {
                if (previous == null) {
                    head = next;
                } else {
                    previous.setNext(next);
                }
                current.setNext(next.getNext());
                next.setNext(current);
                if (current.getNext() == null) {
                    tail = current;
                }
                swapped = true;
            }
            // Avanza los nodos actuales y siguientes
            previous = current;
            current = next;
            next = next.getNext();
        }
    } while (swapped); // Repite el proceso hasta que no se realicen intercambios
    tail.setNext(null); // Elimina cualquier enlace adicional al final
    return true; // Retorna true después de ordenar la lista
}

@Override
public List<E> subList(E from, E to) {
    // Inicializa el nodo actual y crea una nueva lista para almacenar la sublista
    LinkedNode<E> current = head;
    LinkedList<E> newList = new LinkedList<>();

    // Itera sobre la lista hasta encontrar el elemento "from"
    while (current != null && !current.get().equals(from)) {
        current = current.getNext();
    }

    // Continúa iterando y agregando elementos a la nueva lista hasta encontrar el elemento "to"
    while (current != null && !current.get().equals(to)) {
        newList.add(current.get());
        current = current.getNext();
    }

    // Agrega el elemento "to" a la nueva lista si se encuentra
    if (current != null) {
        newList.add(current.get());
    }

    return newList; // Retorna la sublista encontrada
}

 @Override
public E[] toArray() {
    // Inicializa un array del tamaño de la lista
    LinkedNode<E> current = head;
    E[] toArray = (E[]) new Object[size];
    // Llena el array con los elementos de la lista
    for (int i = 0; i < size; i++) {
        toArray[i] = current.get();
        current = current.getNext();
    }
    return toArray; // Retorna el array lleno con los elementos de la lista
}

@Override
public Iterator<E> iterator() {
    inode = head; // Establece el nodo actual como el primer nodo de la lista
    return new Iterator<E>() {
        public boolean hasNext() {
            return inode != null; // Verifica si hay un siguiente nodo en la lista
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the list.");
            }
            E element = inode.get(); // Obtiene el elemento del nodo actual
            inode = inode.getNext(); // Avanza al siguiente nodo en la lista
            return element; // Retorna el elemento del nodo actual
        }
    };
}

@Override
public int size() {
    // Devuelve el tamaño actual de la lista
    return size;
}

@Override
public boolean remove(Collection<E> collection) {
    // Si la lista está vacía o la colección pasada está vacía, no hay nada que eliminar
    if (isEmpty() || collection.isEmpty()) {
        return false;
    }

    // Indica si se ha eliminado algún elemento de la lista
    boolean removedAny = false;
    
    // Itera sobre los elementos de la colección
    Iterator<E> iterator = collection.iterator();
    while (iterator.hasNext()) {
        // Obtiene el elemento actual de la colección
        E elementToRemove = iterator.next();
        
        // Intenta eliminar el elemento de la lista
        if (remove(elementToRemove)) {
            // Si se elimina con éxito, establece el indicador de eliminación en true
            removedAny = true;
        }
    }
    
    // Devuelve true si se eliminó al menos un elemento de la lista
    return removedAny;
}

}