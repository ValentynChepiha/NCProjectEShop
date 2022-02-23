package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities;


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
}
