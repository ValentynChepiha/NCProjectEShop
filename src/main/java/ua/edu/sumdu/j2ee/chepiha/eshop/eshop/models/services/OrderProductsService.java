package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.OrderProductRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.OrderRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.ProductRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Order;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.OrderProduct;

import java.text.ParseException;
import java.util.List;

@Service
public class OrderProductsService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductService productService;
    private final ParseDataValueService parseDataValueService;

    @Autowired
    public OrderProductsService(OrderRepository orderRepository, ProductRepository productRepository,
                                OrderProductRepository orderProductRepository, ProductService productService,
                                ParseDataValueService parseDataValueService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
        this.productService = productService;
        this.parseDataValueService = parseDataValueService;
    }

    public Order getOrderProductsForEdit(long idOrder){
        Order order = orderRepository.getOne(idOrder);

        List<OrderProduct> listOrderProducts = orderProductRepository.getOrderProductsWithAllProducts(idOrder);
        listOrderProducts.forEach(product -> {
            product.setProduct(productRepository.getOne(product.getIdProduct()));
        });

        order.setOrderProductList(listOrderProducts);
        return order;
    }

    public Order getOrderProductsForDelete(long idOrder){
        Order order = orderRepository.getOne(idOrder);

        List<OrderProduct> listOrderProducts = orderProductRepository.getOrderProductsByIDOrder(idOrder);
        listOrderProducts.forEach(product -> {
            product.setProduct(productRepository.getOne(product.getIdProduct()));
        });

        order.setOrderProductList(listOrderProducts);
        return order;
    }

    public void saveOrderProducts(Long orderClient, String orderDate, String orderBody) throws ParseException {
        Order order = new Order();

        order.setIdClient(orderClient);
        order.setDOrder(parseDataValueService.parseStringToDate(orderDate));

        parseDataValueService.parseBodyPage(order, orderBody);
        if(order.validate()){
            long id = orderRepository.create(order);
            if(id>0){
                saveOrderProductsToDB(order, id);
                productService.setProductCountAfterCreateOrder(order);
            }
        }
    }

    public void saveOrderProductsToDB(Order order, long orderId){
        order.getOrderProductList().forEach(orderProduct -> {
            orderProduct.setIdOrder(orderId);
            orderProductRepository.create(orderProduct);
        });
    }

    public void updateOrderProducts(long orderId, Long orderClient,
                                           String orderDate, String orderBody) throws ParseException {

        Order order = orderRepository.getOne(orderId);
        order.setDOrder(parseDataValueService.parseStringToDate(orderDate));
        order.setIdClient(orderClient);

        productService.setProductToOrder(order);
        productService.setProductCountBeforeUpdateOrder(order);
        order.clearOrderProductList();

        parseDataValueService.parseBodyPage(order, orderBody);
        orderRepository.update(order);
        orderProductRepository.deleteByIdOrder(orderId);
        saveOrderProductsToDB(order, orderId);
        productService.setProductCountAfterCreateOrder(order);
    }

    public void deleteOrderProducts(long orderId){
        Order order = orderRepository.getOne(orderId);
        productService.setProductToOrder(order);
        productService.setProductCountBeforeUpdateOrder(order);

        orderProductRepository.deleteByIdOrder(orderId);
        orderRepository.delete(orderId);
    }

}
