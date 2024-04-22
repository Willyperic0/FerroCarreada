package view;

import javax.swing.JOptionPane;

import controller.EmployeeController;
import controller.FileJsonAdapter;
import controller.RouteMController;
import controller.TicketController;
import controller.TrainController;
import model.EmployeeModel;
import model.TrainModel;
import willy.linkedlist.doubly.LinkedList;

import javax.swing.*;
import java.awt.*;

import javax.swing.JOptionPane;

public class LobbyView extends javax.swing.JFrame {

    private javax.swing.JButton jButtonEmployee;
    private javax.swing.JButton jButtonPurchaseTicket;
    private javax.swing.JButton jButtonRoute;
    private javax.swing.JButton jButtonTrain;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabelWelcome;
    private javax.swing.JLabel jLabelInstruction;

    public LobbyView() {
        initComponents();
        setSize(800, 500); // Establece el tamaño de la ventana a 800x500
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonTrain = new javax.swing.JButton();
        jButtonEmployee = new javax.swing.JButton();
        jButtonRoute = new javax.swing.JButton();
        jButtonPurchaseTicket = new javax.swing.JButton();
        jLabelWelcome = new javax.swing.JLabel();
        jLabelInstruction = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lobby");
        setSize(800, 500); // Establece el tamaño de la ventana a 800x500

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lobby"));

        jButtonTrain.setText("Gestión de Trenes");
        jButtonTrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTrainActionPerformed(evt);
            }
        });

        jButtonEmployee.setText("Gestión de Empleados");
        jButtonEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEmployeeActionPerformed(evt);
            }
        });

        jButtonRoute.setText("Gestión de Rutas");
        jButtonRoute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRouteActionPerformed(evt);
            }
        });

        jButtonPurchaseTicket.setText("Compra de Boletos");
        jButtonPurchaseTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPurchaseTicketActionPerformed(evt);
            }
        });

        // Aplica el color al botón
        Color buttonColor = new Color(127, 117, 191);
        jButtonTrain.setBackground(buttonColor);
        jButtonEmployee.setBackground(buttonColor);
        jButtonRoute.setBackground(buttonColor);
        jButtonPurchaseTicket.setBackground(buttonColor);
        jButtonTrain.setForeground(Color.WHITE);
        jButtonEmployee.setForeground(Color.WHITE);
        jButtonRoute.setForeground(Color.WHITE);
        jButtonPurchaseTicket.setForeground(Color.WHITE);

        // Coloca los botones a la izquierda del panel
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(300, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonTrain, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEmployee, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRoute, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPurchaseTicket, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonTrain, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonEmployee, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonRoute, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonPurchaseTicket, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        // Etiqueta de bienvenida
        jLabelWelcome.setFont(new Font("Segoe UI", Font.BOLD, 36));
        jLabelWelcome.setForeground(new Color(78, 41, 115));
        jLabelWelcome.setText("¡Bienvenido!");

        // Etiqueta de instrucción
        jLabelInstruction.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        jLabelInstruction.setForeground(new Color(78, 41, 115));
        jLabelInstruction.setText("¿Qué desea hacer?");

        // Coloca las etiquetas a la derecha del panel
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(300, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelWelcome, GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelInstruction, GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelWelcome)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelInstruction))
                    .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButtonTrainActionPerformed(java.awt.event.ActionEvent evt) {
        TrainController trainController = new TrainController(new TrainModel());
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrainView(trainController).setVisible(true);
            }
        });
        this.dispose();
    }

    private void jButtonEmployeeActionPerformed(java.awt.event.ActionEvent evt) {
        EmployeeController employeeController = new EmployeeController(new EmployeeModel());
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeView(employeeController).setVisible(true);
            }
        });
        this.dispose();
    }

    private void jButtonRouteActionPerformed(java.awt.event.ActionEvent evt) {
        // Crear una instancia del controlador de rutas
        RouteMController routeController = new RouteMController();
    
        // Crear una instancia del JFrame de gestión de rutas
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RouteManagerView(routeController).setVisible(true);
            }
        });
    
        // Cerrar la ventana actual
        this.dispose();
    }
    

    private void jButtonPurchaseTicketActionPerformed(java.awt.event.ActionEvent evt) {
        TicketController ticketController = new TicketController(new TrainController(new TrainModel()));
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new TicketPurchaseView(ticketController).setVisible(true);
            }
        });
        this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LobbyView().setVisible(true);
            }
        });
    }
}