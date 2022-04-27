package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities;

import java.util.Objects;

public class UserRole {

    long id;
    String name;

    public UserRole() {
    }

    public UserRole(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return id == userRole.id &&
                name.equals(userRole.name);
    }

    @Override
    public int hashCode() {
        int salt = 31;
        int result = 7;

        result = salt * result + (int) (id ^ (id >>> 32));
        result = salt * result + name.hashCode();

        return result;
    }
}
