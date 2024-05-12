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

public class DoAddArticle implements Command {

    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        String articleText = request.getParameter("articleText");
        String tileSize = request.getParameter("tileSize");

        try {
            newsService.addArticle(new AddArticleInfo(title, articleText, null, tileSize));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("Controller?command=go_to_index_page");
    }
}
