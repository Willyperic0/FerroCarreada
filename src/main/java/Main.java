import controller.TicketController;
import model.PassengerModel;
import model.TrainModel;

public class Main {
    public static void main(String[] args) {
        // Crear instancia de TicketController
        TicketController ticketController = new TicketController();

        // Agregar algunos tickets de ejemplo
        ticketController.addTicket("2024-04-15 10:00:00", "2024-04-15 12:00:00", "2024-04-15 14:00:00",
            new PassengerModel("Johna", "Doe", 123456789, 12345678, "Jane", "doe", 12345),
                                    new TrainModel("Express", "EXP001", 200, 500),
                                    50.0, "First Class");
        ticketController.addTicket("2024-04-16 11:00:00", "2024-04-16 13:00:00", "2024-04-16 15:00:00",
        new PassengerModel("Alice", "Smith", 987654321, 87654321, "Bob", "Smith", 123456789),
                                    new TrainModel("Local", "LOC001", 100, 300),
                                    30.0, "Economy");

        // Mostrar la lista de tickets
        System.out.println("Lista de tickets:");
        ticketController.displayTickets();

        // Calcular y mostrar el total de ingresos
        double totalRevenue = ticketController.calculateTotalRevenue();
        System.out.println("Total de ingresos: $" + totalRevenue);

        // Guardar los tickets en un archivo JSON
        ticketController.saveTicketsToJson();

        // Cargar los tickets desde el archivo JSON
        ticketController.loadTicketsFromJson();

        // Mostrar la lista de tickets cargada desde el archivo JSON
        System.out.println("Lista de tickets cargada desde archivo JSON:");
        ticketController.displayTickets();
    }
}
