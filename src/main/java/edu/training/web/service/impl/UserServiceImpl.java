package edu.training.web.service.impl;

import edu.training.web.bean.AuthenticationInfo;
import edu.training.web.bean.User;
import edu.training.web.bean.UserProfile;
import edu.training.web.bean.UserRegistrationInfo;
import edu.training.web.dao.AuthenticationDao;
import edu.training.web.dao.DaoException;
import edu.training.web.dao.DaoProvider;
import edu.training.web.service.ServiceException;
import edu.training.web.service.UserRoles;
import edu.training.web.service.UserService;

import java.util.Map;

public class UserServiceImpl implements UserService {

    private AuthenticationDao authenticationDao = DaoProvider.getInstance().getAuthenticationDao();

    @Override
    public User signIn(AuthenticationInfo authenticationInfo) throws ServiceException {

        User user;

        try {
            user = authenticationDao.signIn(authenticationInfo);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public User signUp(UserRegistrationInfo userRegistrationInfo) throws ServiceException {

        if (!userRegistrationInfo.getPassword().equals(userRegistrationInfo.getConfirmPassword())) {
            throw new ServiceException("Passwords do not match");
        }

        try {
            if (authenticationDao.userExists(userRegistrationInfo.getEmail())) {
                throw new ServiceException("User with this email already exists");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        User user;

        try {
            user = authenticationDao.signUp(userRegistrationInfo);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public Map<String, User> getAllUsers() throws ServiceException {

        Map<String, User> users;

        try {
            users = authenticationDao.getAllUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return users;
    }

    @Override
    public UserProfile userProfile(int id) {
        return null;
    }
}
