import java.io.*;
import java.util.*;

// Reservation Class (Serializable)
class Reservation implements Serializable {
    String customerName;
    String roomType;

    public Reservation(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println(customerName + " → " + roomType);
    }
}

// Wrapper class to store full state
class SystemState implements Serializable {
    Map<String, Integer> inventory;
    List<Reservation> bookings;

    public SystemState(Map<String, Integer> inventory, List<Reservation> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

public class UseCase12DataPersistenceRecovery {

    static final String FILE_NAME = "system_state.ser";

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("=== Book My Stay App - UC12 ===");
        System.out.println("====================================");

        // Try to load previous state
        SystemState state = loadState();

        Map<String, Integer> inventory;
        List<Reservation> bookings;

        if (state != null) {
            inventory = state.inventory;
            bookings = state.bookings;

            System.out.println("\n🔁 System Recovered from File!");
        } else {
            // Initialize fresh state
            inventory = new HashMap<>();
            bookings = new ArrayList<>();

            inventory.put("Standard", 2);
            inventory.put("Deluxe", 1);

            System.out.println("\n🆕 Fresh System Started!");
        }

        // Add booking
        if (inventory.get("Standard") > 0) {
            inventory.put("Standard", inventory.get("Standard") - 1);
            bookings.add(new Reservation("Adithya", "Standard"));
        }

        // Display current state
        System.out.println("\nCurrent Bookings:");
        for (Reservation r : bookings) {
            r.display();
        }

        System.out.println("\nInventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + " → " + inventory.get(key));
        }

        // Save state before exit
        saveState(new SystemState(inventory, bookings));

        System.out.println("\n💾 State Saved Successfully!");
        System.out.println("\nUC12 Completed Successfully!");
    }

    // Save to file
    public static void saveState(SystemState state) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(state);
        } catch (IOException e) {
            System.out.println("❌ Error saving state: " + e.getMessage());
        }
    }

    // Load from file
    public static SystemState loadState() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (SystemState) ois.readObject();
        } catch (Exception e) {
            return null; // handle missing/corrupt file
        }
    }
}