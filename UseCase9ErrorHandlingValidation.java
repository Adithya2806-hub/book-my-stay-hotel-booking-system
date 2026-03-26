import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Reservation Class
class Reservation {
    private String customerName;
    private String roomType;
    private int nights;

    public Reservation(String customerName, String roomType, int nights) {
        this.customerName = customerName;
        this.roomType = roomType;
        this.nights = nights;
    }

    public void display() {
        System.out.println(customerName + " → " + roomType + " (" + nights + " nights)");
    }
}

public class UseCase9ErrorHandlingValidation {

    // Valid room types
    static List<String> validRooms = Arrays.asList("Standard", "Deluxe", "Suite");

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("=== Book My Stay App - UC9 ===");
        System.out.println("====================================");

        try {
            // Valid booking
            processBooking("Adithya", "Deluxe", 2);

            // Invalid booking (wrong room type)
            processBooking("Rahul", "Luxury", 3);

        } catch (InvalidBookingException e) {
            System.out.println("\n❌ Booking Failed: " + e.getMessage());
        }

        System.out.println("\nSystem continues running safely...");
        System.out.println("\nUC9 Completed Successfully!");
    }

    // Booking validation method
    public static void processBooking(String name, String roomType, int nights)
            throws InvalidBookingException {

        // Validate room type
        if (!validRooms.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        // Validate nights
        if (nights <= 0) {
            throw new InvalidBookingException("Nights must be greater than 0");
        }

        // If valid → confirm booking
        Reservation r = new Reservation(name, roomType, nights);

        System.out.println("\n✅ Booking Confirmed:");
        r.display();
    }
}