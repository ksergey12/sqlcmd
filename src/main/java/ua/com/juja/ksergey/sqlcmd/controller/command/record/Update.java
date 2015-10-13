package ua.com.juja.ksergey.sqlcmd.controller.command.record;

import ua.com.juja.ksergey.sqlcmd.controller.command.Command;
import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.view.View;

/**
 * Created by user on 08.10.15.
 */
public class Update implements Command {
    private DatabaseManager manager;
    private View view;

    public Update(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    public Update() {
    }

    @Override
    public boolean canExecute(String command) {
        return command.startsWith("update");
    }

    @Override
    public void execute(String command) {
        // TODO implement update
    }

    @Override
    public String format() {
        return "\tupdate|tableName|column1|value1|column2|value2|...|columnN|valueN";
    }

    @Override
    public String description() {
        return "\t\tдля обновления записи в таблице";
    }
}
