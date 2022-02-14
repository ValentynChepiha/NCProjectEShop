package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table("lab3_chepihavv_storage")
public class Storage {

    @Id
    @Column("ID")
    long id;
    String name;
    long idLocation;
    @MappedCollection(idColumn = "ID_LOCATION")
    Location location;

    public Storage() {
    }

    public Storage(long id, String name, long idLocation, Location location) {
        this.id = id;
        this.name = name;
        this.idLocation = idLocation;
        this.location = location;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public long getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(long idLocation) {
        this.idLocation = idLocation;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Location{").append(id).append(" :: ").append(name);
        if(location != null){
            result.append(", ").append(location.toString());
        }
        result.append("}");

        return  result.toString();
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

        return idLocation > 0;
    }
}
