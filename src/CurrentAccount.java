public class CurrentAccount extends BankAccount {
    private double overDraftLimit=5000;

    public CurrentAccount() {
        super();
    }

    public CurrentAccount(String accountHolderName, long mobileNumber) {
        super(accountHolderName, mobileNumber);
    }

    public CurrentAccount(String accountHolderName, long accountNumber, long mobileNumber, long bankBalance) {
        super(accountHolderName, mobileNumber,accountNumber,bankBalance);
    }

    public void setOverDraftLimit(double overDraftLimit) {
        this.overDraftLimit = overDraftLimit;
    }

    public double getOverDraftLimit() {
        return overDraftLimit;
    }
    @Override
    public boolean withdraw(long money) {
        long bankBalance = getBankBalance();
        if(money<=0){
            System.out.println("please enter a valid amount");
            return false;
        }
        double limit=bankBalance+overDraftLimit;
        if(limit==0){
            System.out.println("Dear customer you have used your OverDraftLimit");
            return false;
        }
        if(money<=limit){
            updateBalance(-money);
            System.out.println("Withdraw Successfully");
        }
        else{
            System.out.println("Insufficient Bank Balance");
            return false;
        }
        return true;
    }

    public void displayAccountDetails(){
        System.out.println("==============================");
        System.out.println("Name           : "+super.getAccountHolderName());
        System.out.println("Account Number : "+super.getAccountNumber());
        System.out.println("IFC_CODE       : "+IFC_CODE);
        System.out.println("Balance        : "+super.getBankBalance());
        System.out.println("OverDraftLimit : "+overDraftLimit);
        System.out.println("==============================");
    }
}
