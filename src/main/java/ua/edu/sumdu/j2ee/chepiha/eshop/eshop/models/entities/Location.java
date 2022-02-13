package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("lab3_chepihavv_location")
public class Location {

    @Id
    @Column("ID_LOCATION")
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
        if(id < 1 ){
            return false;
        }

        return validate();
    }

    public boolean validate(){
        if(name == null && name.trim().length() == 0){
            return false;
        }

        if(address == null && address.trim().length() == 0){
            return false;
        }
        return true;
    }
}
