package edu.training.web.bean;

import java.io.Serializable;
import java.util.Objects;

public class NewsTile implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String imgPath;

    private String title;

    private String tileSize;


    public NewsTile() {
    }

    public NewsTile(Integer id, String imgPath, String title) {
        super();
        this.id = id;
        this.imgPath = imgPath;
        this.title = title;
    }

    public NewsTile(Integer id, String imgPath, String title, String tileSize) {
        super();
        this.id = id;
        this.imgPath = imgPath;
        this.title = title;
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
        NewsTile newsTile = (NewsTile) o;
        return Objects.equals(id, newsTile.id) && Objects.equals(imgPath, newsTile.imgPath) && Objects.equals(title, newsTile.title) && Objects.equals(tileSize, newsTile.tileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imgPath, title, tileSize);
    }

    @Override
    public String toString() {
        return "NewsTile{" +
                "id=" + id +
                ", imgPath='" + imgPath + '\'' +
                ", title='" + title + '\'' +
                ", tileSize='" + tileSize + '\'' +
                '}';
    }
}
