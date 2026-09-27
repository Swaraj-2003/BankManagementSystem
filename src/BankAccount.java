public class BankAccount {
    public static final String IFC_CODE = "HDFC0001607";
    private String accountHolderName;
    private long accountNumber;
    private long mobileNumber;
    private long bankBalance;

    public BankAccount() {
    }

    public BankAccount(String accountHolderName, long mobileNumber) {
        this.accountHolderName = accountHolderName;
        this.mobileNumber = mobileNumber;
    }

    protected  void updateBalance(long amount){
        bankBalance+=amount;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String name) {
        this.accountHolderName = name;
    }

    public void setAccountNumber(long accountNumber){
        this.accountNumber=accountNumber;
    }

    public long getAccountNumber() {
       return accountNumber;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public long getBankBalance() {
        return bankBalance;
    }


    public void deposit(long money){
        if(money<=0){
            System.out.println("please enter valid amount");
            return;
        }
        bankBalance+=money;
        System.out.println("Money deposited Successfuly");
    }

    public void withdraw(long money){
        if(money<=0){
            System.out.println("please enter a valid amount");
            return;
        }
        if(bankBalance<money){
            System.out.println("Insufficient bank balance");
        }
        else{
            bankBalance-=money;
            System.out.println("Money withdraw Successfuly");
        }
    }

    public long checkBankBalance(){
        return bankBalance;
    }

    public void displayAccountDetails(){
        System.out.println("==============================");
        System.out.println("Name           : "+accountHolderName);
        System.out.println("Account Number : "+accountNumber);
        System.out.println("IFC_CODE       : "+IFC_CODE);
        System.out.println("Balance        : "+bankBalance);
        System.out.println("==============================");
    }
}
