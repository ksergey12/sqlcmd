package net.sqlcmd.service;

import net.sqlcmd.dao.DatabaseManager;

/**
 * Created by user on 06.12.15.
 */
public interface DatabaseManagerFactory {
    DatabaseManager createDatabaseManager();
}
