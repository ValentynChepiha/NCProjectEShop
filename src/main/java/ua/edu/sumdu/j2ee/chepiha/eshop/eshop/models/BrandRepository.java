package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Brand;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.CreationStatementOracleForCreateNewEntity;

import java.util.List;

@Slf4j
@Repository
public class BrandRepository implements ModelRepository<Brand> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BrandRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long create(Brand brand) {
        log.debug("Create: " + brand.toString());
        CreationStatementOracleForCreateNewEntity psc = new CreationStatementOracleForCreateNewEntity();
        GeneratedKeyHolder newId = new GeneratedKeyHolder();

        psc.setSql("insert into lab3_chepihavv_brand (name, country) values (?, ?)");
        psc.addStatement(brand.getName());
        psc.addStatement(brand.getCountry());

        jdbcTemplate.update(psc, newId);
        log.debug("Id new: "  + newId.getKey().longValue());
        return newId.getKey().longValue();
    }

    @Override
    public void update(Brand brand) {
        log.debug("Update (old value):" + getOne(brand.getId()).toString());
        log.debug("Update (new value):" + brand.toString());
        String sql = "update lab3_chepihavv_brand set name = ?, country = ? where id = ?";
        jdbcTemplate.update(sql, brand.getName(), brand.getCountry(), brand.getId());
    }

    @Override
    public void delete(long id) {
        log.debug("Delete by id:" + id);
        String sql = "delete from lab3_chepihavv_brand where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Brand> getAll() {
        log.debug("Get all");
        String sql = "select * from lab3_chepihavv_brand order by id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Brand.class));
    }

    @Override
    public Brand getOne(long id) {
        log.debug("Get by id: " + id);
        String sql = "select * from lab3_chepihavv_brand where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Brand.class), id);
    }
}
