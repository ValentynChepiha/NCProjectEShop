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
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.ClientRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Location;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Client;

import java.util.List;

@Controller
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private ClientRepository clientRepository;
    private LocationRepository locationRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/clients")
    public String clients(Model model){
        logger.warn("Info about all client rendering...");
        clientRepository = new ClientRepository(jdbcTemplate);
        List<Client> clients = clientRepository.getAll();
        model.addAttribute("clients", clients);
        return "pages/client/all";
    }

    @GetMapping("/clients/add")
    public String clientsAddGet(Model model){
        logger.warn("Page create new client");
        return "pages/client/add";
    }

    @PostMapping("/clients/add")
    public String clientsAddPost(@RequestParam String clientName, @RequestParam String clientEmail,
                                 @RequestParam String clientPhone, @RequestParam String clientAddress,
                                Model model){
        logger.warn("Page saving new client");

        Location location = new Location();
        location.setName("client address");
        location.setAddress(clientAddress);

        if(location.validate()){
            locationRepository = new LocationRepository(jdbcTemplate);
            long id = locationRepository.create(location);

            if(id>0){
                Client client = new Client();
                client.setName(clientName);
                client.setEmail(clientEmail);
                client.setPhone(clientPhone);
                client.setIdLocation(id);

                if(client.validate()){
                    clientRepository = new ClientRepository(jdbcTemplate);
                    id = clientRepository.create(client);
                } else {
                    locationRepository.delete(id);
                }

            }

        }
        return "redirect:/clients";
    }

    @GetMapping("/clients/edit/{id}")
    public String clientsEditGet(@PathVariable(value = "id") long id, Model model){
        logger.warn("Page edit client");
        clientRepository = new ClientRepository(jdbcTemplate);
        Client client = clientRepository.getOne(id);

        locationRepository = new LocationRepository(jdbcTemplate);
        Location location = locationRepository.getOne(client.getIdLocation());
        client.setLocation(location);

        model.addAttribute("client", client);
        return "pages/client/edit";
    }

    @PostMapping("/clients/edit")
    public String clientsEditPost(@RequestParam long clientId, @RequestParam long clientIdLocation,
                                  @RequestParam String clientName, @RequestParam String clientEmail,
                                  @RequestParam String clientPhone, @RequestParam String clientAddress,
                                  @RequestParam String clientNameLocation, Model model){

        logger.warn("Page updating client");
        Client client = new Client();
        client.setId(clientId);
        client.setName(clientName);
        client.setEmail(clientEmail);
        client.setPhone(clientPhone);
        client.setIdLocation(clientIdLocation);

        Location location = new Location();
        location.setId(clientIdLocation);
        location.setName(clientNameLocation);
        location.setAddress(clientAddress);

        if(client.validateFull() && location.validateFull() ){
            clientRepository = new ClientRepository(jdbcTemplate);
            clientRepository.update(client);
            locationRepository = new LocationRepository(jdbcTemplate);
            locationRepository.update(location);
        }
        return "redirect:/clients";
    }

    @GetMapping("/clients/delete/{id}")
    public String clientsDeleteGet(@PathVariable(value = "id") long id, Model model){
        logger.warn("Page delete client");

        clientRepository = new ClientRepository(jdbcTemplate);
        Client client = clientRepository.getOne(id);

        locationRepository = new LocationRepository(jdbcTemplate);
        Location location = locationRepository.getOne(client.getIdLocation());
        client.setLocation(location);

        model.addAttribute("client", client);
        return "pages/client/delete";
    }

    @PostMapping("/clients/delete/{id}")
    public String clientsDeletePost(@PathVariable(value = "id") long id, @RequestParam long clientIdLocation,
                                    Model model){
        logger.warn("Page deleting client");
        clientRepository = new ClientRepository(jdbcTemplate);
        clientRepository.delete(id);
        locationRepository = new LocationRepository(jdbcTemplate);
        locationRepository.delete(clientIdLocation);
        return "redirect:/clients";
    }

}
