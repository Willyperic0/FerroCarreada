package willy.linkedlist.doubly;

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

public class LinkedList <E> extends AbstractList<E> {
    private LinkedNode<E> head;
    private LinkedNode<E> tail;
    private LinkedNode<E> node;

    public LinkedList() {
        super();
        this.head = null;
        this.tail = null;
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

@Override
public boolean add(E element) {
    try {
        // Creamos un nuevo nodo con el elemento dado
        LinkedNode<E> newNode = new LinkedNode<>(element);
        // Si la lista esta vacia, el nuevo nodo se convierte tanto en la cabeza como en la cola
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            // Si la lista no esta vacia, ajustamos los punteros del nuevo nodo y de la cola
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode; // El nuevo nodo ahora es la nueva cola de la lista
            tail.setNext(null); // Aseguramos que el siguiente nodo despues de la cola sea nulo
        }
        size++; // Incrementamos el tamano de la lista
        return true; // Indicamos que se agrego exitosamente el elemento
    } catch (Exception e) {
        // Si ocurre una excepcion, la registramos y retornamos false
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        return false;
    }
}


 @Override
public boolean addFirst(E element) {
    try {
        // Creamos un nuevo nodo con el elemento dado
        LinkedNode<E> newNode = new LinkedNode<>(element);
        // Si la lista esta vacia, el nuevo nodo se convierte tanto en la cabeza como en la cola
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            // Si la lista no esta vacia, ajustamos los punteros del nuevo nodo y de la cabeza
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode; // El nuevo nodo ahora es la nueva cabeza de la lista
            head.setPrevious(null); // Aseguramos que el nodo anterior a la cabeza sea nulo
        }
        size++; // Incrementamos el tamano de la lista
        return true; // Indicamos que se agrego exitosamente el elemento
    } catch (Exception e) {
        // Si ocurre una excepcion, la registramos y retornamos false
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        return false;
    }
}


@Override
public boolean addFirst(Collection<E> collection) {
    try {
        // Obtenemos un iterador para recorrer la coleccion en orden inverso
        Iterator<E> iterator = collection.iterator();

        // Creamos una lista enlazada invertida para almacenar los elementos de la coleccion
        LinkedNode<E> reversedList = null;
        while (iterator.hasNext()) {
            E element = iterator.next();
            // Creamos un nuevo nodo con el elemento actual y lo agregamos al inicio de la lista invertida
            LinkedNode<E> newNode = new LinkedNode<>(element, reversedList, null);
            reversedList = newNode;
        }

        // Agregamos los elementos de la lista invertida al principio de la lista original
        LinkedNode<E> current = reversedList;
        while (current != null) {
            addFirst(current.get()); // Agregamos el elemento actual al principio de la lista original
            current = current.getNext(); // Avanzamos al siguiente nodo en la lista invertida
        }

        return true; // Indicamos que se agregaron exitosamente todos los elementos de la coleccion
    } catch (Exception e) {
        // Si ocurre una excepcion, la registramos y retornamos false
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        return false;
    }
}


@Override
public E peek() {
    try {
        return head.get(); // Retornamos el elemento en la cabeza de la lista
    } catch (Exception e) {
        // Si ocurre una excepcion, la registramos y retornamos null
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        return null;
    }
}


@Override
public E peekLast() {
    try {
        return tail.get(); // Retornamos el elemento en la cola de la lista
    } catch (Exception e) {
        // Si ocurre una excepcion, la registramos y retornamos null
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        return null;
    }
}


@Override
public List<E> peekCollection(int n) {
    try {
        LinkedNode<E> current = head;
        List<E> save = new LinkedList<>();
        // Recorremos la lista hasta obtener los primeros 'n' elementos o hasta llegar al final de la lista
        for (int i = 0; i < n && current != null; i++) {
            save.add(current.get()); // Agregamos el elemento actual a la lista 'save'
            current = current.getNext(); // Avanzamos al siguiente nodo en la lista
        }
        return save; // Retornamos la lista con los primeros 'n' elementos
    } catch (Exception e) {
        // Si ocurre una excepcion, la registramos y retornamos null
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        return null;
    }
}

@Override
public List<E> peekLastCollection(int n) {
    try {
        // Si 'n' es mayor que el tamano de la lista, lo ajustamos para que sea igual al tamano de la lista
        if (n > size) {
            n = size;
        }
        LinkedNode<E> current = this.head;
        LinkedList<E> save = new LinkedList<>();
        // Avanzamos hasta el nodo que corresponde al inicio de los ultimos 'n' elementos
        for (int e = 0; e < size - n; e++) {
            current = current.getNext();
        }
        // Agregamos los ultimos 'n' elementos a la lista 'save'
        for (int i = 0; i < n && current != null; i++) {
            save.add(current.get());
            current = current.getNext();
        }
        return save; // Retornamos la lista con los ultimos 'n' elementos
    } catch (Exception e) {
        // Si ocurre una excepcion, la registramos y retornamos null
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        return null;
    }
}

@Override
public E poll() {
    try {
        if (size == 0) { // Si la lista esta vacia, retornamos null
            return null;
        } else {
            E entrega = head.get(); // Obtenemos el primer elemento de la lista
            if (size == 1) { // Si la lista tiene un solo elemento, eliminamos la cabeza y la cola
                head = null;
                tail = null;
            } else {
                LinkedNode<E> next = head.getNext(); // Obtenemos el siguiente nodo despues de la cabeza
                next.setPrevious(null); // Desvinculamos el nodo siguiente de la cabeza
                head.setNext(null); // Desvinculamos la cabeza del siguiente nodo
                head = next; // El siguiente nodo se convierte en la nueva cabeza
            }
            size--; // Disminuimos el tamano de la lista
            return entrega; // Retornamos el primer elemento de la lista
        }
    } catch (Exception e) {
        // Si ocurre una excepcion, la registramos y retornamos null
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        return null;
    }
}


@Override
public E pollLast() {
    try {
        if (size == 0) { // Si la lista esta vacia, retornamos null
            return null;
        } else {
            E entrega = tail.get(); // Obtenemos el ultimo elemento de la lista
            if (size == 1) { // Si la lista tiene un solo elemento, eliminamos la cabeza y la cola
                head = null;
                tail = null;
            } else {
                LinkedNode<E> previous = tail.getPrevious(); // Obtenemos el nodo anterior a la cola
                previous.setNext(null); // Desvinculamos el nodo anterior de la cola
                tail.setPrevious(null); // Desvinculamos la cola del nodo anterior
                tail = previous; // El nodo anterior se convierte en la nueva cola
            }
            size--; // Disminuimos el tamano de la lista
            return entrega; // Retornamos el ultimo elemento de la lista
        }
    } catch (Exception e) {
        // Si ocurre una excepcion, la registramos y retornamos null
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        return null;
    }
}

@Override
public E[] pollArray(int n) {
    try {
        E[] save = (E[]) new Object[n]; // Creamos un nuevo arreglo para almacenar los elementos
        if (n < size) { // Si 'n' es menor que el tamano de la lista
            for (int i = 0; i < n; i++) {
                save[i] = poll(); // Eliminamos y almacenamos los primeros 'n' elementos en el arreglo
            }
        } else { // Si 'n' es mayor o igual que el tamano de la lista
            System.arraycopy(toArray(), 0, save, 0, size); // Copiamos todos los elementos de la lista al arreglo
            head = null; // Limpiamos la lista al establecer la cabeza y la cola como nulas
            tail = null;
            size = 0;
        }
        return save; // Retornamos el arreglo con los elementos eliminados
    } catch (Exception e) {
        // Si ocurre una excepcion, la registramos y retornamos null
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        return null;
    }
}


 @Override
public E[] pollLastArray(int n) {
    try {
        E[] save = (E[]) new Object[n]; // Creamos un nuevo arreglo para almacenar los elementos
        if (n < size) { // Si 'n' es menor que el tamano de la lista
            for (int i = n - 1; i >= 0; i--) {
                save[i] = pollLast(); // Eliminamos y almacenamos los ultimos 'n' elementos en el arreglo
            }
        } else { // Si 'n' es mayor o igual que el tamano de la lista
            System.arraycopy(toArray(), 0, save, 0, size); // Copiamos todos los elementos de la lista al arreglo
            head = null; // Limpiamos la lista al establecer la cabeza y la cola como nulas
            tail = null;
            size = 0;
        }
        return save; // Retornamos el arreglo con los elementos eliminados
    } catch (Exception e) {
        // Si ocurre una excepcion, la registramos y retornamos null
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        return null;
    }
}

@Override
public List<E> pollCollection(int n) {
    try {
        LinkedNode<E> current = head; // Nodo actual para recorrer la lista
        LinkedList<E> save = new LinkedList<>(); // Creamos una nueva lista para almacenar los elementos eliminados
        for (int i = 0; i < n && current != null; i++) { // Recorremos 'n' elementos de la lista
            save.add(current.get()); // Agregamos el elemento actual a la lista de almacenamiento
            current = current.getNext(); // Movemos al siguiente nodo
            size--; // Decrementamos el tamano de la lista
        }
        if (size == 0) { // Si la lista esta vacia despues de eliminar elementos
            head = null; // Establecemos la cabeza y la cola como nulas
            tail = null;
        } else {
            if (size == 1) { // Si queda un solo elemento en la lista
                head = current; // La cabeza y la cola apuntan al nodo actual
                head.setNext(null); // Desvinculamos el nodo actual
                head.setPrevious(null);
                tail = current;
                tail.setNext(null);
                tail.setPrevious(null);
            } else { // Si quedan mas de un elemento en la lista
                head = current; // La cabeza apunta al nodo actual
                head.setPrevious(null); // Desvinculamos la cabeza del nodo anterior
            }
        }
        return save; // Retornamos la lista de elementos eliminados
    } catch (Exception e) {
        // Si ocurre una excepcion, la registramos y retornamos null
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        return null;
    }
}


@Override
public List<E> pollLastCollection(int n) {
    List<E> save = new LinkedList<>(); // Creamos una nueva lista para almacenar los elementos eliminados
    if (n < size) { // Si 'n' es menor que el tamano de la lista
        for (int i = n - 1; i >= 0; i--) { // Iteramos 'n' veces desde el final de la lista
            save.addFirst(pollLast()); // Eliminamos el ultimo elemento y lo agregamos al inicio de la lista de almacenamiento
        }
    } else { // Si 'n' es mayor o igual que el tamano de la lista
        for (int i = 0; i < size; i++) { // Iteramos sobre todos los elementos de la lista
            save.addFirst(pollLast()); // Eliminamos el ultimo elemento y lo agregamos al inicio de la lista de almacenamiento
        }
        head = null; // Limpiamos la lista al establecer la cabeza y la cola como nulas
        tail = null;
        size = 0;
    }
    return save; // Retornamos la lista de elementos eliminados
}

@Override
public boolean remove(E element) {
    try {
        LinkedNode<E> current = head; // Nodo actual para recorrer la lista
        LinkedNode<E> previous = null; // Nodo anterior al actual
        while (current != null && !current.get().equals(element)) { // Buscamos el elemento en la lista
            previous = current; // Movemos el nodo anterior al nodo actual
            current = current.getNext(); // Movemos al siguiente nodo
        }
        if (current == null) { // Si no se encuentra el elemento en la lista
            return false; // Retornamos false
        }
        if (current == head) { // Si el elemento esta al principio de la lista
            head = current.getNext(); // Movemos la cabeza al siguiente nodo
            head.setPrevious(null); // Desvinculamos la cabeza del nodo anterior
        } else { // Si el elemento esta en el medio o al final de la lista
            if (current == tail) { // Si el elemento esta al final de la lista
                previous.setNext(current.getNext()); // Desvinculamos el nodo actual del nodo anterior
                tail = previous; // Movemos la cola al nodo anterior
                tail.setNext(null); // Desvinculamos la cola del nodo siguiente
            } else { // Si el elemento esta en el medio de la lista
                previous.setNext(current.getNext()); // Desvinculamos el nodo actual del nodo anterior
                current.getNext().setPrevious(previous); // Desvinculamos el nodo siguiente del nodo actual
            }
        }
        size--; // Decrementamos el tamano de la lista
        return true; // Retornamos true
    } catch (Exception e) { // Si ocurre una excepcion
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e); // Registramos la excepcion
    }
    return false; // Retornamos false
}


 @Override
public boolean replace(E element, E newElement, Predicate<E> comparator) {
    try {
        LinkedNode<E> current = head; // Nodo actual para recorrer la lista
        boolean elementReplaced = false; // Variable para controlar si se reemplazo algun elemento
        while (current != null) { // Iteramos sobre la lista
            if (comparator.test(current.get()) && current.get().equals(element)) { // Verificamos si el elemento actual cumple con el predicado y es igual al elemento buscado
                current.set(newElement); // Reemplazamos el elemento actual por el nuevo elemento
                elementReplaced = true; // Marcamos que se ha realizado el reemplazo
            }
            current = current.getNext(); // Movemos al siguiente nodo
        }
        return elementReplaced; // Retornamos si se reemplazo algun elemento
    } catch (Exception e) { // Si ocurre una excepcion
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e); // Registramos la excepcion
    }
    return false; // Retornamos false si no se pudo realizar el reemplazo
}

@Override
public boolean retain(E[] array) {
    LinkedNode<E> current = head; // Nodo actual para recorrer la lista
    LinkedNode<E> previous = null; // Nodo anterior al actual
    boolean elementsRetained = false; // Variable para controlar si se retuvieron algunos elementos
    while (current != null) { // Iteramos sobre la lista
        boolean elementContained = false; // Variable para controlar si el elemento actual esta contenido en el array
        for (E arrayElement : array) { // Iteramos sobre el array
            if (arrayElement.equals(current.get())) { // Verificamos si el elemento actual esta contenido en el array
                elementContained = true; // Marcamos que el elemento esta contenido en el array
                break; // Salimos del bucle si encontramos el elemento en el array
            }
        }
        if (!elementContained) { // Si el elemento actual no esta contenido en el array
            if (previous == null) { // Si el elemento actual es la cabeza de la lista
                head = current.getNext(); // Movemos la cabeza al siguiente nodo
                head.setPrevious(null); // Desvinculamos la cabeza del nodo anterior
            } else { // Si el elemento actual no es la cabeza de la lista
                previous.setNext(current.getNext()); // Desvinculamos el nodo actual del nodo anterior
            }
            if (current == tail) { // Si el elemento actual es la cola de la lista
                tail = previous; // Movemos la cola al nodo anterior
                tail.setNext(null); // Desvinculamos la cola del nodo siguiente
            }
            current = current.getNext(); // Movemos al siguiente nodo
            elementsRetained = true; // Marcamos que se ha retenido algun elemento
            size--; // Decrementamos el tamano de la lista
        } else { // Si el elemento actual esta contenido en el array
            previous = current; // Movemos el nodo anterior al nodo actual
            current = current.getNext(); // Movemos al siguiente nodo
        }
    }
    return elementsRetained; // Retornamos si se retuvieron algunos elementos
}


 @Override
public boolean retain(Collection<E> collection) {
    LinkedNode<E> current = head; // Nodo actual para recorrer la lista
    LinkedNode<E> previous = null; // Nodo anterior al actual
    boolean elementsRetained = false; // Variable para controlar si se retuvieron algunos elementos
    while (current != null) { // Iteramos sobre la lista
        if (!collection.contains(current.get())) { // Verificamos si el elemento actual no esta contenido en la coleccion
            if (previous == null) { // Si el elemento actual es la cabeza de la lista
                head = current.getNext(); // Movemos la cabeza al siguiente nodo
                head.setPrevious(null); // Desvinculamos la cabeza del nodo anterior
            } else { // Si el elemento actual no es la cabeza de la lista
                previous.setNext(current.getNext()); // Desvinculamos el nodo actual del nodo anterior
            }
            if (current == tail) { // Si el elemento actual es la cola de la lista
                tail = previous; // Movemos la cola al nodo anterior
                tail.setNext(null); // Desvinculamos la cola del nodo siguiente
            }
            current = current.getNext(); // Movemos al siguiente nodo
            elementsRetained = true; // Marcamos que se ha retenido algun elemento
            size--; // Decrementamos el tamano de la lista
        } else { // Si el elemento actual esta contenido en la coleccion
            previous = current; // Movemos el nodo anterior al nodo actual
            current = current.getNext(); // Movemos al siguiente nodo
        }
    }
    return elementsRetained; // Retornamos si se retuvieron algunos elementos
}

@Override
public boolean set(E index, E element) {
    try {
        boolean set = false; // Variable para controlar si se realizo la operacion de establecimiento
        LinkedNode<E> current = head; // Nodo actual para recorrer la lista
        while (current.get() != index && !set) { // Buscamos el nodo con el indice dado
            current = current.getNext(); // Movemos al siguiente nodo
        }
        current.set(element); // Establecemos el elemento en el nodo encontrado
        return true; // Retornamos true para indicar que se realizo la operacion de establecimiento
    } catch (Exception e) { // Si ocurre una excepcion
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e); // Registramos la excepcion
    }
    return false; // Retornamos false si no se pudo realizar la operacion de establecimiento
}


  @Override
public boolean sort(ToIntFunction<E> toInt) {
    if (size <= 1) { // Si la lista tiene 0 o 1 elemento, ya esta ordenada
        return true;
    }
    boolean swapped;
    do {
        swapped = false; // Marcador para controlar si se realizo un intercambio en el bucle
        LinkedNode<E> current = head;
        LinkedNode<E> next = head.getNext();
        LinkedNode<E> previous = null;
        while (next != null) {
            int currentIntValue = toInt.applyAsInt((E) current.get());
            int nextIntValue = toInt.applyAsInt((E) next.get());
            if (currentIntValue > nextIntValue) { // Si el valor del elemento actual es mayor que el valor del siguiente elemento
                if (previous == null) { // Si el elemento actual es la cabeza de la lista
                    head = next; // Establecemos el siguiente elemento como la nueva cabeza de la lista
                } else { // Si el elemento actual no es la cabeza de la lista
                    previous.setNext(next); // Establecemos el siguiente elemento del nodo anterior al siguiente nodo del elemento actual
                }
                current.setNext(next.getNext()); // Establecemos el siguiente elemento del elemento actual al siguiente elemento del siguiente nodo
                next.setNext(current); // Establecemos el siguiente elemento del siguiente nodo al elemento actual
                if (current.getNext() == null) { // Si el siguiente elemento del elemento actual es nulo, significa que el elemento actual es la cola de la lista
                    tail = current; // Establecemos el elemento actual como la nueva cola de la lista
                }
                swapped = true; // Marcamos que se realizo un intercambio
            }
            previous = current;
            current = next;
            next = next.getNext(); // Movemos al siguiente par de nodos
        }
    } while (swapped); // Repetimos el bucle hasta que no se realicen mas intercambios
    tail.setNext(null); // Establecemos el siguiente elemento de la cola como nulo para evitar ciclos en la lista
    return true; // Retornamos true indicando que la lista ha sido ordenada
}

@Override
public List<E> subList(E from, E to) {
    LinkedNode<E> current = head; // Nodo actual para recorrer la lista
    LinkedList<E> newList = new LinkedList<>(); // Lista para almacenar la sublista
    while (current != null && !current.get().equals(from)) { // Buscamos el elemento "from" en la lista
        current = current.getNext();
    }
    do {
        newList.add(current.get()); // Agregamos el elemento actual a la sublista
        current = current.getNext(); // Movemos al siguiente elemento
    } while (current.get() != to); // Repetimos hasta encontrar el elemento "to"
    return newList; // Retornamos la sublista
}

@Override
public boolean clear() {
    try {
        head.setNext(null); // Establecemos el siguiente elemento de la cabeza como nulo
        tail.setPrevious(null); // Establecemos el elemento anterior de la cola como nulo
        head = null; // Eliminamos la referencia a la cabeza
        tail = null; // Eliminamos la referencia a la cola
        size = 0; // Establecemos el tamano de la lista como 0
        return true; // Retornamos true indicando que la lista ha sido limpiada con exito
    } catch (Exception e) { // Si ocurre una excepcion
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e); // Registramos la excepcion
    }
    return false; // Retornamos false si no se pudo limpiar la lista
}


 @Override
public boolean reverse() {
    LinkedNode<E> current = head; // Nodo actual para recorrer la lista
    Iterator<E> iterator = this.iterator(); // Iterador para recorrer la lista en sentido inverso
    E[] reverso = (E[]) new Object[size]; // Arreglo para almacenar los elementos en orden inverso
    int i = 1; // indice para almacenar los elementos en el arreglo en orden inverso
    while (iterator.hasNext()) { // Mientras haya elementos en la lista
        E element = iterator.next(); // Obtenemos el siguiente elemento de la lista
        reverso[size - i] = element; // Almacenamos el elemento en el arreglo en orden inverso
        i++; // Incrementamos el indice
    }
    for (E element : reverso) { // Recorremos el arreglo en orden inverso
        current.set(element); // Establecemos el valor del nodo actual como el elemento del arreglo
        current = current.getNext(); // Movemos al siguiente nodo
    }
    return true; // Retornamos true indicando que la lista ha sido invertida correctamente
}

@Override
public void forEach(Function<E, Void> action) {
    LinkedNode<E> current = head; // Nodo actual para recorrer la lista
    while (current != null) { // Mientras haya elementos en la lista
        E element = (E) action.apply(current.get()); // Aplicamos la funcion a cada elemento de la lista
        current.set(element); // Establecemos el valor del nodo actual como el resultado de la funcion aplicada al elemento
        current = current.getNext(); // Movemos al siguiente nodo
    }
}

@Override
public Iterator<E> iterator() {
    return new Iterator<E>() { // Creamos una nueva instancia de Iterator
        LinkedNode<E> current = head; // Nodo actual para recorrer la lista

        @Override
        public boolean hasNext() { // Metodo para verificar si hay mas elementos en la lista
            return current != null; // Retornamos true si el nodo actual no es nulo
        }

        @Override
        public E next() { // Metodo para obtener el siguiente elemento de la lista
            if (!hasNext()) { // Verificamos si hay mas elementos en la lista
                throw new NoSuchElementException("No more elements to list"); // Lanzamos una excepcion si no hay mas elementos
            }
            E element = current.get(); // Obtenemos el elemento del nodo actual
            current = current.getNext(); // Movemos al siguiente nodo
            return element; // Retornamos el elemento obtenido
        }
    };
}


@Override
public boolean contains(E element) {
    LinkedNode<E> current = head; // Nodo actual para recorrer la lista
    while (current != null) { // Mientras haya elementos en la lista
        if (current.get().equals(element)) { // Comparamos el elemento del nodo actual con el elemento buscado
            return true; // Si son iguales, retornamos true indicando que el elemento esta en la lista
        }
        current = current.getNext(); // Movemos al siguiente nodo
    }
    return false; // Si llegamos al final de la lista sin encontrar el elemento, retornamos false
}

@Override
public boolean contains(E[] array) {
    if (array == null || array.length == 0) { // Verificamos si el arreglo es nulo o vacio
        return false; // Si es asi, no hay elementos para buscar, asi que retornamos false
    }

    for (E element : array) { // Iteramos sobre cada elemento del arreglo
        if (!contains(element)) { // Verificamos si el elemento actual no esta contenido en la lista
            return false; // Si no esta contenido, retornamos false
        }
    }

    return true; // Si todos los elementos del arreglo estan contenidos en la lista, retornamos true
}

@Override
public boolean contains(Collection<E> collection) {
    LinkedNode<E> current = head; // Nodo actual para recorrer la lista
    Iterator<E> iter = collection.iterator(); // Iterador para recorrer la coleccion
    int c = 0; // Contador para contar los elementos coincidentes encontrados
    while (iter.hasNext()) { // Mientras haya elementos en la coleccion
        E element = iter.next(); // Obtenemos el siguiente elemento de la coleccion
        if (current != null && current.get().equals(element)) { // Comparamos el elemento actual de la lista con el elemento de la coleccion
            c++; // Incrementamos el contador si hay una coincidencia
        }
        current = current.getNext(); // Movemos al siguiente nodo
    }
    if (c == collection.size()) { // Verificamos si el numero de coincidencias es igual al tamano de la coleccion
        return true; // Si es asi, todos los elementos de la coleccion estan contenidos en la lista, asi que retornamos true
    }
    return false; // Si no, retornamos false
}


 @Override
public boolean isEmpty() {
    // Verificamos si la cabeza, la cola, el nodo y el tamano son todos nulos o iguales a cero
    return head == null && tail == null && node == null && size == 0;
}

@Override
public int size() {
    // Retornamos el tamano de la lista
    return size;
}

@Override
public boolean add(E[] array) {
    if (array == null || array.length == 0) { // Verificamos si el arreglo es nulo o vacio
        return false; // Si es asi, no hay elementos para agregar, asi que retornamos false
    }

    for (E element : array) { // Iteramos sobre cada elemento del arreglo
        add(element); // Agregamos el elemento a la lista
    }
    return true; // Retornamos true indicando que todos los elementos fueron agregados correctamente
}

@Override
public boolean add(Collection<E> collection) {
    if (collection == null || collection.isEmpty()) { // Verificamos si la coleccion es nula o vacia
        return false; // Si es asi, no hay elementos para agregar, asi que retornamos false
    }

    Iterator<E> iterator = collection.iterator(); // Creamos un iterador para recorrer la coleccion
    while (iterator.hasNext()) { // Mientras haya elementos en la coleccion
        add(iterator.next()); // Agregamos el proximo elemento de la coleccion a la lista
    }

    return true; // Retornamos true indicando que todos los elementos fueron agregados correctamente
}

@Override
public E[] peekArray(int n) {
    LinkedNode current = head; // Iniciamos en la cabeza de la lista
    E array[]; // Creamos un arreglo para almacenar los elementos
    array = (E[]) new Object[n]; // Inicializamos el arreglo con el tamano especificado
    for (int i = 0; i < n && current != null; i++) { // Iteramos hasta n o hasta que lleguemos al final de la lista
        array[i] = (E) current.get(); // Agregamos el elemento actual al arreglo
        current = current.getNext(); // Avanzamos al siguiente nodo
    }
    return array; // Retornamos el arreglo
}

@Override
public E[] peekLastArray(int n) {
    E array[]; // Creamos un arreglo para almacenar los elementos
    array = (E[]) new Object[n]; // Inicializamos el arreglo con el tamano especificado
    Object[] aux = peekArray(size); // Obtenemos un arreglo con todos los elementos de la lista
    int j = 0;
    for (int i = aux.length - 1; i >= 0 && j < n; i--) { // Iteramos desde el ultimo elemento del arreglo auxiliar
        array[j] = (E) aux[i]; // Copiamos el elemento al arreglo resultante
        j++;
    }
    return array; // Retornamos el arreglo resultante
}

@Override
public boolean remove(E[] array) {
    if (array == null || array.length == 0) { // Verificamos si el arreglo es nulo o vacio
        return false; // Si es asi, no hay elementos para remover, asi que retornamos false
    }

    boolean removed = false; // Inicializamos la bandera de remocion como falsa
    for (E element : array) { // Iteramos sobre cada elemento del arreglo
        removed |= remove(element); // Intentamos remover el elemento de la lista y actualizamos la bandera de remocion
    }

    return removed; // Retornamos la bandera de remocion
}

@Override
public boolean remove(Collection<E> collection) {
    if (isEmpty() || collection.isEmpty()) { // Verificamos si la lista o la coleccion son vacias
        return false; // Si es asi, no hay elementos para remover, asi que retornamos false
    }

    boolean removedAny = false; // Inicializamos la bandera de remocion como falsa
    Iterator<E> iterator = collection.iterator(); // Creamos un iterador para recorrer la coleccion
    while (iterator.hasNext()) { // Mientras haya elementos en la coleccion
        E elementToRemove = iterator.next(); // Obtenemos el proximo elemento de la coleccion
        if (remove(elementToRemove)) { // Intentamos remover el elemento de la lista
            removedAny = true; // Si se removio algun elemento, actualizamos la bandera de remocion
        }
    }
    return removedAny; // Retornamos la bandera de remocion
}

@Override
public boolean remove(Predicate<E> filter) {
    boolean removed = false; // Inicializamos la bandera de remocion como falsa
    for (Iterator<E> iter = this.iterator(); iter.hasNext();) { // Iteramos sobre los elementos de la lista
        E element = iter.next(); // Obtenemos el proximo elemento
        if (filter.test(element)) { // Verificamos si el elemento cumple con el predicado
            remove(element); // Si es asi, removemos el elemento de la lista
            removed = true; // Actualizamos la bandera de remocion
        }
    }
    return removed; // Retornamos la bandera de remocion
}


 @Override
public boolean replace(E[] array, E[] newArray, Predicate<E> comparator) {
    if (array == null || newArray == null || array.length != newArray.length) {
        return false; // Si los arreglos son nulos o tienen diferentes longitudes, no podemos realizar la operacion de reemplazo, asi que retornamos false
    }

    boolean replaced = false; // Inicializamos una bandera para indicar si se realizo algun reemplazo
    for (int i = 0; i < array.length; i++) { // Iteramos sobre cada elemento de los arreglos
        E currentElement = array[i]; // Obtenemos el elemento actual del primer arreglo
        E newElement = newArray[i]; // Obtenemos el nuevo elemento correspondiente del segundo arreglo
        replaced |= replace(currentElement, newElement, comparator); // Intentamos realizar el reemplazo y actualizamos la bandera si se realizo algun reemplazo
    }

    return replaced; // Retornamos true si se realizo al menos un reemplazo, de lo contrario, retornamos false
}

@Override
public boolean replace(Collection<E> collection, Collection<E> newCollection, Predicate<E> comparator) {
    if (collection == null || newCollection == null || collection.size() != newCollection.size()) {
        return false; // Si las colecciones son nulas o tienen diferentes tamanos, no podemos realizar la operacion de reemplazo, asi que retornamos false
    }

    boolean replaced = false; // Inicializamos una bandera para indicar si se realizo algun reemplazo
    Iterator<E> currentIterator = collection.iterator(); // Creamos un iterador para la coleccion original
    Iterator<E> newIterator = newCollection.iterator(); // Creamos un iterador para la nueva coleccion

    while (currentIterator.hasNext() && newIterator.hasNext()) { // Mientras haya elementos en ambas colecciones
        E currentElement = currentIterator.next(); // Obtenemos el proximo elemento de la coleccion original
        E newElement = newIterator.next(); // Obtenemos el proximo elemento de la nueva coleccion
        replaced |= replace(currentElement, newElement, comparator); // Intentamos realizar el reemplazo y actualizamos la bandera si se realizo algun reemplazo
    }

    return replaced; // Retornamos true si se realizo al menos un reemplazo, de lo contrario, retornamos false
}

@Override
public E[] toArray() {
    E[] array = (E[]) new Object[size]; // Creamos un nuevo arreglo del tamano de la lista
    LinkedNode current = head; // Inicializamos el nodo actual como la cabeza de la lista
    int i = 0; // Inicializamos el indice del arreglo

    while (current != null) { // Mientras haya elementos en la lista
        array[i] = (E) current.get(); // Anadimos el elemento actual al arreglo
        current = current.getNext(); // Avanzamos al siguiente nodo
        i++; // Incrementamos el indice del arreglo
    }

    return array; // Retornamos el arreglo con los elementos de la lista
}

@Override
public boolean addFirst(E[] array) {
    if (array == null || array.length == 0) {
        return false; // Si el arreglo es nulo o vacio, no hay elementos para agregar, asi que retornamos false
    }

    for (E element : array) { // Iteramos sobre cada elemento del arreglo
        addFirst(element); // Agregamos el elemento al principio de la lista
    }
    return true; // Retornamos true indicando que todos los elementos fueron agregados correctamente
}


}