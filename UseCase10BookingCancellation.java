import java.util.*;

// Reservation Class
class Reservation {
    String customerName;
    String roomType;
    String roomId;

    public Reservation(String customerName, String roomType, String roomId) {
        this.customerName = customerName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public void display() {
        System.out.println(customerName + " → " + roomType + " (Room: " + roomId + ")");
    }
}

public class UseCase10BookingCancellation {

    // Inventory (Room Type → Count)
    static Map<String, Integer> inventory = new HashMap<>();

    // Booking History
    static List<Reservation> bookingHistory = new ArrayList<>();

    // Stack for rollback (LIFO)
    static Stack<String> rollbackStack = new Stack<>();

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("=== Book My Stay App - UC10 ===");
        System.out.println("====================================");

        // Initialize inventory
        inventory.put("Standard", 2);
        inventory.put("Deluxe", 2);

        // Confirm bookings
        bookRoom("Adithya", "Standard", "R101");
        bookRoom("Rahul", "Deluxe", "D201");

        // Cancel booking
        cancelBooking("Adithya");

        // Display inventory after rollback
        System.out.println("\nUpdated Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " → " + inventory.get(type));
        }

        System.out.println("\nUC10 Completed Successfully!");
    }

    // Booking method
    public static void bookRoom(String name, String roomType, String roomId) {

        if (inventory.get(roomType) > 0) {
            inventory.put(roomType, inventory.get(roomType) - 1);

            Reservation r = new Reservation(name, roomType, roomId);
            bookingHistory.add(r);

            System.out.println("\n✅ Booking Confirmed:");
            r.display();
        } else {
            System.out.println("\n❌ No rooms available for " + roomType);
        }
    }

    // Cancellation method
    public static void cancelBooking(String name) {

        boolean found = false;

        for (Reservation r : bookingHistory) {
            if (r.customerName.equals(name)) {

                // Push roomId to rollback stack
                rollbackStack.push(r.roomId);

                // Restore inventory
                inventory.put(r.roomType, inventory.get(r.roomType) + 1);

                System.out.println("\n🔁 Booking Cancelled:");
                r.display();

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("\n❌ Booking not found for " + name);
        }
    }
}