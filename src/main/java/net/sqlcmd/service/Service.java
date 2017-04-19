package net.sqlcmd.service;

import net.sqlcmd.dao.UserAction;
import net.sqlcmd.model.DataSet;
import net.sqlcmd.dao.DatabaseManager;

import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 09.11.2015.
 */
public interface Service {
    List<String> commandsList();

    DatabaseManager connect(String database, String user, String password);

    List<List<String>> showTable(DatabaseManager manager, String tableName);

    List<String> showHeader(DatabaseManager manager, String table);

    Set<String> list(DatabaseManager manager);

    void clear(DatabaseManager manager, String tableName);

    void create(DatabaseManager manager, String tableName, DataSet input);

    void update(DatabaseManager manager, String tableName, DataSet input, int id);

    void createTable(DatabaseManager manager, String tableName);

    void dropTable(DatabaseManager manager, String tableName);

    void createDatabase(DatabaseManager manager, String database);

    void dropDatabase(DatabaseManager manager, String database);

    List<UserAction> getAllFor(String user);
}
