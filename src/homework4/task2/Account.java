package homework4.task2;

public abstract class Account {
    private String accountNumber;
    private String accountHolder;
    // Protected allows direct access to subclasses SavingsAccount and CheckingAccount
    protected double balance;

    public Account(String accNum, String name, double bal) {
        this.accountNumber = accNum;
        this.accountHolder = name;
        this.balance = bal;
    }

    // Abstract method to be overridden in subclasses
    public abstract void processAccount();

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }
}
