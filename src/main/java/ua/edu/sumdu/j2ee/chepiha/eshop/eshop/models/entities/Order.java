package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities;

import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.EqualListsOrderProduct;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;

        return id == order.id &&
                idClient == order.idClient &&
                Objects.equals(dOrder, order.dOrder) &&
                EqualListsOrderProduct.compare(orderProductList, order.orderProductList);
    }

    @Override
    public int hashCode() {
        int salt = 31;
        int result = 7;

        result = salt * result + (int) (id ^ (id >>> 32));
        result = salt * result + Objects.hashCode(dOrder) ;
        result = salt * result + (int) (idClient ^ (idClient >>> 32));
        for(OrderProduct orderProduct: orderProductList){
            result = salt * result + orderProduct.hashCode();
        }

        return result;
    }
}
