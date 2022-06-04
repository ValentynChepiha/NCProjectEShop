package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelSelectRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.ProductToOnline;

import java.util.List;

@RestController
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class.getName());

    @Autowired
    private ModelSelectRepository<ProductToOnline> productToOnlineRepository;

    @RequestMapping(value = "/api/goods", produces = { MediaType.APPLICATION_XML_VALUE })
    public List<ProductToOnline> allGoodsGet(Model model) {
        logger.info("ApiController allGoodsGet start...");
        return productToOnlineRepository.getAll();
    }

}
