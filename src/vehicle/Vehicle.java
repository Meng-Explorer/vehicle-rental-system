package vehicle;

import vehicle.exceptions.VehicleNotAvailableException;

// Base abstract class representing a general vehicle.
// Demonstrates Abstraction and Encapsulation.
public abstract class Vehicle {
    private final String vehicleId;
    private final String brand;
    private final String model;
    private final double baseRentalRate;
    private boolean isRented;

    public Vehicle(){
        this.vehicleId = "Unknown";
        this.brand = "Unknown";
        this.model = "Unknown";
        this.baseRentalRate = 0.00;
        this.isRented = false;
    }

    public Vehicle(String vehicleId, String brand, String model, double baseRentalRate) {
        if (baseRentalRate <= 0) {
            throw new IllegalArgumentException("Base rental rate must be positive.");
        }
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.baseRentalRate = baseRentalRate;
        this.isRented = false;
    }
    public String getVehicleId() {
        return vehicleId;
    }
    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public double getBaseRentalRate() {
        return baseRentalRate;
    }
    public boolean isRented() {
        return isRented;
    }

    public void rentVehicle() throws VehicleNotAvailableException {
        if (this.isRented) {
            throw new VehicleNotAvailableException("Vehicle " + vehicleId + " is already rented.");
        }
        this.isRented = true;
    }

    public void returnVehicle() {
        this.isRented = false;
    }
//      Polymorphic method to calculate rental cost.
//      Concrete implementations apply vehicle-specific fee adjustments.
    public abstract double calculateRent(int days);

    @Override
    public String toString() {
        return String.format("[%s] ID: %s | %s %s | Base Rate: $%.2f/day | Status: %s",
                this.getClass().getSimpleName(), vehicleId, brand, model, baseRentalRate,
                (isRented ? "RENTED" : "AVAILABLE"));
    }
}
