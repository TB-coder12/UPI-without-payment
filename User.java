public class User {
    private String name;
    private String upiId;
    private double balance;

    public User(String name, String upiId, double balance) {
        this.name = name;
        this.upiId = upiId;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getUpiId() {
        return upiId;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if(balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
