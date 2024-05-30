package edu.training.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class SQLBaseDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/news-management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Egor2002";

    protected static Connection connection;


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Failed to initialize connection: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to close the JDBC connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception appropriately
            }
        }
    }

}
