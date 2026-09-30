import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<BankAccount> account;
    private long accountNumber;
    private List<Customer> cust;
    private List<Transaction> trans;
    private int transactionId=100;

    public Bank() {
        account = new ArrayList<BankAccount>();
        cust = new ArrayList<Customer>();
        trans = new ArrayList<>();
        getCutomersFromFile();
        getAccountDetailsFromFile();
        getTransactionsFromFile();
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
            if (c1.getName().equalsIgnoreCase(userName)) {
                return c1;
            }
        }
        return null;
    }

    public void displayCustomers(){
        for (Customer c1 : cust) {
            c1.displayCustomerDetails();
        }
    }

    public int getTransactionId(){
        return transactionId++;
    }

    public Customer login(String userName, String password) {

        for (Customer c : cust) {
            String user = c.getUserName();
            String pass = c.getPassword();
            if (user.equalsIgnoreCase(userName) && pass.equals(password)) {
                System.out.println("------------------------------");
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
        addAcccountDetailsToFile();
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
        addAcccountDetailsToFile();
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
            if (ac.getMobileNumber() == cs.getMobileNumber() && ac.getAccountHolderName().equalsIgnoreCase(cs.getName())) {
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
            }
        } else {
            System.out.println("Sorry no accounts are present");
        }
    }

    public void addMoney(Customer cust, long money) {
        BankAccount ac = findMyAccount(cust);
        if (ac != null) {
            ac.deposit(money);
            addAcccountDetailsToFile();
            int transactionId=getTransactionId();
            String transactionType = "Deposit";
            accountNumber = ac.getAccountNumber();
            long amount = money;
            long balanceAfterTransaction = ac.getBankBalance();
            String dateTime = dateAndTimeGenerator();
            saveTransaction(transactionId,accountNumber, transactionType, amount, balanceAfterTransaction, dateTime);
            addTransactionsToFile();
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
        boolean result;
        if (ac1 != null) {
            result = ac1.withdraw(money);
            if (!result) {
                return;
            }
            addAcccountDetailsToFile();
            int transactionId=getTransactionId();
            String transactionType = "Withdraw";
            accountNumber = ac1.getAccountNumber();
            long amount = money;
            long balanceAfterTransaction = ac1.getBankBalance();
            String dateTime = dateAndTimeGenerator();
            saveTransaction(transactionId,accountNumber, transactionType, amount, balanceAfterTransaction, dateTime);
            addTransactionsToFile();
        } else if (ac2 != null) {
            result = ac2.withdraw(money);
            if (!result) {
                return;
            }
            addAcccountDetailsToFile();
            int transactionId=getTransactionId();
            String transactionType = "Withdraw";
            long amount = money;
            accountNumber = ac2.getAccountNumber();
            long balanceAfterTransaction = ac2.getBankBalance();
            String dateTime = dateAndTimeGenerator();
            saveTransaction(transactionId,accountNumber, transactionType, amount, balanceAfterTransaction, dateTime);
            addTransactionsToFile();
        } else {
            System.out.println("Sorry ! You dont have any account");
        }
    }

    public void saveCustomersToFile(Customer cust) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Swaraj\\eclipse-workspace\\BankManagementSystem\\Customer.txt", true));
            bw.write(cust.getName() + "|");
            bw.write(cust.getPassword() + "|");
            bw.write(cust.getUserName() + "|");
            bw.write(cust.getMobileNumber() + "|");
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error while saving customer details");
        }
    }

    public void getCutomersFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Swaraj\\eclipse-workspace\\BankManagementSystem\\Customer.txt"))) {
            String line = br.readLine();
            while (line != null) {
                if (line.trim().isEmpty()) {
                    line = br.readLine();
                    continue;
                }
                String[] str = line.split("\\|");
                String name = str[0];
                String password = str[1];
                String username = str[2];
                long mobileNumber = Long.parseLong(str[3]);
                Customer c = new Customer(name, password, mobileNumber, username);
                cust.add(c);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error while retriving data from file");
        }
    }

    public void addAcccountDetailsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Swaraj\\eclipse-workspace\\BankManagementSystem\\BankAccount.txt"))) {
            for (BankAccount ba : account) {
                if (ba instanceof SavingsAccount) {
                    bw.write("SavingsAccount|");
                } else {
                    bw.write("CurrentAccount|");
                }
                bw.write(ba.getAccountHolderName() + "|");
                bw.write(ba.getAccountNumber() + "|");
                bw.write(ba.getMobileNumber() + "|");
                bw.write(ba.getBankBalance() + "|");
                bw.write(BankAccount.IFC_CODE + "|");
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            System.out.println("Error while Saving data to the file");
        }
    }

    public void getAccountDetailsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Swaraj\\eclipse-workspace\\BankManagementSystem\\BankAccount.txt"))) {
            String line = br.readLine();
            while (line != null) {
                String str[] = line.split("\\|");
                String accountType = str[0];
                String accountHolderName = str[1];
                long accountNumber = Long.parseLong(str[2]);
                long mobileNumber = Long.parseLong(str[3]);
                long bankBalance = Long.parseLong(str[4]);

                if (accountType.equalsIgnoreCase("SavingsAccount")) {
                    SavingsAccount sa = new SavingsAccount(accountHolderName, accountNumber, mobileNumber, bankBalance);
                    account.add(sa);
                } else {
                    CurrentAccount ca = new CurrentAccount(accountHolderName, accountNumber, mobileNumber, bankBalance);
                    account.add(ca);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error while retriving data from file");
        }
    }

    public void saveTransaction(int transactionId,long accountNumber, String transactionType, long amount, long balanceAfterTransaction, String dateTime) {
        Transaction transaction = new Transaction(transactionId,accountNumber, transactionType, amount, balanceAfterTransaction, dateTime);
        trans.add(transaction);
    }

    public String dateAndTimeGenerator() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
        return now.format(formatter);
    }

    public void displayTransactionDetails(Customer cust) {
        BankAccount ac = findMyAccount(cust);
        for (Transaction t : trans) {
            if (t.getAccountNumber() == ac.getAccountNumber()) {
                t.displayTransactionDetails();
            }
        }
    }
    public void displayTransactionDetails() {

        for (Transaction t : trans) {
            t.displayTransactionDetails();
        }
    }

    public void addTransactionsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Swaraj\\eclipse-workspace\\BankManagementSystem\\Transaction.txt"));) {
            for (Transaction tr : trans) {
                bw.write(tr.getTransactionId() + "|");
                bw.write(tr.getAccountNumber() + "|");
                bw.write(tr.getTransactionType() + "|");
                bw.write(tr.getAmount() + "|");
                bw.write(tr.getBalanceAfterTransaction() + "|");
                bw.write(tr.getDateTime());
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            System.out.println("Error while saving transaction data to file");
        }
    }

    public void getTransactionsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Swaraj\\eclipse-workspace\\BankManagementSystem\\Transaction.txt"));) {
            String line = br.readLine();
            int id=0;
            while (line != null) {
                if(line.trim().isEmpty()){
                    return;
                }
                String[] str = line.split("\\|");
                int transactionId = Integer.parseInt(str[0]);
                if(transactionId>id){
                    id=transactionId;
                }
                long accountNumber=Long.parseLong(str[1]);
                String transactionType=str[2];
                long amount=Long.parseLong(str[3]);
                long balanceAfterTransaction=Long.parseLong(str[4]);
                String dateTime=str[5];
                Transaction transaction=new Transaction(transactionId,accountNumber,transactionType,amount,balanceAfterTransaction,dateTime);
                trans.add(transaction);
                line=br.readLine();
            }
            this.transactionId=id;
        } catch (IOException e) {
            System.out.println("Error while retriving data from file");
        }

    }

    public Admin adminlogin(String userName, String password) {
        Admin ad=new Admin();
        if(userName.equals(ad.getUsername()) && password.equals(ad.getPassword())){
            return ad;
        }
        return null;
    }
}
