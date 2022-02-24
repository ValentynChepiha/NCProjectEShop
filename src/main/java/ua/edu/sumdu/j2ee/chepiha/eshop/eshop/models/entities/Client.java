package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities;

import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.ValidateService;

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
        return ValidateService.validateString(name)
                && ValidateService.validateString(email)
                && ValidateService.validateString(phone)
                && idLocation > 0;
    }
}
