package homework4.task1;

public class HourlyEmployee extends Employee{
    private double hourlyRate;
    private double hoursWorked;

    // Default Constructor
    public HourlyEmployee(){
        hourlyRate = 0;
        hoursWorked = 0;
    }

    public HourlyEmployee(String id, String name, double hourlyRate, double hoursWorked) {
        super(id, name);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    // Salary = hourlyRate * hoursWorked
    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }
}
