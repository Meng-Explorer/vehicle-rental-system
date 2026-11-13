package vehicle;

import vehicle.exceptions.InvalidRentalException;
import vehicle.exceptions.VehicleNotAvailableException;

import java.util.*;

public class RentalSystem {

    private final Map<String, Vehicle> fleet = new LinkedHashMap<>();
    private final List<RentalRecord> rentalHistory = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        if (fleet.containsKey(vehicle.getVehicleId())) {
            throw new IllegalArgumentException("Vehicle ID already exists: " + vehicle.getVehicleId());
        }
        fleet.put(vehicle.getVehicleId(), vehicle);
    }

    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> available = new ArrayList<>();
        for (Vehicle v : fleet.values()) {
            if (!v.isRented()) {
                available.add(v);
            }
        }
        return available;
    }

    public double rentVehicle(String vehicleId, int days) throws VehicleNotAvailableException, InvalidRentalException {
        if (days <= 0) {
            throw new InvalidRentalException("Rental duration must be at least 1 day.");
        }
        Vehicle vehicle = fleet.get(vehicleId);
        if (vehicle == null) {
            throw new VehicleNotAvailableException("Vehicle ID " + vehicleId + " does not exist.");
        }
        if (vehicle.isRented()) {
            throw new VehicleNotAvailableException("Vehicle " + vehicleId + " is currently rented.");
        }

        vehicle.rentVehicle();
        double cost = vehicle.calculateRent(days);
        rentalHistory.add(new RentalRecord("REC-" + (rentalHistory.size() + 1001), vehicleId, days, cost));
        return cost;
    }

    public void returnVehicle(String vehicleId) throws VehicleNotAvailableException {
        Vehicle vehicle = fleet.get(vehicleId);
        if (vehicle == null) {
            throw new VehicleNotAvailableException("Vehicle ID " + vehicleId + " does not exist.");
        }
        if (!vehicle.isRented()) {
            throw new IllegalStateException("Vehicle " + vehicleId + " is not currently rented.");
        }
        vehicle.returnVehicle();
    }

    public List<RentalRecord> getRentalHistory() {
        return Collections.unmodifiableList(rentalHistory);
    }

}
