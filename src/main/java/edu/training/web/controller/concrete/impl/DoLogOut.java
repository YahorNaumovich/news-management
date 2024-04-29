package edu.training.web.controller.concrete.impl;

import edu.training.web.bean.User;
import edu.training.web.controller.concrete.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class DoLogOut implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = null;

        HttpSession session = (HttpSession) request.getSession(true);
        session.setAttribute("user", user);

        response.sendRedirect("Controller?command=go_to_index_page");
    }
}
