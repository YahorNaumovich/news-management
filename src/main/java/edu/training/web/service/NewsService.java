package edu.training.web.service;

import edu.training.web.bean.News;

import java.util.List;

public interface NewsService {
    List<News> lastNews();
}
