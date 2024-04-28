package edu.training.web.service.impl;

import edu.training.web.bean.News;
import edu.training.web.service.NewsService;

import java.util.ArrayList;
import java.util.List;

public class NewsServiceImpl implements NewsService {
    @Override
    public List<News> lastNews() {
        List<News> news = new ArrayList<News>();

        news.add(new News("images/img1.jpg", "Article 1", "Owned"));
        news.add(new News("images/img1.jpg", "Article 2", "Source 1", "r2c1"));
        news.add(new News("images/img3.jpg", "Article 3", "Owned", "r2c2"));
        news.add(new News("images/img2.jpg", "Article 5", "Owned"));
        news.add(new News("images/img1.jpg", "Article 4", "Source 2", "r1c2"));
        news.add(new News("images/img1.jpg", "Article 6", "Owned"));
        news.add(new News("images/img1.jpg", "Article 7", "Owned"));

        return news;
    }
}
