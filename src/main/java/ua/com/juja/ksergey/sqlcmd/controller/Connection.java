package ua.com.juja.ksergey.sqlcmd.controller;

/**
 * Created by user on 08.01.2016.
 */
public class Connection {
    private String user;
    private String password;
    private String database;

    public Connection() {
        // do nothing
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabase() {
        return database;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
