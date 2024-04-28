package edu.training.web.controller.concrete.impl;

import edu.training.web.controller.concrete.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToMainPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("invitationMessage", "Hello, there!");

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
        dispatcher.forward(request, response);
    }
}
