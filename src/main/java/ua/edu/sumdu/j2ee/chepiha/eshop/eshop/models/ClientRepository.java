package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Client;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.CreationStatementOracleForCreateNewEntity;

import java.util.List;

@Slf4j
@Repository
public class ClientRepository implements ModelRepository<Client> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long create(Client client) {
        log.debug("Create: " + client.toString());

        CreationStatementOracleForCreateNewEntity psc = new CreationStatementOracleForCreateNewEntity();
        GeneratedKeyHolder newId = new GeneratedKeyHolder();

        psc.setSql("insert into lab3_chepihavv_client (name, email, phone, id_location) values (?, ?, ?, ?)");
        psc.addStatement(client.getName());
        psc.addStatement(client.getEmail());
        psc.addStatement(client.getPhone());
        psc.addStatement(client.getIdLocation());

        jdbcTemplate.update(psc, newId);
        log.debug("Id new: " + newId.getKey().longValue());
        return newId.getKey().longValue();
    }

    @Override
    public void update(Client client) {
        log.debug("Update (old value):" + getOne(client.getId()).toString());
        log.debug("Update (new value):" + client.toString());

        String sql = "update lab3_chepihavv_client set name = ?, email = ?, phone = ?, id_location = ? where id = ?";
        jdbcTemplate.update(sql, client.getName(), client.getEmail(), client.getPhone(),
                client.getIdLocation(), client.getId());
    }

    @Override
    public void delete(long id) {
        log.debug("Delete by id:" + id);
        String sql = "delete from lab3_chepihavv_client where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Client> getAll() {
        log.debug("Get all");
        String sql = "select * from lab3_chepihavv_client order by id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Client.class));
    }

    @Override
    public Client getOne(long id) {
        log.debug("Get by id: " + id);
        String sql = "select * from lab3_chepihavv_client where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Client.class), id);
    }
}
