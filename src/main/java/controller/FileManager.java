package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManager {
    public static void crearArchivo(String nombreArchivo){
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
            System.out.println("se creo el archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void escribirArchivo(String nombreArchivo, String contenido){
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
            System.out.println("se creo el archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
