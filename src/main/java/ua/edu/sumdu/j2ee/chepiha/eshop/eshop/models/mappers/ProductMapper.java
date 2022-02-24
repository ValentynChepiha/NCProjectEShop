package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Product client = new Product();
        client.setId(resultSet.getLong("ID"));
        client.setName(resultSet.getString("NAME"));
        client.setIdBrand(resultSet.getLong("ID_BRAND"));
        client.setPrice(resultSet.getFloat("PRICE"));
        client.setCount(resultSet.getInt("COUNT"));
        client.setDiscount(resultSet.getFloat("DISCOUNT"));
        client.setGift(resultSet.getLong("GIFT"));
        client.setIdStorage(resultSet.getLong("ID_STORAGE"));
        return client;
    }

}
