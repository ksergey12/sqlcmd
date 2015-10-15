package ua.com.juja.ksergey.sqlcmd.model;

import ua.com.juja.ksergey.sqlcmd.view.View;

import java.sql.*;
import java.util.*;

/**
 * Created by user on 30.09.2015.
 */
public class JDBCDatabaseManager implements DatabaseManager {
    private Connection connection;
    private View view;

    public JDBCDatabaseManager(View view) {
        this.view = view;
    }

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
            view.write("В этой базе нет таблиц.");
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
            view.write("Произошла ошибка:" + e.getMessage());
            return tableColumns;
        }
    }

    @Override
    public List<DataSet> getTableValues(String tableName) {
        List<DataSet> result = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM public." + tableName);
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                DataSet dataSet = new DataSetImpl();
                result.add(dataSet);
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    dataSet.put(rsmd.getColumnName(i + 1), rs.getObject(i + 1));
                }
            }
        } catch (SQLException e) {
            view.write("Произошла ошибка:" + e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public void connect(String database, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException cnf) {
            view.write("Driver could not be loaded: " + cnf);
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + database, user,
                    password);
        } catch (SQLException e) {
            view.write("Cant get connection: " + e);
            connection = null;
        }
    }

    @Override
    public void clear(String tableName) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM public." + tableName);
        } catch (SQLException e) {
            view.write("Произошла ошибка:" + e.getMessage());
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
                    "VALUES (" + values + ")");
        } catch (SQLException e) {
            view.write("Произошла ошибка:" + e.getMessage());
        }
    }

    @Override
    public void update(String tableName, int id, DataSet newValue) {
        String delimiter = "%s = ?,";
        String keys = "";
        for (String key : newValue.getNames()) {
            keys += key + delimiter;
        }
        keys = keys.substring(0, keys.length() - 1);
        String sql = "UPDATE public." + tableName + " SET " + keys + " WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            int index = 1;
            for (Object value : newValue.getValues()) {
                ps.setObject(index, value);
                index++;
            }
            ps.setInt(index, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            view.write("Произошла ошибка:" + e.getMessage());
        }
    }

    @Override
    public boolean isConnected() {
        return connection != null;
    }

    @Override
    public int getSize(String tableName) {
        throw new UnsupportedOperationException();
    }
}
