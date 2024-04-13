package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import willy.linkedlist.doubly.LinkedList;

public class FileJsonAdapter<E> implements FileJsonInterface<E> {
    private Object fileWriterLock;

    private FileJsonAdapter() {
        this.fileWriterLock = new Object();
    }

    public static synchronized <E> FileJsonAdapter<E> getInstance() {
        return new FileJsonAdapter<>();
    }

    public LinkedList<E> getObjects(String pathFile, Class<E[]> classOfT) {
        LinkedList<E> objList = new LinkedList<>();
        try {
            Gson gson = new GsonBuilder().create();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFile));
            E[] objArray = gson.fromJson(bufferedReader, classOfT);
            if (objArray != null) {
                for (E obj : objArray) {
                    objList.add(obj);
                }
            }
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return objList;
    }

    public Boolean writeObjects(String pathFile, LinkedList<E> objects) {
        boolean successful = false;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
 
        try (FileWriter writer = new FileWriter(pathFile)) {
            synchronized (fileWriterLock) {
                // Limpiar el archivo (eliminar todos los objetos)
                writer.write(""); // Esto eliminará todo el contenido del archivo
 
                // Escribir los nuevos objetos
                gson.toJson(objects.toArray(), writer);
 
                successful = true;
            }
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return successful;
    }
    
}