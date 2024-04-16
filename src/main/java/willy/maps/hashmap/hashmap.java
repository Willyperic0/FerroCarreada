package willy.maps.hashmap;

public class hashmap<E> {

// Definimos una clase interna Node que representa un nodo de la lista enlazada
private static class Node<E> {
    String key; // Clave del nodo
    E value; // Valor asociado a la clave
    Node<E> next; // Referencia al siguiente nodo en la lista

    // Constructor para crear un nuevo nodo con una clave y un valor
    Node(String key, E value) {
        this.key = key;
        this.value = value;
        this.next = null; // Al principio, el nodo no apunta a ninguno otro
    }
}

// Array de nodos que sirve como almacenamiento para el mapa
private Node<E>[] array;

// Contador de colisiones
private int collisionCount;

// Tamaño actual del hashmap
private int size;

// Constructor del hashmap
public hashmap(int size) {
    // Encontramos el siguiente número primo después de 100
    int prime = findNextPrime(100);
    // Creamos un nuevo array de nodos con el tamaño del próximo número primo
    array = new Node[prime];
    collisionCount = 0; // Inicializamos el contador de colisiones
    this.size = 0; // Inicializamos el tamaño del hashmap
}

// Método para agregar un elemento al hashmap
public boolean put(String key, E element) {
    // Calculamos el índice utilizando la función de hash
    int index = hash(key);
    // Creamos un nuevo nodo con la clave y el elemento proporcionados
    Node<E> newNode = new Node<>(key, element);
    // Si la posición del array está vacía, simplemente colocamos el nuevo nodo ahí
    if (array[index] == null) {
        array[index] = newNode;
    } else {
        // Si hay una colisión, verificamos si la clave ya existe en la lista enlazada
        Node<E> current = array[index];
        Node<E> prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                // Si la clave ya existe, no se puede agregar y devolvemos false
                return false;
            }
            prev = current;
            current = current.next;
        }
        // Si la clave no existe en la lista enlazada, la agregamos al final
        prev.next = newNode;
        collisionCount++; // Incrementamos el contador de colisiones
    }
    size++; // Incrementamos el tamaño del hashmap
    return true; // Indicamos que el elemento se agregó correctamente
}

// Método para obtener un elemento del hashmap a partir de su clave
public E get(String key) {
    // Calculamos el índice utilizando la función de hash
    int index = hash(key);
    // Obtenemos el nodo correspondiente al índice calculado
    Node<E> current = array[index];
    // Buscamos el elemento en la lista enlazada correspondiente
    while (current != null) {
        if (current.key.equals(key)) {
            // Si encontramos la clave, devolvemos su valor asociado
            return current.value;
        }
        current = current.next; // Avanzamos al siguiente nodo en la lista
    }
    return null; // Si no encontramos la clave, devolvemos null
}

// Método para eliminar un elemento del hashmap a partir de su clave
public void remove(String key) {
    // Calculamos el índice utilizando la función de hash
    int index = hash(key);
    // Obtenemos el nodo correspondiente al índice calculado
    Node<E> current = array[index];
    Node<E> prev = null;
    // Buscamos el nodo a eliminar y lo eliminamos
    while (current != null) {
        if (current.key.equals(key)) {
            if (prev == null) {
                // Si el nodo a eliminar es el primero de la lista, actualizamos el array
                array[index] = current.next;
            } else {
                // Si el nodo a eliminar está en medio o al final de la lista, actualizamos los enlaces
                prev.next = current.next;
            }
            size--; // Decrementamos el tamaño del hashmap
            return; // Salimos del método una vez que eliminamos el nodo
        }
        prev = current;
        current = current.next;
    }
}

// Método para calcular el índice utilizando el método de cuadrados medios
private int hash(String key) {
    long seed = 0;
    // Iteramos sobre los caracteres de la clave
    for (int i = 0; i < key.length(); i++) {
        char c = key.charAt(i);
        // Obtenemos el código ASCII de cada caracter y lo sumamos al valor de la semilla
        seed += (int) c;
    }
    // Calculamos el cuadrado del número
    long square = seed * seed;
    // Convertimos el resultado a cadena
    String squareString = String.valueOf(square);
    // Obtenemos la longitud de la cadena
    int length = squareString.length();
    // Tomamos los últimos dos dígitos del número cuadrado como el índice
    String lastTwoDigits = squareString.substring(length - 2);
    // Convertimos los últimos dos dígitos a un número entero y lo devolvemos como el índice
    return Integer.parseInt(lastTwoDigits);
}

// Método para encontrar el siguiente número primo después de un número dado
private int findNextPrime(int n) {
    while (!isPrime(n)) {
        n++;
    }
    return n;
}

// Método para verificar si un número dado es primo
private boolean isPrime(int n) {
    if (n <= 1) {
        return false;
    }
    for (int i = 2; i * i <= n; i++) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

    
    public static void main(String[] args) {
        hashmap<String> hashmap = new hashmap<>(10); // Tamaño del array: 10
        
         // Agregar un elemento
         boolean added = hashmap.put("7259", "luis");
         System.out.println("Elemento agregado: " + added); // Debería imprimir true
         
         // Intentar agregar el mismo elemento nuevamente
         added = hashmap.put("9359", "fuentes");
         System.out.println("Elemento agregado: " + added); // Debería imprimir false
         
         // Obtener un elemento
         String valor = hashmap.get("7259");
         System.out.println("El valor es: " + valor); // Debería imprimir elemento1
         
         // Eliminar el elemento
         hashmap.remove("7259");
         
         // Intentar obtener el elemento eliminado
         String valorEliminado = hashmap.get("7259");
         System.out.println("El valor eliminado es: " + valorEliminado); // Debería imprimir null
    }
}