package edu.training.web.service.impl;

import edu.training.web.bean.AuthenticationInfo;
import edu.training.web.bean.User;
import edu.training.web.bean.UserProfile;
import edu.training.web.bean.UserRegistrationInfo;
import edu.training.web.service.UserRoles;
import edu.training.web.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User signIn(AuthenticationInfo authenticationInfo) {

        return switch (authenticationInfo.getLogin()) {
            case ("admin@mail.ru") -> new User("Admin", UserRoles.ADMINISTRATOR);
            case ("contributor@mail.ru") -> new User("Contributor", UserRoles.CONTRIBUTOR);
            case ("moderator@mail.ru") -> new User("Moderator", UserRoles.MODERATOR);
            case ("reader@mail.ru") -> new User("Reader", UserRoles.READER);
            default -> null;
        };

    }

    @Override
    public User signUp(UserRegistrationInfo userRegistrationInfo) {

        if (userRegistrationInfo.getPassword().equals(userRegistrationInfo.getConfirmPassword())) {

            return new User(userRegistrationInfo.getLogin(), UserRoles.READER);

        }

        return null;
    }

    @Override
    public UserProfile userProfile(int id) {
        return null;
    }
}
