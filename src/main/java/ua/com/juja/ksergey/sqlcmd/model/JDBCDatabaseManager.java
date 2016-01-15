package ua.com.juja.ksergey.sqlcmd.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.sql.*;
import java.util.*;

/**
 * Created by user on 30.09.2015.
 */
public class JDBCDatabaseManager implements DatabaseManager {
    private Connection connection;
    private JdbcTemplate template;

    @Override
    public Set<String> getTableNames() {
        Set<String> tables = new LinkedHashSet<>();
        try {
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet rs = dbm.getTables(null, null, "%", new String[]{"TABLE"});
            while (rs.next()) {
                tables.add(rs.getString("table_name"));
            }
            return tables;
        } catch (SQLException e) {
            e.printStackTrace();
            return tables;
        }
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
    public void connect(String database, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver could not be loaded: ", e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + database, user,
                    password);
            template = new JdbcTemplate(new SingleConnectionDataSource(connection, false));
        } catch (SQLException e) {
            connection = null;
            throw new RuntimeException(
                    String.format("Cant get connection for database '%s', user '%s'",
                            database, user),
                    e);
        }
    }

    @Override
    public void clear(String tableName) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM public." + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(String tableName, DataSet input) {
        try (Statement stmt = connection.createStatement()) {
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
            stmt.executeUpdate("INSERT INTO public." + tableName + " (" + keys + ")" +
                    " VALUES (" + values + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(String tableName, DataSet input, int id) {
        try (Statement stmt = connection.createStatement()) {
            String sql = "UPDATE  \"" + tableName + "\" SET ";
            int index = 0;
            for (String element : input.getNames()) {
                sql += element + " = '" + input.get(element) + "'";

                if (input.getNames().size() > ++index) {
                    sql += ",";
                }
            }
            sql += " WHERE id = " + id;
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = "DROP TABLE " + tableName;
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE " + tableName + "("
                    + "ID serial PRIMARY KEY,"
                    + "NAME VARCHAR(20) NOT NULL, "
                    + "PASSWORD VARCHAR(20) NOT NULL "
                    + ")";
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createDatabase(String database) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE DATABASE " + database);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropDatabase(String database) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP DATABASE " + database);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isConnected() {
        return connection != null;
    }
}
