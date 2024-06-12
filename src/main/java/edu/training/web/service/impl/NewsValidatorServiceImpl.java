package edu.training.web.service.impl;

import edu.training.web.service.NewsValidatorService;

public class NewsValidatorServiceImpl implements NewsValidatorService {
    @Override
    public boolean validateArticleImageNotEmpty(String imagePath) {

        return imagePath != null && !imagePath.isEmpty();

    }

    private static final int MAX_TITLE_LENGTH = 100;

    @Override
    public boolean validateArticleTitleLength(String title) {

        if (title == null) {
            return false;
        }

        return title.length() <= MAX_TITLE_LENGTH;

    }

    private static final int MAX_TEXT_LENGTH = 16777216; // 16 MB

    @Override
    public boolean validateArticleTextLength(String articleText) {

        if (articleText == null) {
            return false;
        }

        return articleText.length() <= MAX_TEXT_LENGTH;

    }
}
