package model;

public class StationModel {
    //PENDIENTE MODIFICAR O IMPLEMENTAR ESTACIONES
    public String nameStation;
    public int idStation;
    ScheduleModel schedule = new ScheduleModel(null, null);


   public StationModel(String nameStation, int idStation) {
      this.nameStation = nameStation;
      this.idStation = idStation;
        
   }
   
    public String getNameStation() {
        return this.nameStation;
    }

    public void setNameStation(String nameStation) {
        this.nameStation = nameStation;
    }

    public int getIdStation() {
        return this.idStation;
    }

    public void setIdStation(int idStation) {
        this.idStation = idStation;
    }
}
