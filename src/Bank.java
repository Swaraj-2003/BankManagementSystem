import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<BankAccount> account;
    private long accountNumber;
    private List<Customer> cust;

    public Bank() {
        account = new ArrayList<BankAccount>();
        cust = new ArrayList<Customer>();
        getCutomersFromFile();
    }

    public void registerCustomer(Customer customer) {
        for (Customer c : cust) {
            {
                if (customer.getUserName().equalsIgnoreCase(c.getUserName())) {
                    System.out.println("the username is already taken please take different name ");
                    return;
                }
            }
        }
        cust.add(customer);
        System.out.println(" You have Registered successfully, Please Log In now");
        saveCustomersToFile(customer);
    }

    public Customer getCustomer(String userName) {
        for (Customer c1 : cust) {
            if (c1.getUserName().equalsIgnoreCase(userName)) {
                return c1;
            }
        }
        return null;
    }

    public Customer login(String userName, String password) {

        for (Customer c : cust) {
            String user = c.getUserName();
            String pass = c.getPassword();
            if (user.equalsIgnoreCase(userName) && pass.equals(password)) {
                System.out.println("Login Successfully");
                return c;
            }
        }
        System.out.println("Invalid Username or Password");
        return null;
    }

    public void createSavingsAccount(SavingsAccount sa) {
        for (BankAccount ba : account) {
            if (ba.getMobileNumber() == sa.getMobileNumber() && ba.getAccountHolderName().equalsIgnoreCase(sa.getAccountHolderName())) {
                if (ba instanceof SavingsAccount) {
                    System.out.println("Account already exists");
                    return;
                }
            }
        }
        long min = 1_000_000_000L;
        long max = 9_999_999_999L;
        accountNumber = min + (long) (Math.random() * (max - min + 1));
        for (BankAccount ba : account) {
            while (accountNumber == ba.getAccountNumber()) {
                accountNumber = min + (long) (Math.random() * (max - min + 1));
            }
        }
        sa.setAccountNumber(accountNumber);
        account.add(sa);
        System.out.println("Hii " + sa.getAccountHolderName() + " your Savings account is created");
    }

    public void createSavingsAccount(SavingsAccount[] sa) {
        for (int i = 0; i < sa.length; i++) {
            account.add(sa[i]);
        }
    }

    public void createCurrentAccount(CurrentAccount ca) {
        for (BankAccount ba : account) {
            if (ba.getMobileNumber() == ca.getMobileNumber() && ba.getAccountHolderName().equalsIgnoreCase(ca.getAccountHolderName())) {
                if (ba instanceof CurrentAccount) {
                    System.out.println("Account already exists");
                    return;
                }
            }
        }
        long min = 1_000_000_000L;
        long max = 9_999_999_999L;
        accountNumber = min + (long) (Math.random() * (max - min + 1));
        for (BankAccount ba : account) {
            while (accountNumber == ba.getAccountNumber()) {
                accountNumber = min + (long) (Math.random() * (max - min + 1));
            }
        }
        ca.setAccountNumber(accountNumber);
        account.add(ca);
        System.out.println("Hii " + ca.getAccountHolderName() + " your Current account is created");
    }

    public void createCurrentAccount(CurrentAccount[] ca) {
        for (int i = 0; i < ca.length; i++) {
            account.add(ca[i]);
        }
    }

    public BankAccount findAccount(long accountNumber) {
        for (BankAccount ac : account) {
            if (ac.getAccountNumber() == accountNumber) {
                return ac;
            }
        }
        return null;
    }

    public BankAccount findMyAccount(Customer cs) {
        for (BankAccount ac : account) {
            if (ac.getMobileNumber() == cs.getMobileNumber()) {
                return ac;
            }
        }
        return null;
    }

    public BankAccount findMySavingsAccount(Customer cs) {
        for (BankAccount ac : account) {
            if (ac instanceof SavingsAccount && ac.getMobileNumber() == cs.getMobileNumber()) {
                return ac;
            }
        }
        return null;
    }

    public BankAccount findMyCurrentAccount(Customer cs) {
        for (BankAccount ac : account) {
            if (ac instanceof CurrentAccount && ac.getMobileNumber() == cs.getMobileNumber()) {
                return ac;
            }
        }
        return null;
    }

    public void displayAccounts() {
        if (!account.isEmpty()) {
            for (int i = 0; i < account.size(); i++) {
                account.get(i).displayAccountDetails();
                System.out.println("------------------------------------------------");
            }
        } else {
            System.out.println("Sorry no accounts are present");
        }
    }

    public void addMoney(Customer cust, long money) {
        BankAccount ac = findMyAccount(cust);
        if (ac != null) {
            ac.deposit(money);
        } else {
            System.out.println("Sorry ! You dont have any account");
        }

    }

    public long checkBalance(Customer cust) {
        BankAccount ac = findMyAccount(cust);
        if (ac != null) {
            long balance = ac.getBankBalance();
            return balance;
        } else {
            System.out.println("Sorry ! You dont have any account");
        }
        return -1;
    }

    public void withdrawMoney(Customer cust, long money) {
        CurrentAccount ac1 = (CurrentAccount) findMyCurrentAccount(cust);
        SavingsAccount ac2 = (SavingsAccount) findMySavingsAccount(cust);
        if (ac1 != null) {
            ac1.withdraw(money);
            return;
        } else if (ac2 != null) {
            ac2.withdraw(money);
            return;
        } else {
            System.out.println("Sorry ! You dont have any account");
        }
    }

    public void saveCustomersToFile(Customer cust) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Swaraj\\eclipse-workspace\\BankManagementSystem\\Customer.txt",true));
            bw.write(cust.getName()+"|");
            bw.write(cust.getPassword()+"|");
            bw.write(cust.getUserName()+"|");
            bw.write(cust.getMobileNumber()+"|");
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error while saving customer details");
        }
    }

    public void getCutomersFromFile(){
        try(BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\Swaraj\\eclipse-workspace\\BankManagementSystem\\Customer.txt"))) {
            String line=br.readLine();
            while(line!=null){
                if(line.trim().isEmpty()) {
                    line = br.readLine();
                    continue;
                }
                String []str=line.split("\\|");
                String name=str[0];
                String password=str[1];
                String username=str[2];
                long mobileNumber=Long.parseLong(str[3]);
                Customer c =new Customer(name,password,mobileNumber,username);
                cust.add(c);
                line= br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error while retriving data from file");
        }
    }
}
