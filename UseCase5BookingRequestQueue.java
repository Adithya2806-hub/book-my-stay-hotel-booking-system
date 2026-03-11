import java.util.LinkedList;
import java.util.Queue;

class Reservation {

    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void displayReservation() {
        System.out.println("Guest: " + guestName + " | Room Type: " + roomType);
    }
}

public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay - Booking Requests ");
        System.out.println(" Hotel Booking System v5.0 ");
        System.out.println("=================================");

        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Guest booking requests
        bookingQueue.add(new Reservation("Adithya", "Single"));
        bookingQueue.add(new Reservation("Rahul", "Double"));
        bookingQueue.add(new Reservation("Priya", "Suite"));

        System.out.println("\nBooking Requests in Queue:");

        for (Reservation r : bookingQueue) {
            r.displayReservation();
        }

        System.out.println("\nAll requests stored in FIFO order.");
    }
}