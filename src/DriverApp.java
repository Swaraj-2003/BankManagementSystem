import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DriverApp {
    public static  void displayCreateAccountMessage(){
        System.out.println("Sorry ! you dont have any account please create your acount first");
    }
    public static void registerCustomer(Bank b, String name, String password, long mobileNumber, String userName) {
        Customer c = new Customer(name, password, mobileNumber, userName);
        b.registerCustomer(c);
        System.out.println(" You have Registered successfully, Please Log In now");
    }

    public static Customer login(Bank b, String userName, String password) {
        Customer cust = b.login(userName, password);
        if (cust != null) {
            System.out.println("Welcome back " + cust.getName());
            return cust;
        }

        return null;
    }

    public static void createSavingsAccount(Customer cust, Bank b) {
        SavingsAccount sa = new SavingsAccount(cust.getName(), cust.getMobileNumber());
        b.createSavingsAccount(sa);
        BankAccount ac = b.findMyAccount(cust);
        if (ac != null) {
            ac.displayAccountDetails();
        }
        else{
            displayCreateAccountMessage();
        }
    }

    public static void createCurrentAccount(Customer cust, Bank b) {
        CurrentAccount ca = new CurrentAccount(cust.getName(), cust.getMobileNumber());
        b.createCurrentAccount(ca);
        BankAccount ac1 = b.findMyAccount(cust);
        if (ac1 != null) {
            ac1.displayAccountDetails();
        }else{
            displayCreateAccountMessage();
        }
    }

    public static void viewMyAccount(Customer cust,Bank b) {
       BankAccount ac= b.findMyAccount(cust);
       if(ac!=null){
           ac.displayAccountDetails();
       }else {
           displayCreateAccountMessage();
       }
    }

    public static void depositMoney(Customer cust, long amount,Bank b) {
        b.addMoney(cust,amount);
    }
    public static void getBalance(Customer cust,Bank b) {
        long balance= b.checkBalance(cust);
        if(balance!=-1) {
            System.out.println("Your BankBalance is " + balance);
        }
    }
    public static void withdrawMoney(Customer cust, Bank b,long money) {
        b.withdrawMoney(cust,money);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String name;
        long mobileNumber;
        String userName;
        String password;
        long money;
        Bank b = new Bank();
        boolean istrue = true;
        while (istrue) {
            System.out.println("Welcome to HDFC bank 🙏🙏🙏");
            System.out.println("Please Enter a choice");
            System.out.println("1. Register\n" +
                    "2. Login\n" +
                    "3. Exit\n"
            );
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Please Enter your details correctly");
                    System.out.println("Enter your name");
                    name = sc.next();
                    System.out.println("Enter your Mobile number");
                    mobileNumber = sc.nextLong();
                    System.out.println("Enter your Username");
                    userName = sc.next();
                    System.out.println("Enter your Password");
                    password = sc.next();
                    registerCustomer(b, name, password, mobileNumber, userName);
                    break;
                case 2:
                    System.out.println("Welcome 🙋");
                    System.out.println("Enter your Username");
                    userName = sc.next();
                    System.out.println("Enter your Password");
                    password = sc.next();
                    Customer cust = login(b, userName, password);
                    if (cust == null) {
                        System.out.println("Sorry please create your account first");
                        break;
                    }
                    istrue = true;
                    while (istrue) {
                        System.out.println("Please enter your choice");
                        System.out.println("1. Create Savings Account\n" +
                                "2. Create Current Account\n" +
                                "3. View My Account\n" +
                                "4. Deposit Money\n" +
                                "5. Withdraw Money\n" +
                                "6. Check Balance\n" +
                                "7. Logout");
                        choice = Integer.parseInt(sc.next());
                        switch (choice) {
                            case 1:
                                createSavingsAccount(cust, b);
                                break;
                            case 2:
                                createCurrentAccount(cust, b);
                                break;
                            case 3:
                                viewMyAccount(cust,b);

                                break;
                            case 4:
                                System.out.println("Enter a amount to deposit");
                                money=sc.nextLong();
                                depositMoney(cust,money,b);
                                break;
                            case 5:
                                System.out.println("Enter amount to withdraw");
                                money=sc.nextLong();
                                withdrawMoney(cust,b,money);
                                break;
                            case 6:
                                getBalance(cust,b);
                                break;
                            case 7:
                                istrue = false;
                                break;
                        }
                    }
                    istrue = false;
                    break;
                case 3:
                    istrue = false;
                    break;
                default:
                    System.out.println("please enter correct number");
            }
        }
    }




}

