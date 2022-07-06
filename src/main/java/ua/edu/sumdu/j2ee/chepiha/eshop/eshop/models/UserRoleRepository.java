package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelUserRoleRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.UserRole;

import java.util.List;

@Slf4j
@Repository
public class UserRoleRepository implements ModelUserRoleRepository<UserRole> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRoleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long create(UserRole userRole) {
        // nothing here
        return 0;
    }

    @Override
    public void update(UserRole userRole) {
        // nothing here
    }

    @Override
    public void delete(long id) {
        // nothing here
    }

    @Override
    public List<UserRole> getAll() {
        log.debug("Get all");
        String sql = "select * from lab3_chepihavv_user_role order by id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserRole.class));
    }

    @Override
    public UserRole getOne(long id) {
        log.debug("Get by id: " + id);
        String sql = "select * from lab3_chepihavv_user_role where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserRole.class), id);
    }

    @Override
    public String getOneOnlyAuthority(long id) {
        String sql = "select * from lab3_chepihavv_user_role where id=?";
        UserRole userRole = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserRole.class), id);
        log.debug("Get authority by id: " + id + ", role: " + userRole.getName());
        return userRole.getName();
    }
}
