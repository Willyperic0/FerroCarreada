package view;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardingOrderView extends JFrame {

    private JPanel jPanelMain;
    private JTextArea jTextAreaBoardingOrder;
    private JButton jButtonBack;
    private String[] stages = {"Bienvenidos", "Ingresen clientes estándar", "Ingresen clientes ejecutivos",
            "Ingresen clientes VIP", "Buen viaje"};
    private int currentStageIndex = 0;

    public BoardingOrderView() {
        initComponents();
        // Establecer el tamaño fijo de la ventana
        setSize(300, 200);
        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        jPanelMain = new JPanel();
        jTextAreaBoardingOrder = new JTextArea();
        jButtonBack = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Display Boarding Order");

        jPanelMain.setBorder(javax.swing.BorderFactory.createTitledBorder("Boarding Order"));
        jPanelMain.setLayout(new BorderLayout());

        jTextAreaBoardingOrder.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(jTextAreaBoardingOrder);
        jPanelMain.add(jScrollPane, BorderLayout.CENTER);

        // Estilos personalizados
        Color backgroundColor = Color.WHITE;
        jPanelMain.setBackground(backgroundColor);

        Font defaultFont = new Font("Segoe UI", Font.PLAIN, 12);
        jTextAreaBoardingOrder.setFont(defaultFont);

        add(jPanelMain);

        pack();
    }

    public void updateBoardingOrder() {
        if (currentStageIndex < stages.length) {
            jTextAreaBoardingOrder.setText(""); // Borrar la etapa anterior
            jTextAreaBoardingOrder.append(stages[currentStageIndex] + "\n");
            if (stages[currentStageIndex].equals("Buen viaje")) {
                // Si es "Buen viaje", cerrar la ventana
                this.dispose();
            }
            currentStageIndex++;
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BoardingOrderView().setVisible(true);
            }
        });
    }
}
