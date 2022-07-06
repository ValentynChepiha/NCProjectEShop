package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities;

import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.ValidateString;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class User {

    long id;
    @NotBlank
    String login;
    @NotBlank
    String password;
    String authority;

    public User() {
    }

    public User(long id, String login, String password, String authority) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.authority = authority;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public boolean validateFull(){
        return validate() && id>0;
    }

    public boolean validate(){
        return ValidateString.validateString(login)
                && ValidateString.validateString(password)
                && ValidateString.validateString(authority);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                Objects.equals(authority, user.authority);
    }

    @Override
    public int hashCode() {
        int salt = 31;
        int result = 7;

        result = salt * result + (int) (id ^ (id >>> 32));
        result = salt * result + login.hashCode();
        result = salt * result + password.hashCode();
        result = salt * result + Objects.hashCode(authority);

        return result;
    }
}
