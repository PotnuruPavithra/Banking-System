package com.banking;

/**
 * Represents a bank account with essential details.
 */
public class Account {

    // Unique identifier for the account
    private int accountId;
    
    // Unique identifier for the customer who owns the account
    private int customerId;
    
    // Type of account (e.g., Savings, Checking)
    private String accountType;
    
    // Current balance in the account
    private double balance;

    /**
     * Gets the account ID.
     * 
     * @return the account ID
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * Sets the account ID.
     * 
     * @param accountId the account ID to set
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    /**
     * Gets the customer ID associated with the account.
     * 
     * @return the customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID for the account.
     * 
     * @param customerId the customer ID to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the account type.
     * 
     * @return the account type (e.g., Savings, Checking)
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the account type.
     * 
     * @param accountType the account type to set
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * Gets the current balance of the account.
     * 
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the account.
     * 
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account [accountId=" + accountId + ", customerId=" + customerId +
               ", accountType=" + accountType + ", balance=" + balance + "]";
    }
}
