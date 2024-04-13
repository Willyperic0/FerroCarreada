package controller;

import willy.linkedlist.doubly.LinkedList;

public interface FileJsonInterface<E> {
    LinkedList<E> getObjects(String pathFile, Class<E[]> classOfT);
    Boolean writeObjects(String pathFile, LinkedList<E> objects);
}
