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
    public boolean execute(String command) {
        view.write(String.format("Вы не можете пользоваться командой '%s' пока " +
                "не подключитесь с помощью команды " +
                "connect|database|user|password", command));
        return false;
    }

    @Override
    public String format() {
        return null;
    }

    @Override
    public String description() {
        return null;
    }
}
