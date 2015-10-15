package ua.com.juja.ksergey.sqlcmd.controller.command.table;

import ua.com.juja.ksergey.sqlcmd.controller.command.Command;
import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.view.View;

import java.util.Set;

/**
 * Created by user on 07.10.15.
 */
public class TableList implements Command {
    private DatabaseManager manager;
    private View view;

    public TableList(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canExecute(String command) {
        return command.equals("list");
    }

    @Override
    public boolean validate(String command) {
        return true;
    }

    @Override
    public boolean execute(String command) {
        view.write(manager.getTableNames().toString());
        return false;
    }

    @Override
    public String format() {
        return "list";
    }

    @Override
    public String description() {
        return "для получения списка всех таблиц подключённой базы";
    }
}
