package com.banking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Manages operations related to bank accounts, including adding, viewing, updating, and closing accounts.
 */
public class AccountManagement {

    /**
     * Adds a new account to the database.
     * 
     * @param customerId the ID of the customer who owns the account
     * @param accountType the type of the account (e.g., Savings, Checking)
     * @param initialBalance the initial balance of the account
     */
	public void addAccount(int customerId, String accountType, double initialBalance) {
	    // First, check if the customer exists
	    if (!customerExists(customerId)) {
	        System.out.println("Error: Customer ID does not exist.");
	        return;
	    }

	    // Proceed with adding the account
	    String query = "INSERT INTO Account (customer_id, account_type, balance) VALUES (?, ?, ?)";
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setInt(1, customerId);
	        statement.setString(2, accountType);
	        statement.setDouble(3, initialBalance);
	        statement.executeUpdate();
	        System.out.println("Account successfully added.");
	    } catch (SQLException e) {
	        System.err.println("Error adding account: " + e.getMessage());
	    }
	}

	private boolean customerExists(int customerId) {
	    String query = "SELECT 1 FROM Customer WHERE customer_id = ?";
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setInt(1, customerId);
	        ResultSet rs = statement.executeQuery();
	        return rs.next();
	    } catch (SQLException e) {
	        System.err.println("Error checking customer existence: " + e.getMessage());
	        return false;
	    }
	}
	


    /**
     * Views the details of a specific account.
     * 
     * @param accountId the ID of the account to view
     */
    public void viewAccountDetails(int accountId) {
        String query = "SELECT * FROM Account WHERE account_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, accountId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println("Account ID: " + rs.getInt("account_id"));
                System.out.println("Customer ID: " + rs.getInt("customer_id"));
                System.out.println("Account Type: " + rs.getString("account_type"));
                System.out.println("Balance: $" + rs.getDouble("balance"));
            } else {
                System.out.println("No account found with ID: " + accountId);
            }
        } catch (SQLException e) {
            System.err.println("Error viewing account details: " + e.getMessage());
        }
    }
    
    /**
     * Updates the details of an existing account.
     * 
     * @param accountId the ID of the account to update
     * @param newAccountType the new type of the account (e.g., Savings, Checking)
     * @param newBalance the new balance of the account
     */
    public void updateAccount(int accountId, String newAccountType, double newBalance) {
        String query = "UPDATE Account SET account_type = ?, balance = ? WHERE account_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newAccountType);
            statement.setDouble(2, newBalance);
            statement.setInt(3, accountId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Account successfully updated.");
            } else {
                System.out.println("No account found with ID: " + accountId);
            }
        } catch (SQLException e) {
            System.err.println("Error updating account: " + e.getMessage());
        }
    }
    public void deleteTransactions(int accountId) {
        String query = "DELETE FROM Transaction WHERE account_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, accountId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Transactions successfully deleted.");
            } else {
                System.out.println("No transactions found for Account ID: " + accountId);
            }
        } catch (SQLException e) {
            System.err.println("Error deleting transactions: " + e.getMessage());
        }
    }

    /**
     * Closes (deletes) an account from the database.
     * 
     * @param accountId the ID of the account to close
     */
    public void closeAccount(int accountId) {
        // First, delete related transactions
        deleteTransactions(accountId);

        // Then, delete the account
        String query = "DELETE FROM Account WHERE account_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, accountId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Account successfully closed.");
            } else {
                System.out.println("No account found with ID: " + accountId);
            }
        } catch (SQLException e) {
            System.err.println("Error closing account: " + e.getMessage());
        }
    }

}
