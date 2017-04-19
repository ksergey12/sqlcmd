package net.sqlcmd.dao;

import net.sqlcmd.model.DataSet;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 13.04.2017.
 */
public class NullDatabaseManager implements DatabaseManager {
    @Override
    public Set<String> getTableNames() {
        return new HashSet<>();
    }

    @Override
    public List<DataSet> getTableValues(String tableName) {
        return new LinkedList<>();
    }

    @Override
    public Set<String> getTableColumns(String tableName) {
        return new HashSet<>();
    }

    @Override
    public void connect(String database, String userName, String password) {
        // do nothing
    }

    @Override
    public void clear(String tableName) {
        // do nothing
    }

    @Override
    public void create(String tableName, DataSet input) {
        // do nothing
    }

    @Override
    public void createDatabase(String database) {
        // do nothing
    }

    @Override
    public void dropDatabase(String database) {
        // do nothing
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void update(String tableName, DataSet input, int id) {
        // do nothing
    }

    @Override
    public void dropTable(String tableName) {
        // do nothing
    }

    @Override
    public void createTable(String tableName) {
        // do nothing
    }

    @Override
    public String getDatabaseName() {
        return "";
    }

    @Override
    public String getUserName() {
        return "";
    }
}
