package ua.com.juja.ksergey.sqlcmd.service;

import org.springframework.stereotype.Component;
import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.model.JDBCDatabaseManager;

/**
 * Created by user on 06.12.15.
 */
@Component
public class DatabaseManagerFactory {
    public DatabaseManager createDatabaseManager(){
        return new JDBCDatabaseManager();
    }
}
