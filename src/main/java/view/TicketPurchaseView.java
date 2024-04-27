package view;

import javax.swing.*;

import controller.RouteController;
import controller.TicketController;
import controller.TrainController;
import model.PassengerModel;
import model.RouteModel;
import model.TrainModel;
import willy.linkedlist.doubly.LinkedList;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.*;

public class TicketPurchaseView extends javax.swing.JFrame {

        private final TicketController ticketController;
        private final TrainController trainController;
        private final RouteController routeController;
    
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
        private javax.swing.JLabel jLabelBaggageWeight; // Nuevo JLabel para el peso del equipaje
        private javax.swing.JPanel jPanelMain;
        private javax.swing.JTextField jTextFieldContactPerson;
        private javax.swing.JTextField jTextFieldContactPersonLastName;
        private javax.swing.JTextField jTextFieldContactPersonPhoneNumber;
        private javax.swing.JTextField jTextFieldDni;
        private javax.swing.JTextField jTextFieldLastName;
        private javax.swing.JTextField jTextFieldName;
        private javax.swing.JTextField jTextFieldPhoneNumber;
        private javax.swing.JTextField jTextFieldBaggageWeight; // Nuevo JTextField para el peso del equipaje
    
        public TicketPurchaseView(TicketController ticketController, TrainController trainController, RouteController routeController) {
            initComponents();
            this.ticketController = ticketController;
            this.trainController = trainController;
            this.routeController = routeController;
    
            ticketController.loadTicketsFromJson();
            initTrainComboBox();
            applyCustomStyles(); // Aplicar estilos personalizados
            setSize(800, 500); // Definir el tamaño de la ventana
            setLocationRelativeTo(null); // Centrar la ventana en la pantalla
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
        }
    
        private void applyCustomStyles() {
            // Colores y tipografía personalizados
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
            jLabelBaggageWeight.setFont(defaultFont); // Establecer la tipografía para el nuevo JLabel
            jLabelBaggageWeight.setForeground(textColor); // Establecer el color para el nuevo JLabel
    
            jButtonPurchase.setFont(defaultFont);
    
            // Otros componentes
            jTextFieldContactPerson.setFont(defaultFont);
            jTextFieldContactPersonLastName.setFont(defaultFont);
            jTextFieldContactPersonPhoneNumber.setFont(defaultFont);
            jTextFieldName.setFont(defaultFont);
            jTextFieldLastName.setFont(defaultFont);
            jTextFieldPhoneNumber.setFont(defaultFont);
            jTextFieldDni.setFont(defaultFont);
            jTextFieldBaggageWeight.setFont(defaultFont); // Establecer la tipografía para el nuevo JTextField
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
            jLabelBaggageWeight = new javax.swing.JLabel(); // Nuevo JLabel para el peso del equipaje
            jTextFieldBaggageWeight = new javax.swing.JTextField(); // Nuevo JTextField para el peso del equipaje
    
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
    
            jLabelTrain.setText("Seleccione la Ruta:"); // Texto para el JLabel del tren
    
            jButtonPurchase.setText("Comprar");
            jButtonPurchase.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    purchaseTicketActionPerformed(evt);
                }
            });
    
            jLabelBaggageWeight.setText("Peso Total del Equipaje (Kg):"); // Establecer el texto para el nuevo JLabel
    
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
                                                            .addComponent(jLabelDni)
                                                            .addComponent(jLabelBaggageWeight)) // Añadir el nuevo JLabel al diseño
                                                    .addGap(18, 18, 18)
                                                    .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(jTextFieldContactPerson)
                                                            .addComponent(jTextFieldContactPersonLastName)
                                                            .addComponent(jTextFieldContactPersonPhoneNumber)
                                                            .addComponent(jTextFieldName)
                                                            .addComponent(jTextFieldLastName)
                                                            .addComponent(jTextFieldPhoneNumber)
                                                            .addComponent(jTextFieldDni)
                                                            .addComponent(jTextFieldBaggageWeight, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))) // Añadir el nuevo JTextField al diseño
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
                                            .addComponent(jLabelBaggageWeight) // Añadir el nuevo JLabel al diseño
                                            .addComponent(jTextFieldBaggageWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)) // Añadir el nuevo JTextField al diseño
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
                // Verificar si algún campo de JTextField está vacío
    if (jTextFieldContactPerson.getText().isEmpty() ||
    jTextFieldContactPersonLastName.getText().isEmpty() ||
    jTextFieldContactPersonPhoneNumber.getText().isEmpty() ||
    jTextFieldName.getText().isEmpty() ||
    jTextFieldLastName.getText().isEmpty() ||
    jTextFieldPhoneNumber.getText().isEmpty() ||
    jTextFieldDni.getText().isEmpty() ||
    jTextFieldBaggageWeight.getText().isEmpty()) {
    
    // Mostrar un mensaje de advertencia si algún campo está vacío
    JOptionPane.showMessageDialog(this, "Por favor, complete toda la información para comprar el boleto.");
    return; // Salir del método si hay campos vacíos
}
                // Obtener los datos del pasajero
            String contactPerson = jTextFieldContactPerson.getText();
            String contactPersonLastName = jTextFieldContactPersonLastName.getText();
            int contactPersonPhoneNumber = Integer.parseInt(jTextFieldContactPersonPhoneNumber.getText());
            String name = jTextFieldName.getText();
            String lastName = jTextFieldLastName.getText();
            int phoneNumber = Integer.parseInt(jTextFieldPhoneNumber.getText());
            int dni = Integer.parseInt(jTextFieldDni.getText());
            PassengerModel passenger = new PassengerModel(name, lastName, phoneNumber, dni, contactPerson, contactPersonLastName, contactPersonPhoneNumber);
    
            // Obtener el valor del peso del equipaje ingresado por el usuario
            double baggageWeight;
            try {
                baggageWeight = Double.parseDouble(jTextFieldBaggageWeight.getText());
                // Verificar si el peso del equipaje está dentro del rango permitido (0 a 80 kg)
                if (baggageWeight < 0 || baggageWeight > 160) {
                    JOptionPane.showMessageDialog(this, "Tamaño del equipaje no permitido.");
                    return; // Salir del método si el peso del equipaje no está dentro del rango permitido
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor válido para el peso del equipaje.");
                return; // Salir del método si el usuario no ingresa un valor numérico válido
            }
    
            // Obtener la categoría seleccionada del combo box
            String selectedCategory = (String) jComboBoxCategory.getSelectedItem();
    
            // Obtener el tren seleccionado del combo box
            String selectedTrainIdentifier = (String) jComboBoxTrains.getSelectedItem();
            String temporal = (String) selectedTrainIdentifier;
            String tmp = routeController.changuaTrainId(temporal);
            selectedTrainIdentifier = (String) tmp;
            System.out.println(selectedTrainIdentifier);
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
                    ticketValue = 1800 * routeController.findDistanceByRouteName(temporal);
                    trainController.updateVip(selectedTrain);
                    break;
                case "Ejecutivo":
                    ticketValue = 1200 * routeController.findDistanceByRouteName(temporal);
                    trainController.updateExecutive(selectedTrain);
                    break;
                case "Estándar":
                    ticketValue = 1000 * routeController.findDistanceByRouteName(temporal);
                    trainController.updateStandard(selectedTrain);
                    break;
            }
            trainController.updateTrainDataTicket(selectedTrainIdentifier,selectedCategory);
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
            jTextFieldBaggageWeight.setText(""); // Limpiar el campo de peso del equipaje
        }
    
        private void initTrainComboBox() {
            LinkedList<RouteModel> routeList = routeController.getRoutes(); // Obtener la lista de rutas del RouteController
            String[] routeNames = new String[routeList.size()];
            for (int i = 0; i < routeList.size(); i++) {
                routeNames[i] = routeList.get(i).getRouteName(); // Obtener el nombre de cada ruta
            }
            jComboBoxTrains.setModel(new javax.swing.DefaultComboBoxModel<>(routeNames)); // Establecer los nombres de las rutas en el combo box
    
            // Agregar un ActionListener al combo box para manejar la selección de la ruta
            jComboBoxTrains.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Obtener el nombre de la ruta seleccionada del combo box
                    String selectedRouteName = (String) jComboBoxTrains.getSelectedItem();
    
                    // Usar el RouteController para obtener el identificador del tren asociado a la ruta seleccionada
                    String selectedTrainIdentifier = routeController.changuaTrainId(selectedRouteName);
    
                    // Verificar si se encontró un identificador de tren válido
                    if (selectedTrainIdentifier != null) {
                        // Usar el TrainController para encontrar el tren por su identificador
                        TrainModel selectedTrain = trainController.findTrainByIdentifier(selectedTrainIdentifier);
                        // Ahora tienes el tren seleccionado para trabajar con él
                    } else {
                        // Si no se encontró un identificador de tren válido, manejar el caso de error
                        System.out.println("Error: No se encontró ningún tren asociado a la ruta seleccionada.");
                    }
                }
            });
        }
    
        public static void main(String[] args) {
            TrainModel trainModel = new TrainModel(); // Crear una instancia de TrainModel
            TrainController trainController = new TrainController(trainModel); // Pasar trainModel como argumento
            RouteController routeController = new RouteController(); // Crear una instancia de RouteController
            TicketController ticketController = new TicketController(trainController); // Pasando trainController como argumento
            TicketPurchaseView frame = new TicketPurchaseView(ticketController, trainController, routeController); // Pasando routeController como argumento
            frame.setVisible(true);
        }
    }