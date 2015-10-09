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
    public boolean canProcess(String command) {
        return true;
    }

    @Override
    public void process(String command) {
        view.write("Несуществующая команда: " + command);
    }

    @Override
    public String format() {
        return "";
    }

    @Override
    public String description() {
        return "";
    }
}
