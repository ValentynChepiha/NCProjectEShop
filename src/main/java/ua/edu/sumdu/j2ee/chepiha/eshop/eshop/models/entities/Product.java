package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.ValidateService;

@Table("lab3_chepihavv_product")
public class Product {

    @Id
    @Column("ID")
    long id;
    String name;
    long idBrand;
    float price;
    int count;
    float discount;
    long gift;
    long idStorage;
    @MappedCollection(idColumn = "ID_BRAND")
    Brand brand;
    @MappedCollection(idColumn = "ID_STORAGE")
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
        if(id < 1 ){
            return false;
        }

        return validate();
    }

    public boolean validate(){
        return ValidateService.validateString(name)
                && idStorage > 0
                && idBrand > 0
                && count >= 0
                && price >= 0;
    }
}
