package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Client;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.mappers.ClientMapper;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.CreationStatementOracle;

import java.util.List;

@Repository
public class ClientRepository implements ModelRepository<Client> {

    private final JdbcTemplate jdbcTemplate;

    public ClientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long create(Client client) {

        CreationStatementOracle psc = new CreationStatementOracle();
        GeneratedKeyHolder newId = new GeneratedKeyHolder();

        psc.setSql("insert into lab3_chepihavv_client (name, email, phone, id_location) values (?, ?, ?, ?)");
        psc.addStatement(client.getName());
        psc.addStatement(client.getEmail());
        psc.addStatement(client.getPhone());
        psc.addStatement(client.getIdLocation());

        jdbcTemplate.update(psc, newId);
        return newId.getKey().longValue();
    }

    @Override
    public void update(Client client) {
        String sql = "update lab3_chepihavv_client set name = ?, email = ?, phone = ?, id_location = ? where id = ?";
        jdbcTemplate.update(sql, client.getName(), client.getEmail(), client.getPhone(),
                client.getIdLocation(), client.getId());
    }

    public void delete(long id) {
        String sql = "delete from lab3_chepihavv_client where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Client> getAll() {
        String sql = "select * from lab3_chepihavv_client order by id";
        return jdbcTemplate.query(sql, new ClientMapper());
    }

    @Override
    public Client getOne(long id) {
        String sql = "select * from lab3_chepihavv_client where id=?";
        Client client = jdbcTemplate.queryForObject(sql, new ClientMapper(), id);
        return client;
    }
}
