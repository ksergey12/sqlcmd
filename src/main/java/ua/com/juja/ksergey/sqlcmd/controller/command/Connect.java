package ua.com.juja.ksergey.sqlcmd.controller.command;

import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.view.View;

/**
 * Created by user on 30.09.2015.
 */
public class Connect implements Command{
    private DatabaseManager manager;
    private View view;

    public Connect(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    public Connect() {
    }

    @Override
    public boolean canExecute(String command) {
        return command.startsWith("connect|");
    }

    @Override
    public void execute(String command) {
        String[] data = command.split("\\|");
        if (data.length != 4) {
            throw new IllegalArgumentException("Неверное количество параметров");
        }
        String database = data[1];
        String user = data[2];
        String password = data[3];

        manager.connect(database, user, password);
        view.write("Подключение выполнено.");
    }

    @Override
    public String format() {
        return "\tconnect|databaseName|userName|password";
    }

    @Override
    public String description() {
        return "\t\tдля подключения к базе данных";
    }
}
