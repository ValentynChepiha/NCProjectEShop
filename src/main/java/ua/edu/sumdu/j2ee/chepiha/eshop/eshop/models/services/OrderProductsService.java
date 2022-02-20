package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import org.springframework.jdbc.core.JdbcTemplate;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.OrderProductRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.OrderRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.ProductRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Order;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.OrderProduct;

import java.text.ParseException;
import java.util.List;

public class OrderProductsService {

    public static Order getOrderProductsForEdit(JdbcTemplate jdbcTemplate, long idOrder){
        OrderRepository orderRepository = new OrderRepository(jdbcTemplate);
        Order order = orderRepository.getOne(idOrder);

        ProductRepository productRepository = new ProductRepository(jdbcTemplate);
        OrderProductRepository orderProductRepository = new OrderProductRepository(jdbcTemplate);

        List<OrderProduct> listOrderProducts = orderProductRepository.getOrderProductsWithAllProducts(idOrder);
        listOrderProducts.forEach(product -> {
            product.setProduct(productRepository.getOne(product.getIdProduct()));
        });

        order.setOrderProductList(listOrderProducts);
        return order;
    }

    public static Order getOrderProductsForDelete(JdbcTemplate jdbcTemplate, long idOrder){
        OrderRepository orderRepository = new OrderRepository(jdbcTemplate);
        Order order = orderRepository.getOne(idOrder);

        ProductRepository productRepository = new ProductRepository(jdbcTemplate);
        OrderProductRepository orderProductRepository = new OrderProductRepository(jdbcTemplate);

        List<OrderProduct> listOrderProducts = orderProductRepository.getOrderProductsByIDOrder(idOrder);
        listOrderProducts.forEach(product -> {
            product.setProduct(productRepository.getOne(product.getIdProduct()));
        });

        order.setOrderProductList(listOrderProducts);
        return order;
    }

    public static void saveOrderProducts(JdbcTemplate jdbcTemplate, Long orderClient,
                                         String orderDate, String orderBody) throws ParseException {
        OrderRepository orderRepository = new OrderRepository(jdbcTemplate);
        Order order = new Order();

        order.setIdClient(orderClient);
        order.setDOrder(ParseDataValueService.parseStringToDate(orderDate));

        ParseDataValueService.parseBodyPage(jdbcTemplate, order, orderBody);

        if(order.validate()){
            long id = orderRepository.create(order);
            if(id>0){
                OrderProductsService.saveOrderProductsToDB(jdbcTemplate, order, id);
                ProductService.setProductCountAfterCreateOrder(jdbcTemplate, order);
            }
        }
    }

    public static void saveOrderProductsToDB(JdbcTemplate jdbcTemplate, Order order, long orderId){
        OrderProductRepository orderProductRepository = new OrderProductRepository(jdbcTemplate);
        order.getOrderProductList().forEach(orderProduct -> {
            orderProduct.setIdOrder(orderId);
            orderProductRepository.create(orderProduct);
        });
    }

    public static void updateOrderProducts(JdbcTemplate jdbcTemplate, long orderId, Long orderClient,
                                           String orderDate, String orderBody) throws ParseException {

        OrderRepository orderRepository = new OrderRepository(jdbcTemplate);
        OrderProductRepository orderProductRepository = new OrderProductRepository(jdbcTemplate);

        Order order = orderRepository.getOne(orderId);
        order.setDOrder(ParseDataValueService.parseStringToDate(orderDate));
        order.setIdClient(orderClient);

        ProductService.setProductToOrder(jdbcTemplate, order);
        ProductService.setProductCountBeforeUpdateOrder(jdbcTemplate, order);
        order.clearOrderProductList();

        ParseDataValueService.parseBodyPage(jdbcTemplate, order, orderBody);
        orderRepository.update(order);
        orderProductRepository.deleteByIdOrder(orderId);
        OrderProductsService.saveOrderProductsToDB(jdbcTemplate, order, orderId);
        ProductService.setProductCountAfterCreateOrder(jdbcTemplate, order);
    }

    public static void deleteOrderProducts(JdbcTemplate jdbcTemplate, long orderId){
        OrderRepository orderRepository = new OrderRepository(jdbcTemplate);
        OrderProductRepository orderProductRepository = new OrderProductRepository(jdbcTemplate);

        Order order = orderRepository.getOne(orderId);
        ProductService.setProductToOrder(jdbcTemplate, order);
        ProductService.setProductCountBeforeUpdateOrder(jdbcTemplate, order);

        orderProductRepository.deleteByIdOrder(orderId);
        orderRepository.delete(orderId);
    }

}
