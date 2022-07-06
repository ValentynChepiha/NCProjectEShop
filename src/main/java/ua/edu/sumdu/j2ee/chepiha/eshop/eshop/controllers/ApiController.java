package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelSelectApiRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.ProductToOnline;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.ParseDataValueService;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.ParseRequestApiService;

import java.util.List;

@RestController
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class.getName());

    private final ModelSelectApiRepository<ProductToOnline> productToOnlineRepository;
    private final ParseDataValueService parseDataValueService;
    private final ParseRequestApiService parseRequestApiService;

    @Autowired
    public ApiController(ModelSelectApiRepository<ProductToOnline> productToOnlineRepository,
                         ParseDataValueService parseDataValueService, ParseRequestApiService parseRequestApiService) {
        this.productToOnlineRepository = productToOnlineRepository;
        this.parseDataValueService = parseDataValueService;
        this.parseRequestApiService = parseRequestApiService;
    }

    @RequestMapping(value = "/api/goods", produces = { MediaType.APPLICATION_XML_VALUE })
    public List<ProductToOnline> allGoodsGet() {
        logger.info("ApiController allGoodsGet start...");
        return productToOnlineRepository.getAll();
    }

    @RequestMapping(value = "/api/goods/{list}", produces = { MediaType.APPLICATION_XML_VALUE })
    public List<ProductToOnline> selectedGoodsGet(@PathVariable String list) {
        logger.info("ApiController selectedGoodsGet start...");
        return productToOnlineRepository.getQueryList(
                parseDataValueService.convertStringToList(list, ",")
        );
    }

    @RequestMapping(value = "/api/order/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String createOrder(@RequestBody String params) {
        System.out.println( "createOrder :: params -  " + params);
        return parseRequestApiService.start(params) ? "ok" : "error";
    }

}
