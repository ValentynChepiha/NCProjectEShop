package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.OrderProductRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.ProductRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Order;

@Service
public class ProductService {

    private final  ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, OrderProductRepository orderProductRepository) {
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
