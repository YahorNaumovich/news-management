package edu.training.web.dao;

import edu.training.web.bean.AuthenticationInfo;
import edu.training.web.bean.User;
import edu.training.web.bean.UserRegistrationInfo;

import java.util.Map;

public interface AuthenticationDao {
    User signInUser(AuthenticationInfo authenticationInfo) throws DaoException;

    boolean userExists(String email) throws DaoException;

    User signUpUser(UserRegistrationInfo userRegistrationInfo) throws DaoException;

    void deleteUser(int id) throws DaoException;

    void changeUserRole(int userId, int roleId) throws DaoException;

    int getUserRoleId(String roleName) throws DaoException;

    void changeUserPassword(int id, String newPassword) throws DaoException;

    Map<String, User> getAllUsers() throws DaoException;
}
