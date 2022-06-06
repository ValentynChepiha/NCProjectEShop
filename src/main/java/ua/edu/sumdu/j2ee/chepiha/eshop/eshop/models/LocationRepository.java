package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Location;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.CreationStatementOracleForCreateNewEntity;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.LoggerMsgService;

import java.util.List;

@Repository
public class LocationRepository implements ModelRepository<Location> {

    private static final LoggerMsgService logger = new LoggerMsgService(LocationRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long create(Location location) {
        logger.msgDebugCreate(location.toString());
        CreationStatementOracleForCreateNewEntity psc = new CreationStatementOracleForCreateNewEntity();
        GeneratedKeyHolder newId = new GeneratedKeyHolder();

        psc.setSql("insert into lab3_chepihavv_location (name, address) values (?, ?)");
        psc.addStatement(location.getName());
        psc.addStatement(location.getAddress());

        jdbcTemplate.update(psc, newId);
        logger.msgDebugCreateNewId(newId.getKey().longValue());
        return newId.getKey().longValue();
    }

    @Override
    public void update(Location location) {
        logger.msgDebugUpdateNewValue(location.toString());
        logger.msgDebugUpdateOldValue(getOne(location.getId()).toString());
        String sql = "update lab3_chepihavv_location set name = ?, address = ? where id = ?";
        jdbcTemplate.update(sql, location.getName(), location.getAddress(), location.getId());
    }

    @Override
    public void delete(long id) {
        logger.msgDebugDelete(id);
        String sql = "delete from lab3_chepihavv_location where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Location> getAll() {
        logger.msgDebugGetAll();
        String sql = "select * from lab3_chepihavv_location order by id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Location.class));
    }

    @Override
    public Location getOne(long id) {
        logger.msgDebugGetOne(id);
        String sql = "select * from lab3_chepihavv_location where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Location.class), id);
    }
}
