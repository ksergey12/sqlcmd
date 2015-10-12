package ua.com.juja.ksergey.sqlcmd.command.command;

/**
 * Created by user on 30.09.2015.
 */
public interface Command {

    boolean canExecute(String command);

    void execute(String command);

    String format();

    String description();
}
