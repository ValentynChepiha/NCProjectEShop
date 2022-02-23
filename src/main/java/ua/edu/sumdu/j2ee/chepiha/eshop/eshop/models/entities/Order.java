package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {

    long id;
    Date dOrder;
    long idClient;

    List<OrderProduct> orderProductList = new ArrayList<>();

    public Order() {
    }

    public Order(long id, Date dOrder, long idClient) {
        this.id = id;
        this.dOrder = dOrder;
        this.idClient = idClient;
    }

    public Order(long id, Date dOrder, long idClient, List<OrderProduct> orderProductList) {
        this.id = id;
        this.dOrder = dOrder;
        this.idClient = idClient;
        this.orderProductList = orderProductList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDOrder() {
        return dOrder;
    }

    public void setDOrder(Date dOrder) {
        this.dOrder = dOrder;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }

    public void clearOrderProductList() {
        this.orderProductList.clear();
    }

    public void addOrderProduct(OrderProduct orderProduct) {
        orderProductList.add(orderProduct);
    }

    @Override
    public String toString() {

        return "Order{" +
                "id=" + id +
                ", dOrder=" + dOrder +
                ", idClient=" + idClient +
                ", orderProductList=" + orderProductList +
                '}';
    }

    public boolean validateFull(){
        return validate() && id>0;
    }

    public boolean validate(){
        return orderProductList.size()>0 && idClient>0 && dOrder!=null;
    }
}