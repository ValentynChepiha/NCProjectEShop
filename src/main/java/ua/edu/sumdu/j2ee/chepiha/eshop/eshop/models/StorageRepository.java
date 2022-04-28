package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Storage;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.CreationStatementOracle;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.LoggerMsgService;

import java.util.List;

@Repository
public class StorageRepository implements ModelRepository<Storage> {

    private static final LoggerMsgService logger = new LoggerMsgService(StorageRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long create(Storage storage) {
        logger.msgDebugCreate(storage.toString());
        CreationStatementOracle psc = new CreationStatementOracle();
        GeneratedKeyHolder newId = new GeneratedKeyHolder();

        psc.setSql("insert into lab3_chepihavv_storage (name, id_location) values (?, ?)");
        psc.addStatement(storage.getName());
        psc.addStatement(storage.getIdLocation());

        jdbcTemplate.update(psc, newId);
        logger.msgDebugCreateNewId(newId.getKey().longValue());
        return newId.getKey().longValue();
    }

    @Override
    public void update(Storage storage) {
        logger.msgDebugUpdateNewValue(storage.toString());
        logger.msgDebugUpdateOldValue(getOne(storage.getId()).toString());
        String sql = "update lab3_chepihavv_storage set name = ?, id_location = ? where id = ?";
        jdbcTemplate.update(sql, storage.getName(), storage.getIdLocation(), storage.getId());
    }

    @Override
    public void delete(long id) {
        logger.msgDebugDelete(id);
        String sql = "delete from lab3_chepihavv_storage where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Storage> getAll() {
        logger.msgDebugGetAll();
        String sql = "select * from lab3_chepihavv_storage order by id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Storage.class));
    }

    @Override
    public Storage getOne(long id) {
        logger.msgDebugGetOne(id);
        String sql = "select * from lab3_chepihavv_storage where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Storage.class), id);
    }
}
