package edu.training.web.dao;

import edu.training.web.bean.AddArticleInfo;
import edu.training.web.bean.Article;
import edu.training.web.bean.News;

import java.util.List;

public interface NewsDao {
    List<News> getLastNews() throws DaoException;
    List<Article> getArticles() throws DaoException;

    void addArticle(AddArticleInfo addArticleInfo) throws DaoException;
}
