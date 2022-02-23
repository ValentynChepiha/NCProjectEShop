package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.UserRole;

import java.util.List;

@Repository
public class UserRoleRepository implements ModelRepository<UserRole> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long create(UserRole userRole) {
        // nothing here
        return 0;
    }

    @Override
    public void update(UserRole userRole) {
        // nothing here
    }

    public void delete(long id) {
    }

    @Override
    public List<UserRole> getAll() {
        String sql = "select * from lab3_chepihavv_user_role order by id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserRole.class));
    }

    @Override
    public UserRole getOne(long id) {
        String sql = "select * from lab3_chepihavv_user_role where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserRole.class), id);
    }

    public String getOneOnlyAuthority(long id) {
        String sql = "select * from lab3_chepihavv_user_role where id=?";
        UserRole userRole = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserRole.class), id);
        return userRole.getName();
    }
}
