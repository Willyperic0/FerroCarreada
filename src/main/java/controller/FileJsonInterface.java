package controller;

import willy.linkedlist.doubly.LinkedList;

public interface FileJsonInterface<E> { // Define una interfaz para manipular objetos almacenados en archivos JSON
    // Método para obtener una lista de objetos desde un archivo JSON
    LinkedList<E> getObjects(String pathFile, Class<E[]> classOfT);

    // Método para escribir una lista de objetos en un archivo JSON
    Boolean writeObjects(String pathFile, LinkedList<E> objects);
}

