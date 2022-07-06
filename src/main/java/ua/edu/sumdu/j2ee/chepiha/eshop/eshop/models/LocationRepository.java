package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Location;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.CreationStatementOracleForCreateNewEntity;

import java.util.List;

@Slf4j
@Repository
public class LocationRepository implements ModelRepository<Location> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LocationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long create(Location location) {
        log.debug("Create: " + location.toString());
        CreationStatementOracleForCreateNewEntity psc = new CreationStatementOracleForCreateNewEntity();
        GeneratedKeyHolder newId = new GeneratedKeyHolder();

        psc.setSql("insert into lab3_chepihavv_location (name, address) values (?, ?)");
        psc.addStatement(location.getName());
        psc.addStatement(location.getAddress());

        jdbcTemplate.update(psc, newId);
        log.debug("Id new: "  + newId.getKey().longValue());
        return newId.getKey().longValue();
    }

    @Override
    public void update(Location location) {
        log.debug("Update (old value):" + getOne(location.getId()).toString());
        log.debug("Update (new value):" + location.toString());
        String sql = "update lab3_chepihavv_location set name = ?, address = ? where id = ?";
        jdbcTemplate.update(sql, location.getName(), location.getAddress(), location.getId());
    }

    @Override
    public void delete(long id) {
        log.debug("Delete by id:" + id);
        String sql = "delete from lab3_chepihavv_location where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Location> getAll() {
        log.debug("Get all");
        String sql = "select * from lab3_chepihavv_location order by id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Location.class));
    }

    @Override
    public Location getOne(long id) {
        log.debug("Get by id: " + id);
        String sql = "select * from lab3_chepihavv_location where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Location.class), id);
    }
}
