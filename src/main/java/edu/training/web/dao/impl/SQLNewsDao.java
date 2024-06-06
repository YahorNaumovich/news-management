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

    @Override
    public List<NewsTile> getLastNews() throws DaoException {

        List<NewsTile> lastNews = new ArrayList<>();

        String sql = "SELECT * FROM tiles ORDER BY ID ASC";

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    Integer id = resultSet.getInt(1);
                    String imagePath = resultSet.getString(2);
                    String title = resultSet.getString(3);
                    String source = resultSet.getString(4);
                    String size = resultSet.getString(5);
                    String articleId = resultSet.getString(6);

                    lastNews.add(new NewsTile(id, imagePath, title, source, articleId, size));

                }
            }
        } catch (SQLException |ConnectionPoolException e) {
            throw new DaoException("Database error occurred during getting last news", e);
        }
        return lastNews;
    }

    @Override
    public List<Article> getArticles() throws DaoException {

        List<Article> articles = new ArrayList<>();

        String sql = "SELECT * FROM articles ORDER BY ID ASC";

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    String id = resultSet.getString(1);
                    String title = resultSet.getString(2);
                    String imagePath = resultSet.getString(3);
                    String articleText = resultSet.getString(4);

                    articles.add(new Article(id, title, imagePath, articleText));
                }
            }
        } catch (SQLException |ConnectionPoolException e) {
            throw new DaoException("Database error occurred during getting articles", e);
        }

        return articles;
    }


    @Override
    public Article getArticleById(String articleId) throws DaoException {

        String sql = "SELECT * FROM articles WHERE id = ?";

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, articleId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    String id = resultSet.getString(1);
                    String title = resultSet.getString(2);
                    String imagePath = resultSet.getString(3);
                    String articleText = resultSet.getString(4);

                    return new Article(id, title, imagePath, articleText);
                }
            }
        } catch (SQLException |ConnectionPoolException e) {
            throw new DaoException("Database error occurred during getting article by id", e);
        }

        return null;
    }

    @Override
    public void addArticle(AddArticleInfo addArticleInfo) throws DaoException {
        String uniqueId = UUID.randomUUID().toString();

        String insertNewTileSql = "INSERT INTO tiles (imagePath, title, source, size, Articles_id, Creator_id) VALUES (?, ?, ?, ?, ?, ?)";
        String insertNewArticleSql = "INSERT INTO articles (id, title, imagePath, text) VALUES (?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement newsTileStatement = null;
        PreparedStatement articleStatement = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);

            articleStatement = connection.prepareStatement(insertNewArticleSql);
            articleStatement.setString(1, uniqueId);
            articleStatement.setString(2, addArticleInfo.getTitle());
            articleStatement.setString(3, addArticleInfo.getImage());
            articleStatement.setString(4, addArticleInfo.getArticleText());
            articleStatement.executeUpdate();

            newsTileStatement = connection.prepareStatement(insertNewTileSql);
            newsTileStatement.setString(1, addArticleInfo.getImage());
            newsTileStatement.setString(2, addArticleInfo.getTitle());
            newsTileStatement.setString(3, "Owned");
            newsTileStatement.setString(4, addArticleInfo.getTileSize());
            newsTileStatement.setString(5, uniqueId);
            newsTileStatement.setInt(6, 1);
            newsTileStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DaoException("Database error occurred during adding article", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Connection pool error occurred during adding article", e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ConnectionPool.getInstance().closeStatement(newsTileStatement);
                ConnectionPool.getInstance().closeStatement(articleStatement);
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
    }

    @Override
    public void editArticle(AddArticleInfo addArticleInfo, String articleId) throws DaoException {

        String updateArticleSql = "UPDATE articles SET title = ?, text = ? WHERE id = ?";
        String updateNewsTileSql = "UPDATE tiles SET title = ?, size = ? WHERE Articles_id = ?";

        Connection connection = null;
        PreparedStatement articleStatement = null;
        PreparedStatement newsTileStatement = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);

            articleStatement = connection.prepareStatement(updateArticleSql);
            articleStatement.setString(1, addArticleInfo.getTitle());
            articleStatement.setString(2, addArticleInfo.getArticleText());
            articleStatement.setString(3, articleId);
            articleStatement.executeUpdate();

            newsTileStatement = connection.prepareStatement(updateNewsTileSql);
            newsTileStatement.setString(1, addArticleInfo.getTitle());
            newsTileStatement.setString(2, addArticleInfo.getTileSize());
            newsTileStatement.setString(3, articleId);
            newsTileStatement.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new DaoException("Database error occurred during editing article", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Connection pool error occurred during editing article", e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ConnectionPool.getInstance().closeStatement(newsTileStatement);
                ConnectionPool.getInstance().closeStatement(articleStatement);
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
    }

    @Override
    public void deleteArticle(String articleId) throws DaoException {

        String deleteNewsTileSql = "DELETE FROM tiles WHERE Articles_id = ?";
        String deleteArticleSql = "DELETE FROM articles WHERE id = ?";

        Connection connection = null;
        PreparedStatement newsTileStatement = null;
        PreparedStatement articleStatement = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);

            newsTileStatement = connection.prepareStatement(deleteNewsTileSql);
            newsTileStatement.setString(1, articleId);
            newsTileStatement.executeUpdate();

            articleStatement = connection.prepareStatement(deleteArticleSql);
            articleStatement.setString(1, articleId);
            articleStatement.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new DaoException("Database error occurred during deleting article", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Connection pool error occurred during deleting article", e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ConnectionPool.getInstance().closeStatement(newsTileStatement);
                ConnectionPool.getInstance().closeStatement(articleStatement);
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
    }
}
