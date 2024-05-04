package edu.training.web.bean;

import java.io.Serializable;
import java.util.Objects;

public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;

    private String title;

    private String imagePath;

    private String text;

    public Article() {
    }

    public Article(String id, String title, String imagePath, String text) {
        this.id = id;
        this.title = title;
        this.imagePath = imagePath;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
        return Objects.equals(id, article.id) && Objects.equals(title, article.title) && Objects.equals(imagePath, article.imagePath) && Objects.equals(text, article.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, imagePath, text);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}