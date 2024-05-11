package edu.training.web.service;

import edu.training.web.bean.Article;
import edu.training.web.bean.News;

import java.util.List;

public interface NewsService {
    List<News> lastNews() throws ServiceException;
    List<Article> articles() throws ServiceException;
    void addArticle();
}
