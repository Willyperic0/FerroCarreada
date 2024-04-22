package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import controller.RouteController;
import controller.TrainController;
import model.RouteModel;
import model.TrainModel;
import willy.linkedlist.doubly.LinkedList;

public class RouteManagerView extends JFrame {
    private JLabel routeNameLabel;
    private JTextField routeNameField;
    private JLabel routePointsLabel;
    private JTextField routePointsField;
    private JLabel selectTrainLabel;
    private JComboBox<String> trainComboBox;
    private JButton assignButton;
    private JButton editButton; // Botón para editar la ruta
    private JButton deleteButton; // Botón para eliminar la ruta
    private JTable routeTable;
    private RouteController routeController;
    String routesFilePath = "FerroCarreada" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "routes.json";

    public RouteManagerView(RouteController routeController) {
        this.routeController = routeController;
        routeController.printAllRoutes();

        setTitle("Route Manager");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Agregar Ruta"));
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        routeNameLabel = new JLabel("Nombre de la ruta:");
        routeNameField = new JTextField(20);
        routePointsLabel = new JLabel("Puntos de la ruta:");
        routePointsField = new JTextField(20);
        selectTrainLabel = new JLabel("Seleccionar tren:");
        trainComboBox = new JComboBox<>();
        assignButton = new JButton("Asignar Tren");
        assignButton.setBackground(new Color(127, 117, 191));
        assignButton.setForeground(Color.WHITE);

        // Agregar el botón "Volver"
        JButton backButton = new JButton("Volver");
        backButton.setBackground(new Color(127, 117, 191));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            java.awt.EventQueue.invokeLater(() -> {
                new LobbyView().setVisible(true);
            });
            dispose();
        });

        // Agregar el botón "Editar"
        editButton = new JButton("Editar");
        editButton.setBackground(new Color(127, 117, 191));
        editButton.setForeground(Color.WHITE);

        // Agregar el botón "Eliminar"
        deleteButton = new JButton("Eliminar");
        deleteButton.setBackground(new Color(127, 117, 191));
        deleteButton.setForeground(Color.WHITE);

        formPanel.add(routeNameLabel, gbc);
        gbc.gridy++;
        formPanel.add(routeNameField, gbc);
        gbc.gridy++;
        formPanel.add(routePointsLabel, gbc);
        gbc.gridy++;
        formPanel.add(routePointsField, gbc);
        gbc.gridy++;
        formPanel.add(selectTrainLabel, gbc);
        gbc.gridy++;
        formPanel.add(trainComboBox, gbc);
        gbc.gridy++;
        formPanel.add(assignButton, gbc);
        gbc.gridy++;
        formPanel.add(editButton, gbc); // Agregar el botón "Editar"
        gbc.gridy++;
        formPanel.add(deleteButton, gbc); // Agregar el botón "Eliminar"
        gbc.gridy++;
        formPanel.add(backButton, gbc); // Agregar el botón "Volver"

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);

        createRouteTable();
        JScrollPane scrollPane = new JScrollPane(routeTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Lista de Rutas"));
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(formPanel, BorderLayout.WEST);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        // Agregar ActionListener al botón de asignación
        assignButton.addActionListener(e -> {
            String routeName = routeNameField.getText();
            String routePoints = routePointsField.getText();

            // Verificar si el nombre de la ruta o los puntos de la ruta están vacíos
            if (routeName.isEmpty() || routePoints.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar el nombre y los puntos de la ruta.");
                return; // Salir del método sin continuar con la asignación
            }

            TrainModel selectedTrain = routeController.findTrainByIdentifier((String) trainComboBox.getSelectedItem());

            if (selectedTrain != null) {
                // Verificar si el tren está disponible antes de asignarlo a la ruta
                if (!routeController.isTrainAvailable(selectedTrain.getIdentifier())) {
                    // Mostrar un JOptionPane indicando que el tren ya está asignado a una ruta
                    JOptionPane.showMessageDialog(null, "El tren ya ha sido asignado a otra ruta.");
                    return; // Salir del método sin continuar con la asignación
                }

                // Verificar si los puntos de la ruta son válidos antes de asignar el tren
                if (areRoutePointsValid(routePoints)) {
                    // Define los puntos de ruta
                    String[] points = routePoints.split("\\s*,\\s*");

                    // Verificar si la distancia entre los puntos es válida
                    int totalDistance = routeController.calculateTotalDistance(points);
                    if (totalDistance != -1) {
                        // La ruta es válida, proceder con la asignación del tren
                        routeController.assignTrainToRoute(selectedTrain.getIdentifier(), routeName, routePoints, totalDistance);
                        updateRouteTable();
                        routeController.saveRoutesToJson(routesFilePath);
                    } else {
                        // Mostrar un JOptionPane indicando que la ruta no es válida debido a la falta de conexiones
                        JOptionPane.showMessageDialog(null, "La ruta especificada no es válida debido a que algunas conexiones no existen.");
                    }
                } else {
                    // Mostrar un JOptionPane indicando que la ruta especificada no es válida
                    JOptionPane.showMessageDialog(null, "La ruta especificada no es válida.");
                }
            } else {
                // Mostrar un JOptionPane indicando que se debe seleccionar un tren
                JOptionPane.showMessageDialog(null, "Por favor, seleccione un tren.");
            }
        });

        // Agregar ActionListener al botón de "Editar"
        editButton.addActionListener(e -> {
            int selectedRowIndex = routeTable.getSelectedRow();
            if (selectedRowIndex != -1) {
                String routeName = (String) routeTable.getValueAt(selectedRowIndex, 0);
                String selectedTrainIdentifier = (String) trainComboBox.getSelectedItem();
                if (selectedTrainIdentifier != null) {
                    TrainModel selectedTrain = routeController.findTrainByIdentifier(selectedTrainIdentifier);
                    if (selectedTrain != null) {
                        // Validar si el tren seleccionado ya está asignado a otra ruta
                        if (!routeController.isTrainAvailable(selectedTrain.getIdentifier())) {
                            JOptionPane.showMessageDialog(null, "El tren ya ha sido asignado a otra ruta.");
                            return; // Salir del método sin continuar con la edición
                        }
                        routeController.editTrainForRoute(routeName, selectedTrainIdentifier);
                        updateRouteTable();
                        routeController.saveRoutesToJson(routesFilePath);
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor, seleccione un tren válido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un tren.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una ruta para editar.");
            }
        });

        // Agregar ActionListener al botón de "Eliminar"
        deleteButton.addActionListener(e -> {
            int selectedRowIndex = routeTable.getSelectedRow();
            if (selectedRowIndex != -1) {
                String selectedTrainIdentifier = (String) routeTable.getValueAt(selectedRowIndex, 1);
                routeController.removeRouteByTrainId(selectedTrainIdentifier);
                updateRouteTable();
                routeController.saveRoutesToJson(routesFilePath);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una ruta para eliminar.");
            }
        });

        loadTrainsToComboBox();
        startTable(); // Iniciar la tabla cargando y agregando las rutas

        setVisible(true);
    }

    private void createRouteTable() {
        routeTable = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nombre de Ruta", "Tren Asignado", "Puntos de Ruta", "Distancia"}
        )) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que ninguna celda sea editable
            }
        };
        
        // Establecer la selección de una sola fila
        routeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    

    private void loadTrainsToComboBox() {
        LinkedList<TrainModel> trainList = routeController.getTrainList();

        if (trainList != null && !trainList.isEmpty()) {
            trainComboBox.removeAllItems(); // Limpiar el JComboBox

            for (int i = 0; i < trainList.size(); i++) {
                TrainModel train = trainList.get(i);
                trainComboBox.addItem(train.getIdentifier());
            }
        } else {
            System.out.println("No se encontraron trenes disponibles.");
        }
    }

    // Método para verificar si los puntos de ruta tienen conexiones existentes
    private boolean areRoutePointsValid(String routePoints) {
        String[] points = routePoints.split("\\s*,\\s*");
        for (int i = 0; i < points.length - 1; i++) {
            if (routeController.getDistance(points[i], points[i + 1]) == -1) {
                return false;
            }
        }
        return true;
    }

    // Método para actualizar la tabla de rutas
    private void updateRouteTable() {
        // Limpiar la tabla antes de agregar los datos
        DefaultTableModel model = (DefaultTableModel) routeTable.getModel();
        model.setRowCount(0);

        // Obtener la lista de rutas del controlador
        LinkedList<RouteModel> routes = routeController.getRoutes();

        // Agregar cada ruta a la tabla
        for (int i = 0; i < routes.size(); i++) {
            RouteModel route = routes.get(i);
            Object[] row = {route.getRouteName(), route.getTrainModel().getIdentifier(), route.getWaypoints(), route.getDistance()};
            model.addRow(row);
        }
    }

    private void startTable() {
        // Cargar las rutas
        routeController.loadRoutesFromJson(routesFilePath);

        // Actualizar la tabla con las rutas cargadas
        updateRouteTable();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RouteController routeController = new RouteController();
            new RouteManagerView(routeController);
        });
    }
}