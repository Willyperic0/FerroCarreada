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
import javax.swing.*;
import java.awt.*;

public class TicketPurchaseView extends javax.swing.JFrame {

    private final TicketController ticketController;
    private final TrainController trainController;

    public TicketPurchaseView(TicketController ticketController, TrainController trainController) {
        initComponents();
        this.ticketController = ticketController;
        this.trainController = trainController; 
        String ticketFilePath = "FerroCarreada" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "tickets.json";
        ticketController.loadTicketsFromJson(ticketFilePath); 
        initTrainComboBox();
        
        // Ajustar el tamaño de la ventana
        setSize(800, 500);
        
        // Aplicar colores y tipografía
        applyCustomStyles();
    }

    // Método para aplicar colores y tipografía personalizados
    private void applyCustomStyles() {
        // Colores personalizados
        Color buttonColor = new Color(127, 117, 191); // Morado oscuro para los botones
        Color textColor = new Color(48, 0, 78); // Morado más oscuro para el texto
        Color backgroundColor = Color.WHITE; // Fondo blanco
        
        // Colores
        jPanelMain.setBackground(backgroundColor);
        jButtonPurchase.setBackground(buttonColor);
        jButtonPurchase.setForeground(backgroundColor);
        
        // Tipografía
        Font defaultFont = new Font("Segoe UI", Font.PLAIN, 12);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 14);
        
        jLabelPassengerInfo.setFont(titleFont);
        jLabelPassengerInfo.setForeground(textColor);
        jLabelContactPerson.setFont(defaultFont);
        jLabelContactPerson.setForeground(textColor);
        jLabelContactPersonLastName.setFont(defaultFont);
        jLabelContactPersonLastName.setForeground(textColor);
        jLabelContactPersonPhoneNumber.setFont(defaultFont);
        jLabelContactPersonPhoneNumber.setForeground(textColor);
        jLabelName.setFont(defaultFont);
        jLabelName.setForeground(textColor);
        jLabelLastName.setFont(defaultFont);
        jLabelLastName.setForeground(textColor);
        jLabelPhoneNumber.setFont(defaultFont);
        jLabelPhoneNumber.setForeground(textColor);
        jLabelDni.setFont(defaultFont);
        jLabelDni.setForeground(textColor);
        jLabelTrain.setFont(defaultFont);
        jLabelTrain.setForeground(textColor);
        
        jButtonPurchase.setFont(defaultFont);
        
        // Otros componentes
        jTextFieldContactPerson.setFont(defaultFont);
        jTextFieldContactPersonLastName.setFont(defaultFont);
        jTextFieldContactPersonPhoneNumber.setFont(defaultFont);
        jTextFieldName.setFont(defaultFont);
        jTextFieldLastName.setFont(defaultFont);
        jTextFieldPhoneNumber.setFont(defaultFont);
        jTextFieldDni.setFont(defaultFont);
        jComboBoxCategory.setFont(defaultFont);
        jComboBoxTrains.setFont(defaultFont);
        
        // Cambiar el cursor de los botones al pasar sobre ellos
        jButtonPurchase.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
        jLabelTrain = new javax.swing.JLabel(); // Label para el JComboBox de trenes
        jComboBoxTrains = new javax.swing.JComboBox<>(); // ComboBox para seleccionar el tren
        jButtonPurchase = new javax.swing.JButton();

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

        jComboBoxCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"VIP", "Ejecutivo", "Estándar"}));

        jLabelTrain.setText("Seleccione el Tren:"); // Texto para el JLabel del tren

        jButtonPurchase.setText("Comprar");
        jButtonPurchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseTicketActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
                jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelMainLayout.createSequentialGroup()
                                .addContainerGap()
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
                                                        .addComponent(jTextFieldDni, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                                        .addGroup(jPanelMainLayout.createSequentialGroup()
                                                .addComponent(jComboBoxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabelTrain)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBoxTrains, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonPurchase)
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
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jComboBoxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelTrain)
                                        .addComponent(jComboBoxTrains, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addComponent(jButtonPurchase)
                                .addContainerGap())
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

        // Obtener el tren seleccionado del combo box
        String selectedTrainIdentifier = (String) jComboBoxTrains.getSelectedItem();
        TrainModel selectedTrain = trainController.findTrainByIdentifier(selectedTrainIdentifier);

        // Simular hora de compra
        String purchaseDateTime = java.time.LocalDateTime.now().toString();

        // Simular hora de partida y llegada
        java.util.Random rand = new java.util.Random();
        int hour = rand.nextInt(24);
        int minute = rand.nextInt(60);
        String departureDateTime = "2024-04-15 " + String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":00";
        String arrivalDateTime = "2024-04-15 " + String.format("%02d", hour + 2) + ":" + String.format("%02d", minute) + ":00";

        double ticketValue = 0.0;

        // Calcular el valor del boleto según la categoría seleccionada
        switch (selectedCategory) {
            case "VIP":
                ticketValue = 30.0;
                break;
            case "Ejecutivo":
                ticketValue = 25.0;
                break;
            case "Estándar":
                ticketValue = 15.0;
                break;
        }

        // Crear el boleto utilizando el TicketController
        ticketController.addTicket(purchaseDateTime, departureDateTime, arrivalDateTime, passenger, selectedTrain, ticketValue, selectedCategory);
        ticketController.saveTicketsToJson();

        // Mostrar un mensaje de éxito
        JOptionPane.showMessageDialog(this, "¡Boleto comprado exitosamente!");

        // Limpiar los campos de entrada
        clearFields();
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

    private void initTrainComboBox() {
        LinkedList<TrainModel> trainList = trainController.getTrainList();
        String[] trainIdentifiers = new String[trainList.size()];
        for (int i = 0; i < trainList.size(); i++) {
            trainIdentifiers[i] = trainList.get(i).getIdentifier();
        }
        jComboBoxTrains.setModel(new javax.swing.DefaultComboBoxModel<>(trainIdentifiers));
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
    private javax.swing.JComboBox<String> jComboBoxCategory;
    private javax.swing.JComboBox<String> jComboBoxTrains;
    private javax.swing.JLabel jLabelContactPerson;
    private javax.swing.JLabel jLabelContactPersonLastName;
    private javax.swing.JLabel jLabelContactPersonPhoneNumber;
    private javax.swing.JLabel jLabelDni;
    private javax.swing.JLabel jLabelLastName;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPassengerInfo;
    private javax.swing.JLabel jLabelPhoneNumber;
    private javax.swing.JLabel jLabelTrain;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JTextField jTextFieldContactPerson;
    private javax.swing.JTextField jTextFieldContactPersonLastName;
    private javax.swing.JTextField jTextFieldContactPersonPhoneNumber;
    private javax.swing.JTextField jTextFieldDni;
    private javax.swing.JTextField jTextFieldLastName;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldPhoneNumber;
    // End of variables declaration
}
