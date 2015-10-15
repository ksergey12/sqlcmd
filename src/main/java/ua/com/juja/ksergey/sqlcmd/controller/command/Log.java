package ua.com.juja.ksergey.sqlcmd.controller.command;

import ua.com.juja.ksergey.sqlcmd.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 07.10.15.
 */
public class Log implements Command {
    private View view;
    private List<String> log;

    public Log(View view, List<String> log) {
        this.view = view;
        this.log = log;
    }

    @Override
    public boolean canExecute(String command) {
        return command.equals("log");
    }

    @Override
    public boolean validate(String command) {
        return true;
    }

    @Override
    public boolean execute(String command) {
        if (log.isEmpty()) {
            view.write("Список команд пуст.");
        } else {
            for (int i = 0; i < log.size(); i++) {
                view.write((i + 1) + ". " + log.get(i));
            }
        }
        return false;
    }

    @Override
    public String format() {
        return "log";
    }

    @Override
    public String description() {
        return "для вывода истории введённых команд";
    }
}
