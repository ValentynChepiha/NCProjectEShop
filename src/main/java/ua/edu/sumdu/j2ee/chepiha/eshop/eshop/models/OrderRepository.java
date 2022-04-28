package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Order;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.mappers.OrderMapper;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.CreationStatementOracle;

import java.util.List;

@Repository
public class OrderRepository implements ModelRepository<Order> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long create(Order order) {

        CreationStatementOracle psc = new CreationStatementOracle();
        GeneratedKeyHolder newId = new GeneratedKeyHolder();

        psc.setSql("insert into lab3_chepihavv_order (d_order, id_client) values (?, ?)");
        psc.addStatement(order.getDOrder());
        psc.addStatement(order.getIdClient());

        jdbcTemplate.update(psc, newId);
        return newId.getKey().longValue();
    }

    @Override
    public void update(Order order) {
        String sql = "update lab3_chepihavv_order set d_order = ?, id_client = ? where id = ?";
        jdbcTemplate.update(sql, order.getDOrder(), order.getIdClient(), order.getId());
    }

    @Override
    public void delete(long id) {
        String sql = "delete from lab3_chepihavv_order where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Order> getAll() {
        String sql = "select * from lab3_chepihavv_order order by id";
        return jdbcTemplate.query(sql, new OrderMapper());
    }

    @Override
    public Order getOne(long id) {
        String sql = "select * from lab3_chepihavv_order where id=?";
        return jdbcTemplate.queryForObject(sql, new OrderMapper(), id);
    }
}
