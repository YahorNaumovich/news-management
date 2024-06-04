package edu.training.web.controller.listener;

import edu.training.web.dao.ConnectionPool;
import edu.training.web.dao.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AppContextListener implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(AppContextListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().initPoolData();
            LOGGER.info("Connection Pool initialized successfully.");
        } catch (ConnectionPoolException e) {
            LOGGER.log(Level.SEVERE, "Error initializing Connection Pool", e);
            throw new RuntimeException("Failed to initialize Connection Pool", e); // Ensure the application fails on startup error
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().dispose();
        LOGGER.info("Connection Pool disposed successfully.");
    }
}
