package view;

import javax.swing.*;

import controller.EmployeeController;
import controller.FileJsonAdapter;
import controller.LoginController;
import controller.TicketController;
import controller.TrainController;
import model.EmployeeModel;
import model.PassengerModel;
import model.TicketModel;
import model.TrainModel;
import willy.linkedlist.doubly.LinkedList;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;


public class TicketPurchaseView extends javax.swing.JFrame {

    private final TicketController ticketController;
    private final TrainController trainController;

    public TicketPurchaseView(TicketController ticketController, TrainController trainController) {
        initComponents();
        this.ticketController = ticketController;
        this.trainController = trainController; // Se inicializa trainController
        String ticketFilePath = "FerroCarreada" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "tickets.json";
        ticketController.loadTicketsFromJson(ticketFilePath); // Cargar los datos de tickets desde el archivo JSON al iniciar la ventana
    }
    
    
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LobbyView().setVisible(true);
            }
        });
        this.dispose();
    }
    private void initComponents() {

        jPanelMain = new javax.swing.JPanel();
        jLabelPassengerInfo = new javax.swing.JLabel();
        jLabelContactPerson = new javax.swing.JLabel();
        jTextFieldContactPerson = new javax.swing.JTextField();
        jLabelContactPersonLastName = new javax.swing.JLabel();
        jTextFieldContactPersonLastName = new javax.swing.JTextField();
        jLabelContactPersonPhoneNumber = new javax.swing.JLabel();
        jTextFieldContactPersonPhoneNumber = new javax.swing.JTextField();
        jLabelName = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabelLastName = new javax.swing.JLabel();
        jTextFieldLastName = new javax.swing.JTextField();
        jLabelPhoneNumber = new javax.swing.JLabel();
        jTextFieldPhoneNumber = new javax.swing.JTextField();
        jLabelDni = new javax.swing.JLabel();
        jTextFieldDni = new javax.swing.JTextField();
        jComboBoxCategory = new javax.swing.JComboBox<>();
        jButtonPurchase = new javax.swing.JButton();
        backButton = new javax.swing.JButton(); // Botón de retorno agregado
    
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compra de Boleto");
    
        jPanelMain.setBorder(javax.swing.BorderFactory.createTitledBorder("Compra de Boleto"));
    
        jLabelPassengerInfo.setText("Información del Pasajero:");
    
        jLabelContactPerson.setText("Persona de Contacto:");
    
        jLabelContactPersonLastName.setText("Apellido de la Persona de Contacto:");
    
        jLabelContactPersonPhoneNumber.setText("Número de Teléfono de la Persona de Contacto:");
    
        jLabelName.setText("Nombre:");
    
        jLabelLastName.setText("Apellido:");
    
        jLabelPhoneNumber.setText("Número de Teléfono:");
    
        jLabelDni.setText("DNI:");
    
        jComboBoxCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VIP", "Ejecutivo", "Estándar" }));
    
        jButtonPurchase.setText("Comprar");
        jButtonPurchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseTicketActionPerformed(evt);
            }
        });
    
        backButton.setText("Volver"); // Texto del botón de retorno
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
    
        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPassengerInfo)
                            .addGroup(jPanelMainLayout.createSequentialGroup()
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelContactPerson)
                                    .addComponent(jLabelContactPersonLastName)
                                    .addComponent(jLabelContactPersonPhoneNumber)
                                    .addComponent(jLabelName)
                                    .addComponent(jLabelLastName)
                                    .addComponent(jLabelPhoneNumber)
                                    .addComponent(jLabelDni))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldContactPerson)
                                    .addComponent(jTextFieldContactPersonLastName)
                                    .addComponent(jTextFieldContactPersonPhoneNumber)
                                    .addComponent(jTextFieldName)
                                    .addComponent(jTextFieldLastName)
                                    .addComponent(jTextFieldPhoneNumber)
                                    .addComponent(jTextFieldDni, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonPurchase)
                        .addGap(152, 152, 152))))
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBoxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backButton) // Agregar el botón de retorno al diseño
                .addContainerGap())
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelPassengerInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelContactPerson)
                    .addComponent(jTextFieldContactPerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelContactPersonLastName)
                    .addComponent(jTextFieldContactPersonLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelContactPersonPhoneNumber)
                    .addComponent(jTextFieldContactPersonPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelName)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLastName)
                    .addComponent(jTextFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPhoneNumber)
                    .addComponent(jTextFieldPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDni)
                    .addComponent(jTextFieldDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonPurchase)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(backButton) // Agregar el botón de retorno al diseño
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    
        pack();
    }
    

    private void purchaseTicketActionPerformed(java.awt.event.ActionEvent evt) {
        // Obtener los datos del pasajero
        String contactPerson = jTextFieldContactPerson.getText();
        String contactPersonLastName = jTextFieldContactPersonLastName.getText();
        int contactPersonPhoneNumber = Integer.parseInt(jTextFieldContactPersonPhoneNumber.getText());
        String name = jTextFieldName.getText();
        String lastName = jTextFieldLastName.getText();
        int phoneNumber = Integer.parseInt(jTextFieldPhoneNumber.getText());
        int dni = Integer.parseInt(jTextFieldDni.getText());
        PassengerModel passenger = new PassengerModel(name, lastName, phoneNumber, dni, contactPerson, contactPersonLastName, contactPersonPhoneNumber);
    
        // Obtener la categoría seleccionada del combo box
        String selectedCategory = (String) jComboBoxCategory.getSelectedItem();
    
        // Simular hora de compra
        String purchaseDateTime = java.time.LocalDateTime.now().toString();
    
        // Simular hora de partida y llegada
        java.util.Random rand = new java.util.Random();
        int hour = rand.nextInt(24);
        int minute = rand.nextInt(60);
        String departureDateTime = "2024-04-15 " + String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":00";
        String arrivalDateTime = "2024-04-15 " + String.format("%02d", hour + 2) + ":" + String.format("%02d", minute) + ":00";
    
        // Obtener el tren seleccionado
        TrainModel train = ticketController.getRandomTrain();
        double ticketValue;
    
// Actualizar el número de vagones según la categoría seleccionada
switch (selectedCategory) {
    case "VIP":
        ticketValue = 30.0;
        int originalVipVagons = train.getVipVagons();
        trainController.updateTrainDataTicket(train.getIdentifier(), "VIP");
        // Verificar si el número de vagones VIP ha cambiado
        if (train.getVipVagons() == originalVipVagons) {
            JOptionPane.showMessageDialog(this, "¡Lo sentimos! No hay vagones VIP disponibles. Por favor, elija otra categoría.");
            return; // Salir del método si no hay vagones VIP disponibles
        } else {
            // Crear el boleto utilizando el TicketController
ticketController.addTicket(purchaseDateTime, departureDateTime, arrivalDateTime, passenger, train, ticketValue, selectedCategory);
ticketController.saveTicketsToJson();

// Mostrar un mensaje de éxito
JOptionPane.showMessageDialog(this, "¡Boleto comprado exitosamente!");

// Limpiar los campos de entrada
clearFields();
}
        break;
    case "Ejecutivo":
        ticketValue = 25.0;
        int originalExecutiveVagons = train.getExecutiveVagons();
        trainController.updateTrainDataTicket(train.getIdentifier(), "Executive");
        // Verificar si el número de vagones ejecutivos ha cambiado
        if (train.getExecutiveVagons() == originalExecutiveVagons) {
            JOptionPane.showMessageDialog(this, "¡Lo sentimos! No hay vagones ejecutivos disponibles. Por favor, elija otra categoría.");
            return; // Salir del método si no hay vagones ejecutivos disponibles
        } else {
            // Crear el boleto utilizando el TicketController
ticketController.addTicket(purchaseDateTime, departureDateTime, arrivalDateTime, passenger, train, ticketValue, selectedCategory);
ticketController.saveTicketsToJson();

// Mostrar un mensaje de éxito
JOptionPane.showMessageDialog(this, "¡Boleto comprado exitosamente!");

// Limpiar los campos de entrada
clearFields();
}
        break;
    case "Estándar":
    default:
        ticketValue = 15.0;
        int originalStandardVagons = train.getStandardVagons();
        trainController.updateTrainDataTicket(train.getIdentifier(), "Standard");
        // Verificar si el número de vagones estándar ha cambiado
        if (train.getStandardVagons() == originalStandardVagons) {
            JOptionPane.showMessageDialog(this, "¡Lo sentimos! No hay vagones estándar disponibles. Por favor, elija otra categoría.");
            return; // Salir del método si no hay vagones estándar disponibles
        } else {
                    // Crear el boleto utilizando el TicketController
        ticketController.addTicket(purchaseDateTime, departureDateTime, arrivalDateTime, passenger, train, ticketValue, selectedCategory);
        ticketController.saveTicketsToJson();
    
        // Mostrar un mensaje de éxito
        JOptionPane.showMessageDialog(this, "¡Boleto comprado exitosamente!");
    
        // Limpiar los campos de entrada
        clearFields();
        }
        break;
}

    }
    
    private void clearFields() {
        jTextFieldContactPerson.setText("");
        jTextFieldContactPersonLastName.setText("");
        jTextFieldContactPersonPhoneNumber.setText("");
        jTextFieldName.setText("");
        jTextFieldLastName.setText("");
        jTextFieldPhoneNumber.setText("");
        jTextFieldDni.setText("");
    }

    public static void main(String[] args) {
        TrainModel trainModel = new TrainModel(); // Crear una instancia de TrainModel
        TrainController trainController = new TrainController(trainModel); // Pasar trainModel como argumento
        TicketController ticketController = new TicketController(trainController); // Pasando trainController como argumento
        TicketPurchaseView frame = new TicketPurchaseView(ticketController, trainController);
        frame.setVisible(true);
    }

// Variables declaration - do not modify
private javax.swing.JButton jButtonPurchase;
private javax.swing.JButton backButton; // Declaración del botón de retorno
private javax.swing.JComboBox<String> jComboBoxCategory;
private javax.swing.JLabel jLabelPassengerInfo;
private javax.swing.JLabel jLabelContactPerson;
private javax.swing.JLabel jLabelContactPersonLastName;
private javax.swing.JLabel jLabelContactPersonPhoneNumber;
private javax.swing.JLabel jLabelName;
private javax.swing.JLabel jLabelLastName;
private javax.swing.JLabel jLabelPhoneNumber;
private javax.swing.JLabel jLabelDni;
private javax.swing.JPanel jPanelMain;
private javax.swing.JTextField jTextFieldContactPerson;
private javax.swing.JTextField jTextFieldContactPersonLastName;
private javax.swing.JTextField jTextFieldContactPersonPhoneNumber;
private javax.swing.JTextField jTextFieldName;
private javax.swing.JTextField jTextFieldLastName;
private javax.swing.JTextField jTextFieldPhoneNumber;
private javax.swing.JTextField jTextFieldDni;
// End of variables declaration

}