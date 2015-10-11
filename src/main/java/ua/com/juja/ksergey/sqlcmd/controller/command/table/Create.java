package ua.com.juja.ksergey.sqlcmd.controller.command.table;

import ua.com.juja.ksergey.sqlcmd.controller.command.Command;
import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.view.View;

/**
 * Created by user on 30.09.2015.
 */
public class Create implements Command {
    private DatabaseManager manager;
    private View view;

    public Create(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    public Create() {
    }

    @Override
    public boolean canExecute(String command) {
        return command.startsWith("create|");
    }

    @Override
    public void execute(String command) {
        view.write("В разработке."); // TODO implement create
    }

    @Override
    public String format() {
        return "\tcreate|tableName|column1|value1|column2|value2|...|columnN|valueN";
    }

    @Override
    public String description() {
        return "\t\tдля создания записи в таблице";
    }
}
