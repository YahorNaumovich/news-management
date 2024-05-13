package edu.training.web.controller.concrete.impl;

import edu.training.web.controller.concrete.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class SelectArticleToDelete implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Object isInDeleteModeAttribute = session.getAttribute("isInDeleteMode");

        boolean isInDeleteMode;

        if (isInDeleteModeAttribute == null) {
            isInDeleteMode = false;
        } else {
            isInDeleteMode = (boolean) isInDeleteModeAttribute;
        }

        session.setAttribute("isInEditMode", false);
        session.setAttribute("isInDeleteMode", !isInDeleteMode);
        response.sendRedirect("Controller?command=go_to_index_page");
    }
}
