package view;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.EmployeeController;
import controller.FileJsonAdapter;
import controller.LoginController;
import model.EmployeeModel;
import willy.linkedlist.doubly.LinkedList;

public class EmployeeView extends javax.swing.JFrame {

    private final EmployeeController employeeController;

    public EmployeeView(EmployeeController employeeController) {
        initComponents();
        this.employeeController = employeeController;
        loadEmployeesFromJson(); // Actualiza la tabla al iniciar la ventana

        // Establecer el tamaño de la ventana
        setSize(800, 500);
        setLocationRelativeTo(null);
        // Personalización de colores y tipografía
        customizeUI();
    }

    private void customizeUI() {
        // Colores
        Color backgroundColor = new Color(255, 255, 255); // Blanco para el fondo
        Color textColor = new Color(78, 41, 115); // Morado para el texto
        Color buttonColor = new Color(127, 117, 191); // Púrpura para los botones

        // Tipografía
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14); // Fuente para las etiquetas
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14); // Fuente para los botones

        // Aplicar colores y tipografía a los componentes
        jPanel1.setBackground(backgroundColor);
        jPanel1.setBorder(BorderFactory.createTitledBorder("Agregar Empleado"));
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

        jPanel2.setBackground(backgroundColor);
        jPanel2.setBorder(BorderFactory.createTitledBorder("Lista de Empleados"));

        jScrollPane1.getViewport().setBackground(Color.WHITE); // Fondo blanco para la tabla
        employeeTable.setForeground(textColor);
        employeeTable.setFont(labelFont);

        backButton.setBackground(buttonColor);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(buttonFont);

        modifyButton.setText("Modificar");
        modifyButton.setBackground(new Color(127, 117, 191)); // Color púrpura para el fondo del botón
        modifyButton.setForeground(Color.WHITE); // Texto blanco para el botón
        modifyButton.setFont(buttonFont);
    }

    private void loadEmployeesFromJson() {
        // Ruta del archivo JSON para empleados
        String pathFile = "FerroCarreada" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "employees.json";

        // Crear una instancia de FileJsonAdapter para empleados
        FileJsonAdapter<EmployeeModel> employeeJsonAdapter = FileJsonAdapter.getInstance();

        // Leer los datos de empleados desde el archivo JSON
        LinkedList<EmployeeModel> employeeList = employeeJsonAdapter.getObjects(pathFile, EmployeeModel[].class);

        // Verificar si se leyeron correctamente los datos
        if (employeeList != null) {
            // Establecer la lista de empleados en el controlador de empleados
            employeeController.setEmployeeList(employeeList);
            // Actualizar la tabla de empleados con los datos leídos
            updateEmployeeTable();
        } else {
            JOptionPane.showMessageDialog(null, "Error al leer los datos de empleados desde el archivo JSON.");
        }
    }

    // ActionListener para el botón "AGREGAR"
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String name = nameText.getText();
        String lastName = lastNameText.getText();
        int phoneNumber = Integer.parseInt(phoneText.getText());
        int dni = Integer.parseInt(dniText.getText());

        // Generar automáticamente el usuario y la contraseña
        String username = generateUsername(name, lastName, dni);
        String password = generatePassword(name, lastName, dni);
        boolean role = false;

        // Agregar empleado al controlador
        employeeController.addEmployee(name, lastName, phoneNumber, dni, username, password, role);

        // Actualizar la tabla de empleados
        updateEmployeeTable();

        // Limpiar los campos de texto después de agregar un empleado
        clearTextFields();

    }

    // ActionListener para el botón "ELIMINAR"
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un empleado para eliminar.");
            return;
        }

        int dni = Integer.parseInt(employeeTable.getValueAt(selectedRow, 3).toString()); // Obtener DNI de la fila seleccionada

        // Obtener el DNI del usuario que inició sesión
        int loggedUserDni = LoginController.loggedDNI();

        // Verificar si el empleado seleccionado es el mismo que el usuario que inició sesión
        if (dni == loggedUserDni) {
            JOptionPane.showMessageDialog(null, "No puedes eliminarte a ti mismo.");
            return;
        }

        // Eliminar empleado del controlador
        employeeController.deleteEmployee(dni);

        // Actualizar la tabla de empleados
        updateEmployeeTable();

    }

    // ActionListener para el botón "Modificar"
private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {
    int selectedRow = employeeTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Seleccione un empleado para modificar.");
        return;
    }

    // Obtener los datos del empleado seleccionado de la tabla
    String name = nameText.getText();
    String lastName = lastNameText.getText();
    String phoneNumberText = phoneText.getText();
    String dniTextInput = dniText.getText();
    String compare = dniTextInput;

    // Validar que al menos un campo esté lleno
    if (name.isEmpty() && lastName.isEmpty() && phoneNumberText.isEmpty() && dniTextInput.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Ingrese al menos un campo para modificar.");
        return;
    }

    // Obtener los datos del empleado seleccionado de la tabla
    EmployeeModel selectedEmployee = employeeController.getEmployeeList().get(selectedRow);
    String originalName = selectedEmployee.getName();
    String originalLastName = selectedEmployee.getLastName();
    int originalPhoneNumber = selectedEmployee.getPhoneNumber();
    int originalDni = selectedEmployee.getDni();

    // Actualizar los campos que estén llenos, manteniendo los antiguos si están vacíos
    if (!name.isEmpty()) {
        originalName = name;
    }
    if (!lastName.isEmpty()) {
        originalLastName = lastName;
    }
    int phoneNumber = originalPhoneNumber;
    if (!phoneNumberText.isEmpty()) {
        phoneNumber = Integer.parseInt(phoneNumberText);
    }
    int dni = originalDni;
    if (!dniTextInput.isEmpty()) {
        dni = Integer.parseInt(dniTextInput);
    }

    // Generar automáticamente el usuario y la contraseña
    String username = generateUsername(originalName, originalLastName, dni);
    String password = generatePassword(originalName, originalLastName, dni);

    // Llamar al método para modificar los datos del empleado seleccionado
    employeeController.updateEmployeeData(originalDni, originalName, originalLastName, phoneNumber, username, password, compare);

    // Actualizar la tabla de empleados
    updateEmployeeTable();

    // Limpiar los campos de texto después de modificar un empleado
    clearTextFields();
}

    
    // Método para actualizar la tabla de empleados
    private void updateEmployeeTable() {
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de agregar los datos

        // Obtener la lista de empleados del controlador
        LinkedList<EmployeeModel> employeeList = employeeController.getEmployeeList();

        // Agregar cada empleado a la tabla
        for (int i = 0; i < employeeList.size(); i++) {
            EmployeeModel employee = employeeList.get(i);
            Object[] row = {employee.getName(), employee.getLastName(), employee.getPhoneNumber(), employee.getDni()};
            model.addRow(row);
        }
    }


    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LobbyView().setVisible(true);
            }
        });
        this.dispose();
    }

    // Método para limpiar los campos de texto después de agregar o modificar un empleado
    private void clearTextFields() {
        nameText.setText("");
        lastNameText.setText("");
        phoneText.setText("");
        dniText.setText("");
    }

    // Método para generar un nombre de usuario basado en el nombre, apellido y los dos últimos dígitos del DNI
    private String generateUsername(String name, String lastName, int dni) {
        // Obtiene los dos últimos dígitos del DNI
        String idDigits = String.valueOf(dni);
        if (idDigits.length() > 2) {
            idDigits = idDigits.substring(idDigits.length() - 2);
        }
        // Genera el nombre de usuario concatenando el nombre, apellido y los dos últimos dígitos del DNI, todo en minúsculas
        return name.toLowerCase() + "." + lastName.toLowerCase() + "." + idDigits;
    }

    // Método para generar una contraseña basada en el primer carácter del nombre, apellido y el DNI
    private String generatePassword(String name, String lastName, int dni) {
        // Obtiene el primer carácter del nombre y del apellido
        char firstCharName = name.charAt(0);
        char firstCharLastName = lastName.charAt(0);
        // Genera la contraseña concatenando los primeros caracteres del nombre, apellido y el DNI
        return String.valueOf(firstCharName) + String.valueOf(firstCharLastName) + dni;
    }

    // Método para inicializar los componentes de la interfaz gráfica
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lastNameText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        phoneText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        dniText = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton(); // Botón de retorno agregado
        modifyButton = new javax.swing.JButton(); // Botón de modificación agregado
    
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Employee Management System");
        setSize(new java.awt.Dimension(800, 500)); // Ajuste del tamaño de la ventana
    
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Empleado"));
        jPanel1.setBackground(new Color(255, 255, 255)); // Color blanco
    
        jLabel1.setText("Nombre:");
        jLabel1.setForeground(new Color(78, 41, 115)); // Color morado
    
        jLabel2.setText("Apellido:");
        jLabel2.setForeground(new Color(78, 41, 115)); // Color morado
    
        jLabel3.setText("Teléfono:");
        jLabel3.setForeground(new Color(78, 41, 115)); // Color morado
    
        jLabel4.setText("DNI:");
        jLabel4.setForeground(new Color(78, 41, 115)); // Color morado
    
        addButton.setText("Agregar");
        addButton.setBackground(new Color(127, 117, 191)); // Color púrpura para el fondo del botón
        addButton.setForeground(Color.WHITE); // Texto blanco para el botón
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
    
        deleteButton.setText("Eliminar");
        deleteButton.setBackground(new Color(127, 117, 191)); // Color púrpura para el fondo del botón
        deleteButton.setForeground(Color.WHITE); // Texto blanco para el botón
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
    
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Empleados"));
        jPanel2.setBackground(new Color(255, 255, 255)); // Color blanco
    
        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
    
            },
            new String [] {
                "Nombre", "Apellido", "Teléfono", "DNI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };
    
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    
        jScrollPane1.setViewportView(employeeTable);
    
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
    
        backButton.setText("Volver"); // Configuración del botón de retorno
        backButton.setBackground(new Color(127, 117, 191)); // Color púrpura para el fondo del botón
        backButton.setForeground(Color.WHITE); // Texto blanco para el botón
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
    
        // Configuración del botón "Modificar"
        modifyButton.setText("Modificar");
        modifyButton.setBackground(new Color(127, 117, 191)); // Color púrpura para el fondo del botón
        modifyButton.setForeground(Color.WHITE); // Texto blanco para el botón
        modifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyButtonActionPerformed(evt);
            }
        });
    
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameText)
                    .addComponent(lastNameText)
                    .addComponent(phoneText)
                    .addComponent(dniText, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modifyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lastNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(phoneText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(dniText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyButton)) // Agregar el botón "Modificar"
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backButton) // Añadir el botón de retorno a la disposición
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backButton) // Añadir el botón de retorno a la disposición
                .addContainerGap())
        );
    
        pack();
    }
    

    // Variables declaration - do not modify
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton; // Declaración del botón de retorno
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton modifyButton; // Declaración del botón "Modificar"
    private javax.swing.JTable employeeTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField dniText;
    private javax.swing.JTextField lastNameText;
    private javax.swing.JTextField nameText;
    private javax.swing.JTextField phoneText;
    // End of variables declaration
    
}
