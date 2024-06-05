package edu.training.web.controller.concrete.impl;

import edu.training.web.bean.Article;
import edu.training.web.controller.concrete.Command;
import edu.training.web.service.NewsService;
import edu.training.web.service.ServiceException;
import edu.training.web.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GoToArticlePage implements Command {

    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String articleId = request.getParameter("articleId");
        System.out.println("Article id associated with this tile is: " + articleId);

        Article article = null;

        try {
            article = newsService.getArticleById(articleId);
            request.setAttribute("article", article);
            request.getRequestDispatcher("WEB-INF/jsp/article.jsp").forward(request, response);
        } catch (ServiceException e) {
            handleError(response, "Error retrieving article: " + e.getMessage());
        } catch (Exception e) {
            handleError(response, "Unexpected error: " + e.getMessage());
        }

    }

    private void handleError(HttpServletResponse response, String errorMessage) throws IOException {
        String encodedMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        response.sendRedirect("Controller?command=go_to_index_page&errorMessage=" + encodedMessage);
    }
}
