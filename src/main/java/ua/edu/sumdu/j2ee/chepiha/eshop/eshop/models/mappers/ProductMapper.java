package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("ID"));
        product.setName(resultSet.getString("NAME"));
        product.setIdBrand(resultSet.getLong("ID_BRAND"));
        product.setPrice(resultSet.getFloat("PRICE"));
        product.setCount(resultSet.getInt("COUNT"));
        product.setDiscount(resultSet.getFloat("DISCOUNT"));
        product.setGift(resultSet.getLong("GIFT"));
        product.setIdStorage(resultSet.getLong("ID_STORAGE"));
        return product;
    }

}
