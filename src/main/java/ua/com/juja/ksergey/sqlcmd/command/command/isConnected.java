package ua.com.juja.ksergey.sqlcmd.command.command;

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
//        view.write("Отсутствует подключение к базе данных.");
        view.write(String.format("Вы не можете пользоваться командой '%s' пока " +
                "не подключитесь с помощью команды " +
                "connect|database|user|password", command));
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
