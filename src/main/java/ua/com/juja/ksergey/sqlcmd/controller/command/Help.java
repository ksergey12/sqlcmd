package ua.com.juja.ksergey.sqlcmd.controller.command;

import ua.com.juja.ksergey.sqlcmd.view.View;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 30.09.2015.
 */
public class Help implements Command {

    private View view;
    private List<Command> commands;

    public Help(View view) {
        this.commands = new LinkedList<>();
        this.view = view;
    }

    @Override
    public boolean canExecute(String command) {
        return command.equals("help");
    }

    @Override
    public boolean validate(String command) {
        return true;
    }

    @Override
    public boolean execute(String input) {
        view.write("Существующие команды:");
        for (Command command : commands) {
            if (command.format() != null) {
                view.write("\t" + command.format());
                view.write("\t\t" + command.description());
            }
        }
        return false;
    }

    @Override
    public String format() {
        return "help";
    }

    @Override
    public String description() {
        return "для вывода этого списка на экран";
    }

    public void addCommands(Command... commands) {
        this.commands.addAll(Arrays.asList(commands));
    }
}
