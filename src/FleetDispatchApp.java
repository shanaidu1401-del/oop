import java.util.Scanner;

/** Console demonstration for one individual student's Bus object design. */
public class FleetDispatchApp {
    /** Runs a menu until the user chooses to exit or input ends. */
    public static void main(String[] args) {
        // Both constructor forms are demonstrated using separate objects.
        Bus spareBus = new Bus();
        spareBus.setRegistrationNumber("PTEST 02");
        spareBus.setDriverName("Kumar");
        spareBus.setRouteName("Standby");
        Bus bus = new Bus("PTEST 01", "Ravi", "Tasek Gelugor to Factory A",
                          40, 20, 25.0, true);
        System.out.println("FLEET DISPATCH DEMONSTRATION");
        System.out.println("Spare bus: " + spareBus.getRegistrationNumber());
        try (Scanner input = new Scanner(System.in)) {
            boolean running = true;
            // The loop keeps the same bus object available across menu actions.
            while (running) {
                System.out.println("\n1 View bus | 2 Board passengers | 3 Set distance | 4 Dispatch | 0 Exit");
                if (!input.hasNextLine()) break;
                try {
                    int choice = Integer.parseInt(input.nextLine().trim());
                    // Each branch interacts with the bus through its public methods.
                    switch (choice) {
                        case 1:
                            showBus(bus);
                            break;
                        case 2:
                            System.out.print("Passengers to board: ");
                            int count = Integer.parseInt(input.nextLine().trim());
                            if (bus.boardPassengers(count))
                                System.out.println("Boarding successful.");
                            else
                                System.out.println("Boarding rejected. Check availability, count and seats.");
                            break;
                        case 3:
                            System.out.print("Trip distance in km: ");
                            bus.setDistanceKm(Double.parseDouble(input.nextLine().trim()));
                            System.out.printf("Distance updated to %.1f km.%n", bus.getDistanceKm());
                            break;
                        case 4:
                            if (bus.dispatch())
                                System.out.println("Bus dispatched. Availability is now false.");
                            else
                                System.out.println("Dispatch rejected. Check availability, passengers and distance.");
                            break;
                        case 0:
                            running = false;
                            System.out.println("Program ended.");
                            break;
                        default:
                            System.out.println("Choose 0 to 4.");
                    }
                } catch (IllegalArgumentException ex) {
                    // NumberFormatException is also an IllegalArgumentException.
                    System.out.println("Invalid input: " + ex.getMessage());
                }
            }
        }
    }

    /** Displays current state using getters, including a calculated result. */
    private static void showBus(Bus bus) {
        System.out.println("Registration: " + bus.getRegistrationNumber());
        System.out.println("Driver: " + bus.getDriverName());
        System.out.println("Route: " + bus.getRouteName());
        System.out.println("Passengers: " + bus.getPassengerCount() + "/" + bus.getSeatingCapacity());
        System.out.printf("Distance: %.1f km%n", bus.getDistanceKm());
        System.out.printf("Occupancy: %.1f%%%n", bus.calculateOccupancyPercentage());
        System.out.println("Available: " + bus.isAvailable());
    }
}
