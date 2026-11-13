package vehicle;

public class Car extends Vehicle {
    private final int numberOfSeats;

    public Car(String vehicleId, String brand, String model, double baseRentalRate, int numberOfSeats) {
        super(vehicleId, brand, model, baseRentalRate);
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public double calculateRent(int days) {
        // Cars charge base rate plus an insurance/seat surcharge of $5 per seat/day
        double ratePerDay = getBaseRentalRate() + (numberOfSeats * 5.0);
        double total = ratePerDay * days;
        // 10% discount for bookings >= 7 days
        if (days >= 7) {
            total *= 0.90;
        }
        return total;
    }

    @Override
    public String toString() {
        return super.toString() + " | Seats: " + numberOfSeats;
    }
}
