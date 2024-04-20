package view;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.FileJsonAdapter;
import controller.TrainController;
import model.TrainModel;
import willy.linkedlist.doubly.LinkedList;

public class TrainView extends javax.swing.JFrame {
    private final TrainController trainController;

    public TrainView(TrainController trainController) {
        initComponents();
        this.trainController = trainController;
        // No se necesita llamar a loadTrainsFromJson() ya que se carga en el controlador
        updateTrainTable(); // Actualiza la tabla de trenes al iniciar la ventana
    }

    // ActionListener para el botón "AGREGAR"
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Add button clicked");
        String identifier = nameText.getText(); // Obtener el identificador del tren del campo "identificador"
        String selectedOption = (String) typeComboBox.getSelectedItem();
        int capacityLoad = Integer.parseInt(capacityLoadText.getText());
        int mileage = Integer.parseInt(mileageText.getText());

        // Validar la capacidad de carga según la opción seleccionada
        if (selectedOption.equals("Arnold")) {
            if (capacityLoad <= 32) {
                // Agregar tren al controlador
                trainController.addTrain(selectedOption, identifier, capacityLoad, mileage); // Usar el identificador en lugar del nombre
                // Actualizar la tabla de trenes
                updateTrainTable();
                // Limpiar los campos de texto después de agregar un tren
                clearTextFields();
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese una capacidad de carga válida.");
            }
        } else if (selectedOption.equals("Mercedes-Benz")) {
            if (capacityLoad <= 28) {
                // Agregar tren al controlador
                trainController.addTrain(selectedOption, identifier, capacityLoad, mileage); // Usar el identificador en lugar del nombre
                // Actualizar la tabla de trenes
                updateTrainTable();
                // Limpiar los campos de texto después de agregar un tren
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

    // ActionListener para el botón "ELIMINAR"
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = trainTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un tren para eliminar.");
            return;
        }

        String identifier = trainTable.getValueAt(selectedRow, 1).toString(); // Obtener identificador de la fila seleccionada

        // Eliminar tren del controlador
        trainController.deleteAndReorganize(identifier);

        // Actualizar la tabla de trenes
        updateTrainTable();
    }

// Método para actualizar la tabla de trenes
private void updateTrainTable() {
    DefaultTableModel model = (DefaultTableModel) trainTable.getModel();
    model.setRowCount(0); // Limpiar la tabla antes de agregar los datos

    // Obtener la lista de trenes del controlador
    LinkedList<TrainModel> trainList = trainController.getTrainList();

    // Agregar cada tren a la tabla
    for (int i = 0; i < trainList.size(); i++) {
        TrainModel train = trainList.get(i);
        Object[] row = {train.getName(), train.getIdentifier(), train.getCapacityLoad(), train.getMileage()};
        model.addRow(row);
    }
}


    // Método para limpiar los campos de texto después de agregar un tren
    private void clearTextFields() {
        nameText.setText("");
        capacityLoadText.setText("");
        mileageText.setText("");
    }
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LobbyView().setVisible(true);
            }
        });
        this.dispose();
    }

// Método para inicializar los componentes de la interfaz gráfica
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
    jLabel5 = new javax.swing.JLabel();
    typeComboBox = new javax.swing.JComboBox<>();
    jPanel2 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    trainTable = new javax.swing.JTable();
    backButton = new javax.swing.JButton(); // Botón de retorno agregado

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
                    .addComponent(deleteButton)))
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
                .addComponent(deleteButton))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Trenes"));

    trainTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Identificador", "Tipo de tren", "Cantidad de vagones", "Kilometraje"
        }
    ));
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
 

    // Variables declaration - do not modify                     
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton; // Botón de retorno agregado
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField capacityLoadText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField mileageText;
    private javax.swing.JTextField nameText;
    private javax.swing.JTable trainTable;
    private javax.swing.JComboBox<String> typeComboBox;
    // End of variables declaration                   
}