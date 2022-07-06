package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities;

import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.ValidateString;

import java.util.Objects;

public class Storage {

    long id;
    String name;
    long idLocation;
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
        return validate() && id>0;
    }

    public boolean validate(){
        return ValidateString.validateString(name) && idLocation > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return id == storage.id &&
                idLocation == storage.idLocation &&
                Objects.equals(name, storage.name) &&
                Objects.equals(location, storage.location);
    }

    @Override
    public int hashCode() {
        int salt = 31;
        int result = 7;

        result = salt * result + (int) (id ^ (id >>> 32));
        result = salt * result + Objects.hashCode(name);
        result = salt * result + (int) (idLocation ^ (idLocation >>> 32));
        result = salt * result + Objects.hashCode(location);

        return result;
    }
}
