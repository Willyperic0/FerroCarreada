package controller;

import java.io.File;
import java.util.Random;

import model.PassengerModel;
import model.TicketModel;
import model.TrainModel;
import willy.linkedlist.doubly.LinkedList;

public class TicketController {
    private LinkedList<TicketModel> tickets;
    private TrainController trainController;

    // Constructor de la clase TicketController
    public TicketController(TrainController trainController) {
        this.tickets = new LinkedList<>();
        this.trainController = trainController;
    }

    // Otros métodos de la clase TicketController...

// Método para obtener un tren aleatorio de la lista de trenes
public TrainModel getRandomTrain() {
    LinkedList<TrainModel> trainList = trainController.getTrainList();
    if (trainList == null || trainList.isEmpty()) {
        return null; // Devuelve null si la lista de trenes está vacía o es nula
    }

    Random rand = new Random();
    int index = rand.nextInt(trainList.size());
    return trainList.get(index);
}
    // Método para obtener la lista de tickets
    public LinkedList<TicketModel> getTicketList() {
        // Especifica la ruta completa del archivo JSON para los tickets
        String ticketFilePath = "FerroCarreada" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "tickets.json";

        // Carga los datos de tickets desde el archivo JSON antes de devolver la lista de tickets
        loadTicketsFromJson(ticketFilePath);

        // Retorna la lista de tickets
        return tickets;
    }

// Guardar la lista de tickets en el archivo JSON especificado
public void saveTicketsToJson() {
    // Definir la ruta del archivo donde se guardarán los tickets en formato JSON
    String ticketFilePath = "FerroCarreada" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "tickets.json";
    
    FileJsonAdapter<TicketModel> ticketJsonAdapter = FileJsonAdapter.getInstance();
    boolean success = ticketJsonAdapter.writeObjects(ticketFilePath, tickets);
    if (success) {
        System.out.println("Lista de tickets guardada en archivo JSON correctamente.");
    } else {
        System.out.println("Error al guardar la lista de tickets en el archivo JSON.");
    }
}

// Cargar la lista de tickets desde el archivo JSON especificado
public void loadTicketsFromJson(String ticketFilePath) {
    FileJsonAdapter<TicketModel> ticketJsonAdapter = FileJsonAdapter.getInstance();
    LinkedList<TicketModel> loadedTickets = ticketJsonAdapter.getObjects(ticketFilePath, TicketModel[].class);
    if (!loadedTickets.isEmpty()) {
        tickets = loadedTickets;
        System.out.println("Lista de tickets cargada desde archivo JSON correctamente.");
    } else {
        System.out.println("No se pudo cargar la lista de tickets desde el archivo JSON.");
    }
}

    // Método para agregar un boleto
    public void addTicket(String purchaseDateTime, String departureDateTime, String arrivalDateTime,
    PassengerModel passenger, TrainModel train, double ticketValue, String category) {
// Generar el ID del boleto
String ticketId = generateTicketId(train, passenger, purchaseDateTime);

// Crear un nuevo objeto TicketModel
TicketModel newTicket = new TicketModel(ticketId, purchaseDateTime, departureDateTime, arrivalDateTime, passenger, train, ticketValue, category);

// Agregar el nuevo boleto a la lista de boletos
tickets.add(newTicket);

// Mostrar mensaje de éxito
System.out.println("Boleto agregado correctamente al sistema.");
}


    // Método para generar el ID del boleto
private String generateTicketId(TrainModel train, PassengerModel passenger, String purchaseDateTime) {
    // Verificar si el tren y su identificador son no nulos
    if (train == null || train.getIdentifier() == null) {
        return null; // Devolver null si el tren o su identificador son nulos
    }

    String trainId = train.getIdentifier().substring(0, 3).replaceAll("[aeiouAEIOU]", "").toUpperCase();
    String passengerId = passenger.getName().substring(0, 2).toUpperCase() +
                         passenger.getLastName().substring(0, 2).toUpperCase() +
                         String.valueOf(passenger.getDni()).substring(6);
    String purchaseDate = purchaseDateTime.replaceAll("[^0-9]", "").substring(0, 8);

    return trainId + passengerId + purchaseDate;
}

    // Método para eliminar un boleto por su ID
    public void deleteAndReorganizeTicket(String ticketIdToDelete) {
        // Crear una lista temporal para almacenar los boletos
        LinkedList<TicketModel> tempTicketList = new LinkedList<>();
        
        // Recorrer los boletos originales y agregar aquellos cuyo ID no coincida con el ID a eliminar
        for (int i = 0; i < tickets.size(); i++) {
            TicketModel ticket = tickets.get(i);
            if (!ticket.getRegistrationId().equals(ticketIdToDelete)) {
                tempTicketList.add(ticket); // Agregar el boleto a la lista temporal
            }
        }
        
        // Actualizar la lista de boletos con la lista temporal
        tickets = tempTicketList;
        
        // Mostrar mensaje de éxito
        System.out.println("Boleto eliminado correctamente.");
    }   

    // Método para buscar un boleto por su ID
    public TicketModel findTicketById(String ticketId) {
        // Recorrer la lista de boletos para encontrar el boleto con el ID dado y devolverlo
        for (int i = 0; i < tickets.size(); i++) {
            TicketModel ticket = tickets.get(i);
            if (ticket.getRegistrationId().equals(ticketId)) {
                return ticket;
            }
        }
        return null; // Devolver null si no se encuentra ningún boleto con el ID dado
    }
    

    // Método para actualizar la información de un boleto
    public void updateTicketInfo(String ticketId, String purchaseDateTime, String departureDateTime,
    String arrivalDateTime, PassengerModel passenger, TrainModel train,
    double ticketValue, String category) {
// Recorrer la lista de boletos para encontrar el boleto con el ID dado
for (int i = 0; i < tickets.size(); i++) {
TicketModel ticket = tickets.get(i);
if (ticket.getRegistrationId().equals(ticketId)) {
// Actualizar la información del boleto
ticket.setPurchaseDateTime(purchaseDateTime);
ticket.setDepartureDateTime(departureDateTime);
ticket.setArrivalDateTime(arrivalDateTime);
ticket.setPassenger(passenger);
ticket.setTrain(train);
ticket.setTicketValue(ticketValue);
ticket.setCategory(category);
System.out.println("Información del boleto actualizada correctamente.");
return;
}
}
// Mostrar mensaje si no se encuentra el boleto con el ID dado
System.out.println("No se encontró ningún boleto con el ID proporcionado.");
}


    // Método para calcular el total de ingresos por venta de boletos
    public double calculateTotalRevenue() {
        double totalRevenue = 0.0;
        // Sumar el valor de cada boleto vendido
        for (int i = 0; i < tickets.size(); i++) {
            TicketModel ticket = tickets.get(i);
            totalRevenue += ticket.getTicketValue();
        }
        return totalRevenue;
    }
    public void displayTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No hay tickets en el sistema.");
        } else {
            for (int i = 0; i < tickets.size(); i++) {
                TicketModel ticket = tickets.get(i);
                System.out.println(ticket);
            }
        }
    }
    
}
