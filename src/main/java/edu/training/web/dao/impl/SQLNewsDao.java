package edu.training.web.dao.impl;

import edu.training.web.bean.AddArticleInfo;
import edu.training.web.bean.Article;
import edu.training.web.bean.NewsTile;
import edu.training.web.dao.DaoException;
import edu.training.web.dao.NewsDao;
import edu.training.web.dao.SQLBaseDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SQLNewsDao extends SQLBaseDao implements NewsDao {

    public SQLNewsDao() {
    }

    @Override
    public List<NewsTile> getLastNews() throws DaoException {

        List<NewsTile> lastNews = new ArrayList<>();

        String sql = "SELECT * FROM tiles ORDER BY ID ASC";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
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
        } catch (SQLException e) {
            throw new DaoException("Database error occurred during getting last news", e);
        }
        return lastNews;
    }

    @Override
    public List<Article> getArticles() throws DaoException {

        List<Article> articles = new ArrayList<>();

        String sql = "SELECT * FROM articles ORDER BY ID ASC";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    String id = resultSet.getString(1);
                    String title = resultSet.getString(2);
                    String imagePath = resultSet.getString(3);
                    String articleText = resultSet.getString(4);

                    articles.add(new Article(id, title, imagePath, articleText));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Database error occurred during getting articles", e);
        }

        return articles;
    }


    @Override
    public Article getArticleById(String articleId) throws DaoException {

        String sql = "SELECT * FROM articles WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

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
        } catch (SQLException e) {
            throw new DaoException("Database error occurred during getting article by id", e);
        }

        return null;
    }

    @Override
    public void addArticle(AddArticleInfo addArticleInfo) throws DaoException {

        Random random = new Random();
        int tileId = random.nextInt();
        String uniqueId = UUID.randomUUID().toString();

        String insertNewTileSql = "INSERT INTO tiles (id, imagePath, title, source, size, Articles_id, Creator_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String insertNewArticleSql = "INSERT INTO articles (id, title, imagePath, text) VALUES (?, ?, ?, ?)";

        try (PreparedStatement newsTileStatement = connection.prepareStatement(insertNewTileSql);
             PreparedStatement articleStatement = connection.prepareStatement(insertNewArticleSql)
        ) {

            connection.setAutoCommit(false);

            articleStatement.setString(1, uniqueId);
            articleStatement.setString(2, addArticleInfo.getTitle());
            articleStatement.setString(3, "images/img1.jpg");
            articleStatement.setString(4, addArticleInfo.getArticleText());
            articleStatement.executeUpdate();

            newsTileStatement.setInt(1, tileId);
            newsTileStatement.setString(2, "images/img1.jpg");
            newsTileStatement.setString(3, addArticleInfo.getTitle());
            newsTileStatement.setString(4, "Owned");
            newsTileStatement.setString(5, addArticleInfo.getTileSize());
            newsTileStatement.setString(6, uniqueId);
            newsTileStatement.setInt(7, 1);
            newsTileStatement.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DaoException("Database error occurred during adding article", e);
        } finally {

            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void editArticle(AddArticleInfo addArticleInfo, String articleId) throws DaoException {

        String updateArticleSql = "UPDATE articles SET title = ?, text = ? WHERE id = ?";
        String updateNewsTileSql = "UPDATE tiles SET title = ?, size = ? WHERE Articles_id = ?";

        try (PreparedStatement articleStatement = connection.prepareStatement(updateArticleSql);
             PreparedStatement newsTileStatement = connection.prepareStatement(updateNewsTileSql)
        ) {
            connection.setAutoCommit(false);

            articleStatement.setString(1, addArticleInfo.getTitle());
            articleStatement.setString(2, addArticleInfo.getArticleText());
            articleStatement.setString(3, articleId);
            articleStatement.executeUpdate();

            newsTileStatement.setString(1, addArticleInfo.getTitle());
            newsTileStatement.setString(2, addArticleInfo.getTileSize());
            newsTileStatement.setString(3, articleId);
            newsTileStatement.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            // Rollback transaction on error
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                // Handle rollback exception
                ex.printStackTrace();
            }
            throw new DaoException("Database error occurred during editing article", e);
        } finally {
            // Reset auto-commit to true
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                // Handle exception
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteArticle(String articleId) throws DaoException {

        String deleteNewsTileSql = "DELETE FROM tiles WHERE Articles_id = ?";
        String deleteArticleSql = "DELETE FROM articles WHERE id = ?";

        try (PreparedStatement newsTileStatement = connection.prepareStatement(deleteNewsTileSql);
             PreparedStatement articleStatement = connection.prepareStatement(deleteArticleSql)
        ) {
            connection.setAutoCommit(false);

            // Delete the news tile
            newsTileStatement.setString(1, articleId);
            newsTileStatement.executeUpdate();

            // Delete the article
            articleStatement.setString(1, articleId);
            articleStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            // Rollback transaction on error
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                // Handle rollback exception
                ex.printStackTrace();
            }
            throw new DaoException("Database error occurred during deleting article", e);
        } finally {
            // Reset auto-commit to true
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                // Handle exception
                e.printStackTrace();
            }
        }

    }
}
