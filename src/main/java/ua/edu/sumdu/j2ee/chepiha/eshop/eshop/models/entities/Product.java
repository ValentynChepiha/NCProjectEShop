package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities;

import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.ValidateString;

import java.util.Objects;

public class Product {

    long id;
    String name;
    long idBrand;
    float price;
    int count;
    float discount;
    long gift;
    long idStorage;
    Brand brand;
    Storage storage;
    Product giftValue;

    public Product() {
    }

    public Product(long id, String name, long idBrand, float price, int count, float discount,
                   long gift, long idStorage, Brand brand, Storage storage, Product giftValue) {
        this.id = id;
        this.name = name;
        this.idBrand = idBrand;
        this.price = price;
        this.count = count;
        this.discount = discount;
        this.gift = gift;
        this.idStorage = idStorage;
        this.brand = brand;
        this.storage = storage;
        this.giftValue = giftValue;
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

    public long getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(long idBrand) {
        this.idBrand = idBrand;
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

    public long getGift() {
        return gift;
    }

    public void setGift(long gift) {
        this.gift = gift;
    }

    public long getIdStorage() {
        return idStorage;
    }

    public void setIdStorage(long idStorage) {
        this.idStorage = idStorage;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Product getGiftValue() {
        return giftValue;
    }

    public void setGiftValue(Product giftValue) {
        this.giftValue = giftValue;
    }

    public boolean validateFull(){
        return validate() && id>0;
    }

    public boolean validate(){
        return ValidateString.validateString(name)
                && idStorage > 0
                && idBrand > 0
                && count >= 0
                && price >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                idBrand == product.idBrand &&
                Float.compare(product.price, price) == 0 &&
                count == product.count &&
                Float.compare(product.discount, discount) == 0 &&
                gift == product.gift &&
                idStorage == product.idStorage &&
                Objects.equals(name, product.name) &&
                brand.equals(product.brand) &&
                storage.equals(product.storage) &&
                Objects.equals(giftValue, product.giftValue);
    }

    @Override
    public int hashCode() {

        System.out.println("Product :: hashCode :: " + this.toString());

        int salt = 31;
        int result = 7;

        result = salt * result + (int) (id ^ (id >>> 32));
        result = salt * result + Objects.hashCode(name);
        result = salt * result + (int) (idBrand ^ (idBrand >>> 32));

        int priceToInt = Float.floatToIntBits(price);
        result = salt * result + priceToInt;
        result = salt * result + count;

        int discountToInt = Float.floatToIntBits(discount);
        result = salt * result + discountToInt;

        result = salt * result + (int) (gift ^ (gift >>> 32));
        result = salt * result + (int) (idStorage ^ (idStorage >>> 32));

        result = salt * result + Objects.hashCode(brand);
        result = salt * result + Objects.hashCode(storage);
        result = salt * result + Objects.hashCode(giftValue);

        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idBrand=" + idBrand +
                ", price=" + price +
                ", count=" + count +
                ", discount=" + discount +
                ", gift=" + gift +
                ", idStorage=" + idStorage +
                ", brand=" + brand +
                ", storage=" + storage +
                ", giftValue=" + giftValue +
                '}';
    }
}
