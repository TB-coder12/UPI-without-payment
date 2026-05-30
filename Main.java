public class Main {

    public static void main(String[] args) {

        UPIService service = new UPIService();

        User u1 = new User(
            "Ram",
            "ram@upi",
            5000
        );

        User u2 = new User(
            "Shyam",
            "shyam@upi",
            2000
        );

        service.addUser(u1);
        service.addUser(u2);

        service.showBalance("ram@upi");

        service.transfer(
            "ram@upi",
            "shyam@upi",
            1000
        );

        service.showBalance("ram@upi");
        service.showBalance("shyam@upi");

        System.out.println("\nTransactions:");
        service.showTransactions();
    }
}
