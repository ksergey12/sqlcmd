package ua.com.juja.ksergey.sqlcmd.controller.command;

import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.view.View;

/**
 * Created by user on 30.09.2015.
 */
public class Delete implements Command {
    private DatabaseManager manager;
    private View view;

    public Delete(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    public Delete() {

    }

    @Override
    public boolean canExecute(String command) {
        return command.startsWith("delete|");
    }

    @Override
    public void execute(String command) {
        view.write("В разработке."); // TODO implement delete
    }

    @Override
    public String format() {
        return "\tdelete|tableName";
    }

    @Override
    public String description() {
        return "\t\tдля удаления таблицы";
    }
}
