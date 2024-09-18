import java.util.InputMismatchException;
import java.util.Scanner;

public class SimpleBankingSystem {
    private static AccountServices accountService = new AccountServices();
    private static Account currentAccount;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("================");
            System.out.println("Welcome to Easy Finance Bank");
            System.out.println("1. Create account");
            System.out.println("2. Login to account");
            System.out.println("3. Quit");
            System.out.println("================");
            System.out.print("Enter your choice: ");

            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }

            scanner.nextLine(); // Consume newline character
            switch (choice) {
                case 1 -> createAccount();
                case 2 -> logIn();
                case 3 -> {
                    System.out.println("Thank you for using Easy Finance Bank. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            delay();
        }
    }

    private static void createAccount() {
        try {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter your PIN (numbers only): ");
            int pin = scanner.nextInt();

            currentAccount = accountService.createAccount(name, pin);
            System.out.println("Account created successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Clear the invalid input
        }

        delay();
        performAccountOperations();
    }

    private static void logIn() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        Account oldAccount = accountService.findAccountByName(name);

        if (oldAccount == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter PIN for " + name + ": ");
        int pin = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (pin == oldAccount.getPin()) {
            currentAccount = oldAccount;
            performAccountOperations();
        } else {
            System.out.println("PIN does not match!");
        }

        delay();
    }

    private static void performAccountOperations() {
        while (currentAccount != null) {
            System.out.println("================");
            System.out.println("Welcome to Easy Finance Bank");
            System.out.println("1. Display Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Display Account Info");
            System.out.println("6. Log out to Main Menu");
            System.out.println("================");
            System.out.print("Enter your choice: ");

            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }

            scanner.nextLine(); // Consume newline character
            switch (choice) {
                case 1 -> showBalance();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> transfer();
                case 5 -> accountService.showAccountInfo(currentAccount);
                case 6 -> {
                    currentAccount = null; // Log out the current user
                    System.out.println("Logged out successfully.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            delay();
        }
    }

    private static void showBalance() {
        System.out.println("Current Balance: $" + accountService.showBalance(currentAccount));
        delay();
    }

    private static void deposit() {
        try {
            System.out.print("Enter amount to deposit: $");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character

            if (amount > 0) {
                accountService.deposit(currentAccount, amount);
                System.out.println("Amount deposited successfully.");
            } else {
                System.out.println("Deposit amount must be positive.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.nextLine(); // Clear the invalid input
        }
        delay();
    }

    private static void withdraw() {
        try {
            System.out.print("Enter amount to withdraw: $");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character

            if (amount > 0) {
                accountService.withdraw(currentAccount, amount);
                System.out.println("Amount withdrawn successfully.");
            } else {
                System.out.println("Withdraw amount must be positive.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.nextLine(); // Clear the invalid input
        }
        delay();
    }

    private static void transfer() {
        System.out.print("Enter recipient's name: ");
        String recipientName = scanner.nextLine();
        Account recipientAccount = accountService.findAccountByName(recipientName);

        if (recipientAccount == null) {
            System.out.println("Recipient account not found.");
            delay();
            return;
        }

        try {
            System.out.print("Enter amount to transfer: $");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character

            if (amount > 0) {
                accountService.transfer(currentAccount, recipientAccount, amount);
                System.out.println("Amount transferred successfully.");
            } else {
                System.out.println("Transfer amount must be positive.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.nextLine(); // Clear the invalid input
        }
        delay();
    }

    private static void delay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
