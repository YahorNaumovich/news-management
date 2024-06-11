package edu.training.web.dao.impl;

import edu.training.web.bean.AddArticleInfo;
import edu.training.web.bean.Article;
import edu.training.web.bean.NewsTile;
import edu.training.web.dao.*;
import edu.training.web.dao.connectionpool.ConnectionPool;
import edu.training.web.dao.connectionpool.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SQLNewsDao implements NewsDao {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public SQLNewsDao() {
    }

    private static final String GET_LAST_NEWS_SQL = "SELECT id, imagePath, title, size FROM articles ORDER BY ID ASC";

    @Override
    public List<NewsTile> getTiles() throws DaoException {

        List<NewsTile> lastNews = new ArrayList<>();

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_LAST_NEWS_SQL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    Integer id = resultSet.getInt(1);
                    String imagePath = resultSet.getString(2);
                    String title = resultSet.getString(3);
                    String size = resultSet.getString(4);

                    lastNews.add(new NewsTile(id, imagePath, title, size));

                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Database error occurred during getting last news", e);
        }
        return lastNews;
    }

    private static final String GET_ALL_ARTICLES = "SELECT id, imagePath, title, text FROM articles ORDER BY ID ASC";

    @Override
    public List<Article> getArticles() throws DaoException {

        List<Article> articles = new ArrayList<>();

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_ARTICLES)) {
            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    int id = resultSet.getInt(1);
                    String imagePath = resultSet.getString(2);
                    String title = resultSet.getString(3);
                    String articleText = resultSet.getString(4);

                    articles.add(new Article(id, title, imagePath, articleText));
                }

            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Database error occurred during getting articles", e);
        }

        return articles;
    }

    private static final String GET_ARTICLE_BY_ID = "SELECT id, imagePath, title, text FROM articles WHERE id = ?";

    @Override
    public Article getArticleById(int articleId) throws DaoException {

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ARTICLE_BY_ID)) {

            statement.setInt(1, articleId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    int id = resultSet.getInt(1);
                    String title = resultSet.getString(2);
                    String imagePath = resultSet.getString(3);
                    String articleText = resultSet.getString(4);

                    return new Article(id, title, imagePath, articleText);
                }

            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Database error occurred during getting article by id", e);
        }

        return null;
    }


    private static final String INSERT_NEW_ARTICLE_SQL = "INSERT INTO articles (imagePath, title, text, size, Creator_id) VALUES (?, ?, ?, ?, ?)";

    @Override
    public void addArticle(AddArticleInfo addArticleInfo) throws DaoException {

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_ARTICLE_SQL)) {

            statement.setString(1, addArticleInfo.getImage());
            statement.setString(2, addArticleInfo.getTitle());
            statement.setString(3, addArticleInfo.getArticleText());
            statement.setString(4, addArticleInfo.getTileSize());
            statement.setInt(5, 1);
            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Database error occurred during adding article", e);
        }
    }

    private static final String UPDATE_ARTICLE_SQL = "UPDATE articles SET title = ?, text = ?, size = ? WHERE id = ?";

    @Override
    public void editArticle(AddArticleInfo addArticleInfo, int articleId) throws DaoException {

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ARTICLE_SQL)) {

            statement.setString(1, addArticleInfo.getTitle());
            statement.setString(2, addArticleInfo.getArticleText());
            statement.setString(3, addArticleInfo.getTileSize());
            statement.setInt(4, articleId);
            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Database error occurred during editing article", e);
        }
    }

    private static final String DELETE_ARTICLE_SQL = "DELETE FROM articles WHERE id = ?";

    @Override
    public void deleteArticle(int articleId) throws DaoException {

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ARTICLE_SQL)) {

            statement.setInt(1, articleId);
            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Database error occurred during deleting article", e);
        }
    }
}
