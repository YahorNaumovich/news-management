package edu.training.web.controller.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;

public class ServletContextListenerImpl implements ServletContextListener {

    private Connection connection;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            String url = "jdbc:mysql://localhost:3306/news-management?useSSL=false";
            String username = "root";
            String password = "Egor2002";
            connection = DriverManager.getConnection(url, username, password);

            // Store the connection in the servlet context
            sce.getServletContext().setAttribute("DBConnection", connection);
            System.out.println("Database connection initialized.");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error initializing database connection", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    // Close the database connection when the application is stopped
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing database connection");
        }

        // Unregister JDBC drivers
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println("JDBC driver unregistered: " + driver);
            } catch (SQLException e) {
                System.out.println("Error unregistering JDBC driver: " + driver);
            }
        }

        // Stop AbandonedConnectionCleanupThread
        com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
        System.out.println("AbandonedConnectionCleanupThread stopped.");
    }
}
