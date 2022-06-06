package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelOrderProductRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelProductRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Order;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.OrderProduct;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Product;

@Service
public class ProductBuildService {

    private final ModelProductRepository<Product> productRepository;
    private final ModelOrderProductRepository<OrderProduct> orderProductRepository;

    @Autowired
    public ProductBuildService(ModelProductRepository<Product> productRepository,
                               ModelOrderProductRepository<OrderProduct> orderProductRepository) {
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
    }

    public void setProductCountAfterCreateOrder(Order order){
        order.getOrderProductList().forEach(product -> {
            productRepository.updateCount(
                    product.getProduct().getId(),
                    product.getProduct().getCount() - product.getCount());
        });
    }

    public void setProductCountBeforeUpdateOrder(Order order){
        order.getOrderProductList().forEach(product -> {
            productRepository.updateCount(
                    product.getProduct().getId(),
                    product.getProduct().getCount() + product.getCount());
        });
    }

    public void setProductToOrder(Order order){
        order.setOrderProductList( orderProductRepository.getOrderProductsByIDOrder(order.getId()) );
        order.getOrderProductList().forEach(product ->
                product.setProduct( productRepository.getOne(product.getIdProduct()) ));
    }

}
