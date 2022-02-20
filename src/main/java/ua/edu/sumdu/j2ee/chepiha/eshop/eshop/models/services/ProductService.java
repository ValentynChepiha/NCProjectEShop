package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import org.springframework.jdbc.core.JdbcTemplate;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.OrderProductRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.ProductRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Order;

public class ProductService {

    public static void setProductCountAfterCreateOrder(JdbcTemplate jdbcTemplate, Order order){
        ProductRepository productRepository = new ProductRepository(jdbcTemplate);

        order.getOrderProductList().forEach(product -> {
            productRepository.updateCount(
                    product.getProduct().getId(),
                    product.getProduct().getCount() - product.getCount());
        });
    }

    public static void setProductCountBeforeUpdateOrder(JdbcTemplate jdbcTemplate, Order order){
        ProductRepository productRepository = new ProductRepository(jdbcTemplate);

        order.getOrderProductList().forEach(product -> {
            productRepository.updateCount(
                    product.getProduct().getId(),
                    product.getProduct().getCount() + product.getCount());
        });
    }

    public static void setProductToOrder(JdbcTemplate jdbcTemplate, Order order){
        OrderProductRepository orderProductRepository = new OrderProductRepository(jdbcTemplate);
        ProductRepository productRepository = new ProductRepository(jdbcTemplate);

        order.setOrderProductList( orderProductRepository.getOrderProductsByIDOrder(order.getId()) );
        order.getOrderProductList().forEach(product ->
                product.setProduct(productRepository.getOne(product.getIdProduct())));
    }
}
