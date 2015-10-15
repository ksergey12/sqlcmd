package ua.com.juja.ksergey.sqlcmd.controller.command;

import ua.com.juja.ksergey.sqlcmd.controller.command.record.*;
import ua.com.juja.ksergey.sqlcmd.controller.command.table.*;
import ua.com.juja.ksergey.sqlcmd.view.View;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 30.09.2015.
 */
public class Help implements Command {

    private View view;

    public Help(View view) {
        this.view = view;
    }

    public Help() {
    }

    @Override
    public boolean canExecute(String command) {
        return command.equals("help");
    }

    @Override
    public void execute(String input) {
        view.write("Существующие команды:");
        List<Command> commands = new LinkedList<>();
        commands.addAll(Arrays.asList(new Connect(),
                new TableList(), new Clear(), new Show(),
                new Create(), new Update(), new Help(),
                new Log(), new Exit(view)));

        for(Command command : commands){
            view.write("\t" + command.format());
            view.write("\t\t" + command.description());
        }
    }

    @Override
    public String format() {
        return "help";
    }

    @Override
    public String description() {
        return "для вывода этого списка на экран";
    }
}
