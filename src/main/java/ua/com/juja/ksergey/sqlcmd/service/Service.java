package ua.com.juja.ksergey.sqlcmd.service;

import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;

import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 09.11.2015.
 */
public interface Service {
    List<String> commandsList();

    DatabaseManager connect(String database, String user, String password);

    List<List<String>> show(DatabaseManager manager, String tableName);

    Set<String> list(DatabaseManager manager);

    void clear(DatabaseManager manager, String tableName);
}
