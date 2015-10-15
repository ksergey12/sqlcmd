package ua.com.juja.ksergey.sqlcmd.controller.command;

import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.view.View;

/**
 * Created by user on 30.09.2015.
 */
public class Connect implements Command {
    private DatabaseManager manager;
    private View view;

    public Connect(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canExecute(String command) {
        return command.startsWith("connect");
    }

    @Override
    public boolean execute(String command) {
        String[] data = command.split("\\|");
        if (data.length != 4) {
            view.write("Неверное количество параметров, формат команды:\n\t" + format());
        } else {
            String database = data[1];
            String user = data[2];
            String password = data[3];
            manager.connect(database, user, password);
            view.write("Подключение выполнено.");
        }
        return false;
    }

    @Override
    public String format() {
        return "connect|database|user|password";
    }

    @Override
    public String description() {
        return "для подключения к базе данных";
    }
}
