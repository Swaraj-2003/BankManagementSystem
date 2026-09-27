public class CurrentAccount extends BankAccount {
    private double overDraftLimit=5000;

    public CurrentAccount() {
        super();
    }

    public CurrentAccount(String accountHolderName, long mobileNumber) {
        super(accountHolderName, mobileNumber);
    }

    public void setOverDraftLimit(double overDraftLimit) {
        this.overDraftLimit = overDraftLimit;
    }

    public double getOverDraftLimit() {
        return overDraftLimit;
    }
    @Override
    public void withdraw(long money) {
        long bankBalance = getBankBalance();
        if(money<=0){
            System.out.println("please enter a valid amount");
            return;
        }
        double limit=bankBalance+overDraftLimit;
        if(limit==0){
            System.out.println("Dear customer you have used your OverDraftLimit");
            return;
        }
        if(money<=limit){
            updateBalance(-money);
            System.out.println("Withdraw Successfully");
        }
        else{
            System.out.println("Insufficient Bank Balance");
        }
    }

    public void displayAccountDetails(){
        System.out.println(super.getAccountHolderName());
        System.out.println(super.getAccountNumber());
        System.out.println(IFC_CODE);
        System.out.println(super.getBankBalance());
        System.out.println(overDraftLimit);
    }
}
