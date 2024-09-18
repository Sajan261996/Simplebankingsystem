import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AccountServices {
    private List<Account> accounts;

    // Constructor
    public AccountServices() {
        this.accounts = new ArrayList<>();
    }

    /**
     * Implement function to create a new account
     * 1. Initialize a new Account object with 'name' and 'pin'
     * 2. Add it to the 'accounts' list
     * 3. Print a success message, "Account created successfully. Account number: " with the account number
     * 4. Return the newly created account object
     * @return newAccount
     */
    // Method to create a new account
    public Account createAccount(String name, int pin) {
        Account newAccount = new Account(name, pin); // Corrected: passing only 'name' and 'pin'
        accounts.add(newAccount);
        System.out.println("Account created successfully. Account number: " + newAccount.getAccountNumber());
        return newAccount;
    }

    // Method to display all details of the account
    public void showAccountInfo(Account account) {
        System.out.println("================================");
        System.out.println("=\tAccount Number: " + account.getAccountNumber());
        System.out.println("=\tName: " + account.getName());
        System.out.println("=\tBalance: $" + account.getBalance());
        System.out.println("================================");
    }

    // Method to generate account number
    private String generateAccountNumber() {
        // Get the first character of the name
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000; // 4-digit random number
        return "EFB" + randomNumber;
    }

    /**
     * Implement function to return the current balance of the account
     */
    public double showBalance(Account account) {
        return account.getBalance();
    }

    /** 
     * Implement deposit function
     * 1. Check if the deposit amount is valid
     * 2. Add the amount to the account balance
     */
    public void deposit(Account account, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount for deposit.");
            return;
        }
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposit successful. Updated balance: $" + account.getBalance());
    }

    // Method to withdraw funds from the account
    public void withdraw(Account account, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount for withdrawal.");
            return;
        }
        if (amount > account.getBalance()) {
            System.out.println("Insufficient funds.");
            return;
        }
        account.setBalance(account.getBalance() - amount);
        System.out.println("Withdrawal successful. Updated balance: $" + account.getBalance());
    }

    // Method to transfer funds from one account to another
    public void transfer(Account senderAccount, Account recipientAccount, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount for transfer.");
            return;
        }
        if (amount > senderAccount.getBalance()) {
            System.out.println("Insufficient funds.");
            return;
        }
        senderAccount.setBalance(senderAccount.getBalance() - amount);
        recipientAccount.setBalance(recipientAccount.getBalance() + amount);
        System.out.println("Transfer successful. Updated balance: $" + senderAccount.getBalance());
    }

    // Method to get an account using the account holder's name
    public Account findAccountByName(String name) {
        for (Account account : accounts) {
            if (account.getName().equals(name)) {
                return account;
            }
        }
        return null;
    }
}
