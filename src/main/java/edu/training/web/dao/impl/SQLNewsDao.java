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
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLNewsDao implements NewsDao {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final Logger LOGGER = Logger.getLogger(SQLNewsDao.class.getName());

    public SQLNewsDao() {
    }

    private static final String GET_LAST_NEWS_SQL = "SELECT id, imagePath, title, size FROM articles ORDER BY ID ASC";

    @Override
    public List<NewsTile> getTiles() throws DaoException {

        LOGGER.log(Level.INFO, "Method getTiles is called");

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

            LOGGER.log(Level.INFO, "Database error occurred during getting last news", e);
            throw new DaoException("Database error occurred during getting last news", e);

        }

        LOGGER.log(Level.INFO, "Method getTiles is finished");
        return lastNews;
    }

    private static final String GET_ALL_ARTICLES = "SELECT id, imagePath, title, text FROM articles ORDER BY ID ASC";

    @Override
    public List<Article> getArticles() throws DaoException {

        LOGGER.log(Level.INFO, "Method getArticles is called");

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

            LOGGER.log(Level.INFO, "Database error occurred during getting articles", e);
            throw new DaoException("Database error occurred during getting articles", e);

        }

        LOGGER.log(Level.INFO, "Method getArticles is finished");
        return articles;
    }

    private static final String GET_ARTICLE_BY_ID = "SELECT id, imagePath, title, text FROM articles WHERE id = ?";

    @Override
    public Article getArticleById(int articleId) throws DaoException {

        LOGGER.log(Level.INFO, "Method getArticleById is called");

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ARTICLE_BY_ID)) {

            statement.setInt(1, articleId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    int id = resultSet.getInt(1);
                    String title = resultSet.getString(2);
                    String imagePath = resultSet.getString(3);
                    String articleText = resultSet.getString(4);

                    LOGGER.log(Level.INFO, "Article {0} found", articleId);
                    return new Article(id, title, imagePath, articleText);
                }

            }
        } catch (SQLException | ConnectionPoolException e) {

            LOGGER.log(Level.INFO, "Database error occurred during getting article by id", e);
            throw new DaoException("Database error occurred during getting article by id", e);

        }

        LOGGER.log(Level.INFO, "No article found");
        return null;
    }


    private static final String INSERT_NEW_ARTICLE_SQL = "INSERT INTO articles (imagePath, title, text, size, Creator_id) VALUES (?, ?, ?, ?, ?)";

    @Override
    public void addArticle(AddArticleInfo addArticleInfo) throws DaoException {

        LOGGER.log(Level.INFO, "Method addArticle is called");

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_ARTICLE_SQL)) {

            statement.setString(1, addArticleInfo.getImage());
            statement.setString(2, addArticleInfo.getTitle());
            statement.setString(3, addArticleInfo.getArticleText());
            statement.setString(4, addArticleInfo.getTileSize());
            statement.setInt(5, 1);
            statement.executeUpdate();

            LOGGER.log(Level.INFO, "Article {0} successfully added", addArticleInfo.getTitle());

        } catch (SQLException | ConnectionPoolException e) {

            LOGGER.log(Level.INFO, "Database error occurred during adding article", e);
            throw new DaoException("Database error occurred during adding article", e);

        }
    }

    private static final String UPDATE_ARTICLE_SQL = "UPDATE articles SET title = ?, text = ?, size = ? WHERE id = ?";

    @Override
    public void editArticle(AddArticleInfo addArticleInfo, int articleId) throws DaoException {

        LOGGER.log(Level.INFO, "Method editArticle is called");

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ARTICLE_SQL)) {

            statement.setString(1, addArticleInfo.getTitle());
            statement.setString(2, addArticleInfo.getArticleText());
            statement.setString(3, addArticleInfo.getTileSize());
            statement.setInt(4, articleId);
            statement.executeUpdate();

            LOGGER.log(Level.INFO, "Article {0} successfully edited", articleId);

        } catch (SQLException | ConnectionPoolException e) {

            LOGGER.log(Level.INFO, "Database error occurred during editing article", e);
            throw new DaoException("Database error occurred during editing article", e);

        }
    }

    private static final String DELETE_ARTICLE_SQL = "DELETE FROM articles WHERE id = ?";

    @Override
    public void deleteArticle(int articleId) throws DaoException {

        LOGGER.log(Level.INFO, "Method deleteArticle is called");

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ARTICLE_SQL)) {

            statement.setInt(1, articleId);
            statement.executeUpdate();

            LOGGER.log(Level.INFO, "Article {0} successfully deleted", articleId);

        } catch (SQLException | ConnectionPoolException e) {

            LOGGER.log(Level.INFO, "Database error occurred during deleting article", e);
            throw new DaoException("Database error occurred during deleting article", e);

        }
    }
}
