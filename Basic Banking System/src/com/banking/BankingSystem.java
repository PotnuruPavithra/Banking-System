package com.banking;

import java.util.Scanner;

/**
 * The main class for the Banking System application.
 * Provides a menu-driven interface for managing bank accounts and transactions.
 */
public class BankingSystem {

    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Create instances of AccountManagement and TransactionManagement
        AccountManagement accountManagement = new AccountManagement();
        TransactionManagement transactionManagement = new TransactionManagement();

        // Infinite loop to keep the menu running until the user chooses to exit
        while (true) {
            // Display the banking system menu
            System.out.println("Banking System Menu:");
            System.out.println("1. Add Account");
            System.out.println("2. View Account");
            System.out.println("3. Update Account");
            System.out.println("4. Close Account");
            System.out.println("5. Deposit Funds");
            System.out.println("6. Withdraw Funds");
            System.out.println("7. Transfer Funds");
            System.out.println("8. View Transaction History");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            // Read the user's choice from the console
            int choice = scanner.nextInt();

            // Execute the appropriate action based on the user's choice
            switch (choice) {
                case 1:
                    // Add a new account
                    System.out.println("Enter Customer ID:");
                    int customerId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    System.out.println("Enter Account Type (Savings/Checking):");
                    String accountType = scanner.nextLine();
                    System.out.println("Enter Initial Balance:");
                    double balance = scanner.nextDouble();
                    accountManagement.addAccount(customerId, accountType, balance);
                    break;
                case 2:
                    // View details of an existing account
                    System.out.println("Enter Account ID:");
                    int accountId = scanner.nextInt();
                    accountManagement.viewAccountDetails(accountId);
                    break;
                case 3:
                    // Update details of an existing account
                    System.out.print("Enter Account ID: ");
                    accountId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter New Account Type (Savings/Checking): ");
                    String newAccountType = scanner.nextLine();
                    System.out.print("Enter New Balance: ");
                    double newBalance = scanner.nextDouble();
                    accountManagement.updateAccount(accountId, newAccountType, newBalance);
                    break;
                case 4:
                    // Close an existing account
                    System.out.print("Enter Account ID to Close: ");
                    accountId = scanner.nextInt();
                    accountManagement.closeAccount(accountId);
                    break;
                case 5:
                    // Deposit funds into an account
                    System.out.println("Enter Account ID:");
                    accountId = scanner.nextInt();
                    System.out.println("Enter Amount to Deposit:");
                    double depositAmount = scanner.nextDouble();
                    transactionManagement.deposit(accountId, depositAmount);
                    break;
                case 6:
                    // Withdraw funds from an account
                    System.out.println("Enter Account ID:");
                    accountId = scanner.nextInt();
                    System.out.println("Enter Amount to Withdraw:");
                    double withdrawalAmount = scanner.nextDouble();
                    transactionManagement.withdraw(accountId, withdrawalAmount);
                    break;
                case 7:
                    // Transfer funds between two accounts
                    System.out.print("Enter Source Account ID: ");
                    int fromAccountId = scanner.nextInt();
                    System.out.print("Enter Destination Account ID: ");
                    int toAccountId = scanner.nextInt();
                    System.out.print("Enter Amount to Transfer: ");
                    double transferAmount = scanner.nextDouble();
                    transactionManagement.transferFunds(fromAccountId, toAccountId, transferAmount);
                    break;
                case 8:
                    // View transaction history for an account
                    System.out.print("Enter Account ID to View Transaction History: ");
                    accountId = scanner.nextInt();
                    transactionManagement.viewTransactionHistory(accountId);
                    break;
                case 9:
                    // Exit the application
                    System.out.println("Exiting...");
                    scanner.close(); // Close the scanner to free resources
                    return; // Exit the loop and end the program
                default:
                    // Handle invalid options
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
