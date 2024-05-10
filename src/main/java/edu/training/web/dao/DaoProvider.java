package edu.training.web.dao;

import edu.training.web.dao.impl.SQLAuthenticationDao;

public final class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();

    private AuthenticationDao authenticationDao = new SQLAuthenticationDao();

    private DaoProvider() {}

    public AuthenticationDao getAuthenticationDao() {
        return authenticationDao;
    }

    public static DaoProvider getInstance() {
        return instance;
    }
}
