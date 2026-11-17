package homework4.task2;

import java.util.Scanner;

public class Bank {
    private String bankName;

    public Bank(String bankName) {
        this.bankName = bankName;
    }

    public void runAccountManagement() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Welcome to " + bankName + " ---");

        // 1. Account type validation
        int type = 0;
        while (true) {
            System.out.print("Choose Account Type (1 for Savings, 2 for Checking): ");
            if (scanner.hasNextInt()) {
                type = scanner.nextInt();
                if (type == 1 || type == 2) {
                    break;
                } else {
                    System.out.println("Invalid selection. Please enter 1 or 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
            }
        }

        scanner.nextLine(); // Clear newline buffer

        // 2. Account number and holder name
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();

        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();

        // 3. Initial balance
        double initialBal = readPositiveDouble(scanner, "Enter Initial Balance: ");

        // 4. Polymorphic reference
        Account account = null;

        if (type == 1) {
            double rate = readPositiveDouble(scanner, "Enter Interest Rate (e.g., 0.05 for 5%): ");
            account = new SavingAccount(accNum, name, initialBal, rate);
        } else {
            double fee = readPositiveDouble(scanner, "Enter Transaction Fee: ");
            account = new CheckingAccount(accNum, name, initialBal, fee);
        }

        // 5. Process and update balance
        account.processAccount();

        // 6. Display output
        System.out.println("\n--- Updated Account Details ---");
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Holder Name: " + account.getAccountHolder());
        System.out.printf("Final Balance: $%.2f%n", account.getBalance());
    }

    // Non-negative input validator
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
                System.out.println("Invalid numerical input. Try again.");
                scanner.next();
            }
        }
        return val;
    }
}
