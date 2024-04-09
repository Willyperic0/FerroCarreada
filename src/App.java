import controller.TrainController;
import controller.WagonController;
import model.TrainModel;
import model.WagonModel;
import view.TrainView;
import view.WagonView;
import willy.linkedlist.singly.LinkedList;

public class App {
    public static void main(String[] args) throws Exception {
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

}
}
