package edu.training.web.controller.concrete.impl;

import edu.training.web.controller.ErrorCode;
import edu.training.web.controller.concrete.AbstractCommand;
import edu.training.web.controller.concrete.Command;
import edu.training.web.service.NewsService;
import edu.training.web.service.ServiceException;
import edu.training.web.service.ServiceProvider;
import edu.training.web.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoDeleteUser extends AbstractCommand {

    private final UserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("userId"));

        try {

            userService.deleteUser(userId);

        } catch (ServiceException e) {
            setErrorAndRedirect(request, response, "Controller?command=go_to_manage_users_page", ErrorCode.USER_DELETE);
        }

        response.sendRedirect("Controller?command=go_to_manage_users_page");
    }
}
