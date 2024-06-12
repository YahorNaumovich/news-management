package edu.training.web.controller.concrete;

import edu.training.web.controller.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class AbstractCommand implements Command {

    public void setErrorAndForward(HttpServletRequest request, HttpServletResponse response, String forwardResource, ErrorCode errorCode) throws ServletException, IOException {
        request.setAttribute("errorMessage", errorCode.getCode());
        request.getRequestDispatcher(forwardResource).forward(request, response);
    }

    public void setErrorAndRedirect(HttpServletRequest request, HttpServletResponse response, String redirectCommand, ErrorCode errorCode) throws IOException {
        request.getSession().setAttribute("errorMessage", errorCode.getCode());
        response.sendRedirect(redirectCommand);
    }

}
