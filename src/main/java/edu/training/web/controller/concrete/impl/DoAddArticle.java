package edu.training.web.controller.concrete.impl;

import edu.training.web.bean.AddArticleInfo;
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

public class DoAddArticle implements Command {

    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        String articleText = request.getParameter("articleText");
        String tileSize = request.getParameter("tileSize");

        String imagePath = null;

        try {
            // File upload handling
            Part filePart = request.getPart("image");
            String fileName = getFileName(filePart);
            imagePath = "images/" + fileName; // Adjust this path as needed

            // Write the file to the server
            filePart.write(request.getServletContext().getRealPath("") + File.separator + imagePath);
        } catch (IOException | ServletException e) {
            throw new RuntimeException("Error uploading file", e);
        }

        try {
            newsService.addArticle(new AddArticleInfo(title, articleText, imagePath, tileSize));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("Controller?command=go_to_index_page");
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
