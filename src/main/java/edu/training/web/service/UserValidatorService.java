package edu.training.web.service;

public interface UserValidatorService {

    boolean doPasswordsMatch(String password, String confirmPassword);

    boolean doesUserExist(String email) throws ServiceException;

    boolean isPasswordValid(String password);

    boolean isPasswordLengthValid(String password);

}
