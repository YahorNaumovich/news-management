package edu.training.web.controller.concrete.impl;

import edu.training.web.bean.AuthenticationInfo;
import edu.training.web.bean.User;
import edu.training.web.controller.ErrorCode;
import edu.training.web.controller.concrete.AbstractCommand;
import edu.training.web.controller.concrete.Command;
import edu.training.web.service.ServiceException;
import edu.training.web.service.ServiceProvider;
import edu.training.web.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class DoAuthentication extends AbstractCommand {
    private final UserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user;

        try {

            user = userService.signIn(new AuthenticationInfo(login, password));

            if (user != null) {

                HttpSession session = (HttpSession) request.getSession(true);
                session.setAttribute("user", user);

                response.sendRedirect("Controller?command=go_to_index_page");

            }

        } catch (ServiceException e) {
            System.out.println(ErrorCode.USER_AUTHENTICATION.getCode());
            setErrorAndRedirect(request, response, "Controller?command=go_to_login_page", ErrorCode.USER_AUTHENTICATION);
        }

    }

}
