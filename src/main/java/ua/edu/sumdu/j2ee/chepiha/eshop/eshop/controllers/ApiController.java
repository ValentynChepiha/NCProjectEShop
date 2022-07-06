package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelSelectApiRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.ProductToOnline;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.ParseDataValueService;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.ParseRequestApiService;

import java.util.List;

@Slf4j
@RestController
public class ApiController {

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
          log.info("ApiController allGoodsGet start...");
        return productToOnlineRepository.getAll();
    }

    @RequestMapping(value = "/api/goods/{list}", produces = { MediaType.APPLICATION_XML_VALUE })
    public List<ProductToOnline> selectedGoodsGet(@PathVariable String list) {
        log.info("ApiController selectedGoodsGet start...");
        return productToOnlineRepository.getQueryList(
                parseDataValueService.convertStringToList(list, ",")
        );
    }

    @RequestMapping(value = "/api/order/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String createOrder(@RequestBody String params) {
        log.info("ApiController createOrder start...");
        log.debug("createOrder :: params -  " + params);
        return parseRequestApiService.start(params) ? "ok" : "error";
    }

}
