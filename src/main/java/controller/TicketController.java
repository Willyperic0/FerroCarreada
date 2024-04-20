package controller;

import java.io.File;
import java.util.Random;

import model.PassengerModel;
import model.TicketModel;
import model.TrainModel;
import willy.linkedlist.doubly.LinkedList;
import willy.priorityqueue.PriorityQueue;

public class TicketController {
    private LinkedList<TicketModel> tickets;
    private TrainController trainController;
    private PriorityQueue<TicketModel> boardingQueue;
    // Constructor de la clase TicketController
    public TicketController(TrainController trainController) {
        this.tickets = new LinkedList<>();
        this.trainController = trainController;
        this.boardingQueue = new PriorityQueue<>(3);
    }

    // Otros métodos de la clase TicketController...
    public TicketModel findTicketByRegistrationId(String registrationId, String trainName) {
        // Crear una nueva lista para almacenar los tickets
        LinkedList<TicketModel> ticketList = new LinkedList<>();
    
        // Especifica la ruta completa del archivo JSON para los tickets
        String ticketFilePath = "FerroCarreada" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "tickets.json";
        System.out.println("Ticket file path: " + ticketFilePath);
    
        // Cargar los datos de tickets desde el archivo JSON en la nueva lista
        loadTicketsFromJson(ticketFilePath, ticketList);
        System.out.println("Ticket list size after loading from JSON: " + ticketList.size());
    
        // Obtener el ID del tren seleccionado
        String trainId = getTrainId(trainName);
        System.out.println("Train ID obtained from getTrainId(): " + trainId);
    
        // Verificar si el registrationId del ticket está presente en la lista de tickets
        for (int i = 0; i < ticketList.size(); i++) {
            TicketModel ticket = ticketList.get(i);
            System.out.println("Ticket registration ID: " + ticket.getRegistrationId());
            System.out.println("Ticket train identifier: " + ticket.getTrain().getIdentifier());
            if (ticket.getRegistrationId().equals(registrationId) && ticket.getTrain().getIdentifier().equals(trainId)) {
                // Si encontramos un ticket con el ID de registro y el ID del tren coincidentes, retornamos el ticket
                System.out.println("Found matching ticket.");
                return ticket;
            }
        }
    
        // Si no se encuentra ningún ticket que cumpla con los criterios, retornamos null
        System.out.println("No matching ticket found.");
        return null;
    }
    
    
    private String getTrainId(String trainName) {
        System.out.println("String getTrainId(String trainName)");
        // Obtener la lista de trenes desde el controlador de trenes
        LinkedList<TrainModel> trainList = trainController.getTrainList();
        
        System.out.println("Train list size: " + trainList.size());
        
        // Buscar el ID del tren por su nombre
        for (int i = 0; i < trainList.size(); i++) {
            TrainModel train = trainList.get(i);
            System.out.println("Train identifier: " + train.getIdentifier());
            if (train.getIdentifier().equals(trainName)) {
                return train.getIdentifier();
            }
        }
        
        // Si no se encuentra ningún tren con el identificador especificado, retornamos null
        return null;
    }
    
    public void loadTicketsFromJson(String ticketFilePath, LinkedList<TicketModel> ticketList) {
        FileJsonAdapter<TicketModel> ticketJsonAdapter = FileJsonAdapter.getInstance();
        LinkedList<TicketModel> loadedTickets = ticketJsonAdapter.getObjects(ticketFilePath, TicketModel[].class);
        if (!loadedTickets.isEmpty()) {
            int size = loadedTickets.size();
            for (int i = 0; i < size; i++) {
                TicketModel ticket = loadedTickets.get(i);
                ticketList.add(ticket);
                System.out.println("Ticket cargado desde archivo JSON: " + ticket);
            }
            System.out.println("Lista de tickets cargada desde archivo JSON correctamente.");
        } else {
            System.out.println("No se pudo cargar la lista de tickets desde el archivo JSON.");
        }
    }

    

    public String getBoardingOrder() {
        StringBuilder boardingOrder = new StringBuilder();
        // Iterar sobre la cola de prioridad y agregar los tickets a la cadena de texto
// Crear una nueva cola de prioridad para almacenar temporalmente los elementos
PriorityQueue<TicketModel> tempQueue = new PriorityQueue<>(boardingQueue.size());

// Copiar los elementos de la cola de abordaje original a la nueva cola
while (!boardingQueue.isEmpty()) {
    TicketModel ticket = boardingQueue.extract();
    tempQueue.insert(ticket);
}

// Restaurar la cola de abordaje original
while (!tempQueue.isEmpty()) {
    TicketModel ticket = tempQueue.extract();
    boardingQueue.insert(ticket);
}

        return boardingOrder.toString();
    }
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
