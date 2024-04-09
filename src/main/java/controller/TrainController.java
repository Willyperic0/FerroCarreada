package controller;

import model.TrainModel;
import view.TrainView;

public class TrainController {
    private TrainModel model;
    private TrainView view;

    public TrainController(TrainModel model, TrainView view) {
        this.model = model;
        this.view = view;
    }

    public void setTrainId(int trainId) {
        model.setTrainId(trainId);
    }

    public int getTrainId() {
        return model.getTrainId();
    }
    
    public void updateView() {
        view.TrainList(model); // Modificación aquí
        view.printTrainDetails();
    }
}
