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

    public TicketSearchView(TicketController ticketController) {
        initComponents();
        this.ticketController = ticketController;
    }

    private void initComponents() {

        jPanelMain = new javax.swing.JPanel();
        jLabelSearchById = new javax.swing.JLabel();
        jTextFieldTicketId = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jScrollPaneResult = new javax.swing.JScrollPane();
        jTextAreaResult = new javax.swing.JTextArea();

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
        jScrollPaneResult.setViewportView(jTextAreaResult);

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneResult, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addComponent(jLabelSearchById)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTicketId)
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
                    .addComponent(jButtonSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneResult, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
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

    private void searchTicketActionPerformed(ActionEvent evt) {
        String registrationId = jTextFieldTicketId.getText(); // Obtener el ID de registro del campo de texto
        
        // Definir la ruta del archivo desde donde se cargarán los tickets en formato JSON
        String ticketFilePath = "FerroCarreada" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "tickets.json";
    
        // Cargar los tickets desde el archivo JSON
        ticketController.loadTicketsFromJson(ticketFilePath);
    
        // Obtener la lista de tickets
        LinkedList<TicketModel> ticketList = ticketController.getTicketList();
        
        // Inicializar absol a -1 para indicar que no se ha encontrado ningún ticket
        int absol = -1;
        
        // Buscar el ticket por su ID de registro
        TicketModel foundTicket = null;
        for (int i = 0; i < ticketList.size(); i++) {
            TicketModel ticket = ticketList.get(i);
            if (ticket.getRegistrationId().equals(registrationId)) {
                absol = i;
                foundTicket = ticket;
                break; // Salir del bucle una vez encontrado el ticket
            }
        }
    
        // Mostrar el resultado
        if (foundTicket != null) {
            // Usar absol para obtener el ticket encontrado
            jTextAreaResult.setText(ticketList.get(absol).toString());
        } else {
            jTextAreaResult.setText("No se encontró ningún ticket con el ID de registro especificado.");
        }
    }
    
    
    public static void main(String[] args) {
        // Aquí puedes instanciar TicketController u obtenerlo de alguna manera según tu implementación
        TrainModel trainModel = new TrainModel(); // Debes proporcionar el objeto TrainModel adecuado aquí
        TrainController trainController = new TrainController(trainModel);
        TicketController ticketController = new TicketController(trainController);
        TicketSearchView frame = new TicketSearchView(ticketController);
        frame.setVisible(true);
    }
    

    // Variables declaration - do not modify
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JLabel jLabelSearchById;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JScrollPane jScrollPaneResult;
    private javax.swing.JTextArea jTextAreaResult;
    private javax.swing.JTextField jTextFieldTicketId;
    // End of variables declaration
}

