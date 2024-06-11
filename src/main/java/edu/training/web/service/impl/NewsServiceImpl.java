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
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewsServiceImpl implements NewsService {

    private final NewsDao newsDao = DaoProvider.getInstance().getNewsDao();

    private static final Logger LOGGER = Logger.getLogger(NewsServiceImpl.class.getName());

    @Override
    public List<NewsTile> getLastNews() throws ServiceException {

        LOGGER.log(Level.INFO, "Method getLastNews is called");

        try {

            return newsDao.getTiles();

        } catch (DaoException e) {

            LOGGER.log(Level.INFO, "Service error occurred during getting last news", e);
            throw new ServiceException(e);

        }
    }

    @Override
    public List<Article> getArticles() throws ServiceException {

        LOGGER.log(Level.INFO, "Method getArticles is called");

        try {

            return newsDao.getArticles();

        } catch (DaoException e) {

            LOGGER.log(Level.INFO, "Service error occurred during getting articles", e);
            throw new ServiceException(e);

        }
    }

    @Override
    public Article getArticleById(int articleId) throws ServiceException {

        LOGGER.log(Level.INFO, "Method getArticleById is called");

        try {

            return newsDao.getArticleById(articleId);

        } catch (DaoException e) {

            LOGGER.log(Level.INFO, "Service error occurred during getting article by id", e);
            throw new ServiceException(e);

        }
    }

    @Override
    public void addArticle(AddArticleInfo addArticleInfo) throws ServiceException {

        LOGGER.log(Level.INFO, "Method addArticle is called");

        try {

            newsDao.addArticle(addArticleInfo);

        } catch (DaoException e) {

            LOGGER.log(Level.INFO, "Service error occurred during adding article", e);
            throw new ServiceException(e);

        }
    }

    @Override
    public void editArticle(AddArticleInfo addArticleInfo, int articleId) throws ServiceException {

        LOGGER.log(Level.INFO, "Method editArticle is called");

        try {

            newsDao.editArticle(addArticleInfo, articleId);

        } catch (DaoException e) {

            LOGGER.log(Level.INFO, "Service error occurred during editing article", e);
            throw new ServiceException(e);

        }
    }

    @Override
    public void deleteArticle(int articleId) throws ServiceException {

        LOGGER.log(Level.INFO, "Method deleteArticle is called");

        try {

            newsDao.deleteArticle(articleId);

        } catch (DaoException e) {

            LOGGER.log(Level.INFO, "Service error occurred during deleting article", e);
            throw new ServiceException(e);

        }
    }
}
