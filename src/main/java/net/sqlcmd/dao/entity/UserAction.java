package net.sqlcmd.dao.entity;


import javax.persistence.*;

/**
 * Created by User on 19.04.2017.
 */
@Entity
@Table(name = "user_actions", schema = "public")
public class UserAction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "db_name")
    private String dbName;

    @Column(name = "action")
    private String action;

    public UserAction() {
        // do nothing
    }

    public UserAction(String userName, String database, String action) {
        this.userName = userName;
        this.dbName = database;
        this.action = action;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
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
