package edu.training.web.controller.concrete.impl;

import edu.training.web.controller.ErrorCode;
import edu.training.web.controller.concrete.AbstractCommand;
import edu.training.web.controller.concrete.Command;
import edu.training.web.service.ServiceException;
import edu.training.web.service.ServiceProvider;
import edu.training.web.service.UserRoles;
import edu.training.web.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class DoChangeUserRole extends AbstractCommand {

    private final UserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String userRole = request.getParameter("role");

        int id = Integer.parseInt(userId);
        UserRoles role = UserRoles.valueOf(userRole);

        try {

            userService.changeUserRole(id, role);
            response.sendRedirect("Controller?command=go_to_manage_users_page");

        } catch (ServiceException e) {

            setErrorAndRedirect(request, response, "Controller?command=go_to_manage_users_page", ErrorCode.ROLE_CHANGE);

        }

    }
}
