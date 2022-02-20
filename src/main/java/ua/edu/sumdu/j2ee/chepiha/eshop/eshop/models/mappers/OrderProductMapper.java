package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.OrderProduct;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderProductMapper implements RowMapper<OrderProduct> {

    public OrderProduct mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(resultSet.getLong("ID"));
        orderProduct.setIdOrder(resultSet.getLong("ID_ORDER"));
        orderProduct.setIdProduct(resultSet.getLong("ID_PRODUCT"));
        orderProduct.setIdGift(resultSet.getLong("ID_GIFT"));
        orderProduct.setDiscount(resultSet.getFloat("DISCOUNT"));
        orderProduct.setCount(resultSet.getInt("COUNT"));
        return orderProduct;
    }

}
