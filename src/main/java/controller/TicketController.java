package controller;

import java.io.File;
import java.util.Random;

import model.EmployeeModel;
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
        loadTicketsFromJson();
    }
    public PriorityQueue<String> getPriorityQueue() {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(3);

        // Agregar los mensajes a la cola de prioridad
        priorityQueue.insert("Bienvenidos");
        priorityQueue.insert("Ingresen clientes VIP");
        priorityQueue.insert("Ingresen clientes ejecutivos");
        priorityQueue.insert("Ingresen clientes estándar");
        priorityQueue.insert("Tengan buen viaje");

        return priorityQueue;
    }

    // Método para imprimir los mensajes de acuerdo con la cola de prioridad
    public void printBoardingOrder(PriorityQueue<String> priorityQueue) {
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.extract());
        }
    }
    // Otros métodos de la clase TicketController...
    public TicketModel findTicketByRegistrationId(String registrationId, String trainName) {
        // Crear una nueva lista para almacenar los tickets
        LinkedList<TicketModel> ticketList = new LinkedList<>();
    
    
        // Cargar los datos de tickets desde el archivo JSON en la nueva lista
        loadTicketsFromJson();

    
        // Obtener el ID del tren seleccionado
        String trainId = getTrainId(trainName);

    
        // Verificar si el registrationId del ticket está presente en la lista de tickets
        for (int i = 0; i < ticketList.size(); i++) {
            TicketModel ticket = ticketList.get(i);

            if (ticket.getRegistrationId().equals(registrationId) && ticket.getTrain().getIdentifier().equals(trainId)) {
                // Si encontramos un ticket con el ID de registro y el ID del tren coincidentes, retornamos el ticket
                return ticket;
            }
        }
    
        // Si no se encuentra ningún ticket que cumpla con los criterios, retornamos null

        return null;
    }
    
    
    private String getTrainId(String trainName) {

        // Obtener la lista de trenes desde el controlador de trenes
        LinkedList<TrainModel> trainList = trainController.getTrainList();
        

        
        // Buscar el ID del tren por su nombre
        for (int i = 0; i < trainList.size(); i++) {
            TrainModel train = trainList.get(i);

            if (train.getIdentifier().equals(trainName)) {
                return train.getIdentifier();
            }
        }
        
        // Si no se encuentra ningún tren con el identificador especificado, retornamos null
        return null;
    }
    
            public void loadTicketsFromJson() {
            String ticketFilePath = "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "tickets.json";
            // Crear una instancia de FileJsonAdapter para trenes
            FileJsonAdapter<TicketModel> ticketJsonAdapter = FileJsonAdapter.getInstance();
        
            // Leer los datos del archivo JSON y establecer la lista de trenes en el controlador de trenes
            LinkedList<TicketModel> loadedTickets = ticketJsonAdapter.getObjects(ticketFilePath, TicketModel[].class);
        
            // Verificar si se leyeron correctamente los datos
            if (loadedTickets != null) {
                // Actualizar la lista de trenes en el controlador de trenes
                this.tickets = loadedTickets;
            } else {
                System.out.println("Error al leer los datos de empleados desde el archivo JSON.");
            }
        }

    

    public String getBoardingOrder(String selectedTrainName) {
        StringBuilder boardingOrder = new StringBuilder();
        
        // Obtener todos los tickets relacionados con el tren seleccionado
        LinkedList<TicketModel> trainTickets = getTrainTickets(selectedTrainName);
        
        // Crear una cola de prioridad para almacenar temporalmente los tickets y establecer el orden de abordaje
        PriorityQueue<TicketModel> boardingQueue = new PriorityQueue<>(trainTickets.size());

        // Agregar los tickets a la cola de prioridad
        for (int i = 0; i < trainTickets.size(); i++) {
            boardingQueue.insert(trainTickets.get(i));
        }

        // Iterar sobre la cola de prioridad y agregar los tickets a la cadena de texto
        while (!boardingQueue.isEmpty()) {
            TicketModel ticket = boardingQueue.extract();
            boardingOrder.append(ticket.toString()).append("\n");
        }

        return boardingOrder.toString();
    }

    // Método para obtener todos los tickets relacionados con el tren seleccionado
    private LinkedList<TicketModel> getTrainTickets(String selectedTrainName) {
        LinkedList<TicketModel> trainTickets = new LinkedList<>();
        // Iterar sobre todos los tickets y agregar aquellos que pertenezcan al tren seleccionado
        for (int i = 0; i < tickets.size(); i++) {
            TicketModel ticket = tickets.get(i);
            if (ticket.getTrain().getIdentifier().equals(selectedTrainName)) {
                trainTickets.add(ticket);
            }
        }
        return trainTickets;
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
        // Carga los datos de tickets desde el archivo JSON antes de devolver la lista de tickets
        loadTicketsFromJson();

        // Retorna la lista de tickets
        return tickets;
    }

// Guardar la lista de tickets en el archivo JSON especificado
public void saveTicketsToJson() {
    // Definir la ruta del archivo donde se guardarán los tickets en formato JSON
    String ticketFilePath = "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "tickets.json";
    
    FileJsonAdapter<TicketModel> ticketJsonAdapter = FileJsonAdapter.getInstance();
    boolean success = ticketJsonAdapter.writeObjects(ticketFilePath, tickets);
    if (success) {

    } else {

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

return;
}
}
// Mostrar mensaje si no se encuentra el boleto con el ID dado

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

        } else {
            for (int i = 0; i < tickets.size(); i++) {
                TicketModel ticket = tickets.get(i);

            }
        }
    }
    
}