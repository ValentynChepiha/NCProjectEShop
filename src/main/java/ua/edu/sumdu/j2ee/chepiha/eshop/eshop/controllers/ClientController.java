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
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Location;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Client;

import java.util.List;

@Controller
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class.getName());

    @Autowired
    private ModelRepository<Client> clientRepository;
    @Autowired
    private ModelRepository<Location> locationRepository;

    @GetMapping("/clients")
    public String clients(Model model){
        logger.info("Info about all client rendering...");
        List<Client> clients = clientRepository.getAll();
        model.addAttribute("clients", clients);
        return "pages/client/all";
    }

    @GetMapping("/clients/add")
    public String clientsAddGet(Model model){
        logger.info("Page create new client");
        return "pages/client/add";
    }

    @PostMapping("/clients/add")
    public String clientsAddPost(@RequestParam String clientName, @RequestParam String clientEmail,
                                 @RequestParam String clientPhone, @RequestParam String clientAddress,
                                Model model){
        logger.info("Page saving new client");

        Location location = new Location();
        location.setName("client address");
        location.setAddress(clientAddress);

        if(location.validate()){
            long id = locationRepository.create(location);

            if(id>0){
                Client client = new Client();
                client.setName(clientName);
                client.setEmail(clientEmail);
                client.setPhone(clientPhone);
                client.setIdLocation(id);

                if(client.validate()){
                    clientRepository.create(client);
                } else {
                    locationRepository.delete(id);
                }

            }

        }
        return "redirect:/clients";
    }

    @GetMapping("/clients/edit/{id}")
    public String clientsEditGet(@PathVariable(value = "id") long id, Model model){
        logger.info("Page edit client");
        Client client = clientRepository.getOne(id);

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

        logger.info("Page updating client");
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
            clientRepository.update(client);
            locationRepository.update(location);
        }
        return "redirect:/clients";
    }

    @GetMapping("/clients/delete/{id}")
    public String clientsDeleteGet(@PathVariable(value = "id") long id, Model model){
        logger.info("Page delete client");

        Client client = clientRepository.getOne(id);

        Location location = locationRepository.getOne(client.getIdLocation());
        client.setLocation(location);

        model.addAttribute("client", client);
        return "pages/client/delete";
    }

    @PostMapping("/clients/delete/{id}")
    public String clientsDeletePost(@PathVariable(value = "id") long id, @RequestParam long clientIdLocation,
                                    Model model){
        logger.info("Page deleting client");
        clientRepository.delete(id);
        locationRepository.delete(clientIdLocation);
        return "redirect:/clients";
    }

}
