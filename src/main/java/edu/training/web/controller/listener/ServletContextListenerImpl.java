package edu.training.web.controller.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServletContextListenerImpl implements ServletContextListener {

    private Connection connection;

    private static final Logger logger = Logger.getLogger(ServletContextListenerImpl.class.getName());

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
            logger.info("Database connection initialized.");

        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.SEVERE, "Error initializing database connection", e);
            throw new RuntimeException("Error initializing database connection", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    // Close the database connection when the application is stopped
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                logger.info("Database connection closed.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error closing database connection", e);
        }

        // Unregister JDBC drivers
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                logger.info("JDBC driver unregistered: " + driver);
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error unregistering JDBC driver: " + driver, e);
            }
        }

        // Stop AbandonedConnectionCleanupThread
        com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
        logger.info("AbandonedConnectionCleanupThread stopped.");
    }
}
