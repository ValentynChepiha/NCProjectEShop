package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities;


import java.util.Objects;

public class OrderProduct {

    long id;
    int count;
    float discount;

    long idOrder;
    long idGift;
    long idProduct;
    Order order;
    Product product;

    public OrderProduct() {
    }

    public OrderProduct(long id, int count, float discount, long idOrder, long idGift,
                        long idProduct, Order order, Product product) {
        this.id = id;
        this.count = count;
        this.discount = discount;
        this.idOrder = idOrder;
        this.idGift = idGift;
        this.idProduct = idProduct;
        this.order = order;
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public long getIdGift() {
        return idGift;
    }

    public void setIdGift(long idGift) {
        this.idGift = idGift;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;

        this.discount = product.discount;
        this.idProduct = product.id;
        this.idGift = product.gift;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", count=" + count +
                ", discount=" + discount +
                ", idOrder=" + idOrder +
                ", idGift=" + idGift +
                ", idProduct=" + idProduct +
                '}';
    }

    public boolean validateFull(){
        return validate() && id>0;
    }

    public boolean validate(){
        return count>0 && idOrder>0 && idProduct>0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
        return id == that.id &&
                count == that.count &&
                Float.compare(that.discount, discount) == 0 &&
                idOrder == that.idOrder &&
                idGift == that.idGift &&
                idProduct == that.idProduct &&
                order.equals(that.order) &&
                product.equals(that.product);
    }

    @Override
    public int hashCode() {
        int salt = 31;
        int result = 7;

        result = salt * result + (int) (id ^ (id >>> 32));
        result = salt * result + count;

        long discountToInt = Float.floatToIntBits(discount);
        result = salt * result + (int) (discountToInt ^ (discountToInt >>> 32));

        result = salt * result + (int) (idOrder ^ (idOrder >>> 32));
        result = salt * result + (int) (idGift ^ (idGift >>> 32));
        result = salt * result + (int) (idProduct ^ (idProduct >>> 32));

        result = salt * result + Objects.hashCode(order);
        result = salt * result + Objects.hashCode(product);

        return result;
    }
}
