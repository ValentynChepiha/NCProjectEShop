package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;

import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Brand;

import org.springframework.jdbc.core.JdbcTemplate;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.CreationStatementOracle;

import java.util.List;

@Repository
public class BrandRepository implements ModelRepository<Brand> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long create(Brand brand) {
        CreationStatementOracle psc = new CreationStatementOracle();
        GeneratedKeyHolder newId = new GeneratedKeyHolder();

        psc.setSql("insert into lab3_chepihavv_brand (name, country) values (?, ?)");
        psc.addStatement(brand.getName());
        psc.addStatement(brand.getCountry());

        jdbcTemplate.update(psc, newId);
        return newId.getKey().longValue();
    }

    @Override
    public void update(Brand brand) {
        String sql = "update lab3_chepihavv_brand set name = ?, country = ? where id = ?";
        jdbcTemplate.update(sql, brand.getName(), brand.getCountry(), brand.getId());
    }

    public void delete(long id) {
        String sql = "delete from lab3_chepihavv_brand where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Brand> getAll() {
        String sql = "select * from lab3_chepihavv_brand order by id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Brand.class));
    }

    @Override
    public Brand getOne(long id) {
        String sql = "select * from lab3_chepihavv_brand where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Brand.class), id);
    }
}
