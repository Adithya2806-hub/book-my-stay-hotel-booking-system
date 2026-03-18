import java.util.*;

// Service class (Add-on)
class Service {
    private String name;
    private int cost;

    public Service(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}

// Add-On Service Manager
class AddOnServiceManager {

    private Map<String, List<Service>> serviceMap;

    public AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    // Add service to reservation
    public void addService(String reservationId, Service service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    // Calculate total cost
    public int calculateTotalCost(String reservationId) {
        int total = 0;
        List<Service> services = serviceMap.get(reservationId);

        if (services != null) {
            for (Service s : services) {
                total += s.getCost();
            }
        }
        return total;
    }

    // Display services
    public void displayServices(String reservationId) {
        List<Service> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        System.out.println("Selected Services:");
        for (Service s : services) {
            System.out.println("- " + s.getName() + " (Rs." + s.getCost() + ")");
        }
    }
}

// Main Class
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Add-On Service Selection ");
        System.out.println("=================================");

        String reservationId = "RES123";

        AddOnServiceManager manager = new AddOnServiceManager();

        // Add services
        manager.addService(reservationId, new Service("Breakfast", 500));
        manager.addService(reservationId, new Service("Airport Pickup", 1000));
        manager.addService(reservationId, new Service("Extra Bed", 700));

        // Display
        manager.displayServices(reservationId);

        int total = manager.calculateTotalCost(reservationId);

        System.out.println("Total Add-On Cost: Rs." + total);

        System.out.println("\nUC7 Completed Successfully!");
    }
}