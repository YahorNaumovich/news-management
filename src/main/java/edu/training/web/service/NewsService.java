package edu.training.web.service;

import edu.training.web.bean.AddArticleInfo;
import edu.training.web.bean.Article;
import edu.training.web.bean.NewsTile;

import java.util.List;

public interface NewsService {
    List<NewsTile> lastNews() throws ServiceException;
    List<Article> articles() throws ServiceException;
    void addArticle(AddArticleInfo addArticleInfo) throws ServiceException;
    void editArticle(AddArticleInfo addArticleInfo, String articleId) throws ServiceException;
}
