package edu.training.web.controller.concrete.impl;

import edu.training.web.bean.AddArticleInfo;
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

public class DoEditArticle implements Command {

    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        String articleText = request.getParameter("articleText");
        String tileSize = request.getParameter("tileSize");

        int articleId = Integer.parseInt(request.getParameter("articleId"));

        try {

            newsService.editArticle(new AddArticleInfo(title, articleText, null, tileSize), articleId);
            response.sendRedirect("Controller?command=go_to_index_page");

        } catch (ServiceException e) {
            handleError(response, "Error editing article: " + e.getMessage());
        } catch (Exception e) {
            handleError(response, "Unexpected error: " + e.getMessage());
        }
    }

    private void handleError(HttpServletResponse response, String errorMessage) throws IOException {
        String encodedMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        response.sendRedirect("Controller?command=go_to_index_page&errorMessage=" + encodedMessage);
    }

}
