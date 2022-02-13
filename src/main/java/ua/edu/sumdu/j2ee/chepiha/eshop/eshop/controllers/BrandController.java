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
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Brand;

import java.util.List;

@Controller
public class BrandController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/brands")
    public String brands(Model model){
        logger.warn("Info about all brands rendering...");
        BrandRepository brandRepository = new BrandRepository(jdbcTemplate);
        List<Brand> brands = brandRepository.getAll();
        model.addAttribute("brands", brands);
        return "brand-all";
    }

    @GetMapping("/brands/add")
    public String brandsAddGet(Model model){
        logger.warn("Page create new brand");
        return "brand-add";
    }

    @PostMapping("/brands/add")
    public String brandsAddPost(@RequestParam String brandName, @RequestParam String brandCountry,
                                Model model){
        logger.warn("Page saving new brand");
        Brand brand = new Brand();
        brand.setName(brandName);
        brand.setCountry(brandCountry);
        if(brand.validate()){
            BrandRepository brandRepository = new BrandRepository(jdbcTemplate);
            long id = brandRepository.create(brand);
        }
        return "redirect:/brands";
    }

    @GetMapping("/brands/edit/{id}")
    public String brandsEditGet(@PathVariable(value = "id") long id, Model model){
        logger.warn("Page edit brand");
        BrandRepository brandRepository = new BrandRepository(jdbcTemplate);
        Brand brand = brandRepository.getOne(id);
        model.addAttribute("brand", brand);
        return "brand-edit";
    }

    @PostMapping("/brands/edit")
    public String brandsEditPost(@RequestParam long brandId, @RequestParam String brandName,
                                 @RequestParam String brandCountry, Model model){

        logger.warn("Page updating brand");
        Brand brand = new Brand();
        brand.setId(brandId);
        brand.setName(brandName);
        brand.setCountry(brandCountry);
        if(brand.validateFull()){
            BrandRepository brandRepository = new BrandRepository(jdbcTemplate);
            brandRepository.update(brand);
        }
        return "redirect:/brands";
    }

    @GetMapping("/brands/delete/{id}")
    public String brandsDeleteGet(@PathVariable(value = "id") long id, Model model){
        logger.warn("Page delete brand");
        BrandRepository brandRepository = new BrandRepository(jdbcTemplate);
        Brand brand = brandRepository.getOne(id);
        model.addAttribute("brand", brand);
        return "brand-delete";
    }

    @PostMapping("/brands/delete/{id}")
    public String brandsDeletePost(@PathVariable(value = "id") long id, Model model){
        logger.warn("Page deleting brand");
        BrandRepository brandRepository = new BrandRepository(jdbcTemplate);
        brandRepository.delete(id);
        return "redirect:/brands";
    }

}
