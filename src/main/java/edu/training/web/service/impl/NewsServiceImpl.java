package edu.training.web.service.impl;

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
    public void addArticle() {
        news.add(0, new News(8, "images/img1.jpg", "Article 8", "Owned", "8","r2c2"));
        articles.add(0, new Article("8", "Article 8", "images/img1.jpg", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."));
    }
}
