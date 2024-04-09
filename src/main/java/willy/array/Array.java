package willy.array;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import willy.util.array.AbstractArray;
import willy.util.collection.Collection;
import willy.util.iterator.Iterator;

public class Array<E> extends AbstractArray<E> {
   private E[] elements;

    public Array(int dimension) {
        /*
        -Este constructor crea un nuevo objeto Array.
        -Toma un parmetro dimension, que indica el tamano del array que se va a crear.
        -Dentro del aconstructor, se inicializa el atributo elements como un array de tipo Object 
            con la dimension especificada.
         -Se realiza un "casting" ((E[])) para convertir este array de Object a un array del tipo generico E. 
            Es importante tener en cuenta que esto puede generar una advertencia de tipo ("unchecked cast"), 
            ya que el tipo generico no se conserva en tiempo de ejecucion.
        */
        this.elements = (E[]) new Object[dimension];
    }

    public Array(E[] array) {
        /*
        -Este constructor tambien crea un nuevo objeto Array.
        -Toma un parametro array, que es un array del tipo generico E.
        -Dentro del constructor, se inicializa el atributo elements con 
            el array que se pasa como argumento.
        -Esto significa que el array interno elements contendra 
            los mismos elementos que el array pasado como argumento,
            y ambos arrays apuntaran a los mismos elementos en 
            memoria. Es decir, no se crea una copia de los elementos.
        */
        this.elements = array;
    }
    
/**
 * Elimina todos los elementos del array.
 * @return true si se eliminan los elementos correctamente, false si ocurre una excepcion.
 */
@Override
public boolean clear() {
    try {
        // Itera sobre todos los elementos del array
        for (int i = 0; i < elements.length; i++) {
            // Asigna null al elemento en la posicion actual del array
            elements[i] = null;
        }
        // Retorna true si no ocurre ninguna excepcion durante el proceso
        return true;
    } catch (Exception e) {
        // En caso de excepcion, registra un mensaje de advertencia
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    // Retorna false si ocurre una excepcion
    return false;
}

  /**
 * Verifica si el elemento especificado esta presente en el array.
 * @param element El elemento que se desea buscar en el array.
 * @return true si el elemento esta presente en el array, false en caso contrario.
 */
@Override
public boolean contains(E element) {
    // Itera sobre todos los elementos del array
    for (int i = 0; i < elements.length; i++) {
        // Compara el elemento actual con el elemento especificado
        if (elements[i].equals(element)) {
            // Si se encuentra una coincidencia, retorna true
            return true;
        }
    }
    // Si no se encuentra el elemento en ninguna posicion, retorna false
    return false;
}


    /**
 * Verifica si todos los elementos del array especificado estan presentes en el array actual.
 * @param array El array cuyos elementos se desean verificar en el array actual.
 * @return true si todos los elementos del array estan presentes en el array actual, false en caso contrario.
 */
@Override
public boolean contains(E[] array) {
    try {
        // Itera sobre todos los elementos del array pasado como argumento
        for(int i = 0; i < array.length; i++){
            // Verifica si el elemento actual del array pasado como argumento esta presente en el array actual
            if (this.contains(array[i]) == false) {
                // Si uno de los elementos no esta presente, retorna false
                return false;
            }
        }
        // Si todos los elementos estan presentes, retorna true
        return true;
    } 
    catch (Exception e) {
        // Si ocurre una excepcion, imprime un mensaje de error y retorna false
        System.err.println(e.getMessage());
        return false;
    }
}


/**
 * Verifica si todos los elementos de la coleccion especificada estan presentes en el array actual.
 * @param collection La coleccion cuyos elementos se desean verificar en el array actual.
 * @return true si todos los elementos de la coleccion estan presentes en el array actual, false en caso contrario.
 */
@Override
public boolean contains(Collection<E> collection) {
    // Obtiene un iterador para la coleccion pasada como argumento
    Iterator<E> iterator = collection.iterator();
    // Itera sobre todos los elementos de la coleccion
    while (iterator.hasNext()) {
        // Verifica si el elemento actual de la coleccion esta presente en el array actual
        if (contains(iterator.next()) == false) {
            // Si uno de los elementos no esta presente, retorna false
            return false;
        }
    }
    // Si todos los elementos estan presentes, retorna true
    return true;
}


/**
 * Verifica si el array esta vacio, es decir, si no contiene ningun elemento.
 * @return true si el array esta vacio, false si contiene al menos un elemento.
 */
@Override
public boolean isEmpty() {
    int nullCount = 0;
    for(int i = 0; i < size(); i++){
        // Verifica si el elemento en la posicion actual es nulo
        if (elements[i] == null) {
            nullCount++;
        }
    }
    // Si la cantidad de elementos nulos es igual al tamano del array, entonces esta vacio
    if (nullCount == elements.length){
        return true;
    } else {
        return false;
    }
}


/**
 * Invierte el orden de los elementos en el array.
 * @return true si la reversion se realiza correctamente, false si ocurre una excepcion.
 */
@Override
public boolean reverse() {
    try {
        E t;
        for (int i = 0; i < size() / 2; i++) {
            // Intercambia el elemento en la posicion i con el elemento en la posicion (length - i - 1)
            t = elements[i];
            elements[i] = elements[elements.length - i - 1];
            elements[elements.length - i - 1] = t;
        }
        return true;
    } catch (Exception e) {
        // En caso de excepcion, registra un mensaje de advertencia
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        return false;
    }
}


/**
 * Retorna el tamano del array, es decir, el numero de elementos que contiene.
 * @return El tamano del array.
 */
@Override
public int size() {
    int count = 0;
    for (int i =0; i < elements.length; i++){
    if (elements[i] != null){
        count++;
    }
}
    return count;
}


/**
 * Itera sobre todos los elementos del array y aplica la accion especificada a cada uno.
 * @param action La accion a aplicar a cada elemento del array.
 */
@Override
public void forEach(Function<E, Void> action) {
    for (E element : elements) {
        action.apply(element);
    }
}


    @Override
/**
 * Devuelve un iterador que permite iterar sobre los elementos del array.
 * @return Un iterador para el array.
 */
public Iterator<E> iterator() {
    // Se crea un nuevo objeto Iterator<E> utilizando una clase anonima
    return new Iterator<E>() {
        // Se inicializa el indice del iterador
        private int index = 0;

        @Override
        public boolean hasNext() {
            // Verifica si aun hay elementos por iterar
            return index < elements.length;
        }

        @Override
        public E next() {
            // Retorna el proximo elemento del array y avanza al siguiente indice
            return elements[index++];
        }
    };
}


    /**
 * Intenta agregar un elemento al array.
 * @param element El elemento que se desea agregar al array.
 * @return true si se agrega el elemento correctamente, false si no hay espacio disponible en el array.
 */
@Override
public boolean add(E element) {
    // Itera sobre todos los elementos del array
    for (int i = 0; i < elements.length; i++) {
        // Busca la primera posicion nula en el array
        if (elements[i] == null) {
            // Cuando encuentra una posicion nula, asigna el elemento y retorna true
            elements[i] = element;
            return true;
        }
    }
    // Si no hay posiciones nulas disponibles en el array, retorna false
    return false;
}


/**
 * Intenta agregar un array de elementos en una posicion especifica del array actual.
 * @param index La posicion en la que se desea agregar el array de elementos.
 * @param array El array de elementos que se desea agregar.
 * @return true si se agrega el array de elementos correctamente, false si ocurre una excepcion.
 */
@Override
public boolean add(int index, E[] array) {
    try {
        // Itera sobre todos los elementos del array pasado como argumento
        for (int i = 0; i < array.length; i++) {
            // Asigna cada elemento del array pasado como argumento a la posicion especificada en el array actual
            elements[index] = array[i];
            // Incrementa el indice para la proxima posicion del array actual
            index++;
        }
        return true;
    } catch (Exception e) {
        // En caso de excepcion, registra un mensaje de advertencia
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        return false;
    }
}


/**
 * Intenta agregar todos los elementos de una coleccion en una posicion especifica del array.
 * @param index La posicion en la que se desea agregar los elementos en el array.
 * @param collection La coleccion de elementos que se desea agregar al array.
 * @return true si se agregan los elementos correctamente, false si no se pueden agregar por alguna razon.
 */
@Override
public boolean add(int index, Collection<E> collection) {
    // Se obtiene un iterador para la coleccion
    Iterator<E> collectionIterator = collection.iterator();
    // Variable para llevar el conteo de la posicion dentro del array
    int i = 0;
    // Se verifica si la posicion index es valida y si hay suficiente espacio en el array
    if (index < elements.length && index >= 0 && this.elements.length <= collection.size() && (collection.size() + index) <= elements.length) {
        try {
            // Se itera sobre todos los elementos de la coleccion
            while (collectionIterator.hasNext()) {
                // Se obtiene el siguiente elemento de la coleccion
                E element = collectionIterator.next();
                // Se agrega el elemento en la posicion index + i del array
                elements[index + i] = element;
                // Se incrementa i para avanzar al siguiente indice del array
                i++;
            }
            // Se retorna true para indicar que se agregaron los elementos correctamente
            return true;
        } catch (Exception e) {
            // En caso de excepcion, se registra un mensaje de advertencia
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
    }
    // Si no se cumplen las condiciones para agregar los elementos, se retorna false
    return false;
}


  /**
 * Reorganiza los elementos del array, colocando todos los elementos no nulos al principio y los elementos nulos al final.
 */
@Override
public void defragment() {
    E t;
    // Itera sobre todos los elementos del array
    for (int i = 0; i < size(); i++) {
        // Si el elemento actual es nulo
        if (elements[i] == null) {
            // Busca el proximo elemento no nulo desde la posicion i+1
            for (int j = i + 1; j < size(); j++) {
                // Si encuentra un elemento no nulo, intercambia el elemento en la posicion i con el elemento en la posicion j
                if (elements[j] != null) {
                    t = elements[i];
                    elements[i] = elements[j];
                    elements[j] = t;
                    break; // Sale del bucle interno despues de realizar el intercambio
                }
            }
        }
    }
}


/**
 * Devuelve el elemento en la posicion especificada del array.
 * @param index La posicion del elemento que se desea obtener.
 * @return El elemento en la posicion especificada del array, o null si ocurre una excepcion.
 */
@Override
public E get(int index) {
    try {
        return elements[index];
    } catch (Exception e) {
        // En caso de excepcion, registra un mensaje de advertencia
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        // Retorna null si ocurre una excepcion
        return null;
    }
}


/**
 * Devuelve el indice de la primera aparicion del elemento especificado en el array.
 * @param element El elemento cuyo indice se desea encontrar en el array.
 * @return El indice de la primera aparicion del elemento, o -1 si el elemento no se encuentra en el array.
 */
@Override
public int indexOf(E element) {
    // Itera sobre todos los elementos del array
    for (int i = 0; i < elements.length; i++) {
        // Compara el elemento actual con el elemento especificado
        if (elements[i].equals(element)) {
            // Si se encuentra una coincidencia, retorna el indice actual
            return i;
        }
    }
    // Si el elemento no se encuentra en el array, retorna -1
    return -1;
}


/**
 * Devuelve el indice de la ultima aparicion del elemento especificado en el array.
 * @param element El elemento cuyo indice se desea encontrar en el array.
 * @return El indice de la ultima aparicion del elemento, o -1 si el elemento no se encuentra en el array.
 */
@Override
public int lastIndexOf(E element) {
    // Itera sobre los elementos del array en orden inverso
    for (int i = elements.length - 1; i >= 0; i--) {
        // Compara el elemento actual con el elemento especificado
        if (elements[i].equals(element)) {
            // Si se encuentra una coincidencia, retorna el indice actual
            return i;
        }
    }
    // Si el elemento no se encuentra en el array, retorna -1
    return -1;
}


/**
 * Elimina el elemento en la posicion especificada del array.
 * @param Index La posicion del elemento que se desea eliminar del array.
 * @return true si se elimina el elemento correctamente, false si el elemento en la posicion especificada es null.
 */
@Override
public boolean remove(int Index) {
    // Verifica si el elemento en la posicion Index no es nulo
    if(elements[Index] != null){
        // Si el elemento no es nulo, se elimina asignandole el valor null
        elements[Index]=null;
        return true;
    }
    // Si el elemento en la posicion especificada es null, retorna false
    return false;
}


/**
 * Elimina el primer elemento que cumple con el predicado especificado.
 * @param filter El predicado que se desea aplicar para eliminar el elemento.
 * @return true si se elimina el elemento correctamente, false si ningun elemento coincide con el predicado.
 */
@Override
public boolean remove(Predicate<E> filter) {
    // Itera sobre todos los elementos del array
    for (int i = 0; i < elements.length; i++) {
        // Verifica si el elemento actual cumple con el predicado especificado
        if (filter.test(elements[i])) {
            // Si el elemento cumple con el predicado, se elimina y se retorna true
            remove(i);
            return true;
        }
    }
    // Si ningun elemento cumple con el predicado, retorna false
    return false;
}


/**
 * Elimina todos los elementos en el rango de indices especificado.
 * @param from El indice de inicio del rango (inclusive).
 * @param to El indice de fin del rango (exclusivo).
 * @return true si se eliminan los elementos correctamente, false si ocurre una excepcion.
 */
@Override
public boolean remove(int from, int to) {
    try {
        // Itera sobre todos los elementos en el rango de indices [from, to)
        for (int i = from; i < to; i++) {
            // Elimina el elemento en la posicion actual asignandole el valor null
            elements[i] = null;
        }
        // Retorna true despues de eliminar todos los elementos en el rango
        return true;
    } catch (Exception e) {
        // En caso de excepcion, registra un mensaje de advertencia
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        // Retorna false si ocurre una excepcion
        return false;
    }
}


/**
 * Cambia la dimension del array.
 * @param newDimension La nueva dimension del array.
 * @return true si se cambia la dimension correctamente, false si ocurre una excepcion o si la nueva dimension es menor o igual a 0.
 */
@Override
public boolean dimension(int newDimension) {
    try {
        // Crea un nuevo array con la nueva dimension
        E[] newElements = (E[]) new Object[newDimension];
        // Verifica si la nueva dimension es valida (mayor que 0)
        if (newDimension > 0) {
            // Copia los elementos del array actual al nuevo array, hasta el minimo entre la nueva dimension y la dimension actual
            int copyDimension = (newDimension < elements.length) ? newDimension : elements.length;
            for (int i = 0; i < copyDimension; i++) {
                newElements[i] = elements[i];
            }
            // Asigna el nuevo array al array actual
            this.elements = newElements;
            return true;
        }
    } catch (Exception e) {
        // En caso de excepcion, registra un mensaje de advertencia
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return false;
}


/**
 * Actualiza el elemento en la posicion especificada del array con el nuevo elemento proporcionado.
 * @param index La posicion del elemento que se desea actualizar en el array.
 * @param element El nuevo elemento que se desea asignar en la posicion especificada del array.
 * @return true si se actualiza el elemento correctamente, false si el elemento en la posicion especificada es null.
 */
@Override
public boolean set(int index, E element) {
    // Verifica si el elemento en la posicion index no es nulo
    if(elements[index] != null){
        // Si el elemento no es nulo, se actualiza con el nuevo elemento
        elements[index] = element;
        return true;
    }
    // Si el elemento en la posicion especificada es null, retorna false
    return false;
}
    
}
