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

public class TicketSearchView extends javax.swing.JFrame {

    private final TicketController ticketController;
    private final TrainController trainController;
    private javax.swing.JComboBox<String> jComboBoxTrains;

    public TicketSearchView(TicketController ticketController, TrainController trainController) {
        initComponents();
        this.ticketController = ticketController;
        this.trainController = trainController;
        initTrainComboBox();
    }

    private void initComponents() {
        jPanelMain = new javax.swing.JPanel();
        jLabelSearchById = new javax.swing.JLabel();
        jTextFieldTicketId = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jTextAreaResult = new javax.swing.JTextArea();
        jLabelSelectTrain = new javax.swing.JLabel();

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

        jTextAreaResult.setEditable(false);

        jLabelSelectTrain.setText("Seleccione el tren:");

        jComboBoxTrains = new javax.swing.JComboBox<>(); // Move declaration here

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextAreaResult, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addComponent(jLabelSearchById)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTicketId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelSelectTrain)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTrains, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSearch)))
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
                    .addComponent(jLabelSelectTrain))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextAreaResult, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
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

    public static void main(String[] args) {
        TrainModel trainModel = new TrainModel();
        TrainController trainController = new TrainController(trainModel);
        TicketController ticketController = new TicketController(trainController);
        TicketSearchView frame = new TicketSearchView(ticketController, trainController);
        frame.setVisible(true);
    }

    private javax.swing.JButton jButtonSearch;
    private javax.swing.JLabel jLabelSearchById;
    private javax.swing.JLabel jLabelSelectTrain;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JScrollPane jScrollPaneResult;
    private javax.swing.JTextArea jTextAreaResult;
    private javax.swing.JTextField jTextFieldTicketId;

}