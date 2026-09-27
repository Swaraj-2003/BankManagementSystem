public class SavingsAccount extends BankAccount {
    private static double interestRate = 4.0;

    public SavingsAccount() {
        super();
    }

    public SavingsAccount(String accountHolderName, long mobileNumber) {
        super(accountHolderName, mobileNumber);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double calculateInterest() {
        long bankBalance = getBankBalance();
        return (bankBalance * interestRate) / 100;
    }
}
