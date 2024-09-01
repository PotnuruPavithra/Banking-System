package com.banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection class provides a method to establish a connection
 * to the MySQL database used by the Banking System application.
 */
public class DatabaseConnection {

    // Database URL, including the database name
    private static final String URL = "jdbc:mysql://localhost:3306/BankingSystem";
    
    // Database username
    private static final String USER = "root";
    
    // Database password
    private static final String PASSWORD = "Phoenix@7";

    /**
     * Gets a connection to the database using JDBC.
     * 
     * @return Connection object to interact with the database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        // Establish and return a connection to the database
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
