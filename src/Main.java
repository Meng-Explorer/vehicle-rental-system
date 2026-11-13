import vehicle.Bike;
import vehicle.Bus;
import vehicle.Car;
import vehicle.Vehicle;
import vehicle.exceptions.InvalidRentalException;
import vehicle.exceptions.VehicleNotAvailableException;
import vehicle.RentalRecord;
import vehicle.RentalSystem;

private static final Scanner scanner = new Scanner(System.in);
private static final RentalSystem system = new RentalSystem();
void main() {
    seedSampleData();
    boolean running = true;
    while (running) {
        System.out.println("\n===== VEHICLE RENTAL MANAGEMENT SYSTEM =====");
        System.out.println("1. List Available Vehicles");
        System.out.println("2. Rent a Vehicle");
        System.out.println("3. Return a Vehicle");
        System.out.println("4. Add a New Vehicle (Admin)");
        System.out.println("5. View Rental Receipts & History");
        System.out.println("6. Exit");
        System.out.print("Enter choice (1-6): ");

        String input = scanner.nextLine().trim();
        switch (input) {
            case "1" -> handleListAvailable();
            case "2" -> handleRent();
            case "3" -> handleReturn();
            case "4" -> handleAddVehicle();
            case "5" -> handleShowHistory();
            case "6" -> {
                System.out.println("Exiting system safely. Goodbye!");
                running = false;
            }
            default -> System.out.println("Invalid option. Please try again.");
        }
    }
}

private static void seedSampleData() {
    system.addVehicle(new Car("C101", "Toyota", "Camry", 45.0, 5));
    system.addVehicle(new Car("C102", "Tesla", "Model 3", 80.0, 5));
    system.addVehicle(new Bike("B201", "Yamaha", "MT-07", 25.0, "Sport"));
    system.addVehicle(new Bike("B202", "Trek", "FX 3", 15.0, "City Bicycle"));
    system.addVehicle(new Bus("U301", "Mercedes", "Sprinter", 120.0, 16));
}

private static void handleListAvailable() {
    List<Vehicle> list = system.getAvailableVehicles();
    if (list.isEmpty()) {
        System.out.println("No vehicles currently available.");
        return;
    }
    System.out.println("\n--- Available Vehicles ---");
    for (Vehicle v : list) {
        System.out.println(v);
    }
}

private static void handleRent() {
    System.out.print("Enter Vehicle ID to rent: ");
    String id = scanner.nextLine().trim();

    System.out.print("Enter rental duration (days): ");
    try {
        int days = Integer.parseInt(scanner.nextLine().trim());
        double total = system.rentVehicle(id, days);
        System.out.printf("Success! Vehicle %s rented for %d days. Total Cost: $%.2f%n", id, days, total);
    } catch (NumberFormatException e) {
        System.out.println("Error: Days must be an integer.");
    } catch (VehicleNotAvailableException | InvalidRentalException e) {
        System.out.println("Rental Failed: " + e.getMessage());
    }
}

private static void handleReturn() {
    System.out.print("Enter Vehicle ID to return: ");
    String id = scanner.nextLine().trim();
    try {
        system.returnVehicle(id);
        System.out.println("Success! Vehicle " + id + " has been marked as available.");
    } catch (VehicleNotAvailableException | IllegalStateException e) {
        System.out.println("Return Failed: " + e.getMessage());
    }
}

private static void handleAddVehicle() {
    try {
        System.out.print("Vehicle Type (1: Car, 2: Bike, 3: Bus): ");
        int type = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Vehicle ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Brand: ");
        String brand = scanner.nextLine().trim();
        System.out.print("Model: ");
        String model = scanner.nextLine().trim();
        System.out.print("Base Daily Rate ($): ");
        double rate = Double.parseDouble(scanner.nextLine().trim());

        switch (type) {
            case 1 -> {
                System.out.print("Number of Seats: ");
                int seats = Integer.parseInt(scanner.nextLine().trim());
                system.addVehicle(new Car(id, brand, model, rate, seats));
            }
            case 2 -> {
                System.out.print("Bike Category/Type: ");
                String bType = scanner.nextLine().trim();
                system.addVehicle(new Bike(id, brand, model, rate, bType));
            }
            case 3 -> {
                System.out.print("Passenger Capacity: ");
                int cap = Integer.parseInt(scanner.nextLine().trim());
                system.addVehicle(new Bus(id, brand, model, rate, cap));
            }
            default -> {
                System.out.println("Unknown vehicle type.");
                return;
            }
        }
        System.out.println("Vehicle added successfully!");
    } catch (Exception e) {
        System.out.println("Failed to add vehicle: " + e.getMessage());
    }
}

private static void handleShowHistory() {
    List<RentalRecord> history = system.getRentalHistory();
    if (history.isEmpty()) {
        System.out.println("No rental records on file.");
        return;
    }
    System.out.println("\n--- Completed Rental Receipts ---");
    for (RentalRecord r : history) {
        System.out.println(r);
    }
}



