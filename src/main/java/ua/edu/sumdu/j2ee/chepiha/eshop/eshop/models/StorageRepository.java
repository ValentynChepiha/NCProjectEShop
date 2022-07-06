package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Storage;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.CreationStatementOracleForCreateNewEntity;

import java.util.List;

@Slf4j
@Repository
public class StorageRepository implements ModelRepository<Storage> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StorageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long create(Storage storage) {
        log.debug("Create: " + storage.toString());
        CreationStatementOracleForCreateNewEntity psc = new CreationStatementOracleForCreateNewEntity();
        GeneratedKeyHolder newId = new GeneratedKeyHolder();

        psc.setSql("insert into lab3_chepihavv_storage (name, id_location) values (?, ?)");
        psc.addStatement(storage.getName());
        psc.addStatement(storage.getIdLocation());

        jdbcTemplate.update(psc, newId);
        log.debug("Id new: "  + newId.getKey().longValue());
        return newId.getKey().longValue();
    }

    @Override
    public void update(Storage storage) {
        log.debug("Update (old value):" + getOne(storage.getId()).toString());
        log.debug("Update (new value):" + storage.toString());
        String sql = "update lab3_chepihavv_storage set name = ?, id_location = ? where id = ?";
        jdbcTemplate.update(sql, storage.getName(), storage.getIdLocation(), storage.getId());
    }

    @Override
    public void delete(long id) {
        log.debug("Delete by id:" + id);
        String sql = "delete from lab3_chepihavv_storage where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Storage> getAll() {
        log.debug("Get all");
        String sql = "select * from lab3_chepihavv_storage order by id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Storage.class));
    }

    @Override
    public Storage getOne(long id) {
        log.debug("Get by id: " + id);
        String sql = "select * from lab3_chepihavv_storage where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Storage.class), id);
    }
}
