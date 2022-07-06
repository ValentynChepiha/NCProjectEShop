package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.ProductToOnlineRepository;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParseDataValueService {

    private static final LoggerMsgService logger = new LoggerMsgService(ProductToOnlineRepository.class);

    public ParseDataValueService() {
    }

    public List<Long> convertStringToList(String data, String separator) {
        List<Long> result = new ArrayList<>();
        logger.msgDebug("data - " + data + "; separator - " + separator );
        try {
            Arrays.
                stream(data.split(separator)).
                map(Long::valueOf).
                forEach(result::add);
            logger.msgDebug("result - " + result );
        } catch (Exception e) {
            logger.msgError("Can't convert: '" + data +  "; separator: " + separator +  "; to List" , e);
        }
        return result;
    };

    public Map<String, String> parseInputParams (String inputData) {
        class PairString {
            final String key;
            final String value;

            public PairString(String key, String value) {
                this.key = key;
                this.value = value;
            }

            public String getKey() {
                return key;
            }

            public String getValue() {
                return value;
            }
        }
        return Arrays.stream(inputData.split("&")).
                map( item -> {
                    int idx = item.indexOf("=");
                    try {
                        return new PairString(
                                URLDecoder.decode(item.substring(0, idx), "UTF-8"),
                                URLDecoder.decode(item.substring(idx + 1), "UTF-8")
                        );
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).
                filter(Objects::nonNull).
                collect(Collectors.toMap(PairString::getKey, PairString::getValue));
    }

    public Map<Long, Integer> getIdCountProducts (Map<String, String> dataParams) {
        Map<Long, Integer> result = new HashMap<>();
        for (Map.Entry<String,String> entry : dataParams.entrySet() ) {
            if(entry.getKey().startsWith("item_")) {
                result.put(
                        Long.valueOf(entry.getKey().split("_")[1]),
                        Integer.valueOf(entry.getValue())
                );
            }
        }
        return result;
    }

}
