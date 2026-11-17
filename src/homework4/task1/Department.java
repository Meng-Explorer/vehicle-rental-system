package homework4.task1;

import java.util.Scanner;

public class Department {
    private String departmentName;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public void runEmployeeManagement() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- " + departmentName + " Management System ---");

        // 1. Validate employee type (1 for Hourly, 2 for Salaried) without try/catch
        int type = 0;
        while (true) {
            System.out.print("Choose Employee Type (1 for Hourly, 2 for Salaried): ");
            if (scanner.hasNextInt()) {
                type = scanner.nextInt();
                if (type == 1 || type == 2) {
                    break;
                } else {
                    System.out.println("Invalid selection. Please enter 1 or 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // consume invalid token
            }
        }

        scanner.nextLine(); // Clear newline buffer after reading integer

        // 2. Read ID and Name
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        // 3. Polymorphic reference
        Employee emp = null;

        if (type == 1) {
            double rate = readPositiveDouble(scanner, "Enter Hourly Rate: ");
            double hours = readPositiveDouble(scanner, "Enter Hours Worked: ");
            emp = new HourlyEmployee(id, name, rate, hours);
        } else {
            double salary = readPositiveDouble(scanner, "Enter Monthly Salary: ");
            emp = new SalariesEmployee(id, name, salary);
        }

        // 4. Print results using polymorphism and printf formatting
        System.out.println("\n--- Employee Salary Details ---");
        System.out.println("ID: " + emp.getId());
        System.out.println("Name: " + emp.getName());
        System.out.printf("Net Salary: $%.2f%n", emp.calculateSalary());
    }

    // Helper method to read non-negative doubles without try/catch
    private double readPositiveDouble(Scanner scanner, String prompt) {
        double val = -1;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                val = scanner.nextDouble();
                if (val >= 0) {
                    break;
                } else {
                    System.out.println("Value cannot be negative. Try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a numerical value.");
                scanner.next();
            }
        }
        return val;
    }
}
