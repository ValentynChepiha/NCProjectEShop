package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements RowMapper<Client> {

    public Client mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getLong("ID"));
        client.setName(resultSet.getString("NAME"));
        client.setEmail(resultSet.getString("EMAIL"));
        client.setPhone(resultSet.getString("PHONE"));
        client.setIdLocation(resultSet.getLong("ID_LOCATION"));
        return client;
    }

}
