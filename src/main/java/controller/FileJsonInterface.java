package controller;

import willy.linkedlist.singly.LinkedList;

public interface FileJsonInterface<E> {
    LinkedList<E> getObjects(String pathFile, Class<E[]> classOfT);
    Boolean writeObjects(String pathFile, String jsonContent);
}
