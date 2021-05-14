package jp;

//Model
public class Account {
    
    private final int id;
    
    private final String firstName;
    private final String lastName;
    private final String accountNumber;
    
    private double deposit;

    public Account(int id, String firstName, String lastName, String accountNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
    }

    public int getId() {
        return id;
    }
    
    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public String getFirstName() {
        return this.firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public String getAccountNumber() {
        return this.accountNumber;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
