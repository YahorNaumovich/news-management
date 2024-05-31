package edu.training.web.controller.listener;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import edu.training.web.dao.SQLBaseDao;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Close the database connection when the context is destroyed
        SQLBaseDao.closeConnection();

        System.out.println("Database connection closed");

        // Deregister JDBC drivers to avoid memory leaks
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            java.sql.Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println("Deregistered driver: " + driver);
            } catch (SQLException e) {
                System.out.println("Error deregistering driver: " + driver);
            }
        }

        // Stop the abandoned connection cleanup thread
        AbandonedConnectionCleanupThread.checkedShutdown();
    }
}
