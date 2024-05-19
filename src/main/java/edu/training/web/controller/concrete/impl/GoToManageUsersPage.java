package edu.training.web.controller.concrete.impl;

import edu.training.web.controller.concrete.Command;
import edu.training.web.service.ServiceException;
import edu.training.web.service.ServiceProvider;
import edu.training.web.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToManageUsersPage implements Command {

    private final UserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            request.setAttribute("users", userService.getAllUsers());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("WEB-INF/jsp/manage_users.jsp").forward(request, response);
    }
}
