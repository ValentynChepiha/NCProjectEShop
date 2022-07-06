package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Order;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.mappers.OrderMapper;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.CreationStatementOracleForCreateNewEntity;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.LoggerMsg;

import java.util.List;

@Repository
public class OrderRepository implements ModelRepository<Order> {

    private static final LoggerMsg logger = new LoggerMsg(OrderRepository.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long create(Order order) {
        logger.msgDebugCreate(order.toString());
        CreationStatementOracleForCreateNewEntity psc = new CreationStatementOracleForCreateNewEntity();
        GeneratedKeyHolder newId = new GeneratedKeyHolder();

        psc.setSql("insert into lab3_chepihavv_order (d_order, id_client) values (?, ?)");
        psc.addStatement(order.getDOrder());
        psc.addStatement(order.getIdClient());

        jdbcTemplate.update(psc, newId);
        logger.msgDebugCreateNewId(newId.getKey().longValue());
        return newId.getKey().longValue();
    }

    @Override
    public void update(Order order) {
        logger.msgDebugUpdateNewValue(order.toString());
        logger.msgDebugUpdateOldValue(getOne(order.getId()).toString());
        String sql = "update lab3_chepihavv_order set d_order = ?, id_client = ? where id = ?";
        jdbcTemplate.update(sql, order.getDOrder(), order.getIdClient(), order.getId());
    }

    @Override
    public void delete(long id) {
        logger.msgDebugDelete(id);
        String sql = "delete from lab3_chepihavv_order where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Order> getAll() {
        logger.msgDebugGetAll();
        String sql = "select * from lab3_chepihavv_order order by id";
        return jdbcTemplate.query(sql, new OrderMapper());
    }

    @Override
    public Order getOne(long id) {
        logger.msgDebugGetOne(id);
        String sql = "select * from lab3_chepihavv_order where id=?";
        return jdbcTemplate.queryForObject(sql, new OrderMapper(), id);
    }
}
