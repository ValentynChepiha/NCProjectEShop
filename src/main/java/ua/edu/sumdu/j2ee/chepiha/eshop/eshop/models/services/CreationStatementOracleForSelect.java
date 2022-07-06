package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CreationStatementOracleForSelect implements PreparedStatementCreator {

    private String sql;

    private final List<String> listTypes;
    private final List<Object> listObjects;

    public CreationStatementOracleForSelect() {
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

    public void addStatement(int statement) {
        listTypes.add("int");
        listObjects.add(statement);
    }

    public void addStatement(float statement) {
        listTypes.add("float");
        listObjects.add(statement);
    }

    public void addStatement(Date statement) {
        listTypes.add("date");
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
        PreparedStatement ps = con.prepareStatement(sql);
        for(int i=0; i<listObjects.size(); i++){
            switch (listTypes.get(i)){
                case "string":
                    ps.setString(i+1, (String) listObjects.get(i));
                    break;
                case "long":
                    ps.setLong(i+1, (Long) listObjects.get(i));
                    break;
                case "int":
                    ps.setInt(i+1, (int) listObjects.get(i));
                    break;
                case "float":
                    ps.setFloat(i+1, (float) listObjects.get(i));
                    break;
                case "date":
                    ps.setDate(i+1, (Date) listObjects.get(i));
                    break;
            }
        }
        log.debug("SQL: " + sql);
        log.debug("Params: " + listObjects.toString());
        return ps;
    }

}
