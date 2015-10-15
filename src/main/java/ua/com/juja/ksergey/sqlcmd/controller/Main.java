package ua.com.juja.ksergey.sqlcmd.controller;

import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.model.JDBCDatabaseManager;
import ua.com.juja.ksergey.sqlcmd.view.Console;
import ua.com.juja.ksergey.sqlcmd.view.View;

/**
 * Created by user on 30.09.2015.
 */
public class Main {

    public static void main(String[] args) {
        View view = new Console();
        DatabaseManager manager = new JDBCDatabaseManager(view);
        MainController controller = new MainController(view, manager);

        controller.run();
    }
}
