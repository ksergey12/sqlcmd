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
    public static final String line = "-----------------------";
    public static final String delimiter = "\t";

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
            printHeader(columns);
            List<DataSet> values = manager.getTableValues(tableName);
            printTable(values);
        }
    }

    private void printTable(List<DataSet> tableData) {
        for (DataSet row : tableData) {
            printRow(row);
        }
        view.write(line);
    }

    private void printRow(DataSet row) {
        List<Object> values = row.getValues();
        String result = "";
        for (Object value : values) {
            result += value + delimiter;
        }
        view.write(result);
    }

    private void printHeader(Set<String> tableColumns) {
        String header = "";
        for (String name : tableColumns) {
            header += name + delimiter;
        }
        view.write(line);
        view.write(header);
        view.write(line);
    }

    @Override
    public String format() {
        return "show|tableName";
    }

    @Override
    public String description() {
        return "для получения содержимого таблицы 'tableName'";
    }
}
