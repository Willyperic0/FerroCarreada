import controller.TrainController;
import controller.WagonController;
import model.TrainModel;
import model.WagonModel;
import view.TrainView;
import view.WagonView;

public class App {
    public static void main(String[] args) throws Exception {
        // Crear un objeto TrainModel
        TrainModel train = new TrainModel(1, "Mercedes-Benz", "EXP001", 28, 1000);

        // Crear una vista
        TrainView view = new TrainView();

        // Crear un controlador
        TrainController controller = new TrainController(train, view);

        // Actualizar la vista
        controller.updateView();

        // Actualizar el modelo y luego la vista
        controller.setTrainId(2);
        controller.updateView();

        // Crear vagones
        WagonModel wagon1 = new WagonModel(1, "Pasajeros", 40, train);
        WagonModel wagon2 = new WagonModel(2, "Carga", 500, train);
        
        // Crear una vista
        WagonView Wview = new WagonView();
        
        // Crear un controlador
        WagonController controller1 = new WagonController(wagon1, Wview);
        WagonController controller2 = new WagonController(wagon2, Wview);
        
        // Actualizar la vista para el primer vagón
        controller1.createWagon();
        
        // Actualizar la vista para el segundo vagón
        controller2.createWagon();
        
        // Actualizar el modelo del segundo vagón y luego la vista
        wagon2.setType("Pasajero");
        controller2.createWagon();
    }
}

