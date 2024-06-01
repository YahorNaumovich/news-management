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

    List<NewsTile> newsTiles = new ArrayList<NewsTile>();
    List<Article> articles = new ArrayList<Article>();

    public SQLNewsDao() {
        newsTiles.add(new NewsTile(1, "images/img1.jpg", "Article 1", "Owned", "1"));
        newsTiles.add(new NewsTile(2, "images/img1.jpg", "Article 2", "Source 1", "2", "r2c1"));
        newsTiles.add(new NewsTile(3, "images/img3.jpg", "Article 3", "Owned", "3", "r2c2"));
        newsTiles.add(new NewsTile(4, "images/img2.jpg", "Article 5", "Owned", "4"));
        newsTiles.add(new NewsTile(5, "images/img1.jpg", "Article 4", "Source 2", "5", "r1c2"));
        newsTiles.add(new NewsTile(6, "images/img1.jpg", "Article 6", "Owned", "6"));
        newsTiles.add(new NewsTile(7, "images/img1.jpg", "Article 7", "Owned", "7"));

        articles.add(new Article("1", "Article 1", "images/img1.jpg", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."));
        articles.add(new Article("2", "Article 2", "images/img1.jpg", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."));
        articles.add(new Article("3", "Article 3", "images/img3.jpg", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."));
        articles.add(new Article("4", "Article 4", "images/img2.jpg", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."));
        articles.add(new Article("5", "Article 5", "images/img1.jpg", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."));
        articles.add(new Article("6", "Article 6", "images/img1.jpg", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."));
        articles.add(new Article("7", "Article 7", "images/img1.jpg", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."));

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

        newsTiles.removeIf(newsTile -> newsTile.getArticleId().equals(articleId));
        articles.removeIf(article -> article.getId().equals(articleId));
    }
}
