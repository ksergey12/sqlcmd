package ua.com.juja.ksergey.sqlcmd.controller.command.record;

import ua.com.juja.ksergey.sqlcmd.controller.command.Command;
import ua.com.juja.ksergey.sqlcmd.model.DataSet;
import ua.com.juja.ksergey.sqlcmd.model.DataSetImpl;
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

    @Override
    public boolean canExecute(String command) {
        return command.startsWith("create");
    }

    @Override
    public boolean validate(String command) {
        return command.split("\\|").length % 2 == 0;
    }

    @Override
    public boolean execute(String command) {
        String[] input = command.split("\\|");
        String tableName = input[1];

        DataSet dataSet = new DataSetImpl();
        for (int index = 1; index < (input.length / 2); index++) {
            String columnName = input[index * 2];
            String value = input[index * 2 + 1];
            dataSet.put(columnName, value);
        }
        manager.create(tableName, dataSet);
        view.write("Запись была успешно создана в таблице " + tableName);

        return false;
    }

    @Override
    public String format() {
        return "create|tableName|column1|value1|column2|value2|...|columnN|valueN";
    }

    @Override
    public String description() {
        return "для создания записи в таблице";
    }
}
