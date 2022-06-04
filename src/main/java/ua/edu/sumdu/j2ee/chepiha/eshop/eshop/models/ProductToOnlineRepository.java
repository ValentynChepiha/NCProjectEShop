package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelSelectRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.ProductToOnline;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.LoggerMsgService;

import java.util.List;

@Repository
public class ProductToOnlineRepository implements ModelSelectRepository<ProductToOnline> {

    private static final LoggerMsgService logger = new LoggerMsgService(ProductToOnlineRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Cacheable("products")
    public List<ProductToOnline> getAll() {
        logger.msgDebugGetAll();
        String sql = "select a.id, a.name, b.name as brand, a.price, a.count, nvl(a.discount, 0) as discount, " +
                " nvl(a.gift, 0) as id_gift, nvl(a2.name, 'empty')  as name_gift from lab3_chepihavv_product a " +
                " left join lab3_chepihavv_brand b on a.id_brand = b.id " +
                " left join lab3_chepihavv_product a2 on a.gift = a2.id";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProductToOnline.class));
    }

    @Override
    public ProductToOnline getOne(long id) {
        logger.msgDebugGetOne(id);
        // noting here
        return null;
    }
}
