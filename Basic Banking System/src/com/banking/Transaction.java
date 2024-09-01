package com.banking;

/**
 * Transaction class represents a financial transaction in the banking system.
 * It contains details about a transaction such as its ID, associated account, type, amount, and date.
 */
public class Transaction {
    // Unique identifier for the transaction
    private int transactionId;
    
    // Account ID associated with the transaction
    private int accountId;
    
    // Type of transaction (e.g., Deposit, Withdrawal, Transfer)
    private String transactionType;
    
    // Amount involved in the transaction
    private double amount;
    
    // Date and time when the transaction occurred
    private String transactionDate;

    // Getter and setter methods for each field

    /**
     * Gets the transaction ID.
     * 
     * @return the transaction ID
     */
    public int getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the transaction ID.
     * 
     * @param transactionId the transaction ID to set
     */
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets the account ID associated with the transaction.
     * 
     * @return the account ID
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * Sets the account ID associated with the transaction.
     * 
     * @param accountId the account ID to set
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    /**
     * Gets the type of the transaction.
     * 
     * @return the transaction type
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the type of the transaction.
     * 
     * @param transactionType the transaction type to set
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Gets the amount involved in the transaction.
     * 
     * @return the transaction amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount involved in the transaction.
     * 
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets the date and time of the transaction.
     * 
     * @return the transaction date
     */
    public String getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the date and time of the transaction.
     * 
     * @param transactionDate the transaction date to set
     */
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
