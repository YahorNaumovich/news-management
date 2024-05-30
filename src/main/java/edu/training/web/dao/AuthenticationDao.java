package edu.training.web.dao;

import edu.training.web.bean.AuthenticationInfo;
import edu.training.web.bean.User;
import edu.training.web.bean.UserRegistrationInfo;

import java.util.Map;

public interface AuthenticationDao {
    User signIn(AuthenticationInfo authenticationInfo) throws DaoException;

    User signUp(UserRegistrationInfo userRegistrationInfo) throws DaoException;

    Map<String, User> getAllUsers() throws DaoException;
}