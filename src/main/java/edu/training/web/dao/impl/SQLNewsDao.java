package edu.training.web.dao.impl;

import edu.training.web.bean.AddArticleInfo;
import edu.training.web.bean.Article;
import edu.training.web.bean.NewsTile;
import edu.training.web.dao.DaoException;
import edu.training.web.dao.NewsDao;

import java.util.*;

public class SQLNewsDao implements NewsDao {

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
        return newsTiles;
    }

    @Override
    public List<Article> getArticles() throws DaoException {
        return articles;
    }

    @Override
    public void addArticle(AddArticleInfo addArticleInfo) throws DaoException {

        Random random = new Random();
        Integer tileId = random.nextInt();
        String uniqueId = UUID.randomUUID().toString();

        newsTiles.add(0, new NewsTile(tileId, "images/img1.jpg", addArticleInfo.getTitle(), "Owned", uniqueId, addArticleInfo.getTileSize()));
        articles.add(0, new Article(uniqueId, addArticleInfo.getTitle(), "images/img1.jpg", addArticleInfo.getArticleText()));
    }

    @Override
    public void editArticle(AddArticleInfo addArticleInfo, String articleId) throws DaoException {
        System.out.println(articleId);
        NewsTile tileToEdit = null;
        Article articleToEdit = null;

        for (NewsTile newsTile : newsTiles) {
            if (newsTile.getArticleId().equals(articleId)) {
                tileToEdit = newsTile;
            }
        }

        for (Article article : articles) {
            if (article.getId().equals(articleId)) {
                articleToEdit = article;
            }
        }

        newsTiles.set(newsTiles.indexOf(tileToEdit), new NewsTile(tileToEdit.getId(), tileToEdit.getImgPath(), addArticleInfo.getTitle(), "Owned", articleId, addArticleInfo.getTileSize()));
        articles.set(articles.indexOf(articleToEdit), new Article(articleToEdit.getId(), addArticleInfo.getTitle(), tileToEdit.getImgPath(), addArticleInfo.getArticleText()));
    }
}
