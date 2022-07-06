package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Order;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.mappers.OrderMapper;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.CreationStatementOracleForCreateNewEntity;

import java.util.List;

@Slf4j
@Repository
public class OrderRepository implements ModelRepository<Order> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long create(Order order) {
        log.debug("Create: " + order.toString());
        CreationStatementOracleForCreateNewEntity psc = new CreationStatementOracleForCreateNewEntity();
        GeneratedKeyHolder newId = new GeneratedKeyHolder();

        psc.setSql("insert into lab3_chepihavv_order (d_order, id_client) values (?, ?)");
        psc.addStatement(order.getDOrder());
        psc.addStatement(order.getIdClient());

        jdbcTemplate.update(psc, newId);
        log.debug("Id new: "  + newId.getKey().longValue());
        return newId.getKey().longValue();
    }

    @Override
    public void update(Order order) {
        log.debug("Update (old value):" + getOne(order.getId()).toString());
        log.debug("Update (new value):" + order.toString());
        String sql = "update lab3_chepihavv_order set d_order = ?, id_client = ? where id = ?";
        jdbcTemplate.update(sql, order.getDOrder(), order.getIdClient(), order.getId());
    }

    @Override
    public void delete(long id) {
        log.debug("Delete by id:" + id);
        String sql = "delete from lab3_chepihavv_order where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Order> getAll() {
        log.debug("Get all");
        String sql = "select * from lab3_chepihavv_order order by id";
        return jdbcTemplate.query(sql, new OrderMapper());
    }

    @Override
    public Order getOne(long id) {
        log.debug("Get by id: " + id);
        String sql = "select * from lab3_chepihavv_order where id=?";
        return jdbcTemplate.queryForObject(sql, new OrderMapper(), id);
    }
}
