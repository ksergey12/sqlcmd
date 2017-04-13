package net.sqlcmd.dao;

import net.sqlcmd.model.DataSet;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 30.09.2015.
 */
public interface DatabaseManager {

    DatabaseManager NULL = new NullDatabaseManager();

    Set<String> getTableNames();

    List<DataSet> getTableValues(String tableName);

    Set<String> getTableColumns(String tableName);

    void connect(String database, String userName, String password);

    void clear(String tableName);

    void create(String tableName, DataSet input);

    boolean createDatabase(String database);

    void dropDatabase(String database);

    boolean isConnected();

    void update(String tableName, DataSet input, int id);

    void dropTable(String tableName);

    void createTable(String tableName);
}
