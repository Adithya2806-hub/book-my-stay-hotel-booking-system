import java.util.HashMap;

public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay - Room Search ");
        System.out.println(" Hotel Booking System v4.0 ");
        System.out.println("=================================");

        HashMap<String, Integer> inventory = new HashMap<>();

        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);

        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        System.out.println("\nAvailable Rooms:");

        if(inventory.get("Single") > 0){
            System.out.println("\nSingle Room Details");
            singleRoom.displayRoomDetails();
            System.out.println("Available Rooms: " + inventory.get("Single"));
        }

        if(inventory.get("Double") > 0){
            System.out.println("\nDouble Room Details");
            doubleRoom.displayRoomDetails();
            System.out.println("Available Rooms: " + inventory.get("Double"));
        }

        if(inventory.get("Suite") > 0){
            System.out.println("\nSuite Room Details");
            suiteRoom.displayRoomDetails();
            System.out.println("Available Rooms: " + inventory.get("Suite"));
        }

        System.out.println("\nSearch Completed Successfully!");
    }
}