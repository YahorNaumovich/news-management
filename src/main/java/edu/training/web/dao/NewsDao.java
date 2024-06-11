package edu.training.web.dao;

import edu.training.web.bean.AddArticleInfo;
import edu.training.web.bean.Article;
import edu.training.web.bean.NewsTile;

import java.util.List;

public interface NewsDao {
    List<NewsTile> getTiles() throws DaoException;
    List<Article> getArticles() throws DaoException;

    Article getArticleById(int articleId) throws DaoException;
    void addArticle(AddArticleInfo addArticleInfo) throws DaoException;
    void editArticle(AddArticleInfo addArticleInfo, int articleId) throws DaoException;
    void deleteArticle(int articleId) throws DaoException;
}
