public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay - Inventory System ");
        System.out.println(" Hotel Booking System v3.0 ");
        System.out.println("=================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display current inventory
        inventory.displayInventory();

        // Example update
        System.out.println("\nUpdating Double Room availability...");

        inventory.updateAvailability("Double Room", 2);

        // Display updated inventory
        inventory.displayInventory();

        System.out.println("\nInventory Setup Completed!");
    }
}