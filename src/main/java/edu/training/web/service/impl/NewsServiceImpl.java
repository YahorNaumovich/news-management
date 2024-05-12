package edu.training.web.service.impl;

import edu.training.web.bean.AddArticleInfo;
import edu.training.web.bean.Article;
import edu.training.web.bean.News;
import edu.training.web.dao.AuthenticationDao;
import edu.training.web.dao.DaoException;
import edu.training.web.dao.DaoProvider;
import edu.training.web.dao.NewsDao;
import edu.training.web.service.NewsService;
import edu.training.web.service.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class NewsServiceImpl implements NewsService {

    List<News> news = new ArrayList<News>();
    List<Article> articles = new ArrayList<Article>();

    private NewsDao newsDao = DaoProvider.getInstance().getNewsDao();

    @Override
    public List<News> lastNews() throws ServiceException {
        try {
            return newsDao.getLastNews();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Article> articles() throws ServiceException {
        try {
            return newsDao.getArticles();
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
}
