package view;

import java.io.File;

import javax.swing.JOptionPane;

import model.EmployeeModel;
import model.TicketModel;
import model.TrainModel;
import controller.EmployeeController;
import controller.LoginController;
import controller.RouteController;
import controller.TicketController;
import controller.TrainController;
import controller.FileJsonAdapter;
import willy.linkedlist.doubly.LinkedList;
import view.EmployeeView;

public class LoginView extends javax.swing.JFrame {
    
    // Lista de empleados cargada desde el archivo JSON

// Instancia del controlador de tickets con el controlador de trenes como parámetro


    static EmployeeModel employeeModel = new EmployeeModel();
    static EmployeeController employeeController = new EmployeeController(employeeModel);
    static TrainModel trainModel = new TrainModel();
    static TrainController trainController = new TrainController(trainModel);
    static LoginController loginController = new LoginController();
    static TicketController ticketController = new TicketController(trainController);
    
    
    /**
     * Creates new form LoginView
     */
    public LoginView() {
        initComponents();
        // Leer los datos del archivo JSON y establecer la lista de empleados en el controlador de empleados
        setSize(800,500);
        setLocationRelativeTo(null);
        loginController.crearCarpetasYArchivos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Usuario");

        jTextField1.setBackground(new java.awt.Color(242, 242, 242));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Contrasena");

        jPasswordField1.setBackground(new java.awt.Color(242, 242, 242));

        jButton1.setBackground(new java.awt.Color(127, 117, 191));
        jButton1.setForeground(new java.awt.Color(245, 245, 245));
        jButton1.setText("INGRESAR");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(127, 117, 191));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("BIENVENIDO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 100, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                .addComponent(jTextField1))
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel3)
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String user = jTextField1.getText();
        String password = jPasswordField1.getText();

        // Verificar si el usuario o la contraseña están vacíos
        if (user.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Uno de los campos se encuentra vacío");
        }  else if (user.equals("purchase") && password.equals("purchase")) {
            // Mostrar mensaje de bienvenida para el usuario admin
            JOptionPane.showMessageDialog(null, "Ingresando a venta de tickets");

            TicketController ticketController = new TicketController(new TrainController(new TrainModel()));
            RouteController routeController = new RouteController();
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new TicketPurchaseView(ticketController,trainController, routeController).setVisible(true);
                }
            });
            this.dispose();
        }
        else {
            // Verificar si el usuario y la contraseña coinciden con algún empleado
            boolean isAuthenticated = loginController.authenticate(user, password, employeeController.getEmployeeList());

            // Mostrar el mensaje correspondiente según la autenticación
            if (isAuthenticated) {
                Boolean role = loginController.authenticateRole(user, password, employeeController.getEmployeeList());
                // Obtener el nombre completo del empleado autenticado
                if (role == false) {
                String fullName = employeeController.getEmployeeFullName(user);
                // Construir el mensaje de bienvenida personalizado
                String welcomeMessage = "Bienvenido, " + fullName;
                JOptionPane.showMessageDialog(null, welcomeMessage);

                // Crear una instancia de TicketSearchView y hacerla visible
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        TicketSearchView ticketSearchView = new TicketSearchView(ticketController, trainController);
                        ticketSearchView.setVisible(true);
                    }
                });
                this.dispose(); // Cerrar la ventana actual
            } else if(role == true) {
                String fullName = employeeController.getEmployeeFullName(user);
                // Construir el mensaje de bienvenida personalizado
                String welcomeMessage = "Bienvenido, " + fullName;
                JOptionPane.showMessageDialog(null, welcomeMessage);

                // Crear una instancia de LobbyView y hacerla visible
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new LobbyView().setVisible(true);
                    }
                });
                this.dispose(); // Cerrar la ventana actual
            }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            }
        }
    }                                          

    public static int getLoggedUserDNI() {
        String user = jTextField1.getText();
        LinkedList<EmployeeModel> employeeList = employeeController.getEmployeeList();
        return loginController.getLoggedUserDNI(user, employeeList);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private static javax.swing.JTextField jTextField1;
    // End of variables declaration                   
}