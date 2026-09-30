public class Transaction {
    private int transactionId;
    private long accountNumber;
    private String transactionType;
    private long amount;
    private long balanceAfterTransaction;
    private String dateTime;

    public Transaction(int transactionId, long accountNumber, String transactionType, long amount, long balanceAfterTransaction, String dateTime) {
        this.accountNumber = accountNumber;
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.dateTime = dateTime;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void displayTransactionDetails() {
        System.out.println("===============================================");
        System.out.println("Transaction Id              :" + transactionId);
        System.out.println("Account Number              :" + accountNumber);
        System.out.println("Transaction type            :" + transactionType);
        System.out.println("Amount                      :" + amount);
        System.out.println("Balance After Transaction   :" + balanceAfterTransaction);
        System.out.println("Date and Time               :" + dateTime);
        System.out.println("===============================================");
    }

}
