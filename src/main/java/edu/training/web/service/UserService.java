package edu.training.web.service;

import edu.training.web.bean.AuthenticationInfo;
import edu.training.web.bean.User;
import edu.training.web.bean.UserProfile;
import edu.training.web.bean.UserRegistrationInfo;

public interface UserService {
    User signIn(AuthenticationInfo authenticationInfo) throws ServiceException;

    User signUp(UserRegistrationInfo userRegistrationInfo) throws ServiceException;

    UserProfile userProfile(int id);
}
