package ua.com.juja.ksergey.sqlcmd.service;

import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.model.JDBCDatabaseManager;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 09.11.2015.
 */
public class ServiceImpl implements Service {
    private DatabaseManager manager;

    public ServiceImpl() {
        manager = new JDBCDatabaseManager();
    }

    @Override
    public List<String> commandsList() {
        return Arrays.asList("help", "menu", "connect");
    }

    @Override
    public void connect(String database, String user, String password) {
        manager.connect(database, user, password);
    }
}
