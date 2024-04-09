package controller;

import model.WagonModel;
import view.WagonView;

public class WagonController {
    private WagonModel wagon;
    private WagonView view;

    public WagonController(WagonModel wagon, WagonView view) {
        this.wagon = wagon;
        this.view = view;
    }

    public void createWagon() {
        view.WagonList(wagon);
    }

    public void updateView() {
        view.WagonList(wagon); // Modificación aquí
        view.printWagonDetails();
    }

    public WagonModel getWagon() {
        return wagon;
    }

    public void setWagon(WagonModel wagon) {
        this.wagon = wagon;
    }

    public WagonView getView() {
        return view;
    }

    public void setView(WagonView view) {
        this.view = view;
    }
}
