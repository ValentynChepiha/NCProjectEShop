package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {

    public Order mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong("ID"));
        order.setDOrder(resultSet.getDate("D_ORDER"));
        order.setIdClient(resultSet.getLong("ID_CLIENT"));
        return order;
    }

}
