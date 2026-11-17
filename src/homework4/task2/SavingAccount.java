package homework4.task2;

public class SavingAccount extends Account{
    private double interestRate;

    public SavingAccount(String accNum, String name, double bal, double interestRate) {
        super(accNum, name, bal);
        this.interestRate = interestRate;
    }

    // Balance update: final balance = balance + (balance * interestRate)
    @Override
    public void processAccount() {
        balance += balance * interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
