package ua.com.juja.ksergey.sqlcmd.controller.command.record;

import ua.com.juja.ksergey.sqlcmd.controller.command.Command;
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

    @Override
    public boolean canExecute(String command) {
        return command.startsWith("clear");
    }

    @Override
    public boolean execute(String command) {
        String[] input = command.split("\\|");
        if (input.length != 2) {
            view.write("Неверное количество параметров, формат команды:\n\t" + format());
        } else {
            String tableName = input[1];
            manager.clear(tableName);
            view.write("Таблица " + tableName + " очищена.");
        }
        return false;
    }

    @Override
    public String format() {
        return "clear|tableName";
    }

    @Override
    public String description() {
        return "для очистки всей таблицы";
    }
}
