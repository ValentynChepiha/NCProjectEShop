package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.BrandRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.LocationRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.ProductRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.StorageRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Brand;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Location;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Product;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Storage;

import java.util.List;

@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private ProductRepository productRepository;
    private LocationRepository locationRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/products")
    public String products(Model model){
        logger.warn("Info about all product rendering...");
        productRepository = new ProductRepository(jdbcTemplate);
        List<Product> products = productRepository.getAll();
        model.addAttribute("products", products);
        return "pages/product/all";
    }

    @GetMapping("/products/add")
    public String productsAddGet(Model model){
        logger.warn("Page create new product");

        BrandRepository brandRepository = new BrandRepository(jdbcTemplate);
        List<Brand> brands = brandRepository.getAll();

        StorageRepository storageRepository = new StorageRepository(jdbcTemplate);
        List<Storage> storages = storageRepository.getAll();

        productRepository = new ProductRepository(jdbcTemplate);
        List<Product> products = productRepository.getAll();

        model.addAttribute("products", products);
        model.addAttribute("brands", brands);
        model.addAttribute("storages", storages);

        return "pages/product/add";
    }

    @PostMapping("/products/add")
    public String productsAddPost(@RequestParam String productName, @RequestParam Long productBrand,
                                  @RequestParam float productPrice, @RequestParam int productCount,
                                  @RequestParam float productDiscount, @RequestParam Long productGift,
                                  @RequestParam Long productStorage, Model model){
        logger.warn("Page saving new product");

        Product product = new Product();
        product.setName(productName);
        product.setIdBrand(productBrand);
        product.setPrice(productPrice);
        product.setCount(productCount);
        product.setDiscount(productDiscount);
        product.setGift(productGift);
        product.setIdStorage(productStorage);

        if(product.validate()){
            productRepository.create(product);
        }
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String productsEditGet(@PathVariable(value = "id") long id, Model model){
        logger.warn("Page edit product");

        productRepository = new ProductRepository(jdbcTemplate);
        Product product = productRepository.getOne(id);

        BrandRepository brandRepository = new BrandRepository(jdbcTemplate);
        List<Brand> brands = brandRepository.getAll();

        StorageRepository storageRepository = new StorageRepository(jdbcTemplate);
        List<Storage> storages = storageRepository.getAll();

        productRepository = new ProductRepository(jdbcTemplate);
        List<Product> products = productRepository.getAllWithoutOneId(product.getId());

        model.addAttribute("products", products);
        model.addAttribute("brands", brands);
        model.addAttribute("storages", storages);
        model.addAttribute("product", product);
        return "pages/product/edit";
    }

    @PostMapping("/products/edit")
    public String productsEditPost(@RequestParam long productId, @RequestParam String productName,
                                   @RequestParam Long productBrand, @RequestParam float productPrice,
                                   @RequestParam int productCount, @RequestParam float productDiscount,
                                   @RequestParam Long productGift, @RequestParam Long productStorage,
                                   Model model){

        logger.warn("Page updating product");

        Product product = new Product();
        product.setId(productId);
        product.setName(productName);
        product.setIdBrand(productBrand);
        product.setPrice(productPrice);
        product.setCount(productCount);
        product.setDiscount(productDiscount);
        product.setGift(productGift);
        product.setIdStorage(productStorage);

        if(product.validateFull()){
            productRepository.update(product);
        }

        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String productsDeleteGet(@PathVariable(value = "id") long id, Model model){
        logger.warn("Page delete product");

        productRepository = new ProductRepository(jdbcTemplate);
        Product product = productRepository.getOne(id);

        BrandRepository brandRepository = new BrandRepository(jdbcTemplate);
        product.setBrand( brandRepository.getOne(product.getIdBrand()) );

        StorageRepository storageRepository = new StorageRepository(jdbcTemplate);
        product.setStorage( storageRepository.getOne(product.getIdStorage()) );

        if(product.getGift() > 0 ){
            product.setGiftValue( productRepository.getOne(product.getGift()) );
        }

        model.addAttribute("product", product);
        return "pages/product/delete";
    }

    @PostMapping("/products/delete/{id}")
    public String productsDeletePost(@PathVariable(value = "id") long id, Model model){
        logger.warn("Page deleting product");
        productRepository = new ProductRepository(jdbcTemplate);
        productRepository.delete(id);

        return "redirect:/products";
    }

}
