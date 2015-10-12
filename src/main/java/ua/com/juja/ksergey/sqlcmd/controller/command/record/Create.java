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

    public Create() {
    }

    @Override
    public boolean canExecute(String command) {
        return command.startsWith("create");
    }

    @Override
    public void execute(String command) {
        String[] input = command.split("\\|");
        if (input.length % 2 != 0) {
            view.write("Должно быть чётное количество параметров, формат команды:\n" + format());
        } else {
            String tableName = input[1];

            DataSet dataSet = new DataSetImpl();
            for (int index = 1; index < (input.length / 2); index++) {
                String columnName = input[index * 2];
                String value = input[index * 2 + 1];

                dataSet.put(columnName, value);
            }
            manager.create(tableName, dataSet);
            view.write(String.format("Запись %s была успешно создана в таблице '%s'.", dataSet, tableName));
        }
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
