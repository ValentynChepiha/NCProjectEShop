package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.User;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.CreationStatementOracle;

import java.util.List;

@Repository
public class UserRepository implements ModelRepository<User> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long create(User user) {
        CreationStatementOracle psc = new CreationStatementOracle();
        GeneratedKeyHolder newId = new GeneratedKeyHolder();

        psc.setSql("insert into lab3_chepihavv_user(login, password, authority) values (?, ?, ?)");
        psc.addStatement(user.getLogin());
        psc.addStatement(user.getPassword());
        psc.addStatement(user.getAuthority());

        jdbcTemplate.update(psc, newId);
        return newId.getKey().longValue();
    }

    @Override
    public void update(User user) {
        String sql = "update lab3_chepihavv_user set password = ?, authority = ? where id = ?";
        jdbcTemplate.update(sql, user.getPassword(), user.getAuthority(), user.getId());
    }

    public void updateRole(User user) {
        String sql = "update lab3_chepihavv_user set authority = ? where id = ?";
        jdbcTemplate.update(sql, user.getAuthority(), user.getId());
    }

    public void delete(long id) {
        String sql = "delete from lab3_chepihavv_user where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<User> getAll() {
        String sql = "select * from lab3_chepihavv_user order by id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User getOne(long id) {
        String sql = "select * from lab3_chepihavv_user where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }
}
