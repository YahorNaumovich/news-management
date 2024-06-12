package edu.training.web.controller;

public enum ErrorCode {

    USER_LIST("error.user.list"),
    USER_DELETE("error.user.delete"),
    ARTICLE_ADD("error.article.add"),
    ARTICLE_DELETE("error.article.delete"),
    ARTICLE_EDIT("error.article.edit"),
    ARTICLE_GET("error.article.get"),
    NEWS_GET("error.news.get"),
    USER_REGISTRATION("error.user.registration"),
    USER_AUTHENTICATION("error.user.authentication"),
    USER_ALREADY_EXISTS("error.user.userAlreadyExists"),
    PASSWORD_MISMATCH("error.user.passwordMismatch"),
    PASSWORD_LENGTH_INVALID("error.user.passwordLengthInvalid"),
    PASSWORD_INVALID("error.user.passwordInvalid"),
    PASSWORD_CHANGE("error.user.passwordChange"),
    ROLE_CHANGE("error.user.roleChange");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
