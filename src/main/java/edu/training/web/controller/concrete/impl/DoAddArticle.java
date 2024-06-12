package edu.training.web.controller.concrete.impl;

import edu.training.web.bean.AddArticleInfo;
import edu.training.web.controller.ErrorCode;
import edu.training.web.controller.concrete.AbstractCommand;
import edu.training.web.controller.concrete.Command;
import edu.training.web.service.NewsService;
import edu.training.web.service.ServiceException;
import edu.training.web.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class DoAddArticle extends AbstractCommand {

    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        String articleText = request.getParameter("articleText");
        String tileSize = request.getParameter("tileSize");

        String imagePath;

        try {

            Part filePart = request.getPart("image");
            String fileName = getFileName(filePart);
            imagePath = "images/" + fileName;

            filePart.write(request.getServletContext().getRealPath("") + File.separator + imagePath);

            newsService.addArticle(new AddArticleInfo(title, articleText, imagePath, tileSize));
            response.sendRedirect("Controller?command=go_to_index_page");

        } catch (IOException | ServletException | ServiceException e) {
            setErrorAndRedirect(request, response, "Controller?command=go_to_index_page", ErrorCode.ARTICLE_ADD);
        }
    }

    // Method to extract file name from Part
    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
