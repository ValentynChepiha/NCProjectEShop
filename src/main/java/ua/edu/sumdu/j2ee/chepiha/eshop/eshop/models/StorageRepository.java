package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Storage;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.mappers.StorageMapper;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.CreationStatementOracle;

import java.util.List;

@Repository
public class StorageRepository implements ModelRepository<Storage> {

    private final JdbcTemplate jdbcTemplate;

    public StorageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long create(Storage storage) {

        CreationStatementOracle psc = new CreationStatementOracle();
        GeneratedKeyHolder newId = new GeneratedKeyHolder();

        psc.setSql("insert into lab3_chepihavv_storage (name, id_location) values (?, ?)");
        psc.addStatement(storage.getName());
        psc.addStatement(storage.getIdLocation());

        jdbcTemplate.update(psc, newId);
        return newId.getKey().longValue();
    }

    @Override
    public void update(Storage storage) {
        String sql = "update lab3_chepihavv_storage set name = ?, id_location = ? where id = ?";
        jdbcTemplate.update(sql, storage.getName(), storage.getIdLocation(), storage.getId());
    }

    public void delete(long id) {
        String sql = "delete from lab3_chepihavv_storage where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Storage> getAll() {
        String sql = "select * from lab3_chepihavv_storage order by id";
        return jdbcTemplate.query(sql, new StorageMapper());
    }

    @Override
    public Storage getOne(long id) {
        String sql = "select * from lab3_chepihavv_storage where id=?";
        Storage storage =  jdbcTemplate.queryForObject(sql, new StorageMapper(), id);
        return storage;
    }
}
