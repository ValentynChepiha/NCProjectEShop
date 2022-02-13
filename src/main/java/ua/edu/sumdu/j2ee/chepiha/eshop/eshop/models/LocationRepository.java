package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Location;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.mappers.LocationMapper;

import java.util.List;

@Repository
public class LocationRepository implements ModelRepository<Location> {

    private final JdbcTemplate jdbcTemplate;

    public LocationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Location location) {
        String sql = "insert into lab3_chepihavv_location (name, address) values (?, ?)";
        jdbcTemplate.update(sql, location.getName(), location.getAddress());
    }

    @Override
    public void update(Location location) {
        String sql = "update lab3_chepihavv_location set name = ?, address = ? where id = ?";
        jdbcTemplate.update(sql, location.getName(), location.getAddress(), location.getId());
    }

    public void delete(long id) {
        String sql = "delete from lab3_chepihavv_location where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Location> getAll() {
        String sql = "select * from lab3_chepihavv_location order by id";
        return jdbcTemplate.query(sql, new LocationMapper());
    }

    @Override
    public Location getOne(long id) {
        String sql = "select * from lab3_chepihavv_location where id=?";
        Location location =  jdbcTemplate.queryForObject(sql, new LocationMapper(), id);
        return location;
    }
}
