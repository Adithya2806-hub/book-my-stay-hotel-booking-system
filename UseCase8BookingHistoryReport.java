import java.util.*;

// Reservation Class
class Reservation {
    private String customerName;
    private String roomType;
    private int nights;

    // Constructor
    public Reservation(String customerName, String roomType, int nights) {
        this.customerName = customerName;
        this.roomType = roomType;
        this.nights = nights;
    }

    // Getters
    public String getCustomerName() {
        return customerName;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNights() {
        return nights;
    }

    // Display
    public void display() {
        System.out.println(customerName + " → " + roomType + " (" + nights + " nights)");
    }
}

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("=== Book My Stay App - UC8 ===");
        System.out.println("====================================");

        // Booking History (List maintains order)
        List<Reservation> bookingHistory = new ArrayList<>();

        // Step 1: Add confirmed bookings
        bookingHistory.add(new Reservation("Adithya", "Deluxe", 2));
        bookingHistory.add(new Reservation("Rahul", "Suite", 3));
        bookingHistory.add(new Reservation("Priya", "Standard", 1));

        // Step 2: Display Booking History
        System.out.println("\nBooking History:");
        for (Reservation r : bookingHistory) {
            r.display();
        }

        // Step 3: Generate Report
        System.out.println("\nBooking Summary Report:");

        int totalBookings = bookingHistory.size();
        int totalNights = 0;

        for (Reservation r : bookingHistory) {
            totalNights += r.getNights();
        }

        System.out.println("Total Bookings: " + totalBookings);
        System.out.println("Total Nights Booked: " + totalNights);

        System.out.println("\nUC8 Completed Successfully!");
    }
}