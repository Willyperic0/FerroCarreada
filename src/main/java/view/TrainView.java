package view;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import controller.TrainController;
import model.TrainModel;
import willy.linkedlist.doubly.LinkedList;
import java.awt.Color;
import java.awt.Font;

public class TrainView extends javax.swing.JFrame {
    private final TrainController trainController;

    public TrainView(TrainController trainController) {
        initComponents();
        this.trainController = trainController;
        updateTrainTable();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Personalización de colores y tipografía
        customizeUI();
        setLocationRelativeTo(null);
        // Establecer el tamaño de la ventana
        setSize(800, 500);
    }

    private void customizeUI() {
        // Colores
        Color backgroundColor = new Color(242, 242, 242); // Gris claro para el fondo
        Color textColor = new Color(13, 13, 13); // Texto negro
        Color buttonColor = new Color(127, 117, 191); // Púrpura para los botones
    
        // Tipografía
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14); // Fuente para las etiquetas
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14); // Fuente para los botones
    
        // Aplicar colores y tipografía a los componentes
        jPanel1.setBackground(backgroundColor);
        jPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Agregar Tren", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, labelFont, textColor));
        jLabel1.setForeground(textColor);
        jLabel1.setFont(labelFont);
        jLabel2.setForeground(textColor);
        jLabel2.setFont(labelFont);
        jLabel3.setForeground(textColor);
        jLabel3.setFont(labelFont);
        jLabel4.setForeground(textColor);
        jLabel4.setFont(labelFont);
        addButton.setBackground(buttonColor);
        addButton.setForeground(Color.WHITE);
        addButton.setFont(buttonFont);
        deleteButton.setBackground(buttonColor);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(buttonFont);
        modifyButton.setBackground(buttonColor); // Botón de modificar
        modifyButton.setForeground(Color.WHITE); // Botón de modificar
        modifyButton.setFont(buttonFont); // Botón de modificar
        typeComboBox.setBackground(Color.WHITE); // Fondo blanco para el ComboBox
    
        jPanel2.setBackground(backgroundColor);
        jPanel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Lista de Trenes", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, labelFont, textColor));
        jScrollPane1.getViewport().setBackground(Color.WHITE); // Fondo blanco para la tabla
        trainTable.setForeground(textColor);
        trainTable.setFont(labelFont);
    
        backButton.setBackground(buttonColor);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(buttonFont);
    }
    

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        capacityLoadText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        mileageText = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        modifyButton = new javax.swing.JButton(); // Botón de modificar
        typeComboBox = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        trainTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
    
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Train Management System");
        setSize(new java.awt.Dimension(800, 500)); // Ajuste del tamaño de la ventana
    
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Tren"));
    
        jLabel1.setText("Identificador:");
    
        jLabel2.setText("Tipo de tren:");
    
        jLabel3.setText("Cantidad de vagones:");
    
        jLabel4.setText("Kilometraje:");
    
        addButton.setText("Agregar");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
    
        deleteButton.setText("Eliminar");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
    
        modifyButton.setText("Modificar"); // Texto del botón de modificar
        modifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyButtonActionPerformed(evt);
            }
        });
    
        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Arnold", "Mercedes-Benz" }));
    
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameText)
                            .addComponent(capacityLoadText)
                            .addComponent(mileageText, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(typeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modifyButton))) // Botón de modificar
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(capacityLoadText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(mileageText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(deleteButton)
                    .addComponent(modifyButton)) // Botón de modificar
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Trenes"));
    
        trainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
    
            },
            new String [] {
                "Identificador", "Tipo de tren", "Cantidad de vagones", "Kilometraje"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };
    
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(trainTable);
    
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addContainerGap())
        );
    
        backButton.setText("Volver"); // Texto del botón de retorno
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
    
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(351, 351, 351)
                .addComponent(backButton) // Agregar el botón de retorno al diseño
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backButton) // Agregar el botón de retorno al diseño
                .addContainerGap())
        );
    
        pack();
    }
    

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Add button clicked");
        String identifier = nameText.getText();
        String selectedOption = (String) typeComboBox.getSelectedItem();
        int capacityLoad = Integer.parseInt(capacityLoadText.getText());
        int mileage = Integer.parseInt(mileageText.getText());

        if (selectedOption.equals("Arnold")) {
            if (capacityLoad <= 32) {
                trainController.addTrain(selectedOption, identifier, capacityLoad, mileage);
                updateTrainTable();
                clearTextFields();
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese una capacidad de carga válida.");
            }
        } else if (selectedOption.equals("Mercedes-Benz")) {
            if (capacityLoad <= 28) {
                trainController.addTrain(selectedOption, identifier, capacityLoad, mileage);
                updateTrainTable();
                clearTextFields();
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese una capacidad de carga válida.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una opción válida.");
        }
        System.out.println("Identifier: " + identifier);
        System.out.println("Selected Option: " + selectedOption);
        System.out.println("Capacity Load: " + capacityLoad);
        System.out.println("Mileage: " + mileage);
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = trainTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un tren para eliminar.");
            return;
        }

        String identifier = trainTable.getValueAt(selectedRow, 1).toString();
        trainController.deleteAndReorganize(identifier);
        updateTrainTable();
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LobbyView().setVisible(true);
            }
        });
        this.dispose();
    }
    private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = trainTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un tren para modificar.");
            return;
        }
        String selectedOption = trainTable.getValueAt(selectedRow, 0).toString();
        // Obtener el identificador del tren seleccionado
        String identifier = trainTable.getValueAt(selectedRow, 1).toString();
    
        // Obtener la antigua capacidad de carga del tren seleccionado
        int oldCapacityLoad = Integer.parseInt(trainTable.getValueAt(selectedRow, 2).toString());
    
        // Obtener el nuevo identificador ingresado en el campo de texto
        String newIdentifier = nameText.getText();
    
        // Obtener la nueva capacidad de carga ingresada en el campo de texto
        int newCapacityLoad = Integer.parseInt(capacityLoadText.getText());
            // Validar si ambos campos están vacíos
    if (newIdentifier.isEmpty() && capacityLoadText.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Ingrese los datos para modificar.");
        return;
    }

    // Validar si se ingresó la capacidad de carga pero no el identificador
    if (newIdentifier.isEmpty() && !capacityLoadText.getText().isEmpty()) {
        // Mantener el identificador anterior y asignar la nueva capacidad de carga
        newIdentifier = identifier;
    } else if (!newIdentifier.isEmpty() && capacityLoadText.getText().isEmpty()) {
        // Si se ingresó el identificador pero no la capacidad de carga
        // Mantener la capacidad de carga anterior y asignar el nuevo identificador
        newCapacityLoad = oldCapacityLoad;
    }

    if (selectedOption.equals("Arnold")) {
        if (newCapacityLoad <= 32) {
            trainController.modifyTrain(identifier, newIdentifier, oldCapacityLoad, newCapacityLoad);
            updateTrainTable();
            clearTextFields();
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese una capacidad de carga válida para el tren Arnold.");
        }
    } else if (selectedOption.equals("Mercedes-Benz")) {
        if (newCapacityLoad <= 28) {
            trainController.modifyTrain(identifier, newIdentifier, oldCapacityLoad, newCapacityLoad);
            updateTrainTable();
            clearTextFields();
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese una capacidad de carga válida para el tren Mercedes-Benz.");
        }
    }
    }
    
    

    private void updateTrainTable() {
        DefaultTableModel model = (DefaultTableModel) trainTable.getModel();
        model.setRowCount(0);

        LinkedList<TrainModel> trainList = trainController.getTrainList();

        for (int i = 0; i < trainList.size(); i++) {
            TrainModel train = trainList.get(i);
            Object[] row = {train.getName(), train.getIdentifier(), train.getCapacityLoad(), train.getMileage()};
            model.addRow(row);
        }
    }

    private void clearTextFields() {
        nameText.setText("");
        capacityLoadText.setText("");
        mileageText.setText("");
    }

    // Variables declaration - do not modify
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton modifyButton; // Botón de modificar
    private javax.swing.JTextField capacityLoadText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField mileageText;
    private javax.swing.JTextField nameText;
    private javax.swing.JTable trainTable;
    private javax.swing.JComboBox<String> typeComboBox;
    // End of variables declaration
}