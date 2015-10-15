package ua.com.juja.ksergey.sqlcmd.controller.command;

import ua.com.juja.ksergey.sqlcmd.view.View;

/**
 * Created by user on 30.09.2015.
 */
public class Exit implements Command {

    private View view;

    public Exit(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String command) {
        return command.equals("exit");
    }

    @Override
    public boolean validate(String command) {
        return true;
    }

    @Override
    public boolean execute(String command) {
        view.write("До скорой встречи!");
        return true;
    }

    @Override
    public String format() {
        return "exit";
    }

    @Override
    public String description() {
        return "для выхода из программы";
    }
}
