package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Product;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelProductRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.mappers.ProductMapper;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.CreationStatementOracle;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.LoggerMsgService;

import java.util.List;

@Repository
public class ProductRepository implements ModelProductRepository<Product> {

    private static final LoggerMsgService logger = new LoggerMsgService(ProductRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public long create(Product product) {
        logger.msgDebugCreate(product.toString());
        CreationStatementOracle psc = new CreationStatementOracle();
        GeneratedKeyHolder newId = new GeneratedKeyHolder();

        StringBuilder sqlBuilder = new StringBuilder();
        StringBuilder sqlStatement = new StringBuilder();
        sqlBuilder.append("insert into lab3_chepihavv_product (name, id_brand, price, count");
        sqlStatement.append("(?, ?, ?, ?");
        psc.addStatement(product.getName());
        psc.addStatement(product.getIdBrand());
        psc.addStatement(product.getPrice());
        psc.addStatement(product.getCount());
        if(product.getDiscount()>0){
            sqlBuilder.append(", discount");
            psc.addStatement(product.getDiscount());
            sqlStatement.append(", ?");
        }
        if(product.getGift()>0){
            sqlBuilder.append(", gift");
            psc.addStatement(product.getGift());
            sqlStatement.append(", ?");
        }
        sqlBuilder.append(", id_storage) values ");
        sqlStatement.append(", ?)");
        psc.addStatement(product.getIdStorage());

        psc.setSql(sqlBuilder.toString() + sqlStatement.toString());
        jdbcTemplate.update(psc, newId);
        logger.msgDebugCreateNewId(newId.getKey().longValue());
        return newId.getKey().longValue();
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public void update(Product product) {
        logger.msgDebugUpdateNewValue(product.toString());
        logger.msgDebugUpdateOldValue(getOne(product.getId()).toString());
        CreationStatementOracle psc = new CreationStatementOracle();
        GeneratedKeyHolder newId = new GeneratedKeyHolder();

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("update lab3_chepihavv_product set name = ?, id_brand = ?, price = ?, count = ?");
        psc.addStatement(product.getName());
        psc.addStatement(product.getIdBrand());
        psc.addStatement(product.getPrice());
        psc.addStatement(product.getCount());

        sqlBuilder.append(", discount = ?");
        psc.addStatement(product.getDiscount());

        sqlBuilder.append(", gift = ?");
        psc.addStatement(product.getGift());

        sqlBuilder.append(", id_storage = ? where id = ?");
        psc.addStatement(product.getIdStorage());
        psc.addStatement(product.getId());

        psc.setSql(sqlBuilder.toString());
        jdbcTemplate.update(psc, newId);
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public void updateCount(long id, int count){
        logger.msgDebugUpdateCountProducts(id, count);
        String sql = "update lab3_chepihavv_product set count = ? where id = ?";
        jdbcTemplate.update(sql, count, id);
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public void delete(long id) {
        logger.msgDebugDelete(id);
        String sql = "delete from lab3_chepihavv_product where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Product> getAll() {
        logger.msgDebugGetAll();
        String sql = "select * from lab3_chepihavv_product order by id";
        return jdbcTemplate.query(sql, new ProductMapper());
    }

    @Override
    public List<Product> getAllWithoutOneId(long oneId) {
        logger.msgDebugGetAllWithoutOneId(oneId);
        String sql = "select * from lab3_chepihavv_product where id != ? order by id";
        return jdbcTemplate.query(sql, new ProductMapper(), oneId);
    }

    @Override
    public Product getOne(long id) {
        logger.msgDebugGetOne(id);
        String sql = "select * from lab3_chepihavv_product where id=?";
        return jdbcTemplate.queryForObject(sql, new ProductMapper(), id);
    }

}
