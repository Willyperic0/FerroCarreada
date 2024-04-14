/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import javax.swing.JOptionPane;

import controller.EmployeeController;
import controller.FileJsonAdapter;
import controller.TrainController;
import model.EmployeeModel;
import model.TrainModel;
import willy.linkedlist.doubly.LinkedList;

/**
 *
 * @author Willyperic0
 */
import javax.swing.JOptionPane;

public class LobbyView extends javax.swing.JFrame {

    public LobbyView() {
        initComponents();
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonTrain = new javax.swing.JButton();
        jButtonEmployee = new javax.swing.JButton();
        jButtonRoute = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lobby");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lobby"));

        jButtonTrain.setText("TrainView");
        jButtonTrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTrainActionPerformed(evt);
            }
        });

        jButtonEmployee.setText("EmployeeView");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonTrain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jButtonRoute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonTrain, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonRoute, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
        JOptionPane.showMessageDialog(null, "Modulo de gestion de rutas no disponible por el momento");
    }                                            

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LobbyView().setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButtonEmployee;
    private javax.swing.JButton jButtonRoute;
    private javax.swing.JButton jButtonTrain;
    private javax.swing.JPanel jPanel1;                 
}
