package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelOrderProductRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelProductRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Order;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.OrderProduct;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Product;

import java.text.ParseException;
import java.util.List;

@Service
public class OrderProductsService {

    private final ModelRepository<Order> orderRepository;
    private final ModelProductRepository<Product> productRepository;
    private final ModelOrderProductRepository<OrderProduct> orderProductRepository;

    private final ProductBuildService productBuildService;
    private final ProductParseDataValueService productParseDataValueService;

    @Autowired
    public OrderProductsService(ModelRepository<Order> orderRepository,
                                ModelProductRepository<Product> productRepository,
                                ModelOrderProductRepository<OrderProduct> orderProductRepository,
                                ProductBuildService productBuildService,
                                ProductParseDataValueService productParseDataValueService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
        this.productBuildService = productBuildService;
        this.productParseDataValueService = productParseDataValueService;
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
        order.setDOrder(productParseDataValueService.parseStringToDate(orderDate));

        productParseDataValueService.parseBodyPage(order, orderBody);
        boolean result = saveOrderToDB(order);
    }

    public boolean saveOrderToDB (Order order) {
        if(!order.validate()){
            return false;
        }

        long id = orderRepository.create(order);
        if(id<=0){
            return false;
        }

        saveOrderProductsToDB(order, id);
        productBuildService.setProductCountAfterCreateOrder(order);
        return true;
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
        order.setDOrder(productParseDataValueService.parseStringToDate(orderDate));
        order.setIdClient(orderClient);

        productBuildService.setProductToOrder(order);
        productBuildService.setProductCountBeforeUpdateOrder(order);
        order.clearOrderProductList();

        productParseDataValueService.parseBodyPage(order, orderBody);
        orderRepository.update(order);
        orderProductRepository.deleteByIdOrder(orderId);
        saveOrderProductsToDB(order, orderId);
        productBuildService.setProductCountAfterCreateOrder(order);
    }

    public void deleteOrderProducts(long orderId){
        Order order = orderRepository.getOne(orderId);
        productBuildService.setProductToOrder(order);
        productBuildService.setProductCountBeforeUpdateOrder(order);

        orderProductRepository.deleteByIdOrder(orderId);
        orderRepository.delete(orderId);
    }

}
