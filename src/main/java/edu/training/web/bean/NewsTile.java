package edu.training.web.bean;

import java.io.Serializable;
import java.util.Objects;

public class NewsTile implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String imgPath;

    private String title;

    private String source;

    private String tileSize;

    private String articleId;

    public NewsTile() {
    }

    public NewsTile(Integer id, String imgPath, String title, String source, String articleId) {
        super();
        this.id = id;
        this.imgPath = imgPath;
        this.title = title;
        this.source = source;
        this.articleId = articleId;
    }

    public NewsTile(Integer id, String imgPath, String title, String source, String articleId, String tileSize) {
        super();
        this.id = id;
        this.imgPath = imgPath;
        this.title = title;
        this.source = source;
        this.tileSize = tileSize;
        this.articleId = articleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTileSize() {
        return tileSize;
    }

    public void setTileSize(String tileSize) {
        this.tileSize = tileSize;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "News{" +
                "imgPath='" + imgPath + '\'' +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsTile newsTile = (NewsTile) o;
        return Objects.equals(imgPath, newsTile.imgPath) && Objects.equals(title, newsTile.title) && Objects.equals(source, newsTile.source) && Objects.equals(tileSize, newsTile.tileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imgPath, title, source, tileSize);
    }
}