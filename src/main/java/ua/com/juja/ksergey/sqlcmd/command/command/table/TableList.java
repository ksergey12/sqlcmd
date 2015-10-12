package ua.com.juja.ksergey.sqlcmd.command.command.table;

import ua.com.juja.ksergey.sqlcmd.command.command.Command;
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

    public TableList() {
    }

    @Override
    public boolean canExecute(String command) {
        return command.equals("list");
    }


    @Override
    public void execute(String command) {
        Set<String> tableNames = manager.getTableNames();
        String message = tableNames.toString();
        view.write(message);
    }

    @Override
    public String format() {
        return "\tlist";
    }

    @Override
    public String description() {
        return "\t\tдля получения списка всех таблиц подключённой базы";
    }
}
