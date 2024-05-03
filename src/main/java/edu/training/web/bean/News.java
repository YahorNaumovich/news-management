package edu.training.web.bean;

import java.io.Serializable;
import java.util.Objects;

public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String imgPath;

    private String title;

    private String source;

    private String tileSize;

    public News() {
    }

    public News(Integer id, String imgPath, String title, String source) {
        super();
        this.id = id;
        this.imgPath = imgPath;
        this.title = title;
        this.source = source;
    }

    public News(Integer id, String imgPath, String title, String source, String tileSize) {
        super();
        this.id = id;
        this.imgPath = imgPath;
        this.title = title;
        this.source = source;
        this.tileSize = tileSize;
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
        News news = (News) o;
        return Objects.equals(imgPath, news.imgPath) && Objects.equals(title, news.title) && Objects.equals(source, news.source) && Objects.equals(tileSize, news.tileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imgPath, title, source, tileSize);
    }

}
