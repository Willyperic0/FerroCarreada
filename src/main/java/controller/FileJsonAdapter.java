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

public class FileJsonAdapter<E> implements FileJsonInterface<E> { // Define una clase genérica FileJsonAdapter que implementa la interfaz FileJsonInterface
    private Object fileWriterLock; // Declara un objeto para sincronizar la escritura en el archivo

    private FileJsonAdapter() { // Constructor privado para evitar instancias externas
        this.fileWriterLock = new Object(); // Inicializa el objeto para sincronizar la escritura en el archivo
    }

    // Método estático para obtener una instancia de FileJsonAdapter
    public static synchronized <E> FileJsonAdapter<E> getInstance() {
        return new FileJsonAdapter<>(); // Retorna una nueva instancia de FileJsonAdapter
    }

    // Método para obtener una lista de objetos desde un archivo JSON
    public LinkedList<E> getObjects(String pathFile, Class<E[]> classOfT) {
        LinkedList<E> objList = new LinkedList<>(); // Crea una nueva lista para almacenar los objetos
        try {
            Gson gson = new GsonBuilder().create(); // Crea un objeto Gson para parsear el JSON
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFile)); // Crea un lector de archivos para leer el JSON
            E[] objArray = gson.fromJson(bufferedReader, classOfT); // Convierte el JSON en un array de objetos
            if (objArray != null) { // Si el array no está vacío
                for (E obj : objArray) { // Para cada objeto en el array
                    objList.add(obj); // Agrega el objeto a la lista
                }
            }
        } catch (IOException e) { // Captura excepciones de E/S
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e); // Registra el error en el registro de errores
        }
        return objList; // Retorna la lista de objetos
    }

    // Método para escribir una lista de objetos en un archivo JSON
    public Boolean writeObjects(String pathFile, LinkedList<E> objects) {
        boolean successful = false; // Inicializa una bandera de éxito como falsa
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Crea un objeto Gson con formato de impresión bonito
        try (FileWriter writer = new FileWriter(pathFile)) { // Crea un escritor de archivos para escribir en el archivo JSON
            synchronized (fileWriterLock) { // Sincroniza el bloque de escritura para evitar concurrencia
                writer.write(""); // Limpia el archivo (elimina todo el contenido)
                gson.toJson(objects.toArray(), writer); // Convierte la lista de objetos en JSON y escribe en el archivo
                successful = true; // Marca la operación como exitosa
            }
        } catch (IOException e) { // Captura excepciones de E/S
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e); // Registra el error en el registro de errores
        }
        return successful; // Retorna si la operación fue exitosa o no
    } 
}
