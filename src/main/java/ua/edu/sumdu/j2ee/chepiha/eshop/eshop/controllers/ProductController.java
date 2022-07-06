package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Brand;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Product;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Storage;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelProductRepository;

@Slf4j
@Controller
public class ProductController {

    private final ModelProductRepository<Product> productRepository;
    private final ModelRepository<Brand> brandRepository;
    private final ModelRepository<Storage> storageRepository;

    @Autowired
    public ProductController(ModelProductRepository<Product> productRepository,
                             ModelRepository<Brand> brandRepository, ModelRepository<Storage> storageRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.storageRepository = storageRepository;
    }

    @GetMapping("/products")
    public String products(Model model){
        log.info("Info about all product rendering...");
        model.addAttribute("products", productRepository.getAll());
        return "pages/product/all";
    }

    @GetMapping("/products/add")
    public String productsAddGet(Model model){
        log.info("Page create new product");
        model.addAttribute("products", productRepository.getAll());
        model.addAttribute("brands", brandRepository.getAll());
        model.addAttribute("storages", storageRepository.getAll());
        return "pages/product/add";
    }

    @PostMapping("/products/add")
    public String productsAddPost(@RequestParam String productName, @RequestParam Long productBrand,
                                  @RequestParam float productPrice, @RequestParam int productCount,
                                  @RequestParam float productDiscount, @RequestParam Long productGift,
                                  @RequestParam Long productStorage, Model model){
        log.info("Page saving new product");
        Product product = new Product();

        log.debug("productsAddPost :: create :: " + product);
        product.setName(productName);

        log.debug("productsAddPost :: add productName :: " + productName);
        product.setIdBrand(productBrand);

        log.debug("productsAddPost :: add productBrand :: " + productBrand);
        product.setPrice(productPrice);

        log.debug("productsAddPost :: add productPrice :: " + productPrice);
        product.setCount(productCount);

        log.debug("productsAddPost :: add productCount :: " + productCount);
        product.setDiscount(productDiscount);

        log.debug("productsAddPost :: add productDiscount :: " + productDiscount);
        product.setGift(productGift);

        log.debug("productsAddPost :: add productGift :: " + productGift);
        product.setIdStorage(productStorage);

        log.debug("productsAddPost :: add productStorage :: " + productStorage);
        log.debug("productsAddPost :: result product :: " + product);
        if(product.validate()){
            productRepository.create(product);
        }
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String productsEditGet(@PathVariable(value = "id") long id, Model model){
        log.info("Page edit product");
        Product product = productRepository.getOne(id);
        model.addAttribute("products", productRepository.getAllWithoutOneId(product.getId()));
        model.addAttribute("brands", brandRepository.getAll());
        model.addAttribute("storages", storageRepository.getAll());
        model.addAttribute("product", product);
        return "pages/product/edit";
    }

    @PostMapping("/products/edit")
    public String productsEditPost(@RequestParam long productId, @RequestParam String productName,
                                   @RequestParam Long productBrand, @RequestParam float productPrice,
                                   @RequestParam int productCount, @RequestParam float productDiscount,
                                   @RequestParam Long productGift, @RequestParam Long productStorage,
                                   Model model){

        log.info("Page updating product");

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
        log.info("Page delete product");

        Product product = productRepository.getOne(id);
        product.setBrand( brandRepository.getOne(product.getIdBrand()) );
        product.setStorage( storageRepository.getOne(product.getIdStorage()) );

        if(product.getGift() > 0 ){
            product.setGiftValue( productRepository.getOne(product.getGift()) );
        }
        model.addAttribute("product", product);
        return "pages/product/delete";
    }

    @PostMapping("/products/delete/{id}")
    public String productsDeletePost(@PathVariable(value = "id") long id, Model model){
        log.info("Page deleting product");
        productRepository.delete(id);
        return "redirect:/products";
    }

}
