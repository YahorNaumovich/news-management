package edu.training.web.service.impl;

import edu.training.web.bean.AuthenticationInfo;
import edu.training.web.bean.User;
import edu.training.web.bean.UserRegistrationInfo;
import edu.training.web.dao.AuthenticationDao;
import edu.training.web.dao.DaoException;
import edu.training.web.dao.DaoProvider;
import edu.training.web.service.ServiceException;
import edu.training.web.service.UserRoles;
import edu.training.web.service.UserService;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {

    private final AuthenticationDao authenticationDao = DaoProvider.getInstance().getAuthenticationDao();

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

    @Override
    public User signIn(AuthenticationInfo authenticationInfo) throws ServiceException {

        LOGGER.log(Level.INFO, "Method signIn is called");

        User user;

        try {

            user = authenticationDao.signInUser(authenticationInfo);

        } catch (DaoException e) {

            LOGGER.log(Level.INFO, "Service error occurred during signing in user", e);
            throw new ServiceException(e);

        }

        return user;
    }

    @Override
    public User signUp(UserRegistrationInfo userRegistrationInfo) throws ServiceException {

        LOGGER.log(Level.INFO, "Method signUp is called");

        User user;

        try {
            user = authenticationDao.signUpUser(userRegistrationInfo);
        } catch (DaoException e) {

            LOGGER.log(Level.INFO, "Service error occurred during signing up user", e);
            throw new ServiceException(e);

        }

        return user;
    }

    @Override
    public void deleteUser(int id) throws ServiceException {

        LOGGER.log(Level.INFO, "Method deleteUser is called");

        try {

            authenticationDao.deleteUser(id);

        } catch (DaoException e) {

            LOGGER.log(Level.INFO, "Service error occurred during deleting user", e);
            throw new ServiceException(e);

        }
    }

    @Override
    public void changeUserRole(int userId, UserRoles role) throws ServiceException {

        LOGGER.log(Level.INFO, "Method changeUserRole is called");

        try {

            int roleId = authenticationDao.getUserRoleId(role.toString());
            authenticationDao.changeUserRole(userId, roleId);

        } catch (DaoException e) {

            LOGGER.log(Level.INFO, "Service error occurred during changing user role", e);
            throw new ServiceException(e);

        }
    }

    @Override
    public void changeUserPassword(int id, String newPassword, String confirmPassword) throws ServiceException {

        LOGGER.log(Level.INFO, "Method changeUserPassword is called");

        if (!newPassword.equals(confirmPassword)) {
            throw new ServiceException("Passwords do not match");
        }

        try {

            authenticationDao.changeUserPassword(id, newPassword);

        } catch (DaoException e) {

            LOGGER.log(Level.INFO, "Service error occurred during changing user password", e);
            throw new ServiceException(e);

        }
    }

    @Override
    public Map<String, User> getAllUsers() throws ServiceException {

        LOGGER.log(Level.INFO, "Method getAllUsers is called");

        Map<String, User> users;

        try {

            users = authenticationDao.getAllUsers();

        } catch (DaoException e) {

            LOGGER.log(Level.INFO, "Service error occurred during getting all users", e);
            throw new ServiceException(e);

        }

        return users;
    }
}
