import java.util.*;

// Booking Request
class BookingRequest {
    String customerName;
    String roomType;

    public BookingRequest(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }
}

// Shared Booking System
class BookingSystem {

    // Shared inventory
    private Map<String, Integer> inventory = new HashMap<>();

    public BookingSystem() {
        inventory.put("Standard", 1);
        inventory.put("Deluxe", 1);
    }

    // Synchronized booking method (critical section)
    public synchronized void bookRoom(BookingRequest request) {

        System.out.println(Thread.currentThread().getName() +
                " trying to book " + request.roomType + " for " + request.customerName);

        int available = inventory.getOrDefault(request.roomType, 0);

        if (available > 0) {
            inventory.put(request.roomType, available - 1);

            System.out.println("✅ Booking Confirmed for " +
                    request.customerName + " (" + request.roomType + ")");
        } else {
            System.out.println("❌ No rooms available for " +
                    request.customerName + " (" + request.roomType + ")");
        }
    }

    public void displayInventory() {
        System.out.println("\nFinal Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " → " + inventory.get(type));
        }
    }
}

// Thread class
class BookingThread extends Thread {

    private BookingSystem system;
    private BookingRequest request;

    public BookingThread(BookingSystem system, BookingRequest request) {
        this.system = system;
        this.request = request;
    }

    @Override
    public void run() {
        system.bookRoom(request);
    }
}

public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("=== Book My Stay App - UC11 ===");
        System.out.println("====================================");

        BookingSystem system = new BookingSystem();

        // Simulate multiple users (threads)
        BookingThread t1 = new BookingThread(system, new BookingRequest("Adithya", "Standard"));
        BookingThread t2 = new BookingThread(system, new BookingRequest("Rahul", "Standard"));
        BookingThread t3 = new BookingThread(system, new BookingRequest("Priya", "Deluxe"));

        // Start threads (concurrent execution)
        t1.start();
        t2.start();
        t3.start();

        // Wait for all threads to finish
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display final inventory
        system.displayInventory();

        System.out.println("\nUC11 Completed Successfully!");
    }
}