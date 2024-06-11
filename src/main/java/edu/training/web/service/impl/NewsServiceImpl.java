package edu.training.web.service.impl;

import edu.training.web.bean.AddArticleInfo;
import edu.training.web.bean.Article;
import edu.training.web.bean.NewsTile;
import edu.training.web.dao.DaoException;
import edu.training.web.dao.DaoProvider;
import edu.training.web.dao.NewsDao;
import edu.training.web.service.NewsService;
import edu.training.web.service.ServiceException;

import java.util.List;

public class NewsServiceImpl implements NewsService {

    private final NewsDao newsDao = DaoProvider.getInstance().getNewsDao();

    @Override
    public List<NewsTile> getLastNews() throws ServiceException {
        try {
            return newsDao.getTiles();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Article> getArticles() throws ServiceException {
        try {
            return newsDao.getArticles();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Article getArticleById(int articleId) throws ServiceException {
        try {
            return newsDao.getArticleById(articleId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addArticle(AddArticleInfo addArticleInfo) throws ServiceException {

        try {
            newsDao.addArticle(addArticleInfo);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void editArticle(AddArticleInfo addArticleInfo, int articleId) throws ServiceException {

        try {
            newsDao.editArticle(addArticleInfo, articleId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteArticle(int articleId) throws ServiceException {

        try {
            newsDao.deleteArticle(articleId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
