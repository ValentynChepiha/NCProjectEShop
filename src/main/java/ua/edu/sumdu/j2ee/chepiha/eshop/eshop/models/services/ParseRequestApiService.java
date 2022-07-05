package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.ClientRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.LocationRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Client;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Location;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Order;

import java.util.Date;
import java.util.Map;

@Service
public class ParseRequestApiService {

    @Autowired
    ParseDataValueService parseDataValueService;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    ProductParseDataValueService productParseDataValueService;
    @Autowired
    OrderProductsService orderProductsService;

    @Transactional
    public boolean start(String inputData) {
        Map<String, String> inputParams = parseDataValueService.parseInputParams(inputData);
        Map<Long, Integer> inputIdCountProduct = parseDataValueService.getIdCountProducts(inputParams);

        Client client = new Client();
        if( !setClient(inputParams, client) || inputIdCountProduct.size() == 0){
            return false;
        };

        client.setIdLocation( locationRepository.create(client.getLocation()) );
        client.setId( clientRepository.create(client) );

        Order order = new Order();
        order.setIdClient(client.getId());

        java.sql.Date dateNow = new java.sql.Date( new Date().getTime() );
        order.setDOrder(dateNow);
        productParseDataValueService.parseIdCountMap(order, inputIdCountProduct);

        return orderProductsService.saveOrderToDB(order);
    }

    public boolean setClient(Map<String, String> inputParams, Client client) {
        if(inputParams.get("clientName") == null || inputParams.get("clientPhone") == null
                || inputParams.get("clientEmail") == null
                || inputParams.get("clientAddress") == null ){
            return false;
        }
        client.setName(inputParams.get("clientName"));
        client.setPhone(inputParams.get("clientPhone"));
        client.setEmail(inputParams.get("clientEmail"));

        Location location = new Location();
        location.setAddress(inputParams.get("clientAddress"));
        location.setName("e-client");

        client.setLocation(location);
        return true;
    }

}
