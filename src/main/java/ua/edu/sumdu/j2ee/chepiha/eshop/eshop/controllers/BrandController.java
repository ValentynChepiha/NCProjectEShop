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

import java.util.List;

@Slf4j
@Controller
public class BrandController {

    private final ModelRepository<Brand> brandRepository;

    @Autowired
    public BrandController(ModelRepository<Brand> brandRepository) {
        this.brandRepository = brandRepository;
    }

    @GetMapping("/brands")
    public String brands(Model model){
        log.info("Info about all brands rendering...");
        List<Brand> brands = brandRepository.getAll();
        model.addAttribute("brands", brands);
        return "pages/brand/all";
    }

    @GetMapping("/brands/add")
    public String brandsAddGet(Model model){
        log.info("Page create new brand");
        return "pages/brand/add";
    }

    @PostMapping("/brands/add")
    public String brandsAddPost(@RequestParam String brandName, @RequestParam String brandCountry,
                                Model model){
        log.info("Page saving new brand");
        Brand brand = new Brand();
        brand.setName(brandName);
        brand.setCountry(brandCountry);
        if(brand.validate()){
            brandRepository.create(brand);
        }
        return "redirect:/brands";
    }

    @GetMapping("/brands/edit/{id}")
    public String brandsEditGet(@PathVariable(value = "id") long id, Model model){
        log.info("Page edit brand");
        Brand brand = brandRepository.getOne(id);
        model.addAttribute("brand", brand);
        return "pages/brand/edit";
    }

    @PostMapping("/brands/edit")
    public String brandsEditPost(@RequestParam long brandId, @RequestParam String brandName,
                                 @RequestParam String brandCountry, Model model){

        log.info("Page updating brand");
        Brand brand = new Brand();
        brand.setId(brandId);
        brand.setName(brandName);
        brand.setCountry(brandCountry);
        if(brand.validateFull()){
            brandRepository.update(brand);
        }
        return "redirect:/brands";
    }

    @GetMapping("/brands/delete/{id}")
    public String brandsDeleteGet(@PathVariable(value = "id") long id, Model model){
        log.info("Page delete brand");
        Brand brand = brandRepository.getOne(id);
        model.addAttribute("brand", brand);
        return "pages/brand/delete";
    }

    @PostMapping("/brands/delete/{id}")
    public String brandsDeletePost(@PathVariable(value = "id") long id, Model model){
        log.info("Page deleting brand");
        brandRepository.delete(id);
        return "redirect:/brands";
    }

}
