package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelSelectApiRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.ProductToOnline;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.ParseDataValueService;

import java.util.List;

@RestController
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class.getName());

    @Autowired
    private ModelSelectApiRepository<ProductToOnline> productToOnlineRepository;
    @Autowired
    private ParseDataValueService parseDataValueService;

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

}
