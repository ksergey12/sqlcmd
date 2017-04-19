package net.sqlcmd.dao;

/**
 * Created by User on 19.04.2017.
 */
public class UserAction {

    private String user;
    private String database;
    private String action;
    private int id;

    public UserAction() {
        // do nothing
    }

    public UserAction(String user, String database, String action) {
        this.user = user;
        this.database = database;
        this.action = action;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setId(int id) {
        this.id = id;
    }
}
