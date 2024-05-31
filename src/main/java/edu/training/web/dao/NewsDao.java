package edu.training.web.dao;

import edu.training.web.bean.AddArticleInfo;
import edu.training.web.bean.Article;
import edu.training.web.bean.NewsTile;

import java.util.List;

public interface NewsDao {
    List<NewsTile> getLastNews() throws DaoException;
    List<Article> getArticles() throws DaoException;

    Article getArticleById(String articleId) throws DaoException;
    void addArticle(AddArticleInfo addArticleInfo) throws DaoException;
    void editArticle(AddArticleInfo addArticleInfo, String articleId) throws DaoException;
    void deleteArticle(String articleId) throws DaoException;
}
