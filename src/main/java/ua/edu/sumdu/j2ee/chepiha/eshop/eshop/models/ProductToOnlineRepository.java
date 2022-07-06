package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelSelectApiRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.ProductToOnline;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.CreationStatementOracleForSelect;

import java.util.List;

@Slf4j
@Repository
public class ProductToOnlineRepository implements ModelSelectApiRepository<ProductToOnline> {

    private final JdbcTemplate jdbcTemplate;
    private final CreationStatementOracleForSelect psc;

    @Autowired
    public ProductToOnlineRepository(JdbcTemplate jdbcTemplate, CreationStatementOracleForSelect psc) {
        this.jdbcTemplate = jdbcTemplate;
        this.psc = psc;
    }

    @Override
    @Cacheable("products")
    public List<ProductToOnline> getAll() {
        log.debug("Get all");
        String sql = "select * from LAB3_CHEPIHAVV_PROTUCT_TO_ONLINE order by id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProductToOnline.class));
    }

    @Override
    public List<ProductToOnline> getQueryList(List<Long> listId) {
        log.debug("getQueryList: get list for id's " + listId);
        psc.clearStatements();
        StringBuilder sql = new StringBuilder("select * from LAB3_CHEPIHAVV_PROTUCT_TO_ONLINE ");

        if(listId.size() > 0) {
            sql.append(" where id in (?");
            psc.addStatement( listId.get(0) );
            for(int i = 1; i < listId.size(); i++) {
                sql.append(", ?");
                psc.addStatement( listId.get(i) );
            }
            sql.append(")");
        } else {
            sql.append(" where 1=2");
        }

        log.debug("getQueryList: result sql " + sql.toString() );
        psc.setSql(sql.toString());
        return jdbcTemplate.query(psc, new BeanPropertyRowMapper<>(ProductToOnline.class));
    }

    @Override
    public ProductToOnline getOne(long id) {
        // noting here
        return null;
    }
}
