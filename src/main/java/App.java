import javax.swing.JOptionPane;

import controller.TrainController;
import controller.WagonController;
import model.TrainModel;
import model.WagonModel;
import view.TrainView;
import view.WagonView;

public class App {
    public static void main(String[] args) throws Exception {
        //Crear variables temporales de tren
int tId = 0, tCapacityLoad, tMileage;
String tName, tIdentifier;
        tId++;
        tCapacityLoad = Integer.parseInt(JOptionPane.showInputDialog("tCapacityLoad"));
        tMileage= Integer.parseInt(JOptionPane.showInputDialog("tMileage"));
        tName = JOptionPane.showInputDialog("tName");
        tIdentifier = JOptionPane.showInputDialog("tIdentifier");

    TrainModel train = new TrainModel(tId, tName, tIdentifier, tCapacityLoad, tMileage);
    TrainView trainView = new TrainView();
    TrainController trainController = new TrainController(train, trainView);
    trainController.updateView();

     int wId=0,wCapacity,wPassengerCapacity,wLuggageCapacity, wTypeD;
     String wType,wTrainIdentifier, wIdentifier;
     
        wId++;
        wTypeD = Integer.parseInt(JOptionPane.showInputDialog(null, "Train Type\n1. para Pasajero\n2. para Carga"));
        switch (wTypeD) {
            case 1:
            wType = "Passenger";
            wPassengerCapacity = 40; 
                break;
            case 2:
            wType = "Cargo";
            wPassengerCapacity = 0;
                break; 

            default:
            wType = "Cargo";
            wPassengerCapacity = 0;
                break;
        }
        wIdentifier = JOptionPane.showInputDialog("wIdentifier");
        wTrainIdentifier = tIdentifier;
        WagonModel wagon = new WagonModel(wId, wType, wPassengerCapacity, wTrainIdentifier, wIdentifier);
        WagonView wagonView = new WagonView();
        WagonController wagonController = new WagonController(wagon, wagonView);
        wagonController.updateView();


}
}
 