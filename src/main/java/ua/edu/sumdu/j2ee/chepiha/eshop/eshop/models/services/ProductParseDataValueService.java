package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelProductRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Order;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.OrderProduct;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Product;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Map;

@Service
public class ProductParseDataValueService {

    private final ModelProductRepository<Product> productRepository;

    @Autowired
    public ProductParseDataValueService(ModelProductRepository<Product> productRepository) {
        this.productRepository = productRepository;
    }

    public Date parseStringToDate(String stringDate) throws ParseException {
        SimpleDateFormat f=new SimpleDateFormat("dd.MM.yyyy");
        return new Date(f.parse(stringDate).getTime());
    }

    public void parseBodyPage(Order order, String bodyPage){
        Arrays.stream(bodyPage.split("&"))
                .forEach(element -> {
                    String[] param = element.split("=");
                    if(param[0]!=null){
                        String[] analiseParam = param[0].split("_");
                        if( "prod".equals(analiseParam[0]) && !"0".equals(param[1]) ){
                            addOneProductToOrder(order, Long.parseLong(analiseParam[1]), Integer.parseInt(param[1]));
                        }
                    }
                });
    }

    public void parseIdCountMap (Order order, Map<Long, Integer> idCount) {
        for ( Map.Entry<Long, Integer> entry: idCount.entrySet() ) {
            addOneProductToOrder(order, entry.getKey(), entry.getValue());
        }
    }

    public void addOneProductToOrder(Order order, Long id, int count) {
        Product product =  productRepository.getOne(id);
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setCount(count);
        order.addOrderProduct(orderProduct);
    }
}
