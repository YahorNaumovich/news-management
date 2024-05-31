package edu.training.web.controller.concrete.impl;

import edu.training.web.bean.NewsTile;
import edu.training.web.controller.concrete.Command;
import edu.training.web.service.NewsService;
import edu.training.web.service.ServiceException;
import edu.training.web.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class GoToIndexPage implements Command {

    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<NewsTile> mainNews = null;
        try {
            mainNews = newsService.lastNews();
            request.setAttribute("mainNews", mainNews);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main_index.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            // Log the error for debugging
            e.printStackTrace();

            // Redirect to an error page or handle the error appropriately
            request.setAttribute("errorMessage", "An error occurred while retrieving the news.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/error.jsp");
            dispatcher.forward(request, response);
        }

    }
}
