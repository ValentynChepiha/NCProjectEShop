package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;

import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Brand;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.mappers.BrandMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

@Repository
public class BrandRepository implements ModelRepository<Brand> {

    private final JdbcTemplate jdbcTemplate;

    public BrandRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Brand brand) {
        String sql = "insert into lab3_chepihavv_brand (name, country) values (?, ?)";
        jdbcTemplate.update(sql, brand.getName(), brand.getCountry());
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
        return jdbcTemplate.query(sql, new BrandMapper());
    }

    @Override
    public Brand getOne(long id) {
        String sql = "select * from lab3_chepihavv_brand where id=?";
        Brand brand =  jdbcTemplate.queryForObject(sql, new BrandMapper(), id);
        return brand;
    }
}
