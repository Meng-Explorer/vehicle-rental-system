package vehicle;

import java.time.LocalDate;

public class RentalRecord {
    private final String recordId;
    private final String vehicleId;
    private final int days;
    private final double totalCost;
    private final LocalDate timestamp;

    public RentalRecord(String recordId, String vehicleId, int days, double totalCost) {
        this.recordId = recordId;
        this.vehicleId = vehicleId;
        this.days = days;
        this.totalCost = totalCost;
        this.timestamp = LocalDate.now();
    }

    @Override
    public String toString() {
        return String.format("Receipt [%s] Date: %s | Vehicle: %s | Days: %d | Total: $%.2f",
                recordId, timestamp, vehicleId, days, totalCost);
    }
}
