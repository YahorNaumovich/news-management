package edu.training.web.controller.concrete.impl;

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

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class DoChangeUserPassword extends AbstractCommand {

    private final UserService userService = ServiceProvider.getInstance().getUserService();

    private final UserValidatorService userValidatorService = ServiceProvider.getInstance().getUserValidatorService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("userId"));
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!userValidatorService.doPasswordsMatch(newPassword, confirmPassword)) {
            setErrorAndRedirect(request, response, "Controller?command=go_to_registration_page", ErrorCode.PASSWORD_MISMATCH);
            return;
        }

        if (!userValidatorService.isPasswordLengthValid(newPassword)) {
            setErrorAndRedirect(request, response, "Controller?command=go_to_registration_page", ErrorCode.PASSWORD_LENGTH_INVALID);
            return;
        }

        if (!userValidatorService.isPasswordValid(newPassword)) {
            setErrorAndRedirect(request, response, "Controller?command=go_to_registration_page", ErrorCode.PASSWORD_INVALID);
            return;
        }

        try {

            userService.changeUserPassword(userId, newPassword, confirmPassword);
            response.sendRedirect("Controller?command=go_to_user_profile_page");

        } catch (ServiceException e) {

            setErrorAndRedirect(request, response, "Controller?command=go_to_user_profile_page", ErrorCode.PASSWORD_CHANGE);

        }

    }
}
