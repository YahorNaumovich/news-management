package edu.training.web.service.impl;

import edu.training.web.dao.AuthenticationDao;
import edu.training.web.dao.DaoException;
import edu.training.web.dao.DaoProvider;
import edu.training.web.service.ServiceException;
import edu.training.web.service.UserValidatorService;

public class UserValidatorServiceImpl implements UserValidatorService {

    private final AuthenticationDao authenticationDao = DaoProvider.getInstance().getAuthenticationDao();

    @Override
    public boolean doPasswordsMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    @Override
    public boolean doesUserExist(String email) throws ServiceException {
        try {
            return authenticationDao.userExists(email);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private static final String ALLOWED_CHARACTERS_REGEX = "^[A-Za-z0-9~`!@#$%^&*()_\\-+={[}\\]|:;\"'<,>.?/]+$";

    @Override
    public boolean isPasswordValid(String password) {

        if (password == null || password.isEmpty()) {
            return false;
        }

        return password.matches(ALLOWED_CHARACTERS_REGEX);
    }

    @Override
    public boolean isPasswordLengthValid(String password) {

        if (password == null) {
            return false;
        }

        int length = password.length();

        return length >= 8 && length <= 20;
    }
}
