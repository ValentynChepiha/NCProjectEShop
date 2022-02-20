package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.*;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.*;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.OrderProductsService;

import java.text.ParseException;
import java.util.List;

@Controller
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private OrderRepository orderRepository;
    private OrderProductRepository orderProductRepository;
    private ProductRepository productRepository;
    private ClientRepository clientRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/orders")
    public String orders(Model model){
        logger.warn("Info about all order rendering...");
        orderRepository = new OrderRepository(jdbcTemplate);

        List<Order> orders = orderRepository.getAll();
        model.addAttribute("orders", orders);
        return "pages/order/all";
    }

    @GetMapping("/orders/add")
    public String ordersAddGet(Model model){
        logger.warn("Page create new order");
        clientRepository = new ClientRepository(jdbcTemplate);
        productRepository = new ProductRepository(jdbcTemplate);
        model.addAttribute("clients", clientRepository.getAll());
        model.addAttribute("products", productRepository.getAll());
        return "pages/order/add";
    }

    @PostMapping("/orders/add")
    public String ordersAddPost(@RequestParam String orderDate, @RequestParam Long orderClient,
                                @RequestBody String orderBody, Model model) throws ParseException {
        logger.warn("Page saving new order");
        OrderProductsService.saveOrderProducts(jdbcTemplate, orderClient, orderDate, orderBody);
        return "redirect:/orders";
    }

    @GetMapping("/orders/edit/{id}")
    public String ordersEditGet(@PathVariable(value = "id") long id, Model model){
        logger.warn("Page edit order");
        clientRepository = new ClientRepository(jdbcTemplate);
        model.addAttribute("clients", clientRepository.getAll());
        model.addAttribute("order", OrderProductsService.getOrderProductsForEdit(jdbcTemplate, id));
        return "pages/order/edit";
    }

    @PostMapping("/orders/edit")
    public String ordersEditPost(@RequestParam long orderId, @RequestParam String orderDate,
                                 @RequestParam Long orderClient, @RequestBody String orderBody,
                                 Model model) throws ParseException {
        logger.warn("Page updating order");
        OrderProductsService.updateOrderProducts(jdbcTemplate, orderId, orderClient, orderDate, orderBody);
        return "redirect:/orders";
    }

    @GetMapping("/orders/delete/{id}")
    public String ordersDeleteGet(@PathVariable(value = "id") long id, Model model){
        logger.warn("Page delete order");

        Order order = OrderProductsService.getOrderProductsForDelete(jdbcTemplate, id);
        clientRepository = new ClientRepository(jdbcTemplate);

        model.addAttribute("client", clientRepository.getOne(order.getIdClient()));
        model.addAttribute("order", order);
        return "pages/order/delete";
    }

    @PostMapping("/orders/delete/{id}")
    public String ordersDeletePost(@PathVariable(value = "id") long id, Model model){
        logger.warn("Page deleting order");
        OrderProductsService.deleteOrderProducts(jdbcTemplate, id);
        return "redirect:/orders";
    }

}
