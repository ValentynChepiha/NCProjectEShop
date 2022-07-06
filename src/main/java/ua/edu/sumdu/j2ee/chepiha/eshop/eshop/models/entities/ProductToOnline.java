package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities;

import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.ValidateString;

import java.util.Objects;

public class ProductToOnline {

    long id;
    String name;
    String brand;
    float price;
    int count;
    float discount;
    long idGift;
    String nameGift;

    public ProductToOnline() {
    }

    public ProductToOnline(long id, String name, String brand, float price, int count, float discount,
                           long idGift, String nameGift) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.count = count;
        this.discount = discount;
        this.idGift = idGift;
        this.nameGift = nameGift;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public long getIdGift() {
        return idGift;
    }

    public void setIdGift(long idGift) {
        this.idGift = idGift;
    }

    public String getNameGift() {
        return nameGift;
    }

    public void setNameGift(String nameGift) {
        this.nameGift = nameGift;
    }

    public boolean validateFull(){
        return validate() && id>0;
    }

    public boolean validate(){
        return ValidateString.validateString(name)
                && ValidateString.validateString(brand)
                && ValidateString.validateString(nameGift)
                && count >= 0
                && price >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductToOnline that = (ProductToOnline) o;
        return id == that.id &&
                Float.compare(that.price, price) == 0 &&
                count == that.count &&
                Float.compare(that.discount, discount) == 0 &&
                idGift == that.idGift &&
                Objects.equals(name, that.name) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(nameGift, that.nameGift);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, price, count, discount, idGift, nameGift);
    }

    @Override
    public String toString() {
        return "ProductToOnline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", discount=" + discount +
                ", idGift=" + idGift +
                ", nameGift='" + nameGift + '\'' +
                '}';
    }
}
