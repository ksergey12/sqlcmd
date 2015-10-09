package ua.com.juja.ksergey.sqlcmd.model;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 30.09.2015.
 */
public interface DatabaseManager {

    Set<String> getTableNames();

    List<DataSet> getTableData(String tableName);

    Set<String> getTableColumns(String tableName);

    void connect(String database, String userName, String password);

    void clear(String tableName);

    void create(String tableName, DataSet input);

    void update(String tableName, int id, DataSet newValue);

    boolean isConnected();

    int getSize(String tableName);
}