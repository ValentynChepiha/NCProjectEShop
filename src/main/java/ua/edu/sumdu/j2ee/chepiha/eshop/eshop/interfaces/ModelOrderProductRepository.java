package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces;

import java.util.List;

public interface ModelOrderProductRepository<T> extends ModelRepository<T> {

    void deleteByIdOrder(long idOrder);
    List<T> getOrderProductsByIDOrder(long idOrder);
    List<T> getOrderProductsWithAllProducts(long idOrder);

}
