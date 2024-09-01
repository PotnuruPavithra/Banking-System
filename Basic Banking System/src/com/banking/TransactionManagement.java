package com.banking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * TransactionManagement class handles financial transactions in the banking system,
 * including deposit, withdrawal, fund transfer, and transaction history management.
 */
public class TransactionManagement {

    /**
     * Deposits a specified amount into the account with the given ID.
     * 
     * @param accountId the ID of the account where funds will be deposited
     * @param amount the amount to deposit
     */
    public void deposit(int accountId, double amount) {
        String query = "UPDATE Account SET balance = balance + ? WHERE account_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, amount);
            statement.setInt(2, accountId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deposit successful.");
                logTransaction(accountId, "Deposit", amount);
            } else {
                System.out.println("Deposit failed. Account ID may not exist.");
            }
        } catch (SQLException e) {
            System.err.println("Error depositing funds: " + e.getMessage());
        }
    }

    /**
     * Withdraws a specified amount from the account with the given ID.
     * 
     * @param accountId the ID of the account from which funds will be withdrawn
     * @param amount the amount to withdraw
     */
    public void withdraw(int accountId, double amount) {
        String query = "UPDATE Account SET balance = balance - ? WHERE account_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, amount);
            statement.setInt(2, accountId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Withdrawal successful.");
                logTransaction(accountId, "Withdrawal", amount);
            } else {
                System.out.println("Withdrawal failed. Account ID may not exist.");
            }
        } catch (SQLException e) {
            System.err.println("Error withdrawing funds: " + e.getMessage());
        }
    }
    
    /**
     * Transfers a specified amount from one account to another.
     * This method handles both the withdrawal from the source account and the deposit to the destination account.
     * It uses a transaction to ensure that both operations succeed or fail together.
     * 
     * @param fromAccountId the ID of the account to withdraw funds from
     * @param toAccountId the ID of the account to deposit funds into
     * @param amount the amount to transfer
     */
    public void transferFunds(int fromAccountId, int toAccountId, double amount) {
        Connection connection = null;
        PreparedStatement withdrawStatement = null;
        PreparedStatement depositStatement = null;
        
        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);  // Start transaction

            String withdrawQuery = "UPDATE Account SET balance = balance - ? WHERE account_id = ?";
            withdrawStatement = connection.prepareStatement(withdrawQuery);
            withdrawStatement.setDouble(1, amount);
            withdrawStatement.setInt(2, fromAccountId);
            int withdrawRowsAffected = withdrawStatement.executeUpdate();

            if (withdrawRowsAffected > 0) {
                String depositQuery = "UPDATE Account SET balance = balance + ? WHERE account_id = ?";
                depositStatement = connection.prepareStatement(depositQuery);
                depositStatement.setDouble(1, amount);
                depositStatement.setInt(2, toAccountId);
                int depositRowsAffected = depositStatement.executeUpdate();

                if (depositRowsAffected > 0) {
                    connection.commit();  // Commit transaction if both succeed
                    System.out.println("Transfer successful.");
                    logTransaction(fromAccountId, "Transfer Out", amount);
                    logTransaction(toAccountId, "Transfer In", amount);
                } else {
                    connection.rollback();  // Rollback transaction on failure
                    System.out.println("Transfer failed. Destination Account ID may not exist.");
                }
            } else {
                connection.rollback();  // Rollback transaction on failure
                System.out.println("Transfer failed. Source Account ID may not exist.");
            }

        } catch (SQLException e) {
            try {
                if (connection != null) connection.rollback();
            } catch (SQLException ex) {
                System.err.println("Error during transaction rollback: " + ex.getMessage());
            }
            System.err.println("Error transferring funds: " + e.getMessage());
        } finally {
            try {
                if (withdrawStatement != null) withdrawStatement.close();
                if (depositStatement != null) depositStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    
    /**
     * Displays the transaction history for the account with the given ID.
     * 
     * @param accountId the ID of the account whose transaction history is to be viewed
     */
    public void viewTransactionHistory(int accountId) {
        String query = "SELECT * FROM Transaction WHERE account_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, accountId);
            ResultSet rs = statement.executeQuery();
            System.out.println("Transaction History for Account ID: " + accountId);
            while (rs.next()) {
                System.out.println("Transaction ID: " + rs.getInt("transaction_id"));
                System.out.println("Transaction Type: " + rs.getString("transaction_type"));
                System.out.println("Amount: " + rs.getDouble("amount"));
                System.out.println("Date: " + rs.getTimestamp("transaction_date"));
                System.out.println("------------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error viewing transaction history: " + e.getMessage());
        }
    }

    /**
     * Logs a transaction to the database.
     * 
     * @param accountId the ID of the account associated with the transaction
     * @param transactionType the type of the transaction (e.g., Deposit, Withdrawal, Transfer)
     * @param amount the amount involved in the transaction
     */
    private void logTransaction(int accountId, String transactionType, double amount) {
        String query = "INSERT INTO Transaction (account_id, transaction_type, amount) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, accountId);
            statement.setString(2, transactionType);
            statement.setDouble(3, amount);
            statement.executeUpdate();
            System.out.println("Transaction logged successfully.");
        } catch (SQLException e) {
            System.err.println("Error logging transaction: " + e.getMessage());
        }
    }
}
