package net.sqlcmd.dao;

import java.util.List;

/**
 * Created by User on 19.04.2017.
 */
public interface UserActionsDao {
    void log(String user, String database, String action);

    List<UserAction> getAllFor(String user);
}
