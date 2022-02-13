package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Brand;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandMapper implements RowMapper<Brand> {

    public Brand mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Brand brand = new Brand();
        brand.setId(resultSet.getLong("ID"));
        brand.setName(resultSet.getString("NAME"));
        brand.setCountry(resultSet.getString("COUNTRY"));
        return brand;
    }

}
