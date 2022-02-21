package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.ValidateService;

@Table("lab3_chepihavv_brand")
public class Brand {

    @Id
    long id;
    String name;
    String country;

    public Brand() {
    }

    public Brand(long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Brand{" +
                id +
                " :: " + name +
                " (" + country +  ")}";
    }

    public boolean validateFull(){
        return validate() && id>0;
    }

    public boolean validate(){
        return ValidateService.validateString(country) && ValidateService.validateString(name);
    }
}
