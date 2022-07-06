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

import java.util.List;

@Slf4j
@Controller
public class LocationController {

    private final ModelRepository<Location> locationRepository;

    @Autowired
    public LocationController(ModelRepository<Location> locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping("/locations")
    public String locations(Model model){
        log.info("Info about all locations rendering...");
        List<Location> locations = locationRepository.getAll();
        model.addAttribute("locations", locations);
        return "pages/location/all";
    }

    @GetMapping("/locations/add")
    public String locationsAddGet(Model model){
        log.info("Page create new location");
        return "pages/location/add";
    }

    @PostMapping("/locations/add")
    public String locationsAddPost(@RequestParam String locationName, @RequestParam String locationAddress,
                                  Model model){
        log.info("Page saving new location");
        Location location = new Location();
        location.setName(locationName);
        location.setAddress(locationAddress);
        if(location.validate()){
            locationRepository.create(location);
        }
        return "redirect:/locations";
    }

    @GetMapping("/locations/edit/{id}")
    public String locationsEditGet(@PathVariable(value = "id") long id, Model model){
        log.info("Page edit location");
        Location location = locationRepository.getOne(id);
        model.addAttribute("location", location);
        return "pages/location/edit";
    }

    @PostMapping("/locations/edit")
    public String locationsEditPost(@RequestParam long locationId, @RequestParam String locationName,
                                    @RequestParam String locationAddress, Model model){

        log.info("Page updating location");
        Location location = new Location();
        location.setId(locationId);
        location.setName(locationName);
        location.setAddress(locationAddress);
        if(location.validateFull()){
            locationRepository.update(location);
        }
        return "redirect:/locations";
    }

    @GetMapping("/locations/delete/{id}")
    public String locationsDeleteGet(@PathVariable(value = "id") long id, Model model){
        log.info("Page delete location");
        Location location = locationRepository.getOne(id);
        model.addAttribute("location", location);
        return "pages/location/delete";
    }

    @PostMapping("/locations/delete/{id}")
    public String locationsDeletePost(@PathVariable(value = "id") long id, Model model){
        log.info("Page deleting location");
        locationRepository.delete(id);
        return "redirect:/locations";
    }

}
