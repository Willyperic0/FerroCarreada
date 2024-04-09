package willy.priorityqueue;

import java.util.function.Function;
import willy.array.Array;
import willy.queue.list.Queue;
import willy.util.collection.Collection;
import willy.util.iterator.Iterator;
import willy.util.priorityqueue.AbstractPriorityQueue;

public class PriorityQueue<E> extends AbstractPriorityQueue<E> {
    private Array<Queue<E>> colas;
    private int prioridades;
 
    public PriorityQueue(int n){
        this.colas = new Array<>(n);
        this.prioridades = n;
        for (int i = 0; i < prioridades; i++) {
            colas.set(i, new Queue<E>());
        }
    }
 
    @Override
    public boolean insert(E element) {
        Queue<E> current = colas.get(prioridades - 1);
        return current.insert(element);
    }
 
    @Override
    public boolean insert(int index, E element) {
        Queue<E> current = colas.get(index);
        return current.insert(element);
    }
 
    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        E elementoPrioridad = null;
        for (int i = 0; i < prioridades; i++) {
            if (!colas.get(i).isEmpty()) {
                elementoPrioridad = colas.get(i).peek();
                break;
            }
        }
        for (int i = 0; i < prioridades; i++) {
            if (!colas.get(i).isEmpty()) {
                E currentElement = colas.get(i).peek();
                if (currentElement != null && currentElement.toString().compareTo(elementoPrioridad.toString()) > 0) {
                    elementoPrioridad = currentElement;
                }
            }
        }
        return elementoPrioridad;
    }
 
    @Override
    public E extract() {
        Queue<E> colaBuscada = null;
        for (int i = 0; i < prioridades; i++) {
            colaBuscada = colas.get(i);
            if (!colaBuscada.isEmpty()) {
                break;
            }
        }
        if (colaBuscada == null) {
            return null;
        }
        return colaBuscada.extract();
    }
 
    @Override
    public boolean clear() {
        for (int i = 0; i < prioridades; i++) {
            colas.get(i).clear();
        }
        return true;
    }
 
    @Override
    public boolean contains(E element) {
        for (int i = 0; i < prioridades; i++) {
            if (colas.get(i).contains(element)) {
                return true;
            }
        }
        return false;
    }
 
    @Override
    public boolean contains(E[] array) {
        for (int i = 0; i < prioridades; i++) {
            colas.get(i).contains(array);
        }
        return true;
    }
 
    @Override
    public boolean contains(Collection<E> collection) {
        for (int i = 0; i < prioridades; i++) {
            colas.get(i).contains(collection);
        }
        return true;
    }
 
    @Override
    public boolean isEmpty() {
        boolean vacia = true;
        for (int i = 0; i < prioridades; i++) {
            vacia &= colas.get(i).isEmpty();
            if (!vacia) {
                break;
            }
        }
        return vacia;
    }
 
    @Override
    public boolean reverse() {
        for (int i = 0; i < prioridades; i++) {
            colas.get(i).reverse();
        }
        return true;
    }
 
    @Override
    public int size() {
        int totalSize = 0;
        for (int i = 0; i < prioridades; i++) {
            totalSize += colas.get(i).size();
        }
        return totalSize;
    }
 
    @Override
    public void forEach(Function<E, Void> action) {
        for (int i = 0; i < prioridades; i++) {
            colas.get(i).forEach(action);
        }
    }
 
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int priorityIndex = 0;
            private Iterator<E> currentIterator = colas.get(0).iterator();
            @Override
            public boolean hasNext() {
                if (currentIterator.hasNext()) {
                    return true;
                } else {
                    priorityIndex++;
                    if (priorityIndex < prioridades) {
                        currentIterator = colas.get(priorityIndex).iterator();
                        return hasNext();
                    } else {
                        return false;
                    }
                }
            }
            @Override
            public E next() {
                return currentIterator.next();
            }
        };
    }
 
}
   
