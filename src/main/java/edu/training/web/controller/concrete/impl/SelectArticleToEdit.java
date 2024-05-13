package edu.training.web.controller.concrete.impl;

import edu.training.web.controller.concrete.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class SelectArticleToEdit implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Object isInEditModeAttribute = session.getAttribute("isInEditMode");

        boolean isInEditMode;

        if (isInEditModeAttribute == null) {
            isInEditMode = false;
        } else {
            isInEditMode = (boolean) isInEditModeAttribute;
        }

        session.setAttribute("isInDeleteMode", false);
        session.setAttribute("isInEditMode", !isInEditMode);
        response.sendRedirect("Controller?command=go_to_index_page");

    }
}
