package edu.training.web.dao.impl;

import edu.training.web.bean.AuthenticationInfo;
import edu.training.web.bean.User;
import edu.training.web.bean.UserRegistrationInfo;
import edu.training.web.dao.AuthenticationDao;
import edu.training.web.dao.DaoException;
import edu.training.web.service.UserRoles;

import java.util.HashMap;

public class SQLAuthenticationDao implements AuthenticationDao {

    HashMap<String, User> users = new HashMap<>();

    public SQLAuthenticationDao() {
        users.put("admin@mail.ru", new User("Admin", UserRoles.ADMINISTRATOR));
        users.put("contributor@mail.ru", new User("Contributor", UserRoles.CONTRIBUTOR));
        users.put("moderator@mail.ru", new User("Moderator", UserRoles.MODERATOR));
        users.put("reader@mail.ru", new User("Reader", UserRoles.READER));
    }

    @Override
    public User signIn(AuthenticationInfo authenticationInfo) throws DaoException {
        return users.get(authenticationInfo.getLogin());
    }

    @Override
    public User signUp(UserRegistrationInfo userRegistrationInfo) throws DaoException {

        if (users.containsKey(userRegistrationInfo.getEmail())) {
            throw new DaoException("This email is already registered");
        }

        if (!userRegistrationInfo.getPassword().equals(userRegistrationInfo.getConfirmPassword())) {
            throw new DaoException("Passwords do not match");
        }

        users.put(userRegistrationInfo.getEmail(), new User(userRegistrationInfo.getLogin(), UserRoles.READER));

        return new User(userRegistrationInfo.getLogin(), UserRoles.READER);
    }
}
