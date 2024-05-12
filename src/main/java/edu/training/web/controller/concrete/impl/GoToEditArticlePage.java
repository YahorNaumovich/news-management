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
        System.out.println("Article id associated with this tile is: " + articleId);

        List<Article> articles = null;
        try {
            articles = newsService.articles();
        } catch (ServiceException e) {
            response.sendRedirect("Controller?command=go_to_index_page");
        }

        for (Article article : articles) {
            if (article.getId().equals(articleId)) {
                request.setAttribute("article", article);
                System.out.println("Article attribute is set");
                request.getRequestDispatcher("WEB-INF/jsp/edit_article.jsp").forward(request, response);
            }
        }
    }
}
