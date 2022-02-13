package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class CreationStatementOracle implements PreparedStatementCreator {

    private String sql;

    private List<String> listTypes;
    private List<Object> listObjects;

    public CreationStatementOracle() {
        listTypes = new ArrayList<>();
        listObjects = new ArrayList<>();
    }

    public void addStatement(String statement) {
        listTypes.add("string");
        listObjects.add(statement);
    }

    public void addStatement(long statement) {
        listTypes.add("long");
        listObjects.add(statement);
    }

    public void clearStatements(){
        listTypes.clear();
        listObjects.clear();
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public PreparedStatement createPreparedStatement(
            final Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql, new String[] {"id"});
        for(int i=0; i<listObjects.size(); i++){
            switch (listTypes.get(i)){
                case "string":
                    ps.setString(i+1, (String) listObjects.get(i));
                    break;
                case "long":
                    ps.setLong(i+1, (Long) listObjects.get(i));
                    break;
            }
        }
        return ps;
    }

}
