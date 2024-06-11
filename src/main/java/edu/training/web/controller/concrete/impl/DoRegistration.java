package edu.training.web.controller.concrete.impl;

import edu.training.web.bean.User;
import edu.training.web.bean.UserRegistrationInfo;
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

public class DoRegistration implements Command {
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
                request.getSession().setAttribute("errorMessage", "error.user.passwordMismatch");
                response.sendRedirect("Controller?command=go_to_registration_page");
                return;
            }

            if (userValidatorService.doesUserExist(email)) {
                request.getSession().setAttribute("errorMessage", "error.user.userAlreadyExists");
                response.sendRedirect("Controller?command=go_to_registration_page");
                return;
            }


            User user = userService.signUp(new UserRegistrationInfo(login, email, password, passwordConfirm));

            if (user != null) {

                HttpSession session = (HttpSession) request.getSession(true);
                session.setAttribute("user", user);

                response.sendRedirect("Controller?command=go_to_index_page");

            }

        } catch (ServiceException e) {

            request.getSession().setAttribute("errorMessage", "error.user.registration");
            response.sendRedirect("Controller?command=go_to_registration_page");

        }
    }

}
