package vehicle;

public class Bus extends Vehicle {
    private final int passengerCapacity;

    public Bus(String vehicleId, String brand, String model, double baseRentalRate, int passengerCapacity) {
        super(vehicleId, brand, model, baseRentalRate);
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public double calculateRent(int days) {
        // Bus service surcharge of $50 flat commercial fee
        double total = (getBaseRentalRate() * days) + 50.0;
        // 5% discount for rentals >= 10 days
        if (days >= 10) {
            total *= 0.95;
        }
        return total;
    }

    @Override
    public String toString() {
        return super.toString() + " | Capacity: " + passengerCapacity + " passengers";
    }
}
