package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Storage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StorageMapper implements RowMapper<Storage> {

    public Storage mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Storage storage = new Storage();
        storage.setId(resultSet.getLong("ID"));
        storage.setName(resultSet.getString("NAME"));
        storage.setIdLocation(resultSet.getLong("ID_LOCATION"));
        return storage;
    }

}
