package ua.com.juja.ksergey.sqlcmd.command.command;

import ua.com.juja.ksergey.sqlcmd.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 07.10.15.
 */
public class Log implements Command {
    private View view;
    private List<String> log = new ArrayList<>();

    public Log(View view, List<String> log) {
        this.view = view;
        this.log = log;
    }

    public Log() {
    }

    @Override
    public boolean canExecute(String command) {
        return command.equals("log");
    }

    @Override
    public void execute(String command) {
        if (log.isEmpty())
            view.write("Список команд пуст.");
        else {
            for (int i = 0; i < log.size(); i++) {
                view.write((i + 1) + ". " + log.get(i));
            }
        }
    }

    @Override
    public String format() {
        return "\tlog";
    }

    @Override
    public String description() {
        return "\t\tдля вывода истории введённых команд";
    }
}
