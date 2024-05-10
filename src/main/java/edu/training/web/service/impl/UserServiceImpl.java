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

        User user;

        try {
            user = authenticationDao.signUp(userRegistrationInfo);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public UserProfile userProfile(int id) {
        return null;
    }
}
