package ua.com.juja.ksergey.sqlcmd.controller.command;

import ua.com.juja.ksergey.sqlcmd.model.DatabaseManager;
import ua.com.juja.ksergey.sqlcmd.view.View;

/**
 * Created by user on 30.09.2015.
 */
public class Drop implements Command {
    private DatabaseManager manager;
    private View view;

    public Drop(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    public Drop() {
    }

    @Override
    public boolean canExecute(String command) {
        return command.startsWith("drop|");
    }

    @Override
    public void execute(String command) {
        view.write("В разработке."); // TODO implement drop
    }

    @Override
    public String format() {
        return "\tdrop|databaseName";
    }

    @Override
    public String description() {
        return "\t\tдля удаления базы";
    }
}
