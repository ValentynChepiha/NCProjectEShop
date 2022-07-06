package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Product;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelProductRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.mappers.ProductMapper;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.CreationStatementOracleForCreateNewEntity;

import java.util.List;

@Slf4j
@Repository
public class ProductRepository implements ModelProductRepository<Product> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public long create(Product product) {
        log.debug("Create: " + product.toString());
        CreationStatementOracleForCreateNewEntity psc = new CreationStatementOracleForCreateNewEntity();
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
        log.debug("Id new: "  + newId.getKey().longValue());
        return newId.getKey().longValue();
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public void update(Product product) {
        log.debug("Update (old value):" + getOne(product.getId()).toString());
        log.debug("Update (new value):" + product.toString());
        CreationStatementOracleForCreateNewEntity psc = new CreationStatementOracleForCreateNewEntity();
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
        log.debug("Update for id = " + id + " count(new value): " + count);
        String sql = "update lab3_chepihavv_product set count = ? where id = ?";
        jdbcTemplate.update(sql, count, id);
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public void delete(long id) {
        log.debug("Delete by id:" + id);
        String sql = "delete from lab3_chepihavv_product where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Product> getAll() {
        log.debug("Get all");
        String sql = "select * from lab3_chepihavv_product order by id";
        return jdbcTemplate.query(sql, new ProductMapper());
    }

    @Override
    public List<Product> getAllWithoutOneId(long oneId) {
        log.debug("Get all without id: " + oneId);
        String sql = "select * from lab3_chepihavv_product where id != ? order by id";
        return jdbcTemplate.query(sql, new ProductMapper(), oneId);
    }

    @Override
    public Product getOne(long id) {
        log.debug("Get by id: " + id);
        String sql = "select * from lab3_chepihavv_product where id=?";
        return jdbcTemplate.queryForObject(sql, new ProductMapper(), id);
    }

}
