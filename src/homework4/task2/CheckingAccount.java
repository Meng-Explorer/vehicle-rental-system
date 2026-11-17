package homework4.task2;

public class CheckingAccount extends Account {
    private double transactionFee;

    public CheckingAccount(String accNum, String name, double bal, double transactionFee) {
        super(accNum, name, bal);
        this.transactionFee = transactionFee;
    }

    // Balance update: final balance = balance - transactionFee
    @Override
    public void processAccount() {
        balance -= transactionFee;
    }

    public double getTransactionFee() {
        return transactionFee;
    }
}
