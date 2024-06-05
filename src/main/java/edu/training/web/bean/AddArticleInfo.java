package edu.training.web.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class AddArticleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String articleText;
    private String image;
    private String tileSize;

    public AddArticleInfo() {
    }

    public AddArticleInfo(String title, String articleText, String image, String tileSize) {
        this.title = title;
        this.articleText = articleText;
        this.image = image;
        this.tileSize = tileSize;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTileSize() {
        return tileSize;
    }

    public void setTileSize(String tileSize) {
        this.tileSize = tileSize;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddArticleInfo that = (AddArticleInfo) o;
        return Objects.equals(title, that.title) && Objects.equals(articleText, that.articleText) && Objects.equals(image, that.image) && Objects.equals(tileSize, that.tileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, articleText, image, tileSize);
    }
}
