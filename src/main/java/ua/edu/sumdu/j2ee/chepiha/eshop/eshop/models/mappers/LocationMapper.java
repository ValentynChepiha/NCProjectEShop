package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Location;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationMapper implements RowMapper<Location> {

    public Location mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Location location = new Location();
        location.setId(resultSet.getLong("ID"));
        location.setName(resultSet.getString("NAME"));
        location.setAddress(resultSet.getString("ADDRESS"));
        return location;
    }

}
