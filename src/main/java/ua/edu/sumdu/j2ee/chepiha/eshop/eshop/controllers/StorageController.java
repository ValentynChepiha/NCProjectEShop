package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.LocationRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.StorageRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Location;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Storage;

@Controller
public class StorageController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/storages")
    public String storages(Model model){
        logger.info("Info about all storage rendering...");
        model.addAttribute("storages", storageRepository.getAll());
        return "pages/storage/all";
    }

    @GetMapping("/storages/add")
    public String storagesAddGet(Model model){
        logger.info("Page create new storage");
        return "pages/storage/add";
    }

    @PostMapping("/storages/add")
    public String storagesAddPost(@RequestParam String storageName, @RequestParam String storageAddress,
                                Model model){
        logger.info("Page saving new storage");

        Location location = new Location();
        location.setName("storage address");
        location.setAddress(storageAddress);
        if(location.validate()){
            long id = locationRepository.create(location);
            Storage storage = new Storage();
            storage.setName(storageName);
            storage.setIdLocation(id);
            if(storage.validate()){
                storageRepository.create(storage);
            }
        }
        return "redirect:/storages";
    }

    @GetMapping("/storages/edit/{id}")
    public String storagesEditGet(@PathVariable(value = "id") long id, Model model){
        logger.info("Page edit storage");
        Storage storage = storageRepository.getOne(id);
        storage.setLocation( locationRepository.getOne( storage.getIdLocation() ) );
        model.addAttribute("storage", storage);
        return "pages/storage/edit";
    }

    @PostMapping("/storages/edit")
    public String storagesEditPost(@RequestParam long storageId, @RequestParam long storageIdLocation,
                                   @RequestParam String storageName, @RequestParam String storageAddress,
                                   @RequestParam String storageNameLocation, Model model){

        logger.info("Page updating storage");
        Storage storage = new Storage();
        storage.setId(storageId);
        storage.setName(storageName);
        storage.setIdLocation(storageIdLocation);

        Location location = new Location();
        location.setId(storageIdLocation);
        location.setName(storageNameLocation);
        location.setAddress(storageAddress);

        if(storage.validateFull() && location.validateFull() ){
            storageRepository.update(storage);
            locationRepository.update(location);
        }
        return "redirect:/storages";
    }

    @GetMapping("/storages/delete/{id}")
    public String storagesDeleteGet(@PathVariable(value = "id") long id, Model model){
        logger.info("Page delete storage");
        Storage storage = storageRepository.getOne(id);
        storage.setLocation( locationRepository.getOne( storage.getIdLocation() ) );
        model.addAttribute("storage", storage);
        return "pages/storage/delete";
    }

    @PostMapping("/storages/delete/{id}")
    public String storagesDeletePost(@PathVariable(value = "id") long id,
                                     @RequestParam long storageIdLocation, Model model){
        logger.info("Page deleting storage");
        storageRepository.delete(id);
        locationRepository.delete(storageIdLocation);
        return "redirect:/storages";
    }

}
