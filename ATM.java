import java.util.Scanner;
import java.util.ArrayList;

class User {
    private String userId;
    private String userPin;
    private double balance;
    private ArrayList<String> transactionHistory;

    public User(String userId, String userPin, double balance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: +" + amount);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdraw: -" + amount);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void transfer(User recipient, double amount) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add("Transfer: -" + amount + " to " + recipient.getUserId());
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

public class ATM {
    public static void main(String[] args) {
        User user = new User("12345", "1234", 1000.0);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM system!");

        // Simulate login
        System.out.print("Enter User ID: ");
        String userId = scanner.next();
        System.out.print("Enter User PIN: ");
        String userPin = scanner.next();

        if (userId.equals(user.getUserId()) && userPin.equals(user.getUserPin())) {
            System.out.println("Login successful!");
            boolean isRunning = true;

            while (isRunning) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Transaction History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        user.printTransactionHistory();
                        break;
                    case 2:
                        System.out.print("Enter the amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        user.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter the amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        user.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter the recipient's User ID: ");
                        String recipientId = scanner.next();
                        User recipient = user; // Replace with logic to find the recipient user
                        System.out.print("Enter the amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        user.transfer(recipient, transferAmount);
                        break;
                    case 5:
                        isRunning = false;
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Login failed. Please check your User ID and PIN.");
        }
    }
}
