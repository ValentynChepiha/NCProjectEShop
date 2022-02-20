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
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.LocationRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.StorageRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Location;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Storage;

import java.util.List;

@Controller
public class StorageController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private StorageRepository storageRepository;
    private LocationRepository locationRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/storages")
    public String storages(Model model){
        logger.warn("Info about all storage rendering...");
        storageRepository = new StorageRepository(jdbcTemplate);
        List<Storage> storages = storageRepository.getAll();
        model.addAttribute("storages", storages);
        return "pages/storage/all";
    }

    @GetMapping("/storages/add")
    public String storagesAddGet(Model model){
        logger.warn("Page create new storage");
        return "pages/storage/add";
    }

    @PostMapping("/storages/add")
    public String storagesAddPost(@RequestParam String storageName, @RequestParam String storageAddress,
                                Model model){
        logger.warn("Page saving new storage");

        Location location = new Location();
        location.setName("storage address");
        location.setAddress(storageAddress);
        if(location.validate()){
            locationRepository = new LocationRepository(jdbcTemplate);
            long id = locationRepository.create(location);

            Storage storage = new Storage();
            storage.setName(storageName);
            storage.setIdLocation(id);
            if(storage.validate()){
                storageRepository = new StorageRepository(jdbcTemplate);
                storageRepository.create(storage);
            }
        }
        return "redirect:/storages";
    }

    @GetMapping("/storages/edit/{id}")
    public String storagesEditGet(@PathVariable(value = "id") long id, Model model){
        logger.warn("Page edit storage");
        storageRepository = new StorageRepository(jdbcTemplate);
        Storage storage = storageRepository.getOne(id);

        locationRepository = new LocationRepository(jdbcTemplate);
        Location location = locationRepository.getOne(storage.getIdLocation());
        storage.setLocation(location);

        model.addAttribute("storage", storage);
        return "pages/storage/edit";
    }

    @PostMapping("/storages/edit")
    public String storagesEditPost(@RequestParam long storageId, @RequestParam long storageIdLocation,
                                   @RequestParam String storageName, @RequestParam String storageAddress,
                                   @RequestParam String storageNameLocation, Model model){

        logger.warn("Page updating storage");
        Storage storage = new Storage();
        storage.setId(storageId);
        storage.setName(storageName);
        storage.setIdLocation(storageIdLocation);

        Location location = new Location();
        location.setId(storageIdLocation);
        location.setName(storageNameLocation);
        location.setAddress(storageAddress);

        if(storage.validateFull() && location.validateFull() ){
            storageRepository = new StorageRepository(jdbcTemplate);
            storageRepository.update(storage);
            locationRepository = new LocationRepository(jdbcTemplate);
            locationRepository.update(location);
        }
        return "redirect:/storages";
    }

    @GetMapping("/storages/delete/{id}")
    public String storagesDeleteGet(@PathVariable(value = "id") long id, Model model){
        logger.warn("Page delete storage");

        storageRepository = new StorageRepository(jdbcTemplate);
        Storage storage = storageRepository.getOne(id);

        locationRepository = new LocationRepository(jdbcTemplate);
        Location location = locationRepository.getOne(storage.getIdLocation());
        storage.setLocation(location);

        model.addAttribute("storage", storage);
        return "pages/storage/delete";
    }

    @PostMapping("/storages/delete/{id}")
    public String storagesDeletePost(@PathVariable(value = "id") long id,
                                     @RequestParam long storageIdLocation, Model model){
        logger.warn("Page deleting storage");
        storageRepository = new StorageRepository(jdbcTemplate);
        storageRepository.delete(id);
        locationRepository = new LocationRepository(jdbcTemplate);
        locationRepository.delete(storageIdLocation);
        return "redirect:/storages";
    }

}
