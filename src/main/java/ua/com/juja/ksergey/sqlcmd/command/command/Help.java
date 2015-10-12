package ua.com.juja.ksergey.sqlcmd.command.command;

import ua.com.juja.ksergey.sqlcmd.command.command.record.*;
import ua.com.juja.ksergey.sqlcmd.command.command.table.*;
import ua.com.juja.ksergey.sqlcmd.view.View;

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
    public void execute(String command) {
        view.write("Существующие команды:");

        Command connect = new Connect();
        view.write(connect.format());
        view.write(connect.description());

        Command tableList = new TableList();
        view.write(tableList.format());
        view.write(tableList.description());

        Command clear = new Clear();
        view.write(clear.format());
        view.write(clear.description());

        Command retrieve = new Show();
        view.write(retrieve.format());
        view.write(retrieve.description());

        Command create = new Create();
        view.write(create.format());
        view.write(create.description());

        Command update = new Update();
        view.write(update.format());
        view.write(update.description());

        Command help = new Help();
        view.write(help.format());
        view.write(help.description());

        Command log = new Log();
        view.write(log.format());
        view.write(log.description());

        Command exit = new Exit();
        view.write(exit.format());
        view.write(exit.description());
    }

    @Override
    public String format() {
        return "\thelp";
    }

    @Override
    public String description() {
        return "\t\tдля вывода этого списка на экран";
    }
}
