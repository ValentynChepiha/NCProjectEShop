package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreationStatementOracle implements PreparedStatementCreator {

    private String sql;
    private List<String> listStatements;


    public CreationStatementOracle() {
        listStatements = new ArrayList<>();
    }

    public void addStatement(String statement) {
        listStatements.add(statement);
    }

    public void clearStatements(){
        listStatements.clear();
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public PreparedStatement createPreparedStatement(
            final Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql, new String[] {"id"});
        for(int i=0; i<listStatements.size(); i++){
            ps.setString(i+1, listStatements.get(i));
        }
        return ps;
    }

}
