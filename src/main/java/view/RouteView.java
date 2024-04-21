package view;

import javax.swing.JFrame;
import java.awt.*;
import model.RouteModel;

public class RouteView extends JFrame {
    private RouteModel modelo;

    public RouteView(RouteModel modelo) {
        this.modelo = modelo;
        configurarInterfazGrafica();
    }

    private void configurarInterfazGrafica() {
        setTitle("Grafo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        dibujarGrafo(g);
    }

    private void dibujarGrafo(Graphics g) {
        // Obtener el número de vértices del modelo
        int numVertices = modelo.obtenerNumeroVertices();

        // Dibujar cada vértice como un círculo en la interfaz gráfica
        for (int i = 0; i < numVertices; i++) {
            int x = 50 + (i * 50); // Posición X del vértice
            int y = 200; // Posición Y constante
            g.fillOval(x, y, 20, 20); // Dibujar un círculo en la posición especificada
        }

        // Dibujar las aristas entre los vértices
        for (int i = 0; i < numVertices; i++) {
            int x1 = 50 + (i * 50); // Posición X del vértice de origen
            int y1 = 200; // Posición Y constante del vértice de origen
            for (int j = 0; j < modelo.obtenerAristas(i).size(); j++) {
                RouteModel.Arista arista = modelo.obtenerAristas(i).get(j);
                int x2 = 50 + (arista.getDestino() * 50); // Posición X del vértice de destino
                int y2 = 200; // Posición Y constante del vértice de destino
                g.drawLine(x1 + 10, y1 + 10, x2 + 10, y2 + 10); // Dibujar una línea entre los vértices
            }
            
        }
    }
}
