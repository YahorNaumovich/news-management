package edu.training.web.service;

import edu.training.web.service.impl.NewsServiceImpl;
import edu.training.web.service.impl.NewsValidatorServiceImpl;
import edu.training.web.service.impl.UserServiceImpl;
import edu.training.web.service.impl.UserValidatorServiceImpl;

public final class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    private final UserService userService = new UserServiceImpl();

    private final UserValidatorService userValidatorService = new UserValidatorServiceImpl();

    private final NewsService newsService = new NewsServiceImpl();

    private final NewsValidatorService newsValidatorService = new NewsValidatorServiceImpl();

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

    public NewsValidatorService getNewsValidationService() {
        return newsValidatorService;
    }
}
