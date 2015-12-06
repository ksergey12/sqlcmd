package ua.com.juja.ksergey.sqlcmd.service;

import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;

/**
 * Created by user on 06.12.15.
 */
public interface DatabaseManagerFactory {
    DatabaseManager createDatabaseManager();
}
