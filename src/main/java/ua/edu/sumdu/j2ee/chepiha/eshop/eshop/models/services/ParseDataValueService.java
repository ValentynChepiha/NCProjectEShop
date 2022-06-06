package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.ProductToOnlineRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ParseDataValueService {

    private static final LoggerMsgService logger = new LoggerMsgService(ProductToOnlineRepository.class);

    public ParseDataValueService() {
    }

    public List<Long> convertStringToList(String data, String separator) {
        List<Long> result = new ArrayList<>();
        logger.msgDebug("data - " + data + "; separator - " + separator );
        Arrays.
                stream(data.split(separator)).
                map(Long::valueOf).
                forEach(result::add);
        logger.msgDebug("result - " + result );
        return  result;
    };

}
