package ua.com.juja.ksergey.sqlcmd.controller.command.record;

import ua.com.juja.ksergey.sqlcmd.controller.command.Command;
import ua.com.juja.ksergey.sqlcmd.model.DataSet;
import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.view.View;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 08.10.15.
 */
public class Show implements Command {
    private DatabaseManager manager;
    private View view;

    public Show(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    public Show() {
    }

    @Override
    public boolean canExecute(String command) {
        return command.startsWith("show");
    }

    @Override
    public void execute(String command) {
        String[] input = command.split("\\|");
        if (input.length != 2) {
            view.write("Неверное количество параметров, формат команды:\n" + format());
        } else {
            String tableName = input[1];
            Set<String> columns = manager.getTableColumns(tableName);
            view.write("---" + columns + "---");
            List<DataSet> values = manager.getTableValues(tableName);
            view.write("---" + values + "---");
        }
    }

    @Override
    public String format() {
        return "\tshow|tableName";
    }

    @Override
    public String description() {
        return "\t\tдля получения содержимого таблицы 'tableName'";
    }
}
