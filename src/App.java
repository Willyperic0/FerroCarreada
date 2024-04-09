import javax.swing.JOptionPane;

import controller.TrainController;
import controller.WagonController;
import model.TrainModel;
import model.WagonModel;
import view.TrainView;
import view.WagonView;

public class App {
    public static void main(String[] args) throws Exception {
        String name = JOptionPane.showInputDialog(null,"Porfavor ingrese su nombre","MODULO EMPLEADOS", 0);
        JOptionPane.showMessageDialog(null, "Bienvenido "+name, "MODULO EMPLEADOS", 0);
        int rta = Integer.parseInt(JOptionPane.showInputDialog(null,"Que desea hacer?\n1. agregar trenes\n2.agregar vagones\n3.gestionar tickets","MODULO EMPLEADOS", 0));
        switch (rta) {
            case 1:
                String Tname, Tidentifier;
                int Tid, Tcapacity, TMileage;
                Tname = JOptionPane.showInputDialog(null,"Porfavor ingrese el nombre del tren","MODULO EMPLEADOS - Registro de trenes", 0);
                Tidentifier = JOptionPane.showInputDialog(null,"Porfavor ingrese el identificador del tren","MODULO EMPLEADOS - Registro de trenes", 0);
                Tid = Integer.parseInt(JOptionPane.showInputDialog(null,"Porfavor ingrese el id del tren","MODULO EMPLEADOS - Registro de trenes", 0));
                Tcapacity = Integer.parseInt(JOptionPane.showInputDialog(null,"Porfavor ingrese la capacidad del tren","MODULO EMPLEADOS - Registro de trenes", 0));
                TMileage = Integer.parseInt(JOptionPane.showInputDialog(null,"Porfavor ingrese la mileage del tren","MODULO EMPLEADOS - Registro de trenes", 0));
                TrainModel newTrain = new TrainModel(Tid, Tname, Tidentifier, Tcapacity, TMileage);
                TrainView trainView = new TrainView();
                TrainController trainController = new TrainController(newTrain, trainView);
                trainController.updateView();
                break;
            case 2:
            JOptionPane.showMessageDialog(null, "lo siento "+name+" este modulo no se encuentra disponible por el momento", "MODULO EMPLEADOS", 0); 
                break;
            case 3:
            JOptionPane.showMessageDialog(null, "lo siento "+name+" este modulo no se encuentra disponible por el momento", "MODULO EMPLEADOS", 0); 
                break;
            default:
                break;
        }
/* 
        // Crear un objeto TrainModel
TrainModel train = new TrainModel(1, "Mercedes-Benz", "EXP001", 28, 1000);

// Crear una vista para el tren
TrainView trainView = new TrainView();

// Crear un controlador para el tren
TrainController trainController = new TrainController(train, trainView);

// Actualizar la vista del tren
trainController.updateView();

// Crear vagones
WagonModel wagon1 = new WagonModel(1, "Pasajeros", 40, train);

WagonModel wagon2 = new WagonModel(2, "Carga", 500, train);

// Crear una vista para los vagones
WagonView wagonView = new WagonView();

// Crear un controlador para el primer vagón
WagonController wagonController1 = new WagonController(wagon1, wagonView);

// Crear un controlador para el segundo vagón
WagonController wagonController2 = new WagonController(wagon2, wagonView);

// Actualizar la vista para el primer y segundo vagón
wagonController1.updateView();
wagonController2.updateView();
changua
*/
}
}
 