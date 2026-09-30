public class Customer {
    private String name;
    private String password;
    private long mobileNumber;
    private String userName;

    public Customer() {

    }

    public Customer(String name, String password, long mobileNumber, String username) {
        this.name = name;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.userName = username;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public  void displayCustomerDetails(){
        System.out.println("===============================");
        System.out.println(this.name);
        System.out.println(this.mobileNumber);
        System.out.println(this.userName);
        System.out.println("===============================");
    }

}
