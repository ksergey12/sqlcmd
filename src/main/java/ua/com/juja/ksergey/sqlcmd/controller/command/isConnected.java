package ua.com.juja.ksergey.sqlcmd.controller.command;

import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.view.View;

/**
 * Created by user on 30.09.2015.
 */
public class isConnected implements Command {
    private DatabaseManager manager;
    private View view;

    public isConnected(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canExecute(String command) {
        return !manager.isConnected();
    }

    @Override
    public void execute(String command) {
        view.write("Отсутствует подключение к базе данных.");
    }

    @Override
    public String format() {
        return "";
    }

    @Override
    public String description() {
        return "";
    }
}
