package willy.maps.Ejercicios;

import java.util.Random;

import willy.maps.binaryTree.bintree.Bintree;
import willy.maps.binaryTree.node.Node;

public class main {
    /*
     Escriba un programa para ejecutar el experimento siguiente: genere 100 númerosaleatorios. 
     Conforme se genera cada número, insértelo en un árbol de búsquedabinaria inicialmente vacío. 
     Después de insertar los 100 números, imprima el nivel dela hoja que tiene el nivel más grande 
     y el nivel de la hoja que tiene el nivel más chico.Repita este proceso 50 veces. Imprima una 
     tabla que indique cuántas veces de las 50ejecuciones produjeron una diferencia entre el nivel 
     de hoja máximo y mínimo de0,1,2,3, y así sucesivamente
    */
    public static void main(String[] args) {
        final int VECES = 50;
        final int cant_num = 100;
        final int RANGO = 100;
        int[] alto = new int[VECES + 1];
        int[] bajo = new int[VECES + 1];
        int[] arr = new int[VECES + 1];
        Random random = new Random();

        for (int CONT1 = 1; CONT1 <= VECES; CONT1++) {
            clearScreen();
            System.out.println("LOS NUMEROS INSERTADOS EN EL ARBOL SON...");
            Bintree<Integer> tree = new Bintree<>();
            for (int CONT2 = 1; CONT2 <= cant_num; CONT2++) {
                int num = random.nextInt(RANGO); // Genero el numero aleatorio
                System.out.print(num + "\t");
                tree.insert(num);
            }
            alto[CONT1] = tree.getHeight();
            System.out.printf("EL NIVEL MAS ALTO DEL %dø ARBOL GENERADO ES = %d\n", CONT1, alto[CONT1]);
            bajo[CONT1] = tree.minHeight();
            System.out.printf("EL NIVEL MAS BAJO DEL %dø ARBOL GENERADO ES = %d\n", CONT1, bajo[CONT1]);
            arr[CONT1] = alto[CONT1] - bajo[CONT1];
            System.out.println();
            for (int x = 1; x <= RANGO; x++) {
                int res = 0;
                for (int i = 1; i <= cant_num; i++) {
                    if (x == arr[i])
                        res++;
                }
                if (res > 0)
                    System.out.printf("SE REPITEN %d VECES EL NUMERO %d\n", res, x);
            }
            waitForInput();
        }
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void waitForInput() {
        System.out.println("Presione Enter para continuar...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}