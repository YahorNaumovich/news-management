package edu.training.web.bean;

import java.util.Objects;

public class Article {

    private Integer id;

    private String title;

    private String imagePath;

    private String text;

    public Article() {
    }

    public Article(Integer id, String title, String imagePath, String text) {
        this.id = id;
        this.title = title;
        this.imagePath = imagePath;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
