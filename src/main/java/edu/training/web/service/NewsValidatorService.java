package edu.training.web.service;

public interface NewsValidatorService {

    boolean validateArticleImageNotEmpty(String imagePath);

    boolean validateArticleTitleLength(String title);

    boolean validateArticleTextLength(String articleText);

}
