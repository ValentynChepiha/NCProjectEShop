package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities;

import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.ValidateString;

import java.util.Objects;

public class Client {

    long id;
    String name;
    String email;
    String phone;
    long idLocation;
    Location location;

    public Client() {
    }

    public Client(long id, String name, String email, String phone, long idLocation) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.idLocation = idLocation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(long idLocation) {
        this.idLocation = idLocation;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", idLocation=" + idLocation +
                '}';
    }

    public boolean validateFull(){
        return validate() && id>0;
    }

    public boolean validate(){
        return ValidateString.validateString(name)
                && ValidateString.validateString(email)
                && ValidateString.validateString(phone)
                && idLocation > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                idLocation == client.idLocation &&
                Objects.equals(name, client.name) &&
                Objects.equals(email, client.email) &&
                Objects.equals(phone, client.phone) &&
                Objects.equals(location, client.location);
    }

    @Override
    public int hashCode() {
        int salt = 31;
        int result = 7;

        result = salt * result + (int) (id ^ (id >>> 32));
        result = salt * result + Objects.hashCode(name);
        result = salt * result + Objects.hashCode(email);
        result = salt * result + Objects.hashCode(phone);
        result = salt * result + (int) (idLocation ^ (idLocation >>> 32));
        result = salt * result + Objects.hashCode(location);

        return result;
    }
}
