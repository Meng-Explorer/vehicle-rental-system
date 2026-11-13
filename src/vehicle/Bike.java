package vehicle;

public class Bike extends Vehicle {
    private final String bikeType;

    public Bike(String vehicleId, String brand, String model, double baseRentalRate, String bikeType) {
        super(vehicleId, brand, model, baseRentalRate);
        this.bikeType = bikeType;
    }

    @Override
    public double calculateRent(int days) {
        // Flat rate without complex surcharges, 15% discount for bookings >= 5 days
        double total = getBaseRentalRate() * days;
        if (days >= 5) {
            total *= 0.85;
        }
        return total;
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: " + bikeType;
    }
}
