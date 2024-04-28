package edu.training.web.service.impl;

import edu.training.web.bean.AuthenticationInfo;
import edu.training.web.bean.User;
import edu.training.web.bean.UserProfile;
import edu.training.web.bean.UserRegistrationInfo;
import edu.training.web.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User signIn(AuthenticationInfo authenticationInfo) {

        if("user@mail.ru".equals(authenticationInfo.getLogin())) {

            return new User("user", "admin");

        }

        return null;
    }

    @Override
    public User signUp(UserRegistrationInfo userRegistrationInfo) {

        if (userRegistrationInfo.getPassword().equals(userRegistrationInfo.getConfirmPassword())) {

            return new User(userRegistrationInfo.getLogin(), "reader");

        }

        return null;
    }

    @Override
    public UserProfile userProfile(int id) {
        return null;
    }
}
