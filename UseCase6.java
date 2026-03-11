import java.util.*;
public class UseCase6{

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay - Room Allocation ");
        System.out.println(" Hotel Booking System v6.0 ");
        System.out.println("=================================");

        // Inventory
        HashMap<String, Integer> inventory = new HashMap<>();
        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);

        // Booking Queue
        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Adithya", "Single"));
        bookingQueue.add(new Reservation("Rahul", "Double"));
        bookingQueue.add(new Reservation("Priya", "Suite"));

        // Track allocated rooms
        HashMap<String, Set<String>> allocatedRooms = new HashMap<>();

        allocatedRooms.put("Single", new HashSet<>());
        allocatedRooms.put("Double", new HashSet<>());
        allocatedRooms.put("Suite", new HashSet<>());

        int roomCounter = 1;

        while(!bookingQueue.isEmpty()) {

            Reservation r = bookingQueue.poll();

            int available = inventory.get(r.roomType);

            if(available > 0) {

                String roomId = r.roomType + "-" + roomCounter++;

                allocatedRooms.get(r.roomType).add(roomId);

                inventory.put(r.roomType, available - 1);

                System.out.println("Reservation Confirmed:");
                System.out.println("Guest: " + r.guestName);
                System.out.println("Room Type: " + r.roomType);
                System.out.println("Room ID: " + roomId);
                System.out.println("----------------------");

            } else {

                System.out.println("No rooms available for " + r.roomType);
            }
        }

        System.out.println("\nFinal Allocation Summary:");

        for(String type : allocatedRooms.keySet()) {

            System.out.println(type + " Rooms: " + allocatedRooms.get(type));
        }
    }
}