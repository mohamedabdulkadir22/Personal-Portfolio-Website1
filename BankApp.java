import java.util.Date;
import java.util.ArrayList;
import java.util.List;

// Account class
class Account implements Comparable<Account> {
    private int accountNumber;
    private double balance;
    private Date dateCreated;
    private String fullName;
    private List<String> transactionHistory;

    // Constructor
    public Account(int accountNumber, double initialBalance, String fullName) {
        this.accountNumber = accountNumber;
        this.balance = Math.max(initialBalance, 0);
        this.fullName = fullName;
        this.dateCreated = new Date();
        this.transactionHistory = new ArrayList<>();
    }

    // Getters and setters
    public int getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public Date getDateCreated() { return dateCreated; }
    public String getFullName() { return fullName; }

    public void setBalance(double balance) {
        this.balance = Math.max(balance, 0);
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Business methods
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        balance += amount;
        transactionHistory.add("Deposit: " + amount + " on " + new Date());
    }

    public void withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException("Invalid amount for withdrawal.");
        }
        balance -= amount;
        transactionHistory.add("Withdrawal: " + amount + " on " + new Date());
    }

    public String checkBalance() {
        return "Balance is: " + balance;
    }

    public String getTransactionHistory() {
        StringBuilder history = new StringBuilder();
        for (String transaction : transactionHistory) {
            history.append(transaction).append("\n");
        }
        return history.toString();
    }

    @Override
    public int compareTo(Account other) {
        return Double.compare(this.balance, other.balance);
    }
}

// SavingsAccount class
class SavingsAccount extends Account {
    private double minimumBalance;

    public SavingsAccount(int accountNumber, double initialBalance, String fullName, double minimumBalance) {
        super(accountNumber, initialBalance, fullName);
        this.minimumBalance = minimumBalance;
    }

    @Override
    public void withdraw(double amount) {
        if (getBalance() - amount < minimumBalance) {
            System.out.println("Withdrawal not possible. Minimum balance would be breached.");
        } else {
            super.withdraw(amount + 2.0); // Deducting $2.00 transaction fee
        }
    }

    public void applyInterest(double annualInterestRate) {
        double monthlyInterest = getBalance() * (annualInterestRate / 12);
        deposit(monthlyInterest);
    }
}

// Main class to run the accounts
public class BankApp {
    public static void main(String[] args) {
        Account regularAccount = new Account(1, 1000.0, "John Doe");
        SavingsAccount savingsAccount = new SavingsAccount(2, 1000.0, "Jane Doe", 200.0);

        regularAccount.deposit(500.0);
        regularAccount.withdraw(200.0);
        System.out.println(regularAccount.checkBalance());

        savingsAccount.deposit(500.0);
        savingsAccount.withdraw(200.0); // Should succeed
        savingsAccount.withdraw(1500.0); // Should fail due to minimum balance
        System.out.println(savingsAccount.checkBalance());
        savingsAccount.applyInterest(0.05); // Apply a 5% annual interest rate
        System.out.println(savingsAccount.checkBalance());

        // Showing transaction history
        System.out.println("Transaction History for Regular Account:");
        System.out.println(regularAccount.getTransactionHistory());

        System.out.println("Transaction History for Savings Account:");
        System.out.println(savingsAccount.getTransactionHistory());
    }
}