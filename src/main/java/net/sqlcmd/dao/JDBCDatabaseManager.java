package net.sqlcmd.dao;

import net.sqlcmd.model.DataSet;
import net.sqlcmd.model.DataSetImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

/**
 * Created by user on 30.09.2015.
 */
@Component
@Scope(value = "prototype")
public class JDBCDatabaseManager implements DatabaseManager {
    private Connection connection;
    private JdbcTemplate template;

    public JDBCDatabaseManager() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver could not be loaded: ", e);
        }
    }

    @Override
    public void connect(String database, String user, String password) {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"
                    + database, user, password);
            template = new JdbcTemplate(new SingleConnectionDataSource(connection, false));
        } catch (SQLException e) {
            connection = null;
            template = null;
            throw new RuntimeException(
                    String.format("Can't get connection for database '%s', user '%s'",
                            database, user),
                    e);
        }
    }

    public Set<String> getTableNames() {
        return new LinkedHashSet<>(template.query("SELECT table_name FROM information_schema.tables WHERE table_schema='public' AND table_type='BASE TABLE'",
                new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return rs.getString("table_name");
                    }
                }
        ));
    }

    @Override
    public Set<String> getTableColumns(String tableName) {
        Set<String> tableColumns = new LinkedHashSet<>();
        try {
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getColumns(null, null, tableName, null);
            while (rs.next()) {
                tableColumns.add(rs.getString("column_name"));
            }
            return tableColumns;
        } catch (Exception e) {
            e.printStackTrace();
            return tableColumns;
        }
    }

    public List<DataSet> getTableValues(String tableName) {
        return template.query("SELECT * FROM public." + tableName,
                new RowMapper<DataSet>() {
                    public DataSet mapRow(ResultSet rs, int rowNum) throws SQLException {
                        ResultSetMetaData rsmd = rs.getMetaData();
                        DataSet dataSet = new DataSetImpl();
                        for (int i = 0; i < rsmd.getColumnCount(); i++) {
                            dataSet.put(rsmd.getColumnName(i + 1), rs.getObject(i + 1));
                        }
                        return dataSet;
                    }
                }
        );
    }

    @Override
    public void clear(String tableName) {
        template.execute("DELETE FROM public." + tableName);
    }

    @Override
    public void create(String tableName, DataSet input) {
        String delimiter = ",";
        String keys = "";
        for (String key : input.getNames()) {
            keys += key + delimiter;
        }
        keys = keys.substring(0, keys.length() - 1);
        String values = "";
        for (Object value : input.getValues()) {
            value = "\'" + value + "\'";
            values += value + delimiter;
        }
        values = values.substring(0, values.length() - 1);

        template.update("INSERT INTO public." + tableName + " (" + keys + ")" +
                " VALUES (" + values + ")");
    }

    @Override
    public void update(String tableName, DataSet input, int id) {
        String sql = "UPDATE \"" + tableName + "\" SET ";
        int index = 0;
        for (String element : input.getNames()) {
            sql += element + " = '" + input.get(element) + "'";

            if (input.getNames().size() > ++index) {
                sql += ",";
            }
        }
        sql += " WHERE id = " + id;

        template.update(sql);
    }

    @Override
    public void createTable(String tableName) {
        template.execute("CREATE TABLE " + tableName + "("
                + "ID serial PRIMARY KEY,"
                + "NAME VARCHAR(20) NOT NULL, "
                + "PASSWORD VARCHAR(20) NOT NULL "
                + ")");
    }

    public void dropTable(String tableName) {
        template.execute("DROP TABLE IF EXISTS " + tableName);
    }

    /**
     * @param database is a database name to create
     * @return true if database created successfully, false is exception occurs
     */
    @Override
    public void createDatabase(String database) {
        template.execute("CREATE DATABASE " + database);
    }

    @Override
    public void dropDatabase(String database) {
        template.execute("DROP DATABASE " + database);
    }

    @Override
    public boolean isConnected() {
        return connection != null;
    }
}
