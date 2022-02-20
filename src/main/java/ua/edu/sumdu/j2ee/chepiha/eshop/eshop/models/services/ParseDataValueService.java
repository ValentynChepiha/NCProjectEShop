package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import org.springframework.jdbc.core.JdbcTemplate;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.ProductRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Order;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.OrderProduct;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Product;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class ParseDataValueService {

    public static Date parseStringToDate(String stringDate) throws ParseException {
        SimpleDateFormat f=new SimpleDateFormat("dd.MM.yyyy");
        return new Date(f.parse(stringDate).getTime());
    }

    public static void parseBodyPage(JdbcTemplate jdbcTemplate, Order order, String bodyPage){
        ProductRepository productRepository = new ProductRepository(jdbcTemplate);

        Arrays.stream(bodyPage.split("&"))
                .forEach(element -> {
                    String[] param = element.split("=");
                    if(param[0]!=null){
                        String[] analiseParam = param[0].split("_");
                        if( "prod".equals(analiseParam[0]) && !"0".equals(param[1]) ){
                            long idProd = Long.parseLong(analiseParam[1]) ;
                            Product product =  productRepository.getOne(idProd);
                            OrderProduct orderProduct = new OrderProduct();
                            orderProduct.setProduct(product);
                            orderProduct.setCount(Integer.parseInt(param[1]));
                            order.addOrderProduct(orderProduct);
                        }
                    }
                });
    }
}
