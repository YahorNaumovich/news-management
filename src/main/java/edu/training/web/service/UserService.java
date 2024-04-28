package edu.training.web.service;
import edu.training.web.bean.AuthenticationInfo;
import edu.training.web.bean.User;
import edu.training.web.bean.UserProfile;
import edu.training.web.bean.UserRegistrationInfo;

public interface UserService {
    User signIn(AuthenticationInfo authenticationInfo);
    User signUp(UserRegistrationInfo userRegistrationInfo);
    UserProfile userProfile(int id);
}
