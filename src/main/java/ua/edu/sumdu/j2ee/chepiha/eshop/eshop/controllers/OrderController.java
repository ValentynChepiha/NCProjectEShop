package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.*;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.OrderProductsService;

import java.text.ParseException;
import java.util.List;

@Controller
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class.getName());
    @Autowired
    private ModelRepository<Order> orderRepository;
    @Autowired
    private ModelRepository<Product> productRepository;
    @Autowired
    private ModelRepository<Client> clientRepository;
    @Autowired
    private OrderProductsService orderProductsService;

    @GetMapping("/orders")
    public String orders(Model model){
        logger.info("Info about all order rendering...");
        List<Order> orders = orderRepository.getAll();
        model.addAttribute("orders", orders);
        return "pages/order/all";
    }

    @GetMapping("/orders/add")
    public String ordersAddGet(Model model){
        logger.info("Page create new order");
        model.addAttribute("clients", clientRepository.getAll());
        model.addAttribute("products", productRepository.getAll());
        return "pages/order/add";
    }

    @PostMapping("/orders/add")
    public String ordersAddPost(@RequestParam String orderDate, @RequestParam Long orderClient,
                                @RequestBody String orderBody, Model model) throws ParseException {
        logger.info("Page saving new order");
        orderProductsService.saveOrderProducts(orderClient, orderDate, orderBody);
        return "redirect:/orders";
    }

    @GetMapping("/orders/edit/{id}")
    public String ordersEditGet(@PathVariable(value = "id") long id, Model model){
        logger.info("Page edit order");
        model.addAttribute("clients", clientRepository.getAll());
        model.addAttribute("order", orderProductsService.getOrderProductsForEdit(id));
        return "pages/order/edit";
    }

    @PostMapping("/orders/edit")
    public String ordersEditPost(@RequestParam long orderId, @RequestParam String orderDate,
                                 @RequestParam Long orderClient, @RequestBody String orderBody,
                                 Model model) throws ParseException {
        logger.info("Page updating order");
        orderProductsService.updateOrderProducts(orderId, orderClient, orderDate, orderBody);
        return "redirect:/orders";
    }

    @GetMapping("/orders/delete/{id}")
    public String ordersDeleteGet(@PathVariable(value = "id") long id, Model model){
        logger.info("Page delete order");

        Order order = orderProductsService.getOrderProductsForDelete(id);

        model.addAttribute("client", clientRepository.getOne(order.getIdClient()));
        model.addAttribute("order", order);
        return "pages/order/delete";
    }

    @PostMapping("/orders/delete/{id}")
    public String ordersDeletePost(@PathVariable(value = "id") long id, Model model){
        logger.info("Page deleting order");
        orderProductsService.deleteOrderProducts(id);
        return "redirect:/orders";
    }

}
