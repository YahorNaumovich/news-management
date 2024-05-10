package edu.training.web.dao.impl;

import edu.training.web.bean.AuthenticationInfo;
import edu.training.web.bean.User;
import edu.training.web.bean.UserRegistrationInfo;
import edu.training.web.dao.AuthenticationDao;
import edu.training.web.dao.DaoException;
import edu.training.web.service.UserRoles;

public class SQLAuthenticationDao implements AuthenticationDao {
    @Override
    public User signIn(AuthenticationInfo authenticationInfo) throws DaoException {
        return switch (authenticationInfo.getLogin()) {
            case ("admin@mail.ru") -> new User("Admin", UserRoles.ADMINISTRATOR);
            case ("contributor@mail.ru") -> new User("Contributor", UserRoles.CONTRIBUTOR);
            case ("moderator@mail.ru") -> new User("Moderator", UserRoles.MODERATOR);
            case ("reader@mail.ru") -> new User("Reader", UserRoles.READER);
            default -> null;
        };
    }

    @Override
    public User signUp(UserRegistrationInfo userRegistrationInfo) throws DaoException {

        if (userRegistrationInfo.getPassword().equals(userRegistrationInfo.getConfirmPassword())) {

            return new User(userRegistrationInfo.getLogin(), UserRoles.READER);

        }

        return null;
    }
}
