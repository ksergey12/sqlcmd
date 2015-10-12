package ua.com.juja.ksergey.sqlcmd.command;

import ua.com.juja.ksergey.sqlcmd.command.command.ExitException;
import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.model.JDBCDatabaseManager;
import ua.com.juja.ksergey.sqlcmd.view.Console;
import ua.com.juja.ksergey.sqlcmd.view.View;

/**
 * Created by user on 30.09.2015.
 */
public class Main {

    public static void main(String[] args) {
        try {
            View view = new Console();
            DatabaseManager manager = new JDBCDatabaseManager();
            MainController controller = new MainController(view, manager);
            controller.run();
        }catch (ExitException e){
            e.printStackTrace();
        }
    }
}
