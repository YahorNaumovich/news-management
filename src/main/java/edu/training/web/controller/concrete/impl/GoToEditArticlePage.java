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
import java.util.List;

public class GoToEditArticlePage implements Command {

    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String articleId = request.getParameter("articleId");

        Article article = null;

        try {
            article = newsService.getArticleById(articleId);
        } catch (ServiceException e) {
            response.sendRedirect("Controller?command=go_to_index_page");
        }

        request.setAttribute("article", article);
        request.setAttribute("articleId", articleId);
        request.getRequestDispatcher("WEB-INF/jsp/edit_article.jsp").forward(request, response);
    }
}
