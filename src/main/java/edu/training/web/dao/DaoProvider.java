package edu.training.web.dao;

import edu.training.web.dao.impl.SQLAuthenticationDao;
import edu.training.web.dao.impl.SQLNewsDao;

public final class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();

    private final AuthenticationDao authenticationDao = new SQLAuthenticationDao();
    private final NewsDao newsDao = new SQLNewsDao();

    private DaoProvider() {
    }

    public AuthenticationDao getAuthenticationDao() {
        return authenticationDao;
    }

    public NewsDao getNewsDao() {
        return newsDao;
    }

    public static DaoProvider getInstance() {
        return instance;
    }
}
