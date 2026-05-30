import java.util.*;

public class UPIService {

    private HashMap<String, User> users = new HashMap<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public void addUser(User user) {
        users.put(user.getUpiId(), user);
    }

    public void transfer(String senderUpi,
                         String receiverUpi,
                         double amount) {

        User sender = users.get(senderUpi);
        User receiver = users.get(receiverUpi);

        if(sender == null || receiver == null) {
            System.out.println("Invalid UPI ID");
            return;
        }

        if(sender.withdraw(amount)) {
            receiver.deposit(amount);

            transactions.add(
                new Transaction(senderUpi,
                                receiverUpi,
                                amount)
            );

            System.out.println("Payment Successful");
        } else {
            System.out.println("Insufficient Balance");
        }
    }

    public void showTransactions() {
        for(Transaction t : transactions) {
            System.out.println(t);
        }
    }

    public void showBalance(String upiId) {
        User user = users.get(upiId);

        if(user != null) {
            System.out.println(
                "Balance: ₹" + user.getBalance()
            );
        }
    }
}
