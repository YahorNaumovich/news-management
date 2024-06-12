package edu.training.web.controller.concrete.impl;

import edu.training.web.bean.User;
import edu.training.web.bean.UserRegistrationInfo;
import edu.training.web.controller.ErrorCode;
import edu.training.web.controller.concrete.AbstractCommand;
import edu.training.web.controller.concrete.Command;
import edu.training.web.service.ServiceException;
import edu.training.web.service.ServiceProvider;
import edu.training.web.service.UserService;
import edu.training.web.service.UserValidatorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class DoRegistration extends AbstractCommand {
    private final UserService userService = ServiceProvider.getInstance().getUserService();

    private final UserValidatorService userValidatorService = ServiceProvider.getInstance().getUserValidatorService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("confirmPassword");

        try {

            if (!userValidatorService.doPasswordsMatch(password, passwordConfirm)) {
                setErrorAndRedirect(request, response, "Controller?command=go_to_registration_page", ErrorCode.PASSWORD_MISMATCH);
                return;
            }

            if (!userValidatorService.isPasswordLengthValid(password)) {
                setErrorAndRedirect(request, response, "Controller?command=go_to_registration_page", ErrorCode.PASSWORD_LENGTH_INVALID);
                return;
            }

            if (!userValidatorService.isPasswordValid(password)) {
                setErrorAndRedirect(request, response, "Controller?command=go_to_registration_page", ErrorCode.PASSWORD_INVALID);
                return;
            }

            if (userValidatorService.doesUserExist(email)) {
                setErrorAndRedirect(request, response, "Controller?command=go_to_registration_page", ErrorCode.USER_ALREADY_EXISTS);
                return;
            }


            User user = userService.signUp(new UserRegistrationInfo(login, email, password, passwordConfirm));

            if (user != null) {

                HttpSession session = (HttpSession) request.getSession(true);
                session.setAttribute("user", user);

                response.sendRedirect("Controller?command=go_to_index_page");

            }

        } catch (ServiceException e) {

            setErrorAndRedirect(request, response, "Controller?command=go_to_registration_page", ErrorCode.USER_REGISTRATION);

        }
    }
}
