package net.sqlcmd.service;

import net.sqlcmd.dao.DatabaseManager;

/**
 * Created by user on 06.12.15.
 */
public class DatabaseManagerFactoryImpl implements DatabaseManagerFactory {

    private String className;

    @Override
    public DatabaseManager createDatabaseManager() {
        try {
            return (DatabaseManager)Class.forName(className).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
