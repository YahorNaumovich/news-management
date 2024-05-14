package edu.training.web.controller.concrete.impl;

import edu.training.web.bean.User;
import edu.training.web.bean.UserRegistrationInfo;
import edu.training.web.controller.concrete.Command;
import edu.training.web.service.ServiceException;
import edu.training.web.service.ServiceProvider;
import edu.training.web.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class DoRegistration implements Command {
    private final UserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("confirmPassword");

        System.out.println("Performed user authentication and authorization. Login: " + login);
        try {
            User user = userService.signUp(new UserRegistrationInfo(login, email, password, passwordConfirm));

            if (user != null) {

                HttpSession session = (HttpSession) request.getSession(true);
                session.setAttribute("user", user);

                response.sendRedirect("Controller?command=go_to_index_page");

            } else {
                response.sendRedirect("Controller?command=go_to_registration_page&authError=Passwords do not match");
            }
        } catch (ServiceException e) {
            response.sendRedirect("Controller?command=go_to_registration_page&authError=" + e.getMessage() + "\"");
        }
    }

}
