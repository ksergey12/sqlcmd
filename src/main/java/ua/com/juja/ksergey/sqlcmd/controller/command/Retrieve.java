package ua.com.juja.ksergey.sqlcmd.controller.command;

import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.view.View;

/**
 * Created by user on 08.10.15.
 */
public class Retrieve implements Command {
    private DatabaseManager manager;
    private View view;

    public Retrieve(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    public Retrieve() {
    }

    @Override
    public boolean canExecute(String command) {
        return command.startsWith("table|");
    }

    @Override
    public void execute(String command) {
        view.write("В разработке."); // TODO implement table
    }

    @Override
    public String format() {
        return "\ttable|tableName";
    }

    @Override
    public String description() {
        return "\t\tдля получения содержимого таблицы 'tableName'";
    }
}
