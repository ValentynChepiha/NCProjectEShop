package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelOrderProductRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.OrderProduct;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.mappers.OrderProductMapper;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.CreationStatementOracleForCreateNewEntity;

import java.util.List;

@Slf4j
@Repository
public class OrderProductRepository implements ModelOrderProductRepository<OrderProduct> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long create(OrderProduct orderProduct) {
        log.debug("Create: " + orderProduct.toString());
        CreationStatementOracleForCreateNewEntity psc = new CreationStatementOracleForCreateNewEntity();
        GeneratedKeyHolder newId = new GeneratedKeyHolder();

        psc.setSql("insert into lab3_chepihavv_order_products (id_order, id_product, id_gift, discount, count) values (?, ?, ?, ?, ?)");

        psc.addStatement(orderProduct.getIdOrder());
        psc.addStatement(orderProduct.getIdProduct());
        psc.addStatement(orderProduct.getIdGift());
        psc.addStatement(orderProduct.getDiscount());
        psc.addStatement(orderProduct.getCount());

        jdbcTemplate.update(psc, newId);
        log.debug("Id new: "  + newId.getKey().longValue());
        return newId.getKey().longValue();
    }

    @Override
    public void update(OrderProduct orderProduct) {
        log.debug("Update (old value):" + getOne(orderProduct.getId()).toString());
        log.debug("Update (new value):" + orderProduct.toString());
        String sql = "update lab3_chepihavv_order_products set id_order=?, id_product=?, id_gift=?, discount=?, count=? where id = ?";
        jdbcTemplate.update(sql, orderProduct.getIdOrder(), orderProduct.getIdProduct(),
                orderProduct.getIdGift(), orderProduct.getDiscount(),
                orderProduct.getCount(), orderProduct.getId() );
    }

    @Override
    public void delete(long id) {
        log.debug("Delete by id:" + id);
        String sql = "delete from lab3_chepihavv_order_products where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteByIdOrder(long idOrder) {
        log.debug("Delete all by IdOrder: " + idOrder);
        String sql = "delete from lab3_chepihavv_order_products where id_order = ?";
        jdbcTemplate.update(sql, idOrder);
    }

    @Override
    public List<OrderProduct> getAll() {
        log.debug("Get all");
        String sql = "select * from lab3_chepihavv_order_products order by id";
        return jdbcTemplate.query(sql, new OrderProductMapper());
    }

    @Override
    public List<OrderProduct> getOrderProductsByIDOrder(long idOrder){
        log.debug("Get all by IdOrder: " + idOrder);
        String sql = "select * from lab3_chepihavv_order_products where id_order = ? order by id";
        return jdbcTemplate.query(sql, new OrderProductMapper(), idOrder);
    }

    @Override
    public List<OrderProduct> getOrderProductsWithAllProducts(long idOrder){
        log.debug("Get full info by IdOrder:" + idOrder);
        String sql = "select rownum as id, ? as id_order, p.id as id_product, p.gift as id_gift, p.discount, o.count from lab3_chepihavv_product p left join  lab3_chepihavv_order_products  o on p.id = o.id_product and o.id_order = ? order by id";
        return jdbcTemplate.query(sql, new OrderProductMapper(), idOrder, idOrder);
    }

    @Override
    public OrderProduct getOne(long id) {
        log.debug("Get by id: " + id);
        String sql = "select * from lab3_chepihavv_order_products where id=?";
        return jdbcTemplate.queryForObject(sql, new OrderProductMapper(), id);
    }
}
