package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities;

import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.ValidateService;

public class Location {

    long id;
    String name;
    String address;

    public Location() {
    }

    public Location(long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Location{" +
                id +
                " :: " + name +
                ", " + address + "}";
    }

    public boolean validateFull(){
        return validate() && id>0;
    }

    public boolean validate(){
        return ValidateService.validateString(name) && ValidateService.validateString(address);
    }
}
