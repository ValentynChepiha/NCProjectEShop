package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelUserRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.User;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.CreationStatementOracleForCreateNewEntity;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class UserRepository implements ModelUserRepository<User> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long create(User user) {
        log.debug("Create: " + user.toString());
        CreationStatementOracleForCreateNewEntity psc = new CreationStatementOracleForCreateNewEntity();
        GeneratedKeyHolder newId = new GeneratedKeyHolder();

        psc.setSql("insert into lab3_chepihavv_user(login, password, authority) values (?, ?, ?)");
        psc.addStatement(user.getLogin());
        psc.addStatement(user.getPassword());
        psc.addStatement(user.getAuthority());

        jdbcTemplate.update(psc, newId);
        log.debug("Id new: "  + newId.getKey().longValue());
        return newId.getKey().longValue();
    }

    @Override
    public void update(User user) {
        log.debug("Update (old value):" + getOne(user.getId()).toString());
        log.debug("Update (new value):" + user.toString());
        String sql = "update lab3_chepihavv_user set password = ?, authority = ? where id = ?";
        jdbcTemplate.update(sql, user.getPassword(), user.getAuthority(), user.getId());
    }

    @Override
    public void updateRole(User user) {
        log.debug("Update by id = " + user.getId() + " role(new value): " + user.getAuthority());
        String sql = "update lab3_chepihavv_user set authority = ? where id = ?";
        jdbcTemplate.update(sql, user.getAuthority(), user.getId());
    }

    @Override
    public void delete(long id) {
        log.debug("Delete by id:" + id);
        String sql = "delete from lab3_chepihavv_user where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<User> getAll() {
        log.debug("Get all");
        String sql = "select * from lab3_chepihavv_user order by id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User getOne(long id) {
        log.debug("Get by id: " + id);
        String sql = "select * from lab3_chepihavv_user where id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User getOneByName(String login) {
        log.debug("Get by name: " + login);
        String sql = "select * from lab3_chepihavv_user where login = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), login);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}

