package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities;

import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.ValidateService;

public class User {

    long id;
    String login;
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
        return ValidateService.validateString(login)
                && ValidateService.validateString(password)
                && ValidateService.validateString(authority);
    }
}
