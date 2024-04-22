package controller;

import willy.linkedlist.doubly.LinkedList;
import willy.maps.hashmap.hashmap;
import java.io.File;

import model.RouteModel;
import model.TrainModel;

public class RouteController {
    private TrainController trainController; 
    private hashmap<Integer> distances;
    private LinkedList<TrainModel> trainList; // Lista de trenes
    private LinkedList<RouteModel> routes; // Lista de rutas
    String routesFilePath = "FerroCarreada" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "routes.json";
    
    public RouteController() {
        this.trainController = new TrainController(new TrainModel()); // Instanciar TrainController con una nueva instancia de TrainModel
        this.distances = new hashmap<>(100); // Tamaño inicial del hashmap
        this.routes = new LinkedList<>(); // Inicializar la lista de rutas
        setDistances();
        loadRoutesFromJson(routesFilePath);
        loadTrainList(); // Cargar la lista de trenes al inicializar RouteMController
    }

    
    public LinkedList<RouteModel> loadRoutes(String routesFilePath) {
        // Crear una instancia de FileJsonAdapter para rutas
        FileJsonAdapter<RouteModel> routeJsonAdapter = FileJsonAdapter.getInstance();
    
        // Leer los datos del archivo JSON y establecer la lista de rutas en el controlador de rutas
        LinkedList<RouteModel> routeList = routeJsonAdapter.getObjects(routesFilePath, RouteModel[].class);
    
        // Verificar si se leyeron correctamente los datos
        if (routeList != null) {
            // Actualizar la lista de rutas en el controlador de rutas
            this.routes = routeList;
        } else {
            System.out.println("Error al leer los datos de rutas desde el archivo JSON.");
        }
        System.out.println("JSON leído.");
        loadTrainList(); // Puedes llamar a este método aquí o donde sea necesario
        return routeList; // Devolver la lista de rutas cargadas
    }
    
    public boolean isTrainAvailable(String trainIdentifier) {
        // Obtener el tamaño de la lista de rutas
        int size = routes.size();
        // Iterar sobre la lista de rutas
        for (int i = 0; i < size; i++) {
            // Obtener la ruta en la posición i
            RouteModel route = routes.get(i);
            // Verificar si el tren está asignado a esta ruta
            if (route.getTrainModel().getIdentifier().equals(trainIdentifier)) {
                return false; // El tren está asignado a una ruta, por lo tanto no está disponible
            }
        }
        return true; // El tren no está asignado a ninguna ruta, por lo tanto está disponible
    }
    public String changuaTrainId(String routeName) {
        // Iterar sobre la lista de rutas para encontrar la ruta con el nombre dado
        for (int i = 0; i < routes.size(); i++) {
            RouteModel route = routes.get(i);
            if (route.getRouteName().equals(routeName)) {
                // Se encontró la ruta, devolver el ID del tren asignado a esta ruta
                return route.getTrainModel().getIdentifier();
            }
        }
        // Si no se encuentra ninguna ruta con el nombre dado, devolver null
        return null;
    }
    
    public TrainModel findTrainByIdentifier(String identifier) {
        // Obtener la lista de trenes
        LinkedList<TrainModel> trainList = getTrainList();
    
        // Iterar sobre la lista de trenes para buscar el tren por identificador
        for (int i = 0; i < trainList.size(); i++) {
            TrainModel train = trainList.get(i);
            if (train.getIdentifier().equals(identifier)) {
                return train; // Devolver el tren si se encuentra
            }
        }
    
        return null; // Devolver null si no se encuentra ningún tren con el identificador dado
    }
    
    // Método para obtener la lista de rutas
// Método para obtener la lista de rutas
public LinkedList<RouteModel> getRoutes() {
    // Especifica la ruta completa del archivo JSON para las rutas
    String routesFilePath = "FerroCarreada" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "routes.json";
    
    // Carga los datos de rutas desde el archivo JSON antes de devolver la lista de rutas
    loadRoutesFromJson(routesFilePath);

    // Retorna la lista de rutas
    return routes;
}

    public void loadTrainList() {
        // Si la lista de rutas está vacía, cargamos los trenes desde el controlador de trenes
        trainList = trainController.getTrainList();
    }
    
    // Método para obtener la lista de trenes
    public LinkedList<TrainModel> getTrainList() {
        loadTrainList();
        return trainList;
    }
    
    
    // Método para guardar las rutas en un archivo JSON
    public void saveRoutesToJson(String routesFilePath) {
        // Crear una instancia de FileJsonAdapter para rutas
        FileJsonAdapter<RouteModel> routeJsonAdapter = FileJsonAdapter.getInstance();
    
        // Guardar los datos de rutas en un archivo JSON
        boolean success = routeJsonAdapter.writeObjects(routesFilePath, routes);
    
        // Mostrar mensaje de éxito o error para rutas
        if (success) {
            System.out.println("Datos de rutas guardados correctamente en el archivo JSON.");
        } else {
            System.out.println("Error al guardar los datos de rutas en el archivo JSON.");
        }
    }
    
    // Método para cargar las rutas desde un archivo JSON
    public void loadRoutesFromJson(String routesFilePath) {
        // Crear una instancia de FileJsonAdapter para rutas
        FileJsonAdapter<RouteModel> routeJsonAdapter = FileJsonAdapter.getInstance();
    
        // Leer los datos del archivo JSON y establecer la lista de rutas en el controlador de rutas
        LinkedList<RouteModel> routeList = routeJsonAdapter.getObjects(routesFilePath, RouteModel[].class);
    
        // Verificar si se leyeron correctamente los datos
        if (routeList != null) {
            // Actualizar la lista de rutas en el controlador de rutas
            this.routes = routeList;
        } else {
            System.out.println("Error al leer los datos de rutas desde el archivo JSON.");
        }
        System.out.println("JSON leido.");
    }
    
    public void assignTrainToRoute(String trainIdentifier, String routeName, String points, int distance) {
        // Obtener el tren del controlador de trenes
        TrainModel train = trainController.findTrainByIdentifier(trainIdentifier);
        if (train == null) {
            System.out.println("Error: No se encontró ningún tren con el identificador proporcionado.");
            return;
        }
        
        // Crear la ruta y asignar el tren
        RouteModel route = new RouteModel(routeName, train, points, distance);
        routes.add(route); // Agregar la ruta a la lista de rutas
        System.out.println("Tren asignado a la ruta correctamente.");
        saveRoutesToJson(routesFilePath);
    }
    
    // Método para definir la distancia entre dos puntos
    public void setDistance(String point1, String point2, int distance) {
        // Construimos una clave única para esta conexión, por ejemplo, combinando los nombres de los puntos
        String key = point1 + "-" + point2;
        // Guardamos la distancia en el hashmap
        distances.put(key, distance);
    }
    
    // Método para obtener la distancia entre dos puntos
    public int getDistance(String point1, String point2) {
        // Construimos la clave para buscar la distancia
        String key = point1 + "-" + point2;
        // Buscamos la distancia en el hashmap
        Integer distance = distances.get(key);
        // Si la distancia no está definida, devolvemos un valor por defecto, por ejemplo, -1
        return distance != null ? distance : -1;
    }
    
    // Método para definir una ruta personalizada y guardarla en el HashMap
    public void defineCustomRoute(String... points) {
        // Comprobar si la ruta es válida
        for (int i = 0; i < points.length - 1; i++) {
            if (getDistance(points[i], points[i + 1]) == -1) {
                System.out.println("La ruta especificada no es válida debido a que la distancia entre " + points[i] + " y " + points[i + 1] + " no está definida.");
                return;
            }
        }
        // La ruta es válida, agregarla al HashMap
        for (int i = 0; i < points.length - 1; i++) {
            String point1 = points[i];
            String point2 = points[i + 1];
            int distance = getDistance(point1, point2);
            setDistance(point1, point2, distance);
        }
    }
    
    // Método para definir las distancias predefinidas
    private void setDistances() {
        setDistance("A", "B", 30);
        setDistance("B", "A", 30);
        setDistance("A", "D", 50);
        setDistance("D", "A", 50);
        setDistance("A", "C", 40);
        setDistance("C", "A", 40);
        setDistance("C", "I", 80);
        setDistance("I", "C", 80);
        setDistance("C", "J", 120);
        setDistance("J", "C", 120);
        setDistance("C", "K", 110);
        setDistance("K", "C", 110);
        setDistance("D", "E", 20);
        setDistance("E", "D", 20);
        setDistance("E", "F", 65);
        setDistance("F", "E", 65);
        setDistance("F", "A", 50);
        setDistance("A", "F", 50);
        setDistance("F", "G", 80);
        setDistance("G", "F", 80);
        setDistance("G", "H", 30);
        setDistance("H", "G", 30);
        setDistance("G", "I", 145);
        setDistance("I", "G", 145);
    }

    
    // Método para calcular la distancia total recorrida a partir de una cadena de puntos
    public int calculateTotalDistance(String[] points) {
        int totalDistance = 0;
        
        // Iterar sobre el array de puntos para sumar las distancias entre ellos
        for (int i = 0; i < points.length - 1; i++) {
            String point1 = points[i];
            String point2 = points[i + 1];
            
            // Verificar si la distancia entre los puntos está definida
            int distance = getDistance(point1, point2);
            if (distance == -1) {
                // La distancia entre los puntos no está definida, por lo que la ruta no es válida
                System.out.println("La ruta especificada no es válida debido a que la distancia entre " + point1 + " y " + point2 + " no está definida.");
                return -1;
            }
            
            // Sumar la distancia al total
            totalDistance += distance;
        }
        
        return totalDistance;
    }
    public int findDistanceByRouteName(String routeName) {
        // Obtener la lista de rutas del RouteController
        LinkedList<RouteModel> routeList = getRoutes();
        
        // Recorrer la lista de rutas para encontrar la distancia de la ruta con el nombre especificado
        for (int i = 0; i < routeList.size(); i++) {
            RouteModel route = routeList.get(i);
            if (route.getRouteName().equals(routeName)) {
                // Se encontró la ruta, devolver la distancia
                return route.getDistance();
            }
        }
        
        // Si no se encuentra ninguna ruta con el nombre especificado, devolver -1 o manejar el caso según sea necesario
        return -1;
    }
    
    
    // Método para imprimir todas las distancias
    public void printAllDistances() {
        System.out.println("Distancias entre puntos:");
        // Iteramos sobre todas las combinaciones de puntos
        for (char c = 'A'; c <= 'K'; c++) {
            for (char d = 'A'; d <= 'K'; d++) {
                // Ignoramos las combinaciones de un punto consigo mismo
                if (c != d) {
                    // Construimos la clave para buscar la distancia de c a d
                    String key1 = c + "-" + d;
                    // Construimos la clave para buscar la distancia de d a c
                    String key2 = d + "-" + c;
                    // Buscamos la distancia en el hashmap
                    Integer distance1 = distances.get(key1);
                    Integer distance2 = distances.get(key2);
                    // Si la distancia está definida, la imprimimos
                    if (distance1 != null) {
                        System.out.println(c + " -> " + d + ": " + distance1);
                    }
                    if (distance2 != null) {
                        System.out.println(d + " -> " + c + ": " + distance2);
                    }
                }
            }
        }
    }
    
    public void printAllRoutes() {
        System.out.println("Lista de rutas:");
        for (int i = 0; i < routes.size(); i++) {
            RouteModel route = routes.get(i);
            System.out.println("Nombre de la ruta: " + route.getRouteName());
            System.out.println("Tren de la ruta: " + route.getTrainModel().getIdentifier());
            System.out.println("Puntos por donde pasa la ruta: " + route.getWaypoints());
            System.out.println("Distancia total de la ruta: " + route.getDistance() + " km");
            System.out.println("------------------------------------");
        }
    }
    
    public static void main(String[] args) {
        RouteController controller = new RouteController();
        controller.loadTrainList();
        LinkedList<TrainModel> trainList = controller.getTrainList();
        
        String[] points = {"A", "B"};
        controller.defineCustomRoute(points);
        int distance = controller.calculateTotalDistance(points);
        String trainIdentifier = "TrainLuis";
        String routeName = "Ruta1";
        controller.assignTrainToRoute(trainIdentifier, routeName, String.join(",", points), distance);
        printRouteData(routeName, String.join(",", points), distance);
        controller.printAllRoutes();
    }
    public void removeRouteByTrainId(String trainIdToDelete) {
        // Crear una LinkedList temporal para almacenar las rutas que no coinciden con el tren a eliminar
        LinkedList<RouteModel> tempRouteList = new LinkedList<>();
        
        // Recorrer los elementos originales y agregar aquellos cuyo identificador de tren asignado no coincida con el tren a eliminar
        for (int i = 0; i < routes.size(); i++) {
            RouteModel route = routes.get(i);
            if (!route.getTrainModel().getIdentifier().equals(trainIdToDelete)) {
                tempRouteList.add(route); // Agregar la ruta a la lista temporal
            }
        }
        
        // Limpiar la lista original de rutas
        routes.clear();
        
        // Agregar las rutas de la lista temporal a la lista original una por una
        for (int i = 0; i < tempRouteList.size(); i++) {
            routes.add(tempRouteList.get(i));
        }
        saveRoutesToJson(routesFilePath);
    }
    public TrainModel getTrainDataByRouteName(String routeName) {
        // Iterar sobre la lista de rutas para encontrar la ruta con el nombre dado
        for (int i = 0; i < routes.size(); i++) {
            RouteModel route = routes.get(i);
            if (route.getRouteName().equals(routeName)) {
                // Encontramos la ruta, devolvemos el tren asignado a esta ruta
                return route.getTrainModel();
            }
        }
        // Si no se encuentra ninguna ruta con el nombre dado, devolvemos null
        return null;
    }
    
    public void editTrainForRoute(String routeName, String newTrainIdentifier) {
        // Iterar sobre la lista de rutas para encontrar la ruta con el nombre dado
        for (int i = 0; i < routes.size(); i++) {
            RouteModel route = routes.get(i);
            if (route.getRouteName().equals(routeName)) {
                // Encontramos la ruta, ahora necesitamos encontrar el nuevo tren en la lista de trenes
                TrainModel newTrain = findTrainByIdentifier(newTrainIdentifier);
                if (newTrain != null) {
                    // Asignamos el nuevo tren a la ruta
                    route.setTrainModel(newTrain);
                    // Guardamos los cambios en el archivo JSON
                    saveRoutesToJson(routesFilePath);
                    System.out.println("El tren asignado a la ruta " + routeName + " ha sido actualizado correctamente.");
                    return;
                } else {
                    System.out.println("Error: No se encontró ningún tren con el identificador proporcionado.");
                    return;
                }
            }
        }
        // Si no se encuentra ninguna ruta con el nombre dado
        System.out.println("Error: No se encontró ninguna ruta con el nombre proporcionado.");
    }
    
    
    private static void printRouteData(String routeName, String points, int distance) {
        System.out.println("Datos de la ruta asignada:");
        System.out.println("Nombre de la ruta: " + routeName);
        System.out.println("Puntos por donde pasa la ruta: " + points);
        System.out.println("Distancia total de la ruta: " + distance + " km");
    }
}