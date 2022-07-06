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
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Location;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Storage;

@Slf4j
@Controller
public class StorageController {

    private final ModelRepository<Storage> storageRepository;
    private final ModelRepository<Location> locationRepository;

    @Autowired
    public StorageController(ModelRepository<Storage> storageRepository, ModelRepository<Location> locationRepository) {
        this.storageRepository = storageRepository;
        this.locationRepository = locationRepository;
    }

    @GetMapping("/storages")
    public String storages(Model model){
        log.info("Info about all storage rendering...");
        model.addAttribute("storages", storageRepository.getAll());
        return "pages/storage/all";
    }

    @GetMapping("/storages/add")
    public String storagesAddGet(Model model){
        log.info("Page create new storage");
        return "pages/storage/add";
    }

    @PostMapping("/storages/add")
    public String storagesAddPost(@RequestParam String storageName, @RequestParam String storageAddress,
                                Model model){
        log.info("Page saving new storage");

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
        log.info("Page edit storage");
        Storage storage = storageRepository.getOne(id);
        storage.setLocation( locationRepository.getOne( storage.getIdLocation() ) );
        model.addAttribute("storage", storage);
        return "pages/storage/edit";
    }

    @PostMapping("/storages/edit")
    public String storagesEditPost(@RequestParam long storageId, @RequestParam long storageIdLocation,
                                   @RequestParam String storageName, @RequestParam String storageAddress,
                                   @RequestParam String storageNameLocation, Model model){

        log.info("Page updating storage");
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
        log.info("Page delete storage");
        Storage storage = storageRepository.getOne(id);
        storage.setLocation( locationRepository.getOne( storage.getIdLocation() ) );
        model.addAttribute("storage", storage);
        return "pages/storage/delete";
    }

    @PostMapping("/storages/delete/{id}")
    public String storagesDeletePost(@PathVariable(value = "id") long id,
                                     @RequestParam long storageIdLocation, Model model){
        log.info("Page deleting storage");
        storageRepository.delete(id);
        locationRepository.delete(storageIdLocation);
        return "redirect:/storages";
    }

}
