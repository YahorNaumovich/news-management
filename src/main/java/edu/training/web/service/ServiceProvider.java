package edu.training.web.service;

import edu.training.web.service.impl.NewsServiceImpl;
import edu.training.web.service.impl.UserServiceImpl;
import edu.training.web.service.impl.UserValidatorServiceImpl;

public final class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    private UserService userService = new UserServiceImpl();

    private UserValidatorService userValidatorService = new UserValidatorServiceImpl();

    private NewsService newsService = new NewsServiceImpl();

    private ServiceProvider() {

    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public UserValidatorService getUserValidatorService() {
        return userValidatorService;
    }

    public NewsService getNewsService() {
        return newsService;
    }
}
