package net.sqlcmd.service;

import net.sqlcmd.dao.entity.UserAction;
import net.sqlcmd.dao.UserActionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import net.sqlcmd.model.DataSet;
import net.sqlcmd.dao.DatabaseManager;

import java.util.*;

/**
 * Created by User on 09.11.2015.
 */
@Component
public abstract class ServiceImpl implements Service {

    abstract DatabaseManager getManager();

    @Autowired
    private UserActionsRepository userActions;

    @Override
    public List<String> commandsList() {
        return Arrays.asList("help", "menu", "connect", "list", "exit");
    }

    @Override
    public DatabaseManager connect(String database, String user, String password) {
        DatabaseManager manager = getManager();
        manager.connect(database, user, password);
        UserAction action = new UserAction(manager.getUserName(), manager.getDatabaseName(),
                "Connect to <b>%s</b>", database);
        userActions.save(action);

        return manager;
    }

    @Override
    public List<List<String>> showTable(DatabaseManager manager, String tableName) {
        List<List<String>> result = new LinkedList<>();

        List<String> columns = new LinkedList<>(manager.getTableColumns(tableName));
        List<DataSet> tableData = manager.getTableValues(tableName);

        for (DataSet dataSet : tableData) {
            List<String> row = new ArrayList<>(columns.size());
            result.add(row);
            for (String column : columns) {
                row.add(dataSet.get(column).toString());
            }
        }
        UserAction action = new UserAction(manager.getUserName(), manager.getDatabaseName(),
                "Show table <b>%s</b>", tableName);
        userActions.save(action);

        return result;
    }

    @Override
    public List<String> showHeader(DatabaseManager manager, String table) {
        return new LinkedList<>(manager.getTableColumns(table));
    }

    @Override
    public Set<String> list(DatabaseManager manager) {
        UserAction action = new UserAction(manager.getUserName(), manager.getDatabaseName(),
                "List all tables");
        userActions.save(action);

        return manager.getTableNames();
    }

    @Override
    public void updateTableFromDataSet(DatabaseManager manager, String tableName, DataSet input) {
        manager.create(tableName, input);

        UserAction action = new UserAction(manager.getUserName(), manager.getDatabaseName(),
                "Update table <b>%s</b>", tableName);
        userActions.save(action);
    }

    @Override
    public void updateTableRecord(DatabaseManager manager, String tableName, DataSet input, String id) {
        manager.update(tableName, input, id);

        UserAction action = new UserAction(manager.getUserName(), manager.getDatabaseName(),
                "Update table row id=%s in <b>%s</b>", tableName, id);
        userActions.save(action);
    }

    @Override
    public void clear(DatabaseManager manager, String tableName) {
        manager.clear(tableName);

        UserAction action = new UserAction(manager.getUserName(),
                manager.getDatabaseName(), "Clear table <b>%s</b>", tableName);
        userActions.save(action);
    }

    @Override
    public void createTable(DatabaseManager manager, String tableName) {
        manager.createTable(tableName);

        UserAction action = new UserAction(manager.getUserName(),
                manager.getDatabaseName(), "Create table <b>%s</b>", tableName);
        userActions.save(action);
    }

    @Override
    public void dropTable(DatabaseManager manager, String tableName) {
        manager.dropTable(tableName);

        UserAction action = new UserAction(manager.getUserName(),
                manager.getDatabaseName(), "Drop table <b>%s</b>", tableName);
        userActions.save(action);
    }

    @Override
    public void createDatabase(DatabaseManager manager, String database) {
        manager.createDatabase(database);

        UserAction action = new UserAction(manager.getUserName(),
                manager.getDatabaseName(), "Create database <b>%s</b>", database);
        userActions.save(action);
    }

    @Override
    public void dropDatabase(DatabaseManager manager, String database) {
        manager.dropDatabase(database);

        UserAction action = new UserAction(manager.getUserName(),
                manager.getDatabaseName(), "Drop database <b>%s</b>", database);
        userActions.save(action);
    }

    @Override
    public List<UserAction> getAllFor(String user) {
        if (user == null)
            throw new IllegalArgumentException("User can't be null.");

        return userActions.findByUserName(user);
    }
}
