package ua.com.juja.ksergey.sqlcmd.controller.command;

import ua.com.juja.ksergey.sqlcmd.view.View;

/**
 * Created by user on 07.10.15.
 */
public class Unsupported implements Command{
    private View view;

    public Unsupported(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String command) {
        return true;
    }

    @Override
    public boolean execute(String command) {
        view.write("Несуществующая команда: " + command);
        return false;
    }

    @Override
    public String format() {
        return null;
    }

    @Override
    public String description() {
        return null;
    }
}
