package edu.training.web.bean;

import java.io.Serializable;
import java.util.Objects;

public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String imagePath;

    private String title;

    private String text;

    public Article() {
    }


    public Article(int id, String imagePath, String title, String text) {
        this.id = id;
        this.imagePath = imagePath;
        this.title = title;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id == article.id && Objects.equals(imagePath, article.imagePath) && Objects.equals(title, article.title) && Objects.equals(text, article.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imagePath, title, text);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", imagePath='" + imagePath + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
