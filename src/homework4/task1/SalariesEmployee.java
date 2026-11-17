package homework4.task1;

public class SalariesEmployee extends Employee{
    private double monthlySalary;

    // Default Constructure
    public SalariesEmployee(){
        monthlySalary = 0;
    }

    public SalariesEmployee(String id, String name, double monthlySalary) {
        super(id, name);
        this.monthlySalary = monthlySalary;
    }

    // Salary = fixed monthlySalary
    @Override
    public double calculateSalary() {
        return monthlySalary;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }
}
