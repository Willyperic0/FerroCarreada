package view;

import javax.swing.*;

import controller.TicketController;
import controller.TrainController;
import model.TicketModel;
import model.TrainModel;
import willy.linkedlist.doubly.LinkedList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import java.awt.*;

public class TicketSearchView extends javax.swing.JFrame {

    private final TicketController ticketController;
    private final TrainController trainController;
    private BoardingOrderView boardingOrderView; // Instancia de BoardingOrderView

    public TicketSearchView(TicketController ticketController, TrainController trainController) {
        initComponents();
        this.ticketController = ticketController;
        this.trainController = trainController;
        boardingOrderView = new BoardingOrderView(); // Crear instancia de BoardingOrderView
        initTrainComboBox();
        applyCustomStyles(); // Aplicar estilos personalizados
        setSize(800, 500); // Establecer tamaño de la ventana
        setResizable(false); // Hacer que la ventana no sea redimensionable
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    private javax.swing.JComboBox<String> jComboBoxTrains;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonIngresar; // Botón "Ingresar" agregado
    private javax.swing.JLabel jLabelSearchById;
    private javax.swing.JLabel jLabelSelectTrain;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JScrollPane jScrollPaneResult;
    private javax.swing.JTextArea jTextAreaResult;
    private javax.swing.JTextField jTextFieldTicketId;

    private void applyCustomStyles() {
        // Colores personalizados
        Color buttonColor = new Color(127, 117, 191); // Morado oscuro para los botones
        Color textColor = new Color(78, 41, 115); // Morado más oscuro para el texto
        Color backgroundColor = Color.WHITE; // Fondo blanco

        // Colores
        jPanelMain.setBackground(backgroundColor);
        jButtonSearch.setBackground(buttonColor);
        jButtonIngresar.setBackground(buttonColor);
        jButtonSearch.setForeground(backgroundColor);
        jButtonIngresar.setForeground(backgroundColor);

        // Tamaño de los botones
        int buttonWidth = 100; // Ancho de los botones
        int buttonHeight = 30; // Alto de los botones
        jButtonSearch.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        jButtonIngresar.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        // Tipografía
        Font defaultFont = new Font("Segoe UI", Font.PLAIN, 12);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 14);

        jLabelSearchById.setFont(defaultFont);
        jLabelSearchById.setForeground(textColor);
        jLabelSelectTrain.setFont(defaultFont);
        jLabelSelectTrain.setForeground(textColor);

        jButtonSearch.setFont(defaultFont);
        jButtonIngresar.setFont(defaultFont);

        jTextFieldTicketId.setFont(defaultFont);
        jComboBoxTrains.setFont(defaultFont);
        jTextAreaResult.setFont(defaultFont);

        // Cambiar el cursor de los botones al pasar sobre ellos
        jButtonSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtonIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void initComponents() {
        jPanelMain = new javax.swing.JPanel();
        jLabelSearchById = new javax.swing.JLabel();
        jTextFieldTicketId = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jButtonIngresar = new javax.swing.JButton();
        jTextAreaResult = new javax.swing.JTextArea();
        jLabelSelectTrain = new javax.swing.JLabel();
        jComboBoxTrains = new javax.swing.JComboBox<>(); // Move declaration here
    
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Buscar Ticket por ID");
    
        jPanelMain.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Ticket por ID"));
    
        jLabelSearchById.setText("ID del Ticket:");
    
        jButtonSearch.setText("Buscar");
        jButtonSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchTicketActionPerformed(evt);
            }
        });
    
        jButtonIngresar.setText("Ingresar");
        jButtonIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ingresarActionPerformed(evt);
            }
        });
    
        jTextAreaResult.setEditable(false);
    
        jLabelSelectTrain.setText("Seleccione el tren:");
    
        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
                jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelMainLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextAreaResult, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE) // Ajustar tamaño del área de texto
                                        .addGroup(jPanelMainLayout.createSequentialGroup()
                                                .addComponent(jLabelSearchById)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldTicketId, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE) // Ajustar tamaño del campo de texto
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabelSelectTrain)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBoxTrains, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE) // Ajustar tamaño del combo box
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonSearch)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonIngresar)))
                                .addContainerGap())
        );
        jPanelMainLayout.setVerticalGroup(
                jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelMainLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelSearchById)
                                        .addComponent(jTextFieldTicketId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonSearch)
                                        .addComponent(jComboBoxTrains, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelSelectTrain)
                                        .addComponent(jButtonIngresar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextAreaResult, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE) // Ajustar tamaño del área de texto
                                .addContainerGap())
        );
    
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE) // Ajustar tamaño del panel principal
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE) // Ajustar tamaño del panel principal
                                .addContainerGap())
        );
    
        pack();
    }

    private void initTrainComboBox() {
        LinkedList<TrainModel> trainList = trainController.getTrainList();
        String[] trainNames = new String[trainList.size()];
        for (int i = 0; i < trainList.size(); i++) {
            trainNames[i] = trainList.get(i).getIdentifier();
        }
        jComboBoxTrains.setModel(new javax.swing.DefaultComboBoxModel<>(trainNames));
    }

    private void searchTicketActionPerformed(ActionEvent evt) {
        String registrationId = jTextFieldTicketId.getText();
        String selectedTrainName = (String) jComboBoxTrains.getSelectedItem();

        // Llamar al controlador para buscar el ticket por su ID de registro y el nombre del tren seleccionado
        TicketModel foundTicket = ticketController.findTicketByRegistrationId(registrationId, selectedTrainName);

        // Mostrar el resultado
        if (foundTicket != null) {
            jTextAreaResult.setText(foundTicket.toString());
        } else {
            jTextAreaResult.setText("No se encontró ningún ticket con el ID de registro especificado para el tren seleccionado.");
        }
    }

    private void ingresarActionPerformed(ActionEvent evt) {
        // Actualizar la cola de prioridad en BoardingOrderView
        boardingOrderView.updateBoardingOrder(); // Llamar al método updateBoardingOrder
        // Mostrar la ventana de BoardingOrderView
        boardingOrderView.setVisible(true);
    }

    public static void main(String[] args) {
        TrainModel trainModel = new TrainModel();
        TrainController trainController = new TrainController(trainModel);
        TicketController ticketController = new TicketController(trainController);
        TicketSearchView frame = new TicketSearchView(ticketController, trainController);
        frame.setVisible(true);
    }
}