package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelUserRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.User;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.CreationStatementOracleForCreateNewEntity;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.LoggerMsg;

import java.util.List;

@Repository
public class UserRepository implements ModelUserRepository<User> {

    private static final LoggerMsg logger = new LoggerMsg(UserRepository.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long create(User user) {
        logger.msgDebugCreate(user.toString());
        CreationStatementOracleForCreateNewEntity psc = new CreationStatementOracleForCreateNewEntity();
        GeneratedKeyHolder newId = new GeneratedKeyHolder();

        psc.setSql("insert into lab3_chepihavv_user(login, password, authority) values (?, ?, ?)");
        psc.addStatement(user.getLogin());
        psc.addStatement(user.getPassword());
        psc.addStatement(user.getAuthority());

        jdbcTemplate.update(psc, newId);
        logger.msgDebugCreateNewId(newId.getKey().longValue());
        return newId.getKey().longValue();
    }

    @Override
    public void update(User user) {
        logger.msgDebugUpdateNewValue(user.toString());
        logger.msgDebugUpdateOldValue(getOne(user.getId()).toString());
        String sql = "update lab3_chepihavv_user set password = ?, authority = ? where id = ?";
        jdbcTemplate.update(sql, user.getPassword(), user.getAuthority(), user.getId());
    }

    @Override
    public void updateRole(User user) {
        logger.msgDebugUpdateUserRole(user.getId(), user.getAuthority());
        String sql = "update lab3_chepihavv_user set authority = ? where id = ?";
        jdbcTemplate.update(sql, user.getAuthority(), user.getId());
    }

    @Override
    public void delete(long id) {
        logger.msgDebugDelete(id);
        String sql = "delete from lab3_chepihavv_user where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<User> getAll() {
        logger.msgDebugGetAll();
        String sql = "select * from lab3_chepihavv_user order by id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User getOne(long id) {
        logger.msgDebugGetOne(id);
        String sql = "select * from lab3_chepihavv_user where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }
}
