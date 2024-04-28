package edu.training.web.bean;

import java.io.Serializable;
import java.util.Objects;

public class UserRegInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String login;
    private String email;
    private String password;

    private String confirmPassword;

    public UserRegInfo() {
    }


    public UserRegInfo(String login, String email, String password, String confirmPassword) {
        super();
        this.login = login;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegInfo that = (UserRegInfo) o;
        return Objects.equals(login, that.login) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(confirmPassword, that.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, email, password, confirmPassword);
    }
}
