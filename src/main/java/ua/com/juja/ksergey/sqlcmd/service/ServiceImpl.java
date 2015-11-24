package ua.com.juja.ksergey.sqlcmd.service;

import ua.com.juja.ksergey.sqlcmd.model.DataSet;
import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.model.JDBCDatabaseManager;

import java.util.*;

/**
 * Created by Admin on 09.11.2015.
 */
public class ServiceImpl implements Service {

    @Override
    public List<String> commandsList() {
        return Arrays.asList("help", "menu", "connect", "list", "exit");
    }

    @Override
    public DatabaseManager connect(String database, String user, String password) {
        DatabaseManager manager = new JDBCDatabaseManager();
        manager.connect(database, user, password);
        return manager;
    }

    @Override
    public List<List<String>> showTable(DatabaseManager manager, String table) {
        List<List<String>> result = new LinkedList<>();

        List<String> columns = new LinkedList<>(manager.getTableColumns(table));
        List<DataSet> tableData = manager.getTableValues(table);

        for (DataSet dataSet : tableData) {
            List<String> row = new ArrayList<>(columns.size());
            result.add(row);
            for (String column : columns) {
                row.add(dataSet.get(column).toString());
            }
        }
        return result;
    }

    @Override
    public List<String> showHeader(DatabaseManager manager, String table) {
        return new LinkedList<>(manager.getTableColumns(table));
    }

    @Override
    public Set<String> list(DatabaseManager manager){
        return manager.getTableNames();
    }

    @Override
    public void create(DatabaseManager manager, String tableName, DataSet input){
        manager.create(tableName, input);
    }

    @Override
    public void update(DatabaseManager manager, String tableName, DataSet input, int id) {
        manager.update(tableName, input, id);
    }

    @Override
    public void clear(DatabaseManager manager,String tableName){
        manager.clear(tableName);
    }
}
