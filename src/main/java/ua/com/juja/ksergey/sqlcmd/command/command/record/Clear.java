package ua.com.juja.ksergey.sqlcmd.command.command.record;

import ua.com.juja.ksergey.sqlcmd.command.command.Command;
import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.view.View;

/**
 * Created by user on 30.09.2015.
 */
public class Clear implements Command {

    private DatabaseManager manager;
    private View view;

    public Clear(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    public Clear() {
    }

    @Override
    public boolean canExecute(String command) {
        return command.startsWith("clear");
    }

    @Override
    public void execute(String command) {
        String[] input = command.split("\\|");
        if (input.length != 2) {
            view.write("Неверное количество параметров, формат команды:\n" + format());
        } else {
            String tableName = input[1];
            manager.clear(tableName);
            view.write("Таблица " + tableName + " очищена.");
        }
    }

    @Override
    public String format() {
        return "\tclear|tableName";
    }

    @Override
    public String description() {
        return "\t\tдля очистки всей таблицы";
    }
}
