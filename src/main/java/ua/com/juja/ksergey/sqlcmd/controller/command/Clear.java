package ua.com.juja.ksergey.sqlcmd.controller.command;

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

    public Clear(){
    }

    @Override
    public boolean canExecute(String command) {
        return command.startsWith("clear|");
    }

    @Override
    public void execute(String command) {
        String[] data = command.split("\\|");
        if (data.length != 2) {
            throw new IllegalArgumentException("Неверное количество параметров");
        }
        manager.clear(data[1]);

        view.write("Таблица очищена.");
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
