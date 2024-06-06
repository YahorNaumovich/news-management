package edu.training.web.controller.concrete.impl;

import edu.training.web.controller.concrete.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class SetLocale implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lang = request.getParameter("lang");
        Locale locale = new Locale(lang);
        request.getSession().setAttribute("locale", locale);
        // Redirect back to the original page
        String referer = request.getHeader("Referer");
        response.sendRedirect(Objects.requireNonNullElse(referer, "Controller?command=go_to_index_page"));
    }
}
