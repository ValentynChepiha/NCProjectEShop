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
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Location;

import java.util.List;

@Controller
public class LocationController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/locations")
    public String locations(Model model){
        logger.warn("Info about all locations rendering...");
        LocationRepository locationRepository = new LocationRepository(jdbcTemplate);
        List<Location> locations = locationRepository.getAll();
        model.addAttribute("locations", locations);
        return "location-all";
    }

    @GetMapping("/locations/add")
    public String locationsAddGet(Model model){
        logger.warn("Page create new location");
        return "location-add";
    }

    @PostMapping("/locations/add")
    public String locationsAddPost(@RequestParam String locationName, @RequestParam String locationAddress,
                                Model model){
        logger.warn("Page saving new location");
        Location location = new Location();
        location.setName(locationName);
        location.setAddress(locationAddress);
        if(location.validate()){
            LocationRepository locationRepository = new LocationRepository(jdbcTemplate);
            long id = locationRepository.create(location);
        }
        return "redirect:/locations";
    }

    @GetMapping("/locations/edit/{id}")
    public String locationsEditGet(@PathVariable(value = "id") long id, Model model){
        logger.warn("Page edit location");
        LocationRepository locationRepository = new LocationRepository(jdbcTemplate);
        Location location = locationRepository.getOne(id);
        model.addAttribute("location", location);
        return "location-edit";
    }

    @PostMapping("/locations/edit")
    public String locationsEditPost(@RequestParam long locationId, @RequestParam String locationName,
                                 @RequestParam String locationAddress, Model model){

        logger.warn("Page updating location");
        Location location = new Location();
        location.setId(locationId);
        location.setName(locationName);
        location.setAddress(locationAddress);
        if(location.validateFull()){
            LocationRepository locationRepository = new LocationRepository(jdbcTemplate);
            locationRepository.update(location);
        }
        return "redirect:/locations";
    }

    @GetMapping("/locations/delete/{id}")
    public String locationsDeleteGet(@PathVariable(value = "id") long id, Model model){
        logger.warn("Page delete location");
        LocationRepository locationRepository = new LocationRepository(jdbcTemplate);
        Location location = locationRepository.getOne(id);
        model.addAttribute("location", location);
        return "location-delete";
    }

    @PostMapping("/locations/delete/{id}")
    public String locationsDeletePost(@PathVariable(value = "id") long id, Model model){
        logger.warn("Page deleting location");
        LocationRepository locationRepository = new LocationRepository(jdbcTemplate);
        locationRepository.delete(id);
        return "redirect:/locations";
    }

}
